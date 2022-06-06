package se.newpos.model;

import java.time.LocalDate;

/**
 * This is the Register where the cashier operates on and stores cash.
 */
public class CashRegister {
    private int registernumber = 1;
    private LocalDate date;
    private double balance;
    private String manager;
    /**
     * Creates a new instance and saves the date, number and starting cashbalance and manager of the shift.
     * 
     */
    public CashRegister(){
        this.date = LocalDate.now();
        this.balance = 1000;
        this.manager = "Robert";
    }
    public void addPayment(Payment paid){
        addBalance(paid.getTotal());
    }
    public double getBalance() {
        return balance;
    }
    private void addBalance(double balance) {
        this.balance += balance;
    }
    public LocalDate getDate (){
        return date;
    }
    public int getRegisterNumber(){
        return registernumber;
    }
}
