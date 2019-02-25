/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.net.ServerSocket;
import java.net.SocketException;
import java.io.*;
import java.net.*;
import java.net.InetAddress;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.*;
import frc.robot.utilities.SmartDashboardValues;
import frc.robot.commands.*;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SerialPort.Port;
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends IterativeRobot {
  public static OI oi;
  public SmartDashboardValues smartDashboardValues;
  public SendableChooser<String> autoChooser;
  Autonomous testing;
  CargoShipAuto auto;
  CargoMiddle midAuto;
  public SerialPort serial;
  public DatagramSocket serverSocket;
  public byte[] receiveData;
  public byte[] sendData;

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    DriveSystem.initialize();
    ElevatorSystem.initialize();
    IntakeSystem.initialize();
    m_oi = new OI();
    // chooser.addOption("My Auto", new MyAutoCommand());
    smartDashboardValues = new SmartDashboardValues();
    DriveSystem.getInstance().setPosition(0);
    ElevatorSystem.getInstance().setPosition(0);
    ElevatorSystem.getInstance().setPIDFValues(0.1, 0.0001, 0, 0);
    /*
     * 
     
    serial = new SerialPort(115200, Port.kUSB);
    //Setup the oDroid Communications
    try {
      serverSocket = new DatagramSocket(3641); //choose a port for your oDroid to send data to

    } catch (SocketException e) {
      e.printStackTrace();
    }
    receiveData = new byte [256];
    sendData = new byte[256];
    */
    autoChooser = new SendableChooser<>();
    autoChooser.addDefault("No Auto", "N");
    autoChooser.addObject("Left", "L");
    autoChooser.addObject("Right", "R");
    autoChooser.addObject("Middle", "M");
    //serial = new SerialPort(115200, Port.kUSB);
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   * You can use it to reset any subsystem information you want to clear when
   * the robot is disabled.
   */
  @Override
  public void disabledInit() {
    DriveSystem.getInstance().setPosition(0);
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
    smartDashboardValues.updateValue();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString code to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons
   * to the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() {
    
    /*
     * String autoSelected = SmartDashboard.getString("Auto Selector",
     * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
     * = new MyAutoCommand(); break; case "Default Auto": default:
     * autonomousCommand = new ExampleCommand(); break; }
     */

    // schedule the autonomous command (example)
   // if (m_autonomousCommand != null) {
    //  m_autonomousCommand.start();
    //}
    DriveSystem.getInstance().setPIDFValues(0.1, 0.0001, 0, 0);
    DriveSystem.getInstance().setPosition(0);
    if(autoChooser.getSelected().equals("L")){
      auto = new CargoShipAuto(true);
    }
    else if(autoChooser.getSelected().equals("R")){
      auto = new CargoShipAuto(false);
    }
    else if(autoChooser.getSelected().equals("M")){
      midAuto = new CargoMiddle();
    }
    if(auto != null || midAuto != null) {
      if(auto != null) {
        auto.start();
      }
      if(midAuto != null) {
        midAuto.start();
      }
    }
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
    smartDashboardValues.updateValue();
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (testing != null) {
      testing.cancel();
    }
    DriveSystem.getInstance().setPIDFValues(.15, 0, 2.5, 0.243);
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
    smartDashboardValues.updateValue();
    //byte[] jVData = serial.read(0);
    //while(jVData[0] != 126) {
      //System.out.println((serial.getBytesReceived()));
    //}
    System.out.println("Pos: " + ElevatorSystem.getInstance().getPosition());
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
    smartDashboardValues.updateValue();
  }
  
  public void sendData(){
    InetAddress _ip = null;
    try {
      _ip = InetAddress.getByName("oDroid.local");
    } catch (Exception e){
      System.out.println(e);
    }
     
    int _port = 3641;
    String requestValue = "0";
    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, _ip, _port);
    try {
        serverSocket.send(sendPacket);
        
    } catch (IOException e) {
        e.printStackTrace();
    }
 }

 public void receiveData(){
     DatagramPacket receivePacket = new DatagramPacket (receiveData, receiveData.length);
     try {
         serverSocket.receive(receivePacket);
     } catch(IOException e) {
         e.printStackTrace();      
     }
     String incoming = new String(receivePacket.getData());
     String[] parts = incoming.split(" ");
     String part1_raw = parts[0];
     double part1_double = Double.parseDouble(part1_raw);
     System.out.println("RECEIVED: " + part1_raw);
 }

  
}

