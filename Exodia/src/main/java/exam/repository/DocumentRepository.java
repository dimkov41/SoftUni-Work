package exam.repository;

import exam.domain.entity.Document;

import java.util.List;
import java.util.Optional;

public interface DocumentRepository extends GenericRepository<Document,String> {
    Document findById(String id);

    boolean deleteById(String id);

    List<Document> getAll();
}
