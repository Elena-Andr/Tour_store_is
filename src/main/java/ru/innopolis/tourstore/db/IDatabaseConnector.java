package ru.innopolis.tourstore.db;

import java.sql.PreparedStatement;
import java.sql.Statement;

public interface IDatabaseConnector {

    Statement getStatement();
    PreparedStatement getPrepareStatement(String sql);
    void closePreparedStatement(PreparedStatement ps);

}
