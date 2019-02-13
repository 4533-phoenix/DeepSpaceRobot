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
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.DriveVelocity;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class DriveSystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  /**
   * Created TalonSRX objects
   */
  TalonSRX rightMaster;
  TalonSRX leftMaster;
  VictorSPX rightSlave;
  VictorSPX leftSlave;
  /**
   * Creating target speed variables for Left and right
   */
  private static DriveSystem INSTANCE;
  private double targetL = 0;
  private double targetR = 0;
  /**
   * setting the max velocity to 250 rpm
   */
  public static double MAX_VELOCITY = 250;
 
  public DriveSystem() {
    /**
     * Creating new TalonSRX's
     */
    rightMaster = new TalonSRX(RobotMap.RIGHT_MASTER_MOTOR); 
    leftMaster = new TalonSRX(RobotMap.LEFT_MASTER_MOTOR);
    rightSlave = new VictorSPX(RobotMap.RIGHT_SLAVE_MOTOR);
    leftSlave = new VictorSPX(RobotMap.LEFT_SLAVE_MOTOR);
    /**
     * configures drive sensors
     */
    leftMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,0,100);
    rightMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,0,100);
    
    leftMaster.setSensorPhase(true);
    rightMaster.setSensorPhase(true);
    /**
     * Inverts the wheel direction
     */
    /**
     * Sets PID
     */
    leftMaster.configAllowableClosedloopError(0, 50, 100);
    rightMaster.configAllowableClosedloopError(0, 50, 100);
  }
  public void drivePercentOutput(double left, double right) {
    /**
     * Using the created motors to drive using percent output
     * 
     */
    rightMaster.set(ControlMode.PercentOutput, right);
    rightSlave.set(ControlMode.PercentOutput, right);
    leftMaster.set(ControlMode.PercentOutput, left);
    leftSlave.set(ControlMode.PercentOutput, left);
  }
  public void drivePosition(int position) {
    /**
     * drive PID using position
     */
    leftMaster.set(ControlMode.Position,position);
    rightMaster.set(ControlMode.Position,position);
    leftSlave.set(ControlMode.Follower, RobotMap.LEFT_MASTER_MOTOR);
    rightSlave.set(ControlMode.Follower, RobotMap.RIGHT_MASTER_MOTOR);
  }
  public void setPosition(int position) {
    leftMaster.setSelectedSensorPosition(position,0,100);
    rightMaster.setSelectedSensorPosition(position,0,100);
  }
  public int getLeftPosition() {
    return leftMaster.getSelectedSensorPosition(0);
  }
  public int getRightPosition() {
    return rightMaster.getSelectedSensorPosition(0);
  }
  /**
   * sets values for PIDF
   * @param p
   * @param i
   * @param d
   * @param f
   */
  public void setPIDFValues(double p,double i,double d,double f){
    leftMaster.config_kF(0,f,100);
    rightMaster.config_kF(0,f,100);
    leftMaster.config_kP(0,p,100);
    rightMaster.config_kP(0,p,100);
    leftMaster.config_kI(0,i,100);
    rightMaster.config_kI(0,i,100);
    leftMaster.config_kD(0,d,100);
    rightMaster.config_kD(0,d,100);
  }
  public void stop(){
    /**
     * stops drivePercentOutput by setting Left and Right to 0
     */
    this.drivePercentOutput(0, 0);
  }

  /**
   * creating getInstance() method by returning INSTANCE
   * @return
   */
  public static DriveSystem getInstance() {
    return INSTANCE;
  }
  /**
   * makes sure instance isn't created more than once
   */
  public static void initialize() {
    if (INSTANCE == null){
      INSTANCE = new DriveSystem();
    }
  }
  /**
   * sets the target speed value of the motors in RPM
   * multipies left/right by the maximum velocity of the robot and by 
   * 4096(ticks per rotation) divided by 600 milliseconds (1 minute)
   * @param right
   * @param left
   */
  public void driveVelocity (double right, double left){
    targetL = left * MAX_VELOCITY * 4096 / 600;
    targetR = right * MAX_VELOCITY * 4096 / 600;
    /**
     * creates new drive control mode, Velocity
     */
    leftMaster.set (ControlMode.Velocity, targetL);
    leftSlave.set (ControlMode.Follower, RobotMap.LEFT_MASTER_MOTOR);
    rightMaster.set (ControlMode.Velocity, targetR);
    rightSlave.set (ControlMode.Follower, RobotMap.RIGHT_MASTER_MOTOR);
  }
  /**
   * gets motor controller temp
   * @return
   */
  public double[] getTemperature() {
    double[] output = {leftMaster.getTemperature(), leftSlave.getTemperature(), rightMaster.getTemperature(), rightSlave.getTemperature()};
    return output;
  }
  /**
   * sets the peak output for the motors
   */
  public void setPeakOutput(double output) {
    rightMaster.configPeakOutputForward(output, 100);
    rightSlave.configPeakOutputForward(output, 100);
    leftMaster.configPeakOutputForward(output, 100);
    leftSlave.configPeakOutputForward(output, 100);
  }
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    this.setDefaultCommand(new DriveVelocity());
  }
}
