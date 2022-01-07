package com.demo.iress.robot.command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.demo.iress.robot.exceptions.InvalidInputException;
import com.demo.iress.robot.exceptions.RobotException;
import com.demo.iress.robot.model.Direction;
import com.demo.iress.robot.model.Robot;
import com.demo.iress.robot.model.RobotPosition;
import com.demo.iress.robot.model.Table;

/**
 * Command factory that is responsible for creating appropriate command as per
 * input parsed.
 *
 * @author Joby
 *
 */
public class CommandFactory {
    private static Logger LOGGER = LoggerFactory.getLogger(CommandFactory.class);

    private static String PLACING = "PLACE";
    // Make the pattern generic so as to validate for numbers, and Directions
    private static Pattern placingParams = Pattern.compile("(PLACE) +(.*),(.*),([A-Za-z-]+)");
    public static final String LEFT = "LEFT";
    public static final String RIGHT = "RIGHT";
    public static final String MOVE = "MOVE";
    public static final String REPORT = "REPORT";

    /**
     * Get the command to be executed corresponding to the input command.
     *
     * @param table
     * @param input
     * @return
     * @throws RobotException
     */
    public static Command getCommand(Table table, String input) throws RobotException {
        input = input.toUpperCase();

        Command commandToReturn = null;

        if (input.startsWith(PLACING)) {
            Robot robot = Robot.INSTANCE;
            robot.setName("Robo");

            Matcher m = placingParams.matcher(input);
            if (m.matches()) {
                Direction direction = null;
                int xPos, yPos = 0;
                try {
                    String strDirection = m.group(4);
                    direction = Direction.valueOf(strDirection);
                } catch (IllegalArgumentException ex) {
                    throw new InvalidInputException("Invalid Input. Direction should be one of NORTH|EAST|SOUTH|WEST");
                }
                try {
                    xPos = Integer.parseInt(m.group(2));
                    yPos = Integer.parseInt(m.group(3));
                } catch (NumberFormatException e) {
                    throw new InvalidInputException("Invalid Input on the co-ordinates. Invalid number.");
                }
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("Placing at xPos: {}, yPos: {}", xPos, yPos);
                }
                commandToReturn = new DoPlaceCommand(Robot.INSTANCE, table, new RobotPosition(xPos, yPos, direction));
            }
        } else {
            // Ensure that any commands before PLACE is ignored, except for REPORT.
            switch (input) {
            case LEFT:
                if (Robot.INSTANCE.getPosition() != null) {
                    commandToReturn = new TurnCommand(Robot.INSTANCE, LEFT);
                }
                break;
            case RIGHT:
                if (Robot.INSTANCE.getPosition() != null) {
                    commandToReturn = new TurnCommand(Robot.INSTANCE, RIGHT);
                }
                break;
            case MOVE:
                if (Robot.INSTANCE.getPosition() != null) {
                    commandToReturn = new MoveCommand(Robot.INSTANCE, table);
                }
                break;
            case REPORT:
                commandToReturn = new ReportCommand(Robot.INSTANCE);
                break;
            default:
                throw new InvalidInputException(
                        "Invalid Command. Accepted commands are PLACE, MOVE, LEFT, RIGHT, REPORT.");
            }
        }
        return commandToReturn;
    }
}
