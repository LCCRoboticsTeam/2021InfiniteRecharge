package frc.robot.subsystems;

// Import WPI libraries
import edu.wpi.first.wpilibj.command.Subsystem;

// Use this for Talon motor controllers
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

//import frc.robot.Robot;

//
/**
 * Class Solenoid
 * @class Solenoid class is for controlling the solenoid that locks the climber
 * into place after climbing
 */
public class ClimberSolenoid extends Subsystem {

    //private final Spark sparkMotor;
     
    private final WPI_TalonSRX talonMotor;
    
    private double speed;
    private boolean printDebug;

    // Default constructor
    public ClimberSolenoid() {
        speed = 0.0;
        talonMotor = new WPI_TalonSRX(2);
        talonMotor.setInverted(false);
        printDebug = false;
    }


    // Intialized constructor, accepts a channel for initialization
    /**
     * @param motorIDInput the unique channel ID of the motor
     */
    public ClimberSolenoid(int motorIDInput, boolean printDebugInput) {
        
        speed = 0.0;
        printDebug = printDebugInput;

        talonMotor = new WPI_TalonSRX(motorIDInput);
        talonMotor.setInverted(false);

        if (printDebug) {
            System.out.println("Solenoid: initialized constructor ");
        }
    }

    // Intialized constructor
    /**
     * @param motorIDInput the unique channel ID of the motor
     * @param speedInput the speed of the motor
     */
    public ClimberSolenoid(int motorIDInput, double speedInput, boolean printDebugInput) {

        speed = speedInput;
        printDebug = printDebugInput;

        talonMotor = new WPI_TalonSRX(motorIDInput);
        talonMotor.setInverted(false);

        if (printDebug) {
            System.out.println("Solenoid: initialized constructor ");
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

