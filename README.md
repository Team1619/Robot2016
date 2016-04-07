# Up-A-Creek Robotics 2016 Code
## Dependencies
1. Java development kit (JDK V1.8.0_*)
2. Eclipse (Luna or later)
3. WPI Libraries
   1. Install the WPI Eclipse plugins, following instructions at [Screenstepslive](http://wpilib.screenstepslive.com/s/4485/m/13809/l/145002-installing-eclipse-c-java)
   2. Add networktables.jar and wpilib.jar as "networktables" and "wpilib" to Eclipse > Preferences > Java > Build Path > Classpath Variables (The jars should exist in $HOME/wpilib/java/current/lib/)
4. NavX Libraries
   1. If on a Windows OS, go to [NavX website](http://www.pdocs.kauailabs.com/navx-mxp/software/roborio-libraries/java/) and install the libraries
   2. If on any other OS, install the libraries from the pinned zip in the software channel of Slack
   3. Put the navx_frc.jar file in the directory $HOME/navx-mxp/roborio/java/lib/navx_frc.jar in your home directory on your computer
   4. Add navx_frc.jar as "navx-mxp" to Eclipse > Preferences > Java > Build Path > Classpath Variables
5. JSON Library
   1. Install JSON-simple. Download json-simple-1.1.1.jar library for JSON in Java at [json-simple](https://code.google.com/archive/p/json-simple/downloads)
   2. Put the jar file in a directory $HOME/json-simple/json-simple.jar
   3. Add json-simple.jar as "json-simple" to Eclipse > Preferences > Java > Build Path > Classpath Variables
5. Clone Repository in Eclipse with File > import... > Git > Projects from Git > Clone URI, using the URI found on [github page](https://github.com/Team1619/Robot2016.git)
