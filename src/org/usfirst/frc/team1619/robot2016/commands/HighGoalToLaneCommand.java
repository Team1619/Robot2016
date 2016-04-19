package org.usfirst.frc.team1619.robot2016.commands;

import org.usfirst.frc.team1619.robot2016.Constants;
import org.usfirst.frc.team1619.robot2016.framework.CommandGroup;
import org.usfirst.frc.team1619.robot2016.framework.CommandSequence;

public class HighGoalToLaneCommand extends CommandSequence {

  public HighGoalToLaneCommand(double initialAngle, int lane, double offset) {
    double distance;

    switch (lane) {
      case 1:
        distance = 100.0;
        break;
      case 2:
        distance = 80.0;
        break;
      case 3:
        distance = 30.0;
        break;
      case 4:
        distance = 30.0;
        break;
      case 5:
        distance = 75.0;
        break;
      default:
        distance = 0.0;
        break;
    }

    CommandSequence rotateThenDrive = new CommandSequence();
    rotateThenDrive.add(
      new DriveRotateToAbsoluteCommand((initialAngle + 180.0) % 360, 2000));
    rotateThenDrive.add(new DriveTranslateCommand(distance - offset, 0.95, 7.5, 2500));

    CommandGroup driveAndArmDown = new CommandGroup();
    driveAndArmDown.add(rotateThenDrive);
    driveAndArmDown
      .add(new ArmMoveToPositionCommand(Constants.ARM_POSITION_DEFAULT, 2000));

    add(driveAndArmDown);
  }

}
