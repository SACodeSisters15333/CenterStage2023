package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BHI260IMU;
//import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;

    @TeleOp
//@Disabled

public class CenterstageTeleOp extends LinearOpMode {

        public DcMotor frontRight = null;
        public DcMotor frontLeft = null;
        public DcMotor backRight = null;
        public DcMotor backLeft = null;
      //  public Servo clawLeft;
       // public Servo clawRight;
        //public Servo droneLauncher;
      //  public Servo hangHold;
        //the reason we use DcMotor Ex instead of DcMotor is for extra speed
       // public DcMotor slides;
       // public DcMotor intake;

       // public DcMotor arm;
        //public DcMotor linearLift;

        @Override
        public void runOpMode() throws InterruptedException {
            frontRight = hardwareMap.get(DcMotor.class, "frontRight");
            frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
            backRight = hardwareMap.get(DcMotor.class, "backRight");
            backLeft = hardwareMap.get(DcMotor.class, "backLeft");
           //arm = hardwareMap.get(DcMotor.class, "arm");

            // slides = hardwareMap.get(DcMotor.class, "slides");
          //  intake = hardwareMap.get(DcMotor.class, "intake");
           // linearLift = hardwareMap.get(DcMotor.class, "linearLift");
           // droneLauncher = hardwareMap.servo.get("droneLauncher");
           // hangHold = hardwareMap.servo.get ("hangHold");

            //slidesLeft = hardwareMap.get(DcMotor.class, "slidesLeft");
            // slidesRight = hardwareMap.get(DcMotor.class, "slidesRight");
            // claw = hardwareMap.servo.get("claw");

            backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
           // arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

            frontRight.setDirection(DcMotor.Direction.FORWARD);
            backRight.setDirection(DcMotor.Direction.FORWARD);
            backLeft.setDirection(DcMotor.Direction.REVERSE);
            frontLeft.setDirection(DcMotor.Direction.REVERSE);

           // arm.setDirection(DcMotor.Direction.REVERSE);

            // slides.setDirection(DcMotor.Direction.REVERSE);

            //intake.setDirection(DcMotor.Direction.REVERSE);

           // linearLift.setDirection(DcMotor.Direction.REVERSE); /*

            // silly field centric!
            // will retrieve imu from hardware map
            // specifies as radians just in case
            BNO055IMU imu = hardwareMap.get(BNO055IMU.class, "imu");
            BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
            parameters.angleUnit = BNO055IMU.AngleUnit.RADIANS;

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
                double botHeading = -imu.getAngularOrientation().firstAngle;

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

                //Drone Launcher set off on drivetrain driver
              /*  if (gamepad1.y) {
                    droneLauncher.setPosition(0.8);
                }
                //hang hold reset
                if (gamepad1.x) {
                    hangHold.setPosition(0.5);
                }
                //hang hold
                if (gamepad2.x) {
                    hangHold.setPosition(0.1);
                }
                // linear lift
                if (gamepad2.y) {
                    linearLift.setPower(-0.5);
                } else if (gamepad2.a) {
                    linearLift.setPower(0.5);
                } else if (gamepad2.b) {
                    linearLift.setPower(0);
                } */

                //intake inward and outward


              //  if (gamepad2.right_bumper) {
                   // intake.setPower(0.2);
             //   } else if (gamepad2.right_trigger > 0) {
              //      intake.setPower(-0.2);
              //  } else intake.setPower(0);


                  /*
                //slides left bumper = up,  left trigger = down
                 if (gamepad2.left_bumper) {
                    slides.setPower(-0.25);
                } else if (gamepad2.left_trigger > 0) {
                    slides.setPower(0.25);
                } else slides.setPower(0);
                */

            }// close op mode
            }//while loop
        } //op mode

