package com.demo.iress.robot;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.demo.iress.robot.exceptions.InvalidInputException;
import com.demo.iress.robot.exceptions.RobotException;
import com.demo.iress.robot.model.Coordinates;
import com.demo.iress.robot.model.Direction;
import com.demo.iress.robot.model.Robot;
import com.demo.iress.robot.model.RobotPosition;
import com.demo.iress.robot.model.Table;

/**
 *
 * @author Joby
 *
 */
public class RobotGameTest {
    Table table = null;

    @Before
    public void setup() {
        table = new Table(new Coordinates(0, 0), new Coordinates(0, 10), new Coordinates(5, 0), new Coordinates(5, 10));
        // Ensure that the Single instance's position is reset so that tests would not
        // conflict each other.
        Robot.INSTANCE.setPosition(null);
    }

    // @Test
    public void testPlayWithinBounds() throws RobotException {

        RobotPosition position = RobotGame.play(table, Arrays.asList("PLACE 0,0,NORTH", "MOVE", "REPORT"));
        Assert.assertEquals(0, position.getXPos());
        Assert.assertEquals(1, position.getYPos());
        Assert.assertEquals(Direction.NORTH, position.getDirection());

        position = RobotGame.play(table, Arrays.asList("PLACE 0,0,NORTH", "LEFT", "REPORT"));
        Assert.assertEquals(0, position.getXPos());
        Assert.assertEquals(0, position.getYPos());
        Assert.assertEquals(Direction.WEST, position.getDirection());

        position = RobotGame.play(table, Arrays.asList("PLACE 1,2,EAST", "MOVE", "MOVE", "LEFT", "MOVE", "REPORT"));
        Assert.assertEquals(3, position.getXPos());
        Assert.assertEquals(3, position.getYPos());
        Assert.assertEquals(Direction.NORTH, position.getDirection());

        position = RobotGame.play(table, Arrays.asList("PLACE 2,5,SOUTH", "LEFT", "MOVE", "LEFT", "MOVE", "REPORT"));
        Assert.assertEquals(3, position.getXPos());
        Assert.assertEquals(6, position.getYPos());
        Assert.assertEquals(Direction.NORTH, position.getDirection());
    }

    @Test
    public void testInitialPlacementOutOfBounds() throws RobotException {
        RobotPosition position = RobotGame.play(table, Arrays.asList("PLACE -1,0,NORTH", "MOVE", "REPORT"));
        Assert.assertNull(position);
    }

    @Test
    public void testEnsurePlaySafely() throws RobotException {
        RobotPosition position = RobotGame.play(table,
                Arrays.asList("PLACE 5,9,NORTH", "MOVE", "MOVE", "MOVE", "MOVE", "REPORT"));
        Assert.assertEquals(5, position.getXPos());
        Assert.assertEquals(10, position.getYPos());
        Assert.assertEquals(Direction.NORTH, position.getDirection());
    }

    @Test
    public void testWaitUntilPlaceCommand() throws RobotException {
        RobotPosition position = RobotGame.play(table,
                Arrays.asList("MOVE", "LEFT", "MOVE", "RIGHT", "PLACE 1,10,NORTH", "REPORT"));
        Assert.assertEquals(1, position.getXPos());
        Assert.assertEquals(10, position.getYPos());
        Assert.assertEquals(Direction.NORTH, position.getDirection());
    }

    @Test(expected = InvalidInputException.class)
    public void testInvalidCommand() throws RobotException {
        RobotPosition position = RobotGame.play(table,
                Arrays.asList("PLACE 1,10,NORTH", "MOVE", "LEFT TURN", "MOVE", "RIGHT", "REPORT"));
        Assert.assertNull(position);
    }

    @Test(expected = InvalidInputException.class)
    public void testInvalidCoordinateValues() throws RobotException {
        RobotPosition position = RobotGame.play(table,
                Arrays.asList("PLACE AA,10,NORTH", "MOVE", "LEFT", "MOVE", "RIGHT", "REPORT"));
        Assert.assertNull(position);
    }

    @Test(expected = InvalidInputException.class)
    public void testInvalidDirection() throws RobotException {
        RobotPosition position = RobotGame.play(table,
                Arrays.asList("PLACE 1,10,NORTH-EAST", "MOVE", "LEFT", "MOVE", "RIGHT", "REPORT"));
        Assert.assertNull(position);
    }
}
