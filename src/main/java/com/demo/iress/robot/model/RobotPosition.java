package com.demo.iress.robot.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Model Representing Position of Robot.
 *
 * @author Joby
 *
 */
@Getter
@Setter
public class RobotPosition extends Coordinates {
    private Direction direction;

    public RobotPosition(int xPos, int yPos, Direction direction) {
        super(xPos, yPos);
        this.direction = direction;
    }

    @Override
    public RobotPosition clone() {
        return new RobotPosition(getXPos(), getYPos(), getDirection());
    }

    @Override
    public String toString() {
        return super.getXPos() + "," + super.getYPos() + "," + direction.name();
    }

}
