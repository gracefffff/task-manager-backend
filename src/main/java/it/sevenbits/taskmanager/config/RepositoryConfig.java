package it.sevenbits.taskmanager.config;

import it.sevenbits.taskmanager.core.repository.DatabaseTasksRepository;
import it.sevenbits.taskmanager.core.repository.ITaskRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcOperations;


/**
 * this is class for configuration ITaskRepository to choose right realization of this interface
 */
@Configuration
public class RepositoryConfig {
    /**
     * this function created the realization of ITaskRepository
     *
     * @param jdbcOperations new JdbcOperations to work with database
     * @return : SimpleTaskRepository like ConcurrentHashMap
     */
    @Bean
    public ITaskRepository taskRepository(@Qualifier("tasksJdbcOperations") final JdbcOperations jdbcOperations) {
        return new DatabaseTasksRepository(jdbcOperations);
    }
}