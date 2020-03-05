/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableRegistry;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Ultrasonic;
import frc.robot.subsystems.IntakeArm;
import frc.robot.subsystems.IntakeBall;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Lights;
//import frc.robot.subsystems.Cargo;

/**
 * The RobotMap is a mapping from the ports sensors and actuators which are wired
 * to a variable name. This provides flexibility in changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
 
  // Public motor IDs, should match what was set using the Phoenix tuner on the robot
                                                  // These were the Luci values:
  public static final int kIntakeArmID = 11;       // armHand = new Spark(0);
  public static final int kIntakeBallID = 10;      // hatchExtender = new Spark(1);
  public static final int kClimberID = 9;         // hatchArm = new Spark(2);
  //public static final int kSolenoid = 12;
  //public static final int kCargoID = 3;           // cargoGate = new Spark(3);

  //public static final int kSponsorLights = 9;
  //public static final int kLeftLight = 10;
  //public static final int kRightLight = 11;

  //public static Ultrasonic environmentalUltrasonicFront;
  //public static Ultrasonic environmentalUltrasonicRear;
  public static IntakeArm intakeArm;
  public static IntakeBall intakeBall;
  public static Climber climber;
  //public static Solenoid solenoid;

  public static Lights sponsorLights;
  public static Lights leftLights;
  public static Lights rightLights;
  //public static Cargo cargo;

  public static void init() {
    
    // Instantiate Intake Arm raise/lower motor (was hatch extender)
    intakeArm = new IntakeArm(kIntakeArmID);
    intakeArm.setPrintDebug(true);

    // hatchExtender = new Spark(1);
    // LiveWindow.addActuator("Hatch", "Extender", (Spark) hatchExtender);
    
    //Instantiate Ball Intake in/out motor (was hatch hand)
    intakeBall = new IntakeBall(kIntakeBallID);
    intakeBall.setPrintDebug(true);
    /*
    armHand = new Spark(0);
    LiveWindow.addActuator("Arm", "Hand", (Spark) armHand);
    */

    // Instantiate Climber up/down motor (was Y  Hatch Arm)
    climber = new Climber(kClimberID);
    climber.setPrintDebug(true);
    /*
    hatchArm = new Spark(2);
    LiveWindow.addActuator("Hatch", "Arm", (Spark) hatchArm);
    LiveWindow.addActuator("light", "board", (Spark) lights);
    */

    //Instantiate Solenoid Brake
    //solenoid = new Solenoid(kSolenoid);

    //Instantiate light controllers
    //sponsorLights = new Lights(kSponsorLights);
    /*
    leftLights = new Lights(10);
    rightLights = new Lights(11);
    */

  }
}