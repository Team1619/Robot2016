package org.usfirst.frc.team1619.robot2016.commands;

import org.usfirst.frc.team1619.robot2016.framework.CommandGroup;
import org.usfirst.frc.team1619.robot2016.framework.CommandSequence;

public class LaneToHighGoalCommand extends CommandSequence {

  public LaneToHighGoalCommand(int lane, double offset) {
    double distance;
    boolean left;

    switch (lane) {
      case 1:
        distance = 100.0;
        left = false;
        break;
      case 2:
        distance = 80.0;
        left = false;
        break;
      case 3:
        distance = 30.0;
        left = false;
        break;
      case 4:
        distance = 30.0;
        left = true;
        break;
      case 5:
        distance = 75.0;
        left = true;
        break;
      default:
        distance = 0.0;
        left = true;
        break;
    }

    CommandGroup driveAndArmDown = new CommandGroup();
    driveAndArmDown
      .add(new DriveTranslateCommand(distance - offset, 0.95, 10.0, 2500));
    driveAndArmDown.add(new ArmMoveToPositionCommand(0.0, 2500));

    add(driveAndArmDown);
    add(new FindContourCommand(left));
  }

}
