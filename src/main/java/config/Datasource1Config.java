package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@PropertySource({ "classpath:application.properties" })
@EnableJpaRepositories(
        basePackages = "com.example.multipledb.multipledbjpa.repository.datasource1",
        entityManagerFactoryRef = "db1EntityManager",
        transactionManagerRef = DatasourceQualify.DATASOURCE1
)
public class Datasource1Config {
    @Autowired
    private Environment env;

    @Bean("datasource1.datasource")
    public DataSource dataSource(){
        MySqlDatasource r = new MySqlDatasource();
        r.setDriverClassName("spring.datasource.driver-class-name");
        r.setPoolName("app.datasource1");
        r.setJdbcUrl(env.getProperty("spring.datasource.url"));
        r.setMaximumPoolSize(env.getProperty("spring.datasource.max_pool_size", Integer.class));
        r.setPassword(env.getProperty("spring.datasource.password"));
        r.setUsername(env.getProperty("spring.datasource.username"));
        return r;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean db1EntityManager() {
        LocalContainerEntityManagerFactoryBean em
                = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan(
                new String[] { "com.example.multipledb.multipledbjpa.domain.datasource1" });

        HibernateJpaVendorAdapter vendorAdapter
                = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.dialect", env.getProperty("spring.jpa.properties.hibernate.dialect"));
        em.setJpaPropertyMap(properties);

        return em;
    }

    @Bean(name = DatasourceQualify.DATASOURCE1)
    public PlatformTransactionManager db1TransactionManager() {
        JpaTransactionManager transactionManager
                = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(
                db1EntityManager().getObject());
        return transactionManager;
    }
}
