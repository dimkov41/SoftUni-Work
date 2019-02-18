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
            Optional<Tube> tube = Optional.of((Tube) this.entityManager
            .createQuery("SELECT t FROM tubes AS t WHERE t.id=:id")
            .setParameter("id",id)
            .getSingleResult());

            tube.ifPresent((t) ->{
                int views = t.getViews();
                incrementViews(t.getId(),views);
                t.setViews(++views);
            });

            return tube;
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Tube> findAll() {
        return this.entityManager.createQuery("SELECT t FROM tubes AS t")
                .getResultList();
    }

    private void incrementViews(String tubeId, int views){
        this.entityManager.getTransaction().begin();
        this.entityManager.createQuery("UPDATE tubes AS t SET t.views = :views WHERE t.id = :id")
                .setParameter("views",++views)
                .setParameter("id",tubeId)
                .executeUpdate();
        try {
            this.entityManager.getTransaction().commit();
        } catch (Exception e){
            this.entityManager.getTransaction().rollback();
        }
    }
}
