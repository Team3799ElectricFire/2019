/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

// Class taken from example code provided by team 1515
// Source: https://github.com/mortorqrobotics/Robama/blob/master/src/org/team1515/robama/AxisButton.java

package frc.robot.Utility;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;


public class AxisButton extends Button {
    Joystick Gamepad;
    int axis;
    double pressThreshold;
    double releaseThreshold;
    int reverseFactor;
    boolean wasPressed;

    public AxisButton(Joystick Gamepad, int axis, double pressThreshold, double releaseThreshold, boolean reverse) {
        this.Gamepad = Gamepad;
        this.axis = axis;
        this.reverseFactor = reverse ? -1 : 1;
        this.pressThreshold = pressThreshold * reverseFactor;
        this.releaseThreshold = releaseThreshold * reverseFactor;
        this.wasPressed = false;
    }

    public AxisButton(Joystick Gamepad, int axis, double pressThreshold, double releaseThreshold) {
        this(Gamepad, axis, pressThreshold, releaseThreshold, false);
    }

    @Override
    public boolean get() {
        double threshold = wasPressed ? releaseThreshold : pressThreshold;
        wasPressed = reverseFactor * Gamepad.getRawAxis(axis) > threshold;
        return wasPressed;
    }

}