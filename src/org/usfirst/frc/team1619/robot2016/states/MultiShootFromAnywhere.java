package org.usfirst.frc.team1619.robot2016.states;

import org.usfirst.frc.team1619.robot2016.SubsystemID;
import org.usfirst.frc.team1619.robot2016.commands.ShootFromAnywhereCommand;
import org.usfirst.frc.team1619.robot2016.framework.SequencerState;

public class MultiShootFromAnywhere extends SequencerState {

  private int driverButton;
  private boolean left;

  private static SubsystemID[] subsystems;

  static {
    subsystems = new SubsystemID[] {SubsystemID.DRIVE_TRAIN,
      SubsystemID.UTILITY_ARM, SubsystemID.INTAKE, SubsystemID.SHOOTER};
  }

  public MultiShootFromAnywhere(int driverButton, boolean left) {
    super(subsystems);

    this.driverButton = driverButton;
    this.left = left;
  }

  @Override
  protected void addCommands() {
    add(new ShootFromAnywhereCommand(left));
  }

  @Override
  public boolean isReadyForActive() {
    return driverInput.getDriverButton(driverButton);
  }

}
