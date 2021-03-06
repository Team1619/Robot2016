package org.usfirst.frc.team1619.robot2016.commands;

import org.usfirst.frc.team1619.robot2016.Constants;
import org.usfirst.frc.team1619.robot2016.IO.SensorInput;
import org.usfirst.frc.team1619.robot2016.IO.SocketTables.SmashBoard;
import org.usfirst.frc.team1619.robot2016.framework.CommandSequence;

public enum DriveFromDefenseToHighGoalGenerator {
    DEFENSE_1(Constants.DEFENSE_1_X, Constants.DEFENSE_1_Y), DEFENSE_2(
      Constants.DEFENSE_2_X, Constants.DEFENSE_2_Y), DEFENSE_3(
        Constants.DEFENSE_3_X, Constants.DEFENSE_3_Y), DEFENSE_4(
          Constants.DEFENSE_4_X, Constants.DEFENSE_4_Y), DEFENSE_5(
            Constants.DEFENSE_5_X, Constants.DEFENSE_5_Y);

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
    HighGoalTargetPosition targetPosition, int yPositionOffset,
    double initialAngle) {
    double relativeYPosition =
      targetPosition.getTargetYCoordinate() - (yCoordinate + yPositionOffset);
    double relativeXPosition =
      targetPosition.getTargetXCoordinate() - xCoordinate;

//    SmashBoard.getInstance()
    
    double straightDistance = Math
      .sqrt(Math.pow(relativeYPosition, 2) + Math.pow(relativeXPosition, 2));
    double angle =
      Math.toDegrees(Math.atan2(relativeXPosition, relativeYPosition));

    CommandSequence driveSequence = new CommandSequence();

    driveSequence
      .addPassive(new ShootManualCommand(Constants.SHOOTER_SPOOL_UP_SPEED, 0));
    driveSequence.add(new DriveRotateCommand(
      angle - (sensorInput.getNavXHeading() - initialAngle), 1.0, 2000));
    driveSequence
      .add(new DriveTranslateCommand(straightDistance, 0.75, 5.0, 2000));
    driveSequence
      .add(new DriveRotateCommand(-angle + targetPosition.getOffsetAngle()
        + Constants.AUTO_CAMERA_BIAS_OFFSET, 1.0, 1500));

    System.out.println("Straight distance :" + straightDistance + "\nAngle: " + angle);
    SmashBoard.getInstance().setString("pathGenerator", "Straight distance :" + straightDistance + "\nAngle: " + angle);
    SmashBoard.getInstance().setString("TargetEnum" + targetPosition.name(), targetPosition.getTargetXCoordinate() + " " + targetPosition.getTargetYCoordinate() + " " + targetPosition.getOffsetAngle());
    
    return driveSequence;
  }
  
}
