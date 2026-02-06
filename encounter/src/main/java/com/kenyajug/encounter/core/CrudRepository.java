package com.kenyajug.encounter.core;
import java.util.Optional;
public interface CrudRepository<E,ID> {
    boolean save(E entity);
    Optional<E> findById(ID id);
    Optional<E> update(E updatedEntity);
    boolean delete(E entity);
}
