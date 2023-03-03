package com.sistemas_activos.entrega_final.controller.Impl;

import com.sistemas_activos.entrega_final.controller.IBaseController;
import com.sistemas_activos.entrega_final.model.Base;
import com.sistemas_activos.entrega_final.service.impl.BaseServiceImpl;
import com.sistemas_activos.entrega_final.util.FormatMessage;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.apache.log4j.Logger;

public abstract class BaseControllerImpl<E extends Base, S extends BaseServiceImpl<E, Long>> implements IBaseController<E, Long> {

    @Autowired
    protected S service;

    private static final Logger logg = Logger.getLogger(BaseControllerImpl.class);

    @Override
    @Operation(summary = "Busca todos los registros")
    @GetMapping("")
    public ResponseEntity<?> getAll() {
        logg.info("Metodo GetAll");
        try {
            logg.info("obtained");
            return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
        } catch (Exception e) {
            logg.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"notice\":\"Notice. No se encontraron registros.\"}");
        }
    }

    @Override
    @GetMapping("/paged")
    @Operation(summary = "Busca los registros en una pagina")
    public ResponseEntity<?> getAll(Pageable pageable){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(service.findAll(pageable));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"404\"}");
        }
    }

    @Override
    @Operation(summary = "Busca un registro por el id")
    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        logg.info("Metodo getOne");
        try {
            E existe = service.findById(id);
            if (existe == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"No se encontraron registros con ese ID.\"}");
            }
            logg.info("obtained");
            return ResponseEntity.status(HttpStatus.OK).body(existe);
        } catch (Exception e) {
            logg.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"notice\":\"Error. Por favor, intente mas tarde.\"}");
        }
    }

    @Override
    @Operation(summary = "Agrega un registro")
    @PostMapping("")
    public ResponseEntity<?> save(@Valid @RequestBody E entity, BindingResult result) {
        logg.info("Metodo save");
        logg.info(entity.toString());
        if (result.hasErrors()) {
            logg.error("error: "+ FormatMessage.formatMessage(result));
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, FormatMessage.formatMessage(result));
        }
        try {
            logg.info("saved");
            return ResponseEntity.status(HttpStatus.CREATED).body(service.save(entity));
        } catch (Exception e) {
            logg.error("error: "+ e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Por favor, intente mas tarde.\"}");
        }
    }

    @Override
    @Operation(summary = "Actualiza un registro por el id")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody E entity, BindingResult result) {
        logg.info("Metodo update");
        logg.info(entity.toString());
        if (result.hasErrors()) {
            logg.error("error: "+ FormatMessage.formatMessage(result));
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, FormatMessage.formatMessage(result));
        }
        try {
            if (!entity.getId().equals(id)) {
//                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "{\"error\":\"Los Ids no coinciden\"}");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Los Ids no coinciden\"}");
            }
            E existe = service.findById(id);
            if (existe == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"No se encontraron registros con ID.\"}");
            }
            logg.info("updated");
            return ResponseEntity.status(HttpStatus.OK).body(service.update(id, entity));
        } catch (Exception e) {
            logg.error("error: "+ e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Por favor, intente mas tarde.\"}");
        }
    }

    @Override
    @Operation(summary = "Elimina un registro por el id")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        logg.info("Metodo delete");
        try {
            E existe = service.findById(id);
            if (existe == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"No se encontraron registros con ID.\"}");
            }
            logg.info("deleted");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(service.delete(id));
        } catch (Exception e) {
            logg.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Por favor intente mas tarde.\"}");
        }
    }
}
