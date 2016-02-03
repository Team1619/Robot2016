package org.usfirst.frc.team1619.robot2016.subsystems;

import org.usfirst.frc.team1619.robot2016.IO.DriverInput;
import org.usfirst.frc.team1619.robot2016.IO.RobotOutput;

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
    MANUAL, PID;
  }

  //Declare stuff
  Modes mode;

  private RobotOutput robotOutput;
  private DriverInput driverInput;
  
  private double targetRotation;

  private DriveTrain() {
    //Init stuff
    mode = Modes.MANUAL;
    
    robotOutput = RobotOutput.getInstance();
    driverInput = DriverInput.getInstance();
    
    targetRotation = 0;
  }

  @Override
  public void initialize() {
  }

  @Override
  public void update() {
    if (mode.equals(Modes.MANUAL)) {
      drive(driverInput.getDriverStick());
    }
    else if(mode.equals(Modes.PID)) {
      
    }
  }

  @Override
  public void disable() {
  }

  public void drive(GenericHID input) {
    robotOutput.drive(input.getY(), input.getTwist());
  }

  public void drive(double translation, double rotation) {
    robotOutput.drive(translation, rotation);
  }

  public void setModeManual() {
    mode = Modes.MANUAL;
  }

  public void setModePID() {
    mode = Modes.PID;
  }
  
  public void setSetpoint(double translation, double rotation) {
    
  }
}
