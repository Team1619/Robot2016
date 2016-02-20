package org.usfirst.frc.team1619.robot2016.states;

import org.usfirst.frc.team1619.robot2016.commands.DriveStraightCommand;
import org.usfirst.frc.team1619.robot2016.commands.Mode;
import org.usfirst.frc.team1619.robot2016.commands.ReorientToGoalCommand;
import org.usfirst.frc.team1619.robot2016.commands.ShootCommand;
import org.usfirst.frc.team1619.robot2016.subsystems.SubsystemID;

public class AutoDriveAndShoot extends State {

  private static SubsystemID[] subsystems;

  private Mode driveAndShoot;

  static {
    subsystems = new SubsystemID[] {SubsystemID.DRIVE_TRAIN,
      SubsystemID.SHOOTER_INTAKE, SubsystemID.UTILITY_ARM};
  }

  public AutoDriveAndShoot() {
    super(subsystems);
  }

  @Override
  protected void setup() {
    driveAndShoot = new Mode();
//    driveAndShoot.add(new DriveStraightCommand());
    driveAndShoot.add(new ReorientToGoalCommand());
//    driveAndShoot.add(new ShootCommand());
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
    setFinished();
  }

  @Override
  public boolean isReadyForActive() {
    return !getFinished();
  }

}
