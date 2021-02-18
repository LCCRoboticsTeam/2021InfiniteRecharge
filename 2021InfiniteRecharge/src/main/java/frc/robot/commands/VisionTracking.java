package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.PiCamera.Logger;
import frc.robot.PiCamera.PiCamera.PiCameraRegion;
import frc.robot.PiCamera.PiCamera.PiCameraRegions;

public class VisionTracking extends Command {
    
    static private final double k_p = 0.001; // proportional power adjustment multiplier
    static private final double k_power = 0.25; // base power
    static private final int k_stopPos = 20; // Stop when top of target is within this distance (in pixels)

    private boolean isFinished = false;

    public VisionTracking() {
        Logger.Log("VisionTracking", 3, "Constructor");

        // Use requires() here to declare subsystem dependencies
        requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        Logger.Log("VisionTracking", 3, "initialize");

        isFinished = false;
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        PiCameraRegions regions = Robot.m_piCamera.GetRegions();
        double leftPower = 0;
        double rightPower = 0;

        if ((regions != null) && regions.GetRegionCount() == 2)
        // if (regions != null)
        {
            PiCameraRegion regionLeft = regions.GetRegion(0);
            PiCameraRegion regionRight = regions.GetRegion(1);

            if ((regionLeft.m_bounds.m_top > k_stopPos) && (regionRight.m_bounds.m_top > k_stopPos)) 
            {

            }
        }
    }

    @Override
    protected boolean isFinished() {
        // TODO Auto-generated method stub
        return false;
    }

}
