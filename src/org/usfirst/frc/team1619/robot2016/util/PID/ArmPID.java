package org.usfirst.frc.team1619.robot2016.util.PID;

import org.usfirst.frc.team1619.robot2016.Constants;
import org.usfirst.frc.team1619.robot2016.IO.SensorInput;

public class ArmPID extends PIDMinimumOutput {

  private SensorInput sensorInput;

  public ArmPID() {
    super();

    sensorInput = SensorInput.getInstance();

    setValues(Constants.ARM_PID);
    setDeadBand(Constants.ARM_PID_DEADZONE);
    setMinimumOutput(Constants.ARM_PID_MINIMUM);
  }

  private double getEncoderPosition() {
    return sensorInput.getDartPosition();
  }

  public void calculate() {

    super.calculate(getEncoderPosition());
  }

  public double getError() {
    return setPoint - getEncoderPosition();
  }

  @Override
  public double iCalc(double error) {
    double iCalc = super.iCalc(error);
    if (Math.abs(error) < deadBand) {
      resetIntegral();
      return 0;
    }
    else {
      return iCalc;
    }
  }

}
