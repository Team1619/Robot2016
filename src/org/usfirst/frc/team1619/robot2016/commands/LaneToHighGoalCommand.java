package org.usfirst.frc.team1619.robot2016.commands;

import org.usfirst.frc.team1619.robot2016.Constants;
import org.usfirst.frc.team1619.robot2016.IO.SocketTables.SmashBoard;
import org.usfirst.frc.team1619.robot2016.framework.CommandGroup;
import org.usfirst.frc.team1619.robot2016.framework.CommandSequence;

public class LaneToHighGoalCommand extends CommandSequence {

  public LaneToHighGoalCommand(double initialAngle, int lane, double offset) {
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

    double angle = initialAngle;

    if (offset != 0.0) {
      double offsetAngle =
        Math.atan(Math.abs(offset) / distance) * 180 / Math.PI;
      SmashBoard.getInstance().setString("test",
        "" + offsetAngle + ", " + offset);
      if (offset > 0) {
        angle += offsetAngle;
      }
      else {
        angle -= offsetAngle;
      }
    }

    add(new DriveRotateToAbsoluteCommand(angle, 2000));
    add(driveAndArmDown);
    add(new FindContourCommand(left));
  }

}
