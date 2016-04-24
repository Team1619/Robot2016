package org.usfirst.frc.team1619.robot2016;

import org.usfirst.frc.team1619.robot2016.IO.DriverInput;
import org.usfirst.frc.team1619.robot2016.IO.SensorInput;
import org.usfirst.frc.team1619.robot2016.IO.SocketTables.SmashBoard;

public class RobotState {

  private static RobotState instance;

  private SmashBoard smashBoard;
  private DriverInput driverInput;
  private SensorInput sensorInput;

  private int armZero;
  private boolean armZeroed;

  private boolean armToDefault;

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
    armZero = 0;
    armZeroed = false;

    armToDefault = false;

    ballPresenceRearRisingEdge = false;
    ballPresenceRearLastValue = false;

    shootOffset = Constants.SHOOTER_INITIAL_OFFSET_ANGLE;
    shootOffsetIncreaseLastValue = false;
    shootOffsetDecreaseLastValue = false;

    hasExtendedScaler = false;

    smashBoard = SmashBoard.getInstance();
    driverInput = DriverInput.getInstance();
    sensorInput = SensorInput.getInstance();
  }

  public void initialize() {
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

    sensorInput.setVisionRingLight(
      sensorInput.getDartPosition() > Constants.ARM_POSITION_VISION - 1.5);

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

    smashBoard.setShootOffset(shootOffset);

    if (smashBoard.getGoodContourFound()) {
      double distance = smashBoard.getDistance();

      if (distance < Constants.SHOT_BATTER_CUT_OFF) {
        smashBoard.setShotRange(1);
      }
      else if (distance < Constants.SHOT_MID_RANGE_CUT_OFF) {
        smashBoard.setShotRange(2);
      }
      else if (distance < Constants.SHOT_LONG_RANGE_CUT_OFF) {
        smashBoard.setShotRange(3);
      }
      else {
        smashBoard.setShotRange(4);
      }
    }
    else {
      smashBoard.setShotRange(0);
    }
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

  public boolean getArmToDefault() {
    return armToDefault;
  }

  public void setArmToDefault(boolean armToDefault) {
    this.armToDefault = armToDefault;
  }

  public boolean getBallPresenceRisingEdge() {
    return ballPresenceRearRisingEdge;
  }

  public void extendedScaler() {
    hasExtendedScaler = true;
  }

  public boolean getHasExtendedScaler() {
    return hasExtendedScaler;
  }
}
