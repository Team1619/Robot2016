package org.usfirst.frc.team1619.robot2016.subsystems;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.usfirst.frc.team1619.robot2016.states.State;

public class Subsystem {

  private static Set<Subsystem> subsystems;

  private SubsystemID subsystemID;

  private List<State> states;
  private State activeState;

  static {
    subsystems = new HashSet<>();
  }

  public static void updateAll() {
    for (Subsystem subsystem : subsystems) {
      subsystem.update();
    }
  }

  public static void resetAll() {
    for (Subsystem subsystem : subsystems) {
      subsystem.reset();
    }
  }

  public Subsystem(SubsystemID subsystemID) {
    subsystems.add(this);

    this.subsystemID = subsystemID;

    states = new ArrayList<>();
    activeState = null;
  }

  protected void update() {
    State nextState = getActiveState();
    if (!(activeState != null && activeState.equals(nextState))) {
      updateActiveState(nextState);
    }
  }

  public void addState(State state) {
    states.add(state);
  }

  public void reset() {
    if (activeState != null) {
      activeState.destruct(subsystemID);
      activeState = null;
    }

    states.clear();
  }

  private void updateActiveState(State newState) {
    if (activeState != null) {
      activeState.destruct(subsystemID);
    }
    activeState = newState;
    if (activeState != null) {
      activeState.initialize(subsystemID);
    }
  }

  private State getActiveState() {
    for (State state : states) {
      if (state.isReadyForActive()) {
        return state;
      }
    }

    return null;
  }

}
