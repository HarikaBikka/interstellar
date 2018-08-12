/**
 * 
 */
package za.co.discovery.assignment.domain.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * @author Harika, Utility calss to read data from .xlsx file and preparing the
 *         data to insert
 */
public class ExcelFileToDataSqlConfig {

	private static final Logger logger = Logger.getLogger(ExcelFileToDataSqlConfig.class);

	private static final String INSERT_INTO_ROUTES_VALUES = "INSERT INTO ROUTES (ROUTE_ID, PLANET_ORIGIN, PLANET_DESTINATION, DISTANCE) VALUES (";
	private static final String INSERT_INTO_PLANET_VALUES = "INSERT INTO PLANET (PLANET_NODE, PLANET_NAME) VALUES (";
	private static final String INSERT_INTO_TRAFFIC_VALUES = "INSERT INTO TRAFFIC (ROUTE_ID, PLANET_ORIGIN, PLANET_DESTINATION, DELAY) VALUES (";

	/*
	 * Reading the xlsx file and preparing the .sql file to insert data
	 */
	public File readXLSXFile() throws IOException {
		if (logger.isDebugEnabled()) {
			logger.debug("readXLSX File");
		}

		// reading excel file
		File insertFile = null;
		Resource excelResource = new ClassPathResource("db/sql/dataToBeImported.xlsx");
		try (FileInputStream excelFileInputStream = new FileInputStream(excelResource.getFile())) {

			XSSFWorkbook workbook = new XSSFWorkbook(excelFileInputStream);

			// create a temp file
			insertFile = File.createTempFile("insert-data", ".sql");

			Path dataToBeWrittenPath = Paths.get(insertFile.toURI());
			List<String> queriesList = new ArrayList<>();

			// preparing the planet worksheet data
			preparePlanetData(workbook.getSheetAt(0), queriesList, INSERT_INTO_PLANET_VALUES);
			
			// hardcoding the L' planet as the provided data doesnot have
			// reference.
			queriesList.add(INSERT_INTO_PLANET_VALUES + "'L''','L semi Planet');");
			
			// preparing the routes worksheet data
			preparePlanetData(workbook.getSheetAt(1), queriesList, INSERT_INTO_ROUTES_VALUES);
			
			// preparing the planet traffic data
			preparePlanetData(workbook.getSheetAt(2), queriesList, INSERT_INTO_TRAFFIC_VALUES);
			
			Files.write(dataToBeWrittenPath, queriesList, StandardCharsets.UTF_8);
			workbook.close();
		}
		return insertFile;

	}

	/*
	 * Reading data from xlsx file.
	 */
	private void preparePlanetData(XSSFSheet worksheet, List<String> queriesList, String tableQuery) {

		Iterator<Row> rows = worksheet.rowIterator();

		int rowToBeDeleted = 0;
		while (rows.hasNext()) {
			Row row = rows.next();
			// skipping the headings
			if (rowToBeDeleted == 0) {
				rows.remove();
				rowToBeDeleted++;
				continue;
			}
			StringBuilder insertQuery = new StringBuilder(tableQuery);
			Iterator<Cell> cells = row.cellIterator();

			while (cells.hasNext()) {
				Cell cell = cells.next();
				CellType type = cell.getCellTypeEnum();
				switch (type) {
				case STRING:
					String stringCellValue = cell.getStringCellValue();
					// resolving the data to support apostrophe
					if (stringCellValue.contains("'"))
						stringCellValue = stringCellValue.replace("'", "''");
					insertQuery.append("'" + stringCellValue + "',");
					break;
				case NUMERIC:
					/*
					 * 1st column is not double, hence downcasting it to integer
					 */
					if (INSERT_INTO_ROUTES_VALUES.equalsIgnoreCase(insertQuery.toString())
							|| INSERT_INTO_TRAFFIC_VALUES.equalsIgnoreCase(insertQuery.toString()))
						insertQuery.append((int) cell.getNumericCellValue() + ",");
					else
						insertQuery.append(cell.getNumericCellValue() + ",");
					break;
				default:
				}
			}

			insertQuery = insertQuery.deleteCharAt(insertQuery.length() - 1);
			insertQuery.append(");");

			queriesList.add(insertQuery.toString());
		}
	}

}