package com.dragon.the.rock;

import com.badlogic.gdx.graphics.g2d.Animation;

/**
 * Created by markapptist on 2018-11-12.
 */

public class Cell extends ZFighter {

    Cell() {

        String[] idleString = {"sprites/cell/idle/9.png", "sprites/cell/idle/10.png",
                "sprites/cell/idle/11.png", "sprites/cell/idle/12.png"};

        idle = loadAnimationFromFiles(idleString, 0.5f, true);

        String[] runString = {"sprites/cell/run/47.png", "sprites/cell/run/48.png"};

        run = loadAnimationFromFiles(runString, 0.5f, false);

        String[] walkString = {"sprites/cell/walk/17.png", "sprites/cell/walk/18.png", "sprites/cell/walk/19.png"
                , "sprites/cell/walk/20.png", "sprites/cell/walk/21.png", "sprites/cell/walk/22.png"
                , "sprites/cell/walk/23.png", "sprites/cell/walk/24.png", "sprites/cell/walk/25.png"
                , "sprites/cell/walk/26.png"};

        walk = loadAnimationFromFiles(walkString, 0.2f, true);

        String[] string1String = {"sprites/cell/string1/0.png", "sprites/cell/string1/1.png", "sprites/cell/string1/2.png"
                , "sprites/cell/string1/3.png"};

        String[] string2String = {"sprites/cell/string2/0.png", "sprites/cell/string2/1.png", "sprites/cell/string2/2.png"
                , "sprites/cell/string2/3.png", "sprites/cell/string2/4.png", "sprites/cell/string2/5.png"};

        String[] string3String = {"sprites/cell/string3/0.png", "sprites/cell/string3/1.png", "sprites/cell/string3/2.png"
                , "sprites/cell/string3/3.png", "sprites/cell/string3/4.png", "sprites/cell/string3/5.png"};

        String[] string4String = {"sprites/cell/string4/0.png", "sprites/cell/string4/1.png", "sprites/cell/string4/2.png"
                , "sprites/cell/string4/3.png", "sprites/cell/string4/4.png", "sprites/cell/string4/5.png"};

        String[] string5String = {"sprites/cell/string5/0.png", "sprites/cell/string5/1.png", "sprites/cell/string5/2.png", "sprites/cell/string5/3.png", "sprites/cell/string5/4.png"};

        String[][] comboString = {string1String,string2String,string3String,string4String,string5String};

        this.setBoundaryRectangle();

        string = new Animation[5];

        for (int i = 0; i < 5; i++){
            string[i] = loadAnimationFromFiles(comboString[i], 0.1f, false);
        }

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
