/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.RobotMap;
import frc.robot.subsystems.DriveSystem;
import jdk.jfr.ContentType;
/**
 * In this command we call the method driveVelocity which originates drive system
 */
public class DriveVelocity extends Command {
  Joystick controller;
  DriveSystem driveSystem;
  JoystickButton leftTrigger;

  public DriveVelocity() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    controller = new Joystick(RobotMap.JOYSTICK_PORT);
    leftTrigger = new JoystickButton(controller, RobotMap.LEFT_TRIGGER);
    driveSystem = DriveSystem.getInstance();
    this.requires(DriveSystem.getInstance());
    driveSystem.setPeakOutput(1);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if (controller.getRawButton(RobotMap.LEFT_TRIGGER)) {
      driveSystem.setVelocity(500);
    }
    else{
      driveSystem.setVelocity(250);
    }
    driveSystem.driveVelocity(controller.getRawAxis(1), controller.getRawAxis(3));
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
