package com.zwiggato.FoodDelivery.utils;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;

public class AddressIdGenerator implements IdentifierGenerator {

    private static final String PREFIX = "AD";
    private static int counter = 1; // demo only (better use DB sequence)

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) {
        counter++;
        return PREFIX + String.format("%07d", counter);
    }
}
