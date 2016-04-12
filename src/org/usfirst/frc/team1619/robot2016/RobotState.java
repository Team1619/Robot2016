package org.usfirst.frc.team1619.robot2016;

import org.usfirst.frc.team1619.robot2016.IO.DriverInput;
import org.usfirst.frc.team1619.robot2016.IO.SensorInput;
import org.usfirst.frc.team1619.robot2016.IO.SocketTables.SmashBoard;

public class RobotState {

  private static RobotState instance;

  private DriverInput driverInput;
  private SensorInput sensorInput;

  private int armZero;
  private boolean armZeroed;

  private boolean intakeStalled;

  private boolean ballPresenceRearRisingEdge;
  private boolean ballPresenceRearLastValue;

  private double shootOffset;
  private boolean shootOffsetIncreaseLastValue;
  private boolean shootOffsetDecreaseLastValue;
  
  private boolean hasExtendedScaler;

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

    shootOffset = Constants.SHOOTER_INITIAL_OFFSET_ANGLE;
    shootOffsetIncreaseLastValue = false;
    shootOffsetDecreaseLastValue = false;
    
    hasExtendedScaler = false;
  }

  public void initialze() {
    intakeStalled = false;

    shootOffset = Constants.SHOOTER_INITIAL_OFFSET_ANGLE;
    shootOffsetIncreaseLastValue = false;
    shootOffsetDecreaseLastValue = false;
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

    boolean offsetIncrease = driverInput
      .getDriverButton(Constants.DRIVER_BUTTON_SHOOT_OFFSET_INCREASE);
    boolean offsetDecrease = driverInput
      .getDriverButton(Constants.DRIVER_BUTTON_SHOOT_OFFSET_DECREASE);
    if (!shootOffsetIncreaseLastValue && offsetIncrease) {
      shootOffset += Constants.SHOOTER_OFFSET_INCREMENT;
    }
    if (!shootOffsetDecreaseLastValue && offsetDecrease) {
      shootOffset -= Constants.SHOOTER_OFFSET_INCREMENT;
    }
    if (driverInput
      .getDriverButton(Constants.DRIVER_BUTTON_SHOOT_OFFSET_RESET)) {
      shootOffset = 0;
    }
    shootOffsetIncreaseLastValue = offsetIncrease;
    shootOffsetDecreaseLastValue = offsetDecrease;

    SmashBoard.getInstance().setShootOffset(shootOffset);
  }

  public double getShootAlignOffset() {
    return shootOffset;
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

  public void setIntakeStalled(boolean stalled) {
    intakeStalled = stalled;
  }

  public boolean getIntakeStalled() {
    return intakeStalled;
  }
  
  public void extendedScaler() {
    hasExtendedScaler = true;
  }
  
  public boolean getHasExtendedScaler() {
    return hasExtendedScaler;
  }
}
