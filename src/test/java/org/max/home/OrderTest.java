package org.max.home;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.max.seminar.CurrentEntity;

import javax.persistence.PersistenceException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrderTest extends AbstractTest {
    @Test
    void getOrders_whenValid_shouldReturn() throws SQLException {
        //given
        String sql = "SELECT * FROM orders";
        Statement stmt = getConnection().createStatement();
        int countTableSize = 0;
        //when
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            countTableSize++;
        }
        final Query query = getSession().createSQLQuery(sql).addEntity(OrdersEntity.class);
        //then
        Assertions.assertEquals(15, countTableSize);
        Assertions.assertEquals(15, query.list().size());
    }

    @Test
    void getOrderbyId_whenValid_shouldReturn() throws SQLException {
        //given
        String sql = "SELECT * FROM orders WHERE order_id='5'";
        Statement stmt  = getConnection().createStatement();
        int countTableSize = 0;
        //when
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            countTableSize++;
        }
        final Query query = getSession().createSQLQuery("SELECT * FROM orders").addEntity(OrdersEntity.class);
        //then
        Assertions.assertEquals(1, countTableSize);
        Assertions.assertEquals(15, query.list().size());
    }
}