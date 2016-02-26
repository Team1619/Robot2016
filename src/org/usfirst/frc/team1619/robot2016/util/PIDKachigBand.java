package org.usfirst.frc.team1619.robot2016.util;

public class PIDKachigBand extends GenericPID {
  private double kKachig;

  private double kachigBand;
  private double deadBand;
  private int kachigOnTime;
  private int kachigOffTime;
  private double minimumOutput;

  private GenericTimer kachigTimer;

  public PIDKachigBand() {
    this(new PIDValues(0, 0, 0));
  }

  public PIDKachigBand(PIDValues values) {
    super(values);
    kKachig = 0;
    kachigBand = 0;
    deadBand = 0;
    kachigOnTime = 0;
    kachigOffTime = 0;
    minimumOutput = 0;

    kachigTimer = new GenericTimer();
    kachigTimer.start();
  }

  public void reset() {
    super.reset();
    kachigTimer.reset();
  }

  public void setKachigBand(double band) {
    kachigBand = band;
  }

  public void setDeadBand(double band) {
    deadBand = band;
  }

  public void setKachigConstant(double constant) {
    kKachig = constant;
  }

  public void setKachigTime(int onTime, int offTime) {
    kachigOnTime = onTime;
    kachigOffTime = offTime;
  }

  public void setMinumumOutput(double minimum) {
    minimumOutput = minimum;
  }

  public void calculate(double currentValue) {
    double error = setPoint - currentValue;
    // If outside deadband, do nothing
    if (Math.abs(error) > deadBand) {
      // If inside kachigband, kachigs
      if (Math.abs(error) < kachigBand) {
        // If we should be kachiging, return the kachig value. Else, return 0
        if (kachigTimer.get() % (kachigOnTime + kachigOffTime) < kachigOnTime) {
          outputValue = Math.min(minimumOutput, 
            Math.max(-minimumOutput, kKachig * error));
        }
        else {
          outputValue = 0;
        }
      }
      else {
        // Always restart the timer so we kachig right away when we're no longer
        // within the normal PID range
        kachigTimer.start();
        outputValue = calcPID(error);
      }
    }
  }
}
