package by.itacademy.javaenterprise.borisevich.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Configuration
@ComponentScan("by.itacademy.javaenterprise.borisevich")
public class SpringConfig {

    @Bean
    public EntityManager entityManager(@Autowired EntityManagerFactory entityManagerFactory) {
        return  entityManagerFactory.createEntityManager();
    }

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        return  Persistence.createEntityManagerFactory("by.it");
    }



}
