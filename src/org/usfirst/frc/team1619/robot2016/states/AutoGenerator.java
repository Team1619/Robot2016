package org.usfirst.frc.team1619.robot2016.states;

import org.usfirst.frc.team1619.robot2016.Constants;
import org.usfirst.frc.team1619.robot2016.SubsystemID;
import org.usfirst.frc.team1619.robot2016.commands.ArmMoveToPositionCommand;
import org.usfirst.frc.team1619.robot2016.commands.ArmZeroCommand;
import org.usfirst.frc.team1619.robot2016.commands.CrossChevalleDeFriseCommand;
import org.usfirst.frc.team1619.robot2016.commands.CrossDefenseCommand;
import org.usfirst.frc.team1619.robot2016.commands.HighGoalToLaneCommand;
import org.usfirst.frc.team1619.robot2016.commands.LaneToHighGoalCommand;
import org.usfirst.frc.team1619.robot2016.commands.ShootFromAnywhereCommand;
import org.usfirst.frc.team1619.robot2016.framework.SequencerState;
import org.usfirst.frc.team1619.robot2016.util.MathUtility;

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

    add(new ArmZeroCommand());

    if (MathUtility.constrain(defense, 7, 1) != defense
      || MathUtility.constrain(lane, 5, 1) != lane) {
      add(new ArmMoveToPositionCommand(Constants.ARM_POSITION_DEFAULT));
      return;
    }

    Defenses defenseEnum = Defenses.values()[defense - 1];
    double initialAngle = sensorInput.getNavXHeading();

    add(new CrossDefenseCommand(defenseEnum,
      Constants.AUTO_DISTANCE_LINE_TO_PLATFORM));

    add(new LaneToHighGoalCommand(initialAngle, lane,
      defenseEnum.getHorizontalOffset()));
    add(new ShootFromAnywhereCommand());
    if (defenseEnum == Defenses.CHEVALLE_DE_FRISE) {
      add(new HighGoalToLaneCommand(initialAngle, lane,
        defenseEnum.getReturnOffset(), Constants.ARM_POSITION_DEFAULT + 1.0));
    }
    else {
      add(new HighGoalToLaneCommand(initialAngle, lane,
        defenseEnum.getReturnOffset()));
    }

    switch (defenseEnum) {
      case ROCK_WALL:
      case ROUGH_TERRAIN:
      case MOAT:
      case RAMPARTS:
      case PORTCULLIS:
        add(new CrossDefenseCommand(defenseEnum,
          defenseEnum.getReturnOffset() + 10.0));
        break;
      case LOW_BAR:
        add(
          new CrossDefenseCommand(defenseEnum, defenseEnum.getReturnOffset()));
        break;
      case CHEVALLE_DE_FRISE:
        add(new CrossChevalleDeFriseCommand(true));
        break;
    }
  }

  @Override
  protected void destruct() {
    super.destruct();

    setFinished();
  }

}
