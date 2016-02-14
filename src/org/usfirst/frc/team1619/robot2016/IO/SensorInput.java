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

  private int driveLeftEncPos;
  private int driveRightEncPos;

  private SensorInput() {
    robotOut = RobotOutput.getInstance();
    navX = new AHRS(SPI.Port.kMXP);

    driveLeftEncPos = 0;
    driveRightEncPos = 0;
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
    return (robotOut.getDriveLeftEncPos() - driveLeftEncPos) / Constants.DRIVE_ENC_TICKS_PER_INCH;
  }

  /**
   * Reset the current encoder position to zero.
   * @param position Left encoder new position
   */
  public void resetDriveLeftPos() {
    driveLeftEncPos = robotOut.getDriveLeftEncPos();
  }

  /**
   * Right drive motor position in inches
   * @return Right drive encoder position
   */
  public int getDriveRightEncoderPosition() {
    return (robotOut.getDriveRightEncPos() - driveRightEncPos) / Constants.DRIVE_ENC_TICKS_PER_INCH;
  }

  /**
   * Reset the current encoder position to zero.
   * @param position Right encoder new position
   */
  public void resetDriveRightPos() {
    driveRightEncPos = robotOut.getDriveRightEncPos();
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

  public double getNavXHeadingRaw() {
    return navX.getFusedHeading();
  }

  public double getNavXRotationSpeed() {
    return navX.getRate();
  }

  /**
   * Set the current NavX heading to 0
   */
  public void resetNavXHeading() {
    navX.zeroYaw();
  }
}
