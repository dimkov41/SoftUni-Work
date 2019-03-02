package sboj.repository;

import sboj.domain.entity.Job;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.function.Function;

public class JobRepositoryImpl extends BaseRepository implements JobRepository {
    @Inject
    public JobRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Job save(Job entity) {
        return super.executeTransaction((e) -> {
            e.persist(entity);
            return entity;
        });
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Job> getAll() {
        return super.executeTransaction((e) -> (List<Job>) e.createQuery("SELECT j FROM jobs AS j")
                .getResultList());
    }

    @Override
    public Job findById(String id) {
        return super.executeTransaction(
                        (e) -> (Job) e.createQuery("SELECT j FROM jobs AS j WHERE j.id = :id")
                                .setParameter("id", id)
                                .getSingleResult());
    }

    public boolean deleteById(String id) {
        return super.executeTransaction((e) -> e.createQuery("DELETE FROM jobs AS j WHERE j.id = :id")
                .setParameter("id", id)
                .executeUpdate()) != 0;
    }
}
