package org.usfirst.frc.team1619.robot2016.states;

import org.usfirst.frc.team1619.robot2016.subsystems.DrivePID;
import org.usfirst.frc.team1619.robot2016.subsystems.SubsystemID;

public abstract class RotateToAngle extends State {

  private static SubsystemID[] subsystems;
  
  private DrivePID drivePID;
  private double target;
  
  static {
    subsystems = new SubsystemID[] {SubsystemID.DRIVE_TRAIN};
  }

  protected RotateToAngle() {
    super(subsystems);

    drivePID = DrivePID.getInstance();
  }

  /**
   * PID values are set using values in Constants, target is retrieved from 
   * RobotState, then target of the PID is set to 0 (because the value 
   * calculated for the current angle is normalized by the target. This allows 
   * for one fewer calculation per iteration)
   */
  @Override
  protected void setup() {
    drivePID.setRotationTarget(getRotationTarget());
  }

  @Override
  protected void execute() {
    robotOutput.arcadeDrive(0, drivePID.getRotation());
  }

  @Override
  protected void pause() {
    robotOutput.arcadeDrive(0, 0);
  }

  @Override
  protected void destroy() {
    robotOutput.arcadeDrive(0, 0);
    target = 0.0;
    drivePID.resetRotation();
  }

  @Override
  public abstract boolean isReadyForActive();
  
  protected abstract double getRotationTarget();
}