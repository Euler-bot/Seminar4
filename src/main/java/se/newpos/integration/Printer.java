package se.newpos.integration;

import se.newpos.model.Reciept;

/**
 * This is the printer that prints reciept.
 */
public class Printer {

    public Printer(){

    }
    public void printReciept(Reciept reciept){
        System.out.println(reciept.createRecieptString());
        
    }
}
