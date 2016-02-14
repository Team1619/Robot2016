
package org.usfirst.frc.team1619.robot2016;

import org.usfirst.frc.team1619.robot2016.IO.SmashBoard;
import org.usfirst.frc.team1619.robot2016.auto.Autonomous;
import org.usfirst.frc.team1619.robot2016.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.IterativeRobot;

public class Robot extends IterativeRobot {

  SmashBoard smashBoard;
  DriveTrain driveTrain;
  Autonomous autonomous;

  public void robotInit() {
    autonomous = Autonomous.getInstance();
    driveTrain = DriveTrain.getInstance();
    smashBoard = SmashBoard.getInstance();
  }

  public void disabledInit() {
    autonomous.disable();
    driveTrain.disable();
  }

  public void disabledPeriodic() {
    smashBoard.update();
  }

  public void autonomousInit() {
    //Init driveTrain before autonomous
    //Otherwise the mode is not set correctly
    driveTrain.initialize();
    autonomous.initialize();
  }

  public void autonomousPeriodic() {
    autonomous.update();
    driveTrain.update();
    smashBoard.update();
  }

  public void teleopInit() {
    driveTrain.initialize();
  }

  public void teleopPeriodic() {
    driveTrain.update();
    smashBoard.update();
  }

  public void testPeriodic() {
  }
}
