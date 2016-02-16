package org.usfirst.frc.team1619.robot2016.util;

import org.usfirst.frc.team1619.robot2016.PIDValues;

public class GenericPID {
  private double kP;
  private double kI;
  private double kD;

  private double outputValue;
  private double setPoint;
  private double prevError;
  private double integral;

  public GenericPID() {
    this(new PIDValues(0.0, 0.0, 0.0));
  }

  public GenericPID(PIDValues values) {
    this.kP = values.p;
    this.kI = values.i;
    this.kD = values.d;

    outputValue = 0;
    setPoint = 0;
    prevError = 0;
    integral = 0;
  }

  public void setValues(PIDValues values) {
    this.kP = values.p;
    this.kI = values.i;
    this.kD = values.d;
  }

  public void setTarget(double target) {
    this.setPoint = target;
  }

  public void calculate(double currentValue) {
    outputValue = calcPID(setPoint - currentValue);
  }

  public double get() {
    return outputValue;
  }

  public void resetError() {
    this.integral = 0;
  }

  private double calcPID(double error) {
    double currentError = error;
    double pCalc;
    double iCalc;
    double dCalc;
    double output;

    // P term
    pCalc = currentError * this.kP;

    // I term
    integral += currentError;
    iCalc = integral * this.kI;

    // D term
    dCalc = (currentError - prevError) * this.kD;

    // output
    output = pCalc + iCalc + dCalc;
    this.prevError = currentError;
    return output;
  }
}
