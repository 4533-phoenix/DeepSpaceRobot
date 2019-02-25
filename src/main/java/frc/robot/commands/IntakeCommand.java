/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.RobotMap;
import frc.robot.subsystems.IntakeSystem;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.*;

public class IntakeCommand extends Command {

  IntakeSystem intake;
  boolean extake;
  double output;
  //Joystick controller;
  //JoystickButton leftBumper = new JoystickButton(controller, RobotMap.LEFT_BUMPER);
  //JoystickButton rightBumper = new JoystickButton(controller, RobotMap.RIGHT_BUMPER);
  public IntakeCommand(boolean extake, double output) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    //controller = new Joystick(0);
    intake = IntakeSystem.getInstance();
    requires(intake);
    this.extake = extake;
    this.output = output;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(extake) {
      intake.out(output);
    }
    else {
      intake.in(output);
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
    intake.stop();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
