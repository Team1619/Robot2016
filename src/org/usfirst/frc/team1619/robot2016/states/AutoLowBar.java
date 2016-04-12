package org.usfirst.frc.team1619.robot2016.states;

import org.usfirst.frc.team1619.robot2016.Constants;
import org.usfirst.frc.team1619.robot2016.SubsystemID;
import org.usfirst.frc.team1619.robot2016.commands.ArmMoveToPositionCommand;
import org.usfirst.frc.team1619.robot2016.commands.ArmZeroCommand;
import org.usfirst.frc.team1619.robot2016.commands.CrossRockWallCommand;
import org.usfirst.frc.team1619.robot2016.commands.DriveRotateToAbsoluteCommand;
import org.usfirst.frc.team1619.robot2016.commands.DriveRotateToHighGoalCommand;
import org.usfirst.frc.team1619.robot2016.commands.HighGoalToLaneCommand;
import org.usfirst.frc.team1619.robot2016.commands.LaneToHighGoalCommand;
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
    double angle = sensorInput.getNavXHeading();

    CommandGroup alignToHighGoal = new CommandGroup();
    alignToHighGoal.add(
      new ArmMoveToPositionCommand(Constants.ARM_POSITION_SHOOT_AUTO, 1500));
    alignToHighGoal.add(new DriveRotateToHighGoalCommand());

    CommandSequence driveAndSpool = new CommandSequence();
    driveAndSpool
      .addPassive(new ShootManualCommand(Constants.SHOOTER_SPOOL_UP_SPEED, 0));
    driveAndSpool.add(new LaneToHighGoalCommand(4, 15.0));
    driveAndSpool.add(alignToHighGoal);

    add(new ArmZeroCommand());
    add(new CrossRockWallCommand(40.0));
    add(new DriveRotateToAbsoluteCommand(angle, 1000));
    add(driveAndSpool);
    add(new ShootCommand(Constants.SHOOTER_SHOOT_SPEED_TARGET_AUTO, 2000));
    add(new HighGoalToLaneCommand(angle, 4, 5.0));
    add(new CrossRockWallCommand(0.0));
  }

  @Override
  protected void destruct() {
    super.destruct();

    setFinished();
  }
}
