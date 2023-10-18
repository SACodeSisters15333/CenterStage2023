package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

//REMINDER -- Battery Level effects Motor Power which effects timing

@Autonomous (name = "MecPlayAuto")

@Disabled

public class AutoPracticeLearningOcto extends LinearOpMode {

    HardwareMapCenterStage15333 Wall_E = new HardwareMapCenterStage15333();

    @Override

    public void runOpMode() throws InterruptedException {

        System.out.println("Starting up");
        telemetry.addData("init pressed", "about to initialize");
        telemetry.update();

        System.out.println("Initialize Robot");
        Wall_E.InitializeRobot(hardwareMap);
        System.out.println("Robot Initialized");

        telemetry.addData("Status", "Ready!");

        telemetry.update();

        waitForStart();

        Wall_E.Straight(.5, 500, 1); //Forward
        Thread.sleep(1000);
        Wall_E.Sideways(1,1000,1); //Drive Right
        Thread.sleep(500);
        Wall_E.Sideways(1,1500,-1); //Drive Right
        Thread.sleep(500);
        Wall_E.DiagonalForward(.5, 1000, -1); //Backward
        Thread.sleep(250);

    }
}