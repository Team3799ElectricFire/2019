/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.InvertType;
//import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
//import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
//import com.ctre.phoenix.motorcontrol.RemoteLimitSwitchSource;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import frc.robot.RobotMap;

public class Lift extends PIDSubsystem {

  private static double posP = 0.1; // TODO Tune PID
  private static double posI = 0.0; 
  private static double posD = 0.0;

  public final double liftOffset = +22.35;

  private TalonSRX m_lift;
  private TalonSRX m_lift2;
  private TalonSRX m_lift3;
  private TalonSRX m_lift4;
  private int liftTimeOut;

  private DigitalInput homeSwitch;

  public Lift() {
    super("Lift", posP, posI, posD);
    this.setAbsoluteTolerance(0.5); // inches?
    this.setOutputRange(-0.5, +1.0); // %outpput
    this.setInputRange(-27.49 + liftOffset, 4.62 + liftOffset); // inches
    getPIDController().setContinuous(false); // Output does not wrap around

    m_lift = new TalonSRX(RobotMap.LIFT_MOTOR_1);
    m_lift2 = new TalonSRX(RobotMap.LIFT_MOTOR_2);
    m_lift3 = new TalonSRX(RobotMap.LIFT_MOTOR_3);
    m_lift4 = new TalonSRX(RobotMap.LIFT_MOTOR_4);

    homeSwitch = new DigitalInput(RobotMap.LIFT_HOMESWITCH);

    // m_lift.configFactoryDefault();
    // m_lift2.configFactoryDefault();
    // m_lift3.configFactoryDefault();
    // m_lift4.configFactoryDefault();

    // m_lift.configForwardLimitSwitchSource(LimitSwitchSource.RemoteTalonSRX,
    // LimitSwitchNormal.NormallyClosed);
    // m_lift.configReverseLimitSwitchSource(LimitSwitchSource.RemoteTalonSRX,
    // LimitSwitchNormal.NormallyClosed);

    // m_lift.configOpenloopRamp(0.5); // seconds from neutral to full demand, other
    // motors follow this leader

    m_lift2.follow(m_lift);
    m_lift3.follow(m_lift);
    m_lift4.follow(m_lift);

    m_lift.setInverted(true);
    m_lift2.setInverted(InvertType.FollowMaster);
    m_lift3.setInverted(InvertType.FollowMaster);
    m_lift4.setInverted(InvertType.FollowMaster);

    m_lift.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, liftTimeOut);
    m_lift.setSensorPhase(false); // set false so movement up = positive encoder tracking
  }

  @Override
  public void initDefaultCommand() {
    // none
  }

  @Override
  protected double returnPIDInput() {
    // Return your input value for the PID loop
    // e.g. a sensor, like a potentiometer:
    // yourPot.getAverageVoltage() / kYourMaxVoltage;
    return GetLiftPosition();
  }

  @Override
  protected void usePIDOutput(double output) {
    // Use output to drive your system, like a motor
    // e.g. yourMotor.set(output);
    m_lift.set(ControlMode.PercentOutput, output);
  }

  public double GetLiftPosition() {
    return m_lift.getSelectedSensorPosition(0) * 4.5 / 4096;
  }

  public double GetLiftVelocity() {
    return m_lift.getSelectedSensorVelocity(0) * 4.5 / 4096;
  }

  public boolean GetHomeSwitch() {
    return homeSwitch.get();
  }

  public void setMotorsLift(double ElevatorSpeed) {
    //System.out.println("Setting Lift Motor Speed: " + ElevatorSpeed);
    m_lift.set(ControlMode.PercentOutput, ElevatorSpeed);
  }

  public void checkHomeSwitch() {
    if (GetHomeSwitch()) {
      zeroEncoder();
    }
  }

  public void zeroEncoder() {
    m_lift.setSelectedSensorPosition(0);
  }
}