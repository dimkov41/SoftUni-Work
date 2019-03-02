package exam.repository;

import exam.domain.entity.Document;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class DocumentRepositoryImpl extends BaseRepository implements DocumentRepository {
    @Inject
    public DocumentRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Document save(Document entity) {
        return super.executeTransaction((e) -> {
            e.persist(entity);
            return entity;
        });


    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Document> getAll() {
        return super.executeTransaction((e) -> (List<Document>) e.createQuery("SELECT d FROM documents AS d")
                .getResultList());
    }

    @Override
    public Document findById(String id) {
        return super.executeTransaction((e) -> (Document) e.createQuery("SELECT d FROM documents AS d WHERE d.id = :id")
                .setParameter("id", id)
                .getSingleResult());
    }

    @Override
    public boolean deleteById(String id) {
        return super.executeTransaction((e) -> e.createQuery("DELETE FROM documents AS j WHERE j.id = :id")
                .setParameter("id", id)
                .executeUpdate()) != 0;
    }
}
