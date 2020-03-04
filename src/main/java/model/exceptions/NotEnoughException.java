package model.exceptions;

public class NotEnoughException extends Exception {

    public NotEnoughException(){
        super("Not Enough of a particular object");
    }

    public NotEnoughException(String message){
        super(message);
    }
}
