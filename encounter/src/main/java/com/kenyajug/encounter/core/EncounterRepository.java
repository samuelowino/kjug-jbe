package com.kenyajug.encounter.core;
public interface EncounterRepository<E,ID> extends CrudRepository<E,ID> {
    Result<Void> deleteById(ID id);
}

