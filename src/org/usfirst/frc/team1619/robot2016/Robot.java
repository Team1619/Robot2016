package org.usfirst.frc.team1619.robot2016;

import org.usfirst.frc.team1619.robot2016.IO.RobotOutput;
import org.usfirst.frc.team1619.robot2016.IO.SensorInput;
import org.usfirst.frc.team1619.robot2016.IO.SmashBoard;
import org.usfirst.frc.team1619.robot2016.framework.RobotState;
import org.usfirst.frc.team1619.robot2016.framework.State;
import org.usfirst.frc.team1619.robot2016.framework.Subsystem;
import org.usfirst.frc.team1619.robot2016.states.ArmManual;
import org.usfirst.frc.team1619.robot2016.states.ArmMoveToAngle;
import org.usfirst.frc.team1619.robot2016.states.AutoDriveAndShoot;
import org.usfirst.frc.team1619.robot2016.states.DriveManual;
import org.usfirst.frc.team1619.robot2016.states.DriveRotateToCameraTarget;
import org.usfirst.frc.team1619.robot2016.states.MultiIntake;

import edu.wpi.first.wpilibj.IterativeRobot;

public class Robot extends IterativeRobot {

  private RobotState robotState;

  private Subsystem driveTrain;
  private Subsystem utilityArm;
  private Subsystem shooter;
  private Subsystem intake;

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
    shooter.addState(autoDriveAndShoot);
    intake.addState(autoDriveAndShoot);
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

    MultiIntake shooterIntake = new MultiIntake();

    driveTrain.addState(new DriveRotateToCameraTarget());
    driveTrain.addState(new DriveManual());

    utilityArm.addState(new ArmMoveToAngle());
    utilityArm.addState(new ArmManual());

    shooter.addState(shooterIntake);
    intake.addState(shooterIntake);
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
