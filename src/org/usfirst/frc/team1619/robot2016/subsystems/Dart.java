package org.usfirst.frc.team1619.robot2016.subsystems;

import org.usfirst.frc.team1619.robot2016.IO.DriverInput;
import org.usfirst.frc.team1619.robot2016.IO.RobotOutput;

public class Dart implements Subsystem {
  private static Dart instance;
  static {
    instance = new Dart();
  }
  
  public static Dart getInstance() {
    return instance;
  }
  
  private RobotOutput robotOutput;
  private DriverInput driverInput;
  
  public Dart() {
    robotOutput = RobotOutput.getInstance();
    driverInput = DriverInput.getInstance();
  }

  @Override
  public void initialize() {
  }

  @Override
  public void update() {
    robotOutput.setDartMotor(driverInput.getOperatorY()/2);
  }

  @Override
  public void disable() {
  }
}
