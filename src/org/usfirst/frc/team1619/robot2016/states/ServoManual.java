package org.usfirst.frc.team1619.robot2016.states;

import org.usfirst.frc.team1619.robot2016.SubsystemID;
import org.usfirst.frc.team1619.robot2016.framework.State;

public class ServoManual extends State {

  private static SubsystemID[] subsystems;

  static {
    subsystems = new SubsystemID[] {SubsystemID.SERVO};
  }

  private boolean throttleEdge;
  
  public ServoManual() {
    super(subsystems);
  }

  @Override
  protected void initialize() {    
    throttleEdge = false;
  }

  @Override
  protected void update() {
    double operatorThrottle = driverInput.getOperatorThrottle();
    if (Math.abs(operatorThrottle) == 1 && !throttleEdge) {      
      robotOutput.setExtensionServoPWM((-operatorThrottle + 1) / 2);
    }
    
    throttleEdge = Math.abs(operatorThrottle) == 1;
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
