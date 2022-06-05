package com.work.year22.reactivedemo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;

public class CassandraConfiguration extends AbstractCassandraConfiguration {

    @Value("${spring.data.cassandra.keyspace-name}")
    private String keySpace;

    @Value("${spring.data.cassandra.contact-points}")
    private String contactPoints;

    @Value("${spring.data.cassandra.port}")
    private int port;

    @Override
    public String getKeyspaceName() {
        return keySpace;
    }

//    @Override---NOT REQUIRED ANYMORE AS DEFAULT IMPLEMENTATION ALREADY PROVIDED in AbstractCassandraConfiguration
//    public String getContactPoints() {
//        return contactPoints;
//    }
//
//    @Override
//    public int getPort() {
//        return port;
//    }
}
