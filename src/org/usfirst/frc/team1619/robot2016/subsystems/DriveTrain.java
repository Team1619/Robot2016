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
  public GenericPID rotationPID;

  private DriveTrain() {
    //Init stuff
    mode = Modes.MANUAL;
    prevMode = null;

    driverInput = DriverInput.getInstance();
    sensorInput = SensorInput.getInstance();
    robotOutput = RobotOutput.getInstance();
    smashBoard = SmashBoard.getInstance();

    translationPID = new GenericPID();
    rotationPID = new GenericPID();
  }

  @Override
  public void initialize() {
    mode = Modes.MANUAL;
    prevMode = null;

    rotationPID.setValues(Constants.DRIVE_PID_ROTATION_P, 
      Constants.DRIVE_PID_ROTATION_I, 
      Constants.DRIVE_PID_ROTATION_D);
    rotationPID.setIRange(25);

    translationPID.setValues(Constants.DRIVE_PID_TRANSLATION_P, 
      Constants.DRIVE_PID_TRANSLATION_I, 
      Constants.DRIVE_PID_TRANSLATION_D);
  }

  @Override
  public void update() {
//    if(driverInput.getTurnPID()
//      && driverInput.getDriverRotate() < Constants.DRIVER_ROTATION_DEADZONE 
//      && driverInput.getDriverRotate() > -Constants.DRIVER_ROTATION_DEADZONE) {
//      if(mode != Modes.PIDHOLDHEADING
//        && sensorInput.getNavXRotationSpeed() < 1 
//        && sensorInput.getNavXRotationSpeed() > -1) {
//        mode = Modes.PIDHOLDHEADING;
//      }
//    }
//    else {
      mode = Modes.MANUAL;
//    }

    if(driverInput.getResetPID()) {
      reset();
      rotationPID.setValues(smashBoard.getP(), 
        smashBoard.getI(), 
        smashBoard.getD());
    }

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
        setRotationTarget(0);
        break;

      case PIDHOLDHEADING:
        reset();
        rotationPID.reset();
        setRotationTarget(0);
        break;

      case PIDFULL:
        break;

      default:
        break;
    }
  }

  private void updateState() {
    switch(mode) {
      case MANUAL:
        drive(driverInput.getDriverStick());
        break;

      case PIDROTATE:
      case PIDHOLDHEADING:
        rotationPID.calculate(sensorInput.getNavXHeading());
        arcadeDrive(driverInput.getDriverTranslate(), rotationPID.get());
        break;

      case PIDFULL:
        translationPID.calculate((sensorInput.getDriveLeftEncoderPosition()
          + sensorInput.getDriveRightEncoderPosition()) / 2);
        rotationPID.calculate(sensorInput.getNavXHeading());
        arcadeDrive(-translationPID.get(), rotationPID.get());
        break;

      default:
        break;
    }
  }

  private void drive(GenericHID input) {
    robotOutput.arcadeDrive(input.getY(), input.getTwist());
  }

  private void arcadeDrive(double translation, double rotation) {
    robotOutput.arcadeDrive(translation, rotation);
  }

  public void setTranslationTarget(double target) {
    translationPID.setTarget(target);
  }

  public void setRotationTarget(double target) {
    rotationPID.setTarget(target);
  }

  public void reset() {
    sensorInput.resetNavXHeading();
  }
}
