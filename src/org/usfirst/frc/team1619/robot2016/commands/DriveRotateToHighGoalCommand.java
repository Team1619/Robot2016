package org.usfirst.frc.team1619.robot2016.commands;

import org.usfirst.frc.team1619.robot2016.Constants;
import org.usfirst.frc.team1619.robot2016.RobotState;
import org.usfirst.frc.team1619.robot2016.IO.SocketTables.SmashBoard;

public class DriveRotateToHighGoalCommand extends DriveRotateCommand {

  private SmashBoard smashBoard;
  private RobotState robotState;

  public DriveRotateToHighGoalCommand() {
    this(false);
  }

  public DriveRotateToHighGoalCommand(boolean infinite) {
    super(0.0, infinite ? 0.0 : Constants.DRIVE_ROTATE_TO_TARGET_TOLERANCE,
      infinite ? 0 : Constants.DRIVE_ROTATE_TO_TARGET_TIMEOUT);

    smashBoard = SmashBoard.getInstance();
    robotState = RobotState.getInstance();
  }

  @Override
  protected void initialize() {
    if (smashBoard.getContourFound()) {
      angle = -smashBoard.getRotationOffsetToAligned()
        + robotState.getShootAlignOffset();
    }

    super.initialize();
  }

  @Override
  protected void update() {
    if (smashBoard.getGoodContourFound()) {
      rotationPID.setTarget(-smashBoard.getRotationOffsetToAligned()
        + robotState.getShootAlignOffset());
    }

    super.update();
  }
}
