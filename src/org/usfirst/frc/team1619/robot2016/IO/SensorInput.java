package org.usfirst.frc.team1619.robot2016.IO;

import org.usfirst.frc.team1619.robot2016.Constants;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.SPI;

/**
 * Sensor inputs from the robot
 */
public class SensorInput {

  private static SensorInput instance;

  static {
    try {
      instance = new SensorInput();
    }
    catch (Throwable e) {
      e.printStackTrace();
      throw e;
    }
  }

  public static SensorInput getInstance() {
    return instance;
  }

  private RobotOutput robotOutput;
  private AHRS navX;
  private DigitalInput upperHallEffect;
  private DigitalInput lowerHallEffect;
  private DigitalInput ballPresenceSensorRear;
  private DigitalInput ballPresenceSensorFront;
  private Relay ballDetectedRear;
  private Relay ballDetectedFront;
  private Relay visionRingLight;

  private DriverStation driverStation;

  private SensorInput() {
    navX = new AHRS(SPI.Port.kMXP);
    upperHallEffect = new DigitalInput(Constants.ARM_UPPER_HALLEFFECT_ID);
    lowerHallEffect = new DigitalInput(Constants.ARM_LOWER_HALLEFFECT_ID);
    ballPresenceSensorRear =
      new DigitalInput(Constants.BALL_PRESENCE_SENSOR_REAR_ID);
    ballPresenceSensorFront =
      new DigitalInput(Constants.BALL_PRESENCE_SENSOR_FRONT_ID);
    ballDetectedRear = new Relay(Constants.RELAY_BALL_DETECTED_REAR_ID);
    ballDetectedFront = new Relay(Constants.RELAY_BALL_DETECTED_FRONT_ID);
    visionRingLight = new Relay(Constants.RELAY_VISION_RING_LIGHT_ID);

    driverStation = DriverStation.getInstance();
  }

  public void initialize() {
    robotOutput = RobotOutput.getInstance();
  }

  public double getMatchTimeRemaining() {
    return driverStation.getMatchTime();
  }

  // Encoders
  public int getShooterEncoderVelocity() {
    return robotOutput.getShooterVelocity();
  }

  /**
   * Left drive motor position in inches
   * 
   * @return Left drive encoder position
   */
  public double getDriveLeftEncoderPosition() {
    return robotOutput.getDriveLeftEncPos()
      / Constants.DRIVE_ENC_TICKS_PER_INCH;
  }

  /**
   * Set the current encoder position. This is usually used for zeroing the
   * encoder.
   * 
   * @param position
   *          Left encoder new position
   */
  public void setDriveLeftPos(int position) {
    robotOutput.setDriveLeftPos(position);
  }

  /**
   * Right drive motor position in inches
   * 
   * @return Right drive encoder position
   */
  public double getDriveRightEncoderPosition() {
    return robotOutput.getDriveRightEncPos()
      / Constants.DRIVE_ENC_TICKS_PER_INCH;
  }

  /**
   * Set the current encoder position. This is usually used for zeroing the
   * encoder.
   * 
   * @param position
   *          Right encoder new position
   */
  public void setDriveRightPos(int position) {
    robotOutput.setDriveRightPos(position);
  }

  public double getRotationVelocity() {
    return navX.getRate();
  }

  /**
   * Gets the dart position.
   * 
   * @return Travel in inches.
   */
  public double getDartPosition() {
    return robotOutput.getDartPosition() / Constants.DART_ENC_TICKS_PER_INCH;
  }

  public double getDartVelocity() {
    return robotOutput.getDartVelocity() / Constants.DART_ENC_TICKS_PER_INCH;
  }

  public void zeroDart() {
    robotOutput.zeroDart();
  }

  /**
   * Left drive motor velocity
   * 
   * @return Left drive encoder velocity
   */
  public int getDriveLeftEncoderVelocity() {
    return robotOutput.getDriveLeftEncVel();
  }

  /**
   * Left drive motor velocity
   * 
   * @return Left drive encoder velocity
   */
  public int getDriveRightEncoderVelocity() {
    return robotOutput.getDriveRightEncVel();
  }

  // NavX
  /**
   * NavX fused heading
   * 
   * @return Current NavX heading
   */
  public double getNavXHeading() {
    return ((navX.getFusedHeading() + 180) % 360) - 180;
  }

  public double getNavXRotationSpeed() {
    return navX.getRate();
  }

  public float getNavXPitch() {
    return navX.getPitch();
  }

  public float getNavXRoll() {
    return navX.getRoll();
  }

  public float getNavXAccelX() {
    return navX.getRawAccelX();
  }

  public float getNavXAccelY() {
    return navX.getRawAccelY();
  }

  /**
   * Set the current NavX heading to 0
   */
  public void resetNavXHeading() {
    navX.reset();
  }

  /**
   * Checks upper limit switch.
   * 
   * @return True if tripped.
   */
  public boolean getUpperHallEffect() {
    // Sensor is low true
    return !upperHallEffect.get();
  }

  /**
   * Checks lower limit switch.
   * 
   * @return True if tripped.
   */
  public boolean getLowerHallEffect() {
    // Sensor is low true
    return !lowerHallEffect.get();
  }

  public boolean getBallPresenceSensorRear() {
    return !ballPresenceSensorRear.get();
  }

  public boolean getBallPresenceSensorFront() {
    return !ballPresenceSensorFront.get();
  }

  public void setRearLEDs(boolean isOn) {
    ballDetectedRear.set(isOn ? Relay.Value.kForward : Relay.Value.kOff);
  }

  public void setFrontLEDs(boolean isOn) {
    ballDetectedFront.set(isOn ? Relay.Value.kForward : Relay.Value.kOff);
  }

  public void setVisionRingLight(boolean isOn) {
    visionRingLight.set(isOn ? Relay.Value.kForward : Relay.Value.kOff);
  }
}
