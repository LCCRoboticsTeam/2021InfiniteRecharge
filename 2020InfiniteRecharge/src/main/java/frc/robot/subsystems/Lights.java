package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Spark;

import frc.robot.Robot;
import frc.robot.RobotMap;

public class Lights extends Subsystem {

    private final Spark lights = RobotMap.lights;
    private double speed;
    private int lightSetting;

    @Override
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }

    // This routine is run continuously while in teleoperate mode
    @Override
    public void periodic() {       

        // Control the Lexan board lighting based on the XBox controller button selected
        // Y = Rainbow
        // B = Red
        // A = 2 color gradient
        // X = Blue
        // Safety on = Rainbow

        if (Robot.m_oi.getYButton()) {
            // Rainbow
            lightSetting = 1;

        } else if (Robot.m_oi.getBButton()) {
            // Solid red
            lightSetting = 2;
            
        } else if (Robot.m_oi.getAButton()) {
            // 2 Color gradient
            lightSetting = 3;
            
        } else if (Robot.m_oi.getXButton()) {
            // Solid blue
            lightSetting = 4;

        } else if (Robot.m_oi.getSafety()) {
            // Rainbow
            lightSetting = 5;
        }

        // (-0.99) Fixed Palette Pattern, Rainbow Palette
        if (lightSetting == 1 && Robot.m_oi.getSafety() != true) {
            
            lights.set(-0.99);
        
        // (0.61) Solid Color, Red
        } else if (lightSetting == 2 && Robot.m_oi.getSafety() != true) {
            
            lights.set(0.61);
        
        // (0.47) Color 1 and 2 Pattern, Color Gradient, Color 1 and 2
        } else if (lightSetting == 3 && Robot.m_oi.getSafety() != true) {
            
            lights.set(0.47);
            
        // (0.87) Solid Color, Blue
        } else if (lightSetting == 4 && Robot.m_oi.getSafety() != true) {
            
            lights.set(0.87);
         
        // (-0.99) Fixed Palette Pattern, Rainbow Palette
        } else if (lightSetting == 5 && Robot.m_oi.getSafety() == true) {

            lights.set(-0.99);

        }
        
    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    // public void rainbow() {

    //     lights.set(-0.99);

    // }

}

