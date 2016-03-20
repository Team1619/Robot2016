package org.usfirst.frc.team1619.robot2016.states;

import org.usfirst.frc.team1619.robot2016.Constants;
import org.usfirst.frc.team1619.robot2016.SubsystemID;
import org.usfirst.frc.team1619.robot2016.framework.State;

public class DriveManual extends State {

  private static SubsystemID[] subsystems;

  static {
    subsystems = new SubsystemID[] {SubsystemID.DRIVE_TRAIN};
  }

  public DriveManual() {
    super(subsystems);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void update() {
    robotOutput.arcadeDrive(driverInput.getDriverY(),
      driverInput.getDriverTwist());
  }

  @Override
  protected void pause() {
    robotOutput.arcadeDrive(0.0, 0.0);
  }

  @Override
  protected void destruct() {
    robotOutput.arcadeDrive(0.0, 0.0);
  }

  @Override
  public boolean isReadyForActive() {
    return true;
  }

}
