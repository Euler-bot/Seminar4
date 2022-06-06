package se.newpos.model;

public class Payment {
    private double amount;
    private double change;

    public Payment(){
        this.amount = 0;
        this.change = 0;
    }
    public Payment(double amount){
        if(amount <= 0){
            this.amount = 0;
        }
        this.amount = amount;
        this.change = 0;
    }
    public void calculateChange(Sale currentSale){
        if(getTotal(currentSale) < this.amount){
            setChange(this.amount - getTotal(currentSale));
        }
    }
    private double getTotal(Sale currentSale){
        return currentSale.getRunningTotal().getTotalPrice();
    }
    public double getAmount() {
        return amount;
    }
    public void addAmount(double amount){
        if(amount > 0){
            this.amount += amount;
        }
    }
    public double getChange() {
        return change;
    }
    public void setChange(double change) {
        this.change = change;
    }
    public void setAmount(double amount){
        this.amount = amount;
    }
    public double getTotal(){
        return (amount - change);
    }
}
