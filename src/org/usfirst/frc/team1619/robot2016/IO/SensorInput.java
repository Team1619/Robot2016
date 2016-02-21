package org.usfirst.frc.team1619.robot2016.IO;

import org.usfirst.frc.team1619.robot2016.Constants;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DigitalInput;
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

  private RobotOutput robotOut;
  private AHRS navX;
  private DigitalInput upperHallEffect;
  private DigitalInput lowerHallEffect;

  private SensorInput() {
    robotOut = RobotOutput.getInstance();
    navX = new AHRS(SPI.Port.kMXP);
    upperHallEffect = new DigitalInput(Constants.UPPER_HALLEFFECT_ID);
    lowerHallEffect = new DigitalInput(Constants.LOWER_HALLEFFECT_ID);
  }

  // Encoders
  public int getShooterEncoderVelocity() {
    return robotOut.getShooterVelocity();
  }

  /**
   * Left drive motor position in inches
   * 
   * @return Left drive encoder position
   */
  public double getDriveLeftEncoderPosition() {
    return robotOut.getDriveLeftEncPos() / Constants.DRIVE_ENC_TICKS_PER_INCH;
  }

  /**
   * Set the current encoder position. This is usually used for zeroing the
   * encoder.
   * 
   * @param position
   *          Left encoder new position
   */
  public void setDriveLeftPos(int position) {
    robotOut.setDriveLeftPos(position);
  }

  /**
   * Right drive motor position in inches
   * 
   * @return Right drive encoder position
   */
  public double getDriveRightEncoderPosition() {
    return robotOut.getDriveRightEncPos() / Constants.DRIVE_ENC_TICKS_PER_INCH;
  }

  /**
   * Set the current encoder position. This is usually used for zeroing the
   * encoder.
   * 
   * @param position
   *          Right encoder new position
   */
  public void setDriveRightPos(int position) {
    robotOut.setDriveRightPos(position);
  }

  /**
   * Gets the dart position.
   * 
   * @return Travel in inches.
   */
  public double getDartPosition() {
    return robotOut.getDartPosition() / Constants.DART_ENC_TICKS_PER_INCH;
  }

  /**
   * Left drive motor velocity
   * 
   * @return Left drive encoder velocity
   */
  public int getDriveLeftEncoderVelocity() {
    return robotOut.getDriveLeftEncVel();
  }

  /**
   * Left drive motor velocity
   * 
   * @return Left drive encoder velocity
   */
  public int getDriveRightEncoderVelocity() {
    return robotOut.getDriveRightEncVel();
  }

  // NavX
  /**
   * NavX fused heading
   * 
   * @return Current NavX heading
   */
  public double getNavXHeading() {
    return navX.getFusedHeading();
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

}
