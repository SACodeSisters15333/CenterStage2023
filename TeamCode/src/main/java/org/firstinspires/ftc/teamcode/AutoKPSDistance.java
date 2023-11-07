package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

//REMINDER -- Battery Level effects Motor Power which effects timing

@Autonomous (name = "LearningAutoCoding")

//@Disabled

public class AutoKPSDistance extends LinearOpMode {

    HardwareMapDistanceMethod Jude = new HardwareMapDistanceMethod();

    @Override

    public void runOpMode() throws InterruptedException {

        System.out.println("Starting up");
        telemetry.addData("init pressed", "about to initialize");
        telemetry.update();

        System.out.println("Initialize Robot");
        Jude.InitializeRobot(hardwareMap);
        System.out.println("Robot Initialized");

        telemetry.addData("Status", "Ready!");

        telemetry.update();

        waitForStart();

        //Move Forward 24 inches = 60.96 cm
        Jude.CalculateDriveTime(60.96, Jude.chassisWheelRadius, 0.5, Jude.chassisMotorSpeed);
        Jude.Straight(0.5, Jude.driveTime, 1);
        Thread.sleep(1000);

        Jude.StopMotion(1000);

        //Move Left for 12 inches = 30.48 cm
        Jude.CalculateDriveTime(60.96, Jude.chassisWheelRadius, 0.5, Jude.chassisMotorSpeed);
        Jude.Sideways(.5, (long) Jude.driveTime, -1); //Left
        Thread.sleep(1000);
        //casting to a long data type treats driveTime as an integer

        Jude.StopMotion(1000);

        //Move Backwards 24 inches = 60.96 cm
        Jude.CalculateDriveTime(60.96, Jude.chassisWheelRadius, 0.5, Jude.chassisMotorSpeed);
        Jude.Straight(0.5, Jude.driveTime, -1);//Backwards
        Thread.sleep(1000);

        Jude.StopMotion(1000);

        //Move Right for 12 inches = 30.48 cm
        Jude.CalculateDriveTime(60.96, Jude.chassisWheelRadius, 0.5, Jude.chassisMotorSpeed);
        Jude.Sideways(.5, (long) Jude.driveTime, 1); //Right
        Thread.sleep(1000);
        //casting to a long data type treats driveTime as an integer

        Jude.StopMotion(1000);

        //put in diag0nol driving

    }
}