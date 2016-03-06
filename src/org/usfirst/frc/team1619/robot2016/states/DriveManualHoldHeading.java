package org.usfirst.frc.team1619.robot2016.states;

import org.usfirst.frc.team1619.robot2016.Constants;
import org.usfirst.frc.team1619.robot2016.SubsystemID;
import org.usfirst.frc.team1619.robot2016.framework.State;
import org.usfirst.frc.team1619.robot2016.util.PID.DriveRotationPID;

public class DriveManualHoldHeading extends State {

  private static SubsystemID[] subsystems;

  static {
    subsystems = new SubsystemID[] {SubsystemID.DRIVE_TRAIN};
  }

  protected DriveRotationPID rotationPID;

  public DriveManualHoldHeading() {
    super(subsystems);

    rotationPID = new DriveRotationPID();
  }

  @Override
  protected void initialize() {
    rotationPID.reset();
    rotationPID.setTarget(0);
  }

  @Override
  protected void update() {
    rotationPID.calculate();
    robotOutput.arcadeDrive(driverInput.getDriverY(), rotationPID.get());
  }

  @Override
  protected void destruct() {
  }

  @Override
  protected void pause() {
    robotOutput.arcadeDrive(0, 0);
  }

  @Override
  public boolean isReadyForActive() {
    /*
     * If State is inactive, it's ready when all of the following are true:
     *  -Button is pressed
     *  -Driver is not turning the joystick 
     *  -Robot is not already turning
     * If State is active, it's ready when:
     *  -Button is pressed
     *  -Driver is not turning
     */
    if(isActive()) {
      return driverInput.getDriverButton(
        Constants.DRIVER_BUTTON_DRIVE_HOLD_HEADING)
        && Math.abs(driverInput.getDriverTwist()) < Constants.JOYSTICK_DEADZONE;
    }
    else {
      return driverInput
        .getDriverButton(Constants.DRIVER_BUTTON_DRIVE_HOLD_HEADING)
        && Math.abs(driverInput.getDriverTwist()) < Constants.JOYSTICK_DEADZONE
        && Math.abs(sensorInput.getNavXRotationSpeed()) 
        < Constants.DRIVE_HOLD_HEADING_START_TOLERANCE;
    }
  }

}
