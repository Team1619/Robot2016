package org.usfirst.frc.team1619.robot2016;

import org.usfirst.frc.team1619.robot2016.IO.RobotOutput;
import org.usfirst.frc.team1619.robot2016.IO.SensorInput;
import org.usfirst.frc.team1619.robot2016.IO.SmashBoard;
import org.usfirst.frc.team1619.robot2016.framework.RobotState;
import org.usfirst.frc.team1619.robot2016.framework.State;
import org.usfirst.frc.team1619.robot2016.framework.Subsystem;
import org.usfirst.frc.team1619.robot2016.states.ArmManual;
import org.usfirst.frc.team1619.robot2016.states.ArmMoveToAngle;
import org.usfirst.frc.team1619.robot2016.states.AutoShootSequence;
import org.usfirst.frc.team1619.robot2016.states.DriveManual;
import org.usfirst.frc.team1619.robot2016.states.DriveManualHoldHeading;
import org.usfirst.frc.team1619.robot2016.states.DriveRotateToCameraTarget;
import org.usfirst.frc.team1619.robot2016.states.DriveRotateToSetAngle;
import org.usfirst.frc.team1619.robot2016.states.LockManual;
import org.usfirst.frc.team1619.robot2016.states.MultiIntake;

import edu.wpi.first.wpilibj.IterativeRobot;

public class Robot extends IterativeRobot {

  private RobotState robotState;

  private Subsystem driveTrain;
  private Subsystem utilityArm;
  private Subsystem shooter;
  private Subsystem intake;
  private Subsystem lock;

  private SmashBoard smashBoard;

  public void robotInit() {
    // Some IO singletons must be initialized, because some depend on others
    RobotOutput.getInstance().initialize();
    SensorInput.getInstance().initialize();

    robotState = RobotState.getInstance();

    driveTrain = new Subsystem(SubsystemID.DRIVE_TRAIN);
    utilityArm = new Subsystem(SubsystemID.UTILITY_ARM);
    shooter = new Subsystem(SubsystemID.SHOOTER);
    intake = new Subsystem(SubsystemID.INTAKE);
    lock = new Subsystem(SubsystemID.LOCK);

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

    AutoShootSequence autoShootSequence = new AutoShootSequence();

    driveTrain.addState(autoShootSequence);
    shooter.addState(autoShootSequence);
    intake.addState(autoShootSequence);
    utilityArm.addState(autoShootSequence);
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

    MultiIntake shooterIntake = new MultiIntake();

    driveTrain.addState(new DriveManualHoldHeading());
    driveTrain.addState(new DriveRotateToCameraTarget());
    driveTrain.addState(new DriveRotateToSetAngle());
    driveTrain.addState(new DriveManual());

    utilityArm.addState(new ArmMoveToAngle());
    utilityArm.addState(new ArmManual());

    shooter.addState(shooterIntake);
    intake.addState(shooterIntake);

    lock.addState(new LockManual());
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
