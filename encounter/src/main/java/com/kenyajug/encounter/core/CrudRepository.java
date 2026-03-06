package com.kenyajug.encounter.core;
public interface CrudRepository<E,ID> {
    Result<Void> save(E entity);
    Result<E> findById(ID id);
    Result<Void> update(E updatedEntity);
    Result<Void> delete(E entity);
}
