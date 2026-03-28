package Exceptions;

public class NotEnoughMoneyException extends ATMException {
    public NotEnoughMoneyException(String message) {
        super(message);
    }
}
