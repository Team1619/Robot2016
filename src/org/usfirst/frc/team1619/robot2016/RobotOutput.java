package org.usfirst.frc.team1619.robot2016;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.RobotDrive;

public class RobotOutput {
  private static RobotOutput instance;

  private CANTalon driveLeft1;
  private CANTalon driveLeft2;
  private CANTalon driveRight1;
  private CANTalon driveRight2;
  
  private RobotDrive drive;

  private RobotOutput() {
    driveRight1 = new CANTalon(Constants.DRIVE_RIGHT_1_ID);
    driveRight2 = new CANTalon(Constants.DRIVE_RIGHT_2_ID);
    driveLeft1 = new CANTalon(Constants.DRIVE_LEFT_1_ID);
    driveLeft2 = new CANTalon(Constants.DRIVE_LEFT_2_ID);
    
    driveRight1.setInverted(true);
    driveRight2.setInverted(true);
    driveLeft1.setInverted(true);
    driveLeft2.setInverted(true);
    
    drive = new RobotDrive(driveLeft1, driveLeft2, driveRight1, driveRight2);
  }

  public static RobotOutput getInstance() {
    if(instance == null) {
      instance = new RobotOutput();
    }
    return instance;
  }

  /**
   * Don't use this method. Use SensorInput instead.
   * @return Left drive encoder position
   */
  public int getDriveLeftEncPos() {
    return driveLeft1.getEncPosition();
  }

  /**
   * Don't use this method. Use SensorInput instead.
   * @return Right drive encoder position
   */
  public int getDriveRightEncPos() {
    return driveRight1.getEncPosition();
  }

  /**
   * Don't use this method. Use SensorInput instead.
   * @return Left drive encoder velocity
   */
  public int getDriveLeftEncVel() {
    return driveLeft1.getEncVelocity();
  }

  /**
   * Don't use this method. Use SensorInput instead.
   * @return Right drive encoder velocity
   */
  public int getDriveRightEncVel() {
    return driveRight1.getEncVelocity();
  }

  /**
   * Drive the robot using arcadeDrive
   * @param translation Forward/back values
   * @param rotation Rotation values
   */
  public void drive(double translation, double rotation) {
    drive.arcadeDrive(translation, rotation);
  }
}
