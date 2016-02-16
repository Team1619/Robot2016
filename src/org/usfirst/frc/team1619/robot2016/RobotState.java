package org.usfirst.frc.team1619.robot2016;

public class RobotState {

  private static RobotState instance;

  private AutoState autoState;
  private TeleopState teleopState;

  private double armTarget;

  static {
    instance = new RobotState();
  }

  public static RobotState getInstance() {
    return instance;
  }

  private RobotState() {
    autoState = new AutoState();
    teleopState = new TeleopState();

    armTarget = 0.0;
  }

  public void update() {

  }

  public void setArmTarget(double newArmTarget) {
    armTarget = newArmTarget;
  }

  public double getArmTarget() {
    return armTarget;
  }

}
