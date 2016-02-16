package org.usfirst.frc.team1619.robot2016.util;

public interface AutoCommand {

  public void initialize();
  
  public void update();
  
  public void destruct();
  
  public boolean finished();
  
}
