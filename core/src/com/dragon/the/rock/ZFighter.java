package com.dragon.the.rock;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by markapptist on 2018-11-12.
 */

public class ZFighter extends ActorBeta {

    Animation<TextureRegion> idle;
    Animation<TextureRegion> run;
    Animation<TextureRegion> startRun;
    Animation<TextureRegion> endRun;
    Animation<TextureRegion> walk;
    Animation<TextureRegion> reverseWalk;
    Animation<TextureRegion> string[];

    boolean hit = false;


    ZFighter() {

    }

    @Override
    public void act(float dt) {
        super.act(dt);

     //   setAcceleration(900);
     //   accelerateAtAngle(270);
      //  applyPhysics(dt);
    }
}
