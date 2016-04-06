package org.usfirst.frc.team1619.robot2016.commands;

import org.usfirst.frc.team1619.robot2016.Constants;
import org.usfirst.frc.team1619.robot2016.framework.CommandGroup;
import org.usfirst.frc.team1619.robot2016.framework.CommandSequence;

public class CrossRockWallCommand extends CommandSequence {

  public CrossRockWallCommand(double offset) {
    CommandGroup crossLowBar = new CommandGroup();
    crossLowBar
      .add(new DriveTranslateCommand(125.0 + offset, 0.85, 10.0, 3750));
    crossLowBar
      .add(new ArmMoveToPositionCommand(Constants.ARM_POSITION_DEFAULT, 1500));

    add(crossLowBar);
  }

}
