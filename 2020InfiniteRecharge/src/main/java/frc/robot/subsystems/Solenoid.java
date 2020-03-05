// package frc.robot.subsystems;

// // Import WPI libraries
// import edu.wpi.first.wpilibj.command.Subsystem;

// // Use this for Talon motor controllers
// import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

// import frc.robot.Robot;

// //
// /**
//  * Class IntakeArm
//  * @class IntakeArm class is for controlling the position of the intake arm
//  */
// public class Solenoid extends Subsystem {

//     //private final Spark sparkMotor;
     
//     private final WPI_TalonSRX talonMotor;
    
//     private double speed;
//     private boolean printDebug;

//     // Default constructor
//     public Solenoid() {
//         speed = 0.0;
//         talonMotor = new WPI_TalonSRX(1);
//         talonMotor.setInverted(false);
//         printDebug = false;
//     }


//     // Intialized constructor, accepts a channel for initialization
//     /**
//      * @param motorIDInput the unique channel ID of the motor
//      */
//     public Solenoid(int motorIDInput) {
//         // speed = Robot.m_oi.getSpeed();
//         speed = 0.0;

//         talonMotor = new WPI_TalonSRX(motorIDInput);
//         talonMotor.setInverted(false);
//         if (printDebug) {
//             System.out.println("IntakeArm: default constructor ");
//         }
//     }

//     // Intialized constructor
//     /**
//      * @param motorIDInput the unique channel ID of the motor
//      * @param speedInput the speed of the motor
//      */
//     public Solenoid(int motorIDInput, double speedInput) {

//         speed = speedInput;
//         talonMotor = new WPI_TalonSRX(motorIDInput);
//         talonMotor.setInverted(false);
//         if (printDebug) {
//             System.out.println("IntakeArm: initialized constructor ");
//         }
//     }

//     @Override
//     public void initDefaultCommand() {

//         // Set the default command for a subsystem here.
//         // setDefaultCommand(new MySpecialCommand());
//     }

//     @Override
//     public void periodic() {
//         // Put code here to be run every loop

//     }

//     // Put methods for controlling this subsystem
//     // here. Call these from Commands.
    
//     public boolean isPrintDebug() {
//         return printDebug;
//     }

//     public void setPrintDebug(boolean printDebug) {
//         this.printDebug = printDebug;
//     }

// }

