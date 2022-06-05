package com.work.year22.reactivedemo.routes;

import com.work.year22.reactivedemo.dto.Invoice;
import com.work.year22.reactivedemo.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class HelloHandlerFunction {

    @Autowired
    InvoiceService invoiceService;
    //The starting point of this new framework is the HandlerFunction<T>, which is essentially a Function<Request, Response<T>>,
    // where Request and Response are newly-defined, immutable interfaces that offer a JDK-8 friendly DSL to the underlying HTTP messages.
    public Mono<ServerResponse> getReactiveHello(ServerRequest serverRequest){
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(Flux.just(1,2,3,4,5).log(), Integer.class);

        //<T, P extends Publisher<T>> Mono<ServerResponse> body(P publisher, Class<T> elementClass);
    }

    //body returns Mono so both functions return mono
    public Mono<ServerResponse> getMonoHello(ServerRequest serverRequest){
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(Mono.just(1).log(),Integer.class);

    }


    public Mono<ServerResponse> getReactiveAllInvoices(ServerRequest serverRequest){

        Flux<Invoice>  invoiceFlux = invoiceService.getAllInvoices();

        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(invoiceFlux,Invoice.class);

    }

    public Mono<ServerResponse> getReactiveInvoiceById(ServerRequest serverRequest){
        int id = Integer.valueOf(serverRequest.pathVariable("id"));

        Mono<Invoice>  invoiceMono = invoiceService.getInvoiceById(id);

        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(invoiceMono,Invoice.class);

    }

    public Mono<ServerResponse> createReactiveInvoice(ServerRequest serverRequest){
        System.out.println("Calling createReactiveInvoice");
        invoiceService.saveInvoice("Nonfunc","5464321",524.3);
        System.out.println("Called createReactiveInvoice");

        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(Mono.just("Invoice Created reactively."),String.class);

    }


}
