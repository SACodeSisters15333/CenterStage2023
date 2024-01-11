package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

//REMINDER -- Battery Level effects Motor Power which effects timing
@Autonomous (name = "RedParkFrontRight")
//@Disabled

public class RedParkFrontRight extends LinearOpMode {

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

        Bob.Straight(.5, 875, 1); //Forward
        Thread.sleep(500);
        Bob.CenterSpin(.5,710,1); //turn right 90 degrees
        //Adjust stop time to pick up pixel
        Thread.sleep(3000);
        Bob. Straight(.5,2500,1); //forward
        Thread.sleep(200);
        Bob.Sideways(.5,800,1); //right
        Thread.sleep(500);
        Bob.Straight(.5,400,1); //forward to park
        Thread.sleep(500);
    }
}