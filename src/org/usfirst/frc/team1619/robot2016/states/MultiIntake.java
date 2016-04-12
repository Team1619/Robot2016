package org.usfirst.frc.team1619.robot2016.states;

import org.usfirst.frc.team1619.robot2016.Constants;
import org.usfirst.frc.team1619.robot2016.SubsystemID;
import org.usfirst.frc.team1619.robot2016.framework.State;
import org.usfirst.frc.team1619.robot2016.states.ArmPIDMove.ArmPosition;
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
    stallTimer.reset();
  }

  @Override
  public void update() {
    robotOutput.setShooterMotor(Constants.SHOOTER_INTAKE_SPEED);
    robotOutput.setIntakeMotor(Constants.INTAKE_INTAKE_SPEED);

    if (Math.abs(
      sensorInput.getShooterEncoderVelocity()) < Constants.INTAKE_STALL_SPEED) {
      if (stallTimer.isStarted()) {
        if (stallTimer.isFinished()) {
          robotState.setIntakeStalled(true);
          setFinished();
        }
      }
      else {
        stallTimer.setDuration(Constants.INTAKE_STALL_TIME_BEFORE_STOP);
        stallTimer.start();
      }
    }
  }

  @Override
  public void pause() {
    robotOutput.setShooterMotor(0);
    robotOutput.setIntakeMotor(0);
    stallTimer.reset();
  }

  @Override
  public boolean isReadyForActive() {
    return driverInput.getOperatorButton(Constants.OPERATOR_BUTTON_INTAKE)
      || ArmPIDMove.getArmPosition() == ArmPosition.INTAKE;
  }

  @Override
  protected void destruct() {
    robotOutput.setShooterMotor(0);
    robotOutput.setIntakeMotor(0);
    stallTimer.reset();
  }

}
