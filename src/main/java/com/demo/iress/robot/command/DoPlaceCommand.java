package com.demo.iress.robot.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.demo.iress.robot.exceptions.RobotException;
import com.demo.iress.robot.model.Robot;
import com.demo.iress.robot.model.RobotPosition;
import com.demo.iress.robot.model.Table;

import lombok.AllArgsConstructor;

/**
 * Command to place the robot on a specific coordinate on the table. (Also
 * validate that the place is within the table
 *
 * @author Joby
 *
 */
@AllArgsConstructor
public class DoPlaceCommand implements Command {
    private static Logger LOGGER = LoggerFactory.getLogger(DoPlaceCommand.class);

    private Robot robot;
    private Table table;
    private RobotPosition position;

    @Override
    public void execute() throws RobotException {
        if (table.isBoundedMove(position)) {
            robot.setPosition(position);
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Robot placed at position :" + position);
            }
        } else {
            LOGGER.warn("Not moving as the robot would fall off.");
        }
    }
}
