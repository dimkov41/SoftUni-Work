package exam.repository;

import exam.domain.entity.User;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class UserRepositoryImpl extends BaseRepository implements UserRepository {
    private EntityManager entityManager;

    @Inject
    public UserRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public User save(User entity) {
        return super.executeTransaction((e) ->{
            e.persist(entity);
            return entity;
        });
    }

    @Override
    public User findByUsername(String username) {
        return super.executeTransaction((e) -> (User) e
                .createQuery("SELECT u FROM users AS u WHERE u.username = :username")
                .setParameter("username", username)
                .getSingleResult()
        );
    }
}
