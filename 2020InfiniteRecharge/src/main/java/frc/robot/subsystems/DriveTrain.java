package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
// import edu.wpi.first.wpilibj.drive.MecanumDrive;
import frc.lib.MecanumDrive;
import frc.robot.Robot;
import frc.robot.commands.JoystickInput;
import edu.wpi.first.wpilibj.smartdashboard.*;

public class DriveTrain extends Subsystem {

	// Use the unique ID number that was assigned with the Phoenix tuner tool
	private final int kFrontLeftCIM = 6;
	private final int kFrontLeft775 = 5;

	private final int kFrontRightCIM = 3;
	private final int kFrontRight775 = 4;

	private final int kBackLeftCIM = 7;
	private final int kBackLeft775 = 8;

	private final int kBackRightCIM = 2;
	private final int kBackRight775 = 1;
	
	// Create CIM identifiers
	WPI_TalonSRX frontLeftMotor;
	WPI_TalonSRX rearLeftMotor;
	WPI_TalonSRX frontRightMotor;
	WPI_TalonSRX rearRightMotor;
	
	// Create 775 identifiers
	WPI_TalonSRX frontLeft775;
	WPI_TalonSRX frontRight775;
	WPI_TalonSRX backLeft775;
	WPI_TalonSRX backRight775;
	
	// Drive Train global parameters
	private double speedLimit;
	private double rotateLimit;
	private double strafeLimit;

	public MecanumDrive myDrive;

	private int Wheel;

	// Constructor for DriveTrain instantiation, with initializers
	public DriveTrain (double speedLimit, double rotateLimit, double strafeLimit) {
		
		// Create motor controllers and assign to the phoenix tuner specified motor identifier
		frontLeftMotor = new WPI_TalonSRX(kFrontLeftCIM);
		rearLeftMotor = new WPI_TalonSRX(kBackLeftCIM);
		frontRightMotor = new WPI_TalonSRX(kFrontRightCIM);
		rearRightMotor = new WPI_TalonSRX(kBackRightCIM);
		
		frontLeft775 = new WPI_TalonSRX(kFrontLeft775);
		frontRight775 = new WPI_TalonSRX(kFrontRight775);
		backLeft775 = new WPI_TalonSRX(kBackLeft775);
		backRight775 = new WPI_TalonSRX(kBackRight775);
		
		// set speed and rotate limit based on initialization parameters
		this.speedLimit = speedLimit;
		this.rotateLimit = rotateLimit;
		this.strafeLimit = strafeLimit;

		//Set "Wheel" for calibration if enabled
		Wheel = 0;

		//Instantiate Mecanum drive with 775 motors
		myDrive = new MecanumDrive(frontLeft775, backLeft775, frontRight775, backRight775);
		
		// Slave CIM motors to 775 motors
		frontLeftMotor.follow(frontLeft775);
		frontRightMotor.follow(frontRight775);
		rearLeftMotor.follow(backLeft775);
		rearRightMotor.follow(backRight775);
		
		System.out.println("Initialized constructor completed");
	}
	
	// Default DriveTrain constructor
	public DriveTrain() {
		
		// Create motor controllers and assign to the phoenix tuner specified motor identifier
		frontLeftMotor = new WPI_TalonSRX(kFrontLeftCIM);
		rearLeftMotor = new WPI_TalonSRX(kBackLeftCIM);
		frontRightMotor = new WPI_TalonSRX(kFrontRightCIM);
		rearRightMotor = new WPI_TalonSRX(kBackRightCIM);
		
		frontLeft775 = new WPI_TalonSRX(kFrontLeft775);
		frontRight775 = new WPI_TalonSRX(kFrontRight775);
		backLeft775 = new WPI_TalonSRX(kBackLeft775);
		backRight775 = new WPI_TalonSRX(kBackRight775);
		
		// Set speedLimit and rotateLimit to default values
		speedLimit = 0.9;
		rotateLimit = 0.45;
		strafeLimit = 1.0;

		//Set "Wheel" for calibration if enabled
		Wheel = 1;

		//Instantiate Mecanum drive with 775 motors
		myDrive = new MecanumDrive(frontLeft775, backLeft775, frontRight775, backRight775);

		//Instantiate Mecanum drive with CIM motors (Comment out code to make them follow 775's first)
		//myDrive = new MecanumDrive(frontLeftMotor, rearLeftMotor, frontRightMotor, rearRightMotor);


		// Slave CIM motors to 775 motors
		frontLeftMotor.follow(frontLeft775);
		frontRightMotor.follow(frontRight775);
		rearLeftMotor.follow(backLeft775);
		rearRightMotor.follow(backRight775);
		
		System.out.println("Default constructor completed");
	}
	
