package se.newpos.integration;

import se.newpos.model.TransactionDTO;

/**
 * This is the applications creator for all external systems.
 */
public class ExternalCreator {
    private AccountSystem accountSystem;
    private DiscountRegistry discountRegistry;
    private TransactionRegistry transactionRegistry;
    private InventorySystem inventorySystem;
    /**
     * Creates all references to all external systems under startup of program.
     */
    public ExternalCreator(){
        accountSystem = new AccountSystem();
        discountRegistry = new DiscountRegistry();
        transactionRegistry = new TransactionRegistry();
        inventorySystem = new InventorySystem();

    }
    /**
     * Getter for accountSystem
     * @return accountsystem
     */
    public AccountSystem getAccountSystem() {
        return accountSystem;
    }
    /**
     * getter for discountRegistry that hold all information of discounts
     * @return discountregistry
     */
    public DiscountRegistry getDiscountRegistry() {
        return discountRegistry;
    }
    /**
     * Getter for transactionRegistry 
     * @return transactionRegistry
     */
    public TransactionRegistry getTransactionRegistry() {
        return transactionRegistry;
    }
    /**
     * Getter for inventorySystem
     * @return the inventorysystem created
     */
    public InventorySystem getInventorySystem() {
        return inventorySystem;
    }
    /**
     * Updates all external systems
     * @param transactionDTO contains all information needed to update
     */
    public void updateAllSystems(TransactionDTO transactionDTO){
        accountSystem.emailTransactions(transactionDTO);
        inventorySystem.updateStorage(transactionDTO);
        transactionRegistry.saveTransaction(transactionDTO);
        discountRegistry.updateCustomerDiscount(transactionDTO);
    }

}
