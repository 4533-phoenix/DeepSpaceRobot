/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.RobotMap;
import frc.robot.subsystems.*;

public class ElevatorPercentDown extends Command {
  ElevatorSystem elevatorSystem;
  double percent;
  Joystick controller;
  double tempPercent;
  public ElevatorPercentDown(double percent) {
    this.percent = percent;
    this.tempPercent = this.percent;
    elevatorSystem = ElevatorSystem.getInstance();
    requires(elevatorSystem);
    //elevatorSystem.setPosition(0);
    controller = new Joystick(RobotMap.JOYSTICK_PORT);
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(controller.getRawButton(RobotMap.RIGHT_TRIGGER)) {
      tempPercent = percent;
      percent = 1;
    }
    else {
      percent = tempPercent;
    }
    elevatorSystem.elevatorPercentOutput(percent);
    if(!elevatorSystem.getLimitSwitchIntake() && !elevatorSystem.getLimitSwitchMid()) {
      elevatorSystem.setPosition((int)(72*4096));
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return !elevatorSystem.getLimitSwitchBottom();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    elevatorSystem.stop();
    elevatorSystem.setPosition(0);
    //elevatorSystem.elevatorMovement(5);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
}
