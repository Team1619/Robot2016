package org.usfirst.frc.team1619.robot2016.subsystems;

import org.usfirst.frc.team1619.robot2016.Constants;
import org.usfirst.frc.team1619.robot2016.IO.SensorInput;
import org.usfirst.frc.team1619.robot2016.util.GenericPID;

public class ArmPID {

  private static ArmPID instance;

  private SensorInput sensorInput;

  private GenericPID upPID;
  private GenericPID downPID;

  private double target;

  static {
    instance = new ArmPID();
  }

  public static ArmPID getInstance() {
    return instance;
  }

  private ArmPID() {
    sensorInput = SensorInput.getInstance();

    upPID = new GenericPID();
    downPID = new GenericPID();

    upPID.setValues(Constants.ARM_PID_UP);
    downPID.setValues(Constants.ARM_PID_DOWN);
  }

  public void setTarget(double newTarget) {
    target = newTarget;
    upPID.setTarget(target);
    downPID.setTarget(target);
  }

  public double get() {
    double position = sensorInput.getDartPosition();

    if (position < target) {
      upPID.calculate(position);
      return upPID.get();
    }
    else {
      downPID.calculate(position);
      return downPID.get();
    }
  }

  public void resetIntegral() {
    upPID.resetIntegral();
    downPID.resetIntegral();
  }

  public void reset() {
    upPID.reset();
    downPID.reset();
  }
  
}
