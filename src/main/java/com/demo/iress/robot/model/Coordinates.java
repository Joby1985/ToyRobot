package com.demo.iress.robot.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Co-ordinate representation.
 *
 * @author Joby
 *
 */
@Data
@AllArgsConstructor
public class Coordinates {
    private int xPos = 0;
    private int yPos = 0;
}
