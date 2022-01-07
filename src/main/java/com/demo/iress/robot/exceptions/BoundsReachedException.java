package com.demo.iress.robot.exceptions;

/**
 * Exception to denote that the move will not be performed as that would result
 * in the robot falling down.
 *
 * @author Joby
 *
 */
public class BoundsReachedException extends RobotException {

    public BoundsReachedException() {
        super("Bounds reached. Robot will not be moved.");
    }
}
