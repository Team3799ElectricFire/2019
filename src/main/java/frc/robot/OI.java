/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.Utility.AxisButton;
import frc.robot.Utility.POVButton;
import frc.robot.RobotMap;
import frc.robot.commands.*;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  private Joystick Gamepad = new Joystick(RobotMap.GAMEPAD_PORT_ID);
  private JoystickButton A = new JoystickButton(Gamepad, RobotMap.A_BUTTON);
  private JoystickButton Y = new JoystickButton(Gamepad, RobotMap.Y_BUTTON);
  private JoystickButton X = new JoystickButton(Gamepad, RobotMap.X_BUTTON);
  private JoystickButton B = new JoystickButton(Gamepad, RobotMap.B_BUTTON);
  private JoystickButton Start = new JoystickButton(Gamepad, RobotMap.START_BUTTON);
  private JoystickButton Back = new JoystickButton(Gamepad, RobotMap.BACK_BUTTON);
  private JoystickButton LeftBumper = new JoystickButton(Gamepad, RobotMap.LEFT_BUMPER);
  private JoystickButton RightBumper = new JoystickButton(Gamepad, RobotMap.RIGHT_BUMPER);
  private JoystickButton LeftStick = new JoystickButton(Gamepad, RobotMap.LEFT_STICK);
  private JoystickButton RightStick = new JoystickButton(Gamepad, RobotMap.RIGHT_STICK);
  private POVButton UP = new POVButton(Gamepad, RobotMap.POV_UP);
  private POVButton Down = new POVButton(Gamepad, RobotMap.POV_DOWN);
  private AxisButton LeftTrigger = new AxisButton(Gamepad, RobotMap.LEFT_TRIGGER, .3, .1);
  private AxisButton RightTrigger = new AxisButton(Gamepad, RobotMap.RIGHT_TRIGGER, .3, .1);
  
  private Joystick SadStick = new Joystick(RobotMap.SadStick_PORT_ID);
  private JoystickButton sA = new JoystickButton(SadStick, RobotMap.A_BUTTON);
  private JoystickButton sB = new JoystickButton(SadStick, RobotMap.B_BUTTON);
  private JoystickButton sY = new JoystickButton(SadStick, RobotMap.Y_BUTTON);
  private JoystickButton sX = new JoystickButton(SadStick, RobotMap.X_BUTTON);
  private JoystickButton sSTART = new JoystickButton(SadStick, RobotMap.START_BUTTON);
  private JoystickButton sBACK = new JoystickButton(SadStick, RobotMap.BACK_BUTTON);
  private POVButton sUP = new POVButton(SadStick, RobotMap.POV_UP);
  private POVButton sDOWN = new POVButton(SadStick, RobotMap.POV_DOWN);
  private POVButton sLEFT = new POVButton(SadStick, RobotMap.POV_LEFT);
  

  public OI() {
    RightTrigger.whileHeld(new LiftDown());
    RightBumper.whileHeld(new LiftUp());
    //X.whenPressed(new TurboToggle());
    B.whileHeld(new FollowVisionTarget());
    Start.whenPressed(new IntakeUp());
    Back.whenPressed(new IntakeDown());
    LeftBumper.whileHeld(new BallIntakeIn());
    LeftTrigger.whileHeld(new BallIntakeOut());
    A.whenPressed(new HatchClose());
    Y.whenPressed(new HatchOpen());
    LeftStick.whenPressed(new TurboLow());
    RightStick.whenPressed(new TurboHigh());

    sA.whenPressed(new LiftPID(1.23));// + Robot.m_lift.liftOffset)); // bottom panel
    sB.whenPressed(new LiftPID(12));//+Robot.m_lift.liftOffset)); // middle panel
    sY.whenPressed(new LiftPID(22.41));// +  + Robot.m_lift.liftOffset)); // uppermost panel
    sDOWN.whenPressed(new LiftPID(9.84));// +  + Robot.m_lift.liftOffset)); // bottom cargo
    sLEFT.whenPressed(new LiftPID(20.57));// +  + Robot.m_lift.liftOffset)); // middle cargo 
    sUP.whenPressed(new LiftPID(29.32 ));//+  + Robot.m_lift.liftOffset)); // uppermost cargo 
    sSTART.whileActive(new PlungerBoyDown());
    sBACK.whileActive(new PlungerBoyUp());
    sX.whenPressed(new ToggleDrive());

  }

  public double getLeftY() {
    return Gamepad.getRawAxis(RobotMap.LEFT_STICK_AXIS_Y);
  }

  public double getLeftX() {
    return Gamepad.getRawAxis(RobotMap.LEFT_STICK_AXIS_X);
  }

  public double getRightY() {
    return Gamepad.getRawAxis(RobotMap.RIGHT_STICK_AXIS_Y);
  }

  public double getRightX() {
    return Gamepad.getRawAxis(RobotMap.RIGHT_STICK_AXIS_X);
  }

  public double getLeftTrigger() {
    return Gamepad.getRawAxis(RobotMap.LEFT_TRIGGER);
  }

  public double getRightTrigger() {
    return Gamepad.getRawAxis(RobotMap.RIGHT_TRIGGER);
  }

  public int getPOV() {
    return Gamepad.getPOV();
  }
}