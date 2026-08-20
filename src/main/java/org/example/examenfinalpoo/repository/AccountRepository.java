package org.example.examenfinalpoo.repository;

import org.example.examenfinalpoo.model.Account;
import org.example.examenfinalpoo.model.AccountType;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.DriverManager;
import java.sql.SQLException;

@Repository
public class AccountRepository {

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/bank_db";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "";

    public Account findById(String id) {
        var sql = "SELECT * FROM account WHERE id = ?";
        try (var connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             var statement = connection.prepareStatement(sql)) {
            statement.setString(1, id);
            try (var resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Account(
                            resultSet.getString("id"),
                            AccountType.valueOf(resultSet.getString("account_type"))
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("SQL Error : " + e.getMessage());
        }
        return null;
    }

    public BigDecimal calculateBalance(String accountId) {
        var sql = "SELECT COALESCE(SUM(CASE WHEN transaction_type = 'IN' THEN amount ELSE -amount END), 0) AS balance "
                + "FROM transaction WHERE account_id = ?";
        try (var connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             var statement = connection.prepareStatement(sql)) {
            statement.setString(1, accountId);
            try (var resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getBigDecimal("balance");
                }
            }
        } catch (SQLException e) {
            System.out.println("SQL Error : " + e.getMessage());
        }
        return BigDecimal.ZERO;
    }
}