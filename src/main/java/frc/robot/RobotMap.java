/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  // For example to map the left and right motors, you could define the
  // following variables to use with your drivetrain subsystem.
  // public static int leftMotor = 1;
  // public static int rightMotor = 2;

  public static int PDP = 15; //  CAN ID 
  public static int PCM_ID = 0; // CAN ID 

  public static int TURBO_FORWARD = 1; // PCM PORT ID
  public static int TURBO_REVERSE = 2; // PCM PORT ID
  
  public static int FRONT_LEFT_DRIVEMOTOR = 13; // CAN ID
  public static int BACK_LEFT_DRIVEMOTOR = 14; // CAN ID 
  public static int FRONT_RIGHT_DRIVEMOTOR = 12; // CAN ID 
  public static int BACK_RIGHT_DRIVEMOTOR = 11; // CAN ID 

  public static int INTAKE_MOTOR = 5; // CAN ID
  public static int INTAKE_SOLENOID = 0; // PCM Port

  public static int HATCH_MANIPULATOR_SOLENOID = 3; // PCM Port

  public static int LIFT_MOTOR_1 = 1; // CAN ID 
  public static int LIFT_MOTOR_2 = 2; // CAN ID 
  public static int LIFT_MOTOR_3 = 3; // CAN ID 
  public static int LIFT_MOTOR_4 = 4; // CAN ID
  public static int LIFT_HOMESWITCH = 9; // DIO PORT

  public static int PLUNGER_MOTOR = 6; // CAN ID
  public static int ANALOG_POTENTIOMETER = 0; // ANALOG Port
 
  public static int Relay = 0; // RELAY Port
 
  public static int GAMEPAD_PORT_ID = 0; // USB Port (driver station)
  public static int SadStick_PORT_ID = 1; //USB Port (driver station)
  
  public static int A_BUTTON = 1;
  public static int B_BUTTON = 2;
  public static int X_BUTTON = 3;
  public static int Y_BUTTON = 4;
  public static int LEFT_BUMPER = 5;
  public static int RIGHT_BUMPER = 6;
  public static int BACK_BUTTON = 7;
  public static int START_BUTTON = 8;
  public static int LEFT_STICK = 9;
  public static int RIGHT_STICK = 10;

  public static int POV_UP = 0;
  public static int POV_DOWN = 180;
  public static int POV_LEFT = 270;

  public static int LEFT_STICK_AXIS_X = 0;
  public static int LEFT_STICK_AXIS_Y = 1;
  public static int LEFT_TRIGGER = 2;
  public static int RIGHT_TRIGGER = 3;
  public static int RIGHT_STICK_AXIS_X = 4;
  public static int RIGHT_STICK_AXIS_Y = 5;

// SECOND CONTROLLER FOR SECOND DRIVE LIFT OPTION ("Go to this positon on lift")

public static int GAMEPAD_PORT_ID_2 = 1;

}