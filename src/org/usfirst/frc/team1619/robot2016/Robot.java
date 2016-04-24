package org.usfirst.frc.team1619.robot2016;

import org.usfirst.frc.team1619.robot2016.IO.RobotOutput;
import org.usfirst.frc.team1619.robot2016.IO.SensorInput;
import org.usfirst.frc.team1619.robot2016.IO.SocketTables.SmashBoard;
import org.usfirst.frc.team1619.robot2016.framework.State;
import org.usfirst.frc.team1619.robot2016.framework.Subsystem;
import org.usfirst.frc.team1619.robot2016.states.ArmManual;
import org.usfirst.frc.team1619.robot2016.states.ArmMoveToDefault;
import org.usfirst.frc.team1619.robot2016.states.ArmZeroToTop;
import org.usfirst.frc.team1619.robot2016.states.AutoGenerator;
import org.usfirst.frc.team1619.robot2016.states.DriveManual;
import org.usfirst.frc.team1619.robot2016.states.DriveManualHoldHeading;
import org.usfirst.frc.team1619.robot2016.states.DriveRotateToCameraTarget;
import org.usfirst.frc.team1619.robot2016.states.DriveRotateToSetAngle;
import org.usfirst.frc.team1619.robot2016.states.IntakeManual;
import org.usfirst.frc.team1619.robot2016.states.LEDsPassive;
import org.usfirst.frc.team1619.robot2016.states.MultiIntake;
import org.usfirst.frc.team1619.robot2016.states.MultiScale;
import org.usfirst.frc.team1619.robot2016.states.MultiShootFromAnywhere;
import org.usfirst.frc.team1619.robot2016.states.MultiShootManual;
import org.usfirst.frc.team1619.robot2016.states.ScalerManual;
import org.usfirst.frc.team1619.robot2016.states.ServoManual;
import org.usfirst.frc.team1619.robot2016.states.ShootManual;
import org.usfirst.frc.team1619.robot2016.states.ShootPassive;

import edu.wpi.first.wpilibj.IterativeRobot;

public class Robot extends IterativeRobot {

  private RobotState robotState;

  private Subsystem driveTrain;
  private Subsystem utilityArm;
  private Subsystem shooter;
  private Subsystem intake;
  private Subsystem scaler;
  private Subsystem leds;
  private Subsystem servo;

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
    leds = new Subsystem(SubsystemID.LEDS);
    servo = new Subsystem(SubsystemID.SERVO);

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
    robotState.initialize();
    Subsystem.resetAll();
    State.resetAll();

    AutoGenerator autoSequence = new AutoGenerator();
    LEDsPassive ledsState = new LEDsPassive();

    driveTrain.addState(autoSequence);
    shooter.addState(autoSequence);
    intake.addState(autoSequence);
    utilityArm.addState(autoSequence);

    leds.addState(ledsState);
  }

  public void autonomousPeriodic() {
    robotState.update();
    Subsystem.updateAll();
    State.updateAll();
    smashBoard.update();
  }

  public void teleopInit() {
    robotState.initialize();
    Subsystem.resetAll();
    State.resetAll();

    MultiIntake multiIntake = new MultiIntake();
    MultiShootFromAnywhere multiShootFromAnywhereFindLeft =
      new MultiShootFromAnywhere(Constants.DRIVER_BUTTON_SHOOT_FIND_LEFT, true);
    MultiShootFromAnywhere multiShootFromAnywhereFindRight =
      new MultiShootFromAnywhere(Constants.DRIVER_BUTTON_SHOOT_FIND_RIGHT,
        false);
    MultiShootManual multiShootManual = new MultiShootManual();
    MultiScale multiScale = new MultiScale();

    driveTrain.addState(multiShootFromAnywhereFindLeft);
    driveTrain.addState(multiShootFromAnywhereFindRight);
    driveTrain.addState(new DriveManualHoldHeading());
    driveTrain.addState(new DriveRotateToCameraTarget());
    driveTrain.addState(new DriveRotateToSetAngle());
    driveTrain.addState(new DriveManual());

    // utilityArm.addState(new ArmManualOveride());
    utilityArm.addState(new ArmZeroToTop());
    utilityArm.addState(multiScale);
    utilityArm.addState(multiShootFromAnywhereFindLeft);
    utilityArm.addState(multiShootFromAnywhereFindRight);
    utilityArm.addState(new ArmMoveToDefault());
    utilityArm.addState(multiIntake);
    utilityArm.addState(new ArmManual());

    shooter.addState(new ShootManual());
    shooter.addState(multiShootManual);
    shooter.addState(multiShootFromAnywhereFindLeft);
    shooter.addState(multiShootFromAnywhereFindRight);
    shooter.addState(multiIntake);
    shooter.addState(new ShootPassive());

    intake.addState(new IntakeManual());
    intake.addState(multiShootManual);
    intake.addState(multiShootFromAnywhereFindLeft);
    intake.addState(multiShootFromAnywhereFindRight);
    intake.addState(multiIntake);

    scaler.addState(multiScale);
    scaler.addState(new ScalerManual());

    leds.addState(multiShootFromAnywhereFindLeft);
    leds.addState(multiShootFromAnywhereFindRight);
    leds.addState(multiIntake);
    leds.addState(new LEDsPassive());

    servo.addState(multiShootFromAnywhereFindLeft);
    servo.addState(multiShootFromAnywhereFindRight);
    servo.addState(new ServoManual());
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
