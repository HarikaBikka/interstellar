/**
 * 
 */
package za.co.discovery.assignment.domain.config;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Harika
 *
 */
@Configuration
@ComponentScan("za.co.discovery.assignment.domain.service")
@EnableJpaRepositories("za.co.discovery.assignment.domain.repository")
@EnableTransactionManagement
public class InfrastructureConfig {

	// configuring data dource
	@Bean
	public DataSource dataSource() throws URISyntaxException {
		return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.DERBY).build();
	}

	// preparing the data from excel sheet
	@Bean
	public ResourceDatabasePopulator databasePopulator() throws IOException, URISyntaxException {
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.setSqlScriptEncoding("UTF-8");
		populator.addScript(new FileSystemResource(new ExcelFileToDataSqlConfig().readXLSXFile()));
		return populator;
	}

	// inserting the data
	@Bean
	public InitializingBean populatorExecutor() {
		return new InitializingBean() {
			@Override
			public void afterPropertiesSet() throws Exception {
				DatabasePopulatorUtils.execute(databasePopulator(), dataSource());
			}
		};
	}

	// using jpa with hibernate implementation
	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setDatabase(Database.DERBY);
		vendorAdapter.setGenerateDdl(true);
		vendorAdapter.setShowSql(true);
		return vendorAdapter;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource,
			JpaVendorAdapter jpaVendorAdapter) {
		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
		factoryBean.setDataSource(dataSource);
		factoryBean.setJpaVendorAdapter(jpaVendorAdapter);
		factoryBean.setPackagesToScan("za.co.discovery.assignment.domain.entity");
		factoryBean.afterPropertiesSet();

		return factoryBean;
	}

	// registering transaction manager
	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
		return new JpaTransactionManager(emf);
	}
}
