package model;

import java.time.LocalDate;
public class CreditContract {
    private int id;
    private int clientId;
    private int creditTypeId;
    private double amount;
    private LocalDate issueDate;
    private LocalDate returnDate;

    public CreditContract(int id, int clientId, int creditTypeId, double amount, LocalDate issueDate, LocalDate returnDate) {
        this.id = id;
        this.clientId = clientId;
        this.creditTypeId = creditTypeId;
        this.amount = amount;
        this.issueDate = issueDate;
        this.returnDate = returnDate;
    }

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }

    public int getClientId(){
        return clientId;
    }
    public void setClientId(int clientId){
        this.clientId = clientId;
    }

    public int getCreditTypeId(){
        return creditTypeId;
    }
    public void setCreditTypeId(int creditTypeId){
        this.creditTypeId = creditTypeId;
    }

    public double getAmount(){
        return amount;
    }
    public void setAmount(double amount){
        this.amount = amount;
    }

    public LocalDate getIssueDate(){
        return issueDate;
    }
    public void setIssueDate(LocalDate issueDate){
        this.issueDate = issueDate;
    }

    public LocalDate getReturnDate(){
        return returnDate;
    }
    public void setReturnDate(LocalDate returnDate){
        this.returnDate = returnDate;
    }
}
