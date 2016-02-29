package org.usfirst.frc.team1619.robot2016.states;

import org.usfirst.frc.team1619.robot2016.SubsystemID;
import org.usfirst.frc.team1619.robot2016.framework.State;
import org.usfirst.frc.team1619.robot2016.util.PID.DriveRotationPID;
import org.usfirst.frc.team1619.robot2016.util.PID.DriveTranslationPID;

public abstract class DriveRotateToAngle extends State {

  private static SubsystemID[] subsystems;

  private DriveRotationPID driveRotationPID;

  static {
    subsystems = new SubsystemID[] {SubsystemID.DRIVE_TRAIN};
  }

  protected DriveRotateToAngle() {
    super(subsystems);

    new DriveTranslationPID();
    driveRotationPID = new DriveRotationPID();
  }

  /**
   * PID values are set using values in Constants, target is retrieved from
   * RobotState, then target of the PID is set to 0 (because the value
   * calculated for the current angle is normalized by the target. This allows
   * for one fewer calculation per iteration)
   */
  @Override
  protected void initialize() {
    driveRotationPID.setTarget(getRotationTarget());
  }

  @Override
  protected void update() {
    driveRotationPID.calculate();
    robotOutput.arcadeDrive(0, driveRotationPID.get());
  }

  @Override
  protected void pause() {
    robotOutput.arcadeDrive(0, 0);
  }

  @Override
  protected void destruct() {
    robotOutput.arcadeDrive(0, 0);
    driveRotationPID.reset();
  }

  @Override
  public abstract boolean isReadyForActive();

  protected abstract double getRotationTarget();
}