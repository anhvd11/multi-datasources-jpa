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
        basePackages = "com.example.multipledb.multipledbjpa.repository.datasource2",
        entityManagerFactoryRef = "db2EntityManager",
        transactionManagerRef = DatasourceQualify.DATASOURCE2
)
public class Datasource2Config {
    @Autowired
    private Environment env;

    @Bean("datasource2.datasource")
    public DataSource dataSource(){
        MySqlDatasource r = new MySqlDatasource();
        r.setDriverClassName("spring.datasource2.driver-class-name");
        r.setPoolName("app.datasource2");
        r.setJdbcUrl(env.getProperty("app.datasource2.url"));
        r.setMaximumPoolSize(env.getProperty("app.datasource2.max_pool_size", Integer.class));
        r.setPassword(env.getProperty("spring.datasource2.password"));
        r.setUsername(env.getProperty("spring.datasource2.username"));
        return r;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean db2EntityManager() {
        LocalContainerEntityManagerFactoryBean em
                = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan(
                new String[] { "com.example.multipledb.multipledbjpa.domain.datasource2" });

        HibernateJpaVendorAdapter vendorAdapter
                = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.dialect", env.getProperty("spring.jpa.properties.hibernate.dialect"));
        em.setJpaPropertyMap(properties);

        return em;
    }

    @Bean
    public PlatformTransactionManager db2TransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(db2EntityManager().getObject());
        return transactionManager;
    }
}
