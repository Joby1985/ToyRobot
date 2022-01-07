package com.demo.iress.robot.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.demo.iress.robot.exceptions.RobotException;
import com.demo.iress.robot.model.Robot;

import lombok.AllArgsConstructor;

/**
 * Command to report the robot's location on the table.
 *
 * @author Joby
 *
 */
@AllArgsConstructor
public class ReportCommand implements Command {
    static final Logger LOGGER = LoggerFactory.getLogger(ReportCommand.class);

    private Robot robot;

    @Override
    public void execute() throws RobotException {
        StringBuilder strB = new StringBuilder();
        strB.append("Output: ")
                .append((robot.getPosition() == null ? "Robot could not be positioned yet." : robot.getPosition()));
        String output = strB.toString();
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(output);
        }
        System.out.println(output);
    }
}
