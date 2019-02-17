package meTube.configuration;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class ApplicationBeanConfiguration {
    private final String PERSISTENCE_NAME = "soft_uni";

    @Produces
    public EntityManager entityManager(){
        return Persistence.createEntityManagerFactory(PERSISTENCE_NAME).createEntityManager();
    }
}
