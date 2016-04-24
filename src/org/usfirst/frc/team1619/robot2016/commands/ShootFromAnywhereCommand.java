package org.usfirst.frc.team1619.robot2016.commands;

import org.usfirst.frc.team1619.robot2016.Constants;
import org.usfirst.frc.team1619.robot2016.IO.SocketTables.SmashBoard;
import org.usfirst.frc.team1619.robot2016.framework.CommandGroup;
import org.usfirst.frc.team1619.robot2016.framework.CommandSequence;

public class ShootFromAnywhereCommand extends CommandSequence {

  private SmashBoard smashBoard;

  private int shot;

  public ShootFromAnywhereCommand() {
    this(0);
  }

  public ShootFromAnywhereCommand(int shot) {
    super();

    this.shot = shot;
  }

  @Override
  protected void initialize() {
    smashBoard = SmashBoard.getInstance();

    if (!smashBoard.getContourFound()) {
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
    rotateToHighGoalAndMoveArm
      .add(new DriveRotateToHighGoalCommand(Constants.SHOT_ANGLE_TOLERANCE));
    rotateToHighGoalAndMoveArm
      .add(new ArmMoveToPositionCommand(targetArmPosition));

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

}
