package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
/**
 * The system that allows the robot to elevator cubes and shoot them out
 * @author 4533 Programming Team
 *
 */
public class ElevatorSystem extends Subsystem {
    private TalonSRX elevatorMotor;
	private static ElevatorSystem INSTANCE;
	DigitalInput limitSwitch;
  	DigitalInput limitSwitchTwo;
  	DigitalInput limitSwitchThree;
  	DigitalInput limitSwitchFour;
	
	/**
	 * Sets up the motors for elevator
	 */
	public ElevatorSystem() {
		elevatorMotor =new TalonSRX(RobotMap.ELEVATOR_MOTOR);
		elevatorMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,0,100);
		elevatorMotor.setSensorPhase(true);
		elevatorMotor.configAllowableClosedloopError(0, 50, 100);
		limitSwitch = new DigitalInput(0);
		limitSwitchTwo = new DigitalInput(1);
		limitSwitchThree = new DigitalInput(3);
		limitSwitchFour = new DigitalInput(5);
	}
	public void elevatorPercentOutput(double speed) {
		elevatorMotor.set(ControlMode.PercentOutput, speed);
	}
	/**
	 * Instance is instantiated as a new ElevatorSystem
	 */
	public static void initialize() {
		if(INSTANCE == null) {
			INSTANCE = new ElevatorSystem();
		}
	}
	
	/**
	 * @return Instance: An instance of ElevatorSystem
	 */
	public static ElevatorSystem getInstance() {
		return INSTANCE;
	}
	/**
	 * Sets the motors to bring the cube in
	 */
	public void up(double percent) {
		elevatorMotor.set(ControlMode.PercentOutput, -percent);
	}
	/**
	 * Sets the motors to push the cube out
	 */
	public void down(double percent) {
		elevatorMotor.set(ControlMode.PercentOutput, percent);
	}
	/**
	 * Stops the motors
	 */
	public void stop() {
		elevatorMotor.set(ControlMode.PercentOutput, 0);
	}
	public void setPIDFValues(double p,double i,double d,double f){
		elevatorMotor.config_kF(0,f,100);
		elevatorMotor.config_kP(0,p,100);
		elevatorMotor.config_kI(0,i,100);
		elevatorMotor.config_kD(0,d,100);
	  }
	/**
	 * Detects if there is a cube in the Elevator
	 * @return False because we have no sensor on the elevator yet
	 */
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
	}

	public void elevatorMovement (int postion) {
		elevatorMotor.set(ControlMode.PercentOutput, postion);
	}

	public int getPosition() {
		return elevatorMotor.getSelectedSensorPosition(0);
	}

	public void setPosition(int distance) {
		elevatorMotor.setSelectedSensorPosition(distance);
	}
	public boolean getLimitSwitchIntake(){
		return limitSwitch.get();
	}
	public boolean getLimitSwitchMid(){
		return limitSwitchTwo.get();
	}
	public boolean getLimitSwitchBall(){
		return limitSwitchThree.get();
	}
	public boolean getLimitSwitchBottom(){
		return limitSwitchFour.get();
	}

}