package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Spark;

import frc.robot.Robot;
import frc.robot.RobotMap;

public class HatchExtend extends Subsystem {

    private final Spark extender = new Spark(1);//RobotMap.hatchExtender;

    private double speed;

    public HatchExtend(){
        extender.setInverted(false);
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
        speed = .4;
        

    	if (Robot.m_oi.getHandExt()) {
    		System.out.println("Hand Extend button pressed, speed = "+ -1.0*speed);
    		extender.set(-1.0*speed);
    		
    	} else if (Robot.m_oi.getHandRtc()) {
    		System.out.println("Hand Retract button pressed, speed = "+ speed);
    		extender.set(speed);
    		
    	} else {
    		
    		extender.set(0);
    		
    	}
    	
    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

}

