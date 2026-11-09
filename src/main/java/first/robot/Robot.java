// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package first.robot;

import first.fms.SimFms;
import org.wpilib.command3.Scheduler;
import org.wpilib.command3.button.CommandGamepad;
import org.wpilib.framework.OpModeRobot;
import first.robot.constants.DriverConstants;
import first.robot.mechanisms.ExampleMechanism;

import org.wpilib.system.Timer;

public class Robot extends OpModeRobot {
  final ExampleMechanism exampleMechanism = new ExampleMechanism();
  final CommandGamepad exampleController =
      new CommandGamepad(DriverConstants.DRIVER_CONTROLLER_PORT);

  private final Timer simMatchTimer = new Timer();
  private final SimFms simFms = new SimFms();

  public Robot() {}

  @Override
  public void robotPeriodic() {
    Scheduler.getDefault().run();
  }


//  @Override
//  public void simulationInit() {
//    // Start the stopwatch when simulation begins
//    simMatchTimer.restart();
//  }
//
//  @Override
//  public void simulationPeriodic() {
//    // Read total seconds elapsed since simulationInit()
//    double elapsedSec = simMatchTimer.get();
//    simFms.updateFmsSimulation(elapsedSec);
//  }
}
