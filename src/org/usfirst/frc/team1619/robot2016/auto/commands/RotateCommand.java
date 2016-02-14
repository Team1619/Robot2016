package org.usfirst.frc.team1619.robot2016.auto.commands;

import org.usfirst.frc.team1619.robot2016.Constants;
import org.usfirst.frc.team1619.robot2016.auto.AutoCommand;
import org.usfirst.frc.team1619.robot2016.subsystems.DriveTrain;

public class RotateCommand implements AutoCommand {

  DriveTrain driveTrain;
  double rotationTarget;

  public RotateCommand(double rotation) {
    driveTrain = DriveTrain.getInstance();
    rotationTarget = rotation;
  }

  @Override
  public void init() {
    driveTrain.setModePIDFull();
    driveTrain.resetRotation();
    driveTrain.setRotationTarget(rotationTarget);
  }

  @Override
  public void update() {
  }

  @Override
  public boolean finished() {
    return driveTrain.currentRotationError() 
        <= Constants.AUTO_TEST_ROTATION_TOLERANCE;
  }
}
