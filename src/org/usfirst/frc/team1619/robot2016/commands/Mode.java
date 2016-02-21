package org.usfirst.frc.team1619.robot2016.commands;

import java.util.LinkedList;
import java.util.Queue;

public class Mode {

  private Queue<Command> commands;
  private Command currentCommand;
  private boolean finished;

  public Mode() {
    commands = new LinkedList<>();
    finished = false;
  }

  public void initialize() {
    if (commands.isEmpty()) {
      finished = true;
      return;
    }

    currentCommand = commands.poll();
    currentCommand.initialize();
  }

  public void update() {
    if (finished) {
      return;
    }

    currentCommand.update();

    if (currentCommand.finished()) {
      currentCommand.destruct();

      if (commands.isEmpty()) {
        finished = true;
      }
      else {
        currentCommand = commands.poll();
        currentCommand.initialize();
      }
    }
  }

  public void add(Command command) {
    commands.add(command);
  }

  public void destruct() {
    if (currentCommand != null) {
      currentCommand.destruct();
    }

    currentCommand = null;
  }

  public boolean getFinished() {
    return finished;
  }

}