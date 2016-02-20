package org.usfirst.frc.team1619.robot2016.states;

import java.awt.print.Printable;

import org.usfirst.frc.team1619.robot2016.Constants;
import org.usfirst.frc.team1619.robot2016.subsystems.SubsystemID;
import org.usfirst.frc.team1619.robot2016.util.GenericPID;

public abstract class RotateToAngle extends State {

  private static SubsystemID[] subsystems;
  
  private GenericPID rotationPID;
  private double target;
  
  static {
    subsystems = new SubsystemID[] {SubsystemID.DRIVE_TRAIN};
  }

  protected RotateToAngle() {
    super(subsystems);

    rotationPID = new GenericPID();
  }

  /**
   * PID values are set using values in Constants, target is retrieved from 
   * RobotState, then target of the PID is set to 0 (because the value 
   * calculated for the current angle is normalized by the target. This allows 
   * for one fewer calculation per iteration)
   */
  @Override
  protected void setup() {
    rotationPID.setValues(Constants.DRIVE_PID_ROTATION);
    rotationPID.setIRange(Constants.DRIVE_PID_ROTATION_IRANGE);
    
    rotationPID.setTarget(0);
    target = getRotationTarget();
  }

  @Override
  protected void execute() {
    rotationPIDCalc();
    robotOutput.arcadeDrive(0, 0.5*rotationPID.get());
  }

  @Override
  protected void pause() {
    robotOutput.arcadeDrive(0, 0);
  }

  @Override
  protected void destroy() {
    robotOutput.arcadeDrive(0, 0);
    target = 0.0;
    rotationPID.reset();
  }

  @Override
  public boolean isReadyForActive() {
    return driverInput.getDriverButton(Constants.DRIVER_BUTTON_DRIVE_PID_TURN);
  }
  
  protected abstract double getRotationTarget();
  
  private void rotationPIDCalc() {
    double currentAngle = ((sensorInput.getNavXHeading() + 540 - target) % 360) - 180;
    rotationPID.calculate(currentAngle);
  }

}