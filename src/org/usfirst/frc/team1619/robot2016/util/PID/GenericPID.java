package org.usfirst.frc.team1619.robot2016.util.PID;

import org.usfirst.frc.team1619.robot2016.util.MathUtility;

public class GenericPID {
  protected double kP;
  protected double kI;
  protected double kD;

  protected double outputValue;
  protected double setPoint;
  protected double prevError;
  protected double integral;
  protected double deadBand;

  protected double integralRange;
  protected double integralMax;

  public GenericPID() {
    this(0.0, 0.0, 0.0);
  }

  public GenericPID(PIDValues values) {
    this.kP = values.p;
    this.kI = values.i;
    this.kD = values.d;

    outputValue = 0;
    setPoint = 0;
    prevError = 0;
    integral = 0;
    deadBand = 0;
  }

  public GenericPID(double pValue, double iValue, double dValue) {
    kP = pValue;
    kI = iValue;
    kD = dValue;

    outputValue = 0;
    setPoint = 0;
    prevError = 0;
    integral = 0;

    integralRange = 0;
    integralMax = 0;
  }

  public void setValues(PIDValues values) {
    this.kP = values.p;
    this.kI = values.i;
    this.kD = values.d;
  }

  public void setValues(double pValue, double iValue, double dValue) {
    kP = pValue;
    kI = iValue;
    kD = dValue;
  }

  public void setTarget(double target) {
    setPoint = target;
  }

  public void calculate(double currentValue) {
    double error = setPoint - currentValue;
    if (Math.abs(error) > deadBand) {
      outputValue = calcPID(setPoint - currentValue);
    }
    else {
      outputValue = 0;
    }
  }

  public double get() {
    return outputValue;
  }

  public double getSetPoint() {
    return setPoint;
  }

  public void resetIntegral() {
    integral = 0;
  }

  /**
   * Reset the PID loop to initial conditions
   */
  public void reset() {
    outputValue = 0;
    setPoint = 0;
    prevError = 0;
    integral = 0;
  }

  /**
   * Integral term will only be calculated when -range < error < range
   * @param range Range that the I term is calculated within
   */
  public void setIRange(double range) {
    integralRange = range;
  }

  /**
   * Integral will be constrained to values between -max and max
   * @param max Maximum integral value
   */
  public void setIMax(double max) {
    integralMax = max;
  }

  public void setDeadBand(double band) {
    deadBand = band;
  }

  protected double calcPID(double error) {
    double currentError = error;
    double pCalc;
    double iCalc;
    double dCalc;
    double output;

    // P term
    pCalc = currentError * this.kP;

    // I term
    //If there is an integral range, only add the integral term within bounds
    if(integralRange != 0) {
      if(currentError <= integralRange && currentError >= -integralRange) {
        integral += currentError;
      }
      else {
        integral = 0;
      }
    }
    else {
      integral += currentError;
    }
    //If there is a integral max, constrain the final result
    if(integralMax != 0) {
      integral = MathUtility.constrain(integral, integralMax, -integralMax);
    }
    iCalc = integral * kI;

    // D term
    dCalc = (currentError - prevError) * this.kD;

    // output
    output = pCalc + iCalc + dCalc;
    prevError = currentError;
    return output;
  }
}
