package org.usfirst.frc.team1619.robot2016.states;

import org.usfirst.frc.team1619.robot2016.Constants;
import org.usfirst.frc.team1619.robot2016.SubsystemID;
import org.usfirst.frc.team1619.robot2016.framework.State;

public class IntakeManual extends State {

  private static SubsystemID[] subsystems;

  static {
    subsystems = new SubsystemID[] {SubsystemID.INTAKE};
  }

  public IntakeManual() {
    super(subsystems);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void update() {
    if (driverInput
      .getOperatorButton(Constants.OPERATOR_BUTTON_MANUAL_INTAKE_IN)) {
      robotOutput.setIntakeMotor(Constants.INTAKE_INTAKE_SPEED);
    }
    else if (driverInput
      .getOperatorButton(Constants.OPERATOR_BUTTON_MANUAL_INTAKE_OUT)) {
      robotOutput.setIntakeMotor(Constants.INTAKE_SHOOT_SPEED);
    }
    else {
      robotOutput.setIntakeMotor(0);
    }
  }

  @Override
  protected void destruct() {
    robotOutput.setIntakeMotor(0);
  }

  @Override
  protected void pause() {
    robotOutput.setIntakeMotor(0);
  }

  @Override
  public boolean isReadyForActive() {
    return driverInput
      .getOperatorButton(Constants.OPERATOR_BUTTON_MANUAL_INTAKE_IN)
      || driverInput
        .getOperatorButton(Constants.OPERATOR_BUTTON_MANUAL_INTAKE_OUT);
  }

}
