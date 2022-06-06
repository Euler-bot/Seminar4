package se.newpos.view;

import se.newpos.controller.Controller;
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
        CurrentItemDTO currentItemToDisplay;
        controller.startNewSale();
        System.out.println("New sale have been initated. \n");
        SaleInfoDTO runningTotal = controller.endSales();
        ItemDTO enteredItem = new ItemDTO(1267);
        currentItemToDisplay = controller.enterItem(enteredItem);
        System.out.println(currentItemToDisplay.getCurrentItemName() + " has been added to sale");
        enteredItem = new ItemDTO(3213);
        currentItemToDisplay = controller.enterItem(enteredItem);
        System.out.println(currentItemToDisplay.getCurrentItemName() + " has been added to sale");
        enteredItem = new ItemDTO(5643);
        currentItemToDisplay = controller.enterItem(enteredItem);
        System.out.println(currentItemToDisplay.getCurrentItemName() + " has been added to sale");
        currentItemToDisplay = controller.addCurrentItemByAmount(currentItemToDisplay, 4);
        System.out.println("Added 4 more of " + currentItemToDisplay.getCurrentItemName()   +" to sale\n");
        if(currentItemToDisplay == null){
            System.out.println("Item not found try again");
        }
        runningTotal = controller.endSales();
        System.out.println("RunningTotal:");
        runningTotal.printRunningTotal();
        controller.addDiscount("198610070198");
        System.out.println("Runningtotal after discount was added:");
        runningTotal = controller.endSales();
        runningTotal.printRunningTotal();
        controller.enterPayment(100);
        controller.enterPayment(200);
        System.out.println("A new sale have been completed. \n");
        controller.startNewSale();
        System.out.println("New sale have been initated. \n");
        enteredItem = new ItemDTO(3213);
        currentItemToDisplay = controller.enterItem(enteredItem);
        System.out.println(currentItemToDisplay.getCurrentItemName() + " has been added to sale");
        enteredItem = new ItemDTO(1344);
        currentItemToDisplay = controller.enterItem(enteredItem);
        System.out.println(currentItemToDisplay.getCurrentItemName() + " has been added to sale");
        currentItemToDisplay = controller.addCurrentItemByAmount(currentItemToDisplay, 4);
        System.out.println("Added 4 more of " + currentItemToDisplay.getCurrentItemName()   +" to sale\n");
        if(currentItemToDisplay == null){
            System.out.println("Item not found try again");
        }
        System.out.println("RunningTotal:");
        controller.endSale();
        controller.addDiscount("198610070199");
        System.out.println("RunningTotal:");
        controller.endSale();
        controller.enterPayment(500);
        System.out.println("A second sale have been completed.");

    }

}
