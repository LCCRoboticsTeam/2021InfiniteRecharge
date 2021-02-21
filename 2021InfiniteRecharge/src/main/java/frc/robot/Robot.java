/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.cscore.UsbCamera;
// import edu.wpi.cscore.VideoMode;
// import edu.wpi.cscore.VideoMode.PixelFormat;
import edu.wpi.cscore.VideoSink;

import edu.wpi.first.cameraserver.CameraServer;
// import edu.wpi.first.wpilibj.ADXL362;
// import edu.wpi.first.wpilibj.ADXRS450_Gyro;
// import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.DriverStation;
// import edu.wpi.first.wpilibj.I2C;
// import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
// import edu.wpi.first.wpilibj.interfaces.Accelerometer;
// import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.PiCamera.PiCamera;
import frc.robot.PiCamera.PiCamera.PiCameraRegions;
import frc.robot.PiCamera.PiCamera.PiCameraRegion;

// import com.kauailabs.navx.frc.AHRS;

import frc.robot.commands.*;
import frc.robot.subsystems.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  public static OI m_oi;                  // access to user interface
  public static PiCamera m_piCamera;
  public static DriveTrain driveTrain;    // access to drive train
  public UsbCamera camera1;
  public UsbCamera camera2;
  public VideoSink server;

  double speedLimit;
  double rotateLimit;
  double strafeLimit;

  // public static Navigator navigator;

  // ADXRS450_Gyro gyro;
  // ADXL362 accel;
  // BuiltInAccelerometer bAccel;

  // AHRS navx;

  Timer autoTimer;

  int outputCount;

  int currentCamera;

  Command m_autonomousCommand;
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {

    speedLimit = 0.9;
		rotateLimit = 0.65;
		strafeLimit = 0.8;

    System.out.println("robotInit: entered subroutine");

    m_piCamera = new PiCamera();

    m_piCamera.Connect("10.55.14.12", 5800);

    // Create user interface access
    m_oi = new OI();
    System.out.println("robotInit created OI");

    // navigator = new Navigator();
    // System.out.println("robotInit created navigator");

    autoTimer = new Timer();

    // Initialize the mapping of motor controllers to the Phoenix tuner designated IDs
    // Spark and Talon motors are mapped in here
    //RobotMap.setPrintDebug(true);

    RobotMap.init();
    System.out.println("robotInit RobotMap.init() completed");

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    
    camera1 = CameraServer.getInstance().startAutomaticCapture(0);
    // camera2 = CameraServer.getInstance().startAutomaticCapture(1);
    
    server = CameraServer.getInstance().getServer();
    
    // currentCamera = 0;
    // gyro.calibrate();
    // gyro.reset();
    // environmental = new Environmental();
    // SmartDashboard.putData(environmental);
    
    // Create the drive train
    driveTrain = new DriveTrain();

    // m_chooser.setDefaultOption("Default Auto", new ExampleCommand());
    //m_chooser.setDefaultOption("Autonomous Command", new AutonomousCommand(driveTrain.myDrive));
    // chooser.addOption("My Auto", new MyAutoCommand());
    m_autonomousCommand = new AutonomousCommand(driveTrain.myDrive, "Vision");

    // Publish Mode and Dial value to SmartDashboard
    //SmartDashboard.putData("Auto mode", m_chooser);
    SmartDashboard.putBoolean("Joystick 0 Connected", DriverStation.getInstance().isJoystickConnected(0));
    SmartDashboard.putBoolean("Joystick 1 Connected", DriverStation.getInstance().isJoystickConnected(1));
    SmartDashboard.putData("Auto mode", m_chooser);
    SmartDashboard.putNumber("LPDial", m_oi.getSpeed());

    // navx = new AHRS(I2C.Port.kOnboard);

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
    //SmartDashboard.putData("Auto mode", m_chooser);
    SmartDashboard.putNumber("LPDial", m_oi.getSpeed());
    SmartDashboard.putNumber("Trigger", m_oi.getRTrigger());

    // SmartDashboard.putNumber("Navx Roll: ", navx.getRoll());
    // SmartDashboard.putNumber("Navx Yaw: ", navx.getYaw());
    // SmartDashboard.putNumber("Navx Angle: ", navx.getAngle());
    // SmartDashboard.putNumber("Navx Update Rate: ", navx.getActualUpdateRate());
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   * You can use it to reset any subsystem information you want to clear when
   * the robot is disabled.
   */
  @Override
  public void disabledInit() {
    outputCount = 0;
    
    //SmartDashboard.putNumber("LPDial", 0.0);
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();

    

    if(Robot.m_piCamera.GetRegions() != null) {
      PiCameraRegions regions = Robot.m_piCamera.GetRegions();
      if (regions.GetRegionCount() > 0) {
          for (int i = 0; i < regions.GetRegionCount(); i++) {
              PiCameraRegion region = regions.GetRegion(i);
              if (region.m_bounds != null) {
                  int top = region.m_bounds.m_top;
                  int bottom = region.m_bounds.m_bottom;
                  int left = region.m_bounds.m_left;
                  int right = region.m_bounds.m_right;
                  int height = Math.abs(bottom-top);
                  int width = Math.abs(right-left);
                  boolean square = (Math.abs(height-width)<20) ? true : false;
                  if (square) {
                      System.out.print("Region " + i + "; ");
                      System.out.print("Top: " + top + "; ");
                      System.out.print("Bottom: " + bottom + "; ");
                      System.out.print("Left: " + left + "; ");
                      System.out.print("Right: " + right + "; ");
                      System.out.print("Turn Direction: ");
                      // System.out.println(((right+left)/2)<320 ? "left; " : "right; ");

                      int difference = ((right+left)/2)-320;

                      if (difference < -60) {
                          System.out.println("left, " + ((6.84*(Math.pow(10, -6)))*Math.pow(difference, 2))+0.35);
                      } else if (difference > 60) {
                          System.out.println("right, " + ((6.84*(Math.pow(10, -6)))*Math.pow(difference, 2))+0.35);
                      } else {
                          System.out.println("center, ");
                      }
                  }
              }
          }
      } 
  }

    // System.out.println(outputCount + ": " + Robot.navigator.getDistanceToTarget(Robot.navigator.getGearHeight()));
    // outputCount++;
    // SmartDashboard.putNumber("Gear Center", Robot.navigator.getGearCenter());
    // SmartDashboard.putNumber("Distance Target", Robot.navigator.getDistanceToTarget(Robot.navigator.getGearHeight()));
    // SmartDashboard.putNumber("Gear Height", Robot.navigator.getGearHeight());
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
    // m_autonomousCommand = m_chooser.getSelected();


    
    // String autoSelected = SmartDashboard.getString("Auto Selector",
    // "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
    // = new MyAutoCommand(); break; case "Default Auto": default:
    // autonomousCommand = new ExampleCommand(); break; }
     

    // schedule the autonomous command (example)
    Scheduler.getInstance().run();
    
    if (m_autonomousCommand != null) {
      m_autonomousCommand.start();
    }
    //autoTimer.reset();
		//autoTimer.start();
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }

    if (RobotMap.solenoid.getSoleState() != 0) {

      RobotMap.solenoid.setSoleState(0);

    }

    if (RobotMap.climber.getClimbState() != 0) {
      RobotMap.climber.setClimbState(0);
    }

  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();

    if(Robot.m_piCamera.GetRegions() != null) {
      PiCameraRegions regions = Robot.m_piCamera.GetRegions();
      if (regions.GetRegionCount() > 0) {
          for (int i = 0; i < regions.GetRegionCount(); i++) {
              PiCameraRegion region = regions.GetRegion(i);
              if (region.m_bounds != null) {
                  int top = region.m_bounds.m_top;
                  int bottom = region.m_bounds.m_bottom;
                  int left = region.m_bounds.m_left;
                  int right = region.m_bounds.m_right;
                  int height = Math.abs(bottom-top);
                  int width = Math.abs(right-left);
                  boolean square = (Math.abs(height-width)<20) ? true : false;
                  if (square) {
                      System.out.print("Region " + i + "; ");
                      System.out.print("Top: " + top + "; ");
                      System.out.print("Bottom: " + bottom + "; ");
                      System.out.print("Left: " + left + "; ");
                      System.out.print("Right: " + right + "; ");
                      System.out.print("Turn Direction: ");
                      // System.out.println(((right+left)/2)<320 ? "left; " : "right; ");

                      if (((right+left)/2)-320 < -20) {
                          System.out.println("left; ");
                      } else if (((right+left)/2)-320 > 20) {
                          System.out.println("right; ");
                      } else {
                          System.out.println("center; ");
                      }
                  }
              }
          }
      } 
  }

    // myDrive.driveCartesian(
		// 	(Robot.m_oi.getY() * rotateLimit),       // set Y speed
		// 	(Robot.m_oi.getX()  * strafeLimit),      // set X speed
		// 	(Robot.m_oi.getRotate() * speedLimit), // set rotation rate
    // 	0);
    
    

    // SmartDashboard.putNumber("Gear Center", Robot.navigator.getGearCenter());
    // SmartDashboard.putNumber("Distance Target", Robot.navigator.getDistanceToTarget(Robot.navigator.getGearHeight()));
    // SmartDashboard.putNumber("Gear Height", Robot.navigator.getGearHeight());

    // if (m_oi.getYButton()) {

    //   server.setSource(camera1);
      
    // } else if (m_oi.getBButton()) {
      
    //   server.setSource(camera2);
      
    // }
    
      
      // System.out.print("Accel X: " + accel.getX() + ", ");
      // System.out.print("Accel Y: " + accel.getY() + ", ");
      // System.out.println("Accel Z: " + accel.getZ());

      //System.out.println("Gyro Angle: " + gyro.getAngle());

    //SmartDashboard.putNumber("Speed Value:", m_oi.getSpeed());
    // SmartDashboard.putNumber("Controller X: ", m_oi.getX());

  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
