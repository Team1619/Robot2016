package org.usfirst.frc.team1619.robot2016.commands;

import org.usfirst.frc.team1619.robot2016.Constants;
import org.usfirst.frc.team1619.robot2016.framework.CommandGroup;
import org.usfirst.frc.team1619.robot2016.framework.CommandSequence;

public class ShootAlignHighGoalCommand extends CommandGroup {

  protected CommandSequence firingSequence;
  protected CommandSequence moveArmAndSpoolUp;

  public ShootAlignHighGoalCommand(int timeOut) {
    this(Constants.SHOOTER_SHOOT_SPEED_TARGET, timeOut);
  }

  public ShootAlignHighGoalCommand(int speedTarget, int timeOut) {
    this(speedTarget, Constants.ARM_POSITION_SHOOT_OUTERWORKS, timeOut);
  }

  public ShootAlignHighGoalCommand(int speedTarget, double armPosition,
    int timeOut) {
    super(timeOut);

    moveArmAndSpoolUp = new CommandSequence();
    firingSequence = new CommandSequence();

    moveArmAndSpoolUp.add(new ArmMoveToPositionCommand(armPosition, 1000));
    moveArmAndSpoolUp
      .addPassive(new ShootManualCommand(Constants.SHOOTER_SPOOL_UP_SPEED, 0));

    firingSequence.add(moveArmAndSpoolUp);
    firingSequence.add(new ShootCommand(speedTarget, 2000));

    add(new DriveRotateToHighGoalCommand());
    add(firingSequence);
  }
}
