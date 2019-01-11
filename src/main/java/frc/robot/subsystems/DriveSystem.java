/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class DriveSystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  TalonSRX rightMaster;
  TalonSRX leftMaster;
  TalonSRX rightSlave;
  TalonSRX leftSlave;
  Joystick joystick;
  public DriveSystem() {
    rightMaster = new TalonSRX(2); 
    leftMaster = new TalonSRX(0);
    rightSlave = new TalonSRX(3);
    leftSlave = new TalonSRX(1); 
    joystick = new Joystick(0);
  }
  public void drivePercentOutput(double left, double right) {
    rightMaster.set(ControlMode.PercentOutput, right);
    rightSlave.set(ControlMode.PercentOutput, right);
    leftMaster.set(ControlMode.PercentOutput, left);
    leftSlave.set(ControlMode.PercentOutput, left);
  }
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
