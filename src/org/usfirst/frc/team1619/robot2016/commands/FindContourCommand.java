package org.usfirst.frc.team1619.robot2016.commands;

import org.usfirst.frc.team1619.robot2016.Constants;
import org.usfirst.frc.team1619.robot2016.IO.SocketTables.SmashBoard;
import org.usfirst.frc.team1619.robot2016.framework.Command;

public class FindContourCommand extends Command {

  private SmashBoard smashBoard;

  private boolean left;

  public FindContourCommand(boolean left) {
    super(0);

    smashBoard = SmashBoard.getInstance();

    this.left = left;
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void update() {
    if (smashBoard.getContourFound()) {
      setFinished();
    }

    robotOutput.arcadeDrive(0.0,
      left ? -1 : 1 * Constants.DRIVE_FIND_CONTOUR_ROTATE_SPEED);
  }

  @Override
  public void pause() {
    robotOutput.arcadeDrive(0.0, 0.0);
  }

  @Override
  public void destruct() {
    robotOutput.arcadeDrive(0.0, 0.0);
  }

}
