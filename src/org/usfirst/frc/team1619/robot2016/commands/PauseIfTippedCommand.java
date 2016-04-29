package org.usfirst.frc.team1619.robot2016.commands;

public class PauseIfTippedCommand extends PauseCommand {

  public PauseIfTippedCommand(int timeout) {
    super(timeout);
  }

  @Override
  public void initialize() {
    if (sensorInput.getNavXPitch() == 0) {
    }
  }
  
}
