package frc.robot.subsystems;

// Import WPI libraries
import edu.wpi.first.wpilibj.command.Subsystem;

// Use this for Spark motor controllers
import edu.wpi.first.wpilibj.Spark;
/* 
// Use this for Talon motor controllers
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
*/

import frc.robot.Robot;

public class Climber extends Subsystem {

    private final Spark sparkMotor;
    /* 
    private final WPI_TalonSRX talonMotor;
    */
    private double speed;
    private boolean printDebug;

    // Default constructor
    public Climber () {
        speed = 0.4;
        sparkMotor = new Spark (1);
        sparkMotor.setInverted(false);
        printDebug = false;
    }
        
    // Intialized constructor, accepts a channel for initialization
    /**
     * @param motorIDInput the unique channel ID of the motor
     */
    public Climber (int motorIDInput) {
        // speed = Robot.m_oi.getSpeed();
        speed = 0.4;

        sparkMotor = new Spark(motorIDInput);
        sparkMotor.setInverted(false);

        if (printDebug) {
            System.out.println("Climb: initialized constructor");
        }
    }

    @Override
    public void initDefaultCommand() {
    	
        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }

    @Override
    public void periodic() {

        // speed = Robot.m_oi.getSpeed(); 
        //Cargo Arm Speed, change if need faster.   	
        speed = .71;
        
            //System.out.println("Cargo Saftey off.");

    		if (Robot.m_oi.getClimbUp()) {
                if (printDebug) {
                    System.out.println("Climb: up speed = " + speed);
                }
                sparkMotor.setInverted(false);  // do not reverse motor
                sparkMotor.set(speed);          // activate motor
    		
    		} else if (Robot.m_oi.getClimbDown()) {
                if (printDebug) {
                    System.out.println("IntakeArm: retract speed = " + speed);
                }
                sparkMotor.setInverted(true);   // reverse motor
                sparkMotor.set(speed);
                
    		} else {  // else no hand button pressed, so stop motor
                sparkMotor.set(0);
            }
    	
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

