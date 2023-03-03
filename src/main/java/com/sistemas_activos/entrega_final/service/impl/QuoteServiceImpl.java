package com.sistemas_activos.entrega_final.service.impl;

import com.sistemas_activos.entrega_final.model.Quote;
import com.sistemas_activos.entrega_final.repository.IQuotesRepository;
import com.sistemas_activos.entrega_final.service.IQuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuoteServiceImpl extends BaseServiceImpl<Quote,Long> implements IQuoteService{

    @Autowired
    private IQuotesRepository quotesRepository;

    @Override
    public List<Quote> findByOriginContaining(String filter) {
        return quotesRepository.findByOriginContaining(filter);
    }

    @Override
    public List<Quote> findWordInComments(String filter) {
        return quotesRepository.findWordInComments(filter);
    }
}
