package org.usfirst.frc.team1619.robot2016.states;

import org.usfirst.frc.team1619.robot2016.Constants;
import org.usfirst.frc.team1619.robot2016.SubsystemID;
import org.usfirst.frc.team1619.robot2016.commands.ShootAlignHighGoalCommand;
import org.usfirst.frc.team1619.robot2016.framework.SequencerState;

public class MultiShootAlign extends SequencerState {

  private static SubsystemID[] subsystems;

  static {
    subsystems = new SubsystemID[] {SubsystemID.DRIVE_TRAIN,
      SubsystemID.UTILITY_ARM, SubsystemID.INTAKE, SubsystemID.SHOOTER};
  }

  private ShootAlignHighGoalCommand shootAlignHighGoal;
  
  public MultiShootAlign() {
    super(subsystems);
  }

  @Override
  public boolean isReadyForActive() {
    return driverInput.getDriverButton(Constants.DRIVER_BUTTON_SHOOT);
  }

  @Override
  protected void addCommands() {
    shootAlignHighGoal = new ShootAlignHighGoalCommand(0);

    add(shootAlignHighGoal);
  }

}
