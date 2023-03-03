package com.sistemas_activos.entrega_final.repository;

import com.sistemas_activos.entrega_final.model.Quote;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface IQuotesRepository extends IBaseRepository<Quote,Long>{

    List<Quote> findByOriginContaining(String filter);

    @Query(value = "SELECT * FROM quotes WHERE comments LIKE %:filter% ",nativeQuery = true)
    List<Quote> findWordInComments(@PathVariable("filter") String filter);

}
