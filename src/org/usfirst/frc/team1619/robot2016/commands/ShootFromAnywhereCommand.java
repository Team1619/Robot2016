package org.usfirst.frc.team1619.robot2016.commands;

import org.usfirst.frc.team1619.robot2016.Constants;
import org.usfirst.frc.team1619.robot2016.IO.SocketTables.SmashBoard;
import org.usfirst.frc.team1619.robot2016.framework.CommandGroup;
import org.usfirst.frc.team1619.robot2016.framework.CommandSequence;

public class ShootFromAnywhereCommand extends CommandSequence {

  private SmashBoard smashBoard;

  private boolean left;

  public ShootFromAnywhereCommand(boolean left) {
    super();

    this.left = left;

    smashBoard = SmashBoard.getInstance();
  }

  @Override
  protected void initialize() {
    double distance = smashBoard.getDistance();

    int targetShooterSpeed;
    double targetArmPosition;

    if (distance < Constants.SHOT_BATTER_CUT_OFF) {
      targetShooterSpeed = Constants.SHOT_BATTER_SHOOT_SPEED;
      targetArmPosition = Constants.SHOT_BATTER_ARM_POSITION;
      smashBoard.setShotRange(1);
    }
    else if (distance < Constants.SHOT_MID_RANGE_CUT_OFF) {
      targetShooterSpeed = Constants.SHOT_MID_RANGE_SHOOT_SPEED;
      targetArmPosition = Constants.SHOT_MID_RANGE_ARM_POSITION;
      smashBoard.setShotRange(2);
    }
    else if (distance < Constants.SHOT_LONG_RANGE_CUT_OFF) {
      targetShooterSpeed = Constants.SHOT_LONG_RANGE_SHOOT_SPEED;
      targetArmPosition = Constants.SHOT_LONG_RANGE_ARM_POSITION;
      smashBoard.setShotRange(3);
    }
    else {
      targetShooterSpeed = Constants.SHOT_LONGEST_RANGE_SHOOT_SPEED;
      targetArmPosition = Constants.SHOT_LONGEST_RANGE_ARM_POSITION;
      smashBoard.setShotRange(4);
    }

    CommandGroup rotateToHighGoalAndMoveArm = new CommandGroup();
    rotateToHighGoalAndMoveArm.add(new DriveRotateToHighGoalCommand());
    rotateToHighGoalAndMoveArm
      .add(new ArmMoveToPositionCommand(targetArmPosition));

    CommandSequence alignAndSpool = new CommandSequence();
    alignAndSpool.add(rotateToHighGoalAndMoveArm);
    alignAndSpool
      .addPassive(new ShootManualCommand(Constants.SHOOTER_SPOOL_UP_SPEED, 0));

    if (!smashBoard.getContourFound()) {
      add(new FindContourCommand(left));
    }

    add(alignAndSpool);
    add(new ShootCommand(targetShooterSpeed, 5000));

    super.initialize();
  }

  @Override
  public void pause() {
    super.pause();

    smashBoard.setShotRange(0);
  }

  @Override
  public void destruct() {
    super.destruct();

    smashBoard.setShotRange(0);
  }

}
