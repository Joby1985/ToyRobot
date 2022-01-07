package com.demo.iress.robot;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.demo.iress.robot.command.Command;
import com.demo.iress.robot.command.CommandFactory;
import com.demo.iress.robot.exceptions.RobotException;
import com.demo.iress.robot.model.Coordinates;
import com.demo.iress.robot.model.Robot;
import com.demo.iress.robot.model.RobotPosition;
import com.demo.iress.robot.model.Table;

//import ch.qos.logback.classic.Logger;

/**
 * Class representing the Robot Game.
 *
 * @author Joby
 *
 */
public class RobotGame {
    private static Logger LOGGER = LoggerFactory.getLogger(RobotGame.class);

    private static final Pattern TBL_POSITIONS_CONFIG = Pattern.compile("\\((\\d+),(\\d+)\\)");
    private static Properties PROPERTIES;

    public static void main(String[] args) {
        String line = null;

        try (Scanner in = new Scanner(System.in)) {
            List<String> commands = new ArrayList<>();
            System.out.println(
                    "Please input the commands seperated by New line '\\n'. To denote end of input, provide a blank line and press 'Enter'.");
            // Input is end with a blank line
            while ((line = in.nextLine()) != null && !line.isEmpty()) {
                commands.add(line);
            }
            final Properties properties = getProperties();
            Table table = getTable(properties);
            play(table, commands);
        } catch (RobotException e) {
            LOGGER.error("An error occurred {}", e.getMessage());
        }
    }

    /**
     * Creates a table object and return based on the co-ordinate configs
     *
     * @param properties
     * @return
     */
    private static Table getTable(final Properties properties) {
        int[] coOrds1 = getXYPositions(properties, "table.southwest", "(0,0)");
        int[] coOrds2 = getXYPositions(properties, "table.southeast", "(5,0)");
        int[] coOrds3 = getXYPositions(properties, "table.northeast", "(5,5)");
        int[] coOrds4 = getXYPositions(properties, "table.northwest", "(0,5)");

        // Creates the table where the Robot can play.
        Table table = new Table(new Coordinates(coOrds1[0], coOrds1[1]), new Coordinates(coOrds2[0], coOrds2[1]),
                new Coordinates(coOrds3[0], coOrds3[1]), new Coordinates(coOrds4[0], coOrds4[1]));
        return table;
    }

    /**
     * Gets the system wide properties
     *
     * @return
     */
    private static Properties getProperties() {
        if (PROPERTIES == null) {
            String propPath = System.getProperty("properties.path");
            PROPERTIES = new Properties();

            if (propPath != null) {

                try (FileInputStream propsStream = new FileInputStream(propPath)) {
                    PROPERTIES.load(propsStream);
                } catch (IOException e) {
                    LOGGER.error("An error occurred {}", e.getMessage());
                }
            }
        }
        return PROPERTIES;
    }

    /**
     * Gets the XY positions from the config of the form (1,2)
     *
     * @param props
     * @param propKey
     * @param defaultVal
     * @return
     */
    private static int[] getXYPositions(Properties props, String propKey, String defaultVal) {
        String coOrd1 = (String) props.getOrDefault(propKey, defaultVal);
        Matcher matcher = TBL_POSITIONS_CONFIG.matcher(coOrd1);
        int[] xyPositions = null;
        if (matcher.matches()) {
            xyPositions = new int[] { Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)) };

        }
        return xyPositions;
    }

    /**
     * Play the game (series of commands) and finally return the Robot position
     *
     * @param table
     * @param commands
     * @return
     * @throws RobotException
     */
    public static RobotPosition play(Table table, List<String> commands) throws RobotException {
        for (String inputCmd : commands) {
            Command command = CommandFactory.getCommand(table, inputCmd);
            if (command != null) {
                command.execute();
            }
        }
        return Robot.INSTANCE.getPosition();
    }
}
