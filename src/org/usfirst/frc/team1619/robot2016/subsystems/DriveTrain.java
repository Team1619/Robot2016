package org.usfirst.frc.team1619.robot2016.subsystems;

import org.usfirst.frc.team1619.robot2016.Constants;
import org.usfirst.frc.team1619.robot2016.IO.DriverInput;
import org.usfirst.frc.team1619.robot2016.IO.RobotOutput;
import org.usfirst.frc.team1619.robot2016.IO.SensorInput;
import org.usfirst.frc.team1619.robot2016.IO.SmashBoard;
import org.usfirst.frc.team1619.robot2016.util.GenericPID;

public class DriveTrain implements Subsystem {
  //Singleton stuff
  //static is called when the class is loaded
  private static DriveTrain instance;
  static {
    instance = new DriveTrain();
  }
  
  public static DriveTrain getInstance() {
    return instance;
  }

  //Enum for current state
  private enum Modes {
    MANUAL, PIDROTATE, PIDHOLDHEADING, PIDFULL;
  }

  //Declare stuff
  private Modes mode;
  private Modes prevMode;

  private DriverInput driverInput;
  private SensorInput sensorInput;
  private RobotOutput robotOutput;
  private SmashBoard smashBoard;

  private GenericPID translationPID;
  private GenericPID rotationPID;

  private double rotationTarget;
  private double currentAngle;

  private DriveTrain() {
    //Init stuff
    mode = Modes.MANUAL;
    prevMode = null;

    driverInput = DriverInput.getInstance();
    sensorInput = SensorInput.getInstance();
    robotOutput = RobotOutput.getInstance();
    smashBoard = SmashBoard.getInstance();

    rotationPID = new GenericPID();
    rotationPID.setValues(Constants.DRIVE_PID_ROTATION_P, 
      Constants.DRIVE_PID_ROTATION_I, 
      Constants.DRIVE_PID_ROTATION_D);
    rotationPID.setIRange(Constants.DRIVE_PID_ROTATION_IRANGE);

    translationPID = new GenericPID();
    translationPID.setValues(Constants.DRIVE_PID_TRANSLATION_P, 
      Constants.DRIVE_PID_TRANSLATION_I, 
      Constants.DRIVE_PID_TRANSLATION_D);
    translationPID.setIRange(Constants.DRIVE_PID_TRANSLATION_IRANGE);

    rotationTarget = 0;
    currentAngle = 0;
  }

  @Override
  public void initialize() {
    mode = Modes.MANUAL;
    prevMode = null;
  }

  @Override
  public void update() {
//    if(driverInput.getTurnPID()) {
//      mode = Modes.PIDFULL;
//    }
//    else {
//      mode = Modes.MANUAL;
//    }
//
//    if(driverInput.getResetPID()) {
//      rotationTarget = sensorInput.getNavXHeading();
//      rotationPID.setValues(smashBoard.getP(), 
//        smashBoard.getI(), 
//        smashBoard.getD());
//      resetTranslation();
//      translationPID.setTarget(2000);
//    }

    if(prevMode != mode) {
      initState();
    }
    updateState();
    prevMode = mode;
  }

  @Override
  public void disable() {
  }

  private void initState() {
    switch(mode) {
      case MANUAL:
        break;

      case PIDROTATE:
        rotationPID.reset();
        rotationPID.setTarget(0);
        break;

      case PIDHOLDHEADING:
        resetRotation();
        rotationPID.reset();
        rotationPID.setTarget(0);
        rotationTarget = 0;
        break;

      case PIDFULL:
        rotationPID.reset();
        rotationPID.setTarget(0);
        break;

      default:
        break;
    }
  }

  private void updateState() {
    switch(mode) {
      case MANUAL:
        driveManual();
        break;

      case PIDROTATE:
      case PIDHOLDHEADING:
        rotationPIDCalc();
        arcadeDrive(driverInput.getDriverTranslate(), rotationPID.get());
        break;

      case PIDFULL:
        translationPID.calculate((sensorInput.getDriveLeftEncoderPosition()
          + sensorInput.getDriveRightEncoderPosition()) / 2);
        rotationPIDCalc();
        arcadeDrive(-translationPID.get(), rotationPID.get());
        break;

      default:
        break;
    }
  }

  private void rotationPIDCalc() {
    currentAngle = 
      ((sensorInput.getNavXHeadingRaw() + 180 - rotationTarget) % 360) - 180;
    rotationPID.calculate(currentAngle);
  }

  private void driveManual() {
    robotOutput.arcadeDrive(driverInput.getDriverTranslate(), 
      driverInput.getDriverRotate());
  }

  private void arcadeDrive(double translation, double rotation) {
    robotOutput.arcadeDrive(translation, rotation);
  }

  public void setModeManual() {
    mode = Modes.MANUAL;
  }

  public void setModePIDRotate() {
    mode = Modes.PIDROTATE;
  }

  public void setModePIDHoldHeading() {
    mode = Modes.PIDHOLDHEADING;
  }

  public void setModePIDFull() {
    mode = Modes.PIDFULL;
  }

  public void setRotationTarget(double target) {
    //constrain target to numbers between 180 and -180
    rotationTarget = Math.min(180, Math.max(-180, target));
  }

  public void setTranslationTarget(double target) {
    translationPID.setTarget(target);
  }

  public void resetTranslation() {
    sensorInput.resetDriveLeftPos();
    sensorInput.resetDriveRightPos();
  }

  public void resetRotation() {
    sensorInput.resetNavXHeading();
  }

  /**
   * Returns the absolute value of the current rotation error.
   * @return Absolute value of current rotation angle error.
   */
  public double currentRotationError() {
    return Math.abs(currentAngle);
  }

  /**
   * Returns the absolute value of the current translation error.
   * @return Absolute value of current translation error.
   */
  public double currentTranslationError() {
    //Math?
    return Math.abs(translationPID.getSetPoint()
      -((sensorInput.getDriveLeftEncoderPosition()
        + sensorInput.getDriveRightEncoderPosition())
      / 2));
  }
}
