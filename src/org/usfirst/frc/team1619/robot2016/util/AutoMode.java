package org.usfirst.frc.team1619.robot2016.util;

import java.util.LinkedList;
import java.util.Queue;

public class AutoMode {

  private Queue<AutoCommand> autoCommands;
  private AutoCommand currentCommand;
  private boolean finished;

  public AutoMode() {
    autoCommands = new LinkedList<>();
    finished = false;
  }

  public void initialize() {
    if (autoCommands.isEmpty()) {
      finished = true;
      return;
    }

    currentCommand = autoCommands.poll();
    currentCommand.initialize();
  }

  public void update() {
    if (finished) {
      return;
    }

    currentCommand.update();

    if (currentCommand.finished()) {
      currentCommand.destruct();

      if (autoCommands.isEmpty()) {
        finished = true;
      }
      else {
        currentCommand = autoCommands.poll();
        currentCommand.initialize();
      }
    }
  }

  public void add(AutoCommand command) {
    autoCommands.add(command);
  }

  public boolean getFinished() {
    return finished;
  }

}
