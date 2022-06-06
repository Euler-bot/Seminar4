package se.newpos.startup;

import se.newpos.controller.Controller;
import se.newpos.integration.ExternalCreator;
import se.newpos.integration.Printer;
import se.newpos.view.View;

/**
 * 
 * This starts the entire POS application for seminar 3 in course IV1350 at KTH. 
 * Main method starts the application.
 * @author Robert Furuvald
 */
public class Main {
    /**
     * The main method used to start the entire application.
     * 
     * @param args The application does not take any parameters.
     * 
     */
    public static void main(String[] args){
        ExternalCreator creator = new ExternalCreator();
        Printer printer = new Printer();
        Controller controller = new Controller(creator, printer);
        View view = new View(controller);
        
        view.fakeSale();
        
    }

}
