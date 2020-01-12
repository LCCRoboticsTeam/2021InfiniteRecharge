package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
// import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import frc.robot.Robot;
import frc.robot.commands.JoystickInput;

public class DriveTrain extends Subsystem {

	private final int kFrontLeftCIM = 1;
	private final int kFrontLeft775 = 0;
	private final int kFrontRightCIM = 6;
	private final int kFrontRight775 = 7;
	private final int kBackLeftCIM = 2;
	private final int kBackLeft775 = 3;
	private final int kBackRightCIM = 5;
	private final int kBackRight775 = 4;
	
	WPI_TalonSRX frontLeftMotor;
	WPI_TalonSRX rearLeftMotor;
	WPI_TalonSRX frontRightMotor;
	WPI_TalonSRX rearRightMotor;
	
	WPI_TalonSRX frontLeft775;
	WPI_TalonSRX frontRight775;
	WPI_TalonSRX backLeft775;
	WPI_TalonSRX backRight775;
	
	private double speedLimit;
	private double rotateLimit;
	
	public MecanumDrive myDrive;

	public DriveTrain (double speedLimit, double rotateLimit) {
		
		frontLeftMotor = new WPI_TalonSRX(kFrontLeftCIM);
		rearLeftMotor = new WPI_TalonSRX(kBackLeftCIM);
		frontRightMotor = new WPI_TalonSRX(kFrontRightCIM);
		rearRightMotor = new WPI_TalonSRX(kBackRightCIM);
		
		frontLeft775 = new WPI_TalonSRX(kFrontLeft775);
		frontRight775 = new WPI_TalonSRX(kFrontRight775);
		backLeft775 = new WPI_TalonSRX(kBackLeft775);
		backRight775 = new WPI_TalonSRX(kBackRight775);
		
		this.speedLimit = speedLimit;
		this.rotateLimit = rotateLimit;
		
		myDrive = new MecanumDrive(frontLeft775, backLeft775, frontRight775, backRight775);
		
		frontLeftMotor.follow(frontLeft775);
		frontRightMotor.follow(frontRight775);
		rearLeftMotor.follow(backLeft775);
		rearRightMotor.follow(backRight775);
		
		System.out.println("Constructors are working");
	
	}
	
	public DriveTrain() {
		
		frontLeftMotor = new WPI_TalonSRX(kFrontLeftCIM);
		rearLeftMotor = new WPI_TalonSRX(kBackLeftCIM);
		frontRightMotor = new WPI_TalonSRX(kFrontRightCIM);
		rearRightMotor = new WPI_TalonSRX(kBackRightCIM);
		
		frontLeft775 = new WPI_TalonSRX(kFrontLeft775);
		frontRight775 = new WPI_TalonSRX(kFrontRight775);
		backLeft775 = new WPI_TalonSRX(kBackLeft775);
		backRight775 = new WPI_TalonSRX(kBackRight775);
		
		/* Speed controller is commented out due to issues after porting to VS */

		// speedLimit = Robot.m_oi.getSpeed();
		// rotateLimit = Robot.m_oi.getSpeed();
		
		// Temporary fix for speedLimit and rotateLimit
		speedLimit = .5;
		rotateLimit = .35;


		myDrive = new MecanumDrive(frontLeft775, backLeft775, frontRight775, backRight775);
		
		frontLeftMotor.follow(frontLeft775);
		frontRightMotor.follow(frontRight775);
		rearLeftMotor.follow(backLeft775);
		rearRightMotor.follow(backRight775);
		
		System.out.println("Constructors are working");
		
	}
	
    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new JoystickInput());
    }

    @Override
    public void periodic() {
			// myDrive.
			if (Robot.m_oi.getSafety()) {

				speedLimit = 1;

			} else {

				speedLimit = .5; 

			}

			rotateLimit = .35;

			myDrive.driveCartesian((Robot.m_oi.getRotate() * rotateLimit), (Robot.m_oi.getX() * .75), (Robot.m_oi.getY() * speedLimit), 0);
    	
    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

}

