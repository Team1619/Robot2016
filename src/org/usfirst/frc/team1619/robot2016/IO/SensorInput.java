package org.usfirst.frc.team1619.robot2016.IO;

import org.usfirst.frc.team1619.robot2016.Constants;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI;

import edu.wpi.first.wpilibj.DigitalInput;

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
  private DigitalInput upperHallEffect;

  private SensorInput() {
    robotOut = RobotOutput.getInstance();
    navX = new AHRS(SPI.Port.kMXP);
    upperHallEffect = new DigitalInput(Constants.UPPER_HALLEFFECT_ID);
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
   * Right drive motor position in inches
   * @return Right drive encoder position
   */
  public int getDriveRightEncoderPosition() {
    return robotOut.getDriveRightEncPos();
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
   * @return Current NavX heading
   */
  public double getNavXHeading() {
    return navX.getFusedHeading();
  }

  /**
   * Set the current NavX heading to 0
   */
  public void resetNavXHeading() {
    navX.reset();
  }
  
  public boolean getUpperHallEffect() {
    return upperHallEffect.get();
  }
}
