package com.journaldev.model;

/**
 * Created by vsshm_000 on 15.11.2016.
 */

import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Greg Turnquist
 */
// tag::code[]
public interface AccountRepository extends PagingAndSortingRepository<Account, Long> {

}
// end::code[]
