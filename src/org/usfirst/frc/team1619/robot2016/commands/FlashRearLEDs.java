package org.usfirst.frc.team1619.robot2016.commands;

public class FlashRearLEDs extends FlashLEDs {

  public FlashRearLEDs(int period) {
    super(period);
  }

  @Override
  protected void setLEDs(boolean on) {
    sensorInput.setRearLEDs(on);
  }

}
