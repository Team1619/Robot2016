package org.usfirst.frc.team1619.robot2016.IO;

import org.usfirst.frc.team1619.robot2016.Constants;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI;

/**
 * Sensor inputs from the robot
 * @author Tim
 */
public class SensorInput {
  private static SensorInput instance;
  static {
    instance = new SensorInput();
  }

  private RobotOutput robotOut;
  private AHRS navX;

  private SensorInput() {
    robotOut = RobotOutput.getInstance();
    navX = new AHRS(SPI.Port.kMXP);
  }

  public static SensorInput getInstance() {
    return instance;
  }

  //Encoders
  /**
   * Left drive motor position in inches
   * @return Left drive encoder position
   */
  public int getDriveLeftEncoderPosition() {
    return robotOut.getDriveLeftEncPos() / Constants.DRIVE_ENC_TICKS_PER_INCH;
  }

  /**
   * Set the current encoder position.
   * This is usually used for zeroing the encoder.
   * @param position Left encoder new position
   */
  public void setDriveLeftPos(int position) {
    robotOut.setDriveLeftPos(position);
  }

  /**
   * Right drive motor position in inches
   * @return Right drive encoder position
   */
  public int getDriveRightEncoderPosition() {
    return robotOut.getDriveRightEncPos() / Constants.DRIVE_ENC_TICKS_PER_INCH;
  }

  /**
   * Set the current encoder position.
   * This is usually used for zeroing the encoder.
   * @param position Right encoder new position
   */
  public void setDriveRightPos(int position) {
    robotOut.setDriveRightPos(position);
  }

  /**
   * Left drive motor velocity
   * @return Left drive encoder velocity
   */
  public int getDriveLeftEncoderVelocity() {
    return robotOut.getDriveLeftEncVel();
  }

  /**
   * Left drive motor velocity
   * @return Left drive encoder velocity
   */
  public int getDriveRightEncoderVelocity() {
    return robotOut.getDriveRightEncVel();
  }

  //NavX
  /**
   * NavX fused heading
   * @return Current NavX heading between -180 and 180
   */
  public double getNavXHeading() {
    return ((navX.getFusedHeading() + 180) % 360) - 180;
  }

  /**
   * Set the current NavX heading to 0
   */
  public void resetNavXHeading() {
    navX.reset();
  }
}
