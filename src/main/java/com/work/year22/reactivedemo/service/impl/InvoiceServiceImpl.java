package com.work.year22.reactivedemo.service.impl;

import com.work.year22.reactivedemo.dto.Invoice;
import com.work.year22.reactivedemo.repo.InvoiceReactiveRepository;
import com.work.year22.reactivedemo.repo.InvoiceRepository;
import com.work.year22.reactivedemo.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class InvoiceServiceImpl implements InvoiceService {
    @Autowired
    InvoiceRepository invoiceRepository;

    @Autowired
    InvoiceReactiveRepository invoiceReactiveRepository;

    @Override
    public void saveInvoice(String name, String invoiceNumber, double amount) {

        int id = (int)(Math.random() * (100-1))+ 1;
        Invoice inv = new Invoice(id , name,invoiceNumber,amount);
        invoiceRepository.save(inv);
    }

    @Override
    public Mono<Invoice> getInvoiceById(int id) {
        return invoiceReactiveRepository.findById(id);
    }

    @Override
    public Flux<Invoice> getAllInvoices() {
        return invoiceReactiveRepository.findAll();
    }

    /*You can use collectList() operator in Flux for this which gives a Mono of List.
    FlatMapMany - This is a Mono operator which is used to transform a Mono object into a Flux object.
I GUESS IF USING CRUD REPOSITORY and NOT ReactiveCrudRepository
            userRepository.findAll().collectList().flatMapMany(Flux::just);*/

    private static void sleepExecution(int i) {
        try{
            Thread.sleep(1000);
        }catch (InterruptedException iex){

        }
    }

    @Override
    public List<Integer> getTraditionalOutput() throws InterruptedException{

        List<Integer> listOut = IntStream.range(1,10).peek(InvoiceServiceImpl::sleepExecution).peek(System.out::println).boxed().collect(Collectors.toList());
        //In java 16 ->IntStream.range(1,10).boxed().toList();

        return listOut;
    }

    @Override
    public List<Invoice> getTraditionalOutputByIdAndName(int id,String name) {

        List<Invoice> invList = null; //NOT WORKING invoiceRepository.findByIdAndName(id,name);

        return invList;
    }




    @Override
    public Flux<Integer> getReactiveOutput() throws InterruptedException{

        Flux<Integer> listOut = Flux.range(1,10).delayElements(Duration.ofSeconds(1)).doOnNext(System.out::println).map(i-> {return i;});

        return listOut;
    }



}
