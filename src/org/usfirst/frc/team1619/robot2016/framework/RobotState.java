package org.usfirst.frc.team1619.robot2016.framework;

import org.usfirst.frc.team1619.robot2016.Constants;
import org.usfirst.frc.team1619.robot2016.IO.DriverInput;
import org.usfirst.frc.team1619.robot2016.IO.SensorInput;

public class RobotState {

  private static RobotState instance;

  private DriverInput driverInput;
  private SensorInput sensorInput;

  private TeleopState teleopState;
  
  private double armTarget;
  private double rotateTarget;

  static {
    instance = new RobotState();
  }

  public static RobotState getInstance() {
    return instance;
  }

  private RobotState() {
    driverInput = DriverInput.getInstance();
    sensorInput = SensorInput.getInstance();

    teleopState = new TeleopState();

    armTarget = 0.0;
  }

  public void update() {
    if (driverInput.getDriverButton(Constants.DRIVER_BUTTON_DRIVE_PID_RESET)) {
      rotateTarget = sensorInput.getNavXHeading();
    }
  }

  public void setArmTarget(double newArmTarget) {
    armTarget = newArmTarget;
  }

  public double getArmTarget() {
    return armTarget;
  }

  public double getRotateTarget() {
    return rotateTarget;
  }
  
}