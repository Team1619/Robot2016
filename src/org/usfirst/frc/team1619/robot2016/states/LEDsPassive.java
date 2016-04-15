package org.usfirst.frc.team1619.robot2016.states;

import org.usfirst.frc.team1619.robot2016.SubsystemID;
import org.usfirst.frc.team1619.robot2016.framework.State;

public class LEDsPassive extends State {

  private static SubsystemID[] subsystems;

  static {
    subsystems = new SubsystemID[] {SubsystemID.LEDS};
  }

  public LEDsPassive() {
    super(subsystems);
  }

  @Override
  protected void initialize() {    
  }

  @Override
  protected void update() {    
    sensorInput.setBallDetectedRear(sensorInput.getBallPresenceSensorRear());
    sensorInput.setBallDetectedFront(sensorInput.getBallPresenceSensorFront());
  }

  @Override
  protected void destruct() {    
    sensorInput.setBallDetectedFront(false);
    sensorInput.setBallDetectedRear(false);
  }

  @Override
  protected void pause() {
    sensorInput.setBallDetectedFront(false);
    sensorInput.setBallDetectedRear(false);    
  }

  @Override
  public boolean isReadyForActive() {
    return true;
  }
 
}
