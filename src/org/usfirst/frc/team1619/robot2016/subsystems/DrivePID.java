package org.usfirst.frc.team1619.robot2016.subsystems;

import org.usfirst.frc.team1619.robot2016.Constants;
import org.usfirst.frc.team1619.robot2016.IO.SensorInput;
import org.usfirst.frc.team1619.robot2016.util.GenericPID;

public class DrivePID {

  private static DrivePID instance;

  private SensorInput sensorInput;

  private GenericPID rotationPID;
  private GenericPID translationPID;

  private double rotationTarget;
  private double translationTarget;

  static {
    instance = new DrivePID();
  }

  public static DrivePID getInstance() {
    return instance;
  }

  private DrivePID() {
      sensorInput = SensorInput.getInstance();

      rotationPID = new GenericPID();
      translationPID = new GenericPID();

      rotationPID.setValues(Constants.DRIVE_PID_ROTATION);
      rotationPID.setIRange(Constants.DRIVE_PID_ROTATION_IRANGE);
      
      translationPID.setValues(Constants.DRIVE_PID_TRANSLATION);
  } 

  public void setRotationTarget(double newTarget) {
    rotationTarget = newTarget;
    rotationPID.setTarget(0.0);
  }

  public double getRotation() {
    double currentAngle = ((sensorInput.getNavXHeading() + 540 - rotationTarget) % 360) - 180;
    rotationPID.calculate(currentAngle);
    return rotationPID.get();
  }

  public void setTranslationTarget(double newTarget) {
    translationTarget = newTarget;
    translationPID.setTarget(translationTarget);
  }
  
  public double getTranslation() {
    double currentTranslation = sensorInput.getDriveRightEncoderPosition();
    translationPID.calculate(currentTranslation);
    return translationPID.get();
  }
  
  public void resetRotationIntegral() {
    rotationPID.resetIntegral();
  }

  public void resetRotation() {
    rotationPID.reset();
  }
  
  public void resetTranslationIntegral() {
    translationPID.resetIntegral();
  }
  
  public void resetTranslation() {
    translationPID.reset();
  }
  
}
