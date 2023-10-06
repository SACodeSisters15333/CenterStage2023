package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BHI260IMU;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
//@Disabled

public class TeleOpCenterStage15333 extends LinearOpMode {

    public DcMotor frontRight = null;
    public DcMotor frontLeft = null;
    public DcMotor backRight = null;
    public DcMotor backLeft = null;
    public Servo claw;

    //the reason we use DcMotor Ex instead of DcMotor is for extra speed
    public DcMotorEx slidesLeft;
    public DcMotorEx slidesRight;

    @Override
    public void runOpMode() throws InterruptedException {
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");

        //slidesLeft = hardwareMap.get(DcMotorEx.class, "slidesLeft");
        //slidesRight = hardwareMap.get(DcMotorEx.class, "slidesRight");
        //claw = hardwareMap.servo.get("claw");

        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        frontRight.setDirection(DcMotorSimple.Direction.FORWARD);
        backRight.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        // silly field centric!
        // will retrieve imu from hardware map
        // specifices as radians just in case
        BHI260IMU imu = hardwareMap.get(BHI260IMU.class, "imu");
        IMU.Parameters parameters = new Parameters();
        parameters.imuOrientationOnRobot = BHI260IMU.Parameters.Radians;
        // without this, data retrieving from the IMU throws an exception
        imu.initialize(parameters);

        waitForStart();

        while (opModeIsActive()) {

            //for power play in 2023 the sticsk were reversed
            //normally double y should be = -gamepad (we removed negativein 2023)
            double y = -gamepad1.left_stick_y; // Remember, this is reversed!
            double x = gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
            double rx = gamepad1.right_stick_x;

            // Read inverse IMU heading, as the IMU heading is CW positive
            double botHeading = -imu.getRobotOrientation().firstAngle;

            double rotX = x * Math.cos(botHeading) - y * Math.sin(botHeading);
            double rotY = x * Math.sin(botHeading) + y * Math.cos(botHeading);

            // Denominator is the largest motor power (absolute value) or 1
            // This ensures all the powers maintain the same ratio, but only when
            // at least one is out of the range [-1, 1]
            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
            double frontLeftPower = (rotY + rotX + rx) / denominator;
            double backLeftPower = (rotY - rotX + rx) / denominator;
            double frontRightPower = (rotY - rotX - rx) / denominator;
            double backRightPower = (rotY + rotX - rx) / denominator;

            frontLeft.setPower(frontLeftPower);
            backLeft.setPower(backLeftPower);
            frontRight.setPower(frontRightPower);
            backRight.setPower(backRightPower);

            // opening the claw!
           /* if (gamepad1.right_bumper) {
                claw.setPosition(0.2); } */

            // closing the claw!
          /*  else if (gamepad1.left_bumper) {
                claw.setPosition(1); } */

            // silly claw telemetry!
           // telemetry.addData("Servo Position", claw.getPosition());
           // telemetry.update();
        }
    }
}





