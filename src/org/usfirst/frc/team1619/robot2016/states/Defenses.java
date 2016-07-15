package org.usfirst.frc.team1619.robot2016.states;

import org.usfirst.frc.team1619.robot2016.Constants;

public enum Defenses {
  LOW_BAR(-5.0, Constants.AUTO_RETURN_OFFSET_LOW_BAR), CHEVALLE_DE_FRISE(-7.5,
    Constants.AUTO_RETURN_OFFSET_CHEVALLE), RAMPARTS(0.0,
      Constants.AUTO_RETURN_OFFSET_RAMPARTS), MOAT(0.0,
        Constants.AUTO_RETURN_OFFSET_MOAT), ROUGH_TERRAIN(0.0,
          Constants.AUTO_RETURN_OFFSET_ROUGH_TERRAIN), ROCK_WALL(0.0,
            Constants.AUTO_RETURN_OFFSET_ROCK_WALL), TWO_BALL(0.0, 0.0);

  private double horizontalOffset;
  private double returnOffset;

  Defenses(double horizontalOffset, double returnOffset) {
    this.horizontalOffset = horizontalOffset;
    this.returnOffset = returnOffset;
  }

  public double getHorizontalOffset() {
    return horizontalOffset;
  }

  public double getReturnOffset() {
    return returnOffset;
  }

}
