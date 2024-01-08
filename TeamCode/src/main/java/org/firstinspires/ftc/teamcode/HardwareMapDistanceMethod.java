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
    double cmToMove; // desired number of centimeters for Chassis to move
    double chassisWheelDiameter=10.16; // diameter in cm for radius 4" wheel
    double chassisMotorSpeed=312; //chassis motor speed is 312 rev per minute - account for gearing


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

        frontRight.setDirection(DcMotorSimple.Direction.FORWARD); //Competition Bot
        backRight.setDirection(DcMotorSimple.Direction.REVERSE); //Competition Bot
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE); //Competition Bot
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE); //Competition Bot


        //Initialize Lift
        //slidesLeft = HWMap.dcMotor.get("slidesLeft");
        //slidesRight = HWMap.dcMotor.get("slidesRight");

        //Initialize Servos
        //claw = HWMap.crservo.get("claw");
        //claw = HWMap.servo.get("claw");

    }  //end of method InitializeRobot

    // ---------------------------------------Determine Time needed for Distance Desired -----------

    public void CalculateDriveTime(double cmToMove, double power) throws InterruptedException {
        /*
        Calculates number of milliSeconds to move the desired amount of centimeters
        Global variables declared: wheelRadius; chassisMotorSpeed, circumference
        output to variable driveTime

        Information
        Radius of chassis wheel in centimeters to nearest tenth
        goBilda Mecanum lists wheels as 96 mm = 9.6cm for full diameter

        Chassis motors for CenterStage Spring 2024 is 312 rev per min
        theoretical speed per website https://www.gobilda.com/strafer-chassis-kit-96mm-mecanum-wheels/ is 5.14 ft/sec

        IMPORTANT -- consider Robot Weight as well as power assigned to motion (100% versus 50% versus 25 % etc)
        Helpful to do analysis on how far robot moves in 1 sec at afull power to get a better understading
        of the competition robots speed capability (can "ignore" rev per minute

        based on testing of robot for CenterStage Spring 2024 --
        1 sec at full power moved 37 in (this will be the conversion factor used)

        */

        driveTime= 0; //sets drive time to 0

        //double testSpeedInches = 60; //inches moved in 1 sec at 100% power (units in/sec)
        //double testSpeedCm = testSpeedInches*2.54;//2.54 cm in an inches - cm moved in 1 sec at 100% power (units cm/sec)

        long circumference = (long) (3.14159*chassisWheelDiameter);

        long msec = 60*1000; //number of milliseconds in a minute

        /*  is this stuff needed?
        double revSpeed; //number of revolutions based on power chosen for motor speed
        //if full power is 312 rev per minute; then 50% power if 156 rev per minute
        //hence speed calculation based on 156 rev per minute

        revSpeed = speed*power;
        */

        driveTime = (cmToMove/(circumference*chassisMotorSpeed))*(msec/power);
        //driveTime = (cmToMove/testSpeedCm)*(msec/power);

    }//end CalculateTime


    public void CalculateSpinTime(double angle, double power) throws InterruptedException {
        // Calculates number of milliSeconds to spin a specific angle in degreews
        //output to variable driveTime

        long msec = 1000; //number of milliseconds in a sec

        double revSpeed; //number of revolutions based on power chosen for motor speed
        //if full power is 270 degree per sec; then 50% power if 135 degrees per sec
        //hence speed calculation based on 135 degrees per secon

        double DegPerSec = 225; //200 degree per sec base on wheel arc since not a true center

        driveTime = (angle/DegPerSec)*(msec/power);

    }//end CalculateSpinTime



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