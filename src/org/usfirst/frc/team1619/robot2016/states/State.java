package org.usfirst.frc.team1619.robot2016.states;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.usfirst.frc.team1619.robot2016.subsystems.SubsystemID;

public abstract class State {

  private static Set<State> states;

  private Set<SubsystemID> subsystems;
  private Set<SubsystemID> activeSubsystems;

  private boolean finished;

  static {
    states = new HashSet<>();
  }

  public static void updateAll() {
    for (State state : states) {
      state.update();
    }
  }

  protected State(SubsystemID[] subsystems) {
    states.add(this);

    this.subsystems = Arrays.stream(subsystems).collect(Collectors.toSet());
    activeSubsystems = new HashSet<>();

    finished = false;
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
      destroy();
      finished = false;
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

  protected void setFinished() {
    finished = true;
  }

}
