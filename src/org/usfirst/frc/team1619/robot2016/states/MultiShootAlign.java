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
  private int readyForActiveButton;
  private int speedTarget;
  private double armPosition;
  
  public MultiShootAlign(int driverButton, int speedTarget) {
    this(driverButton, speedTarget, Constants.ARM_POSITION_SHOOT_OUTERWORKS);
  }

  public MultiShootAlign(int driverButton, int speedTarget, double armPosition) {
    super(subsystems);
    
    readyForActiveButton = driverButton;
    this.speedTarget = speedTarget;
    this.armPosition = armPosition;
  }
  
  @Override
  public boolean isReadyForActive() {
    return driverInput.getDriverButton(readyForActiveButton);
  }

  @Override
  protected void addCommands() {
    shootAlignHighGoal = new ShootAlignHighGoalCommand(speedTarget, armPosition, 0);

    add(shootAlignHighGoal);
  }

}
