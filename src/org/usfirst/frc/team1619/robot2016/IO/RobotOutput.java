package org.usfirst.frc.team1619.robot2016.IO;

import org.usfirst.frc.team1619.robot2016.Constants;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.RobotDrive;

public class RobotOutput {
  //Singleton
  private static RobotOutput instance;
  static {
    instance = new RobotOutput();
  }

  public static RobotOutput getInstance() {
    return instance;
  }

  //Declare
  private CANTalon driveLeft1;
  private CANTalon driveLeft2;
  private CANTalon driveRight1;
  private CANTalon driveRight2;
  
  private CANTalon testMotor;
  private CANTalon dartMotor;
  
  private CANTalon intakeMotor;
  private CANTalon shootMotor;
  
  private RobotDrive drive;

  private RobotOutput() {
    //Init
    driveRight1 = new CANTalon(Constants.DRIVE_RIGHT_1_ID);
    driveRight2 = new CANTalon(Constants.DRIVE_RIGHT_2_ID);
    driveLeft1 = new CANTalon(Constants.DRIVE_LEFT_1_ID);
    driveLeft2 = new CANTalon(Constants.DRIVE_LEFT_2_ID);
    
    testMotor = new CANTalon(Constants.TEST_MOTOR_ID);
    dartMotor = new CANTalon(Constants.DART_MOTOR_ID);
    
    intakeMotor = new CANTalon(Constants.INTAKE_MOTOR_ID);
    shootMotor = new CANTalon(Constants.SHOOT_MOTOR_ID);

    driveRight1.setInverted(true);
    driveRight2.setInverted(true);
    driveLeft1.setInverted(true);
    driveLeft2.setInverted(true);

    dartMotor.setInverted(true);
    
    driveRight1.enableBrakeMode(true);
    driveRight2.enableBrakeMode(true);
    driveLeft1.enableBrakeMode(true);
    driveLeft2.enableBrakeMode(true);

    drive = new RobotDrive(driveLeft1, driveLeft2, driveRight1, driveRight2);
  }
  
  /**
   * Don't use this method. Use SensorInput instead.
   * @return Left drive encoder position
   */
  public int getDriveLeftEncPos() {
    return -driveLeft1.getEncPosition();
  }
  
  public int getDartPosition() {
    return dartMotor.getEncPosition();
  }

  /**
   * Don't use this method. Use SensorInput instead.
   * @param Left drive encoder new position
   */
  public void setDriveLeftPos(int position) {
    driveLeft1.setEncPosition(-position);
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
   * @param Right drive encoder new position
   */
  public void setDriveRightPos(int position) {
    driveRight1.setEncPosition(-position);
  }

  /**
   * Don't use this method. Use SensorInput instead.
   * @return Left drive encoder velocity
   */
  public int getDriveLeftEncVel() {
    return -driveLeft1.getEncVelocity();
  }

  /**
   * Don't use this method. Use SensorInput instead.
   * @return Right drive encoder velocity
   */
  public int getDriveRightEncVel() {
    return -driveRight1.getEncVelocity();
  }

  /**
   * Drive the robot using arcadeDrive
   * @param translation
   *          Forward/back values
   * @param rotation
   *          Rotation values
   */
  public void arcadeDrive(double translation, double rotation) {
    drive.arcadeDrive(-translation, rotation);
  }

  /**
   * Drive the robot using tankDrive
   * @param leftValue
   *          Left motor values
   * @param rightValue
   *          Right motor values
   */
  public void tankDrive(double leftValue, double rightValue) {
    drive.tankDrive(-leftValue, -rightValue);
  }
  
  /**
   * Sets the speed of the test motor.
   * @param speed
   *          Speed of test motor.
   */
  
  public void setTestMotor(double speed) {
	  testMotor.set(speed);
  }
  
  public void setDartMotor(double speed) {
    dartMotor.set(speed);
  }
  
  public void setIntakeMotor(double speed) {
    intakeMotor.set(speed);
  }
  
  public void setShootMotor(double speed) {
    shootMotor.set(speed);
  }

  public int getShootMotorSpeed() {
    return shootMotor.getAnalogInPosition();
  }
}
