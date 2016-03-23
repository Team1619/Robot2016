package org.usfirst.frc.team1619.robot2016.framework;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CommandSequence extends Command {

  private Queue<Command> commands;
  private List<Command> passiveCommands;
  private Command currentCommand;
  
  public CommandSequence() {
    commands = new LinkedList<>();
    passiveCommands = new ArrayList<>();
  }
  
  public void add(Command command) {
    commands.add(command);
  }
  
  public void addPassive(Command command) {
    passiveCommands.add(command);
  }
  
  @Override
  protected void initialize() {
    if (commands.isEmpty()) {
      setFinished();
      return;
    }
    
    for (Command passiveCommand : passiveCommands) {
      passiveCommand.initializeCommand();
    }
    
    currentCommand = commands.poll();
    currentCommand.initializeCommand();
  }

  @Override
  protected void update() {
    if (getFinished()) {
      return;
    }
    
    for (Command passiveCommand : passiveCommands) {
      passiveCommand.updateCommand();
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
    if (currentCommand != null) {
      currentCommand.pause();
    }
    
    for (Command passiveCommand : passiveCommands) {
      passiveCommand.pause();
    }
  }

  @Override
  public void destruct() {
    if (currentCommand != null) {
      currentCommand.destruct();
    }
    
    for (Command passiveCommand : passiveCommands) {
      passiveCommand.destruct();
    }
    
    currentCommand = null;
    commands.clear();
  }
  
}
