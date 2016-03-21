package org.usfirst.frc.team1619.robot2016.util.PID;

import org.usfirst.frc.team1619.robot2016.Constants;
import org.usfirst.frc.team1619.robot2016.IO.SensorInput;

public class DriveTranslationPID extends PIDMinimumOutput {
  SensorInput sensorInput;

  public DriveTranslationPID() {
    super();
    sensorInput = SensorInput.getInstance();

    setValues(Constants.DRIVE_PID_TRANSLATION);
    setIRange(Constants.DRIVE_PID_TRANSLATION_IRANGE);
    setIMax(Constants.DRIVE_PID_TRANSLATION_IMAX);
    setDeadBand(Constants.DRIVE_PID_TRANSLATION_DEADZONE);
    setMinimumOutput(Constants.DRIVE_PID_TRANSLATION_MINIMUM_OUTPUT);
  }

  private double getEncoderPosition() {
    return (sensorInput.getDriveRightEncoderPosition()
      + sensorInput.getDriveLeftEncoderPosition()) / 2;
  }

  public void calculate() {
    super.calculate(getEncoderPosition());
//    SmashBoard.getInstance().setAngleError(getError());
  }

  public double getError() {
    return setPoint - getEncoderPosition();
  }

  public void setTarget(double target) {
    super.setTarget(target + getEncoderPosition());
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