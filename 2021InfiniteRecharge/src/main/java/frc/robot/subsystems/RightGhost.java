package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import java.time.Clock;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Timer;
// import edu.wpi.first.wpilibj.livewindow.LiveWindow;
//import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.Robot;
import frc.robot.RobotMap;

public class RightGhost extends Subsystem {

    private Spark lights;
    //private Spark lights = RobotMap.lights;
    private int channel;
    
    private int state;

    private double green;
    private double darkBlue; 
    private double orange;
    private double pink;
    private double aqua;
    private double red;
    private double white;
    private double rainbowGrad;
    private double rainbowBlink;
    private double rainbowWave;

    private double counter;

    @Override
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }

    public RightGhost (int channelInput){
        //set values for light patterns
        green = 0.75;
        darkBlue = 0.85;
        orange = 0.65;
        pink = 0.57;
        aqua = 0.81;
        red = 0.61;
        white = 0.93;
        rainbowGrad = -0.99;
        rainbowBlink = -0.89;
        rainbowWave = -0.45;

        //set light state
        state = 0;

        counter = 0;

        channel = channelInput;
        lights = new Spark (channelInput);
        lights.set(green);
        lights.setInverted(false);
    }
    // This routine is run continuously while in teleoperate mode
    @Override
    public void periodic() {       
        //periodic code
        // if (state == 0 && Robot.m_oi.getSafety() == false) {
        //     lights.set(green);
        // } else if (state == 1 && Robot.m_oi.getSafety() == false) {
        //     lights.set(darkBlue);
        // } else if (state == 2 && Robot.m_oi.getSafety() == false) {
        //     lights.set(orange);
        // } else if (state == 3 && Robot.m_oi.getSafety() == false) {
        //     lights.set(pink);
        // } else if (state == 4 && Robot.m_oi.getSafety() == false) {
        //     lights.set(aqua);
        // } else if (state == 5 && Robot.m_oi.getSafety() == false) {
        //     lights.set(red);
        // } else if (state == 6 && Robot.m_oi.getSafety() == false) {
        //     lights.set(white);
        // } else if (state == 7 && Robot.m_oi.getSafety() == false) {
        //     lights.set(rainbowGrad);
        // } else if (state == 8 && Robot.m_oi.getSafety() == false) {
        //     lights.set(rainbowBlink);
        // } else if (state == 9 && Robot.m_oi.getSafety() == false) {
        //     lights.set(rainbowWave);
        // } else if (state == 10 && Robot.m_oi.getSafety() == false) {
        //     lights.set(orange);
        //     Timer.delay(1);
        //     lights.set(pink);
        //     Timer.delay(1);
        // } else if (state == 11 && Robot.m_oi.getSafety() == false) {
        //     lights.set(red);
        //     Timer.delay(1);
        //     lights.set(aqua);
        //     Timer.delay(1);
        // }

        if (Robot.m_oi.getArmDown() && Robot.m_oi.getSafety() == false && Robot.m_oi.getBallOut() == false) {
            lights.set(darkBlue);
        } else if (Robot.m_oi.getBallOut() && Robot.m_oi.getSafety() == false) {
            if (counter <= 25) {
                lights.set(darkBlue);
                counter ++;
            } else if (counter > 25 && counter <= 50) {
                lights.set(white);
                counter ++;
            } else {
                counter = 0;
            }
            // lights.set(darkBlue);
            //Timer.delay(0.5);
            // lights.set(white);
            //Timer.delay(0.5);
        } else if (Robot.m_oi.getSafety()) {
            lights.set(rainbowGrad);
        } else {
            //Timer.delay(1);
            // lights.set(aqua);
            //Timer.delay(1);
            if (counter <= 75) {
                lights.set(red);
                counter ++;
            } else if (counter > 75 && counter <= 150) {
                lights.set(aqua);
                counter ++;
            } else {
                counter = 0;
            }
        }

    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void setGreen() {
        state = 0;
    }

    public void setDarkBlue() {
        state = 1;
    }

    public void setOrange() {
        state = 2;
    }

    public void setPink() {
        state = 3;
    }

    public void setAqua() {
        state = 4;
    }

    public void setRed() {
        state = 5;
    }

    public void setWhite() {
        state = 6;
    }

    public void setRainGrad() {
        state = 7;
    }

    public void setRainBlink() {
        state = 8;
    }

    public void setRainWave() {
        state = 9;
    }

    public void setOPGame() {
        state = 10;
    }

    public void setRAGame() {
        state = 11;
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
    

}

