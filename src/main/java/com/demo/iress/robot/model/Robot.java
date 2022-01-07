package com.demo.iress.robot.model;

/**
 * Enum Model representing Robot.
 *
 * Set as a ENUM so as to easily achieve Singleton requirement.
 *
 * @author Joby
 *
 */
public enum Robot {
    INSTANCE;

    private RobotPosition position;

    private String name;

    public void setPosition(RobotPosition position) {
        this.position = position;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RobotPosition getPosition() {
        return position;
    }

    public String getName() {
        return name;
    }
}
