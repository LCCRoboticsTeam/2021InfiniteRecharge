package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Spark;

import frc.robot.Robot;
import frc.robot.RobotMap;

public class HatchArm extends Subsystem {

    private final Spark arm = RobotMap.hatchArm;
    private double speed;
    
    @Override
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }

    @Override
    public void periodic() {
        // Put code here to be run every loop
    	
    	speed = Robot.m_oi.getSpeed();
        //speed = .5;
        
        
    // 	if (Robot.m_oi.getSafety()) {


    //         if (Robot.m_oi.getArmUp()) {
    //             System.out.println("Arm up button pressed, speed = "+ -1.0*speed);
    //             arm.set(-1.0*speed);
            
    //         } else if (Robot.m_oi.getArmDown()) {
    //             System.out.println("Arm down button pressed, speed = "+ speed);
    //             arm.set(speed);
                
    // 	    } else {
    		
    // 	    	arm.set(0);
            
    //      	}
    //    } 	
    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

}

