package org.usfirst.frc.team1619.robot2016.util;

public class GenericTimer {

  private long initialTime;
  private int duration;

  public GenericTimer() {
    initialTime = 0;
    duration = 0;
  }

  /**
   * Reset the timer to initial conditions
   */
  public void reset() {
    initialTime = 0;
    duration = 0;
  }

  public void start() {
    initialTime = getCurrentTime();
  }

  /**
   * Set the duration of the timer
   * 
   * @param duration
   *          Timer length in milliseconds
   */
  public void setDuration(int duration) {
    this.duration = duration;
  }

  /**
   * Return if the timer has started
   * 
   * @return boolean: has timer started
   */
  public boolean isStarted() {
    return initialTime != 0;
  }
  
  /**
   * Return if the set time has elapsed
   * 
   * @return boolean: has timer duration elapsed
   */
  public boolean isFinished() {
    return get() >= 0;
  }

  /**
   * Get the amount of time since the timer duration passed. Negative values are
   * the amount of time until isFinished will return true.
   * 
   * @return int: time elapsed in milliseconds
   */
  public int get() {
    return (int)(getCurrentTime() - (initialTime + duration));
  }

  private long getCurrentTime() {
    return System.nanoTime() / 1000000;
  }
}