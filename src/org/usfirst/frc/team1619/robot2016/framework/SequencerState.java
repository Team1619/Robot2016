package org.usfirst.frc.team1619.robot2016.framework;

import java.util.LinkedList;
import java.util.Queue;

import org.usfirst.frc.team1619.robot2016.SubsystemID;
import org.usfirst.frc.team1619.robot2016.commands.Command;

public abstract class SequencerState extends State {

  private Queue<Command> commands;
  private Command currentCommand;

  public SequencerState(SubsystemID[] subsystems) {
    super(subsystems);

    commands = new LinkedList<>();
  }

  @Override
  protected void setup() {
    addCommands();
    
    if (commands.isEmpty()) {
      setFinished();
      return;
    }

    currentCommand = commands.poll();
    currentCommand.initialize();
  }

  @Override
  protected void execute() {
    if (getFinished()) {
      return;
    }

    currentCommand.update();

    if (currentCommand.finished()) {
      currentCommand.destruct();

      if (commands.isEmpty()) {
        setFinished();
      }
      else {
        currentCommand = commands.poll();
        currentCommand.initialize();
      }
    }
  }
  
  @Override
  protected void destroy() {
    if (currentCommand != null) {
      currentCommand.destruct();
    }
    
    currentCommand = null;
  }

  @Override
  public boolean isReadyForActive() {
    return !getFinished();
  }
  
  public void add(Command command) {
    commands.add(command);
  }

  /**
   * Call add() for each command in this sequencer
   */
  protected abstract void addCommands();
  
}