package org.usfirst.frc.team1619.robot2016.subsystems;

import org.usfirst.frc.team1619.robot2016.IO.DriverInput;
import org.usfirst.frc.team1619.robot2016.IO.RobotOutput;
import org.usfirst.frc.team1619.robot2016.IO.SensorInput;
import org.usfirst.frc.team1619.robot2016.IO.SmashBoard;

public class TestMotor implements Subsystem {
  private static TestMotor instance;
  static {
    instance = new TestMotor();
  }

  public static TestMotor getInstance() {
    return instance;
  }
  
  private RobotOutput robotOutput;
  private DriverInput driverInput;

  public TestMotor() {
    robotOutput = RobotOutput.getInstance();
    driverInput = DriverInput.getInstance();
  }

  @Override
  public void initialize() {
  }

  @Override
  public void update() {
//    if (driverInput.getTestForwardButton()) {
//      robotOutput.setTestMotor(-(driverInput.getDriverThrottle() / 2) + 0.5);
//    }
//    else if (driverInput.getTestBackwardButton()) {
//      robotOutput.setTestMotor((driverInput.getDriverThrottle() / 2) - 0.5);
//    }
//    else {
//      robotOutput.setTestMotor(0);
//    }
  }

  @Override
  public void disable() {
  }
}
