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
public class DebtStudentException extends Exception {

    /**
     * Creates a new instance of <code>DebtStudentException</code> without
     * detail message.
     */
    public DebtStudentException() {
    }

    /**
     * Constructs an instance of <code>DebtStudentException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public DebtStudentException(String msg) {
        super(msg);
    }
}
