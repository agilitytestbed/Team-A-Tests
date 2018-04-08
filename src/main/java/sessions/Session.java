package sessions;

import categories.Category;
import transactions.Transaction;

import java.util.LinkedHashMap;

public class Session {
    private final int sessionId;
    private LinkedHashMap<Integer, Transaction> transactions = new LinkedHashMap<>();
    private LinkedHashMap<Integer, Category> categories = new LinkedHashMap<>();

    public Session(int sessionId) {
        this.sessionId = sessionId;
    }

    public int getSessionId() {
        return sessionId;
    }

    public LinkedHashMap<Integer, Transaction> getTransactions() {
        return transactions;
    }

    public LinkedHashMap<Integer, Category> getCategories() {
        return categories;
    }
}
