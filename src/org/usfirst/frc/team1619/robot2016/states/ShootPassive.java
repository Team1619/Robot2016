package org.usfirst.frc.team1619.robot2016.states;

import org.usfirst.frc.team1619.robot2016.Constants;
import org.usfirst.frc.team1619.robot2016.SubsystemID;
import org.usfirst.frc.team1619.robot2016.framework.State;

public class ShootPassive extends State {

  private static SubsystemID[] subsystems;

  static {
    subsystems = new SubsystemID[] {SubsystemID.SHOOTER};
  }

  public ShootPassive() {
    super(subsystems);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void update() {
    robotOutput.setShooterMotor(sensorInput.getBallPresenceSensorRear()
      ? Constants.SHOOTER_PASSIVE_SPEED : -Constants.SHOOTER_PASSIVE_SPEED);
  }

  @Override
  protected void destruct() {
    robotOutput.setShooterMotor(0);
  }

  @Override
  protected void pause() {
    robotOutput.setShooterMotor(0);
  }

  @Override
  public boolean isReadyForActive() {
    return true;
  }

}
