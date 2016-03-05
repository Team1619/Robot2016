package org.usfirst.frc.team1619.robot2016.commands;

import org.usfirst.frc.team1619.robot2016.IO.SmashBoard;

public class DriveRotateToHighGoalCommand extends DriveRotateCommand {

  private SmashBoard smashBoard;

  public DriveRotateToHighGoalCommand() {
    super(0.0, 2500);

    smashBoard = SmashBoard.getInstance();
  }

  @Override
  protected void initialize() {
    angle = -smashBoard.getRotationOffsetToAligned();

    super.initialize();
  }

}
