package org.usfirst.frc.team1619.robot2016.commands;

public abstract class ControlFrontLEDs extends ControlLEDs {

  @Override
  protected void setLEDs(boolean on) {
    sensorInput.setFrontLEDs(on);
  }

}
