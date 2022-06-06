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
    /**
     * Add payment to balance in cashregister
     * @param paid holds the payment to be added.
     */
    public void addPayment(Payment paid){
        addBalance(paid.getTotal());
    }
    /**
     * getter to get the balance
     * @return  a double with the balance 
     */
    public double getBalance() {
        return balance;
    }
    /**
     * Method adding a choosen amount to balance 
     * @param amount is the amount to be added
     */
    private void addBalance(double amount) {
        this.balance += amount;
    }
    /**
     * Getter for the date.
     * @return LocalDate date
     */
    public LocalDate getDate (){
        return date;
    }
    /**
     * Getter gfor registernumber
     * @return an int 
     */
    public int getRegisterNumber(){
        return registernumber;
    }
}
