package org.usfirst.frc.team1619.robot2016.states;

import org.usfirst.frc.team1619.robot2016.SubsystemID;
import org.usfirst.frc.team1619.robot2016.commands.DriveRotateCommand;
import org.usfirst.frc.team1619.robot2016.framework.State;

public abstract class DriveRotateToAngle extends State {

  private static SubsystemID[] subsystems;

  private DriveRotateCommand rotateCommand;

  static {
    subsystems = new SubsystemID[] {SubsystemID.DRIVE_TRAIN};
  }

  protected DriveRotateToAngle() {
    super(subsystems);

    rotateCommand = new DriveRotateCommand(getRotationTarget(), 0);
  }

  @Override
  protected void initialize() {
    rotateCommand = new DriveRotateCommand(getRotationTarget());
    rotateCommand.initializeCommand();
  }

  @Override
  protected void update() {
    rotateCommand.updateCommand();
  }

  @Override
  protected void pause() {
    rotateCommand.pause();
  }

  @Override
  protected void destruct() {
    rotateCommand.destruct();
  }

  @Override
  public abstract boolean isReadyForActive();

  protected abstract double getRotationTarget();
}