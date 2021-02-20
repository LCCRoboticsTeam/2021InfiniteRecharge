package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
// import edu.wpi.first.wpilibj.trajectory.Trajectory.State;

// import org.ejml.dense.block.decomposition.qr.BlockHouseHolder_DDRB;

import edu.wpi.first.wpilibj.Spark;
// import edu.wpi.first.wpilibj.livewindow.LiveWindow;
//import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

// import frc.robot.RobotMap;
import frc.robot.lightmaps.*;

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

    public SponsorLights(int channelInput) {
        channel = channelInput;
        lights = new Spark (channelInput);
        lights.setInverted(false);
    }
    // This routine is run continuously while in teleoperate mode
    @Override
    public void periodic() {       

    
        
    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void setMavGrad() {
        lights.set(ColorBoth.endToEndBlend);
    }

    public void setRed() {
        lights.set(Solid.red);
    }

    public void setBlue() {
        lights.set(Solid.blue);
    }

    public void setRainGrad() {
        lights.set(FixedPalette.rainbowRainbow);
    }

    public void setRainBlink() {
        lights.set(FixedPalette.rainbowGlitter);
    }

    public void setRainWave() {
        lights.set(FixedPalette.waveRainbow);
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

