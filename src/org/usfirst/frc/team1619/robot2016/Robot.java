package org.usfirst.frc.team1619.robot2016;

import org.usfirst.frc.team1619.robot2016.IO.SmashBoard;
import org.usfirst.frc.team1619.robot2016.states.ArmToAngle;
import org.usfirst.frc.team1619.robot2016.states.AutoDriveAndShoot;
import org.usfirst.frc.team1619.robot2016.states.ManualArm;
import org.usfirst.frc.team1619.robot2016.states.ManualDrive;
import org.usfirst.frc.team1619.robot2016.states.ManualShooterIntake;
import org.usfirst.frc.team1619.robot2016.states.RotateToPresetTarget;
import org.usfirst.frc.team1619.robot2016.states.State;
import org.usfirst.frc.team1619.robot2016.subsystems.Subsystem;
import org.usfirst.frc.team1619.robot2016.subsystems.SubsystemID;

import edu.wpi.first.wpilibj.IterativeRobot;

public class Robot extends IterativeRobot {

  private RobotState robotState;

  private Subsystem driveTrain;
  private Subsystem utilityArm;
  private Subsystem shooterIntake;

  private SmashBoard smashBoard;

  public void robotInit() {
    robotState = RobotState.getInstance();

    driveTrain = new Subsystem(SubsystemID.DRIVE_TRAIN);
    utilityArm = new Subsystem(SubsystemID.UTILITY_ARM);
    shooterIntake = new Subsystem(SubsystemID.SHOOTER_INTAKE);

    smashBoard = SmashBoard.getInstance();
  }

  public void disabledInit() {
    Subsystem.resetAll();
    State.resetAll();
  }

  public void disabledPeriodic() {
    smashBoard.update();
  }

  public void autonomousInit() {
    Subsystem.resetAll();
    State.resetAll();

    AutoDriveAndShoot autoDriveAndShoot = new AutoDriveAndShoot();

    driveTrain.addState(autoDriveAndShoot);
    shooterIntake.addState(autoDriveAndShoot);
    utilityArm.addState(autoDriveAndShoot);
  }

  public void autonomousPeriodic() {
    robotState.update();
    Subsystem.updateAll();
    State.updateAll();
    smashBoard.update();
  }

  public void teleopInit() {
    Subsystem.resetAll();
    State.resetAll();

    driveTrain.addState(new RotateToPresetTarget());
    driveTrain.addState(new ManualDrive());
    
    utilityArm.addState(new ArmToAngle());
    utilityArm.addState(new ManualArm());
    
    shooterIntake.addState(new ManualShooterIntake());
  }

  public void teleopPeriodic() {
    robotState.update();
    Subsystem.updateAll();
    State.updateAll();
    smashBoard.update();
  }

  public void testPeriodic() {
  }

}
