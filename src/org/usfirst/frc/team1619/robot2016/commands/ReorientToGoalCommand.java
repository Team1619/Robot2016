package org.usfirst.frc.team1619.robot2016.commands;

import org.usfirst.frc.team1619.robot2016.Constants;
import org.usfirst.frc.team1619.robot2016.IO.RobotOutput;
import org.usfirst.frc.team1619.robot2016.IO.SensorInput;
import org.usfirst.frc.team1619.robot2016.IO.SmashBoard;
import org.usfirst.frc.team1619.robot2016.util.GenericPID;

public class ReorientToGoalCommand implements Command {

  private GenericPID rotationPID;

  private RobotOutput robotOutput;
  private SensorInput sensorInput;
  private SmashBoard smashBoard;

  private int stage;

  @Override
  public void initialize() {
    robotOutput = RobotOutput.getInstance();
    sensorInput = SensorInput.getInstance();
    smashBoard = SmashBoard.getInstance();

    rotationPID = new GenericPID();
    rotationPID.setValues(Constants.DRIVE_PID_ROTATION);
    rotationPID.setIRange(Constants.DRIVE_PID_ROTATION_IRANGE);

    rotationPID.setTarget(0.0);

    stage = 0;
  }

  @Override
  public void update() {
    if (stage == 0) {
      if (sensorInput.getNavXHeading() < 2.0) {
        robotOutput.arcadeDrive(0.0, 0.0);
        stage++;
      }
      else {
        rotationPID.calculate((sensorInput.getNavXHeading() + 180) % 360 - 180);
        robotOutput.arcadeDrive(0.0, rotationPID.get());
      }
    }
    else if (stage == 5) {
      double offsetAngle = smashBoard.getRotationOffsetToAligned();

      if (Math.abs(sensorInput.getNavXHeading() - offsetAngle) < 0.5) {
        robotOutput.arcadeDrive(0.0, 0.0);
        stage++;
      }
      else {
        rotationPID.setTarget(offsetAngle);
        rotationPID.calculate((sensorInput.getNavXHeading() + 180) % 360 - 180);
      }
    }
    else {
      stage++;
    }
  }

  @Override
  public void destruct() {
    robotOutput.arcadeDrive(0.0, 0.0);
  }

  @Override
  public boolean finished() {
    return stage == 6;
  }

}
