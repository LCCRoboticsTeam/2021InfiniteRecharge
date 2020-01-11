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
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Ultrasonic;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  // For example to map the left and right motors, you could define the
  // following variables to use with your drivetrain subsystem.
  // public static int leftMotor = 1;
  // public static int rightMotor = 2;
  public static Ultrasonic environmentalUltrasonicFront;
  public static Ultrasonic environmentalUltrasonicRear;
  public static Spark cargoGate;
  public static Spark hatchExtender;
  public static Spark armHand;
  public static Spark hatchArm;
  public static Spark lights;
  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;


  public static void init() {
    cargoGate = new Spark(3);
    // double value = SmartDashboard.set;
    //SendableRegistry.setName("Cargo", "Gate", (Spark) cargoGate);
    // Sendable.setName("Cargo","Gate");
    cargoGate.setInverted(false);
    // hatchExtender = new Spark(1);
    // LiveWindow.addActuator("Hatch", "Extender", (Spark) hatchExtender);
    // hatchExtender.setInverted(false);
    armHand = new Spark(0);
    //LiveWindow.addActuator("Arm", "Hand", (Spark) armHand);
    armHand.setInverted(false);
    hatchArm = new Spark(2);
    //LiveWindow.addActuator("Hatch", "Arm", (Spark) hatchArm);
    hatchArm.setInverted(false);
    lights = new Spark(9);
    //LiveWindow.addActuator("light", "board", (Spark) lights);
    lights.setInverted(false);
    
  }
}