/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Matricula.logic.Exceptions;

/**
 *
 * @author atenea
 */
public class DateBeforeException extends Exception {

    /**
     * Creates a new instance of <code>DateBeforeException</code> without detail
     * message.
     */
    public DateBeforeException() {
    }

    /**
     * Constructs an instance of <code>DateBeforeException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public DateBeforeException(String msg) {
        super(msg);
    }
}
