package org.usfirst.frc.team1619.robot2016;

/**
 * Sensor inputs from the robot
 * @author Tim
 *
 */
class SensorInput {
  private static SensorInput instance;
  
  private SensorInput() {
    
  }
  
  public static SensorInput getInstance() {
    if(instance == null) {
      instance = new SensorInput();
    }
    return instance;
  }
  
  
}