    @Override
    public void initDefaultCommand() {
		// Create joystick controller
		setDefaultCommand(new JoystickInput());
    }

	// Periodic run continuously during teleoperate mode
    @Override
    public void periodic() {
		// myDrive.

		// System.out.println(Robot.m_oi.getSpeed());
		// SmartDashboard.putNumber("Dial Output: ", Robot.m_oi.getSpeed());

		// //Wheel Speed Limits

		// SmartDashboard.putNumber("Front Left Percent", myDrive.getFLSpeed());
		// SmartDashboard.putNumber("Front Right Percent", myDrive.getFRSpeed());
		// SmartDashboard.putNumber("Rear Left Percent", myDrive.getRLSpeed());
		// SmartDashboard.putNumber("Rear Right Percent", myDrive.getRRSpeed());

		//Test Code for Selecting Calibration Motor 
		//***COMMENT OUT BEFORE REAL GAME USE***
		// if (Robot.m_oi.getArmDown()) {
		// 	// System.out.println("Front Left Wheel Selected");
		// 	Wheel = 1;
		// 	SmartDashboard.putNumber("Wheel Selected: ", Wheel);
			
		// } else if (Robot.m_oi.getArmUp()) {
		// 	// System.out.println("Back Left Wheel Selected");
		// 	Wheel = 2;
		// 	SmartDashboard.putNumber("Wheel Selected: ", Wheel);
			
		// } else if (Robot.m_oi.getBallIn()) {
		// 	// System.out.println("Front Right Wheel Selected");
		// 	Wheel = 3;
		// 	SmartDashboard.putNumber("Wheel Selected: ", Wheel);
			
		// } else if (Robot.m_oi.getBallOut()) {
		// 	// System.out.println("Back Right Wheel Selected");
		// 	Wheel = 4;
		// 	SmartDashboard.putNumber("Wheel Selected: ", Wheel);
			
		// } else if (Robot.m_oi.getBlueButton()) {
		// 	// System.out.println("Back Right Wheel Selected");
		// 	Wheel = 0;
		// 	SmartDashboard.putNumber("Wheel Selected: ", Wheel);
		// } 

		// if (Wheel == 1) {

		// 	myDrive.setFLSpeed(Robot.m_oi.getSpeed());

		// } else if (Wheel == 2) {

		// 	myDrive.setRLSpeed(Robot.m_oi.getSpeed());

		// } else if (Wheel == 3) {

		// 	myDrive.setFRSpeed(Robot.m_oi.getSpeed());

		// } else if (Wheel == 4) {

		// 	myDrive.setRRSpeed(Robot.m_oi.getSpeed());

		// }

		// if (Robot.m_oi.getSafety()) {

		// 	speedLimit = 1.0;
		// 	strafeLimit = 1.0;

		// } else {

		// 	speedLimit = 0.5; 
		// 	strafeLimit = 0.8;
				
		// }



		//System.out.print ("strafeLimit: " + strafeLimit);
		//System.out.println(Robot.m_oi.getX() * strafeLimit);

		myDrive.driveCartesian(
			(Robot.m_oi.getY() * speedLimit),       // set Y speed
			(Robot.m_oi.getX()  * strafeLimit),      // set X speed
			(Robot.m_oi.getRotate() * rotateLimit), // set rotation rate
			0);                                     // gyro angle 
		
		// myDrive.driveCartesian(
		// 	(Robot.m_oi.getY()),       // set Y speed
		// 	(Robot.m_oi.getX()),      // set X speed
		// 	(Robot.m_oi.getRotate()), // set rotation rate
		// 	0); 

    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

}

