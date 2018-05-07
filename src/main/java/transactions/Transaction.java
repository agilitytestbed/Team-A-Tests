package transactions;

import categories.Category;
import java.util.Date;

public class Transaction {
    private int transactionId;

    private String transactionDate;

    private String externalIBAN;

    private double amount;

    private TransactionType type;

    private Category category;



    public int getId() {

        return transactionId;
    }

    public void setId(int transactionId) {

        this.transactionId = transactionId;
    }

    public String getTranactionDate() {

        return transactionDate;
    }

    public void setTranactionDate(String transactionDate) {

        this.transactionDate = transactionDate;
    }


    private String getExternalIBAN(){
        return externalIBAN;
    }

    public void setExternalIBAN(String externalIBAN) {

        this.externalIBAN = externalIBAN;
    }

    private TransactionType getType(){
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public double getAmount() {

        return amount;
    }



    public void setAmount(double amount) {

        this.amount = amount;
    }

    public Category getCategory() {

        return category;
    }

    public void setCategory(Category category) {

        this.category = category;
    }
}
