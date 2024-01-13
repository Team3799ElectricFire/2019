/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

// Class taken from example code provided by team 1515
// Source: https://github.com/mortorqrobotics/Robama/blob/master/src/org/team1515/robama/POVButton.java

package frc.robot.Utility;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;

/**
 * Add your docs here.
 */
public class POVButton extends Button {
	
	Joystick joystick;
	int num;
	
	public POVButton(Joystick joystick, int num) {
		this.joystick = joystick;
		this.num = num;
	}

	@Override
	public boolean get() {
		return joystick.getPOV() == num;
	}

}