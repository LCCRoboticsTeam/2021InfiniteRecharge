package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
// import edu.wpi.first.wpilibj.drive.MecanumDrive;
import frc.lib.MecanumDrive;
import frc.robot.Robot;
import frc.robot.commands.JoystickInput;

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
		speedLimit = .5;
		rotateLimit = .35;
		strafeLimit = .8;

		//Instantiate Mecanum drive with 775 motors
		myDrive = new MecanumDrive(frontLeft775, backLeft775, frontRight775, backRight775);

		//Instantiate Mecanum drive with CIM motors
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
				(Robot.m_oi.getX() * strafeLimit),      // set X speed
				(Robot.m_oi.getRotate() * rotateLimit), // set rotation rate
				0);                                     // gyro angle 
    	
    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

}

