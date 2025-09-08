package com.zwiggato.FoodDelivery.utils;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AddressIdGenerator implements IdentifierGenerator {

    private static final String PREFIX = "AD";
    private static int counter = 0; // demo only (better use DB sequence)

//    @Override
//    public Serializable generate(SharedSessionContractImplementor session, Object object) {
//        counter++;
//        return PREFIX + String.format("%07d", counter);
//    }

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) {
        if(counter == 0)
            getCounter(session);
        counter++;
        return PREFIX + String.format("%07d", counter);
    }

    private void getCounter(SharedSessionContractImplementor session){
       try (Connection connection = session.getJdbcConnectionAccess().obtainConnection()) {
           System.out.println("\n\n\n-------------- getting max address Id --------------\n\n\n");
            Statement statement = connection.createStatement();
            // Fetch max current numeric part from IDs
            ResultSet rs = statement.executeQuery("SELECT MAX(CAST(SUBSTRING(id, 3) AS INT)) FROM address");
            if (rs.next())
                counter = rs.getInt(1);
           System.out.println("Max id obtained :"+ counter);
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }
    }
}
