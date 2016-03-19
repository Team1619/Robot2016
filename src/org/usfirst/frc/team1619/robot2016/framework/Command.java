package org.usfirst.frc.team1619.robot2016.framework;

import org.usfirst.frc.team1619.robot2016.IO.DriverInput;
import org.usfirst.frc.team1619.robot2016.IO.RobotOutput;
import org.usfirst.frc.team1619.robot2016.IO.SensorInput;
import org.usfirst.frc.team1619.robot2016.util.GenericTimer;

public abstract class Command {

  private int timeout;
  private GenericTimer timer;

  private boolean finished;

  protected DriverInput driverInput;
  protected RobotOutput robotOutput;
  protected SensorInput sensorInput;

  protected Command() {
    this(0);
  }

  protected Command(int timeout) {
    this.timeout = timeout;
    timer = new GenericTimer();

    driverInput = DriverInput.getInstance();
    robotOutput = RobotOutput.getInstance();
    sensorInput = SensorInput.getInstance();
  }

  public void initializeCommand() {
    if (timeout > 0) {
      timer.setDuration(timeout);
      timer.start();
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

  public abstract void pause();

  public abstract void destruct();

  public boolean getFinished() {
    return finished;
  }

  protected void setFinished() {
    finished = true;
  }

}
