package se.newpos.integration;

public class ServerDownException extends Exception {
    /**
     * this method creates a new instance
     * @param message The message to be shown.
     */
	public ServerDownException(String message){
        super(message);
    }
}