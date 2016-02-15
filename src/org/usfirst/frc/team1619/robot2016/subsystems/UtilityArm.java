package org.usfirst.frc.team1619.robot2016.subsystems;

import org.usfirst.frc.team1619.robot2016.Constants;
import org.usfirst.frc.team1619.robot2016.IO.DriverInput;
import org.usfirst.frc.team1619.robot2016.IO.RobotOutput;
import org.usfirst.frc.team1619.robot2016.IO.SensorInput;

public class UtilityArm implements Subsystem {
  private static UtilityArm instance;
  static {
    try {
      instance = new UtilityArm();
    }
    catch (Throwable e) {
      e.printStackTrace();
      throw e;
    }
  }

  public static UtilityArm getInstance() {
    return instance;
  }

  private RobotOutput robotOutput;
  private DriverInput driverInput;
  private SensorInput sensorInput;

  public UtilityArm() {
    robotOutput = RobotOutput.getInstance();
    driverInput = DriverInput.getInstance();
    sensorInput = SensorInput.getInstance();
  }

  @Override
  public void initialize() {
  }

  @Override
  public void update() {
    double velocity = driverInput.getOperatorY() * Constants.DART_SPEED_FACTOR;

    if (velocity < 0.0) {
      velocity = sensorInput.getUpperHallEffect() ? 0.0 : velocity;
    }
    else if (velocity > 0.0) {
      velocity = sensorInput.getLowerHallEffect() ? 0.0 : velocity;
    }

    robotOutput.setDartMotor(velocity);
    // double angle = getArmAngle(sensorInput.getDartPosition());
    // System.out.println("calculated angle " + angle);
    // System.out.println("current assumed offset " + getDartExtension(0.0));
  }

  @Override
  public void disable() {
  }

  public double getArmAngle(double dartExtention) {
    double dartPivotLengthSquared =
      ((Constants.DART_WIDTH * Constants.DART_WIDTH)
        + ((Constants.DART_HEIGHT + dartExtention)
          * (Constants.DART_HEIGHT + dartExtention)));
    double numerator = (((Constants.ARM_LENGTH * Constants.ARM_LENGTH)
      + (Constants.ARM_TO_DART_LENGTH * Constants.ARM_TO_DART_LENGTH))
      - dartPivotLengthSquared);
    double denominator =
      2.0 * Constants.ARM_LENGTH * Constants.ARM_TO_DART_LENGTH;
    System.out.println("dart extention " + dartExtention);
    // System.out.println("dart length " + Math.sqrt(dartPivotLengthSquared));
    // System.out.println("numerator constant " +
    // ((Constants.ARM_LENGTH*Constants.ARM_LENGTH) +
    // (Constants.ARM_TO_DART_LENGTH*Constants.ARM_TO_DART_LENGTH)));
    // System.out.println("denominator " + denominator);
    // System.out.println("cos angle " + numerator/denominator);
    double triangleAngle = Math.toDegrees(Math.acos(numerator / denominator));
    return ((triangleAngle - Constants.ARM_TO_DART_DECLINATION_ANGLE)
      + Constants.ARM_PIVOTS_TO_TOP_SURFACE_ANGLE);
  }

  public double getDartExtension(double Angle) {
    double triangleAngle = Angle + Constants.ARM_TO_DART_DECLINATION_ANGLE
      - Constants.ARM_PIVOTS_TO_TOP_SURFACE_ANGLE;
    double ratio = Math.cos(Math.toRadians(triangleAngle));
    double denominator =
      2.0 * Constants.ARM_LENGTH * Constants.ARM_TO_DART_LENGTH;
    double ab2cosC = ratio * denominator;
    double aSquaredPlus_bSquared = (Constants.ARM_LENGTH * Constants.ARM_LENGTH)
      + (Constants.ARM_TO_DART_LENGTH * Constants.ARM_TO_DART_LENGTH);
    double dartPivotLengthSquared = aSquaredPlus_bSquared - ab2cosC;
    double dartParallelLength = Math.sqrt(
      dartPivotLengthSquared - (Constants.DART_WIDTH * Constants.DART_WIDTH));
    return (dartParallelLength - Constants.DART_HEIGHT);
  }
}
