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
    setDeadBand(Constants.DRIVE_PID_TRANSLATION_DEADZONE);
    setMinimumOutput(Constants.DRIVE_TRANSLATION_MINIMUM_OUTPUT);
  }

  private double getEncoderPosition() {
    return (sensorInput.getDriveRightEncoderPosition()
      + sensorInput.getDriveLeftEncoderPosition()) / 2;
  }

  public void calculate() {
    super.calculate(getEncoderPosition());
  }

  public double getError() {
    return setPoint - getEncoderPosition();
  }

  public void setTarget(double target) {
    super.setTarget(target + getEncoderPosition());
  }
}