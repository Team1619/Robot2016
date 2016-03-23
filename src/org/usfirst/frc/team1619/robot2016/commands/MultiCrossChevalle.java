package org.usfirst.frc.team1619.robot2016.commands;

import org.usfirst.frc.team1619.robot2016.Constants;
import org.usfirst.frc.team1619.robot2016.framework.CommandGroup;
import org.usfirst.frc.team1619.robot2016.framework.CommandSequence;

public class MultiCrossChevalle extends CommandSequence {

  public MultiCrossChevalle() {
    CommandGroup moveAndArmDown = new CommandGroup();
    moveAndArmDown.add(new DriveTranslateCommand(50.0, 0.8, 1.5, 3750));
    moveAndArmDown
      .add(new ArmMoveToPositionCommand(Constants.ARM_POSITION_DEFAULT + 1.0));

    CommandGroup moveForwardAndArmDown = new CommandGroup();
    CommandSequence waitForDrive = new CommandSequence();
    waitForDrive.add(new PauseCommand(750));
    waitForDrive.add(new DriveTranslateCommand(60.0, 1.0, 10.0, 1500));
    moveForwardAndArmDown.add(waitForDrive);
    moveForwardAndArmDown
      .add(new ArmMoveToPositionCommand(Constants.ARM_POSITION_INTAKE));
    CommandGroup finishCross = new CommandGroup();
    finishCross.add(new DriveTranslateCommand(25.0, 1.0, 5.0, 2000));
    finishCross
      .add(new ArmMoveToPositionCommand(Constants.ARM_POSITION_DEFAULT, 2000));

    add(moveAndArmDown);
    add(moveForwardAndArmDown);
    add(finishCross);
  }

}
