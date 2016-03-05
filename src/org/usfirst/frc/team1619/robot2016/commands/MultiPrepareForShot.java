package org.usfirst.frc.team1619.robot2016.commands;

import org.usfirst.frc.team1619.robot2016.framework.Command;
import org.usfirst.frc.team1619.robot2016.framework.CommandGroup;

public class MultiPrepareForShot extends Command {

  private CommandGroup armMoveAndRotateCommandGroup;

  public MultiPrepareForShot(double distance) {
    armMoveAndRotateCommandGroup = new CommandGroup();

    armMoveAndRotateCommandGroup.add(new DriveRotateToHighGoalCommand());
    armMoveAndRotateCommandGroup.add(
      new ArmMoveToPositionCommand(getArmPositionTargetForDistance(distance)));
  }

  @Override
  protected void initialize() {
    armMoveAndRotateCommandGroup.initializeCommand();
  }

  @Override
  protected void update() {
    armMoveAndRotateCommandGroup.updateCommand();
  }

  @Override
  public void pause() {
    armMoveAndRotateCommandGroup.pause();
    robotOutput.setShooterMotor(0.0);
  }

  @Override
  public void destruct() {
    armMoveAndRotateCommandGroup.destruct();
    robotOutput.setShooterMotor(0.0);
  }

  private double getArmPositionTargetForDistance(double distance) {
    return -0.5;
  }

}