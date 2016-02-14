package org.usfirst.frc.team1619.robot2016.subsystems;

import org.usfirst.frc.team1619.robot2016.Constants;
import org.usfirst.frc.team1619.robot2016.IO.DriverInput;
import org.usfirst.frc.team1619.robot2016.IO.RobotOutput;
import org.usfirst.frc.team1619.robot2016.IO.SensorInput;

public class UtilityArm implements Subsystem {
  private static UtilityArm instance;
  static {
    instance = new UtilityArm();
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
    else if (velocity > 0.0 ) {
      velocity = sensorInput.getLowerHallEffect() ? 0.0 : velocity;
    }

    robotOutput.setDartMotor(velocity);
    
    if (driverInput.getIntakeButton()) {
      robotOutput.setIntakeMotor(Constants.INTAKE_INTAKE_SPEED);
      robotOutput.setShootMotor(Constants.SHOOT_INTAKE_SPEED);
    }
    else if (driverInput.getShootButton()) {
      robotOutput.setIntakeMotor(Constants.INTAKE_SHOOT_SPEED);
      robotOutput.setShootMotor(-(driverInput.getOperatorThrottle() + 1) / 2);
    }
    else {
      robotOutput.setIntakeMotor(0.0);
      robotOutput.setShootMotor(-(driverInput.getOperatorThrottle() + 1) / 2);
    }
  }

  @Override
  public void disable() {
  }
}
