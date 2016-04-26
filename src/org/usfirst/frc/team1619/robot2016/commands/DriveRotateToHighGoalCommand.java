package org.usfirst.frc.team1619.robot2016.commands;

import org.usfirst.frc.team1619.robot2016.Constants;
import org.usfirst.frc.team1619.robot2016.RobotState;
import org.usfirst.frc.team1619.robot2016.IO.SocketTables.SmashBoard;

public class DriveRotateToHighGoalCommand extends DriveRotateCommand {

  private SmashBoard smashBoard;
  private RobotState robotState;

  private double tolerance;
  private int updating;

  public DriveRotateToHighGoalCommand(double tolerance) {
    this(tolerance, false);
  }

  public DriveRotateToHighGoalCommand(boolean infinite) {
    this(0.0, infinite);
  }

  public DriveRotateToHighGoalCommand(double tolerance, boolean infinite) {
    super(0.0, infinite ? 0.0 : tolerance,
      infinite ? 0 : Constants.DRIVE_ROTATE_TO_TARGET_TIMEOUT);

    smashBoard = SmashBoard.getInstance();
    robotState = RobotState.getInstance();

    this.tolerance = tolerance;
  }

  @Override
  protected void initialize() {
    if (smashBoard.getContourFound()) {
      angle = -smashBoard.getRotationOffsetToAligned()
        + robotState.getShootAlignOffset();
      updating = 0;
    }

    super.initialize();
  }

  @Override
  protected void update() {
    if (smashBoard.getContourFound() && updating < 5) {
      double offset = -smashBoard.getRotationOffsetToAligned()
        + robotState.getShootAlignOffset();

      if (Math.abs(offset) < tolerance) {
        updating++;
      }
      else {
        updating = 0;
      }

      rotationPID.setTarget(offset);
    }

    super.update();
  }
}
