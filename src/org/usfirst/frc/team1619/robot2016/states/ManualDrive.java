package org.usfirst.frc.team1619.robot2016.states;

import org.usfirst.frc.team1619.robot2016.IO.DriverInput;
import org.usfirst.frc.team1619.robot2016.IO.RobotOutput;
import org.usfirst.frc.team1619.robot2016.subsystems.SubsystemID;

public class ManualDrive extends State {

  private static SubsystemID[] subsystems;

  private DriverInput driverInput;
  private RobotOutput robotOutput;

  static {
    subsystems = new SubsystemID[] {SubsystemID.DRIVE_TRAIN};
  }

  public ManualDrive() {
    super(subsystems);

    driverInput = DriverInput.getInstance();
    robotOutput = RobotOutput.getInstance();
  }

  @Override
  protected void setup() {
  }

  @Override
  protected void execute() {
    robotOutput.arcadeDrive(driverInput.getDriverY(),
      driverInput.getDriverTwist());
  }

  @Override
  protected void pause() {
    robotOutput.arcadeDrive(0.0, 0.0);
  }

  @Override
  protected void destroy() {
    robotOutput.arcadeDrive(0.0, 0.0);
  }

  @Override
  public boolean isReadyForActive() {
    return true;
  }

}
