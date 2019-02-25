package exam.repository;

import exam.domain.entity.Document;

import java.util.Optional;

public interface DocumentRepository extends GenericRepository<Document,String> {
    Optional<Document> findById(String id);

    boolean deleteById(String id);
}
