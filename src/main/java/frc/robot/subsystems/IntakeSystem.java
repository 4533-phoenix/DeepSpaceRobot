package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
/**
 * The system that allows the robot to intake cubes and shoot them out
 * @author 4533 Programming Team
 *
 */
public class IntakeSystem extends Subsystem {
    private VictorSPX intakeMotor;
	private static IntakeSystem INSTANCE;
	
	/**
	 * Sets up the motors for intake
	 */
	public IntakeSystem() {
		intakeMotor = new VictorSPX(RobotMap.INTAKE_MOTOR);
	}
	
	/**
	 * Instance is instantiated as a new IntakeSystem
	 */
	public static void initialize() {
		if(INSTANCE == null) {
			INSTANCE = new IntakeSystem();
		}
	}
	
	/**
	 * @return Instance: An instance of IntakeSystem
	 */
	public static IntakeSystem getInstance() {
		return INSTANCE;
	}
	/**
	 * Sets the motors to bring the cube in
	 */
	public void in(double percent) {
		intakeMotor.set(ControlMode.PercentOutput, -percent);
	}
	/**
	 * Sets the motors to push the cube out
	 */
	public void out(double percent) {
		intakeMotor.set(ControlMode.PercentOutput, percent);
	}
	/**
	 * Stops the motors
	 */
	public void stop() {
		intakeMotor.set(ControlMode.PercentOutput, 0);
	}
	/**
	 * Detects if there is a cube in the Intake
	 * @return False because we have no sensor on the intake yet
	 */
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
	}
}