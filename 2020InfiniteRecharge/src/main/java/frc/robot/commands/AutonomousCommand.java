package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import frc.lib.MecanumDrive;
import frc.robot.subsystems.DriveTrain;

public class AutonomousCommand extends Command {

    private static final String kDefaultAuto = "Default";
    private static final String kCustomAuto = "My Auto";

    private Timer autoTimer = new Timer();
    private String m_autoSelected;
    private SendableChooser<String> m_chooser = new SendableChooser<>();
    private MecanumDrive myDrive;


    public AutonomousCommand() {

    }

    public AutonomousCommand(MecanumDrive myDriveInput) {
        myDrive = myDriveInput;
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        m_autoSelected = m_chooser.getSelected();
        autoTimer.reset();
        autoTimer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {

        switch (m_autoSelected) {
            case kCustomAuto:
                break;
            case kDefaultAuto:
            default:
                if (autoTimer.get() < 3.0) {
                    myDrive.driveCartesian (0.0, -0.35, 0.0);
                    
                } else {
                    myDrive.driveCartesian (0.0, 0.0, 0.0);
                }
                break;
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    }
}
