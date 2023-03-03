package com.sistemas_activos.entrega_final.controller.Impl;

import com.sistemas_activos.entrega_final.model.Quote;
import com.sistemas_activos.entrega_final.service.IQuoteService;
import com.sistemas_activos.entrega_final.service.impl.QuoteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@RestController
@CrossOrigin("*")
@RequestMapping("api/v1/quotes")
public class QuoteController extends BaseControllerImpl<Quote, QuoteServiceImpl>{

    @Autowired
    private IQuoteService quoteService;

    @GetMapping("/filterOrigin")
    public ResponseEntity<?> findByOrigin(@Param("origin")String origin){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(quoteService.findByOriginContaining(origin));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/filterComments")
    public ResponseEntity<?> findByComments(@Param("comment")String comment){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(quoteService.findWordInComments(comment));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
