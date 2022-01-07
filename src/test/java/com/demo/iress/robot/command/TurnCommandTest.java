package com.demo.iress.robot.command;

import org.junit.Assert;
import org.junit.Test;

import com.demo.iress.robot.exceptions.RobotException;
import com.demo.iress.robot.model.Direction;
import com.demo.iress.robot.model.Robot;
import com.demo.iress.robot.model.RobotPosition;

/**
 * Test the Turn command
 *
 * @author Joby
 *
 */
public class TurnCommandTest {

    @Test
    public void testTurnRightWhenInEastDirection() throws RobotException {
        TurnCommand cmd = new TurnCommand(Robot.INSTANCE, "RIGHT");
        Robot.INSTANCE.setPosition(new RobotPosition(0, 0, Direction.EAST));
        cmd.execute();
        Assert.assertEquals(Direction.SOUTH, Robot.INSTANCE.getPosition().getDirection());
    }

    @Test
    public void testTurnLeftWhenInEastDirection() throws RobotException {
        TurnCommand cmd = new TurnCommand(Robot.INSTANCE, "LEFT");
        Robot.INSTANCE.setPosition(new RobotPosition(0, 0, Direction.EAST));
        cmd.execute();
        Assert.assertEquals(Direction.NORTH, Robot.INSTANCE.getPosition().getDirection());
    }

    @Test
    public void testTurnRightWhenInSouthDirection() throws RobotException {
        TurnCommand cmd = new TurnCommand(Robot.INSTANCE, "RIGHT");
        Robot.INSTANCE.setPosition(new RobotPosition(0, 0, Direction.SOUTH));
        cmd.execute();
        Assert.assertEquals(Direction.WEST, Robot.INSTANCE.getPosition().getDirection());
    }

    @Test
    public void testTurnLeftWhenInSouthDirection() throws RobotException {
        TurnCommand cmd = new TurnCommand(Robot.INSTANCE, "LEFT");
        Robot.INSTANCE.setPosition(new RobotPosition(0, 0, Direction.SOUTH));
        cmd.execute();
        Assert.assertEquals(Direction.EAST, Robot.INSTANCE.getPosition().getDirection());
    }

    @Test
    public void testTurnRightWhenInWestDirection() throws RobotException {
        TurnCommand cmd = new TurnCommand(Robot.INSTANCE, "RIGHT");
        Robot.INSTANCE.setPosition(new RobotPosition(0, 0, Direction.WEST));
        cmd.execute();
        Assert.assertEquals(Direction.NORTH, Robot.INSTANCE.getPosition().getDirection());
    }

    @Test
    public void testTurnLeftWhenInWestDirection() throws RobotException {
        TurnCommand cmd = new TurnCommand(Robot.INSTANCE, "LEFT");
        Robot.INSTANCE.setPosition(new RobotPosition(0, 0, Direction.WEST));
        cmd.execute();
        Assert.assertEquals(Direction.SOUTH, Robot.INSTANCE.getPosition().getDirection());
    }

    @Test
    public void testTurnRightWhenInNorthDirection() throws RobotException {
        TurnCommand cmd = new TurnCommand(Robot.INSTANCE, "RIGHT");
        Robot.INSTANCE.setPosition(new RobotPosition(0, 0, Direction.NORTH));
        cmd.execute();
        Assert.assertEquals(Direction.EAST, Robot.INSTANCE.getPosition().getDirection());
    }

    @Test
    public void testTurnLeftWhenInNorthDirection() throws RobotException {
        TurnCommand cmd = new TurnCommand(Robot.INSTANCE, "LEFT");
        Robot.INSTANCE.setPosition(new RobotPosition(0, 0, Direction.NORTH));
        cmd.execute();
        Assert.assertEquals(Direction.WEST, Robot.INSTANCE.getPosition().getDirection());
    }
}
