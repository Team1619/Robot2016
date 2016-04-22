package org.usfirst.frc.team1619.robot2016.commands;

import org.usfirst.frc.team1619.robot2016.Constants;
import org.usfirst.frc.team1619.robot2016.framework.CommandGroup;
import org.usfirst.frc.team1619.robot2016.framework.CommandSequence;

public class LaneToHighGoalCommand extends CommandSequence {

  public LaneToHighGoalCommand(double initialAngle, int lane) {
    double distance;
    boolean left;

    switch (lane) {
      case 1:
        distance = Constants.AUTO_LANE_1_DISTANCE;
        left = false;
        break;
      case 2:
        distance = Constants.AUTO_LANE_2_DISTANCE;
        left = false;
        break;
      case 3:
        distance = Constants.AUTO_LANE_3_DISTANCE;
        left = false;
        break;
      case 4:
        distance = Constants.AUTO_LANE_4_DISTANCE;
        left = true;
        break;
      case 5:
        distance = Constants.AUTO_LANE_5_DISTANCE;
        left = true;
        break;
      default:
        distance = 0.0;
        left = true;
        break;
    }

    CommandGroup driveAndArmDown = new CommandGroup();
    driveAndArmDown.add(new DriveTranslateCommand(distance, 0.95, 10.0, 2500));
    driveAndArmDown
      .add(new ArmMoveToPositionCommand(Constants.ARM_POSITION_VISION, 2500));

    add(new DriveRotateToAbsoluteCommand(initialAngle));
    add(driveAndArmDown);
    add(new FindContourCommand(left));
  }

}
