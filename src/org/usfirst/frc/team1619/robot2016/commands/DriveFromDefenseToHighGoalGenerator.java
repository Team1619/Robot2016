package org.usfirst.frc.team1619.robot2016.commands;

import org.usfirst.frc.team1619.robot2016.Constants;
import org.usfirst.frc.team1619.robot2016.IO.SensorInput;
import org.usfirst.frc.team1619.robot2016.framework.CommandSequence;

public enum DriveFromDefenseToHighGoalGenerator {
    DEFENSE_1(Constants.DEFENSE_1_X, Constants.DEFENSE_1_Y),
    DEFENSE_2(Constants.DEFENSE_2_X, Constants.DEFENSE_2_Y),
    DEFENSE_3(Constants.DEFENSE_3_X, Constants.DEFENSE_3_Y),
    DEFENSE_4(Constants.DEFENSE_4_X, Constants.DEFENSE_4_Y),
    DEFENSE_5(Constants.DEFENSE_5_X, Constants.DEFENSE_5_Y);

  private SensorInput sensorInput;

  private double xCoordinate;
  private double yCoordinate;

  private DriveFromDefenseToHighGoalGenerator(double xCoordinate,
    double yCoordinate) {
    sensorInput = SensorInput.getInstance();

    this.xCoordinate = xCoordinate;
    this.yCoordinate = Math.abs(yCoordinate);
  }

  public double getRobotXCoordinate() {
    return xCoordinate;
  }

  public double getRobotYCoordinate() {
    return yCoordinate;
  }

  public CommandSequence getDriveToTargetGoalSequence(
    HighGoalTargetPosition targetPosition, int yPositionOffset) {
    double relativeYPosition =
      targetPosition.getTargetYCoordinate() - (yCoordinate + yPositionOffset);
    double relativeXPosition =
      targetPosition.getTargetXCoordinate() - xCoordinate;

    double straightDistance = Math
      .sqrt(Math.pow(relativeYPosition, 2) + Math.pow(relativeXPosition, 2));
    double angle = (180 / Math.PI)
      * Math.atan(Math.abs(relativeXPosition) / relativeYPosition);

    if (relativeXPosition < 0.0) {
      angle = -angle;
    }

    CommandSequence driveSequence = new CommandSequence();

    driveSequence.add(new DriveRotateCommand(angle, 1.0, 2000));
    driveSequence
      .add(new DriveTranslateCommand(straightDistance, 0.75, 5.0, 2000));
    driveSequence.add(new DriveRotateCommand(
      -angle + targetPosition.getOffsetAngle(), 1.0, 1500));

    System.out.println(angle);
    System.out.println(straightDistance);
    System.out.println(-angle + targetPosition.getOffsetAngle());
    return driveSequence;
  }
}
