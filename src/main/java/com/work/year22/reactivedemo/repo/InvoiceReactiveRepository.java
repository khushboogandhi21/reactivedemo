package com.work.year22.reactivedemo.repo;

import com.work.year22.reactivedemo.dto.Invoice;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface InvoiceReactiveRepository extends ReactiveCrudRepository<Invoice, Integer> {

}
