
package org.usfirst.frc.team1619.robot2016;

import org.usfirst.frc.team1619.robot2016.IO.SmashBoard;
import org.usfirst.frc.team1619.robot2016.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.IterativeRobot;

public class Robot extends IterativeRobot {
  
  public void robotInit() {
    
  }

  public void disabledInit() {
    
  }
  
  public void disabledPeriodic() {
    SmashBoard.getInstance().update();
  }
  
  public void autonomousInit() {

  }

  public void autonomousPeriodic() {
    
  }

  
  public void teleopPeriodic() {
    DriveTrain.getInstance().update();
    SmashBoard.getInstance().update();
  }

  public void testPeriodic() {

  }

}
