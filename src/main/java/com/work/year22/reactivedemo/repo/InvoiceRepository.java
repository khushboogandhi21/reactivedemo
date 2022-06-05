package com.work.year22.reactivedemo.repo;

import com.work.year22.reactivedemo.dto.Invoice;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface InvoiceRepository extends CassandraRepository<Invoice, Integer> {  //can use JPARepository //PagingAndSortingRepository

    @AllowFiltering
    List<Invoice> findByName(String name);

//    @Query(value = "select * from invoicedata.invoice where id IN  ?  allow filtering",allowFiltering = true)
//    //@AllowFiltering THIS DON'T WORK ADD IN QUERY
//    List<Invoice> findByIdAndName(int id,String name);
    //NOT WORKING
}
