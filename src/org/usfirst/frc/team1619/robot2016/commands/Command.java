package org.usfirst.frc.team1619.robot2016.commands;

public interface Command {

  public void initialize();

  public void update();

  public void destruct();

  public boolean finished();

}
