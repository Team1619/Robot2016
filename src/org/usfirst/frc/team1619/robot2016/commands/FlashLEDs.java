package org.usfirst.frc.team1619.robot2016.commands;

import org.usfirst.frc.team1619.robot2016.framework.Command;

public abstract class FlashLEDs extends Command {

  private int flashing;
  private int period;

  protected FlashLEDs(int period) {
    this.period = period;
  }

  @Override
  protected void initialize() {
    flashing = 0;
  }

  @Override
  protected void update() {
    if (flashing < period) {
      setLEDs(true);
    }
    else {
      setLEDs(false);
    }

    flashing = (flashing + 1) % (period * 2);
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

}
