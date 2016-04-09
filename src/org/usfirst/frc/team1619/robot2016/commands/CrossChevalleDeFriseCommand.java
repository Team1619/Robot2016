package org.usfirst.frc.team1619.robot2016.commands;

import org.usfirst.frc.team1619.robot2016.Constants;
import org.usfirst.frc.team1619.robot2016.framework.CommandGroup;
import org.usfirst.frc.team1619.robot2016.framework.CommandSequence;

public class CrossChevalleDeFriseCommand extends CommandSequence {

  public CrossChevalleDeFriseCommand() {
    CommandGroup moveAndArmDown = new CommandGroup();
    moveAndArmDown.add(new DriveTranslateCommand(55.0, 0.8, 2.0, 2500));
    moveAndArmDown.add(
      new ArmMoveToPositionCommand(Constants.ARM_POSITION_DEFAULT + 1.0, 1500));

    // CommandGroup moveForwardAndArmDown = new CommandGroup();
    // CommandSequence armDownThenUp = new CommandSequence();
    // armDownThenUp
    // .add(new ArmMoveToPositionCommand(Constants.ARM_POSITION_INTAKE, 1000));
    // armDownThenUp.add(new ArmManualCommand(1.0, 1500));
    // moveForwardAndArmDown.add(armDownThenUp);
    //
    // CommandSequence waitForDrive = new CommandSequence();
    // waitForDrive.add(new DriveTranslateCommand(10.0, 0.75, 0, 1000));
    // waitForDrive.add(new DriveTranslateCommand(60.0, 1.0, 10.0, 1500));
    // moveForwardAndArmDown.add(waitForDrive);
    //
    // add(moveAndArmDown);
    // add(moveForwardAndArmDown);
    // add(new DriveTranslateCommand(30.0, 0.8, 10.0, 2000));
    CommandSequence startCrossing = new CommandSequence();
    startCrossing
      .add(new ArmMoveToPositionCommand(Constants.ARM_POSITION_INTAKE, 500));
    startCrossing.add(new DriveTranslateCommand(10.0, 0.7, 500));

    CommandSequence fullCrossing = new CommandSequence();
    fullCrossing.add(startCrossing);

    CommandGroup raiseArmCrossing = new CommandGroup();

    CommandSequence armDelayed = new CommandSequence();
    armDelayed.add(new PauseCommand(500));
    armDelayed.add(
      new ArmMoveToPositionCommand(Constants.ARM_POSITION_DEFAULT + 1, 1000));

    raiseArmCrossing.add(armDelayed);
    raiseArmCrossing.add(new DriveTranslateCommand(85.0, 0.9, 2.5, 3750));

    fullCrossing.add(raiseArmCrossing);
    add(moveAndArmDown);
    add(fullCrossing);
  }

}
