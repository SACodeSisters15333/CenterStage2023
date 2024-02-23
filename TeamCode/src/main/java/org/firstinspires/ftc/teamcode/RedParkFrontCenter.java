package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

//REMINDER -- Battery Level effects Motor Power which effects timing
@Autonomous (name = "RedParkFrontCenter")
//@Disabled

public class RedParkFrontCenter extends LinearOpMode {

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
        Thread.sleep (500);
        Bob.intake(-0.75,900,1);
        //drop pixel
        Bob.Straight(.5,800,-1);
        Thread.sleep(500);
       Bob.Sideways(.5,2600,1); //Left to park
      Thread.sleep(500);
    }
}