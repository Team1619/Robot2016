package org.usfirst.frc.team1619.robot2016.commands;

import org.usfirst.frc.team1619.robot2016.Constants;
import org.usfirst.frc.team1619.robot2016.framework.CommandGroup;
import org.usfirst.frc.team1619.robot2016.framework.CommandSequence;

public class CrossRockWallCommand extends CommandSequence {

  public CrossRockWallCommand(double offset) {
    CommandGroup crossRockWall = new CommandGroup();
    crossRockWall
      .add(new DriveTranslateCommand(125.0 + offset, 0.9, 10.0, 3750));
    crossRockWall
      .add(new ArmMoveToPositionCommand(Constants.ARM_POSITION_DEFAULT, 2000));

    add(crossRockWall);
  }

}
