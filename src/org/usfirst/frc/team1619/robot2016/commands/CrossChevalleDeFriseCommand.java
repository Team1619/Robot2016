package org.usfirst.frc.team1619.robot2016.commands;

import org.usfirst.frc.team1619.robot2016.Constants;
import org.usfirst.frc.team1619.robot2016.framework.CommandGroup;
import org.usfirst.frc.team1619.robot2016.framework.CommandSequence;

public class CrossChevalleDeFriseCommand extends CommandSequence {

  public CrossChevalleDeFriseCommand() {
    CommandSequence startCrossing = new CommandSequence();
    startCrossing.add(new ArmManualCommand(-1.0, 500));
    startCrossing.add(new DriveTranslateCommand(10.0, 0.7, 500));

    CommandGroup raiseArmCrossing = new CommandGroup();

    CommandSequence armDelayed = new CommandSequence();
    armDelayed.add(new PauseCommand(500));
    armDelayed.add(
      new ArmMoveToPositionCommand(Constants.ARM_POSITION_DEFAULT + 1.0, 2000));

    raiseArmCrossing.add(armDelayed);
    raiseArmCrossing.add(new DriveTranslateCommand(85.0, 0.9, 2.5, 3750));

    CommandSequence fullCrossing = new CommandSequence();
    fullCrossing.add(startCrossing);
    fullCrossing.add(raiseArmCrossing);

    add(fullCrossing);
  }

}
