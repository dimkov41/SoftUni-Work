package exam.repository;

import exam.domain.entity.Document;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class DocumentRepositoryImpl implements DocumentRepository {
    private final EntityManager entityManager;

    @Inject
    public DocumentRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<Document> save(Document entity) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(entity);
        try {
            this.entityManager.getTransaction().commit();
            return Optional.of(entity);
        } catch (Exception e) {
            this.entityManager.getTransaction().rollback();
            return Optional.empty();
        }
    }

    @Override
    public List<Document> getAll() {
        return this.entityManager.createQuery("SELECT d FROM documents AS d")
                .getResultList();
    }

    @Override
    public Optional<Document> findById(String id) {
        try {
            return Optional.ofNullable((Document)
                    this.entityManager.createQuery("SELECT d FROM documents AS d WHERE d.id = :id")
                            .setParameter("id", id)
                            .getSingleResult());
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public boolean deleteById(String id) {
        try {
            this.entityManager.getTransaction().begin();
            this.entityManager.createQuery("DELETE FROM documents AS j WHERE j.id = :id")
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
