package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.trajectory.Trajectory.State;

import org.ejml.dense.block.decomposition.qr.BlockHouseHolder_DDRB;

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

    private double mavGrad;
    private double red;
    private double blue;
    private double rainbowGrad;
    private double rainbowBlink;
    private double rainbowWave;

    private double volt;

    private int intState;
    private int count;

    private boolean buttonState;

    @Override
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }

    public SponsorLights (int channelInput){
        mavGrad = 0.47;
        red = 0.61;
        blue = 0.87;
        rainbowGrad = -0.99;
        rainbowBlink = -0.89;
        rainbowWave = -0.45;

        volt = -0.99;

        intState = 0;
        count = 0;
        buttonState = false;

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

        /*
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
            
            lights.set(rainbowGrad);
        
        // (0.61) Solid Color, Red
        } else if (lightSetting == 2 && Robot.m_oi.getSafety() != true) {
            
            lights.set(red);
        
        // (0.47) Color 1 and 2 Pattern, Color Gradient, Color 1 and 2
        } else if (lightSetting == 3 && Robot.m_oi.getSafety() != true) {
            
            lights.set(mavGrad);
            
        // (0.87) Solid Color, Blue
        } else if (lightSetting == 4 && Robot.m_oi.getSafety() != true) {
            
            lights.set(blue);
         
        // (-0.99) Fixed Palette Pattern, Rainbow Palette
        } else if (Robot.m_oi.getSafety() == true) {

            lights.set(rainbowGrad);

        }
        */
    
        if (Robot.m_oi.getYButton()) {

            buttonState = true;

        }

        if (buttonState = true && intState == 1) {

            volt = volt+0.2;
            intState = 0;

        }

        if (count < 50) {
            count ++;
        } else {
            count = 0;
            buttonState = false;
            intState = 1;
        } 

        lights.set(volt);

        // Publish values to SmartDashboard
    //SendableRegistry.setName("lightSetting", 1, (Spark) cargoGate);
    //Sendable.setName("Cargo","Gate"); 
    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    // mavGrad = 0.47;
    // red = 0.61;
    // blue = 0.87;
    // rainbowGrad = -0.99;
    // rainbowBlink = -0.89;
    // rainbowWave = -0.45;

    public void setMavGrad() {
        lights.set(mavGrad);
    }

    public void setRed() {
        lights.set(red);
    }

    public void setBlue() {
        lights.set(blue);
    }

    public void setRainGrad() {
        lights.set(rainbowGrad);
    }

    public void setRainBlink() {
        lights.set(rainbowBlink);
    }

    public void setRainWave() {
        lights.set(rainbowWave);
    }

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

