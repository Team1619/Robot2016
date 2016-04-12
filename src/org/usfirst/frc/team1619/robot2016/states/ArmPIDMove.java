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
      || (robotState.getBallPresenceRisingEdge()
        && sensorInput.getDartPosition() < Constants.ARM_POSITION_DEFAULT)
      || robotState.getIntakeStalled()) {
      armPos = ArmPosition.NEUTRAL;
      robotState.setIntakeStalled(false);
    }

    if (driverInput.getOperatorButton(Constants.OPERATOR_BUTTON_ARM_SHOOT)) {
      armPos = ArmPosition.SHOOT;
    }

    switch (armPos) {
      case INTAKE:
        robotOutput.setDartMotor(-1.0);
        break;
      case NEUTRAL:
        armPID.setTarget(Constants.ARM_POSITION_DEFAULT);
        armPID.calculate();
        robotOutput.setDartMotor(MathUtility.constrain(armPID.get(),
          Constants.ARM_MAX_SPEED, -Constants.ARM_MAX_SPEED));
        break;
      case SHOOT:
        robotOutput.setDartMotor(1.0);
        break;
    }

    if (Math.abs(driverInput.getOperatorY()) >= Constants.JOYSTICK_DEADZONE) {
      setFinished();
    }
  }

  @Override
  protected void pause() {
    robotOutput.setDartMotor(0);
    setFinished();
  }

  @Override
  protected void destruct() {
    armPos = ArmPosition.NEUTRAL;
    setFinished();
  }

  @Override
  public boolean isReadyForActive() {
    return isButtonPressed() || !getFinished();
  }

  private boolean isButtonPressed() {
    return driverInput.getOperatorButton(Constants.OPERATOR_BUTTON_ARM_INTAKE)
      || driverInput.getOperatorButton(Constants.OPERATOR_BUTTON_ARM_NEUTRAL)
      || driverInput.getOperatorButton(Constants.OPERATOR_BUTTON_ARM_SHOOT)
      || (robotState.getBallPresenceRisingEdge()
        && sensorInput.getDartPosition() < Constants.ARM_POSITION_DEFAULT);
  }

  public static ArmPosition getArmPosition() {
    return armPos;
  }
}
