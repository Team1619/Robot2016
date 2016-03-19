package org.usfirst.frc.team1619.robot2016.states;

import org.usfirst.frc.team1619.robot2016.Constants;
import org.usfirst.frc.team1619.robot2016.SubsystemID;
import org.usfirst.frc.team1619.robot2016.framework.State;
import org.usfirst.frc.team1619.robot2016.util.MathUtility;
import org.usfirst.frc.team1619.robot2016.util.PID.ArmPID;

public class ArmPIDMove extends State {

  private static SubsystemID[] subsystems;

  static {
    subsystems = new SubsystemID[] {SubsystemID.UTILITY_ARM};
  }

  public static enum ArmPosition {
    INTAKE, NEUTRAL, SHOOT
  }

  private ArmPID armPID;
  private static ArmPosition armPos;

  public ArmPIDMove() {
    super(subsystems);

    armPID = new ArmPID();
    armPID.setTarget(Constants.ARM_POSITION_DEFAULT);

    armPos = ArmPosition.NEUTRAL;
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void update() {

    if (driverInput.getOperatorButton(Constants.OPERATOR_BUTTON_ARM_INTAKE)) {
      armPos = ArmPosition.INTAKE;
    }

    if (driverInput.getOperatorButton(Constants.OPERATOR_BUTTON_ARM_NEUTRAL)
      || robotState.getBallPresenceRisingEdge()) {
      armPos = ArmPosition.NEUTRAL;
    }

    if (driverInput.getOperatorButton(Constants.OPERATOR_BUTTON_ARM_SHOOT)) {
      armPos = ArmPosition.SHOOT;
    }

    switch (armPos) {
      case INTAKE:
        armPID.setTarget(Constants.ARM_POSITION_INTAKE);
        break;
      case NEUTRAL:
        armPID.setTarget(Constants.ARM_POSITION_DEFAULT);
        break;
      case SHOOT:
        armPID.setTarget(Constants.ARM_POSITION_SHOOT_HIGH);
        break;
      default:
        break;
    }

    armPID.calculate();
    robotOutput.setDartMotor(MathUtility.constrain(armPID.get(),
      Constants.ARM_MAX_SPEED, -Constants.ARM_MAX_SPEED));

    if (Math.abs(driverInput.getOperatorY()) >= Constants.JOYSTICK_DEADZONE) {
      setFinished();
    }
  }

  @Override
  protected void pause() {
  }

  @Override
  protected void destruct() {
    armPos = ArmPosition.NEUTRAL;
  }

  @Override
  public boolean isReadyForActive() {
    return isButtonPressed() || !getFinished();
  }

  private boolean isButtonPressed() {
    return driverInput.getOperatorButton(Constants.OPERATOR_BUTTON_ARM_INTAKE)
      || driverInput.getOperatorButton(Constants.OPERATOR_BUTTON_ARM_NEUTRAL)
      || driverInput.getOperatorButton(Constants.OPERATOR_BUTTON_ARM_SHOOT)
      || robotState.getBallPresenceRisingEdge();
  }

  public static ArmPosition getArmPosition() {
    return armPos;
  }
}
