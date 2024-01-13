/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

//import edu.wpi.cscore.CvSink;
//import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
//import org.opencv.core.Mat;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
//import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.ZeroLift;
import frc.robot.commands.Zero_and_Rise_To_Low_Hatch;
import frc.robot.subsystems.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  public static TankDrive m_tankdrive = new TankDrive();
  public static Lift m_lift = new Lift();
  public static BallIntake m_ball_intake = new BallIntake();
  public static Hatch m_hatch = new Hatch();
  public static ThePlunger m_plunger = new ThePlunger();
  public static Turbo s_gearshift = new Turbo();
  public static Light m_light = new Light();
  public static OI m_oi;
  public static NetworkTable m_table;

  public Zero_and_Rise_To_Low_Hatch m_zero_And_Rise_To_Low_Hatch =  new Zero_and_Rise_To_Low_Hatch();

  Command m_autonomousCommand;
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */
  @Override
  public void robotInit() {
    m_oi = new OI();
    // m_chooser.setDefaultOption("Default Auto", new ExampleCommand());
    // chooser.addOption("My Auto", new MyAutoCommand());
    // SmartDashboard.putData("Auto mode", m_chooser);
    NetworkTableInstance inst = NetworkTableInstance.getDefault();
    inst.startClientTeam(3799);
    m_table = inst.getTable("ChickenVision");
    m_table.getEntry("Tape").setBoolean(true);

    // without creating a new thread, it works
    // Get the UsbCamera from CameraServer
    UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
    // Set the resolution
    camera.setResolution(320,240);
    camera.setFPS(15);

    // Force intake to be UP
    //m_ball_intake.setIntakePosition(true);

    // Camera Streaming Method #2 With a new thread created: WILL CREAT OVERRUN MESSAGE!!
     /* Thread cameraThread = new Thread(() -> { 

        int vertRes = 480; int horzRes = 640;
        int framerate = 15;
     
        Mat img = new Mat();
     
        UsbCamera FWDcam = CameraServer.getInstance().startAutomaticCapture();
     
        FWDcam.setResolution(horzRes, vertRes); FWDcam.setFPS(framerate); 
        CvSink FWDcvsink = CameraServer.getInstance().getVideo();
        CvSource output = CameraServer.getInstance().putVideo("Driver Camera", horzRes, vertRes);
     
     
        while (!Thread.interrupted()) { 
          if (FWDcvsink.grabFrame(img) == 0) {
            // Send the output the error.
            output.notifyError(FWDcvsink.getError());
            // skip the rest of the current iteration
            continue;
          }
          output.putFrame(img); }
     
     } ); 
     cameraThread.setDaemon(true);
     cameraThread.start();  */
  }   
/*


  /**
   * This function is called every robot packet, no matter the mode. Use this for
   * items like diagnostics that you want ran during disabled, autonomous,
   * teleoperated and test.
   *
   * <p>
   * This runs after the mode specific periodic functions, but before LiveWindow
   * and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    SmartDashboard.putBoolean("Tape Detected?", m_table.getEntry("tapeDetected").getBoolean(true));
    SmartDashboard.putNumber("Type Yaw", m_table.getEntry("tapeYaw").getDouble(0));

    SmartDashboard.putNumber("Right Position", m_tankdrive.PositionRight());
    SmartDashboard.putNumber("Left Position", m_tankdrive.PositionLeft());
    SmartDashboard.putNumber("Right Velocity", m_tankdrive.VelocityRight());
    SmartDashboard.putNumber("Left Velocity", m_tankdrive.VelocityLeft());

    SmartDashboard.putNumber("Lift Position", m_lift.GetLiftPosition());
    SmartDashboard.putNumber("Lift Velocity", m_lift.GetLiftVelocity());

    SmartDashboard.putNumber("X Location", m_tankdrive.get_X_Location());
    SmartDashboard.putNumber("Y Location", m_tankdrive.get_Y_Location());
    SmartDashboard.putNumber("Robot Angle", m_tankdrive.getYaw());

    SmartDashboard.putNumber("Dart Potentiometer", m_plunger.getPot());

    SmartDashboard.putBoolean("Lift Home At Home Switch", m_lift.GetHomeSwitch());
    SmartDashboard.putBoolean("Intake Solenoid", m_ball_intake.getLeftPosition());

    // SmartDashboard.putNumber("Air Tank Pressure", );
    // TODO Add this back in to display the psi of the tank on the dashboard after
    // the mech guys add a sensor
  }

  /**
   * This function is called once each time the robot enters Disabled mode. You
   * can use it to reset any subsystem information you want to clear when the
   * robot is disabled.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable chooser
   * code works with the Java SmartDashboard. If you prefer the LabVIEW Dashboard,
   * remove all of the chooser code and uncomment the getString code to get the
   * auto name from the text box below the Gyro
   *
   * <p>
   * You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons to
   * the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_chooser.getSelected();
    /*
     * String autoSelected = SmartDashboard.getString("Auto Selector", "Default");
     * switch(autoSelected) { case "My Auto": autonomousCommand = new
     * MyAutoCommand(); break; case "Default Auto": default: autonomousCommand = new
     * ExampleCommand(); break; }
     */

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.start();
    }
    m_ball_intake.setIntakePosition(false);
    m_tankdrive.resetOdometry();
    m_tankdrive.zeroYaw();
    m_zero_And_Rise_To_Low_Hatch.start();
    //m_lift.zeroEncoder();
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();

    m_tankdrive.Odometry();
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
