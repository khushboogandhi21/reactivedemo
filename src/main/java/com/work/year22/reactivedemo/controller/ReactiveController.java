package com.work.year22.reactivedemo.controller;

import com.work.year22.reactivedemo.dto.Employee;
import com.work.year22.reactivedemo.dto.Invoice;
import com.work.year22.reactivedemo.service.InvoiceService;
import com.work.year22.reactivedemo.service.impl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping(value = "/api/")
public class ReactiveController {
    @Autowired
    private InvoiceService invoiceService;

    @GetMapping(value = "/getInvoiceById/{id}",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Mono<Invoice> getInvoiceById(@PathVariable int id){

        Mono<Invoice> monoInv = invoiceService.getInvoiceById(id);
       // return new ResponseEntity<Mono<Optional<Invoice>>>(monoInv, HttpStatus.OK);
        return monoInv;
    }

    @GetMapping(value = "/getAllInvoices",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Invoice> getAllInvoices(){

        Flux<Invoice> fluxInv = invoiceService.getAllInvoices();
        // return new ResponseEntity<Mono<Optional<Invoice>>>(monoInv, HttpStatus.OK);
        return fluxInv;
    }

    @GetMapping(path = "/hello", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getHello(){
        return new ResponseEntity<String>("Hello World", HttpStatus.OK);
    }


    @PostMapping(path="/createInvoice")
    public ResponseEntity<String> createInvoice(@RequestHeader String name, @RequestHeader String invoiceNumber, @RequestHeader double amount){
       //try {
           invoiceService.saveInvoice(name, invoiceNumber, amount);
           //int x=  4/0;
           return new ResponseEntity<>("Invoice Created",HttpStatus.CREATED);
      // }catch(Exception ex){
           // throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Exception in getReactiveOutput:",ex);
          /* OUTPUT IN POSTMAN
          {
               "timestamp": "2022-05-29T09:03:57.770+00:00",
                   "path": "/createInvoice",
                   "status": 500,
                   "error": "Internal Server Error",
                   "requestId": "dd06bb43-3"
           }*/

           //ResponseEntity is better than ResponseStatusException as it returns a proper http body

          // return new ResponseEntity<>("Error in creating invoice",HttpStatus.INTERNAL_SERVER_ERROR);


      // }
    }

    @GetMapping(path = "/getTraditionalOutput",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Integer> getTraditionalOutput(){
        List<Integer> listOut =null;
        try {
            listOut = invoiceService.getTraditionalOutput();
        }catch (Exception ex){
            System.out.println("Exception in getTraditionalOutput:" + ex.getMessage());
        }
        return listOut;
    }

    @GetMapping(path = "/getTraditionalOutputByIdAndName/{id}/{name}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getTraditionalOutputByIdAndName(@PathVariable int id,@PathVariable String name){
        List<Invoice> invoiceList =null;
        try {
            invoiceList = invoiceService.getTraditionalOutputByIdAndName(id,name);
        }catch (Exception ex){
            System.out.println("Exception in getTraditionalOutputByIdAndName:" + ex.getMessage());
            return new ResponseEntity<>("Exception in getTraditionalOutputByIdAndName:" + ex.getMessage() ,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(invoiceList,HttpStatus.OK);
    }




    /**
     * Works only in browser not in postman as async
     * @return
     */
    @GetMapping(path = "/getReactiveOutput", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Integer> getReactiveOutput(){  //Flux<String>
        Flux<Integer> listOut = null;
        try {
            listOut = invoiceService.getReactiveOutput();
           // int x=  4/0;

        }catch (Exception ex){
            System.out.println("Exception in getReactiveOutput:" + ex.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Exception in getReactiveOutput:");
        }

        return listOut;
//        return Flux.fromStream(new Random()
//                        .ints(10)
//                        .mapToObj(value -> "this is data " + value))
//                .delayElements(Duration.ofSeconds(1));
    }
}
