package org.usfirst.frc.team1619.robot2016.states;

import org.usfirst.frc.team1619.robot2016.SubsystemID;
import org.usfirst.frc.team1619.robot2016.framework.State;

public class ServoManual extends State {

  private static SubsystemID[] subsystems;

  static {
    subsystems = new SubsystemID[] {SubsystemID.SERVO};
  }

  public ServoManual() {
    super(subsystems);
  }

  @Override
  protected void initialize() {    
  }

  @Override
  protected void update() {
    robotOutput.setExtensionServoPWM((-driverInput.getOperatorThrottle() + 1) / 2);
  }

  @Override
  protected void destruct() {
    
  }

  @Override
  protected void pause() {
  }

  @Override
  public boolean isReadyForActive() {
    return true;
  }

  
}
