package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Timer;
// import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import frc.lib.MecanumDrive;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.PiCamera.PiCamera.PiCameraRegions;
import frc.robot.PiCamera.PiCamera.PiCameraRegion;

public class AutonomousCommand extends Command {

    public static final String kDefaultAuto = "Default";
    public static final String kGalacticAuto = "Galactic";
    public static final String kBarrelAuto = "Barrel";
    public static final String kSlalomAuto = "Slalom";
    public static final String kBounceAuto = "Bounce";
    // private static final String kCustomAuto = "Vision";

    private Timer autoTimer = new Timer();
    private String m_autoSelected;
    // private SendableChooser<String> m_chooser = new SendableChooser<>();
    private MecanumDrive myDrive;

    private boolean scanLeft;
    private boolean scanStart;
    private boolean scanEnable;

    private boolean isCapturing;

    private int capCount;

    private boolean needSqr;

    private double startTime;

    public AutonomousCommand() {
        this.m_autoSelected = kDefaultAuto;
        System.out.println("AutonomousCommand: default constructor");
    }

    public AutonomousCommand(MecanumDrive myDriveInput) {
        myDrive = myDriveInput;
        this.m_autoSelected = kDefaultAuto;
        System.out.println("AutonomousCommand: initialized constructor");
    }

    public AutonomousCommand(MecanumDrive myDriveInput, String m_autoSelected) {
        myDrive = myDriveInput;
        this.m_autoSelected = m_autoSelected;
        System.out.println("AutonomousCommand: initialized constructor");
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        // m_autoSelected = m_chooser.getSelected();
        startTime = 0;
        isCapturing = false;
        scanLeft = false;
        scanStart = true;
        scanEnable = true;
        needSqr = true;
        capCount = 0;
        autoTimer.reset();
        autoTimer.start();
        System.out.println("AutonomousCommand: initialize() completed");
    }
    
    
    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        
        switch (m_autoSelected) {
            case kGalacticAuto:
            
                System.out.println("AutonomousCustom");

                RobotMap.intakeBall.intakeIn();

                if (isCapturing != true) {
                    if(Robot.m_piCamera.GetRegions() != null) {
                        PiCameraRegions regions = Robot.m_piCamera.GetRegions();
                        PiCameraRegion region;
                        if (regions.GetRegionCount() > 0) {
                            if (scanStart != true) {
                                scanStart = true;
                            }
                            if (scanEnable != true) {
                                scanEnable = true;
                            }
                            if (scanLeft != true) {
                                scanLeft = true;
                            }
                            region = regions.GetRegion(0);
                            for (int i = 0; i < regions.GetRegionCount(); i++) {
                                if (region.m_bounds.m_top < regions.GetRegion(i).m_bounds.m_top) {
                                    region = regions.GetRegion(i);
                                }
                            }
                            if (region.m_bounds != null) {
                                int top = region.m_bounds.m_top;
                                int bottom = region.m_bounds.m_bottom;
                                int left = region.m_bounds.m_left;
                                int right = region.m_bounds.m_right;
                                int height = Math.abs(bottom-top);
                                int width = Math.abs(right-left);
                                boolean square = (Math.abs(height-width)<20) ? true : false;
                                if (square || capCount >= 3) {
                                    if (scanStart != true) {
                                        scanStart = true;
                                    }
                                    if (scanEnable != true) {
                                        scanEnable = true;
                                    }
                                    if (scanLeft != true) {
                                        scanLeft = true;
                                    }
                                    // System.out.print("Region " + i + "; ");
                                    System.out.print("Top: " + top + "; ");
                                    System.out.print("Bottom: " + bottom + "; ");
                                    System.out.print("Left: " + left + "; ");
                                    System.out.print("Right: " + right + "; ");
                                    System.out.print("Turn Direction: ");
                                    // System.out.println(((right+left)/2)<320 ? "left; " : "right; ");
                
                                    int difference = ((right+left)/2)-320;
                
                                    double curve = ((6.84*(Math.pow(10, -6)))*Math.pow(difference, 2))+0.35;

                                    if (difference < -40) {
                                        System.out.println("left, " + curve);
                                        myDrive.driveCartesian(-curve, 0.0, 0.0, 0);
                                    } else if (difference > 40) {
                                        System.out.println("right, " + curve);
                                        myDrive.driveCartesian(curve, 0.0, 0.0, 0);
                                    } else {
                                        System.out.println("center, ");
                                        if (top < 270) {
                                            // RobotMap.intakeBall.intakeIn();
                                            myDrive.driveCartesian(0.0 , 0.0, 0.29, 0.0);
                                        } else {
                                            // RobotMap.intakeBall.intakeOff();
                                            isCapturing = true;
                                            startTime = autoTimer.get();
                                            // myDrive.driveCartesian(0.0, 0.0, 0.0, 0.0);
                                        }
                                    }
                                } else {
                                    // if no regions
                                    if (scanEnable) {
                                        if (scanStart == true) {
                                            scanStart = false;
                                            startTime = autoTimer.get();
                                        }
                                        if (scanLeft == true) {
                                            if (autoTimer.get() - startTime < 1.5) {
                                                myDrive.driveCartesian(0.4, 0.0, 0.0, 0.0);
                                            } else {
                                                scanStart = true;
                                                scanLeft = false;
                                                myDrive.driveCartesian(0.0, 0.0, 0.0, 0.0);
                                            }
                                        } else {
                                            // Scan right, scanLeft == false
                                            if (autoTimer.get() - startTime < 2.5) {
                                                myDrive.driveCartesian(0.4, 0.0, 0.0, 0.0);
                                            } else {
                                                scanEnable = false;
                                                scanStart = true;
                                                scanLeft = true;
                                                
                                                RobotMap.intakeBall.intakeOff();
        
                                                myDrive.driveCartesian(0.0, 0.0, 0.0, 0.0);
                                            }
                                        }
                                    }
                                }
                            }
                        } else {
                            // if no regions
                            if (scanEnable) {
                                if (scanStart == true) {
                                    scanStart = false;
                                    startTime = autoTimer.get();
                                }
                                if (scanLeft == true) {
                                    if (autoTimer.get() - startTime < 1.5) {
                                        myDrive.driveCartesian(0.4, 0.0, 0.0, 0.0);
                                    } else {
                                        scanStart = true;
                                        scanLeft = false;
                                        myDrive.driveCartesian(0.0, 0.0, 0.0, 0.0);
                                    }
                                } else {
                                    // Scan right, scanLeft == false
                                    if (autoTimer.get() - startTime < 2.5) {
                                        myDrive.driveCartesian(0.4, 0.0, 0.0, 0.0);
                                    } else {
                                        scanEnable = false;
                                        scanStart = true;
                                        scanLeft = true;
                                        
                                        RobotMap.intakeBall.intakeOff();

                                        myDrive.driveCartesian(0.0, 0.0, 0.0, 0.0);
                                    }
                                }
                            }
                        }
                    }
                } else {
                    // runs when isCapturing == true
                    if (autoTimer.get() - startTime < 1) {
                        // Continue for 2 seconds.
                        myDrive.driveCartesian(0.0 , 0.0, 0.29, 0.0);
                        // RobotMap.intakeBall.intakeIn();
                    } else {
                        // Stop motors and track next ball
                        capCount++;
                        isCapturing = false;
                        myDrive.driveCartesian(0.0, 0.0, 0.0, 0.0);
                        // RobotMap.intakeBall.intakeOff();
                    }
                }
                break;
            case kBarrelAuto: 

                // TODO: Gyro? Second Camera? Both?

                // Track Cone 1
                // Get Within Distance
                // Strafe around to Left (Clockwise)
                // Wait until see other cone
                // Go Towards Cone 2
                // Get within distance
                // Strafe around to Right (Counter-Clockwise)
                // Wait until see Cone 3
                // Track Cone 3
                // Get within distance
                // Strafe around to Right (Counter-Clockwise)
                // Track other cones for Start Zone
                // Go to Start Zone

                break;
            case kSlalomAuto: 
                
                break;
            case kBounceAuto: 
                
                break;
            case kDefaultAuto:

                // if (autoTimer.get() < 1.0) {
                //     myDrive.driveCartesian (0.0, 0.0, -0.5);
                // } else {
                //     myDrive.driveCartesian (0.0, 0.0, 0.0);
                // }

                break;
        }
        
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        System.out.println("AutonomousCommand: isFinished() called");
        return false;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        System.out.println("AutonomousCommand: end() called");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
        System.out.println("AutonomousCommand: interrrupted() called");
    }
}
