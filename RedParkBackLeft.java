package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

//REMINDER -- Battery Level effects Motor Power which effects timing
@Autonomous (name = "RedParkBackLeft")
//@Disabled

public class RedParkBackLeft extends LinearOpMode {

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

        Bob.Straight(.5, 900, 1); //Forward
        Thread.sleep(500);
        //Adjust time or stop period based on how were picking up pixel
        Bob.CenterSpin(.5,800,-1); //turn left 90 degrees
        Thread.sleep(3000);
        Bob.CenterSpin(.5,1650,1); //turn right 180 degrees
        Thread.sleep(500);
        Bob. Straight(.5,800,1); //forward
        Thread.sleep(200);
        Bob.Sideways(.5,900,1); //right
        Thread.sleep(500);
        Bob.Straight(.5,400,1); //forward to park
        Thread.sleep(500);

    }
}