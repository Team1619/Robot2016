package org.usfirst.frc.team1619.robot2016.states;

import org.usfirst.frc.team1619.robot2016.Constants;
import org.usfirst.frc.team1619.robot2016.SubsystemID;
import org.usfirst.frc.team1619.robot2016.framework.State;
import org.usfirst.frc.team1619.robot2016.states.ArmPIDMove.ArmPosition;

public class MultiIntake extends State {

  private static SubsystemID[] subsystems;

  static {
    subsystems = new SubsystemID[] {SubsystemID.INTAKE, SubsystemID.SHOOTER};
  }

  public MultiIntake() {
    super(subsystems);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void update() {

    robotOutput.setShooterMotor(Constants.SHOOTER_INTAKE_SPEED);
    robotOutput.setIntakeMotor(Constants.INTAKE_INTAKE_SPEED);
  }

  @Override
  public void pause() {
    robotOutput.setShooterMotor(0);
    robotOutput.setIntakeMotor(0);
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
  }

}
