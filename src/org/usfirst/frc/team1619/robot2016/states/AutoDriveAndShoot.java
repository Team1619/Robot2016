package org.usfirst.frc.team1619.robot2016.states;

import org.usfirst.frc.team1619.robot2016.SubsystemID;
import org.usfirst.frc.team1619.robot2016.commands.ArmToTopCommand;
import org.usfirst.frc.team1619.robot2016.commands.DriveStraightForDistance;
import org.usfirst.frc.team1619.robot2016.commands.Mode;
import org.usfirst.frc.team1619.robot2016.commands.RotateByAngle;
import org.usfirst.frc.team1619.robot2016.commands.ShootCommand;
import org.usfirst.frc.team1619.robot2016.framework.State;

public class AutoDriveAndShoot extends State {

  private static SubsystemID[] subsystems;

  private Mode driveAndShoot;

  static {
    subsystems = new SubsystemID[] {SubsystemID.DRIVE_TRAIN,
      SubsystemID.SHOOTER, SubsystemID.INTAKE, SubsystemID.UTILITY_ARM};
  }

  public AutoDriveAndShoot() {
    super(subsystems);
  }

  @Override
  protected void setup() {
    driveAndShoot = new Mode();
    driveAndShoot.add(new DriveStraightForDistance(100.0));
    driveAndShoot.add(new RotateByAngle(49.0));
    driveAndShoot.add(new DriveStraightForDistance(20.0));
    driveAndShoot.add(new ArmToTopCommand());
    driveAndShoot.add(new ShootCommand());
    driveAndShoot.initialize();
  }

  @Override
  protected void execute() {
    driveAndShoot.update();

    if (driveAndShoot.getFinished()) {
      setFinished();
    }
  }

  @Override
  protected void pause() {
  }

  @Override
  protected void destroy() {
    driveAndShoot.destruct();
    setFinished();
  }

  @Override
  public boolean isReadyForActive() {
    return !getFinished();
  }

}
