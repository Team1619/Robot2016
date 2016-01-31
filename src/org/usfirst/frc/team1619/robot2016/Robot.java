
package org.usfirst.frc.team1619.robot2016;

import org.usfirst.frc.team1619.robot2016.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.IterativeRobot;

public class Robot extends IterativeRobot {

  public void robotInit() {

  }

  public void autonomousInit() {

  }

  public void autonomousPeriodic() {

  }

  public void teleopPeriodic() {
    DriveTrain.getInstance().update();
  }

  public void testPeriodic() {

  }

}
