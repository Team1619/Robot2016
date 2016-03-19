package org.usfirst.frc.team1619.robot2016.states;

import org.usfirst.frc.team1619.robot2016.Constants;
import org.usfirst.frc.team1619.robot2016.SubsystemID;
import org.usfirst.frc.team1619.robot2016.framework.State;

public class ScalerManual extends State {

  private static SubsystemID[] subsystems;

  static {
    subsystems = new SubsystemID[] {SubsystemID.SCALER};
  }

  public ScalerManual() {
    super(subsystems);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void update() {
    if (driverInput
      .getOperatorButton(Constants.OPERATOR_BUTTON_MANUAL_SCALER_EXTEND)) {
      robotOutput.setScalerMotor(Constants.SCALER_EXTEND_SPEED);
    }
    else if (driverInput
      .getOperatorButton(Constants.OPERATOR_BUTTON_MANUAL_SCALER_RETRACT)) {
      robotOutput.setScalerMotor(Constants.SCALER_RETRACT_SPEED);
    }
    else {
      robotOutput.setScalerMotor(0);
    }
  }

  @Override
  protected void destruct() {
    robotOutput.setScalerMotor(0.0);
  }

  @Override
  protected void pause() {
    robotOutput.setScalerMotor(0.0);
  }

  @Override
  public boolean isReadyForActive() {
        return (driverInput
            .getOperatorButton(Constants.OPERATOR_BUTTON_MANUAL_SCALER_EXTEND)
            || driverInput
            .getOperatorButton(Constants.OPERATOR_BUTTON_MANUAL_SCALER_RETRACT))
            && (driverInput.getOperatorButton(Constants.OPERATOR_BUTTON_SCALER_TIMER_OVERIDE)
            || sensorInput.getMatchTimeRemaining() <= Constants.SCALER_MANUAL_TIMER_START);
        }
}
