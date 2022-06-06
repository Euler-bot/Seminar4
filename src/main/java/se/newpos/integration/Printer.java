package se.newpos.integration;

import se.newpos.model.Reciept;

/**
 * This is the printer that prints reciept.
 */
public class Printer {

    /**
     * Prints out the reciept to screen.
     * @param reciept contains all information and creates a string to be printed.
     */
    public void printReciept(Reciept reciept){
        System.out.println(reciept.createRecieptString());
        
    }
}
