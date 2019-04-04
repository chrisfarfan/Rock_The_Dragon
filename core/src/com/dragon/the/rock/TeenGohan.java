package com.dragon.the.rock;

import com.badlogic.gdx.graphics.g2d.Animation;

/**
 * Created by markapptist on 2018-11-12.
 */

public class TeenGohan extends ZFighter {

    TeenGohan() {

        String[] idleString = {"sprites/teengohan/idle/0.png", "sprites/teengohan/idle/1.png",
                "sprites/teengohan/idle/2.png", "sprites/teengohan/idle/3.png", "sprites/teengohan/idle/4.png"};

        idle = loadAnimationFromFiles(idleString, 0.5f, true);

        String[] startRunString = {"sprites/teengohan/run/0.png", "sprites/teengohan/run/1.png"};

        startRun = loadAnimationFromFiles(startRunString, 2f, false);

        String[] runString = {"sprites/teengohan/run/1.png"};

        run = loadAnimationFromFiles(runString, 0.5f, true);

        String[] endRunString = {"sprites/teengohan/run/1.png","sprites/teengohan/run/2.png"};

        endRun = loadAnimationFromFiles(endRunString, 2f, false);

        String[] walkString = {"sprites/teengohan/walk/0.png", "sprites/teengohan/walk/1.png", "sprites/teengohan/walk/2.png"
                , "sprites/teengohan/walk/3.png", "sprites/teengohan/walk/4.png", "sprites/teengohan/walk/5.png"};

        walk = loadAnimationFromFiles(walkString, 0.2f, true);

        String[] reverseWalkString = {"sprites/teengohan/walk/5.png", "sprites/teengohan/walk/4.png", "sprites/teengohan/walk/3.png"
                , "sprites/teengohan/walk/2.png", "sprites/teengohan/walk/1.png", "sprites/teengohan/walk/0.png"};

        reverseWalk = loadAnimationFromFiles(reverseWalkString, 0.2f, true);

        String[] string1String = {"sprites/teengohan/string1/0.png", "sprites/teengohan/string1/1.png", "sprites/teengohan/string1/2.png"
                , "sprites/teengohan/string1/3.png", "sprites/teengohan/string1/4.png"};

        String[] string2String = {"sprites/teengohan/string2/0.png", "sprites/teengohan/string2/1.png", "sprites/teengohan/string2/2.png"
                , "sprites/teengohan/string2/3.png", "sprites/teengohan/string2/4.png", "sprites/teengohan/string2/5.png"};

        String[] string3String = {"sprites/teengohan/string3/0.png", "sprites/teengohan/string3/1.png", "sprites/teengohan/string3/2.png"
                , "sprites/teengohan/string3/3.png"};

        String[] string4String = {"sprites/teengohan/string4/0.png", "sprites/teengohan/string4/1.png", "sprites/teengohan/string4/2.png"
                , "sprites/teengohan/string4/3.png", "sprites/teengohan/string4/4.png"};

        String[] string5String = {"sprites/teengohan/string5/0.png", "sprites/teengohan/string5/1.png", "sprites/teengohan/string5/2.png"};

        String[][] comboString = {string1String,string2String,string3String,string4String,string5String};

        string = new Animation[5];

        for (int i = 0; i < 5; i++){
            string[i] = loadAnimationFromFiles(comboString[i], 0.1f, false);
        }

        this.setBoundaryRectangle();

        setScale(2.0f);

        setMaxSpeed(900);

    }

    @Override
    public void act(float dt) {
        super.act(dt);

        setAcceleration(900);
    //    accelerateAtAngle(270);
     //   applyPhysics(dt);
    }

}
