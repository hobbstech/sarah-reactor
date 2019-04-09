package io.github.hobbstech.sarah_core_utils.service;

import java.util.Collection;

public interface AbstractCrudService<T, R, ID> {

    R save(T t);

    R update(Long id, T t);

    void delete(ID id);

    Collection<R> findAll();

    R findById(ID id);

}
