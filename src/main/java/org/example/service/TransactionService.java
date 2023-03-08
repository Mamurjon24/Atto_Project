package org.example.service;
import org.example.container.ComponentContainer;
import org.example.dto.Transaction;
import org.example.enums.TransactionType;
import org.example.repository.TransactionRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
public class TransactionService {
    public static List<Transaction> getAllTranzaction() {
        List<Transaction> transactionListForAdmin = TransactionRepository.getALlTransaction();
        return transactionListForAdmin;
    }
    public static void getAllProfileTransaction(Integer id) {
        List<Transaction> transactionList = TransactionRepository.getTranzactionByProfilePhone(id);
        transactionList.forEach(System.out::println);
    }
    public static List<Transaction> getTodayTranzaction() {
        List<Transaction> transactionListForToday = new LinkedList<>();
        for (Transaction transaction : TransactionRepository.getALlTransaction()) {
            if (transaction.getCreatedDate().toLocalDate().equals(LocalDateTime.now().toLocalDate())) {
                transactionListForToday.add(transaction);
            }
        }
        return transactionListForToday;
    }
    public static List<Transaction> getTranzactionByDate(String data) {
        List<Transaction> transactionListByDate = new LinkedList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
        LocalDate localDate = LocalDate.parse(data, formatter);
        for (Transaction transaction : TransactionRepository.getALlTransaction()) {
            if (localDate.equals(transaction.getCreatedDate().toLocalDate())) {
                transactionListByDate.add(transaction);
            }
        }
        return transactionListByDate;
    }
    public static List<Transaction> getTranzactionBetweenDate(String fromDate, String toDate) {
        List<Transaction> transactionListBetweenData = new LinkedList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
        LocalDate fromDate1 = LocalDate.parse(fromDate, formatter);
        LocalDate toDate1 = LocalDate.parse(toDate, formatter);
        for (Transaction transaction : TransactionRepository.getALlTransaction()) {
            if (fromDate1.isBefore(transaction.getCreatedDate().toLocalDate()) && toDate1.isAfter(transaction.getCreatedDate().toLocalDate())){
                transactionListBetweenData.add(transaction);
            }
        }
        return transactionListBetweenData;
    }
    public static List<Transaction> getTranzactionTransactionListByTerminal(Integer termianlcode) {
        List<Transaction> transactionListByTerminal = TransactionRepository.getTranByTerminalCode(String.valueOf(termianlcode));
        return transactionListByTerminal;
    }
    public static List<Transaction> getTranzactionTransactionListCardNum(Integer cardNum) {
        List<Transaction> transactionListCardNum = TransactionRepository.getTranByCardNumber(cardNum);
        return transactionListCardNum;
    }
    public void createTransaction(Integer cardId, Integer terminalId, Double amount, TransactionType type) {
        Transaction transaction = new Transaction();
        transaction.setCardId(cardId);
        transaction.setTerminalId(terminalId);
        transaction.setAmount(amount);
        transaction.setTransactionType(type);
        transaction.setCreatedDate(LocalDateTime.now());

        TransactionRepository transactionRepository = ComponentContainer.transactionRepository;
        transactionRepository.createTransaction(transaction);
    }
}
