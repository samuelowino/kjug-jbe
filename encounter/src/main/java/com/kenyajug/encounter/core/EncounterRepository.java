package com.kenyajug.encounter.core;
public interface EncounterRepository<E,ID> extends CrudRepository<E,ID> {
    boolean deleteById(ID id);
}

