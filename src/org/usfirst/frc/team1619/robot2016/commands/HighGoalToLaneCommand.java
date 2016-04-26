package org.usfirst.frc.team1619.robot2016.commands;

import org.usfirst.frc.team1619.robot2016.Constants;
import org.usfirst.frc.team1619.robot2016.framework.CommandSequence;

public class HighGoalToLaneCommand extends CommandSequence {

  public HighGoalToLaneCommand(double initialAngle, int lane, double offset) {
    this(initialAngle, lane, offset, Constants.ARM_POSITION_DEFAULT);
  }

  public HighGoalToLaneCommand(double initialAngle, int lane, double offset,
    double armAngle) {
    double distance;

    switch (lane) {
      case 1:
        distance = Constants.AUTO_LANE_1_DISTANCE;
        break;
      case 2:
        distance = Constants.AUTO_LANE_2_DISTANCE;
        break;
      case 3:
        distance = Constants.AUTO_LANE_3_DISTANCE;
        break;
      case 4:
        distance = Constants.AUTO_LANE_4_DISTANCE;
        break;
      case 5:
        distance = Constants.AUTO_LANE_5_DISTANCE;
        break;
      default:
        distance = 0.0;
        break;
    }

    CommandSequence rotateThenDrive = new CommandSequence();
    rotateThenDrive.add(
      new DriveRotateToAbsoluteCommand((initialAngle + 180.0) % 360, 2000));
    rotateThenDrive
      .add(new DriveTranslateCommand(distance - offset, 0.95, 7.5, 2500));

    CommandSequence driveAndArmDown = new CommandSequence();
    driveAndArmDown.add(rotateThenDrive);
    driveAndArmDown.addPassive(new ArmMoveToPositionCommand(armAngle));

    add(driveAndArmDown);
  }

}
