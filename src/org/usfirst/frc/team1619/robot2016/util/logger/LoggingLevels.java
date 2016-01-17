package org.usfirst.frc.team1619.robot2016.util.logger;

/**
 * Created by DanielHathcock on 10/20/15.
 * Project: Logger
 * Package: org.usfirst.frc.team1619.logger
 */
public enum LoggingLevels
{
    ERROR, WARNING, INFO, DEBUG;

    @Override
    public String toString()
    {
        return name().charAt(0) + name().substring(1).toLowerCase();
    }
}
