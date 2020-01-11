package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Spark;

import frc.robot.Robot;
import frc.robot.RobotMap;

public class Cargo extends Subsystem {

    private final Spark gate = RobotMap.cargoGate;
    
    //private Timer cTimer = new Timer();
    private double speed;
    
    @Override
    public void initDefaultCommand() {
    	
        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }

    @Override
    public void periodic() {

        // speed = Robot.m_oi.getSpeed(); 
        //Cargo Arm Speed, change if need faster.   	
        speed = .71;
        
            System.out.println("Cargo Saftey off.");

    		if (Robot.m_oi.getCargoGate()) {
    		System.out.println("Cargo Gate Button pressed, speed = "+ (-1.0*speed));
    		gate.set(-1.0*speed);
    		
    		} else {
    		
    			//System.out.println("Cargo Gate Button not pressed: " + speed);
    			gate.set(speed);
    		
    		}
    	
    }
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

}

