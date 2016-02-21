package org.usfirst.frc.team1619.robot2016.commands;

import org.usfirst.frc.team1619.robot2016.Constants;
import org.usfirst.frc.team1619.robot2016.IO.RobotOutput;
import org.usfirst.frc.team1619.robot2016.IO.SensorInput;
import org.usfirst.frc.team1619.robot2016.util.GenericPID;

public class RotateByAngle implements Command {

  private RobotOutput robotOutput;
  private SensorInput sensorInput;

  private GenericPID rotationPID;

  private double offsetAngle;
  private double targetAngle;

  private int done;

  public RotateByAngle(double angle) {
    offsetAngle = angle;

    robotOutput = RobotOutput.getInstance();
    sensorInput = SensorInput.getInstance();

    rotationPID = new GenericPID();
    rotationPID.setValues(Constants.DRIVE_PID_ROTATION);
    rotationPID.setIRange(Constants.DRIVE_PID_ROTATION_IRANGE);
  }

  @Override
  public void initialize() {
    targetAngle = (sensorInput.getNavXHeading() + offsetAngle + 360) % 360;
    rotationPID.setTarget(0.0);

    done = 0;
  }

  @Override
  public void update() {
    double difference =
      ((sensorInput.getNavXHeading() - targetAngle + 540) % 360) - 180;

    if (Math.abs(difference) < 0.25) {
      done++;
    }
    else if (done > 0) {
      done = 0;
    }

    rotationPID.calculate(difference);
    robotOutput.arcadeDrive(0.0,
      Math.max(Math.min(rotationPID.get(), 0.625), -0.625));
  }

  @Override
  public void destruct() {
    robotOutput.arcadeDrive(0.0, 0.0);
  }

  @Override
  public boolean finished() {
    return done > 1;
  }

}
