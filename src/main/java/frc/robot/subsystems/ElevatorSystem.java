package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
/**
 * The system that allows the robot to elevator cubes and shoot them out
 * @author 4533 Programming Team
 *
 */
public class ElevatorSystem extends Subsystem {
    private VictorSPX elevatorMotor;
	private static ElevatorSystem INSTANCE;
	
	/**
	 * Sets up the motors for elevator
	 */
	public ElevatorSystem() {
		elevatorMotor = new VictorSPX(RobotMap.INTAKE_MOTOR);
		elevatorMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,0,100);
		elevatorMotor.setSensorPhase(true);
		elevatorMotor.configAllowableClosedloopError(0, 50, 100);
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
}