package com.journaldev.model;

/**
 * Created by vsshm_000 on 15.11.2016.
 */

import com.journaldev.model.Document;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Greg Turnquist
 */
// tag::code[]
public interface DocumentRepository extends PagingAndSortingRepository<Document, Long> {

}
// end::code[]

