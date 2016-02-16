package org.usfirst.frc.team1619.robot2016.commands;

import org.usfirst.frc.team1619.robot2016.IO.RobotOutput;
import org.usfirst.frc.team1619.robot2016.util.AutoCommand;

public class DriveCommand implements AutoCommand {

  private RobotOutput robotOutput;

  private int time;

  @Override
  public void initialize() {
    robotOutput = RobotOutput.getInstance();
    time = 0;
  }

  @Override
  public void update() {
    robotOutput.arcadeDrive(-0.5, 0.0);
    time++;
  }

  @Override
  public void destruct() {
    robotOutput.arcadeDrive(0.0, 0.0);
  }

  @Override
  public boolean finished() {
    return time > 100;
  }

}
