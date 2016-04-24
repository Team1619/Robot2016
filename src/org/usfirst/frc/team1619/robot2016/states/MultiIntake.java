package org.usfirst.frc.team1619.robot2016.states;

import org.usfirst.frc.team1619.robot2016.Constants;
import org.usfirst.frc.team1619.robot2016.SubsystemID;
import org.usfirst.frc.team1619.robot2016.framework.State;
import org.usfirst.frc.team1619.robot2016.util.GenericTimer;

public class MultiIntake extends State {

  private GenericTimer stallTimer;

  private static SubsystemID[] subsystems;

  static {
    subsystems = new SubsystemID[] {SubsystemID.INTAKE, SubsystemID.SHOOTER};
  }

  public MultiIntake() {
    super(subsystems);

    stallTimer = new GenericTimer();
  }

  @Override
  public void initialize() {
    stallTimer.setDuration(Constants.INTAKE_STALL_TIME_BEFORE_STOP);
  }

  @Override
  public void update() {
    robotOutput.setDartMotor(-1.0);

    if (sensorInput.getDartPosition() < Constants.ARM_POSITION_DEFAULT
      || driverInput
        .getOperatorButton(Constants.OPERATOR_BUTTON_INTAKE_MANUAL)) {
      robotOutput.setShooterMotor(Constants.SHOOTER_INTAKE_SPEED);
      robotOutput.setIntakeMotor(Constants.INTAKE_INTAKE_SPEED);
    }

    if (Math.abs(
      sensorInput.getShooterEncoderVelocity()) < Constants.INTAKE_STALL_SPEED
      && sensorInput.getDartPosition() < Constants.ARM_POSITION_DEFAULT) {
      if (stallTimer.isStarted()) {
        if (stallTimer.isFinished()) {
          robotState.setArmToDefault(true);
        }
      }
      else {
        stallTimer.start();
      }
    }

    if (robotState.getBallPresenceRisingEdge()) {
      setFinished();
    }
  }

  @Override
  public void pause() {
    robotOutput.setShooterMotor(0.0);
    robotOutput.setIntakeMotor(0.0);
    setFinished();
  }

  @Override
  public boolean isReadyForActive() {
    return (driverInput.getOperatorButton(Constants.OPERATOR_BUTTON_INTAKE)
      || isActive())
      && !(driverInput.getOperatorY() > Constants.JOYSTICK_DEADZONE)
      && !getFinished();
  }

  @Override
  protected void destruct() {
    robotOutput.setShooterMotor(0.0);
    robotOutput.setIntakeMotor(0.0);
    stallTimer.reset();

    setFinished(false);
  }

}
