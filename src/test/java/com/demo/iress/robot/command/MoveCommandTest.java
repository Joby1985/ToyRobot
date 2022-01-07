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
public class MoveCommandTest {
    Table table = null;

    @Before
    public void setup() {
        table = new Table(new Coordinates(0, 0), new Coordinates(0, 10), new Coordinates(5, 0), new Coordinates(5, 10));
    }

    @Test
    public void testMove() throws RobotException {
        Robot.INSTANCE.setPosition(new RobotPosition(5, 9, Direction.NORTH));

        MoveCommand cmd = new MoveCommand(Robot.INSTANCE, table);
        cmd.execute();
        Assert.assertEquals(5, Robot.INSTANCE.getPosition().getXPos());
        Assert.assertEquals(10, Robot.INSTANCE.getPosition().getYPos());
        Assert.assertEquals(Direction.NORTH, Robot.INSTANCE.getPosition().getDirection());
    }

    @Test
    public void testSafeMove() throws RobotException {
        Robot.INSTANCE.setPosition(new RobotPosition(5, 10, Direction.NORTH));

        MoveCommand cmd = new MoveCommand(Robot.INSTANCE, table);
        cmd.execute();
        Assert.assertEquals(5, Robot.INSTANCE.getPosition().getXPos());
        Assert.assertEquals(10, Robot.INSTANCE.getPosition().getYPos());
        Assert.assertEquals(Direction.NORTH, Robot.INSTANCE.getPosition().getDirection());
    }
}
