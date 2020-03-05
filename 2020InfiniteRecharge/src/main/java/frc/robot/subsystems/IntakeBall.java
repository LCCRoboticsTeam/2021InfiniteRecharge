package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
/*
// Use this for Spark motor controllers
import edu.wpi.first.wpilibj.Spark;
*/
// Use this for Talon motor controllersA
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;


// import edu.wpi.first.wpilibj.SpeedController;

import frc.robot.Robot;

/**
 *
 */
public class IntakeBall extends Subsystem {

    //private final SpeedController extender = RobotMap.armHand;
    //private final Spark sparkMotor;
    
    private final WPI_TalonSRX talonMotor;
    
    private double speed;
    private boolean printDebug;

    // Intialized constructor, accepts a channel for initialization
    /**
     * @param motorIDInput the unique channel ID of the motor
     */
    public IntakeBall (int motorIDInput) {
    
        speed = 0.0;

        talonMotor = new WPI_TalonSRX(motorIDInput);
        talonMotor.setInverted(false);
    }

    // Intialized constructor
    /**
     * @param motorIDInput the unique channel ID of the motor
     * @param speedInput the speed of the motor
     */
    public IntakeBall (int motorIDInput, double speedInput) {

        speed = speedInput;
        talonMotor = new WPI_TalonSRX(motorIDInput);
        talonMotor.setInverted(false);
    }

    @Override
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }

    @Override
    public void periodic() {
        // Put code here to be run every loop

    	// speed = Robot.m_oi.getSpeed();
    	//speed = .78;
    	
    	// if (Robot.m_oi.getBallIn()) {
        //     if (printDebug) {
        //         System.out.println("IntakeArm: extend speed = " + -1.0 * speed);
        //     }
        //     sparkMotor.setInverted(false);  // do not reverse motor
        //     sparkMotor.set(speed);          // activate motor

    	// } else if (Robot.m_oi.getBallOut()) {
        //     if (printDebug) {
        //         System.out.println("IntakeArm: extend speed = " + -1.0 * speed);
        //     }

        //     sparkMotor.setInverted(true);  // reverse motor
        //     sparkMotor.set(speed);         // activate motor

    	// } else {
        //     sparkMotor.set(0);
    	// }
    	
    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public boolean isPrintDebug() {
        return printDebug;
    }

    public void setPrintDebug(boolean printDebug) {
        this.printDebug = printDebug;
    }
}