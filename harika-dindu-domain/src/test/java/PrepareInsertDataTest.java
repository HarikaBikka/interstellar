import java.io.IOException;
import java.net.URISyntaxException;

import org.junit.Assert;
import org.junit.Test;

import za.co.discovery.assignment.domain.config.ExcelFileToDataSqlConfig;

/**
 * 
 */

/**
 * @author Harika
 *
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = AppConfig.class)
public class PrepareInsertDataTest {

	@Test
	public void testReadingData() throws IOException, URISyntaxException{
		Assert.assertNotNull(new ExcelFileToDataSqlConfig().readXLSXFile());
	}
}
