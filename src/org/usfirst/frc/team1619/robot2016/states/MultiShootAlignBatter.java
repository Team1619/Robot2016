package org.usfirst.frc.team1619.robot2016.states;

import org.usfirst.frc.team1619.robot2016.Constants;
import org.usfirst.frc.team1619.robot2016.commands.ShootAlignHighGoalBatterCommand;

public class MultiShootAlignBatter extends MultiShootAlign {

  protected ShootAlignHighGoalBatterCommand shootAlignHighGoalBatter;

  @Override
  protected void addCommands() {
    shootAlignHighGoalBatter = new ShootAlignHighGoalBatterCommand(getSpeedTarget(), 0);

    add(shootAlignHighGoalBatter);
  }

  @Override
  protected int getSpeedTarget() {
    return Constants.SHOOTER_SHOOT_SPEED_TARGET_BATTER;
  }

  @Override
  public boolean isReadyForActive() {
    return driverInput.getDriverButton(Constants.DRIVER_BUTTON_SHOOT_BATTER);
  }
}
