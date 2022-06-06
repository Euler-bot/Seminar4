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
    public AccountSystem getAccountSystem() {
        return accountSystem;
    }

    public DiscountRegistry getDiscountRegistry() {
        return discountRegistry;
    }

    public TransactionRegistry getTransactionRegistry() {
        return transactionRegistry;
    }

    public InventorySystem getInventorySystem() {
        return inventorySystem;
    }
    public void updateAllSystems(TransactionDTO transactionDTO){
        accountSystem.emailTransactions(transactionDTO);
        inventorySystem.updateStorage(transactionDTO);
        transactionRegistry.saveTransaction(transactionDTO);
        discountRegistry.updateCustomerDiscount(transactionDTO);
    }

}
