package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Original created by ashley.peake on 8/30/2018.
 * modified by KPSorrells (for teaching RoboLearners) 11/26/2022
 */

//@Disabled 
public class HardwareMapDistanceMethod {

    // drivetrain motors
    public DcMotor frontLeft;
    public DcMotor backLeft;
    public DcMotor frontRight;
    public DcMotor backRight;

    // lift motors
    //public DcMotor slidesLeft;
    //public DcMotor slidesRight;

    // servos or claw - CR = continuous Servo
    // public CRServo claw;
   // public Servo claw = null;

    //Sets variable driveTime as global
    double driveTime;

    //Global Constants and Variables

    //Radius of chassis wheel in centimeters to nearest tenth
    //goBilda Mecanum lists wheels as 96 mm = 9.6cm for full diameter
    double chassisWheelRadius=9.6/2; //divide diameter by 2 for radius
    int chassisMotorSpeed=312; //chassis motor speed in rev per minute

    //enter Rev per min of any motor - be specific about motor purpose



//----------------------------Initialize Robot ---------------------------------
  /*  This method allows us to initialize the motors and sensors only once.
      It is called and used in every other program after "Init" is pressed.
   */

    public void InitializeRobot(HardwareMap hwMap) {

        HardwareMap HWMap = hwMap;

        //initialize Wheels
        frontLeft = HWMap.dcMotor.get("frontLeft");
        backLeft = HWMap.dcMotor.get("backLeft");
        frontRight = HWMap.dcMotor.get("frontRight");
        backRight = HWMap.dcMotor.get("backRight");


        // Reverse the right side motors
        // Reverse left motors if you are using NeveRests
        //frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);//set for PracticeBot
        //backLeft.setDirection(DcMotorSimple.Direction.REVERSE); //Set for PracticeBot
        //frontRight.setDirection(DcMotorSimple.Direction.REVERSE);//Competition Bot PowerPlay
        frontRight.setDirection(DcMotorSimple.Direction.FORWARD); //Practice Bot
        backRight.setDirection(DcMotorSimple.Direction.FORWARD); //Competition Bot
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE); //Competition Bot & PracticeBot
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE); //Competition Bot & PracticeBot /


        //Initialize Lift
        //slidesLeft = HWMap.dcMotor.get("slidesLeft");
        //slidesRight = HWMap.dcMotor.get("slidesRight");

        //Initialize Servos
        //claw = HWMap.crservo.get("claw");
        //claw = HWMap.servo.get("claw");

    }  //end of method InitializeRobot

    // ---------------------------------------Determine Time needed for Distance Desired -----------

    public void CalculateDriveTime(double centimeters, double radius, double power, double speed) throws InterruptedException {
        // Calculates number of milliSeconds to move the desired amount of centimeters
        //Global variables declared: wheelRadius; chassisMotorSpeed, circumference
        //output to variable driveTime

        double circumference; //circumference of type of wheel in use
        long msec = 60000; //number of milliseconds in a minute (60 *1000)

        circumference= (float) 2*3.14*radius;
        driveTime = centimeters/circumference/speed*power*msec;

    }//end CalculateTime





