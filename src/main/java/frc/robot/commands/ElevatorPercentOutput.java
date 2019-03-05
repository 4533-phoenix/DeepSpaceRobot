/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.*;

public class ElevatorPercentOutput extends Command {
  ElevatorSystem elevatorSystem;
  double percent;
  public ElevatorPercentOutput(double percent) {
    this.percent = percent;
    elevatorSystem = ElevatorSystem.getInstance();
    requires(elevatorSystem);
    elevatorSystem.setPosition(0);

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
    elevatorSystem.elevatorPercentOutput(percent);
    if(!elevatorSystem.getLimitSwitchIntake() && !elevatorSystem.getLimitSwitchMid()) {
      elevatorSystem.setPosition((int)(72*4096));
    }
    else if(!elevatorSystem.getLimitSwitchBottom()) {
      elevatorSystem.setPosition((int)(0));
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    elevatorSystem.stop();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
}
