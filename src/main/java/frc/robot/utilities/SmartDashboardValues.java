/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.utilities;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.DrivePosition;
import frc.robot.subsystems.DriveSystem;
import frc.robot.subsystems.ElevatorSystem;
import edu.wpi.first.wpilibj.DriverStation;
/**
 * Add your docs here.
 */
public class SmartDashboardValues {
    public SmartDashboardValues() {
       SmartDashboard.putNumber("Current Distance Traveled This Match", (int)  (-DriveSystem.getInstance().getRightPosition() / (4096/(6*Math.PI)))/12);
       //SmartDashboard.putNumberArray("Talon SRX Temperatures", DriveSystem.getInstance().getTemperature());
       SmartDashboard.putString("Drive Position", "");
       SmartDashboard.putData("Drive Distance", new DrivePosition(48));
       SmartDashboard.putNumber("Max Velocity", DriveSystem.MAX_VELOCITY);
       SmartDashboard.putNumber("StringPotPosition", ElevatorSystem.getInstance().position());
    }
    public void updateValue() {
        SmartDashboard.putNumber("Current Distance Traveled This Match",(int) (-DriveSystem.getInstance().getRightPosition() / (4096/(6*Math.PI)))/12);
        //SmartDashboard.putNumberArray("Talon SRX Temperatures", DriveSystem.getInstance().getTemperature());
        SmartDashboard.putData("Drive Distance", new DrivePosition(48));
        DriveSystem.MAX_VELOCITY = SmartDashboard.getNumber("Max Velocity", 250);
        SmartDashboard.putNumber("Actual Max Velocity", DriveSystem.MAX_VELOCITY);
        SmartDashboard.putNumber("Match Time", DriverStation.getInstance().getMatchTime());
        SmartDashboard.putNumber("StringPotPosition", ElevatorSystem.getInstance().position());
    }
}