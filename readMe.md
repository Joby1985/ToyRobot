# Toy Robot

This is a simple Java program that helps setting up a "Table" and then to "Place" a Robot and to issue commands to the Robot to move it safely (avoiding fall from table) around the table with commands LEFT/RIGHT/MOVE etc.. No movement will be done if it identifies that the Robot would fall off.

The Robot should be initially placed in a specific direction (NORTH/EAST/SOUTH/WEST) at one point (co-ordinate) on the table by using a PLACE command, eg:

```
PLACE 0,0,NORTH
```

## Usage

To run the program, checkout from github, then go the root of the project and on the command line, execute the below command to build it using gradle.

```
$ gradlew clean build --refresh-dependencies
```

Note: It is built using Java 8. (Please look at the java version set in build.gradle). if you have a different Java version like Java 11, please change the properties in build.gradle (sourceCompatibility = 1.11, targetCompatibility = 1.11)

Now run the program using java runtime in your path (appropriate Java 8 / Java 11), for eg:

```
$ java -jar build/libs/ToyRobot.jar

Please input the commands seperated by New line '\n'. To denote end of input, provide a blank line and press 'Enter'.

PLACE 1,2,EAST
MOVE
MOVE
LEFT
MOVE
REPORT

```

You should see the output like:

```
Output: 3,3,NORTH
```

## Implementation

Patterns used:
1. Command Pattern
2. Factory Pattern
3. Singleton Pattern (Using Enum)

Robot is a Singleton object and would be positioned only if a valid Initial position (within the table) is set. Any commands except 'REPORT', before a valid PLACE command would be ignored.

Turn (Left/Right) is implemented using a mapped knowledge base. (When facing NORTH, LEFT command results in facing WEST): getDirectionTransformMap() in TurnCommand.java

Validations:
1. Ensures co-ordinates and Direction in the PLACE command are valid.
2. Ensures the input commands are valid.
3. Ensures the robot is always on the table for each movement. (If initial placement is not on table, it wont be placed until a valid PLACE command is issued). You can have multiple PLACE commands as part of an instruction set.

Configuration:
--------------
There is a config (robot.properties) under src/main/resources. This can be used to specify different Tables (different co-ordinates and bounds) for the table. Bundled jar has the default config set. This could be externalized and can be used by passing the VM param 'properties.path' as property like below

```
$ java -Dproperties.path=<PATH>/robot.properties -jar build/libs/ToyRobot.jar
```

Logging:
--------
Logging is implemented using logback. There is a config for this (logback.xml) under src/main/resources. This may be copied over to another location and further configured and the path might be provided to the runtime. (Bundled jar has the default log config set.)

```
$ java -Dlogback.configurationFile=<PATH>/logback.xml -jar build/libs/ToyRobot.jar
```

Running with externalized config and log config:
------------------------------------------------
```
$ java -Dproperties.path=<PATH>/robot.properties -Dlogback.configurationFile=<PATH>/logback.xml -jar build/libs/ToyRobot.jar
```



Dependency libraries' versions:
------------------------------
Gradle is used to manage the build and dependencies. The dependency library versions are configured in gradle.properties.

Lombok:
-------
Lombok library is used to minimize code and autogenerate where required. This will help in avoiding boilerplate code so as to concentrate only on main code.

## Implementation -- Functionalities

'Direction' & MOVE:
------------------
Direction translates to a +/- unit in X-axis and Y-axis. (Ref: Direction.java)

eg:
		a. Direction = NORTH
				X-direction = 0;
				Y-direction = +1;
		b. Direction = WEST
				X-direction = -1;
				Y-direction = 0;			
			
		Once the above is set, it is very easy to do a Move.
			new X-Pos = current X-position + Direction.X-direction	
			new Y-Pos = current Y-position + Direction.Y-direction

##Assumptions
1) A Cartesian plane is assumed for the table and 

	NORTH ==>  +Y-axis
    EAST  ==>  +X-axis
    SOUTH ==>  -Y-axis
    WEST  ==>  -X-axis

2) Input would be from the standard input, though this could be changed to a file with minimal changes in the main class RobotGame.java

Note: To denote End of file (input), leave a blank line and press "Enter".

## Extensibility
If more commands need to be added, just add the implementation of corresponding command extending the Command interface, and provide this command in the CommandFactory. (Add new case)
