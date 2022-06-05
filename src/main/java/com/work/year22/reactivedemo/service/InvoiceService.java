package com.work.year22.reactivedemo.service;

import com.work.year22.reactivedemo.dto.Invoice;
import jnr.ffi.annotations.In;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;


public interface InvoiceService {

    public void saveInvoice(String name, String number, double amount);

    public Mono<Invoice> getInvoiceById(int id);

    public Flux<Invoice> getAllInvoices();

    public List<Integer> getTraditionalOutput() throws InterruptedException;

    public Flux<Integer> getReactiveOutput() throws InterruptedException;

    public List<Invoice> getTraditionalOutputByIdAndName(int id,String name);

}
