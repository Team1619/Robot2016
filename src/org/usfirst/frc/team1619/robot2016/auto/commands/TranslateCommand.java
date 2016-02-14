package org.usfirst.frc.team1619.robot2016.auto.commands;

import org.usfirst.frc.team1619.robot2016.Constants;
import org.usfirst.frc.team1619.robot2016.auto.AutoCommand;
import org.usfirst.frc.team1619.robot2016.subsystems.DriveTrain;

public class TranslateCommand implements AutoCommand {

  private DriveTrain driveTrain;
  private int targetDistance;

  public TranslateCommand(int distance) {
    driveTrain = DriveTrain.getInstance();
    targetDistance = distance;
  }

  @Override
  public void init() {
    driveTrain.setModePIDFull();
    driveTrain.resetTranslation();
    driveTrain.setTranslationTarget(targetDistance);
    driveTrain.resetRotation();
    driveTrain.setRotationTarget(0);
  }

  @Override
  public void update() {
  }

  @Override
  public boolean finished() {
    return driveTrain.currentTranslationError() 
        <= Constants.AUTO_TEST_TRANSLATION_TOLERANCE;
  }

}
