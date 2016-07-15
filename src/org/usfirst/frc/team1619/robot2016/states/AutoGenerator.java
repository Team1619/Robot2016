package org.usfirst.frc.team1619.robot2016.states;

import org.usfirst.frc.team1619.robot2016.Constants;
import org.usfirst.frc.team1619.robot2016.SubsystemID;
import org.usfirst.frc.team1619.robot2016.commands.ArmMoveToPositionCommand;
import org.usfirst.frc.team1619.robot2016.commands.ArmZeroCommand;
import org.usfirst.frc.team1619.robot2016.commands.CrossChevalleDeFriseCommand;
import org.usfirst.frc.team1619.robot2016.commands.CrossDefenseCommand;
import org.usfirst.frc.team1619.robot2016.commands.DriveRotateToAbsoluteCommand;
import org.usfirst.frc.team1619.robot2016.commands.DriveTranslateCommand;
import org.usfirst.frc.team1619.robot2016.commands.FindContourCommand;
import org.usfirst.frc.team1619.robot2016.commands.GetBallCommand;
import org.usfirst.frc.team1619.robot2016.commands.HighGoalToLaneCommand;
import org.usfirst.frc.team1619.robot2016.commands.LaneToHighGoalCommand;
import org.usfirst.frc.team1619.robot2016.commands.PauseCommand;
import org.usfirst.frc.team1619.robot2016.commands.ShootFromAnywhereCommand;
import org.usfirst.frc.team1619.robot2016.framework.CommandGroup;
import org.usfirst.frc.team1619.robot2016.framework.CommandSequence;
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
    int lane = smashBoard.getAutoLane() + 1;
    int defense = smashBoard.getAutoDefense() + 1;

    add(new ArmZeroCommand());
    double initialAngle = sensorInput.getNavXHeading();

    if (defense == 7) {
      CommandGroup driveAndArmDown = new CommandGroup();
      CommandSequence waitToDrive = new CommandSequence();
      waitToDrive.add(new PauseCommand(125));
      waitToDrive.add(new DriveTranslateCommand(160.0, 0.9, 15.0, 3750));
      driveAndArmDown.add(new ArmMoveToPositionCommand(
        Constants.ARM_POSITION_DEFAULT + 0.5, 2000));
      driveAndArmDown.add(waitToDrive);
      add(driveAndArmDown);

      add(new FindContourCommand(true));
      add(new ShootFromAnywhereCommand());
      CommandGroup rotateAndArmDown = new CommandGroup();
      rotateAndArmDown.add(
        new DriveRotateToAbsoluteCommand((initialAngle + 180) % 360, 2500));
      rotateAndArmDown.add(
        new ArmMoveToPositionCommand(Constants.ARM_POSITION_DEFAULT, 2000));
      add(rotateAndArmDown);

      add(new DriveTranslateCommand(120.0, 1.0, 20.0, 2500));
      add(new DriveRotateToAbsoluteCommand((initialAngle + 180) % 360, 2500));
      add(new GetBallCommand());
      add(new DriveRotateToAbsoluteCommand(initialAngle, 2500));

      CommandGroup driveAndArmUp = new CommandGroup();
      driveAndArmUp.add(new ArmMoveToPositionCommand(
        Constants.ARM_POSITION_DEFAULT + 0.5, 2000));
      driveAndArmDown.add(new DriveTranslateCommand(175.0, 0.9, 15.0, 3750));
      add(driveAndArmDown);

      add(new FindContourCommand(false));
      add(new ShootFromAnywhereCommand());
      return;
    }

    if (MathUtility.constrain(defense, 6, 1) != defense
      || MathUtility.constrain(lane, 5, 1) != lane) {
      add(new ArmMoveToPositionCommand(Constants.ARM_POSITION_DEFAULT));
      return;
    }

    Defenses defenseEnum = Defenses.values()[defense - 1];

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
