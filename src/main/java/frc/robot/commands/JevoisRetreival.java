/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.SerialPort;
public class JevoisRetreival extends Command {
  private SerialPort serial;
  private int count;
  public JevoisRetreival() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    //serial = new SerialPort(921600,SerialPort.Port.kUSB);
    count = 0;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    try{
      System.out.println("Test");
      //System.out.println(((Integer)serial.getBytesReceived()).toString());
      count = 1;
    }
    catch(Exception e) {
      System.out.println("Error");
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return count == 1;
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
