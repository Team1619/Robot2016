package org.usfirst.frc.team1619.robot2016.util.PID;

import org.usfirst.frc.team1619.robot2016.Constants;
import org.usfirst.frc.team1619.robot2016.IO.SensorInput;
import org.usfirst.frc.team1619.robot2016.util.MathUtility;

public class DriveRotationPID extends GenericPID {
  SensorInput sensorInput;
  double targetAngle;

  public DriveRotationPID() {
    super();
    sensorInput = SensorInput.getInstance();
    targetAngle = 0;

    setValues(Constants.DRIVE_PID_ROTATION);
    setIRange(Constants.DRIVE_PID_ROTATION_IRANGE);
    setIMax(Constants.DRIVE_PID_ROTATION_IMAX);
    setDeadBand(Constants.DRIVE_PID_ROTATION_DEADZONE);
  }

  private double getRotationError(double value) {
    return ((sensorInput.getNavXHeading() + 540 - value) % 360) - 180;
  }

  @Override
  public void setTarget(double target) {
    targetAngle = sensorInput.getNavXHeading() + target;
    setPoint = 0;
  }

  public void calculate() {
    super.calculate(getRotationError(targetAngle));
//    SmashBoard.getInstance().setAngleError(getError());
  }

  @Override
  public double get() {
    return MathUtility.constrain(super.get(),
      Constants.AUTO_DRIVE_ROTATION_MAX_OUTPUT,
      -Constants.AUTO_DRIVE_ROTATION_MAX_OUTPUT);
  }

  public double getError() {
    return -getRotationError(targetAngle);
  }

  @Override
  public double iCalc(double error) {
    double iCalc = super.iCalc(error);
    if (Math.abs(error) < deadBand || Math.signum(iCalc) != Math.signum(error)) {
      resetIntegral();
      return 0;
    }
    else {
      return iCalc;
    }
  }
}
