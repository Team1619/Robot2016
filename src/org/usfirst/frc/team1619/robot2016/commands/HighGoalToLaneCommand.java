package org.usfirst.frc.team1619.robot2016.commands;

import org.usfirst.frc.team1619.robot2016.framework.CommandSequence;

public class HighGoalToLaneCommand extends CommandSequence {

  public HighGoalToLaneCommand(double initialAngle, int lane, double offset) {
    switch (lane) {
      case 1:
        add(
          new DriveRotateToAbsoluteCommand((initialAngle + 180.0) % 360, 2000));
        add(new DriveTranslateCommand(100.0 - offset, 0.9, 7.5, 2500));
        break;
      case 2:
        add(
          new DriveRotateToAbsoluteCommand((initialAngle + 180.0) % 360, 2000));
        add(new DriveTranslateCommand(100.0 - offset, 0.9, 7.5, 2500));
        break;
      case 3:
        add(
          new DriveRotateToAbsoluteCommand((initialAngle + 180.0) % 360, 2000));
        add(new DriveTranslateCommand(37.5 - offset, 0.9, 7.5, 2500));
        break;
      case 4:
        add(
          new DriveRotateToAbsoluteCommand((initialAngle + 180.0) % 360, 2000));
        add(new DriveTranslateCommand(45.0 - offset, 0.9, 7.5, 2500));
        break;
      case 5:
        add(
          new DriveRotateToAbsoluteCommand((initialAngle + 180.0) % 360, 2000));
        add(new DriveTranslateCommand(90.0 - offset, 0.9, 7.5, 2500));
        break;
    }
  }

}
