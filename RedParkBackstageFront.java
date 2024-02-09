package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

//REMINDER -- Battery Level effects Motor Power which effects timing
@Autonomous (name = "RedParkBackstageFront")
//@Disabled

public class RedParkBackstageFront extends LinearOpMode {

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

        Bob.Straight(.5, 100, 1); //Forward
        Thread.sleep(500);
        Bob.CenterSpin(.5,670,1); //turn right 90 degrees
        Thread.sleep(500);
        Bob. Straight(.5,3100,1); //forward
        Thread.sleep(200);
    }
}