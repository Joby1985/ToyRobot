package com.demo.iress.robot.command;

import com.demo.iress.robot.exceptions.RobotException;

/**
 * Abstract command that can represent a command to the robot
 *
 * @author Joby
 *
 */
public interface Command {
    public void execute() throws RobotException;
}
