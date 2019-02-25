package sboj.repository;

import sboj.domain.entity.Job;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JobRepositoryImpl implements JobRepository {
    private final EntityManager entityManager;

    @Inject
    public JobRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<Job> save(Job entity) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(entity);
        try {
            this.entityManager.getTransaction().commit();
            return Optional.of(entity);
        } catch (Exception e) {
            this.entityManager.getTransaction().rollback();
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public List<Job> getAll() {
        return this.entityManager.createQuery("SELECT j FROM jobs AS j")
                .getResultList();
    }

    @Override
    public Optional<Job> findById(String id) {
        try {
            return Optional.of((Job) this.entityManager.createQuery("SELECT j FROM jobs AS j WHERE j.id = :id")
                    .setParameter("id", id)
                    .getSingleResult());
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public boolean deleteById(String id) {
        try {
            this.entityManager.getTransaction().begin();
            this.entityManager.createQuery("DELETE FROM jobs AS j WHERE j.id = :id")
                    .setParameter("id",id)
                    .executeUpdate();
            this.entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            this.entityManager.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }
    }
}
