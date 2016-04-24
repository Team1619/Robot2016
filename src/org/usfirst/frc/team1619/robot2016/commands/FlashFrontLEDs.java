package org.usfirst.frc.team1619.robot2016.commands;

public class FlashFrontLEDs extends FlashLEDs {

  public FlashFrontLEDs(int period) {
    super(period);
  }

  @Override
  protected void setLEDs(boolean on) {
    sensorInput.setFrontLEDs(on);
  }

}
