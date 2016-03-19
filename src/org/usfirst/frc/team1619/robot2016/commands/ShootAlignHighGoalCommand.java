package org.usfirst.frc.team1619.robot2016.commands;

import org.usfirst.frc.team1619.robot2016.Constants;
import org.usfirst.frc.team1619.robot2016.framework.CommandGroup;
import org.usfirst.frc.team1619.robot2016.framework.CommandSequence;

public class ShootAlignHighGoalCommand extends CommandGroup {

  private CommandSequence firingSequence;

  public ShootAlignHighGoalCommand(int timeOut) {
    super(timeOut);

    firingSequence = new CommandSequence();

    firingSequence.add(new ArmMoveToPositionCommand(Constants.ARM_POSITION_SHOOT_HIGH, 1000));
    firingSequence.add(new ShootCommand(Constants.SHOOTER_SHOOT_SPEED_TARGET, 2000));

    add(new DriveRotateToHighGoalCommand());
    add(firingSequence);
  }
}
