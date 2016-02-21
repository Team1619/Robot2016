package org.usfirst.frc.team1619.robot2016.framework;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.usfirst.frc.team1619.robot2016.SubsystemID;
import org.usfirst.frc.team1619.robot2016.IO.DriverInput;
import org.usfirst.frc.team1619.robot2016.IO.RobotOutput;
import org.usfirst.frc.team1619.robot2016.IO.SensorInput;
import org.usfirst.frc.team1619.robot2016.IO.SmashBoard;

public abstract class State {

  private static Set<State> states;

  private Set<SubsystemID> subsystems;
  private Set<SubsystemID> activeSubsystems;

  private boolean finished;

  protected RobotState robotState;
  protected DriverInput driverInput;
  protected RobotOutput robotOutput;
  protected SensorInput sensorInput;
  protected SmashBoard smashBoard;

  static {
    states = new HashSet<>();
  }

  public static void updateAll() {
    for (State state : states) {
      state.update();
    }
  }

  public static void resetAll() {
    states.clear();
  }

  protected State(SubsystemID[] subsystems) {
    states.add(this);

    this.subsystems = Arrays.stream(subsystems).collect(Collectors.toSet());
    activeSubsystems = new HashSet<>();

    finished = false;

    robotState = RobotState.getInstance();
    driverInput = DriverInput.getInstance();
    robotOutput = RobotOutput.getInstance();
    sensorInput = SensorInput.getInstance();
    smashBoard = SmashBoard.getInstance();
  }

  public void initialize(SubsystemID subsystemID) {
    activeSubsystems.add(subsystemID);

    if (activeSubsystems.containsAll(subsystems)) {
      setup();
    }
  }

  protected abstract void setup();

  protected void update() {
    if (activeSubsystems.containsAll(subsystems)) {
      execute();
    }
  }

  protected abstract void execute();

  public void destruct(SubsystemID subsystemID) {
    activeSubsystems.remove(subsystemID);

    if (activeSubsystems.size() == 0) {
      finished = false;
      destroy();
    }
    else {
      pause();
    }
  }

  protected abstract void pause();

  protected abstract void destroy();

  public abstract boolean isReadyForActive();

  public boolean isReadyForDestruct() {
    return finished;
  }

  protected boolean getFinished() {
    return finished;
  }

  protected void setFinished() {
    finished = true;
  }

}
