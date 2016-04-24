package org.usfirst.frc.team1619.robot2016.commands;

import org.usfirst.frc.team1619.robot2016.framework.Command;

public abstract class ControlLEDs extends Command {

  @Override
  protected void initialize() {
  }

  @Override
  protected void update() {
    updateLEDs();
  }

  @Override
  public void pause() {
    setLEDs(false);
  }

  @Override
  public void destruct() {
    setLEDs(false);
  }

  protected abstract void setLEDs(boolean on);

  protected abstract void updateLEDs();
}
