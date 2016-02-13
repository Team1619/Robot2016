package org.usfirst.frc.team1619.robot2016.subsystems;

import org.usfirst.frc.team1619.robot2016.Constants;
import org.usfirst.frc.team1619.robot2016.IO.DriverInput;
import org.usfirst.frc.team1619.robot2016.IO.RobotOutput;
import org.usfirst.frc.team1619.robot2016.IO.SensorInput;
import org.usfirst.frc.team1619.robot2016.IO.SmashBoard;
import org.usfirst.frc.team1619.robot2016.util.GenericPID;

import edu.wpi.first.wpilibj.GenericHID;

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
    MANUAL, PIDROTATE, PIDTRANSLATE, PIDFULL;
  }

  //Declare stuff
  Modes mode;

  private DriverInput driverInput;
  private SensorInput sensorInput;
  private RobotOutput robotOutput;

  private GenericPID translationPID;
  public GenericPID rotationPID;

  private DriveTrain() {
    //Init stuff
    mode = Modes.MANUAL;

    driverInput = DriverInput.getInstance();
    sensorInput = SensorInput.getInstance();
    robotOutput = RobotOutput.getInstance();

    translationPID = new GenericPID();
    rotationPID = new GenericPID();
  }

  @Override
  public void initialize() {
    mode = Modes.MANUAL;
    
    rotationPID.setValues(Constants.DRIVE_PID_ROTATION_P, 
      Constants.DRIVE_PID_ROTATION_I, 
      Constants.DRIVE_PID_ROTATION_D);

    translationPID.setValues(Constants.DRIVE_PID_TRANSLATION_P, 
      Constants.DRIVE_PID_TRANSLATION_I, 
      Constants.DRIVE_PID_TRANSLATION_D);
  }

  @Override
  public void update() {
    if(driverInput.getTurnPID()) {
      mode = Modes.PIDROTATE;
    }
    else {
      mode = Modes.MANUAL;
    }
    if(driverInput.getResetPID()) {
      reset();
      setRotationTarget(0);
//      rotationPID.setValues(SmashBoard.getInstance().getP(), SmashBoard.getInstance().getI(), 0);
    }
    
    switch(mode) {
      case MANUAL:
        drive(driverInput.getDriverStick());
        break;
      case PIDROTATE:
        rotationPID.calculate(((sensorInput.getNavXHeading() + 180) % 360) - 180);
          arcadeDrive(driverInput.getDriverY(), rotationPID.get());
        break;
      case PIDTRANSLATE:
        translationPID.calculate((sensorInput.getDriveLeftEncoderPosition()
          + sensorInput.getDriveRightEncoderPosition()) / 2);
        arcadeDrive(translationPID.get(), rotationPID.get());
        break;
      case PIDFULL:
        break;
      default:
        break;
    }
  }

  @Override
  public void disable() {
  }

  public void drive(GenericHID input) {
    robotOutput.arcadeDrive(input.getY(), input.getTwist());
  }

  public void arcadeDrive(double translation, double rotation) {
    robotOutput.arcadeDrive(translation, rotation);
  }

  public void tankDrive(double leftValue, double rightValue) {
    robotOutput.tankDrive(leftValue, rightValue);
  }

  public void setModeManual() {
    mode = Modes.MANUAL;
  }

  public void setModePIDTranslate() {
    mode = Modes.PIDTRANSLATE;
    rotationPID.setTarget(sensorInput.getDriveLeftEncoderPosition()
      - sensorInput.getDriveRightEncoderPosition());
  }

  public void setModePIDRotate() {
    mode = Modes.PIDROTATE;
  }

  public void setModePIDFull() {
    mode = Modes.PIDFULL;
  }

  public void setTranslationTarget(double target) {
    translationPID.setTarget(target);
  }

  public void setRotationTarget(double target) {
    rotationPID.setTarget(target);
  }

  public void reset() {
    sensorInput.setDriveLeftPos(0);
    sensorInput.setDriveRightPos(0);
    sensorInput.resetNavXHeading();
  }
}
