package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import edu.wpi.first.wpilibj.Spark;

import frc.robot.Robot;
import frc.robot.lightmaps.FixedPalette;
import frc.robot.lightmaps.Solid;

public class LeftGhost extends Subsystem {

    private Spark lights;
    private int channel;

    private int counter;

    @Override
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }

    public LeftGhost (int channelInput){

        counter = 0;

        channel = channelInput;
        lights = new Spark (channelInput);
        lights.set(Solid.darkBlue);
        lights.setInverted(false);
    }
    // This routine is run continuously while in teleoperate mode
    @Override
    public void periodic() {       
        //periodic code
        
        if (Robot.m_oi.getArmDown() && Robot.m_oi.getSafety() == false && Robot.m_oi.getBallOut() == false) {
            lights.set(Solid.darkBlue);
        } else if (Robot.m_oi.getBallOut() && Robot.m_oi.getSafety() == false) {
            if (counter <= 25) {
                lights.set(Solid.darkBlue);
                counter ++;
            } else if (counter > 25 && counter <= 50) {
                lights.set(Solid.white);
                counter ++;
            } else {
                counter = 0;
            }

        } else if (Robot.m_oi.getSafety()) {
            lights.set(FixedPalette.rainbowRainbow);
        } else {
            
            if (counter <= 75) {
                lights.set(Solid.hotPink);
                counter ++;
            } else if (counter > 75 && counter <= 150) {
                lights.set(Solid.orange);
                counter ++;
            } else {
                counter = 0;
            }
        }

    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

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

}

