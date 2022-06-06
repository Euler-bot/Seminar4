package se.newpos.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.newpos.model.CashRegister;
import se.newpos.model.Item;
import se.newpos.model.ItemDTO;
import se.newpos.model.Reciept;
import se.newpos.model.Sale;
import se.newpos.model.Transaction;
import se.newpos.model.TransactionDTO;



public class PrinterTest {

	private Printer printers;

	@BeforeEach
	public void setup() {
		this.printers = new Printer();
	}
	@AfterEach
	public void tearDown(){
		this.printers = null;
	}

	@Test
	public void testPrintReciept() {
		Reciept reciept;
		Item item = new Item(123, "Mjölk", "Mjölk 1 liter", 20, 0.12, 1);
		ItemDTO enteredItemDTO = new ItemDTO(item);
		Sale sale = new Sale();
		CashRegister cashRegister = new CashRegister();
		sale.addEnteredItem(enteredItemDTO);
		sale.getRunningTotal().setTotalVat(5);
		sale.enteredPayment(200);
		cashRegister.addPayment(sale.gPayment());
		TransactionDTO transactionDTO = new TransactionDTO(new Transaction(sale, sale.gPayment(), cashRegister));
		reciept = new Reciept(transactionDTO);
		String actual = reciept.createRecieptString();
		String time = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
		String expected = "------------------RECEIPT-----------------\n" + 
				"Date: " + LocalDate.now() +"\nPOS: 1" + "\tOrdernr: " + transactionDTO.getOrderNumber()
				+ "\nItems: " + "\nMjölk" + "\tprice: 20,00" + "\tAmount: 1\n"
				+ "\nTotal amount of items: 1" + "\nTotalprice: 22,40"
				+"\nTotal vat: 5,00" + "\nTotal discount: 0,00"
				+"\nPaid in cash: 200,00" + "\nChange recieved: 177,60\n" 
				+"\nSale starttime: " + time + "\nSale completed: " + time
				+ "\n--------------------End-------------------\n";
		//printers.printReciept(reciept);
		assertEquals(expected, actual, "Reciept string is not equal expected");	
	}
}
