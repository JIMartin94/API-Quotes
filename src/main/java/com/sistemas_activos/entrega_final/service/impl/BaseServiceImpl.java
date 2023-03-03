package com.sistemas_activos.entrega_final.service.impl;

import com.sistemas_activos.entrega_final.model.Base;
import com.sistemas_activos.entrega_final.repository.IBaseRepository;
import com.sistemas_activos.entrega_final.service.IBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public abstract class BaseServiceImpl<E extends Base, ID extends Serializable> implements IBaseService<E, ID> {

    @Autowired
    protected IBaseRepository<E, ID> baseRepository;

    @Override
    public List<E> findAll() throws Exception {
        try {
            return baseRepository.findAll();
        }catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Page<E> findAll(Pageable pageable) throws Exception {
        try {
            return baseRepository.findAll(pageable);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public E findById(ID id) throws Exception {
        try {
            return baseRepository.findById(id).orElse(null);
        }catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public E save(E entity) throws Exception {
        try {
            return baseRepository.save(entity);
        }catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public E update(ID id, E entity) throws Exception {
        try {
            Optional<E> entityOptional = baseRepository.findById(id);
            if (entityOptional.isPresent()){
                return baseRepository.save(entity);
            }
            return null;
        }catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public boolean delete(ID id) throws Exception {
        try {
            if(baseRepository.existsById(id)) {
                baseRepository.deleteById(id);
                return true;
            }else {
                return false;
            }
        }catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
