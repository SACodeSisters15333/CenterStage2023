package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

//REMINDER -- Battery Level effects Motor Power which effects timing

@Autonomous (name = "AutoKPSDistance")

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

        Thread.sleep(1000);

        Jude.CalculateSpinTime(360,0.5);
        Jude.CenterSpin(0.5, (long) Jude.driveTime, 1);

        //Move Forward 24 inches = 60.96 cm
        Jude.CalculateDriveTime(60.96, Jude.chassisWheelRadius, 0.5, Jude.chassisMotorSpeed);
        System.out.println("Drive Time in milliSeconds");
        System.out.println(Jude.driveTime);

        Jude.Straight(0.5, Jude.driveTime, 1);
        Thread.sleep(1000);

        Jude.StopMotion(1000);

        //Move Left for 12 inches = 30.48 cm
        Jude.CalculateDriveTime(30.48, Jude.chassisWheelRadius, 0.5, Jude.chassisMotorSpeed);
        Jude.Sideways(.5, (long)(2* Jude.driveTime), -1); //Left
        Thread.sleep(1000);
        //casting to a long data type treats driveTime as an integer

        Jude.StopMotion(1000);

        //Move Backwards 36 inches = 60.96+30.48 cm
        Jude.CalculateDriveTime(91.44, Jude.chassisWheelRadius, 0.5, Jude.chassisMotorSpeed);
        Jude.Straight(0.5, Jude.driveTime, 1);//Forwards
        Thread.sleep(1000);

        Jude.StopMotion(1000);

        //Move Right for 12 inches = 30.48 cm
        Jude.CalculateDriveTime(30.48, Jude.chassisWheelRadius, 0.5, Jude.chassisMotorSpeed);
        Jude.Sideways(.5, (long) (2*Jude.driveTime), 1); //Right
        Thread.sleep(1000);
        //casting to a long data type treats driveTime as an integer

        //Move Right for 12 inches = 30.48 cm
        Jude.CalculateDriveTime(152.4, Jude.chassisWheelRadius, 0.5, Jude.chassisMotorSpeed);
        Jude.Straight(.5, (long) (Jude.driveTime), -1); //Backwards
        Thread.sleep(1000);
        //casting to a long data type treats driveTime as an integer



        Jude.StopMotion(1000);

        //put in diag0nol driving

    }
}