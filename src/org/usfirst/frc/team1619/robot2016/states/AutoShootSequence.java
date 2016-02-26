package org.usfirst.frc.team1619.robot2016.states;

import org.usfirst.frc.team1619.robot2016.SubsystemID;
import org.usfirst.frc.team1619.robot2016.commands.DriveForDistanceCommand;
import org.usfirst.frc.team1619.robot2016.commands.PauseCommand;
import org.usfirst.frc.team1619.robot2016.commands.ShootCommand;
import org.usfirst.frc.team1619.robot2016.framework.CommandGroup;
import org.usfirst.frc.team1619.robot2016.framework.CommandSequence;
import org.usfirst.frc.team1619.robot2016.framework.SequencerState;

public class AutoShootSequence extends SequencerState {

  private static SubsystemID[] subsystems;

  static {
    subsystems = new SubsystemID[] {SubsystemID.INTAKE, SubsystemID.SHOOTER};
  }

  public AutoShootSequence() {
    super(subsystems);
  }

  @Override
  protected void addCommands() {
    CommandSequence waitAndShoot = new CommandSequence();
    waitAndShoot.add(new PauseCommand(2500));
    waitAndShoot.add(new ShootCommand(90000, 2500));

    CommandGroup driveAndShoot = new CommandGroup();
    driveAndShoot.add(new DriveForDistanceCommand(25.0));
    driveAndShoot.add(waitAndShoot);

    add(driveAndShoot);
  }

  @Override
  protected void destruct() {
    super.destruct();

    setFinished();
  }

}
