package org.usfirst.frc.team1619.robot2016.commands;

import org.usfirst.frc.team1619.robot2016.Constants;
import org.usfirst.frc.team1619.robot2016.framework.CommandSequence;

public class CrossLowBarCommand extends CommandSequence {

  public CrossLowBarCommand() {
    add(new ArmMoveToPositionCommand(Constants.ARM_POSITION_LOW_BAR, 1500));
    add(new DriveTranslateCommand(Constants.AUTO_LOW_BAR_DRIVE_DISTANCE, 0.85, 3000));
  }
  
}
