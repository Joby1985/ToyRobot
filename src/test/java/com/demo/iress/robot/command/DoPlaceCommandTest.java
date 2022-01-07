package com.demo.iress.robot.command;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.demo.iress.robot.exceptions.RobotException;
import com.demo.iress.robot.model.Coordinates;
import com.demo.iress.robot.model.Direction;
import com.demo.iress.robot.model.Robot;
import com.demo.iress.robot.model.RobotPosition;
import com.demo.iress.robot.model.Table;

/**
 * Test the Turn command
 *
 * @author Joby
 *
 */
public class DoPlaceCommandTest {
    Table table = null;

    @Before
    public void setup() {
        table = new Table(new Coordinates(0, 0), new Coordinates(0, 10), new Coordinates(5, 0), new Coordinates(5, 10));
        // Ensure that the Single instance's position is reset so that tests would not
        // conflict each other.
        Robot.INSTANCE.setPosition(null);
    }

    @Test
    public void testPlacing() throws RobotException {
        DoPlaceCommand cmd = new DoPlaceCommand(Robot.INSTANCE, table, new RobotPosition(0, 0, Direction.NORTH));
        cmd.execute();
        Assert.assertEquals(0, Robot.INSTANCE.getPosition().getXPos());
        Assert.assertEquals(0, Robot.INSTANCE.getPosition().getYPos());
        Assert.assertEquals(Direction.NORTH, Robot.INSTANCE.getPosition().getDirection());
    }

    @Test
    public void testPlacingOutOfBounds() throws RobotException {
        DoPlaceCommand cmd = new DoPlaceCommand(Robot.INSTANCE, table, new RobotPosition(10, 0, Direction.NORTH));
        cmd.execute();
        Assert.assertNull(Robot.INSTANCE.getPosition());
    }
}
