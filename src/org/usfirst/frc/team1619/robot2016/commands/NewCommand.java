package org.usfirst.frc.team1619.robot2016.commands;

import org.usfirst.frc.team1619.robot2016.util.GenericTimer;

public abstract class NewCommand {

  private int timeout;
  private GenericTimer timer;

  private boolean finished;

  protected NewCommand() {
    this(0);
  }

  protected NewCommand(int timeout) {
    this.timeout = timeout;
    timer = new GenericTimer();
  }

  public void initializeCommand() {
    if (timeout > 0) {
      timer.reset();
      timer.setDuration(timeout);
    }

    finished = false;

    initialize();
  }

  protected abstract void initialize();

  public void updateCommand() {
    if (timeout > 0 && timer.isFinished()) {
      handleTimeout();
    }
    else {
      update();
    }
  }

  protected abstract void update();

  protected void handleTimeout() {
    setFinished();
  }

  protected abstract void destruct();

  public boolean getFinished() {
    return finished;
  }

  protected void setFinished() {
    finished = true;
  }

}
