package se.newpos.view;

import se.newpos.controller.Controller;
import se.newpos.integration.ItemNotFoundException;
import se.newpos.integration.ServerDownException;
import se.newpos.model.CurrentItemDTO;
import se.newpos.model.ItemDTO;
import se.newpos.model.SaleInfoDTO;

/**
 * This is the real view to the user. 
 */
public class View {
    private Controller controller;
    /**
     * THIS must run before any other methods
     * @param controller 
     */
    public View(Controller controller){
        this.controller = controller;
    }
    /**
     * Runs two fakeSale with hardcoded items and discounts.
     * 
     */
    public void fakeSale() {
        CurrentItemDTO currentItemToDisplay = null;
        controller.startNewSale();
        System.out.println("New sale have been initated. \n");
        ItemDTO enteredItem = new ItemDTO(1267);
        try {
            currentItemToDisplay = controller.enterItem(enteredItem);
            System.out.println(currentItemToDisplay.getCurrentItemName() + " has been added to sale");
        } catch (ItemNotFoundException e) {
            System.out.println("[View] Item not found!");
        }
        catch (ServerDownException e) {
            System.out.println("Server down, try again");
        }
        enteredItem = new ItemDTO(3213);
        try {
            currentItemToDisplay = controller.enterItem(enteredItem);
            System.out.println(currentItemToDisplay.getCurrentItemName() + " has been added to sale");
            
        } catch (ItemNotFoundException e) {
            System.out.println("[View] Item not found!");
        }
        catch (ServerDownException e) {
            System.out.println("Server down, try again");
        }
        enteredItem = new ItemDTO(3219);
        try {
            currentItemToDisplay = controller.enterItem(enteredItem);
            System.out.println(currentItemToDisplay.getCurrentItemName() + " has been added to sale");
        } catch (ItemNotFoundException e) {
            System.out.println("[View] Item not found!");
        }
        catch (ServerDownException e) {
            System.out.println("Server down, try again");
        }
        enteredItem = new ItemDTO(5643);
        try {
            currentItemToDisplay = controller.enterItem(enteredItem);
            System.out.println(currentItemToDisplay.getCurrentItemName() + " has been added to sale");
        } catch (ItemNotFoundException e) {
            System.out.println("[View] Item not found!");
        }
        catch (ServerDownException e) {
            System.out.println("Server down, try again");
        }
        //In this case I have hardcoded code for enabling to add currentItem by amount on screen.
        //Normal case I would not assume this and the display would just display that the item was not found
        //and therefor you can't add by amount, but if no exception then it would be possible to add shown by display
        if(currentItemToDisplay != null){
            currentItemToDisplay = controller.addCurrentItemByAmount(currentItemToDisplay, 4);
            System.out.println("Added 4 more of " + currentItemToDisplay.getCurrentItemName()   +" to sale\n");
        }
        try {
            controller.enterItem(new ItemDTO(0));
        } catch (ServerDownException e) {
            System.out.println(e.getMessage() + " Try again, if not connected within 2 min call support.");
        }
        catch (ItemNotFoundException e) {
        }
        SaleInfoDTO runningTotal = controller.endSales();
        System.out.println("RunningTotal:");
        runningTotal.printRunningTotal();
        controller.addDiscount("199610180192");
        System.out.println("Runningtotal after discount was added:");
        runningTotal = controller.endSales();
        runningTotal.printRunningTotal();
        controller.enterPayment(100);
        controller.enterPayment(200);
        System.out.println("A sale have been completed. \n");
        

    }

}
