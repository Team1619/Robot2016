package org.usfirst.frc.team1619.robot2016.states;

import org.usfirst.frc.team1619.robot2016.SubsystemID;
import org.usfirst.frc.team1619.robot2016.commands.ArmZeroCommand;
import org.usfirst.frc.team1619.robot2016.commands.CrossRockWallCommand;
import org.usfirst.frc.team1619.robot2016.commands.DriveRotateToAbsoluteCommand;
import org.usfirst.frc.team1619.robot2016.commands.HighGoalToLaneCommand;
import org.usfirst.frc.team1619.robot2016.commands.LaneToHighGoalCommand;
import org.usfirst.frc.team1619.robot2016.commands.ShootCommand;
import org.usfirst.frc.team1619.robot2016.commands.ShootManualCommand;
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

    CommandSequence driveAndSpool = new CommandSequence();
    driveAndSpool.addPassive(new ShootManualCommand(0.625, 0));
    driveAndSpool.add(new LaneToHighGoalCommand(3, 15.0));

    add(new ArmZeroCommand());
    add(new CrossRockWallCommand(40.0));
    add(new DriveRotateToAbsoluteCommand(angle, 1000));
    add(driveAndSpool);
    add(new ShootCommand(5000, 2000));
    add(new HighGoalToLaneCommand(angle, 3, 5.0));
    add(new CrossRockWallCommand(0.0));
  }

  @Override
  protected void destruct() {
    super.destruct();

    setFinished();
  }
}
