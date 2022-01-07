package com.demo.iress.robot.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.demo.iress.robot.exceptions.RobotException;
import com.demo.iress.robot.model.Robot;
import com.demo.iress.robot.model.RobotPosition;
import com.demo.iress.robot.model.Table;

import lombok.AllArgsConstructor;

/**
 * Command to move the robot by a unit on the table. (Also validate that the
 * place is within the table.) This takes the direction set and appropriately
 * sets the next location.
 *
 * @author Joby
 *
 */
@AllArgsConstructor
public class MoveCommand implements Command {
    private static Logger LOGGER = LoggerFactory.getLogger(DoPlaceCommand.class);

    private Robot robot;
    private Table table;

    @Override
    public void execute() throws RobotException {
        RobotPosition pos = robot.getPosition();
        RobotPosition nextPos = pos.clone();
        // This is how we move. For a NORTH direction move, we will have Y-direction =
        // +1, and X-direction = 0
        nextPos.setXPos(pos.getXPos() + pos.getDirection().getXDirection());
        nextPos.setYPos(pos.getYPos() + pos.getDirection().getYDirection());
        if (table.isBoundedMove(nextPos)) {
            robot.setPosition(nextPos);
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Robot moved to position :" + nextPos);
            }
        } else {
            LOGGER.warn("Not moving as the robot would fall off.");
        }
    }

}
