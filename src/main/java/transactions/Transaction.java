package transactions;

import categories.Category;

import java.util.Date;

public class Transaction {
    private int transactionId;

    private Date transactionDate;

    private String payee;

    private String paymentReference;

    private double amount;

    private int categoryId;

    public int getId() {
        return transactionId;
    }

    public void setId(int transactionId) {
        this.transactionId = transactionId;
    }

    public Date getTranactionDate() {
        return transactionDate;
    }

    public void setTranactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }
    public String getPayee() {
        return payee;
    }

    public void setPayee(String payee) {
        this.payee = payee;
    }
    public String getPaymentReference() {
        return paymentReference;
    }

    public void setPaymentReference(String paymentReference) {
        this.paymentReference = paymentReference;
    }
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getCategory() {
        return categoryId;
    }

    public void setCategory(int categoryId) {
        this.categoryId = categoryId;
    }
}
