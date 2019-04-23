package it.sevenbits.taskmanager.config;

import it.sevenbits.taskmanager.core.repository.DatabaseTasksRepository;
import it.sevenbits.taskmanager.core.repository.ITaskRepository;
import it.sevenbits.taskmanager.core.repository.SimpleTaskRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcOperations;

import java.util.concurrent.ConcurrentHashMap;

/**
 * this is class for configuration ITaskRepository to choose right realization of this interface
 */
@Configuration
public class RepositoryConfig {
    /**
     * this function created the realization of ITaskRepository
     * @return : SimpleTaskRepository like ConcurrentHashMap
     */
    @Bean
    public ITaskRepository taskRepository( @Qualifier("tasksJdbcOperations") JdbcOperations jdbcOperations) {
            return new DatabaseTasksRepository(jdbcOperations);
 //return new SimpleTaskRepository(new ConcurrentHashMap<>());
        }
}