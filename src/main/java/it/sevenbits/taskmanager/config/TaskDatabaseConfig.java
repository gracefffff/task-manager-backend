package it.sevenbits.taskmanager.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.flyway.FlywayDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * Database config class
 */
@Configuration
public class TaskDatabaseConfig {
    /**
     * Function to create database connection
     * @return  new DataSource to work with database
     */
    @Bean
   @FlywayDataSource
    @Qualifier("tasksDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.tasks")
    public DataSource tasksDataSource() {
        return DataSourceBuilder.create().build();
    }

    /**
     * Function to get correct wrap over DataSource
     * @param tasksDataSource- object contains connection to database
     * @return new JdbcTemplate to work with database
     */
    @Bean
    @Qualifier("tasksJdbcOperations")
    public JdbcOperations tasksJdbcOperations(@Qualifier("tasksDataSource") final DataSource tasksDataSource) {
        return new JdbcTemplate(tasksDataSource);
    }
}
