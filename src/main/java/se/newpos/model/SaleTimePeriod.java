package se.newpos.model;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
/**
 * One single timeperiod of a sale.
 */
public class SaleTimePeriod {
    private String startSaleTime;
    private String endSaleTime;
    private static DateTimeFormatter dFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    
    /**
     * Creates a new instsance and saves the time.
     * Add set endSaleTime when sale is completed.
     */
    public SaleTimePeriod(){
        this.startSaleTime = LocalTime.now().format(dFormatter);
    }
    public void setEndTimeOfSale(){
        this.endSaleTime = LocalTime.now().format(dFormatter);
    }
    public String getStartSaleTime() {
        return startSaleTime;
    }
    public void setStartSaleTime(String startSaleTime) {
        this.startSaleTime = startSaleTime;
    }
    public String getEndSaleTime() {
        return endSaleTime;
    }
    public void setEndSaleTime(String endSaleTime) {
        this.endSaleTime = endSaleTime;
    }
     
}
