package com.demo.iress.robot.exceptions;

/**
 * Exception to denote that there is an issue with input.
 *
 * @author Joby
 *
 */
public class InvalidInputException extends RobotException {

    public InvalidInputException(String message) {
        super(message == null ? "Invalid input received." : message);
    }
}
