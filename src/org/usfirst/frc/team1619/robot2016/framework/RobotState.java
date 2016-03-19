package org.usfirst.frc.team1619.robot2016.framework;

import org.usfirst.frc.team1619.robot2016.IO.DriverInput;
import org.usfirst.frc.team1619.robot2016.IO.SensorInput;

public class RobotState {

  private static RobotState instance;

  private DriverInput driverInput;
  private SensorInput sensorInput;

  private int armZero;
  private boolean armZeroed;
  
  private boolean ballPresenceRisingEdge;
  private boolean ballPresenceLastValue;

  static {
    instance = new RobotState();
  }

  public static RobotState getInstance() {
    return instance;
  }

  private RobotState() {
    driverInput = DriverInput.getInstance();
    sensorInput = SensorInput.getInstance();

    armZero = 0;
    armZeroed = false;
    
    ballPresenceRisingEdge = false;
    ballPresenceLastValue = false;
  }

  public void update() {
    boolean ballDetected = sensorInput.getBallPresenceSensor();
    if (!ballPresenceLastValue && ballDetected) {
      ballPresenceRisingEdge = true;
    }
    else {
      ballPresenceRisingEdge = false;
    }
    ballPresenceLastValue = ballDetected;
  }

  public int getArmZero() {
    return armZero;
  }

  public void setArmZero(int position) {
    armZero = position;
    armZeroed = true;
  }

  public boolean getArmZeroed() {
    return armZeroed;
  }
  
  public boolean getBallPresenceRisingEdge() {
    return ballPresenceRisingEdge;
  }
}
