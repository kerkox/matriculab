package Matricula.logic.Exceptions;

public class DateBeforeException extends Exception {

    public DateBeforeException() {
    }

    public DateBeforeException(String msg) {
        super(msg);
    }
}
