package com.dragon.the.rock;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.utils.Align;

/**
 * Created by markapptist on 2018-11-12.
 */

public class MenuScreen extends ScreenBeta {

    TextButton startButton;
    TextButton helpButton;
    TextButton exitButton;

    ActorBeta logo;

    Label label;

    /**PARTICLE EFFECTS**/
    FireParticle fire;

    ActorBeta background;

    @Override
    public void initialize() {

        background = new ActorBeta(0, 0, mainStage);
        background.loadTexture("sprites/backgrounds/menubackground.jpg");
        background.setScale(1.0f);

        logo = new ActorBeta(WIDTH / 3, HEIGHT / 2, mainStage);
        logo.loadTexture("sprites/splashscreen/DBZlogo.png");
        logo.setScale(2.0f);


//        uiTable.background(skin.getDrawable("window-c"));

        uiStage.addActor(tableContainer);

        startButton = new TextButton("Start", skin.get(("default"), TextButton.TextButtonStyle.class));
        startButton.setOrigin(Align.center);
        startButton.setTransform(true);
        startButton.setScale(1);

        helpButton = new TextButton("Help", skin.get(("default"), TextButton.TextButtonStyle.class));
        helpButton.setOrigin(Align.center);
        helpButton.setTransform(true);
        helpButton.setScale(1);

        exitButton = new TextButton("Exit", skin.get(("default"), TextButton.TextButtonStyle.class));
        exitButton.setOrigin(Align.center);
        exitButton.setTransform(true);
        exitButton.setScale(1);

        setUpButtons();

        label = new Label("LABEL", labelStyle);

        //Add to TABLE

        uiTable.row().padTop(HEIGHT /20).padBottom(HEIGHT / 20);
        uiTable.add(startButton).size(startButton.getWidth(), startButton.getHeight()).expandX();

        uiTable.row().padTop(HEIGHT /20).padBottom(HEIGHT / 20);
        uiTable.add(helpButton).size(helpButton.getWidth(), helpButton.getHeight()).expandX();

        uiTable.row().padTop(HEIGHT / 20).padBottom(HEIGHT / 20);
        uiTable.add(exitButton).size(exitButton.getWidth(), exitButton.getHeight()).expandX();



        /**PARTICLE EFFECTS**/
        fire = new FireParticle();
        fire.centerAtActor(helpButton);
        fire.start();
        fire.setPosition(WIDTH / 2, HEIGHT / 2);
        fire.setScale(16.0f);
        

        mainStage.addActor(fire);
    }

    public void setUpButtons() {

        startButton.addListener(new ActorGestureListener() {
            @Override
            public void touchDown(InputEvent event, float x, float y, int pointer, int button) {
                super.touchDown(event, x, y, pointer, button);
                SplashScreen.defaultBackgroundMusic.stop();
                if(RockTheDragon.storyScreen == null) {
                    RockTheDragon.storyScreen = new StoryScreen();
                    RockTheDragon.setActiveScreen(RockTheDragon.storyScreen);
                }else{
                    RockTheDragon.storyScreen.dispose();
                    RockTheDragon.storyScreen = new StoryScreen();
                    RockTheDragon.setActiveScreen(RockTheDragon.storyScreen);
                }
            }
        });

        helpButton.addListener(new ActorGestureListener() {
            @Override
            public void touchDown(InputEvent event, float x, float y, int pointer, int button) {
                super.touchDown(event, x, y, pointer, button);


            }
        });

        exitButton.addListener(new ActorGestureListener() {
            @Override
            public void touchDown(InputEvent event, float x, float y, int pointer, int button) {
                super.touchDown(event, x, y, pointer, button);
                Gdx.app.exit();

            }
        });
    }

    @Override
    public void render(float delta) {super.render(delta);}

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void hide() {
        super.hide();
    }

    @Override
    public void update(float dt) {fire.act(dt);}
}
