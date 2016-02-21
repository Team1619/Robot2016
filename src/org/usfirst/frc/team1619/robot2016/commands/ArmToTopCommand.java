package org.usfirst.frc.team1619.robot2016.commands;

import org.usfirst.frc.team1619.robot2016.IO.RobotOutput;
import org.usfirst.frc.team1619.robot2016.IO.SensorInput;

public class ArmToTopCommand implements Command {

  private RobotOutput robotOutput;
  private SensorInput sensorInput;

  @Override
  public void initialize() {
    robotOutput = RobotOutput.getInstance();
    sensorInput = SensorInput.getInstance();
  }

  @Override
  public void update() {
    if (!sensorInput.getUpperHallEffect()) {
      robotOutput.setDartMotor(0.5);
    }
    else {
      robotOutput.setDartMotor(0.0);
    }
  }

  @Override
  public void destruct() {
    robotOutput.setDartMotor(0.0);
  }

  @Override
  public boolean finished() {
    return sensorInput.getUpperHallEffect();
  }

}
