package org.usfirst.frc.team1619.robot2016.util.PID;

import org.usfirst.frc.team1619.robot2016.util.MathUtility;

public class PIDMinimumOutput extends GenericPID {
  private double minimumOutput;

  public PIDMinimumOutput() {
    this(new PIDValues(0, 0, 0));
  }

  public PIDMinimumOutput(PIDValues values) {
    super(values);
    minimumOutput = 0;
  }

  public void calculate(double currentValue) {
    double error = setPoint - currentValue;
    if (Math.abs(error) > deadBand) {
      outputValue =
        MathUtility.absMax(calcPID(error), minimumOutput, -minimumOutput);
    }
    else {
      outputValue = 0;
    }
  }

  public void setMinimumOutput(double minimumOutput) {
    this.minimumOutput = minimumOutput;
  }
}
