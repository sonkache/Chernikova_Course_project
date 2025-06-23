package model;

import java.time.LocalDate;

public class Payment {
    private int creditContractId;
    private double amount;
    private double penalty;
    private LocalDate paymentDate;

    public Payment(int creditContractId, double amount, double penalty, LocalDate paymentDate) {
        this.creditContractId = creditContractId;
        this.amount = amount;
        this.penalty = penalty;
        this.paymentDate = paymentDate;
    }

    public int getCreditContractId(){
        return creditContractId;
    }
    public void setCreditContractId(int creditContractId){
        this.creditContractId = creditContractId;
    }

    public double getAmount(){
        return amount;
    }
    public void setAmount(double amount){
        this.amount = amount;
    }

    public double getPenalty(){
        return penalty;
    }
    public void setPenalty(double penalty){
        this.penalty = penalty;
    }

    public LocalDate getPaymentDate(){
        return paymentDate;
    }
    public void setPaymentDate(LocalDate paymentDate){
        this.paymentDate = paymentDate;
    }
}
