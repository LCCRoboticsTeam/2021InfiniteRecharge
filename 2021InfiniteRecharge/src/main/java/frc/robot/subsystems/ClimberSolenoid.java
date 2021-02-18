package frc.robot.subsystems;

// Import WPI libraries
import edu.wpi.first.wpilibj.command.Subsystem;

// Use this for Talon motor controllers
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

//
/**
 * Class Solenoid
 * @class Solenoid class is for controlling the solenoid that locks the climber
 * into place after climbing
 */
public class ClimberSolenoid extends Subsystem {
     
    private final WPI_TalonSRX talonMotor;
    
    private boolean printDebug;

    private int soleState;

    // Default constructor
    public ClimberSolenoid() {
        soleState = 0;
        talonMotor = new WPI_TalonSRX(2);
        talonMotor.setInverted(false);
        printDebug = false;
    }

    // Intialized constructor, accepts a channel for initialization
    /**
     * @param motorIDInput the unique channel ID of the motor
     */
    public ClimberSolenoid(int motorIDInput, boolean printDebugInput) {
        
        soleState = 0;
        // speed = 0.0;
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

        if (soleState == 0) {

            talonMotor.set(.7); 

        } else if (soleState == 1) {

            talonMotor.set(0);

        }
    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public void extendSolenoid (boolean printDebugInput) {
        if (printDebugInput) {
            System.out.println("Solenoid: extend solenoid called");
        }
        soleState = 1;
    }

    /**
     * @return the soleState
     */
    public int getSoleState() {
        return soleState;
    }

    /**
     * @param soleState the soleState to set
     */
    public void setSoleState(int soleState) {
        this.soleState = soleState;
    }

    public boolean isPrintDebug() {
        return printDebug;
    }

    public void setPrintDebug(boolean printDebug) {
        this.printDebug = printDebug;
    }

}

