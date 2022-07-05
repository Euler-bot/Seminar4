package se.newpos.view;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import se.newpos.model.RunningTotal;
import se.newpos.model.SaleObserver;

class TotalRevenueFileOutput implements SaleObserver {
	private PrintWriter logger;
    private double totalRevenueSum;

    TotalRevenueFileOutput(){
        totalRevenueSum = 0;
        try {
            logger = new PrintWriter(new FileWriter("total_revenue.txt"), true);
        } catch (IOException e) {
            System.out.println("Unable to create a logger!");
            e.printStackTrace();
        }

    }

    @Override
    public void updateTotal(RunningTotal runningTotal){
        totalRevenueSum += runningTotal.getTotalPrice();
        logger.println("Total revenue: " + totalRevenueSum);
    }

}