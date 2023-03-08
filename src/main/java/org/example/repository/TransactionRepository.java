package org.example.repository;
import org.example.db.DataBase;
import org.example.dto.Transaction;
import org.example.enums.TransactionType;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
public class TransactionRepository {

    public static List<Transaction> getTranzactionByProfilePhone(Integer cardid) {
        try {
            Connection connection = DataBase.getConnection();
            String sql = "select * from transaction where card_id = '" + cardid + "';";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            List<Transaction> transactionList = new LinkedList<>();
            if (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                Integer cardId = resultSet.getInt("card_id");
                Double balance = resultSet.getDouble("amount");
                Integer terminal_Id = resultSet.getInt("terminal_id");
                String type = resultSet.getString("type");
                LocalDateTime createdDate = resultSet.getTimestamp("created_date").toLocalDateTime();

                Transaction transaction = new Transaction();
                transaction.setId(id);
                transaction.setCardId(cardId);
                transaction.setAmount(balance);
                transaction.setTerminalId(terminal_Id);
                transaction.setTransactionType(TransactionType.valueOf(type));
                transaction.setCreatedDate(createdDate);
                transactionList.add(transaction);
            }
            return transactionList;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }
    public static List<Transaction> getALlTransaction() {
        try {Connection connection = DataBase.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from transaction");
            List<Transaction> adminTransaction = new LinkedList<>();
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                Integer cardId = resultSet.getInt("card_id");
                Double balance = resultSet.getDouble("amount");
                Integer terminal_Id = resultSet.getInt("terminal_id");
                String type = resultSet.getString("type");
                LocalDateTime createdDate = resultSet.getTimestamp("created_date").toLocalDateTime();

                Transaction transaction = new Transaction();
                transaction.setId(id);
                transaction.setCardId(cardId);
                transaction.setAmount(balance);
                transaction.setTerminalId(terminal_Id);
                transaction.setTransactionType(TransactionType.valueOf(type));
                transaction.setCreatedDate(createdDate);
                adminTransaction.add(transaction);
            }
            return adminTransaction;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }

    public int createTransaction(Transaction transaction) {
        try (Connection connection = DataBase.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "insert into transaction(card_id,terminal_id,amount,type,created_date) " +
                            "values (?,?,?,?,?)");
            statement.setInt(1, transaction.getCardId());
            if (transaction.getTerminalId() != null) {
                statement.setInt(2, transaction.getTerminalId());
            } else {
                statement.setObject(2, null);
            }
            statement.setDouble(3, transaction.getAmount());
            statement.setString(4, transaction.getTransactionType().name());
            statement.setTimestamp(5, Timestamp.valueOf(transaction.getCreatedDate()));

            int resultSet = statement.executeUpdate();
            return resultSet;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public static List<Transaction> getTranByTerminalCode(String terNum) {
        try {Connection connection = DataBase.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from transaction where terminal_id='" + terNum + "';");
            List<Transaction> adminTransaction = new LinkedList<>();
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                Integer cardId = resultSet.getInt("card_id");
                Double balance = resultSet.getDouble("amount");
                Integer terminal_Id = resultSet.getInt("terminal_id");
                String type = resultSet.getString("type");
                LocalDateTime createdDate = resultSet.getTimestamp("created_date").toLocalDateTime();

                Transaction transaction = new Transaction();
                transaction.setId(id);
                transaction.setCardId(cardId);
                transaction.setAmount(balance);
                transaction.setTerminalId(terminal_Id);
                transaction.setTransactionType(TransactionType.valueOf(type));
                transaction.setCreatedDate(createdDate);
                adminTransaction.add(transaction);
            }
            return adminTransaction;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }
    public static List<Transaction> getTranByCardNumber(Integer cardNum) {
        try {Connection connection = DataBase.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from transaction where card_id='" + cardNum + "';");
            List<Transaction> adminTransaction = new LinkedList<>();
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                Integer cardId = resultSet.getInt("card_id");
                Double balance = resultSet.getDouble("amount");
                Integer terminal_Id = resultSet.getInt("terminal_id");
                String type = resultSet.getString("type");
                LocalDateTime createdDate = resultSet.getTimestamp("created_date").toLocalDateTime();

                Transaction transaction = new Transaction();
                transaction.setId(id);
                transaction.setCardId(cardId);
                transaction.setAmount(balance);
                transaction.setTerminalId(terminal_Id);
                transaction.setTransactionType(TransactionType.valueOf(type));
                transaction.setCreatedDate(createdDate);
                adminTransaction.add(transaction);
            }
            return adminTransaction;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }
}
