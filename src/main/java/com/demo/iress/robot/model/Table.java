package com.demo.iress.robot.model;

/**
 * Model representing Table.
 *
 * @author Joby
 *
 */
public class Table {

    private int leastXCoord;
    private int leastYCoord;
    private int highestXCoord;
    private int highestYCoord;

    public Table(Coordinates coord1, Coordinates coord2, Coordinates coord3, Coordinates coord4) {
        leastXCoord = getMinValue(new int[] { coord1.getXPos(), coord2.getXPos(), coord3.getXPos(), coord4.getXPos() });
        leastYCoord = getMinValue(new int[] { coord1.getYPos(), coord2.getYPos(), coord3.getYPos(), coord4.getYPos() });

        highestXCoord = getMaxValue(
                new int[] { coord1.getXPos(), coord2.getXPos(), coord3.getXPos(), coord4.getXPos() });
        highestYCoord = getMaxValue(
                new int[] { coord1.getYPos(), coord2.getYPos(), coord3.getYPos(), coord4.getYPos() });

    }

    /**
     * Is the position within the Table boundaries?
     *
     * @param robotPosition
     * @return
     */
    public boolean isBoundedMove(RobotPosition robotPosition) {
        return leastXCoord <= robotPosition.getXPos() && highestXCoord >= robotPosition.getXPos()
                && leastYCoord <= robotPosition.getYPos() && highestYCoord >= robotPosition.getYPos();
    }

    public static int getMaxValue(int[] numbers) {
        int maxValue = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] > maxValue) {
                maxValue = numbers[i];
            }
        }
        return maxValue;
    }

    public static int getMinValue(int[] numbers) {
        int minValue = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] < minValue) {
                minValue = numbers[i];
            }
        }
        return minValue;
    }
}
