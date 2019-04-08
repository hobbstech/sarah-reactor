package io.github.hobbstech.sarah_core_utils.service;

public interface AbstractCrudService<T, R> {

    R save(T t);

    R update(Long id, T t);

}
