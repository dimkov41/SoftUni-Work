package meTube.repository;

import meTube.domain.entities.Tube;
import meTube.domain.entities.User;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.RollbackException;
import java.util.List;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {
    private final EntityManager entityManager;

    @Inject
    public UserRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public boolean save(User entity) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(entity);
        try {
            this.entityManager.getTransaction().commit();
        } catch (RollbackException e){
            this.entityManager.getTransaction().rollback();
            return false;
        }
        return true;
    }

    @Override
    public Optional<User> findByName(String name) {
        try {
            return Optional.of(
                    (User) this.entityManager
                            .createQuery("SELECT u FROM users AS u WHERE u.username = :username")
                            .setParameter("username", name)
                            .getSingleResult());
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Tube> getAllTubes(String username) {
        return this.entityManager.createQuery("SELECT u.tubes FROM users AS u WHERE u.username = :username")
                .setParameter("username",username)
                .getResultList();
    }
}
