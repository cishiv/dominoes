# 28 stock, 2 player Dominoes

## Getting Started

To run the application you will need the following:
1. JDK 15.0.1 (Java 15)
2. Gradle 6.7 (Wrapper Included)

You can build the application from source, or use the bundled JAR file (`./bin/app.jar`), provided you have a recent JRE installed on your machine.

### Building from source

To build from source, execute the following Gradle Wrapper command from your terminal (or IDE), which will download the Gradle binaries, and create the (executable) JAR file.
(Note: This command will also run the test cases that are part of the application!)
```
mshiv@LAPTOP-ENNNEGDS MINGW64 ~/Desktop/Development/assessments/dominoes (main)
$ ./gradlew build

Deprecated Gradle features were used in this build, making it incompatible with Gradle 7.0.
Use '--warning-mode all' to show the individual deprecation warnings.
See https://docs.gradle.org/6.7/userguide/command_line_interface.html#sec:command_line_warnings

BUILD SUCCESSFUL in 4s
7 actionable tasks: 7 executed
```

After you've built the application, simply run the JAR with the following command, and you should see similar output!

```
mshiv@LAPTOP-ENNNEGDS MINGW64 ~/Desktop/Development/assessments/dominoes (main)
$ java -jar app/build/libs/app.jar
Game starting with first tile: <6:2>
Alice plays <6:6> to connect to tile <6:2> on the board
Board is now: <6:6> <6:2>
Bob plays <3:2> to connect to tile <6:2> on the board
Board is now: <6:6> <6:2> <2:3>
Alice plays <6:5> to connect to tile <6:6> on the board
Board is now: <5:6> <6:6> <6:2> <2:3>
Bob plays <5:1> to connect to tile <5:6> on the board
Board is now: <1:5> <5:6> <6:6> <6:2> <2:3>
Alice plays <5:3> to connect to tile <2:3> on the board
Board is now: <1:5> <5:6> <6:6> <6:2> <2:3> <3:5>
Bob plays <5:2> to connect to tile <3:5> on the board
Board is now: <1:5> <5:6> <6:6> <6:2> <2:3> <3:5> <5:2>
Alice plays <1:0> to connect to tile <1:5> on the board
Board is now: <0:1> <1:5> <5:6> <6:6> <6:2> <2:3> <3:5> <5:2>
Bob plays <2:0> to connect to tile <0:1> on the board
Board is now: <2:0> <0:1> <1:5> <5:6> <6:6> <6:2> <2:3> <3:5> <5:2>
Alice can't play, drawing tile <4:2>
Alice plays <4:2> to connect to tile <5:2> on the board
Board is now: <2:0> <0:1> <1:5> <5:6> <6:6> <6:2> <2:3> <3:5> <5:2> <2:4>
Bob plays <2:2> to connect to tile <2:0> on the board
Board is now: <2:2> <2:0> <0:1> <1:5> <5:6> <6:6> <6:2> <2:3> <3:5> <5:2> <2:4>
Alice can't play, drawing tile <6:0>
Alice can't play, drawing tile <1:1>
Alice can't play, drawing tile <6:1>
Alice can't play, drawing tile <3:1>
Alice can't play, drawing tile <4:4>
Alice plays <4:4> to connect to tile <2:4> on the board
Board is now: <2:2> <2:0> <0:1> <1:5> <5:6> <6:6> <6:2> <2:3> <3:5> <5:2> <2:4> <4:4>
Bob plays <2:1> to connect to tile <2:2> on the board
Board is now: <1:2> <2:2> <2:0> <0:1> <1:5> <5:6> <6:6> <6:2> <2:3> <3:5> <5:2> <2:4> <4:4>
Alice plays <3:1> to connect to tile <1:2> on the board
Board is now: <3:1> <1:2> <2:2> <2:0> <0:1> <1:5> <5:6> <6:6> <6:2> <2:3> <3:5> <5:2> <2:4> <4:4>
Bob plays <6:4> to connect to tile <4:4> on the board
Board is now: <3:1> <1:2> <2:2> <2:0> <0:1> <1:5> <5:6> <6:6> <6:2> <2:3> <3:5> <5:2> <2:4> <4:4> <4:6>
Player Bob has won!

```

### Using the bundled JAR

It is recommended to build from source, however, if that is not possible - a precompiled JAR is provided.

Run the following command from your terminal in the root project directory.

`java -jar ./bin/app.jar`

This should run the application in exactly the same way as above.


If you experience any technical difficulties, please don't hesitate to contact me!
