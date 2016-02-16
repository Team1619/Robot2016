package org.usfirst.frc.team1619.robot2016.states;

import org.usfirst.frc.team1619.robot2016.commands.DriveCommand;
import org.usfirst.frc.team1619.robot2016.commands.ShootCommand;
import org.usfirst.frc.team1619.robot2016.subsystems.SubsystemID;
import org.usfirst.frc.team1619.robot2016.util.AutoMode;

public class AutoDrive extends State {

  private static SubsystemID[] subsystems;

  private AutoMode drive;

  static {
    subsystems =
      new SubsystemID[] {SubsystemID.DRIVE_TRAIN, SubsystemID.SHOOTER_INTAKE};
  }

  public AutoDrive() {
    super(subsystems);
  }

  @Override
  protected void setup() {
    drive = new AutoMode();
    drive.add(new DriveCommand());
    drive.add(new ShootCommand());
    drive.initialize();
  }

  @Override
  protected void execute() {
    drive.update();

    if (drive.getFinished()) {
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
