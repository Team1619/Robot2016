package org.usfirst.frc.team1619.robot2016.subsystems;

import org.usfirst.frc.team1619.robot2016.DriverInput;
import org.usfirst.frc.team1619.robot2016.RobotOutput;

import edu.wpi.first.wpilibj.GenericHID;

public class DriveTrain implements Subsystem {
  //Singleton stuff
  //static is called when the class is loaded
  private static DriveTrain instance;
  static {
    instance = new DriveTrain();
  }

  

  //Declare stuff
  private RobotOutput robotOutput;
  private DriverInput driverInput;

  private DriveTrain() {
    //Init stuff
    robotOutput = RobotOutput.getInstance();
    driverInput = DriverInput.getInstance();
  }

  public static DriveTrain getInstance() {
    return instance;
  }

  @Override
  public void initialize() {
  }

  @Override
  public void update() {
    this.drive(driverInput.getDriverStick());
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

  
}
