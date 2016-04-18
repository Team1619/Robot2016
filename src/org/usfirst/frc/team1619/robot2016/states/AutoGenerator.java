package org.usfirst.frc.team1619.robot2016.states;

import org.usfirst.frc.team1619.robot2016.SubsystemID;
import org.usfirst.frc.team1619.robot2016.framework.SequencerState;

public class AutoGenerator extends SequencerState {

  private static SubsystemID[] subsystems;

  static {
    subsystems = new SubsystemID[] {SubsystemID.INTAKE, SubsystemID.SHOOTER,
      SubsystemID.DRIVE_TRAIN, SubsystemID.UTILITY_ARM};
  }

  public AutoGenerator() {
    super(subsystems);
  }

  @Override
  protected void addCommands() {
    int lane = smashBoard.getAutoLane();
    int defense = smashBoard.getAutoDefense();

    if (!(lane > 0 && defense > 0)) {
      return;
    }
    
    switch (defense) {
      case 1: // Low bar
        break;
      case 2: // Rock wall
        break;
      case 3: // Rough terrain
        break;
      case 4: // Moat
        break;
      case 5: // Ramparts
        break;
      case 6: // Chevalle de frise
        break;
    }
  }

  @Override
  protected void destruct() {
    super.destruct();

    setFinished();
  }

}
