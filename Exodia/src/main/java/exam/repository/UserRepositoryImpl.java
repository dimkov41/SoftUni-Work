package exam.repository;

import exam.domain.entity.User;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {
    private final EntityManager entityManager;

    @Inject
    public UserRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<User> save(User entity) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(entity);
        try {
            this.entityManager.getTransaction().commit();
            return Optional.of(entity);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        try {
            return Optional.of((User) this.entityManager
                    .createQuery("SELECT u FROM users AS u WHERE u.username = :username")
                    .setParameter("username", username)
                    .getSingleResult());
        } catch (Exception e){
            return Optional.empty();
        }
    }
}
