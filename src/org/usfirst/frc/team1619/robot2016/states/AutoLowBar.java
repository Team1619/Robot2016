package org.usfirst.frc.team1619.robot2016.states;

import org.usfirst.frc.team1619.robot2016.SubsystemID;
import org.usfirst.frc.team1619.robot2016.commands.DriveRotateCommand;
import org.usfirst.frc.team1619.robot2016.commands.DriveTranslateCommand;
import org.usfirst.frc.team1619.robot2016.commands.PauseCommand;
import org.usfirst.frc.team1619.robot2016.framework.SequencerState;

public class AutoLowBar extends SequencerState {

  private static SubsystemID[] subsystems;

  static {
    subsystems = new SubsystemID[] {SubsystemID.INTAKE, SubsystemID.SHOOTER,
      SubsystemID.DRIVE_TRAIN, SubsystemID.UTILITY_ARM};
  }

  public AutoLowBar() {
    super(subsystems);
  }

  @Override
  protected void addCommands() {
    double angle = sensorInput.getNavXHeading();
    // double armLowbarCrossPosition = Constants.ARM_POSITION_INTAKE + 0.1;
    // double translateAcrossLowbar = 140.0;
    // double rotateToIntermediateMove = 16.38;
    // double translateToIntermediateMove = 130;
    // double rotateToHighGoal = 60 - rotateToIntermediateMove;
    //
    // double translateTolerance = 2.0;
    //
    // add(new ArmZeroCommand());
    // add(new ArmMoveToPositionCommand(armLowbarCrossPosition, 2000));
    // add(new DriveTranslateCommand(translateAcrossLowbar, 3000));
    // add(new DriveRotateCommand(rotateToIntermediateMove, 1000));
    // add(new DriveTranslateCommand(translateToIntermediateMove, 3000));
    // add(new DriveRotateCommand(rotateToHighGoal, 1000));
    // add(new ArmMoveToPositionCommand(Constants.ARM_POSITION_SHOOT_LOW,
    // 2000));
    // add(new CrossChevalleDeFrise());
    // add(new ArmMoveToPositionCommand(-1, 1500));
    // add(new ArmMoveToPositionCommand(Constants.ARM_POSITION_DEFAULT, 1500));
    // add(DriveFromDefenseToHighGoalGenerator.DEFENSE_2
    // .getDriveToTargetGoalSequence(HighGoalTargetPosition.LEFT, 17, angle));
    // add(new ArmZeroCommand());
    add(new DriveTranslateCommand(205.0, 0.9, 5.0, 5000));
    add(new DriveRotateCommand(57.5, 2000));
    add(new PauseCommand(2500));
    add(new DriveRotateCommand(122.5, 2000));
    // add(new ArmZeroCommand());
    // add(new PauseCommand(500));
    // add(new ShootAlignHighGoalCommand(0));
  }

  @Override
  protected void destruct() {
    super.destruct();

    setFinished();
  }
}
