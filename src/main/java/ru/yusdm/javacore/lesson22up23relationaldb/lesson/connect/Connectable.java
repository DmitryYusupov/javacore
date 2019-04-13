package ru.yusdm.javacore.lesson22up23relationaldb.lesson.connect;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Admin on 4/10/2019.
 */
public interface Connectable {

    Connection getConnection() throws SQLException;
}
