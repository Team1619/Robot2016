package org.usfirst.frc.team1619.robot2016.subsystems;

import org.usfirst.frc.team1619.robot2016.Constants;
import org.usfirst.frc.team1619.robot2016.IO.DriverInput;
import org.usfirst.frc.team1619.robot2016.IO.RobotOutput;
import org.usfirst.frc.team1619.robot2016.IO.SensorInput;

public class Dart implements Subsystem {
  private static Dart instance;
  static {
    instance = new Dart();
  }
  
  private SensorInput sensorInput;
  
  public static Dart getInstance() {
    return instance;
  }
  
  private RobotOutput robotOutput;
  private DriverInput driverInput;
  
  public Dart() {
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
    else if (velocity > 0.0 ) {
      velocity = sensorInput.getLowerHallEffect() ? 0.0 : velocity;
    }

    robotOutput.setDartMotor(velocity);
  }

  @Override
  public void disable() {
  }
}
