package com.medical_store_management_system.GUI_Layer.Text_Animation;

/*
 *       This class will perform animation of the any text given to it and will do it in an infinite manner
 *     Infinite Manner is achieved by :
 *               When the string counter reaches at the end then change the string counter back to 0 making it an infinite looping
 *               through the string infinite many times.
 * */

import java.util.Random;

public class TextAnimator implements Runnable {

    private String text;
    private int animationTime;
    private TextOutput textOutput;
    private Random random = new Random();

    public TextAnimator(String text, int animationTime, TextOutput textField) {
        this.text = text;
        this.animationTime = animationTime;
        this.textOutput = textField;
    }


    //---------------------- Over riding the run method of the runnable interface to implement the mulit threading -------------------------------------------------------
    @Override
    public void run() {

        try {
            for (int i = 0; i <= text.length(); i++) {
                String textAtThisPoint = text.substring(0, i);

                textOutput.writeText(textAtThisPoint);
                Thread.sleep(animationTime + random.nextInt(150));

                if (i == text.length())
                    i = 0;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
