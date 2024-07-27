package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Original created by ashley.peake on 8/30/2018.
 * modified by KPSorrells (for teaching RoboLearners) 11/26/2022
 */

//@Disabled 
public class HardwareMapCenterStage15333 {

    // drivetrain motors
    public DcMotor frontLeft;
    public DcMotor backLeft;
    public DcMotor frontRight;
    public DcMotor backRight;
   // public DcMotor intake;

    // lift motors
    //public DcMotor slidesLeft;
    //public DcMotor slidesRight;

    // servos or claw - CR = continuous Servo
    // public CRServo claw;
   // public Servo claw = null;

    //Sets variable driveTime as an integer
    int driveTime;


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
        //intake = HWMap.dcMotor.get ("intake");


        // Reverse the right side =
        // Reverse left motors if you are using NeveRests
        //frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);//set for PracticeBot
        //backLeft.setDirection(DcMotorSimple.Direction.REVERSE); //Set for PracticeBot
        //frontRight.setDirection(DcMotorSimple.Direction.REVERSE);//Competition Bot PowerPlay
        frontRight.setDirection(DcMotorSimple.Direction.FORWARD); //Practice Bot
        backRight.setDirection(DcMotorSimple.Direction.FORWARD); //Competition Bot
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE); //Competition Bot & PracticeBot
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE); //CoRmpetition Bot & PracticeBot /


        //Initialize Lift
        //slidesLeft = HWMap.dcMotor.get("slidesLeft");
        //slidesRight = HWMap.dcMotor.get("slidesRight");

        //Initialize Servos
        //claw = HWMap.crservo.get("claw");
        //claw = HWMap.servo.get("claw");

    }  //end of method InitializeRobot


