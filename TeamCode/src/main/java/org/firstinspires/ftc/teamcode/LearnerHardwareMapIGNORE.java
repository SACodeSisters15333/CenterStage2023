package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
public class LearnerHardwareMapIGNORE {

    /**
     * Created by Destiny Hale 2022
     */

//Define elements of robot
    public class Hardware {

        //Wheels
        public DcMotor leftFront;
        public DcMotor leftBack;
        public DcMotor rightFront;
        public DcMotor rightBack;

        // Other Motors

        // Servos

        // Sensors



        //---------------------------------INIT--ROBOT---------------------------------------------------
   /*  This method allows us to initialize the motors and sensors only once.
       It is used in every other program after "Init" is pressed.
    */

        public void initializeRobot(HardwareMap hwMap) {

            HardwareMap HWMap = hwMap;

            //initialize Wheel motors

            leftFront = HWMap.dcMotor.get("leftFront");
            leftBack = HWMap.dcMotor.get("leftBack");
            rightFront = HWMap.dcMotor.get("rightFront");
            rightBack = HWMap.dcMotor.get("rightBack");

            //initialize Sensors




        }

//--------------------------Autonomous Driving Methods-------------------------------------------------------

        /*  Below are methods used to move Vector's wheels during autonomous
         */
//Drives Forwards and Backwards
        public void driveStraight(double power, int time) throws InterruptedException {
            //set wheels to a power for a set amount of seconds
            leftFront.setPower(-power);
            leftBack.setPower(-power);
            rightFront.setPower(power);
            rightBack.setPower(power);
            Thread.sleep(time);

            //then Stop
            leftFront.setPower(0.0);
            leftBack.setPower(0.0);
            rightFront.setPower(0.0);
            rightBack.setPower(0.0);

            leftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            rightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }

        public void turnLeftA(double power, int time) throws InterruptedException {
            //set all wheels to given power for a given amount of seconds
            rightFront.setPower(power);
            leftFront.setPower(power);
            leftBack.setPower(power);
            rightBack.setPower(power);
            Thread.sleep(time);
        }

        public void autoStrafe(double power, int time) throws InterruptedException {
            //set front wheels to a power and back to opposite of power for some time
            leftFront.setPower(power);
            leftBack.setPower(-power);
            rightFront.setPower(power);
            rightBack.setPower(-power);
            Thread.sleep(time);
        }

        //Used to completely stop wheels
        public void WaitA(int time) throws InterruptedException {
            leftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            rightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

            leftFront.setPower(0.0);
            leftBack.setPower(0.0);
            rightFront.setPower(0.0);
            rightBack.setPower(0.0);
            Thread.sleep(time);
        }
        //--------------------------Autonomous Apparatus Methods-------------------------------------------------------

        /*  Below are all of methods used to move non-wheel parts of Vector.
         */
    }

}
