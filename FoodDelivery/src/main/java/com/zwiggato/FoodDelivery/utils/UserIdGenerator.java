package com.zwiggato.FoodDelivery.utils;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserIdGenerator implements IdentifierGenerator {

    private static final String PREFIX = "US";
    private static int counter = 0; // demo only (better use DB sequence)

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) {
        if(counter == 0)
            getCounter(session);
        counter++;
        return PREFIX + String.format("%07d", counter); // e.g. US001, US002
    }

    private void getCounter(SharedSessionContractImplementor session){
        System.out.println("\n\n\n-------------- getting max user Id --------------\n\n\n");
        try (Connection connection = session.getJdbcConnectionAccess().obtainConnection()){
            Statement statement = connection.createStatement();
            // Fetch max current numeric part from IDs
            ResultSet rs = statement.executeQuery("SELECT MAX(CAST(SUBSTRING(id, 3) AS INT)) FROM users");
            if (rs.next())
                counter = rs.getInt(1);
            System.out.println("Max ID obtained as "+ counter);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
