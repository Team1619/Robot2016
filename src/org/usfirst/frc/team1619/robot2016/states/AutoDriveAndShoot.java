package org.usfirst.frc.team1619.robot2016.states;

import org.usfirst.frc.team1619.robot2016.SubsystemID;
import org.usfirst.frc.team1619.robot2016.commands.ArmToTopCommand;
import org.usfirst.frc.team1619.robot2016.commands.DriveStraightForDistance;
import org.usfirst.frc.team1619.robot2016.commands.RotateByAngle;
import org.usfirst.frc.team1619.robot2016.commands.ShootCommand;
import org.usfirst.frc.team1619.robot2016.framework.SequencerState;
import org.usfirst.frc.team1619.robot2016.framework.State;

public class AutoDriveAndShoot extends SequencerState {

  private static SubsystemID[] subsystems;

  static {
    subsystems = new SubsystemID[] {SubsystemID.DRIVE_TRAIN,
      SubsystemID.SHOOTER, SubsystemID.INTAKE, SubsystemID.UTILITY_ARM};
  }

  public AutoDriveAndShoot() {
    super(subsystems);
  }

  @Override
  protected void addCommands() {
    add(new DriveStraightForDistance(100.0));
    add(new RotateByAngle(49.0));
    add(new DriveStraightForDistance(20.0));
    add(new ArmToTopCommand());
    add(new ShootCommand());
  }

  @Override
  protected void pause() {
  }
  
}
