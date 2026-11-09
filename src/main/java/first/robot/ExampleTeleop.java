// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package first.robot;

import org.wpilib.driverstation.DriverStationDisplay;
import org.wpilib.driverstation.DriverStationErrors;
import org.wpilib.opmode.OpMode;
import org.wpilib.opmode.Teleop;
import org.wpilib.util.Alert;

// No more need for a chooser in Elastic!
@Teleop(textColor = "#000000", backgroundColor = "#FF0000", description = "Only an example!")

public class ExampleTeleop implements OpMode {
  private int pCount = 0;
  Alert alert = new Alert("miscRobotFault", "Check Robot", Alert.Level.HIGH);

  public ExampleTeleop(Robot robot) {
    robot.exampleController.rightStick().whileTrue(robot.exampleMechanism.exampleCommand());
  }

  @Override
  public void start() {
    // Example of using DS Alerts screen
    alert.set(true);
  }

  @Override
  public void periodic() {
    //Example of using DS Display Screen
    DriverStationDisplay.addData("Item01", pCount++);
    DriverStationDisplay.updateLines();
  }
}
