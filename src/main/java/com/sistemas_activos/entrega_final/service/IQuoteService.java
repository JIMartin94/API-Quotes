package com.sistemas_activos.entrega_final.service;

import com.sistemas_activos.entrega_final.model.Quote;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface IQuoteService extends IBaseService<Quote,Long>{

    List<Quote> findByOriginContaining(String filter);

    List<Quote> findWordInComments(String filter);
}
