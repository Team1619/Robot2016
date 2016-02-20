package org.usfirst.frc.team1619.robot2016.commands;

import org.usfirst.frc.team1619.robot2016.IO.RobotOutput;
import org.usfirst.frc.team1619.robot2016.IO.SensorInput;

public class ShootCommand implements Command {

  private RobotOutput robotOutput;
  private SensorInput sensorInput;

  private int extraTime;

  @Override
  public void initialize() {
    robotOutput = RobotOutput.getInstance();
    sensorInput = SensorInput.getInstance();

    extraTime = 0;
  }

  @Override
  public void update() {
    robotOutput.setShooterMotor(1.0);

    if (sensorInput.getShooterEncoderVelocity() >= 29000) {
      robotOutput.setIntakeMotor(1.0);
      extraTime++;
    }

    if (extraTime > 0) {
      extraTime++;
    }
  }

  @Override
  public void destruct() {
    robotOutput.setShooterMotor(0.0);
    robotOutput.setIntakeMotor(0.0);
  }

  @Override
  public boolean finished() {
    return extraTime > 10;
  }

}
