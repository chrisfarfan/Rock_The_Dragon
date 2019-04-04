package com.dragon.the.rock;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class SplashScreen extends ScreenBeta {

    Scene splash;

    ActorBeta logo;
    ActorBeta background;

    @Override
    public void initialize() {

        background = new ActorBeta(0, 0, mainStage);
        background.loadTexture("sprites/backgrounds/menubackground.jpg");
        background.setScale(1.0f);

        logo = new ActorBeta(WIDTH / 3, HEIGHT / 2, mainStage);
        logo.loadTexture("sprites/splashscreen/DBZlogo.png");
        logo.setScale(2.0f);
        logo.setOpacity(0);

        ActorBeta.setWorldBounds(WIDTH, HEIGHT);


        uiStage.addActor(uiTable);

        splash = new Scene();
        mainStage.addActor(splash);

        splash.addSegment(new SceneSegment(logo, Actions.fadeIn(4.8f)));
        //splash.addSegment(new SceneSegment(logo, SceneActions.moveToOutsideLeft(5)));



        defaultBackgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("music/DBZTheme.mp3"));
        defaultBackgroundMusic.play();
        defaultBackgroundMusic.setLooping(true);
        splash.start();

    }

    @Override
    public void update(float dt) {

        if(splash.isSceneFinished())
            RockTheDragon.setActiveScreen(new MenuScreen());

    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        super.touchDown(screenX, screenY, pointer, button);

        splash.loadNextSegment();

        return true;
    }
}
