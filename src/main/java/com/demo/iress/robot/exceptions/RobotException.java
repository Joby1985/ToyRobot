package com.demo.iress.robot.exceptions;

/**
 * Basic Robot Exception.
 *
 * @author Joby
 *
 */
public abstract class RobotException extends Exception {

    public RobotException(String message) {
        super(message);
    }
}
