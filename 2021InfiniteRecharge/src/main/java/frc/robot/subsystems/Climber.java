package frc.robot.subsystems;

// Import WPI libraries
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.*;
/*
// Use this for Spark motor controllers
import edu.wpi.first.wpilibj.Spark;
*/
// Use this for Talon motor controllers
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;


import frc.robot.Robot;
import frc.robot.RobotMap;

public class Climber extends Subsystem {
    /*
    private final Spark sparkMotor;
    */
    private final WPI_TalonSRX talonMotor;

    private double speed;
    private boolean printDebug;

    private int climbState;

    // Default constructor
    public Climber () {
        speed = 0.0;
        talonMotor = new WPI_TalonSRX(1);
        talonMotor.setInverted(false);
        printDebug = false;
    }
        
    // Intialized constructor, accepts a channel for initialization
    /**
     * @param motorIDInput the unique channel ID of the motor
     */
    public Climber (int motorIDInput) {
        // speed = Robot.m_oi.getSpeed();
        speed = 0.0;
        climbState = 0;

        talonMotor = new WPI_TalonSRX(motorIDInput);
        talonMotor.setInverted(false);

        if (printDebug) {
            System.out.println("Climb: initialized constructor");
        }
    }

    public Climber (int motorIDInput, double speed) {
        // speed = Robot.m_oi.getSpeed();
        this.speed = speed;
        // this.speed = Robot.m_oi.getSpeed();
        climbState = 0;

        talonMotor = new WPI_TalonSRX(motorIDInput);
        talonMotor.setInverted(false);

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
        //speed = 0.0;
        
        
        // System.out.println("Saftey off.");
        SmartDashboard.putBoolean("Climber Safety Mode: ", Robot.m_oi.getSafety());

        if (Robot.m_oi.getSafety()) {
    		if (Robot.m_oi.getClimbUp()) {
                // if (printDebug) {
                //     System.out.println("Climb: up speed = " + speed);
                // }
                // talonMotor.setInverted(false);  // do not reverse motor
                talonMotor.set(-speed);          // activate motor

    		} else if (Robot.m_oi.getClimbDown()) {
                // if (printDebug) {
                //     System.out.println("IntakeArm: retract speed = " + speed);
                // }
                // talonMotor.setInverted(true);   // reverse motor
                talonMotor.set(speed);
                
    		} else {  // else no hand button pressed, so stop motor
                talonMotor.set(0);
            }

            if (climbState == 0 && Robot.m_oi.getClimbUp()) {
                climbState = 1;
            }

            if (climbState == 1 && Robot.m_oi.getClimbDown()) {
                climbState = 2;
            }

            if (climbState == 2 && talonMotor.getSensorCollection().isFwdLimitSwitchClosed()) {
                // Activiate the solenoid
                RobotMap.solenoid.extendSolenoid(true);
            }
        }
    }
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    /**
     * @return the climbState
     */
    public int getClimbState() {
        return climbState;
    }

    /**
     * @param climbState the climbState to set
     */
    public void setClimbState(int climbState) {
        this.climbState = climbState;
    }

    public boolean isPrintDebug() {
        return printDebug;
    }

    public void setPrintDebug(boolean printDebug) {
        this.printDebug = printDebug;
    }
}

