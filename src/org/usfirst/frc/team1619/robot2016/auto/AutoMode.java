package org.usfirst.frc.team1619.robot2016.auto;

import java.util.ArrayList;

public class AutoMode {
  //Declare
  private ArrayList<AutoCommand> autoCommands;
  private AutoCommand currentCommand;
  private int currentIndex;
  private boolean finished;

  AutoMode() {
    //Init
    autoCommands = new ArrayList<AutoCommand>();
    currentIndex = 0;
    finished = false;
  }

  public void init() {
    currentIndex = 0;
    if(autoCommands.size() <= 0) {
      finished = true;
    }
    else {
      currentCommand = autoCommands.get(0);
      currentCommand.init();
    }
  }

  /**
   * Sequentially updates commands in autoCommands list
   */
  public void update() {
    //logic for updating autonomous commands
    if(!finished) {
      //update the current command
      currentCommand.update();
      //Has the command finished executing?
      if(currentCommand.finished()) {
        //Was this the last command?
        if(autoCommands.size() <= currentIndex + 1) {
          finished = true;
        }
        //If not, make the next command the current one and initialize it
        else {
          currentIndex++;
          currentCommand = autoCommands.get(currentIndex);
          currentCommand.init();
        }
      }
    }
  }

  /**
   * Add a command to this mode.
   * Commands will be executed in the order that they're added. If you want to
   * add parallel commands, you should create a command with both commands 
   * inside it.
   * @param command Command to be executed.
   */
  public void add(AutoCommand command) {
    autoCommands.add(command);
  }
}
