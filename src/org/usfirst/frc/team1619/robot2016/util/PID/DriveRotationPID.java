package org.usfirst.frc.team1619.robot2016.util.PID;

import org.usfirst.frc.team1619.robot2016.Constants;
import org.usfirst.frc.team1619.robot2016.IO.SensorInput;

public class DriveRotationPID extends PIDKachigBand {
  SensorInput sensorInput;
  double targetAngle;

  public DriveRotationPID() {
    super();
    sensorInput = SensorInput.getInstance();
    targetAngle = 0;

    setValues(Constants.DRIVE_PID_ROTATION);
    setIRange(Constants.DRIVE_PID_ROTATION_IRANGE);
    setKachigBand(Constants.DRIVE_PID_ROTATION_KACHIG_BAND);
    setKachigConstant(Constants.DRIVE_PID_ROTATION_KACHIG_CONSTANT);
    setKachigTime(Constants.DRIVE_PID_ROTATION_KACHIG_ONTIME,
      Constants.DRIVE_PID_ROTATION_KACHIG_OFFTIME);
  }

  private double getRotationError(double value) {
    return ((sensorInput.getNavXHeading() + 540 - value) % 360) - 180;
  }

  public void setTarget(double target) {
    targetAngle = sensorInput.getNavXHeading() + target;
    setPoint = 0;
  }

  public void calculate() {
    super.calculate(getRotationError(targetAngle));
  }

  public double getError() {
    return -getRotationError(setPoint);
  }
}
