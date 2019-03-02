package sboj.repository;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.concurrent.Callable;
import java.util.function.Function;

public abstract class BaseRepository {
    private EntityManager entityManager;

    public BaseRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    <T> T executeTransaction(Function<EntityManager, T> func) {
        T result = null;

        try {
            this.entityManager.getTransaction().begin();
            result = func.apply(this.entityManager);
            this.entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.entityManager.getTransaction().rollback();
        }
        return result;
    }
}
