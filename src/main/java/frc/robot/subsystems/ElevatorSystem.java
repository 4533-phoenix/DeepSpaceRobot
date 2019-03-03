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
	//creates the elevator motor object
    private TalonSRX elevatorMotor;
	private static ElevatorSystem INSTANCE;
	//creates the limit switch objects for the elevators
	DigitalInput limitSwitch;
  	DigitalInput limitSwitchTwo;
  	DigitalInput limitSwitchThree;
  	DigitalInput limitSwitchFour;
	
	/**
	 * Sets up the motors for elevator
	 */
	public ElevatorSystem() {
		//instanciating the object elevatorMotor
		elevatorMotor = new TalonSRX(RobotMap.ELEVATOR_MOTOR);
		//gets the values for the encoder
		elevatorMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder,0,100);
		//configures drive sensors
		elevatorMotor.setSensorPhase(true);
		//how presice the elevator is 
		elevatorMotor.configAllowableClosedloopError(0, 50, 100);
		//instanciates limit switch objects
		limitSwitch = new DigitalInput(0);
		limitSwitchTwo = new DigitalInput(1);
		limitSwitchThree = new DigitalInput(3);
		limitSwitchFour = new DigitalInput(5);
		//configues Ramp Rate (seconds)
		elevatorMotor.configClosedloopRamp(1);
		elevatorMotor.setInverted(true);
	}
	// creates 
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
	 * Stops the motors
	 */
	public void stop() {
		elevatorMotor.set(ControlMode.PercentOutput, .1);
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
		elevatorMotor.set(ControlMode.Position, postion);
	}

	public int getPosition() {
		return elevatorMotor.getSelectedSensorPosition(0);
	}

	public void setPosition(int distance) {
		elevatorMotor.setSelectedSensorPosition(distance);
		System.out.println("Distance Set: " + distance);
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