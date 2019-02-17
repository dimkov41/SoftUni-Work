package meTube.repository;

import java.util.Optional;

public interface GenericRepository<E,K> {
    boolean save (E entity);
}
