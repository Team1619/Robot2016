package org.usfirst.frc.team1619.robot2016.util.PID;

import org.usfirst.frc.team1619.robot2016.Constants;
import org.usfirst.frc.team1619.robot2016.IO.SensorInput;
import org.usfirst.frc.team1619.robot2016.IO.SocketTables.SmashBoard;

public class ArmPID extends PIDMinimumOutput {

  private SensorInput sensorInput;

  public ArmPID() {
    super();

    sensorInput = SensorInput.getInstance();

    setValues(Constants.ARM_PID);
    setIRange(Constants.ARM_PID_IRANGE);
    setIMax(Constants.ARM_PID_IMAX);
    setDeadBand(Constants.ARM_PID_DEADZONE);
    setMinimumOutput(Constants.ARM_PID_MINIMUM);
  }

  private double getEncoderPosition() {
    return sensorInput.getDartPosition();
  }

  public void calculate() {
    super.calculate(getEncoderPosition());
    SmashBoard.getInstance().setAngleError(getError());
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
