package org.usfirst.frc.team1619.robot2016;

import org.usfirst.frc.team1619.robot2016.IO.RobotOutput;
import org.usfirst.frc.team1619.robot2016.IO.SensorInput;
import org.usfirst.frc.team1619.robot2016.IO.SocketTables.SmashBoard;
import org.usfirst.frc.team1619.robot2016.framework.RobotState;
import org.usfirst.frc.team1619.robot2016.framework.State;
import org.usfirst.frc.team1619.robot2016.framework.Subsystem;
import org.usfirst.frc.team1619.robot2016.states.ArmManual;
import org.usfirst.frc.team1619.robot2016.states.ArmPIDMove;
import org.usfirst.frc.team1619.robot2016.states.ArmZeroToTop;
import org.usfirst.frc.team1619.robot2016.states.AutoGenerator;
import org.usfirst.frc.team1619.robot2016.states.DriveManual;
import org.usfirst.frc.team1619.robot2016.states.DriveManualHoldHeading;
import org.usfirst.frc.team1619.robot2016.states.DriveRotateToCameraTarget;
import org.usfirst.frc.team1619.robot2016.states.DriveRotateToSetAngle;
import org.usfirst.frc.team1619.robot2016.states.IntakeManual;
import org.usfirst.frc.team1619.robot2016.states.MultiIntake;
import org.usfirst.frc.team1619.robot2016.states.MultiScale;
import org.usfirst.frc.team1619.robot2016.states.MultiShootAlign;
import org.usfirst.frc.team1619.robot2016.states.MultiShootAlignBatter;
import org.usfirst.frc.team1619.robot2016.states.MultiShootAlignHigh;
import org.usfirst.frc.team1619.robot2016.states.MultiShootAlignLow;
import org.usfirst.frc.team1619.robot2016.states.MultiShootManual;
import org.usfirst.frc.team1619.robot2016.states.ScalerManual;
import org.usfirst.frc.team1619.robot2016.states.ShootManual;

import edu.wpi.first.wpilibj.IterativeRobot;

public class Robot extends IterativeRobot {

  private RobotState robotState;

  private Subsystem driveTrain;
  private Subsystem utilityArm;
  private Subsystem shooter;
  private Subsystem intake;
  private Subsystem scaler;

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
    scaler = new Subsystem(SubsystemID.SCALER);

    smashBoard = SmashBoard.getInstance();
  }

  public void disabledInit() {
    Subsystem.resetAll();
    State.resetAll();
  }

  public void disabledPeriodic() {
    robotState.update();
    smashBoard.update();
  }

  public void autonomousInit() {
    Subsystem.resetAll();
    State.resetAll();

    AutoGenerator autoSequence = new AutoGenerator();

    driveTrain.addState(autoSequence);
    shooter.addState(autoSequence);
    intake.addState(autoSequence);
    utilityArm.addState(autoSequence);
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

    MultiIntake multiIntake = new MultiIntake();
    MultiShootAlignHigh multiShootAlignHigh = new MultiShootAlignHigh();
    MultiShootAlignLow multiShootAlignLow = new MultiShootAlignLow();
    MultiShootAlign multiShootAlignBatter = new MultiShootAlignBatter();
    MultiShootManual multiShootManual = new MultiShootManual();
    MultiScale multiScale = new MultiScale();

    driveTrain.addState(multiShootAlignHigh);
    driveTrain.addState(multiShootAlignLow);
    driveTrain.addState(multiShootAlignBatter);
    driveTrain.addState(new DriveManualHoldHeading());
    driveTrain.addState(new DriveRotateToCameraTarget());
    driveTrain.addState(new DriveRotateToSetAngle());
    driveTrain.addState(new DriveManual());

    // utilityArm.addState(new ArmManualOveride());
    utilityArm.addState(multiScale);
    utilityArm.addState(multiShootAlignHigh);
    utilityArm.addState(multiShootAlignLow);
    utilityArm.addState(multiShootAlignBatter);
    utilityArm.addState(new ArmZeroToTop());
    utilityArm.addState(new ArmPIDMove());
    utilityArm.addState(new ArmManual());

    shooter.addState(new ShootManual());
    shooter.addState(multiShootManual);
    shooter.addState(multiShootAlignHigh);
    shooter.addState(multiShootAlignLow);
    shooter.addState(multiShootAlignBatter);
    shooter.addState(multiIntake);

    intake.addState(new IntakeManual());
    intake.addState(multiShootManual);
    intake.addState(multiShootAlignHigh);
    intake.addState(multiShootAlignLow);
    intake.addState(multiShootAlignBatter);
    intake.addState(multiIntake);

    scaler.addState(multiScale);
    scaler.addState(new ScalerManual());
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
