package org.usfirst.frc.team1619.robot2016.commands;

import org.usfirst.frc.team1619.robot2016.Constants;
import org.usfirst.frc.team1619.robot2016.IO.RobotOutput;
import org.usfirst.frc.team1619.robot2016.IO.SensorInput;
import org.usfirst.frc.team1619.robot2016.util.GenericPID;

public class DriveStraightCommand implements Command {

  private RobotOutput robotOutput;
  private SensorInput sensorInput;

  private GenericPID rotationPID;

  private int reached;

  @Override
  public void initialize() {
    robotOutput = RobotOutput.getInstance();
    sensorInput = SensorInput.getInstance();

    sensorInput.resetNavXHeading();

    rotationPID = new GenericPID();
    rotationPID.setValues(Constants.DRIVE_PID_ROTATION);
    rotationPID.setIRange(Constants.DRIVE_PID_ROTATION_IRANGE);

    rotationPID.setTarget(0.0);

    reached = 0;
  }

  @Override
  public void update() {
    if (reached == 0 && Math.abs(sensorInput.getNavXPitch())
      + Math.abs(sensorInput.getNavXRoll()) > 10.0) {
      reached++;
    }
    else if (reached > 0 && Math.abs(sensorInput.getNavXAccelX())
      + Math.abs(sensorInput.getNavXAccelY()) < 0.375) {
      reached++;
    }
    else if (reached > 0) {
      reached = 1;
    }

    rotationPID.calculate(((sensorInput.getNavXHeading() + 180) % 360) - 180);
    robotOutput.arcadeDrive(-0.9, rotationPID.get());
  }

  @Override
  public void destruct() {
    robotOutput.arcadeDrive(0.0, 0.0);
  }

  @Override
  public boolean finished() {
    return reached > 6;
  }

}
