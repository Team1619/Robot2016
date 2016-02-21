package org.usfirst.frc.team1619.robot2016.states;

public class RotateToCameraTarget extends RotateToAngle {

  @Override
  protected double getRotationTarget() {
    double val = (sensorInput.getNavXHeading()
        - smashBoard.getRotationOffsetToAligned() + 180) % 360 - 180;
    System.out.println(smashBoard.getRotationOffsetToAligned());
    System.out.println(val);
    return val;
  }

}