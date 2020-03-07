package frc.robot.subsystems;

// Import WPI libraries
import edu.wpi.first.wpilibj.command.Subsystem;
/*
// Use this for Spark motor controllers
import edu.wpi.first.wpilibj.Spark;
*/
// Use this for Talon motor controllers
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import frc.robot.Robot;

//
/**
 * Class IntakeArm
 * @class IntakeArm class is for controlling the position of the intake arm
 */
public class IntakeArm extends Subsystem {
    /*
    private final Spark sparkMotor;
    */
    private final WPI_TalonSRX talonMotor;
    
    private double speed;
    private boolean printDebug;

    // Default constructor
    public IntakeArm () {
        speed = 0.0;
        talonMotor = new WPI_TalonSRX(1);
        talonMotor.setInverted(true);
        printDebug = false;
    }


    // Intialized constructor, accepts a channel for initialization
    /**
     * @param motorIDInput the unique channel ID of the motor
     */
    public IntakeArm(int motorIDInput) {
        // speed = Robot.m_oi.getSpeed();
        speed = 0.0;

        talonMotor = new WPI_TalonSRX(motorIDInput);
        talonMotor.setInverted(true);
        if (printDebug) {
            System.out.println("IntakeArm: default constructor ");
        }
    }

    // Intialized constructor
    /**
     * @param motorIDInput the unique channel ID of the motor
     * @param speedInput the speed of the motor
     */
    public IntakeArm(int motorIDInput, double speedInput) {

        speed = speedInput;
        // speed = Robot.m_oi.getSpeed();
        talonMotor = new WPI_TalonSRX(motorIDInput);
        talonMotor.setInverted(true);
        
        if (printDebug) {
            System.out.println("IntakeArm: initialized constructor ");
        }
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

        if (Robot.m_oi.getArmUp()) {
            // if (printDebug) {
            //     System.out.println("IntakeArm: extend speed = " + -1.0 * speed);
            // }
            // talonMotor.setInverted(false);  // do not reverse motor
            talonMotor.set(speed);          // activate motor

        } else if (Robot.m_oi.getArmDown()) {
            // if (printDebug) {
            //     System.out.println("IntakeArm: retract speed = " + speed);
            // }
            // talonMotor.setInverted(false);
            talonMotor.set(-speed);

        } else {  // else no hand button pressed, so stop motor
            talonMotor.set(0);
        }

    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    /**
     * Turn this motor on/off, useful for autonomous
     * @param speedInput desired speed, 0.0 = stop, negative is reverse (down)
     */
    public void driveIntakeArm (double speedInput) {

        // if (printDebug) {
        //     System.out.println("IntakeArm: driveIntakeArm speed = " + speed);
        // }

        // talonMotor.setInverted(false);
        // talonMotor.set(speed);
    }
    
    public boolean isPrintDebug() {
        return printDebug;
    }

    public void setPrintDebug(boolean printDebug) {
        this.printDebug = printDebug;
    }

}

