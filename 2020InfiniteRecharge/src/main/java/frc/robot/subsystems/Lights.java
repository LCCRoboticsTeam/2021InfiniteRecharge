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

    @Override
    public void periodic() {
        // Put code here to be run every loop        

        if (Robot.m_oi.getYButton()) {
            
            lightSetting = 1;

        } else if (Robot.m_oi.getBButton()) {
            
            lightSetting = 2;
            
        } else if (Robot.m_oi.getAButton()) {
            
            lightSetting = 3;
            
        } else if (Robot.m_oi.getXButton()) {
            
            lightSetting = 4;

        } else if (Robot.m_oi.getSafety());

        if (lightSetting == 1 && Robot.m_oi.getSafety() != true) {
            
            lights.set(-0.99);
        
        } else if (lightSetting == 2 && Robot.m_oi.getSafety() != true) {
            
            lights.set(0.61);
            
        } else if (lightSetting == 3 && Robot.m_oi.getSafety() != true) {
            
            lights.set(0.47);
            
        } else if (lightSetting == 4 && Robot.m_oi.getSafety() != true) {
            
            lights.set(0.87);
            
        } else if (Robot.m_oi.getSafety()) {

            lights.set(-0.99);

        }
        
    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    // public void rainbow() {

    //     lights.set(-0.99);

    // }

}

