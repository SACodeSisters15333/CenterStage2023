package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

//REMINDER -- Battery Level effects Motor Power which effects timing
@Autonomous (name = "RedParkBackstageBack")
//@Disabled

public class RedParkBackstageBack extends LinearOpMode {

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

        //Bob.Sideways(.5, 4200, 1); //Forward

        Bob.frontRight.setDirection(DcMotorSimple.Direction.REVERSE); //motion Backward
        Bob.backRight.setDirection(DcMotorSimple.Direction.FORWARD); //motion Forward
        Bob.frontLeft.setDirection(DcMotorSimple.Direction.FORWARD); //motion Backwards
        Bob.backLeft.setDirection(DcMotorSimple.Direction.REVERSE); //CoRmpetition Bot & PracticeBot /

        Bob.frontLeft.setPower(0.5);
        Bob.frontRight.setPower(0.5);
        Bob.backLeft.setPower(0.5);
        Bob.backRight.setPower(0.5);

        Thread.sleep(3000);

        Bob.frontLeft.setPower(0);
        Bob.frontRight.setPower(0);
        Bob.backLeft.setPower(0);
        Bob.backRight.setPower(0);


    }
}