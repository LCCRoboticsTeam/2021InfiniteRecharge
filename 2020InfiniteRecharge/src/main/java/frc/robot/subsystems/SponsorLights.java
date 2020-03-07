package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Spark;
// import edu.wpi.first.wpilibj.livewindow.LiveWindow;
//import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.Robot;
import frc.robot.RobotMap;

public class SponsorLights extends Subsystem {

    private Spark lights;
    //private Spark lights = RobotMap.lights;
    private int channel;
    // private double speed;
    private int lightSetting;

    @Override
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }

    public SponsorLights (int channelInput){
        channel = channelInput;
        lights = new Spark (channelInput);
        lights.setInverted(false);
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
        } else if (Robot.m_oi.getSafety() == true) {

            lights.set(-0.99);

        }
    // Publish values to SmartDashboard
    //SendableRegistry.setName("lightSetting", 1, (Spark) cargoGate);
    //Sendable.setName("Cargo","Gate"); 
    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    // public void rainbow() {

    //     lights.set(-0.99);

    // }


    /**
     * @return int return the channel
     */
    public int getChannel() {
        return channel;
    }

    /**
     * @param channel the channel to set
     */
    public void setChannel(int channel) {
        this.channel = channel;
    }

    public void setVolt(Double voltage) {

        lights.set(voltage);

    }

    // /**
    //  * @return double return the speed
    //  */
    // public double getSpeed() {
    //     return speed;
    // }

    // /**
    //  * @param speed the speed to set
    //  */
    // public void setSpeed(double speed) {
    //     this.speed = speed;
    // }

    /**
     * @return int return the lightSetting
     */
    public int getLightSetting() {
        return lightSetting;
    }

    /**
     * @param lightSetting the lightSetting to set
     */
    public void setLightSetting(int lightSetting) {
        this.lightSetting = lightSetting;
    }

}

