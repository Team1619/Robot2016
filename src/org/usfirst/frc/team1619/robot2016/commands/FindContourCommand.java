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
    if (smashBoard.getContourFound() || smashBoard.getContourEdge()
      || sensorInput.getDartPosition() < -1.0) {

      if (smashBoard.getGoodContourFound()) {
        setFinished();
      }
      else {
        robotOutput.arcadeDrive(0.0,
          (left ? -1 : 1) * Constants.DRIVE_FIND_GOOD_CONTOUR_ROTATE_SPEED);
      }
    }
    else {
      robotOutput.arcadeDrive(0.0,
        (left ? -1 : 1) * Constants.DRIVE_FIND_CONTOUR_ROTATE_SPEED);
    }

    robotOutput.setDartMotor(1.0);
  }

  @Override
  public void pause() {
    robotOutput.arcadeDrive(0.0, 0.0);
    robotOutput.setDartMotor(0.0);
  }

  @Override
  public void destruct() {
    robotOutput.arcadeDrive(0.0, 0.0);
    robotOutput.setDartMotor(0.0);
  }

}
