package org.usfirst.frc.team1619.robot2016.framework;

import org.usfirst.frc.team1619.robot2016.IO.DriverInput;
import org.usfirst.frc.team1619.robot2016.IO.SensorInput;

public class RobotState {

  private static RobotState instance;

  private DriverInput driverInput;
  private SensorInput sensorInput;

  private int armZero;
  private boolean armZeroed;
  
  private boolean ballPresenceRearRisingEdge;
  private boolean ballPresenceRearLastValue;

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
    
    ballPresenceRearRisingEdge = false;
    ballPresenceRearLastValue = false;
  }

  public void update() {
    boolean ballDetectedRear = sensorInput.getBallPresenceSensorRear();
    if (!ballPresenceRearLastValue && ballDetectedRear) {
      ballPresenceRearRisingEdge = true;
    }
    else {
      ballPresenceRearRisingEdge = false;
    }
    ballPresenceRearLastValue = ballDetectedRear;
    
    sensorInput.setBallDetectedRear(ballDetectedRear);
    sensorInput.setBallDetectedFront(sensorInput.getBallPresenceSensorFront());
    sensorInput.setVisionRingLight(true);
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
    return ballPresenceRearRisingEdge;
  }
}
