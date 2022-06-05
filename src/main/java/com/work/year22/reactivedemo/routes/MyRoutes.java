package com.work.year22.reactivedemo.routes;


import com.work.year22.reactivedemo.dto.Invoice;
import com.work.year22.reactivedemo.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class MyRoutes {

    @Autowired
    InvoiceService invoiceService;

    //The first argument is a request predicate. Notice how we used a statically imported RequestPredicates.
    // GET method here. The second parameter defines a handler function that'll be used if the predicate applies.
    /*@Bean
    RouterFunction<ServerResponse> getFuncHello(){  //getting whitelabel error


        return route(POST("/funchello"),
                req -> req.body(bodyToMono(Invoice.class))
                        .doOnNext(x->invoiceService.saveInvoice("Nonfunc","5464321",524.3))
                        .then(ServerResponse.ok().build()));

//        RouterFunctions.route().POST("/person",
//                request -> {
//                    Mono<Invoice> person = request.body(toMono(Invoice.class));
//                    return Response.ok().build(invoiceService.saveInvoice("Nonfunc","5464321",524.3));
//                });


//        return route(POST("/employees/update"),
//                req -> req.body(toMono(Employee.class))
//                        .doOnNext(employeeRepository()::updateEmployee)
//                        .then(ok().build()));
//        //return route().GET("/product", req -> ok().body(ps.findAll()))
//                .build();
//        return route(RequestPredicates.GET("/employees/{id}"),
//                req ->  ServerResponse.ok().body(
//                        employeeRepository().findEmployeeById(req.pathVariable("id")), Employee.class));
    }*/


    //Route takes handlerfunction and returns RouterFunction
    @Bean
    public RouterFunction<ServerResponse> getReactiveHello(HelloHandlerFunction helloHandlerFunction){
        return RouterFunctions.route(GET("/getReactiveFluxHello").and(accept(MediaType.APPLICATION_JSON)), helloHandlerFunction::getReactiveHello)
                .andRoute(GET("/getReactiveMonoHello").and(accept(MediaType.APPLICATION_JSON)),helloHandlerFunction :: getMonoHello)
                .andRoute(GET("/getReactiveAllInvoices"),helloHandlerFunction :: getReactiveAllInvoices)
                .andRoute(POST("/createReactiveInvoice"),helloHandlerFunction :: createReactiveInvoice)
                .andRoute(GET("/getReactiveInvoiceById/{id}"),helloHandlerFunction :: getReactiveInvoiceById);

        //public static <T extends ServerResponse> RouterFunction<T> route(RequestPredicate predicate, HandlerFunction<T> handlerFunction) {
    }


}
