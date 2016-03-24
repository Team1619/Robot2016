package org.usfirst.frc.team1619.robot2016;

import org.usfirst.frc.team1619.robot2016.commands.ArmMoveToPositionCommand;
import org.usfirst.frc.team1619.robot2016.commands.DriveTranslateCommand;
import org.usfirst.frc.team1619.robot2016.framework.CommandSequence;

public class CrossLowBarCommand extends CommandSequence {

  public CrossLowBarCommand() {
    add(new ArmMoveToPositionCommand(Constants.ARM_POSITION_LOW_BAR, 2000));
    add(new DriveTranslateCommand(Constants.AUTO_LOW_BAR_DRIVE_DISTANCE, 3000));
  }
  
}
