package org.usfirst.frc.team1619.robot2016.auto;

import org.usfirst.frc.team1619.robot2016.auto.commands.RotateCommand;
import org.usfirst.frc.team1619.robot2016.auto.commands.TranslateCommand;

public class Autonomous {
  private static Autonomous instance;
  static {
    instance = new Autonomous();
  }

  public static Autonomous getInstance() {
    return instance;
  }

  AutoMode autoTest;

  private Autonomous() {
    autoTest = new AutoMode();
    autoTest.add(new TranslateCommand(2000));
    autoTest.add(new RotateCommand(90));
    autoTest.add(new TranslateCommand(2000));
    autoTest.add(new RotateCommand(90));
    autoTest.add(new TranslateCommand(2000));
    autoTest.add(new RotateCommand(90));
    autoTest.add(new TranslateCommand(2000));
    autoTest.add(new RotateCommand(90));
  }

  public void initialize() {
    autoTest.init();
  }

  public void update() {
    autoTest.update();
  }

  public void disable() {
  }
}
