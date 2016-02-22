package org.usfirst.frc.team1619.robot2016.subsystems;

import org.usfirst.frc.team1619.robot2016.Constants;
import org.usfirst.frc.team1619.robot2016.IO.SensorInput;
import org.usfirst.frc.team1619.robot2016.util.GenericPID;
import org.usfirst.frc.team1619.robot2016.util.PIDKachigBand;

public class DrivePID {

  private static DrivePID instance;

  private SensorInput sensorInput;

  private PIDKachigBand rotationPID;
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

    rotationPID = new PIDKachigBand();
    translationPID = new GenericPID();

    rotationPID.setValues(Constants.DRIVE_PID_ROTATION);
    rotationPID.setIRange(Constants.DRIVE_PID_ROTATION_IRANGE);
    rotationPID.setKachigBand(Constants.DRIVE_PID_ROTATION_KACHIG_BAND);
    rotationPID.setKachigConstant(Constants.DRIVE_PID_ROTATION_KACHIG_CONSTANT);
    rotationPID.setMinumumOutput(Constants.DRIVE_PID_ROTATION_MINIMUM);
    rotationPID.setKachigTime(Constants.DRIVE_PID_ROTATION_KACHIG_ONTIME,
      Constants.DRIVE_PID_ROTATION_KACHIG_OFFTIME);

    translationPID.setValues(Constants.DRIVE_PID_TRANSLATION);
    translationPID.setIRange(Constants.DRIVE_PID_TRANSLATION_IRANGE);
  }

  public void setRotationTarget(double newTarget) {
    rotationTarget = newTarget;
    rotationPID.setTarget(0.0);
  }

  public double getRotation() {
    // While outside of the alternate control band,
    double currentAngle =
      ((sensorInput.getNavXHeading() + 540 - rotationTarget) % 360) - 180;
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
