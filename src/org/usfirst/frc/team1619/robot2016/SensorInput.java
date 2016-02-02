package org.usfirst.frc.team1619.robot2016;

/**
 * Sensor inputs from the robot
 * 
 * @author Tim
 *
 */
public class SensorInput {
  private static SensorInput instance;

  private RobotOutput robotOut;

  private SensorInput() {
    robotOut = RobotOutput.getInstance();
  }

  public static SensorInput getInstance() {
    if (instance == null) {
      instance = new SensorInput();
    }
    return instance;
  }

  public int getDriveLeftEncoderPosition() {
    return robotOut.getDriveLeftEncPos();
  }

  public int getDriveRightEncoderPosition() {
    return robotOut.getDriveRightEncPos();
  }

  public int getDriveLeftEncoderVelocity() {
    return robotOut.getDriveLeftEncVel();
  }

  public int getDriveRightEncoderVelocity() {
    return robotOut.getDriveRightEncVel();
  }
}
