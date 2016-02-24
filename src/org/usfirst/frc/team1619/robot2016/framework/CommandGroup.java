package org.usfirst.frc.team1619.robot2016.framework;

import java.util.HashSet;
import java.util.Set;

public class CommandGroup extends Command {

  private Set<Command> commands;

  public CommandGroup() {
    this(0);
  }

  public CommandGroup(int timeout) {
    super(timeout);

    commands = new HashSet<>();
  }

  public void add(Command command) {
    commands.add(command);
  }

  @Override
  protected void initialize() {
    for (Command command : commands) {
      command.initializeCommand();
    }
  }

  @Override
  protected void update() {
    for (Command command : commands) {
      command.updateCommand();

      if (command.getFinished()) {
        commands.remove(command);

        if (commands.isEmpty()) {
          setFinished();
        }
      }
    }
  }

  @Override
  public void pause() {
    for (Command command : commands) {
      command.pause();
    }
  }

  @Override
  public void destruct() {
    for (Command command : commands) {
      command.destruct();
    }
  }

}
