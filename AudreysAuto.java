package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

//REMINDER -- Battery Level effects Motor Power which effects timing

@Autonomous (name = "AudreysAuto")

//@Disabled

public class AudreysAuto extends LinearOpMode {

    HardwareMapCenterStage15333 Bob = new HardwareMapCenterStage15333();

    @Override

    public void runOpMode() throws InterruptedException {

        System.out.println("Starting up");
        telemetry.addData("init pressed", "about to initialize");
        telemetry.update();

        System.out.println("Initialize Robot");
        Bob.InitializeRobot(hardwareMap);
        System.out.println("Robot Initialized");

        telemetry.addData("Status", "Ready!");
        telemetry.update();

        waitForStart();

        Bob.Straight(.5, 2500, 1); //Forward
        Thread.sleep(1000);
        Bob.Sideways(.5,2500,-1); //Left
        Thread.sleep(500);
        Bob.Sideways(.5,1000,1); //Right
        Thread.sleep(500);
        //.Straight(.5, 4000, -1); //Backward
        Thread.sleep(250);

    }
}