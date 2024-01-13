/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.DriveTank;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SPI;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.kauailabs.navx.frc.*;

public class TankDrive extends Subsystem {
  private TalonSRX m_frontleft;
  private TalonSRX m_backleft;
  private TalonSRX m_frontright;
  private TalonSRX m_backright;
  private int rightTimeOut;
  private int leftTimeOut;
  private AHRS NavxSensor;
  double distance;
  double y_location;
  double x_location;
  double previous_left_position;
  double previous_right_position;
  double scale; // reduce max output to make drivetrain less twitchy.
  // double scaleLow = 0.4;

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new DriveTank());
  }

  public TankDrive() {
    scale = 0.7;

    m_frontleft = new TalonSRX(RobotMap.FRONT_LEFT_DRIVEMOTOR);
    m_backleft = new TalonSRX(RobotMap.BACK_LEFT_DRIVEMOTOR);
    m_frontright = new TalonSRX(RobotMap.FRONT_RIGHT_DRIVEMOTOR);
    m_backright = new TalonSRX(RobotMap.BACK_RIGHT_DRIVEMOTOR);

    m_frontleft.configFactoryDefault();
    m_backleft.configFactoryDefault();
    m_frontright.configFactoryDefault();
    // m_backright.configFactoryDefault();

    m_frontleft.setNeutralMode(NeutralMode.Brake);
    m_backleft.setNeutralMode(NeutralMode.Brake);
    m_frontright.setNeutralMode(NeutralMode.Brake);
    m_backright.setNeutralMode(NeutralMode.Brake);

    // m_frontleft.configOpenloopRamp(0.5); // seconds from neutral to full demand,
    // other motors follow this leader
    // m_frontright.configOpenloopRamp(0.5); // seconds from neutral to full demand,
    // other motors follow this leader

    m_backleft.follow(m_frontleft);
    m_backright.follow(m_frontright);

    m_frontright.setInverted(true);
    m_backright.setInverted(InvertType.FollowMaster);

    m_frontright.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, rightTimeOut);
    m_frontright.setSensorPhase(true);
    m_frontleft.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, leftTimeOut);
    m_frontleft.setSensorPhase(true);

    try {
      NavxSensor = new AHRS(SPI.Port.kMXP);
    } catch (Exception e) {
      DriverStation.reportError("Error instantiating Navx: " + e.getMessage(), true);
    }
  }

  public double PositionRight() {
    return m_frontright.getSelectedSensorPosition(0) * 10.21 / 4096;
  }

  public double PositionLeft() {
    return m_frontleft.getSelectedSensorPosition(0) * 10.21 / 4096;
  }

  public double VelocityRight() {
    return m_frontright.getSelectedSensorVelocity(0) * 10.21 / 4096;
  }

  public double VelocityLeft() {
    return m_frontleft.getSelectedSensorVelocity(0) * 10.21 / 4096;
  }

  public void ToggleScale(){
    if (scale == 0.7) {
      scale = 0.4;
    } else {
      scale = 0.7;
    }
  }

  public void setMotors(double leftSpeed, double rightSpeed) {
    m_frontleft.set(ControlMode.PercentOutput, leftSpeed * leftSpeed * leftSpeed * scale);
    m_frontright.set(ControlMode.PercentOutput, rightSpeed * rightSpeed * rightSpeed * scale);
  }

  public double getYaw() {
    return NavxSensor.getYaw();
  }

  public void zeroYaw() {
    NavxSensor.zeroYaw();
  }

  public void resetOdometry() {
    distance = 0;
    x_location = 0;
    y_location = 0;

    m_frontright.setSelectedSensorPosition(0);
    m_frontleft.setSelectedSensorPosition(0);
  }

  public void Odometry() {
    double right_change = PositionRight() - previous_right_position;
    double left_change = PositionLeft() - previous_left_position;

    distance = (left_change + right_change) / 2;
    x_location = x_location + distance * Math.cos(NavxSensor.getYaw() * Math.PI / 180);
    y_location = y_location + distance * Math.sin(NavxSensor.getYaw() * Math.PI / 180);

    previous_left_position = PositionLeft();
    previous_right_position = PositionRight();

    // System.out.println("RC: " + right_change + " LC: " + left_change + " dist: "
    // + distance);
  }

  public double get_X_Location() {
    return x_location;
  }

  public double get_Y_Location() {
    return y_location;
  }

  public void score() {

  }
}
