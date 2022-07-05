package se.newpos.view;

import se.newpos.model.RunningTotal;
import se.newpos.model.SaleObserver;




class TotalRevenueView implements SaleObserver {
    private double totalSaleAmount;

    public TotalRevenueView(){
       this.totalSaleAmount = 0;
    }
    @Override
    public void updateTotal(RunningTotal runningTotal) {
        addTotalSaleAmount(runningTotal.getTotalPrice());
        printRevenue();
    }
    private void addTotalSaleAmount(double sum){
        this.totalSaleAmount += sum;
    }
    private void printRevenue(){
        System.out.println("---------DISPLAY---------");
        System.out.println("---------REVENUE---------");
        System.out.println("Amount: " + totalSaleAmount );
        System.out.println("-------------------------");
    }
}