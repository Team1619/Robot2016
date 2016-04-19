package org.usfirst.frc.team1619.robot2016.commands;

import org.usfirst.frc.team1619.robot2016.Constants;
import org.usfirst.frc.team1619.robot2016.framework.CommandSequence;
import org.usfirst.frc.team1619.robot2016.states.Defenses;

public class CrossDefenseCommand extends CommandSequence {

  /**
   * Cross specified defense including starting offset from defense
   * 
   * @param defense
   *          - 1 (Low bar), 2 (Rock wall), 3 (Rough terrain), 4 (Moat), 5
   *          (Ramparts), 6 (Chevalle de frise)
   * @param offset
   *          - initial offset from platform
   */
  public CrossDefenseCommand(Defenses defense, double offset) {
    double distance, armPosition;

    switch (defense) {
      case LOW_BAR:
        distance = Constants.AUTO_DISTANCE_LOW_BAR + offset;
        armPosition = Constants.ARM_POSITION_LOW_BAR;
        break;
      case ROCK_WALL:
        distance = Constants.AUTO_DISTANCE_ROCK_WALL + offset;
        armPosition = Constants.ARM_POSITION_DEFAULT;
        break;
      default:
      case ROUGH_TERRAIN:
        distance = Constants.AUTO_DISTANCE_ROUGH_TERRAIN + offset;
        armPosition = Constants.ARM_POSITION_DEFAULT;
        break;
      case MOAT:
        distance = Constants.AUTO_DISTANCE_MOAT + offset;
        armPosition = Constants.ARM_POSITION_DEFAULT;
        break;
      case RAMPARTS:
        distance = Constants.AUTO_DISTANCE_RAMPARTS + offset;
        armPosition = Constants.ARM_POSITION_DEFAULT;
        break;
      case CHEVALLE_DE_FRISE:
        distance = Constants.AUTO_DISTANCE_CHEVALLE + offset;
        armPosition = Constants.ARM_POSITION_DEFAULT;
        break;
    }

    CommandSequence cross = new CommandSequence();

    cross.add(
      new DriveTranslateCommand(distance, Constants.AUTO_STATIC_DEFENSES_SPEED,
        Constants.AUTO_DEFENSE_TRANSLATE_TOLERANCE, 0));
    cross.addPassive(new ArmMoveToPositionCommand(armPosition));
    
    if (defense == Defenses.CHEVALLE_DE_FRISE) {
      cross.add(new CrossChevalleDeFriseCommand());
    }

    add(cross);
  }

}
