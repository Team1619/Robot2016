package org.usfirst.frc.team1619.robot2016.states;

import org.usfirst.frc.team1619.robot2016.Constants;
import org.usfirst.frc.team1619.robot2016.SubsystemID;
import org.usfirst.frc.team1619.robot2016.framework.State;

public class ShootManual extends State {

  private static SubsystemID[] subsystems;

  static {
    subsystems = new SubsystemID[] {SubsystemID.SHOOTER};
  }

  public ShootManual() {
    super(subsystems);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void update() {
    if (driverInput
        .getOperatorButton(Constants.OPERATOR_BUTTON_MANUAL_SHOOTER_IN)) {
        robotOutput.setShooterMotor(Constants.SHOOTER_INTAKE_SPEED);
      }
      else if (driverInput
        .getOperatorButton(Constants.OPERATOR_BUTTON_MANUAL_SHOOTER_OUT)) {
        robotOutput.setShooterMotor(Constants.SHOOTER_SHOOT_SPEED);
      }
      else {
        robotOutput.setShooterMotor(0);
      }
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
    return driverInput
        .getOperatorButton(Constants.OPERATOR_BUTTON_MANUAL_SHOOTER_IN)
        || driverInput
          .getOperatorButton(Constants.OPERATOR_BUTTON_MANUAL_SHOOTER_OUT);
  }
}