//--------------------------Driving Pathways------------------------------------

  /*  This method allows the robot to drive forward based on encoder values.
      A distance is given that is converted to an encoder position in the code.
      leftDirection and rightDirection set the direction of the motors to allow
      the robot to either move straight ot turn.
   */

    public void Straight(double power, double milliSeconds, int Direction) throws InterruptedException {

        //For driving forward or backward
        // declare variables for this method (power, milliSeconds (milliseconds) & Direction)
        //For forwards set direction = 1 (In method call)
        // For backwards set direction = -1 (In method call)
        //example: driveStraight(1, 5, 1) means drive straight at 100% power, for 5 seconds, in forward direction
        //example: driveStraight(1, 5, -1) means drive straight at 100% power, for 5 seconds, in backwards direction

        frontLeft.setPower(power * Direction);
        backLeft.setPower(power * Direction);
        frontRight.setPower(power * Direction);
        backRight.setPower(power * Direction);

        Thread.sleep((long) milliSeconds);

        //Stop Robot
        frontLeft.setPower(0.0);
        backLeft.setPower(0.0);
        frontRight.setPower(0.0);
        backRight.setPower(0.0);

    } //End DriveStraight Method

    public void Sideways(double power, long milliSeconds, int Direction) throws InterruptedException {

        //For strafing to the left or the right
        // declare variables for this method (power, milliSeconds (milliseconds) & Direction)
        //For right motion set direction = 1 (In method call)
        //For left motion set direction = -1 (In method call)
        //example: driveSideways(.5, 3, 1) means drive straight at 50% power, for 3 seconds, in right direction
        //example: driveSideways(.75, 5, -1) means drive straight at 75% power, for 5 seconds, in left direction

        frontLeft.setPower(power * Direction);
        backLeft.setPower(power * -Direction);
        frontRight.setPower(power * -Direction);
        backRight.setPower(power * Direction);

        Thread.sleep(milliSeconds);

        // stops all motion
        frontLeft.setPower(0.0);
        backLeft.setPower(0.0);
        frontRight.setPower(0.0);
        backRight.setPower(0.0);

    } //Ends DriveSideways Method

    public void DiagonalForward(double power, long milliSeconds, int Direction) throws InterruptedException {

        //For driving forward in a diagonal direction
        // declare variables for this method (power, milliSeconds (milliseconds) & Direction)
        //For right motion set direction = 1 (In method call)
        //For left motion set direction = -1 (In method call)
        //example: DiagonalForward(.8, 3, 1) means drive straight at 80% power, for 3 seconds, in forward right direction
        //example: DiagonalForward(.75, 5, -1) means drive straight at 75% power, for 5 seconds, in forward left direction

        if (Direction == 1) {

            frontLeft.setPower(power * Direction);
            backLeft.setPower(0);
            frontRight.setPower(0);
            backRight.setPower(power * Direction);

            Thread.sleep(milliSeconds);
        }

        if (Direction == -1) {

            frontLeft.setPower(0);
            backLeft.setPower(power * -Direction);
            frontRight.setPower(power * -Direction);
            backRight.setPower(0);

            Thread.sleep(milliSeconds);
        }

        // stops all motion

        frontLeft.setPower(0.0);
        backLeft.setPower(0.0);
        frontRight.setPower(0.0);
        backRight.setPower(0.0);

    } //End Diagonal Forward Method


    public void DiagonalBackward(double power, long milliSeconds, int Direction) throws InterruptedException {

        //For driving forward in a diagonal direction
        // declare variables for this method (power, milliSeconds (milliseconds) & Direction)
        //For right motion set direction = 1 (In method call)
        //For left motion set direction = -1 (In method call)
        //example: DiagonalBackward(.8, 3, 1) means drive straight at 80% power, for 3 seconds, in back Right direction
        //example: DiagonalForward(.75, 5, -1) means drive straight at 75% power, for 5 seconds, in left direction

        if (Direction == 1) {

            frontLeft.setPower(0);
            backLeft.setPower(power * -Direction);
            frontRight.setPower(power * -Direction);
            backRight.setPower(0);

            Thread.sleep(milliSeconds);
        }

        if (Direction == -1) {

            frontLeft.setPower(power * Direction);
            backLeft.setPower(0);
            frontRight.setPower(0);
            backRight.setPower(power * Direction);

            Thread.sleep(milliSeconds);
        }

        // stops all motion

        frontLeft.setPower(0.0);
        backLeft.setPower(0.0);
        frontRight.setPower(0.0);
        backRight.setPower(0.0);

    } //End Diagonal Backward Method


    public void CenterSpin(double power, long milliSeconds, int Direction) throws InterruptedException {

        //For turning robot on center
        // declare variables for this method (power, milliSeconds (milliseconds) & Direction)
        //For right motion set direction = 1 (In method call)
        //For left motion set direction = -1 (In method call)
        //180 degree spin at 100% power takes -----; at 75% takes ----; at 50% takes
        //90 degree spring at 100% power takes----; at 75% takes ----; at 50% takes
        //Check BATTERY Power

        //example: CenterSpin(1, 3, 1) means spin at 100% power, for 3 seconds, to the right

        if (Direction == 1) {

            frontLeft.setPower(power * Direction);
            backLeft.setPower(power * Direction);
            frontRight.setPower(power * -Direction);
            backRight.setPower(power * -Direction);

            Thread.sleep(milliSeconds);
        }

        if (Direction == -1) {

            frontLeft.setPower(power * Direction);
            backLeft.setPower(power * Direction);
            frontRight.setPower(power * -Direction);
            backRight.setPower(power * -Direction);

            Thread.sleep(milliSeconds);
        }

        // stops all motion

        frontLeft.setPower(0.0);
        backLeft.setPower(0.0);
        frontRight.setPower(0.0);
        backRight.setPower(0.0);

    } //End CenterSpin Method

    public void StopMotion(double milliseconds) throws InterruptedException {
        // stops all motion

        frontLeft.setPower(0.0);
        backLeft.setPower(0.0);
        frontRight.setPower(0.0);
        backRight.setPower(0.0);
    }//end StopMotion Method
  /*
    public void moveLift(double power, long milliSeconds, int Direction) throws InterruptedException{
        slidesLeft.setPower(power * Direction);
        slidesRight.setPower(power * Direction);
        Thread.sleep(milliSeconds);

        //lift.setPower(0);
    }//end moveLift Method
*/

/*
    public void moveClaw(double power) throws InterruptedException{
        claw.setPosition(power);
        //Thread.sleep(milliSeconds);

        //slidesLeft.setPower(0);
        //slidesRight.setPower(0);

    }
*/

}