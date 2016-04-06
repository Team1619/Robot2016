package org.usfirst.frc.team1619.robot2016.commands;

import org.usfirst.frc.team1619.robot2016.framework.CommandGroup;
import org.usfirst.frc.team1619.robot2016.framework.CommandSequence;

public class LaneToHighGoalCommand extends CommandSequence {

  public LaneToHighGoalCommand(int lane, double offset) {
    switch (lane) {
      case 1: {
        CommandGroup driveAndArmDown = new CommandGroup();
        driveAndArmDown
          .add(new DriveTranslateCommand(100.0 - offset, 0.9, 7.5, 2500));
        driveAndArmDown.add(new ArmMoveToPositionCommand(0.0, 2500));

        add(driveAndArmDown);
        add(new DriveRotateCommand(60.0, 2000));
        break;
      }
      case 2: {
        CommandGroup driveAndArmDown = new CommandGroup();
        driveAndArmDown
          .add(new DriveTranslateCommand(100.0 - offset, 0.9, 7.5, 2500));
        driveAndArmDown.add(new ArmMoveToPositionCommand(0.0, 2500));

        add(driveAndArmDown);
        add(new DriveRotateCommand(55.0, 2000));
        break;
      }
      case 3: {
        CommandGroup driveAndArmDown = new CommandGroup();
        driveAndArmDown
          .add(new DriveTranslateCommand(37.5 - offset, 0.9, 7.5, 2500));
        driveAndArmDown.add(new ArmMoveToPositionCommand(0.0, 2500));

        add(driveAndArmDown);
        add(new DriveRotateCommand(17.5, 1500));
        break;
      }
      case 4: {
        CommandGroup driveAndArmDown = new CommandGroup();
        driveAndArmDown
          .add(new DriveTranslateCommand(45.0 - offset, 0.9, 7.5, 2500));
        driveAndArmDown.add(new ArmMoveToPositionCommand(0.0, 2500));

        add(driveAndArmDown);
        add(new DriveRotateCommand(-12.5, 1500));
        break;
      }
      case 5: {
        CommandGroup driveAndArmDown = new CommandGroup();
        driveAndArmDown
          .add(new DriveTranslateCommand(90.0 - offset, 0.9, 7.5, 2500));
        driveAndArmDown.add(new ArmMoveToPositionCommand(0.0, 2500));

        add(driveAndArmDown);
        add(new DriveRotateCommand(-40.0, 2000));
        break;
      }
    }
  }

}
