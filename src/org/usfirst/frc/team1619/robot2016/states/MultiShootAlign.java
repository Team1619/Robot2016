package org.usfirst.frc.team1619.robot2016.states;

import org.usfirst.frc.team1619.robot2016.Constants;
import org.usfirst.frc.team1619.robot2016.SubsystemID;
import org.usfirst.frc.team1619.robot2016.commands.ShootAlignHighGoalCommand;
import org.usfirst.frc.team1619.robot2016.framework.SequencerState;

public class MultiShootAlign extends SequencerState {

  private static SubsystemID[] subsystems;

  protected int speedTarget;

  static {
    subsystems = new SubsystemID[] {SubsystemID.DRIVE_TRAIN,
      SubsystemID.UTILITY_ARM, SubsystemID.INTAKE, SubsystemID.SHOOTER};
  }

  protected ShootAlignHighGoalCommand shootAlignHighGoal;
  
  public MultiShootAlign() {
    super(subsystems);
  }

  protected int getSpeedTarget() {
    return Constants.SHOOTER_SHOOT_SPEED_TARGET;
  }

  @Override
  public boolean isReadyForActive() {
    return driverInput.getDriverButton(Constants.DRIVER_BUTTON_SHOOT_MID);
  }

  @Override
  protected void addCommands() {
    shootAlignHighGoal = new ShootAlignHighGoalCommand(getSpeedTarget(), 0);

    add(shootAlignHighGoal);
  }

}
