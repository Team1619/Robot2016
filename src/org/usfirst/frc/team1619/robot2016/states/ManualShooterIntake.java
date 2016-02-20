package org.usfirst.frc.team1619.robot2016.states;

import org.usfirst.frc.team1619.robot2016.Constants;
import org.usfirst.frc.team1619.robot2016.subsystems.SubsystemID;

public class ManualShooterIntake extends State {

  private static SubsystemID[] subsystems;

  static {
    subsystems = new SubsystemID[] {SubsystemID.SHOOTER_INTAKE};
  }

  public ManualShooterIntake() {
    super(subsystems);
  }

  @Override
  protected void setup() {
  }

  @Override
  protected void execute() {
    if (driverInput.getOperatorButton(Constants.OPERATOR_BUTTON_SHOOT)) {
      robotOutput
        .setShooterMotor(((-driverInput.getOperatorThrottle() + 1.0) / 2.0));

      if (driverInput.getOperatorButton(Constants.OPERATOR_BUTTON_INTAKE)
        || sensorInput.getShooterEncoderVelocity() >= 30000) {
        robotOutput.setIntakeMotor(1.0);
      }
    }
    else if (driverInput.getOperatorButton(Constants.OPERATOR_BUTTON_INTAKE)) {
      robotOutput.setIntakeMotor(-1.0);
      robotOutput.setShooterMotor(-1.0);
    }
    else {
      robotOutput.setIntakeMotor(0.0);
      robotOutput.setShooterMotor(0.0);
    }
  }

  @Override
  protected void pause() {
    robotOutput.setIntakeMotor(0.0);
    robotOutput.setShooterMotor(0.0);
  }

  @Override
  protected void destroy() {
    robotOutput.setIntakeMotor(0.0);
    robotOutput.setShooterMotor(0.0);
  }

  @Override
  public boolean isReadyForActive() {
    return true;
  }

}
