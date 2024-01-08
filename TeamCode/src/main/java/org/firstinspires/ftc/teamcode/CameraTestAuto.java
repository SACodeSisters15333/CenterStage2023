package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.processors.TestingItemRecognitionProcessor;
import org.firstinspires.ftc.vision.VisionPortal;

//from Listing 16.7 FirstVisionOpmode found in Alan Smith's Learning JAVA for FTC pg. 135
//use concept of state machines chapter 12 of Smith's book
//does not use Linear OpMode -- hence no use of wait for start or for runOpMode

@Disabled
@Autonomous (name = "CameraTestAUTO")

public class CameraTestAuto extends OpMode {

    HardwareMapCenterStage15333 Genevieve = new HardwareMapCenterStage15333();

    //setting up camera and reading Object placement
    private TestingItemRecognitionProcessor visionProcessor;
    private VisionPortal visionPortal;

    @Override
    public void init() {
        visionProcessor = new TestingItemRecognitionProcessor();
        visionPortal = VisionPortal.easyCreateWithDefaults(
                hardwareMap.get(WebcamName.class, "Webcam 1"), visionProcessor);
    } //ends void initialize vision processor

    @Override
    public void init_loop() {
    }//ends void initial loop

    @Override
    public void start() {
        visionPortal.stopStreaming();
    } //ends void start

    @Override
    public void loop() {
        telemetry.addData("Identified", visionProcessor.getSelection());
        /*
            switch (visionProcessor.getSelection()) {

                case LEFT:
                    //Place purple pixel on tape line with white pixel
                    //tape line is on is vertical and on the left
                   try {
                        //put in movement code
                        Genevieve.Sideways(.6, 1000, -1); //left
                        //Thread.sleep(500);
                        //Bass_Pro_Shop.DriveStraight(.6, 950, -1);
                        //Thread.sleep(500);
                        //Wall_E.CenterSpin(0.5, 250, 1);
                    }
                    catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    break; //end case LEFT and stops loop

                case MIDDLE:

                    //Place purple pixel on tape line with white pixel
                    //tape line is on is horizontal and directly forward

                    try {
                        //put in movement code
                        Genevieve.Straight(.5,1000,1); //forward
                    }
                    catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    break; //ends case MIDDLE and stops loop

                case RIGHT:

                    //Place purple pixel on tape line with white pixel
                    //tape line is on is vertical and on the right

                    try {
                        //put in movement code
                        Genevieve.Sideways(.6, 100, -1); //right
                    }
                    catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    break; //ends case RIGHT and stops loop

                case NONE:
                    break; //ends case NONE and stops loop

                default:
                    telemetry.addData("Auto", "Finished");
                    //indicates no cases found

            } //ends Switch selection

        } //ends void loop
        */
    }
}