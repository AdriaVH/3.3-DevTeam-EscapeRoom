package exceptions;

public class UnpaidTicketException extends Exception {
    public UnpaidTicketException(String userName) {

        super("Player"+ userName+ "hasn't paid the ticket");
    }
}
