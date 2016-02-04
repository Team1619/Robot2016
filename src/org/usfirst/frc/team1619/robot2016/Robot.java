package org.usfirst.frc.team1619.robot2016;

import org.usfirst.frc.team1619.robot2016.IO.SmashBoard;
import org.usfirst.frc.team1619.robot2016.subsystems.DriveTrain;
import org.usfirst.frc.team1619.robot2016.subsystems.UtilityArm;

import edu.wpi.first.wpilibj.IterativeRobot;

public class Robot extends IterativeRobot {

  private SmashBoard smashBoard;
  private DriveTrain driveTrain;
  private UtilityArm utilityArm;

  public void robotInit() {
    smashBoard = SmashBoard.getInstance();
    driveTrain = DriveTrain.getInstance();
    utilityArm = UtilityArm.getInstance();
  }

  public void disabledInit() {
  }

  public void disabledPeriodic() {
    smashBoard.update();
  }

  public void autonomousInit() {
  }

  public void autonomousPeriodic() {
    smashBoard.update();
  }

  public void teleopInit() {
    driveTrain.initialize();
}
  
  public void teleopPeriodic() {
    driveTrain.update();
    smashBoard.update();
    utilityArm.update();
  }

  public void testPeriodic() {
  }
}
