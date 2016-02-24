package org.usfirst.frc.team1619.robot2016.states;

import org.usfirst.frc.team1619.robot2016.SubsystemID;
import org.usfirst.frc.team1619.robot2016.framework.State;

public class LockManual extends State {

  private static SubsystemID[] subsystems;

  static {
    subsystems = new SubsystemID[] {SubsystemID.LOCK};
  }

  public LockManual() {
    super(subsystems);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void update() {
    if (driverInput.getOperatorButton(11)) {
      robotOutput.setLockMotor(0.1);
    }
    else if (driverInput.getOperatorButton(12)) {
      robotOutput.setLockMotor(-0.1);
    }
  }

  @Override
  protected void pause() {
    robotOutput.setLockMotor(0.0);
  }

  @Override
  protected void destruct() {
    robotOutput.setLockMotor(0.0);
  }

  @Override
  public boolean isReadyForActive() {
    return driverInput.getOperatorButton(11)
      || driverInput.getOperatorButton(12);
  }

}
