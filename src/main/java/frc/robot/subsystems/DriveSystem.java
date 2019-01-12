/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.DriveCommand;
import frc.robot.RobotMap;

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
  private static DriveSystem INSTANCE;
  private double targetL = 0;
  private double targetR = 0;
  public DriveSystem() {
    rightMaster = new TalonSRX(RobotMap.RIGHT_MASTER_MOTOR); 
    leftMaster = new TalonSRX(RobotMap.LEFT_MASTER_MOTOR);
    rightSlave = new TalonSRX(RobotMap.RIGHT_SLAVE_MOTOR);
    leftSlave = new TalonSRX(RobotMap.LEFT_SLAVE_MOTOR);
    leftMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 100);
    rightMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 100);
  }
  public void drivePercentOutput(double left, double right) {
    rightMaster.set(ControlMode.PercentOutput, right);
    rightSlave.set(ControlMode.PercentOutput, right);
    leftMaster.set(ControlMode.PercentOutput, left);
    leftSlave.set(ControlMode.PercentOutput, left);
  }
  public static DriveSystem getInstance() {
    return INSTANCE;
  }
  public static void initialize() {
    if (INSTANCE == null){
      INSTANCE = new DriveSystem();
    }
  }
  public void driveVelocity (double left, double right){
    targetL = left * 4096 / 600;
    targetR = right * 4096 / 600;
    leftMaster.set (ControlMode.Velocity, targetL);
    leftSlave.set (ControlMode.Follower, RobotMap.LEFT_MASTER_MOTOR);
    rightMaster.set (ControlMode.Velocity, targetR);
    rightSlave.set (ControlMode.Follower, RobotMap.RIGHT_MASTER_MOTOR);
  }
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    this.setDefaultCommand(new DriveCommand());
  }
}
