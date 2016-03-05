package org.usfirst.frc.team1619.robot2016.states;

import org.usfirst.frc.team1619.robot2016.Constants;
import org.usfirst.frc.team1619.robot2016.SubsystemID;
import org.usfirst.frc.team1619.robot2016.commands.ArmMoveToPositionCommand;
import org.usfirst.frc.team1619.robot2016.commands.ArmZeroCommand;
import org.usfirst.frc.team1619.robot2016.commands.DriveRotateCommand;
import org.usfirst.frc.team1619.robot2016.commands.DriveRotateToHighGoalCommand;
import org.usfirst.frc.team1619.robot2016.commands.DriveTranslateCommand;
import org.usfirst.frc.team1619.robot2016.commands.ShootAlignHighGoalCommand;
import org.usfirst.frc.team1619.robot2016.commands.ShootCommand;
import org.usfirst.frc.team1619.robot2016.commands.ShootManualCommand;
import org.usfirst.frc.team1619.robot2016.framework.CommandGroup;
import org.usfirst.frc.team1619.robot2016.framework.CommandSequence;
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
    double armLowbarCrossPosition = Constants.ARM_POSITION_INTAKE + 0.1;
    double translateAcrossLowbar = 140.0 / 2;
    double rotateToIntermediateMove = 16.38;
    double translateToIntermediateMove = 130 / 2;
    double rotateToHighGoal = 60 - rotateToIntermediateMove;

    double translateTolerance = 2.0;

    add(new ArmZeroCommand());
    add(new ArmMoveToPositionCommand(armLowbarCrossPosition, 2000));
    add(new DriveTranslateCommand(translateAcrossLowbar, 3000));
    add(new DriveRotateCommand(rotateToIntermediateMove, 1000));
    add(new DriveTranslateCommand(translateToIntermediateMove, 3000));
    add(new DriveRotateCommand(rotateToHighGoal, 1000));
    add(new ArmMoveToPositionCommand(Constants.ARM_POSITION_SHOOT_LOW, 2000));
    add(new ShootAlignHighGoalCommand(0));
//    add(new MultiPrepareForShot(100 - translateToHighGoal));
//    add(DriveFromDefenseToHighGoalGenerator.DEFENSE_1
//      .getDriveToTargetGoalSequence(HighGoalTargetPosition.LEFT, 15));
//    add(new DriveRotateCommand(angle - sensorInput.getNavXHeading(), 1.0, 2000));
//    add(new DriveTranslateCommand(straightDistance, 1.0, 5.0));
//    add(new DriveRotateCommand(
//      -angle + targetPosition.getOffsetAngle(), 1.0, 1500));
  }

  @Override
  protected void destruct() {
    super.destruct();

    setFinished();
  }
}
