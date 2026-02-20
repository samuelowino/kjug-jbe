package com.kenyajug.encounter.core;
public interface CrudRepository<E,ID> {
    boolean save(E entity);
    Result<E> findById(ID id);
    Result<Boolean> update(E updatedEntity);
    boolean delete(E entity);
}
