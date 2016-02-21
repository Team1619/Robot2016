package org.usfirst.frc.team1619.robot2016.commands;

import org.usfirst.frc.team1619.robot2016.Constants;
import org.usfirst.frc.team1619.robot2016.IO.RobotOutput;
import org.usfirst.frc.team1619.robot2016.IO.SensorInput;
import org.usfirst.frc.team1619.robot2016.util.GenericPID;

public class ReorientToGoalCommand implements Command {

  private GenericPID rotationPID;

  private RobotOutput robotOutput;
  private SensorInput sensorInput;

  private int done;

  @Override
  public void initialize() {
    robotOutput = RobotOutput.getInstance();
    sensorInput = SensorInput.getInstance();

    rotationPID = new GenericPID();
    rotationPID.setValues(Constants.DRIVE_PID_ROTATION);
    rotationPID.setIRange(Constants.DRIVE_PID_ROTATION_IRANGE);

    rotationPID.setTarget(0.0);

    done = 0;
  }

  @Override
  public void update() {
    rotationPID.calculate((sensorInput.getNavXHeading() + 180) % 360 - 180);
    robotOutput.arcadeDrive(0.0, rotationPID.get());

    if (Math.abs((sensorInput.getNavXHeading() + 180) % 360 - 180) < 1.0) {
      done++;
    }
    else {
      done = 0;
    }
  }

  @Override
  public void destruct() {
    robotOutput.arcadeDrive(0.0, 0.0);
  }

  @Override
  public boolean finished() {
    return done > 3;
  }

}
