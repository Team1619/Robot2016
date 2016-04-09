package org.usfirst.frc.team1619.robot2016.IO;

import org.usfirst.frc.team1619.robot2016.Constants;
import org.usfirst.frc.team1619.robot2016.RobotState;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Servo;

public class RobotOutput {

  // Singleton
  private static RobotOutput instance;

  static {
    try {
      instance = new RobotOutput();
    }
    catch (Throwable exception) {
      exception.printStackTrace();
      throw exception;
    }
  }

  public static RobotOutput getInstance() {
    return instance;
  }

  // Declare
  private RobotState robotState;
  private SensorInput sensorInput;

  private CANTalon driveLeft1;
  private CANTalon driveLeft2;
  private CANTalon driveRight1;
  private CANTalon driveRight2;

  private CANTalon dartMotor;

  private CANTalon intakeMotor;
  private CANTalon shooterMotor;
  private CANTalon shooterMotor2;

  private CANTalon scalerMotor;

  private Servo extensionServo;
  
  private RobotDrive drive;

  private RobotOutput() {
    // Initialize
    driveRight1 = new CANTalon(Constants.DRIVE_RIGHT_1_ID);
    driveRight2 = new CANTalon(Constants.DRIVE_RIGHT_2_ID);
    driveLeft1 = new CANTalon(Constants.DRIVE_LEFT_1_ID);
    driveLeft2 = new CANTalon(Constants.DRIVE_LEFT_2_ID);

    // Positive - dart extend; Negative - dart retract
    dartMotor = new CANTalon(Constants.DART_MOTOR_ID);

    intakeMotor = new CANTalon(Constants.INTAKE_MOTOR_ID);
    shooterMotor = new CANTalon(Constants.SHOOTER_MOTOR_ID);
    shooterMotor2 = new CANTalon(Constants.SHOOTER_MOTOR_2_ID);
    
    scalerMotor = new CANTalon(Constants.SCALER_MOTOR_ID);

    driveRight1.setInverted(true);
    driveRight2.setInverted(true);
    driveLeft1.setInverted(true);
    driveLeft2.setInverted(true);

    intakeMotor.setInverted(true);
    shooterMotor.setInverted(false);
    shooterMotor2.setInverted(false);

    driveRight1.enableBrakeMode(true);
    driveRight2.enableBrakeMode(true);
    driveLeft1.enableBrakeMode(true);
    driveLeft2.enableBrakeMode(true);

    scalerMotor.enableBrakeMode(true);
    
    extensionServo = new Servo(0);

    drive = new RobotDrive(driveLeft1, driveLeft2, driveRight1, driveRight2);
  }

  public void initialize() {
    robotState = RobotState.getInstance();
    sensorInput = SensorInput.getInstance();
  }

  /**
   * Don't use this method. Use SensorInput instead.
   * 
   * @return Left drive encoder position
   */
  public int getDriveLeftEncPos() {
    return -driveLeft1.getEncPosition();
  }

  /**
   * Gets the position of the dart.
   * 
   * @return Dart encoder position.
   */
  public int getDartPosition() {
    return dartMotor.getEncPosition() - robotState.getArmZero();
  }

  public void zeroDart() {
    robotState.setArmZero(dartMotor.getEncPosition());
  }

  public void setIntakeMotor(double speed) {
    intakeMotor.set(speed);
  }

  public void setShooterMotor(double speed) {
    shooterMotor.set(speed);
    shooterMotor2.set(speed);
  }

  public int getShooterVelocity() {
    return shooterMotor.getEncVelocity();
  }

  public void setScalerMotor(double speed) {
    scalerMotor.set(speed);
  }

  /**
   * 
   * @param value PWM value between 0.0 and 1.0
   */
  public void setExtensionServoPWM(double value) {
    extensionServo.set(value);
  }
  
  /**
   * Don't use this method. Use SensorInput instead.
   * 
   * @param Left
   *          drive encoder new position
   */
  public void setDriveLeftPos(int position) {
    driveLeft1.setEncPosition(-position);
  }

  /**
   * Don't use this method. Use SensorInput instead.
   * 
   * @return Right drive encoder position
   */
  public int getDriveRightEncPos() {
    return driveRight1.getEncPosition();
  }

  /**
   * Don't use this method. Use SensorInput instead.
   * 
   * @param Right
   *          drive encoder new position
   */
  public void setDriveRightPos(int position) {
    driveRight1.setEncPosition(-position);
  }

  /**
   * Don't use this method. Use SensorInput instead.
   * 
   * @return Left drive encoder velocity
   */
  public int getDriveLeftEncVel() {
    return -driveLeft1.getEncVelocity();
  }

  /**
   * Don't use this method. Use SensorInput instead.
   * 
   * @return Right drive encoder velocity
   */
  public int getDriveRightEncVel() {
    return -driveRight1.getEncVelocity();
  }

  /**
   * Drive the robot using arcadeDrive
   * 
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
   * 
   * @param leftValue
   *          Left motor values
   * @param rightValue
   *          Right motor values
   */
  public void tankDrive(double leftValue, double rightValue) {
    drive.tankDrive(-leftValue, -rightValue);
  }

  /**
   * Sets the dart motor speed, zero if trying to travel past sensors, and cut
   * down if near to sensors.
   * 
   * @param speed
   *          Speed of dart.
   */
  public boolean setDartMotor(double voltage) {
    if (sensorInput.getUpperHallEffect()) {
      sensorInput.zeroDart();
    }

    boolean returnValue = true;

    if ((voltage > 0.0 && sensorInput.getUpperHallEffect())
      || (voltage < 0.0 && sensorInput.getLowerHallEffect())) {
      voltage = 0.0;
      returnValue = false;
    }
    else if (voltage > 0.0
      && sensorInput.getDartPosition() > Constants.ARM_TOP_THROTTLE_POSITION) {
      voltage = Math.min(voltage, Constants.ARM_THROTTLE_SPEED);
    }
    else if (voltage < 0.0 && sensorInput
      .getDartPosition() < Constants.ARM_BOTTOM_THROTTLE_POSITION) {
      voltage = Math.max(voltage, -Constants.ARM_THROTTLE_SPEED);
    }

    dartMotor.set(voltage);

    return returnValue;
  }

  public boolean setDartMotorNonZeroed(double voltage) {
    if (sensorInput.getUpperHallEffect()) {
      sensorInput.zeroDart();
    }

    boolean returnValue = true;

    if ((voltage > 0.0 && sensorInput.getUpperHallEffect())
      || (voltage < 0.0 && sensorInput.getLowerHallEffect())) {
      voltage = 0.0;
      returnValue = false;
    }

    dartMotor.set(voltage);

    return returnValue;
  }

}
