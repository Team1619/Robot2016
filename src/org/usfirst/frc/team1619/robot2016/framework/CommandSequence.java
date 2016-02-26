package org.usfirst.frc.team1619.robot2016.framework;

import java.util.LinkedList;
import java.util.Queue;

public class CommandSequence extends Command {

  private Queue<Command> commands;
  private Command currentCommand;
  
  public CommandSequence() {
    commands = new LinkedList<>();
  }
  
  public void add(Command command) {
    commands.add(command);
  }
  
  @Override
  protected void initialize() {
    if (commands.isEmpty()) {
      setFinished();
      return;
    }
    
    currentCommand = commands.poll();
    currentCommand.initializeCommand();
  }

  @Override
  protected void update() {
    if (getFinished()) {
      return;
    }
    
    currentCommand.updateCommand();
    
    if (currentCommand.getFinished()) {
      currentCommand.destruct();
      
      if (commands.isEmpty()) {
        setFinished();
      }
      else {
        currentCommand = commands.poll();
        currentCommand.initializeCommand();
      }
    }
  }

  @Override
  public void pause() {
    currentCommand.pause();
  }

  @Override
  public void destruct() {
    if (currentCommand != null) {
      currentCommand.destruct();
    }
    
    currentCommand = null;
    commands.clear();
  }
  
}
