package org.usfirst.frc.team1619.robot2016.util.PID;

import org.usfirst.frc.team1619.robot2016.Constants;
import org.usfirst.frc.team1619.robot2016.IO.SensorInput;

public class DriveRotationPID extends PIDKachigBand {
  SensorInput sensorInput;

  public DriveRotationPID() {
    super();
    sensorInput = SensorInput.getInstance();

    setValues(Constants.DRIVE_PID_ROTATION);
    setIRange(Constants.DRIVE_PID_ROTATION_IRANGE);
    setKachigBand(Constants.DRIVE_PID_ROTATION_KACHIG_BAND);
    setKachigConstant(Constants.DRIVE_PID_ROTATION_KACHIG_CONSTANT);
    setMinumumOutput(Constants.DRIVE_PID_ROTATION_MINIMUM);
    setKachigTime(Constants.DRIVE_PID_ROTATION_KACHIG_ONTIME,
      Constants.DRIVE_PID_ROTATION_KACHIG_OFFTIME);
  }

  private double getRotation() {
    return ((sensorInput.getNavXHeading() + 540 - setPoint) % 360) - 180;
  }

  public void calculate() {
    super.calculate(getRotation());
  }

  public double get() {
    return super.get();
  }

  public double getError() {
    return setPoint - getRotation();
  }

  public void setRotationKachig(boolean isKachiging) {
    if (isKachiging) {
      setKachigBand(Constants.DRIVE_PID_ROTATION_KACHIG_BAND);
    }
    else {
      setKachigBand(0);
    }
  }
}
