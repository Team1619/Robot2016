package org.usfirst.frc.team1619.robot2016.states;

import org.usfirst.frc.team1619.robot2016.Constants;
import org.usfirst.frc.team1619.robot2016.SubsystemID;
import org.usfirst.frc.team1619.robot2016.framework.State;
import org.usfirst.frc.team1619.robot2016.util.GenericTimer;

public class MultiShootManual extends State {

  private static SubsystemID[] subsystems;

  static {
    subsystems = new SubsystemID[] {SubsystemID.INTAKE, SubsystemID.SHOOTER};
  }

  private boolean shooting;

  private GenericTimer intakeOutTimer;

  public MultiShootManual() {
    super(subsystems);

    shooting = false;

    intakeOutTimer = new GenericTimer();
    intakeOutTimer.setDuration(Constants.SHOOTING_INTAKE_OUT_TIME);
  }

  @Override
  protected void initialize() {
    shooting = false;
  }

  @Override
  protected void update() {
    robotOutput.setShooterMotor(Constants.SHOOTER_SHOOT_SPEED);

    if(driverInput.getOperatorButton(Constants.OPERATOR_BUTTON_SHOOT_RELEASE)
        || sensorInput.getShooterEncoderVelocity() > Constants.SHOOTER_SHOOT_SPEED_TARGET) {
      shooting = true;
    }

    if(shooting) {
      robotOutput.setIntakeMotor(Constants.INTAKE_SHOOT_SPEED);
    }
    else {
      robotOutput.setIntakeMotor(0);
      intakeOutTimer.start();
    }

    if (intakeOutTimer.isFinished()) {
      setFinished();
    }
  }

  @Override
  protected void destruct() {
    robotOutput.setShooterMotor(0.0);
    robotOutput.setIntakeMotor(0.0);
  }

  @Override
  protected void pause() {
    robotOutput.setShooterMotor(0.0);
    robotOutput.setIntakeMotor(0.0);
  }

  @Override
  public boolean isReadyForActive() {
    return driverInput.getOperatorButton(Constants.OPERATOR_BUTTON_SHOOT);
  }
}
