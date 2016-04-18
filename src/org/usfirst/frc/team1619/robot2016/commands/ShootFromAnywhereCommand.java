package org.usfirst.frc.team1619.robot2016.commands;

import org.usfirst.frc.team1619.robot2016.Constants;
import org.usfirst.frc.team1619.robot2016.IO.SocketTables.SmashBoard;
import org.usfirst.frc.team1619.robot2016.framework.CommandGroup;
import org.usfirst.frc.team1619.robot2016.framework.CommandSequence;

public class ShootFromAnywhereCommand extends CommandSequence {

  private SmashBoard smashBoard;

  private int shot;

  public ShootFromAnywhereCommand(int shot) {
    super();

    this.shot = shot;
  }

  @Override
  protected void initialize() {
    smashBoard = SmashBoard.getInstance();

    if (!smashBoard.getGoodContourFound()) {
      return;
    }

    int targetShooterSpeed;
    double targetArmPosition;

    if (shot == 0) {
      shot = smashBoard.getShotRange();
    }

    switch (shot) {
      case 1:
        targetShooterSpeed = Constants.SHOT_BATTER_SHOOT_SPEED;
        targetArmPosition = Constants.SHOT_BATTER_ARM_POSITION;
        break;
      case 2:
        targetShooterSpeed = Constants.SHOT_MID_RANGE_SHOOT_SPEED;
        targetArmPosition = Constants.SHOT_MID_RANGE_ARM_POSITION;
        break;
      case 3:
        targetShooterSpeed = Constants.SHOT_LONG_RANGE_SHOOT_SPEED;
        targetArmPosition = Constants.SHOT_LONG_RANGE_ARM_POSITION;
        break;
      case 4:
        targetShooterSpeed = Constants.SHOT_LONGEST_RANGE_SHOOT_SPEED;
        targetArmPosition = Constants.SHOT_LONGEST_RANGE_ARM_POSITION;
        break;
      default:
        targetShooterSpeed = Constants.SHOT_MID_RANGE_SHOOT_SPEED;
        targetArmPosition = Constants.SHOT_MID_RANGE_ARM_POSITION;
        break;
    }

    CommandGroup rotateToHighGoalAndMoveArm = new CommandGroup();
    rotateToHighGoalAndMoveArm.add(new DriveRotateToHighGoalCommand());
    rotateToHighGoalAndMoveArm
      .add(new ArmMoveToPositionCommand(targetArmPosition, 1750));

    CommandSequence alignAndSpool = new CommandSequence();
    alignAndSpool.add(rotateToHighGoalAndMoveArm);
    alignAndSpool
      .addPassive(new ShootManualCommand(Constants.SHOOTER_SPOOL_UP_SPEED, 0));

    add(alignAndSpool);

    CommandSequence shoot = new CommandSequence();
    shoot.add(new ShootCommand(targetShooterSpeed, 5000));
    shoot.addPassive(new DriveRotateToHighGoalCommand(true));

    add(shoot);

    super.initialize();
  }

  @Override
  public void pause() {
    super.pause();
  }

  @Override
  public void destruct() {
    super.destruct();
  }

}
