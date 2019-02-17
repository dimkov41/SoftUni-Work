package meTube.repository;

import meTube.domain.entities.Tube;
import meTube.domain.entities.User;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class TubeRepositoryImpl implements TubeRepository {
    private final EntityManager entityManager;

    @Inject
    public TubeRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public boolean save(Tube entity) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(entity);
        try {
            this.entityManager.getTransaction().commit();
        } catch (Exception e){
            this.entityManager.getTransaction().rollback();
            return false;
        }
        return true;
    }

    @Override
    public Optional<Tube> findById(String id) {
        try{
            return Optional.of((Tube) this.entityManager
            .createQuery("SELECT t FROM tubes AS t WHERE t.id=:id")
            .setParameter("id",id)
            .getSingleResult());
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
