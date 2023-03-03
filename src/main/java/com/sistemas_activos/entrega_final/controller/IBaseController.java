package com.sistemas_activos.entrega_final.controller;

import com.sistemas_activos.entrega_final.model.Base;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.Serializable;

public interface IBaseController <E extends Base, ID extends Serializable> {
    ResponseEntity<?> getAll();
    ResponseEntity<?> getAll(Pageable pageable);
    ResponseEntity<?> getOne(@PathVariable ID id);
    ResponseEntity<?> save(@RequestBody E entity, BindingResult result);
    ResponseEntity<?> update(@PathVariable ID id, @RequestBody E entity,BindingResult result);
    ResponseEntity<?> delete(@PathVariable ID id);
}
