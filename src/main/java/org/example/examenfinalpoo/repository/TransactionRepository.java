package org.example.examenfinalpoo.repository;

import org.example.examenfinalpoo.model.Transaction;
import org.example.examenfinalpoo.model.TransactionType;
import org.springframework.stereotype.Repository;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TransactionRepository {

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/bank_db";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "";

    public List<Transaction> findAll(String type) {
        var sql = "SELECT * FROM transaction";
        if (type != null) {
            sql += " WHERE transaction_type = ?";
        }
        List<Transaction> transactions = new ArrayList<>();
        try (var connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             var statement = connection.prepareStatement(sql)) {
            if (type != null) {
                statement.setString(1, type.toUpperCase());
            }
            try (var resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    transactions.add(convert(resultSet));
                }
            }
        } catch (SQLException e) {
            System.out.println("SQL Error : " + e.getMessage());
        }
        return transactions;
    }

    public List<Transaction> findByAccountId(String accountId) {
        var sql = "SELECT * FROM transaction WHERE account_id = ?";
        List<Transaction> transactions = new ArrayList<>();
        try (var connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             var statement = connection.prepareStatement(sql)) {
            statement.setString(1, accountId);
            try (var resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    transactions.add(convert(resultSet));
                }
            }
        } catch (SQLException e) {
            System.out.println("SQL Error : " + e.getMessage());
        }
        return transactions;
    }

    public void save(Transaction transaction) {
        var sql = "INSERT INTO transaction (id, created_at, transaction_type, amount, reason, account_id) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        try (var connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             var statement = connection.prepareStatement(sql)) {
            statement.setString(1, transaction.getId());
            statement.setTimestamp(2, Timestamp.from(transaction.getCreatedAt()));
            statement.setString(3, transaction.getTransactionType().name());
            statement.setBigDecimal(4, transaction.getAmount());
            statement.setString(5, transaction.getReason());
            statement.setString(6, transaction.getAccountId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("SQL Error : " + e.getMessage());
        }
    }

    private Transaction convert(ResultSet resultSet) throws SQLException {
        return new Transaction(
                resultSet.getString("id"),
                resultSet.getTimestamp("created_at").toInstant(),
                TransactionType.valueOf(resultSet.getString("transaction_type")),
                resultSet.getBigDecimal("amount"),
                resultSet.getString("reason"),
                resultSet.getString("account_id")
        );
    }
}