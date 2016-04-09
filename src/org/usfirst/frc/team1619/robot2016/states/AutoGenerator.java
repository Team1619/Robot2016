package org.usfirst.frc.team1619.robot2016.states;

import org.usfirst.frc.team1619.robot2016.Constants;
import org.usfirst.frc.team1619.robot2016.SubsystemID;
import org.usfirst.frc.team1619.robot2016.commands.ArmMoveToPositionCommand;
import org.usfirst.frc.team1619.robot2016.commands.ArmZeroCommand;
import org.usfirst.frc.team1619.robot2016.commands.CrossChevalleDeFriseCommand;
import org.usfirst.frc.team1619.robot2016.commands.CrossLowBarCommand;
import org.usfirst.frc.team1619.robot2016.commands.DriveFromDefenseToHighGoalGenerator;
import org.usfirst.frc.team1619.robot2016.commands.HighGoalTargetPosition;
import org.usfirst.frc.team1619.robot2016.commands.ShootAlignHighGoalCommand;
import org.usfirst.frc.team1619.robot2016.commands.ShootManualCommand;
import org.usfirst.frc.team1619.robot2016.framework.CommandSequence;
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
    double initialAngle = sensorInput.getNavXHeading();
    int defense = smashBoard.getAutoDefense();
    int lane = smashBoard.getAutoLane();
    int targetGoal = smashBoard.getAutoTargetGoal();
    System.out.println(defense + " " + lane + " " + targetGoal);
    smashBoard.setString("autoGenerator", defense + " " + lane + " " + targetGoal);
    int distanceOffsetFromOuterWorks;
    HighGoalTargetPosition highGoalTargetPosition;
    DriveFromDefenseToHighGoalGenerator driveFromDefenseToHighGoalGenerator;

    add(new ArmZeroCommand());

    switch (defense) {
      case 0: // Do nothing
        add(new ArmMoveToPositionCommand(Constants.ARM_POSITION_DEFAULT));
        return;
      case 1: // Low bar
        distanceOffsetFromOuterWorks = -5;
        add(new CrossLowBarCommand());
        break;
      case 2: // Chevalle de Frise
        distanceOffsetFromOuterWorks = 50;
        add(new CrossChevalleDeFriseCommand());
        break;
      case 3: // Portcullis
        distanceOffsetFromOuterWorks = 20;
        break;
      default:
        add(new CrossLowBarCommand());
        distanceOffsetFromOuterWorks = -15;
        break;
    }

    switch (targetGoal) {
      case 1:
        highGoalTargetPosition = HighGoalTargetPosition.LEFT;
        break;
      case 2:
        highGoalTargetPosition = HighGoalTargetPosition.MIDDLE;
        break;
      case 3:
        highGoalTargetPosition = HighGoalTargetPosition.RIGHT;
      default:
        highGoalTargetPosition = HighGoalTargetPosition.LEFT;
    }

    switch (lane) {
      case 1:
        driveFromDefenseToHighGoalGenerator =
          DriveFromDefenseToHighGoalGenerator.DEFENSE_1;
        break;
      case 2:
        driveFromDefenseToHighGoalGenerator =
          DriveFromDefenseToHighGoalGenerator.DEFENSE_2;
        break;
      case 3:
        driveFromDefenseToHighGoalGenerator =
          DriveFromDefenseToHighGoalGenerator.DEFENSE_3;
        break;
      case 4:
        driveFromDefenseToHighGoalGenerator =
          DriveFromDefenseToHighGoalGenerator.DEFENSE_4;
        break;
      case 5:
        driveFromDefenseToHighGoalGenerator =
          DriveFromDefenseToHighGoalGenerator.DEFENSE_5;
        break;
      default:
        driveFromDefenseToHighGoalGenerator =
          DriveFromDefenseToHighGoalGenerator.DEFENSE_1;
        break;
    }

    CommandSequence armToVisionAndSpool = new CommandSequence();
    armToVisionAndSpool.add(new ArmMoveToPositionCommand(Constants.ARM_POSITION_VISION, 1500));
    armToVisionAndSpool.addPassive(new ShootManualCommand(Constants.SHOOTER_SPOOL_UP_SPEED, 0));
    
//    smashBoard.setString("", text);
    
    add(driveFromDefenseToHighGoalGenerator.getDriveToTargetGoalSequence(
      highGoalTargetPosition, distanceOffsetFromOuterWorks,
      initialAngle));
    add(armToVisionAndSpool);
    add(new ShootAlignHighGoalCommand(Constants.SHOOTER_SHOOT_SPEED_TARGET_AUTO, Constants.ARM_POSITION_SHOOT_AUTO, 5000));
  }

  @Override
  protected void destruct() {
    super.destruct();

    setFinished();
  }

}
