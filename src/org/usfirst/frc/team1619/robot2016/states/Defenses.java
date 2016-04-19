package org.usfirst.frc.team1619.robot2016.states;

import org.usfirst.frc.team1619.robot2016.Constants;

public enum Defenses {
    LOW_BAR(Constants.AUTO_RETURN_OFFSET_LOW_BAR), 
    ROCK_WALL(Constants.AUTO_RETURN_OFFSET_ROCK_WALL), 
    ROUGH_TERRAIN(Constants.AUTO_RETURN_OFFSET_ROUGH_TERRAIN), 
    MOAT(Constants.AUTO_RETURN_OFFSET_MOAT), 
    RAMPARTS(Constants.AUTO_RETURN_OFFSET_RAMPARTS), 
    CHEVALLE_DE_FRISE(Constants.AUTO_RETURN_OFFSET_CHEVALLE);

  private double returnOffset;
  
  Defenses(double returnOffset) {
    this.returnOffset = returnOffset;
  }

  public double getReturnOffset() {
    return returnOffset;
  }
  
}
