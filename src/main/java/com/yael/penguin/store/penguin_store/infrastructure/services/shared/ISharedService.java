package com.yael.penguin.store.penguin_store.infrastructure.services.shared;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;




public interface ISharedService<E> {

    public List<E> findAll();
    public Optional<E> findById(Long id);
    public E save(E entity);
    public Page<E> findAll(Pageable pageable);
    public void deleteById(Long id);

}