//--------------------------Driving Pathways------------------------------------

  /*  This method allows the robot to drive forward based on encoder values.
      A distance is given that is converted to an encoder position in the code.
      leftDirection and rightDirection set the direction of the motors to allow
      the robot to either move straight ot turn.
   */

    public void Straight(double power, double totalSeconds, int Direction) throws InterruptedException {

        //For driving forward or backward
        // declare variables for this method (power, totalSeconds (milliseconds) & Direction)
        //For forwards set direction = 1 (In method call)
        // For backwards set direction = -1 (In method call)
        //example: driveStraight(1, 5, 1) means drive straight at 100% power, for 5 seconds, in forward direction
        //example: driveStraight(1, 5, -1) means drive straight at 100% power, for 5 seconds, in backwards direction

        frontLeft.setPower(power * Direction);
        backLeft.setPower(power * Direction);
        frontRight.setPower(power * Direction);
        backRight.setPower(power * Direction);

        Thread.sleep((long) totalSeconds);

        //Stop Robot
        frontLeft.setPower(0.0);
        backLeft.setPower(0.0);
        frontRight.setPower(0.0);
        backRight.setPower(0.0);

    } //End DriveStraight Method

    public void Sideways(double power, long totalSeconds, int Direction) throws InterruptedException {

        //For strafing to the left or the right
        // declare variables for this method (power, totalSeconds (milliseconds) & Direction)
        //For right motion set direction = 1 (In method call)
        //For left motion set direction = -1 (In method call)
        //example: driveSideways(.5, 3, 1) means drive straight at 50% power, for 3 seconds, in right direction
        //example: driveSideways(.75, 5, -1) means drive straight at 75% power, for 5 seconds, in left direction
        if (Direction==1) {
            frontLeft.setPower(power);
            backLeft.setPower(power);
            frontRight.setPower(-power);
            backRight.setDirection(DcMotorSimple.Direction.REVERSE);
        }

        Thread.sleep(totalSeconds);

        // stops all motion
        frontLeft.setPower(0.0);
        backLeft.setPower(0.0);
        frontRight.setPower(0.0);
        backRight.setPower(0.0);

    } //Ends DriveSideways Method

    public void DiagonalForward(double power, long totalSeconds, int Direction) throws InterruptedException {

        //For driving forward in a diagonal direction
        // declare variables for this method (power, totalSeconds (milliseconds) & Direction)
        //For right motion set direction = 1 (In method call)
        //For left motion set direction = -1 (In method call)
        //example: DiagonalForward(.8, 3, 1) means drive straight at 80% power, for 3 seconds, in forward right direction
        //example: DiagonalForward(.75, 5, -1) means drive straight at 75% power, for 5 seconds, in forward left direction

        if (Direction == 1) {

            frontLeft.setPower(power * Direction);
            backLeft.setPower(0);
            frontRight.setPower(0);
            backRight.setPower(power * Direction);

            Thread.sleep(totalSeconds);
        }

        if (Direction == -1) {

            frontLeft.setPower(0);
            backLeft.setPower(power * -Direction);
            frontRight.setPower(power * -Direction);
            backRight.setPower(0);

            Thread.sleep(totalSeconds);
        }

        // stops all motion

        frontLeft.setPower(0.0);
        backLeft.setPower(0.0);
        frontRight.setPower(0.0);
        backRight.setPower(0.0);

    } //End Diagonal Forward Method


    public void DiagonalBackward(double power, long totalSeconds, int Direction) throws InterruptedException {

        //For driving forward in a diagonal direction
        // declare variables for this method (power, totalSeconds (milliseconds) & Direction)
        //For right motion set direction = 1 (In method call)
        //For left motion set direction = -1 (In method call)
        //example: DiagonalBackward(.8, 3, 1) means drive straight at 80% power, for 3 seconds, in back Right direction
        //example: DiagonalForward(.75, 5, -1) means drive straight at 75% power, for 5 seconds, in left direction

        if (Direction == 1) {

            frontLeft.setPower(0);
            backLeft.setPower(power * -Direction);
            frontRight.setPower(power * -Direction);
            backRight.setPower(0);

            Thread.sleep(totalSeconds);
        }

        if (Direction == -1) {

            frontLeft.setPower(power * Direction);
            backLeft.setPower(0);
            frontRight.setPower(0);
            backRight.setPower(power * Direction);

            Thread.sleep(totalSeconds);
        }

        // stops all motion

        frontLeft.setPower(0.0);
        backLeft.setPower(0.0);
        frontRight.setPower(0.0);
        backRight.setPower(0.0);

    } //End Diagonal Backward Method


    public void CenterSpin(double power, long totalSeconds, int Direction) throws InterruptedException {

        //For turning robot on center
        // declare variables for this method (power, totalSeconds (milliseconds) & Direction)
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

            Thread.sleep(totalSeconds);
        }

        if (Direction == -1) {

            frontLeft.setPower(power * Direction);
            backLeft.setPower(power * Direction);
            frontRight.setPower(power * -Direction);
            backRight.setPower(power * -Direction);

            Thread.sleep(totalSeconds);
        }

        // stops all motion

        frontLeft.setPower(0.0);
        backLeft.setPower(0.0);
        frontRight.setPower(0.0);
        backRight.setPower(0.0);

    } //End CenterSpin Method

    public void StopMotion(double seconds) throws InterruptedException {
        // stops all motion

        frontLeft.setPower(0.0);
        backLeft.setPower(0.0);
        frontRight.setPower(0.0);
        backRight.setPower(0.0);

    }//end StopMotion Method
    public void intake (double power, long totalSeconds, int Direction) throws InterruptedException {
       // intake.setPower(0);
    }
  /*
    public void moveLift(double power, long totalSeconds, int Direction) throws InterruptedException{
        slidesLeft.setPower(power * Direction);
        slidesRight.setPower(power * Direction);
        Thread.sleep(totalSeconds);

        //lift.setPower(0);
    }//end moveLift Method
*/

/*
    public void moveClaw(double power) throws InterruptedException{
        claw.setPosition(power);
        //Thread.sleep(totalSeconds);

        //slidesLeft.setPower(0);
        //slidesRight.setPower(0);

    }
*/

}