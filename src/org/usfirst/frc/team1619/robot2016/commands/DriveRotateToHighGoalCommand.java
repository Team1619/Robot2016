package org.usfirst.frc.team1619.robot2016.commands;

import org.usfirst.frc.team1619.robot2016.IO.SocketTables.SmashBoard;
import org.usfirst.frc.team1619.robot2016.framework.RobotState;

public class DriveRotateToHighGoalCommand extends DriveRotateCommand {

  private SmashBoard smashBoard;
  private RobotState robotState;

  public DriveRotateToHighGoalCommand() {
    super(0.0, 2500);

    smashBoard = SmashBoard.getInstance();
    robotState = RobotState.getInstance();
  }

  @Override
  protected void initialize() {
    angle = -smashBoard.getRotationOffsetToAligned() + robotState.getShootAlignOffset();

    super.initialize();
  }

}
