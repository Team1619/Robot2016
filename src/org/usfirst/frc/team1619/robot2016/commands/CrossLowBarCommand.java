package org.usfirst.frc.team1619.robot2016.commands;

import org.usfirst.frc.team1619.robot2016.Constants;
import org.usfirst.frc.team1619.robot2016.framework.CommandGroup;
import org.usfirst.frc.team1619.robot2016.framework.CommandSequence;

public class CrossLowBarCommand extends CommandSequence {

  public CrossLowBarCommand() {
    CommandSequence waitAndDrive = new CommandSequence();
    waitAndDrive.add(new PauseCommand(500));
    waitAndDrive.add(new DriveTranslateCommand(
      Constants.AUTO_LOW_BAR_DRIVE_DISTANCE, 0.85, 2500));

    CommandGroup crossLowBar = new CommandGroup();
    crossLowBar.add(waitAndDrive);
    crossLowBar
      .add(new ArmMoveToPositionCommand(Constants.ARM_POSITION_LOW_BAR, 1500));

    add(crossLowBar);
  }

}
