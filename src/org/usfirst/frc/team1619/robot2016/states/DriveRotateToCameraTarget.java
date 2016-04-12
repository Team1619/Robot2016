package org.usfirst.frc.team1619.robot2016.states;

import org.usfirst.frc.team1619.robot2016.Constants;
import org.usfirst.frc.team1619.robot2016.SubsystemID;
import org.usfirst.frc.team1619.robot2016.commands.DriveRotateToHighGoalCommand;
import org.usfirst.frc.team1619.robot2016.framework.SequencerState;

public class DriveRotateToCameraTarget extends SequencerState {

  private static SubsystemID[] subsystems;
  
  static {
    subsystems = new SubsystemID[] {SubsystemID.DRIVE_TRAIN};
  }

  public DriveRotateToCameraTarget() {
    super(subsystems);
  }

  
  @Override
  public boolean isReadyForActive() {
    return driverInput
      .getDriverButton(Constants.DRIVER_BUTTON_DRIVE_TURN_TO_CAMERA);
  }

  @Override
  protected void addCommands() {
    add(new DriveRotateToHighGoalCommand());
  }

}