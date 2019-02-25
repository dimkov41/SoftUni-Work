package sboj.config;

import org.modelmapper.ModelMapper;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class ApplicationConfiguration {
    private final String PERSISTENCE_NAME = "soft_uni";

    @Produces
    public EntityManager entityManager(){
        return Persistence.createEntityManagerFactory(PERSISTENCE_NAME).createEntityManager();
    }

    @Produces
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
