package com.dragon.the.rock;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;

import java.awt.Color;

/**
 * Created by markapptist on 2018-11-12.
 */

public class GameScreen extends ScreenBeta {

    Cell cell;
    TeenGohan teenGohan;

    Touchpad touchpad;

    Skin skin;
    Skin uiSkin;
    Skin comicSkin;
    Skin comic2Skin;

    ActorBeta foreground;
    ActorBeta background;

    ProgressBar playerHealth;
    ProgressBar enemyHealth;

    float gameClock;
    float aiTime;
    Label timer;
    Label winMessage;

    int comboCounter;
    int cellComboCounter;

    boolean buttonPressed;
    boolean isRunning;
    float lastXTouchPad;
    float lastYTouchPad;

    private TextButton restartButton;
    private TextButton exitButton;
    private SplashScreen splashScreen;

    @Override
    public void initialize() {

        ActorBeta.setWorldBounds(WIDTH, HEIGHT);

        foreground = new ActorBeta(0, 0, mainStage);
        foreground.loadTexture("sprites/backgrounds/cellgames.jpg");
        foreground.setSize(WIDTH, HEIGHT);

//        background = new ActorBeta(900, 300, mainStage);
//        background.loadTexture("sprites/backgrounds/background0_20.png");
//        background.setScale(2.0f);

        skin = new Skin(Gdx.files.internal("skins/pixthulhu/skin/pixthulhu-ui.json"));
        uiSkin = new Skin(Gdx.files.internal("skins/arcade/skin/arcade-ui.json"));
        comicSkin = new Skin(Gdx.files.internal("skins/comic/skin/comic-ui.json"));
        comic2Skin = new Skin(Gdx.files.internal("skins/comic2/skin/comic-ui.json"));


        defaultSoundEffect = Gdx.audio.newSound(Gdx.files.internal("sounds/hit.wav"));

        gameClock = 90;
        String timerText = String.format("%.2f",gameClock);
        timer = new Label(timerText,comicSkin);
        timer.setPosition(WIDTH * 0.52f,HEIGHT * 0.92f,Align.center);
        timer.setFontScale(4);
        uiStage.addActor(timer);

        winMessage = new Label(timerText,comicSkin);
        winMessage.setPosition(Gdx.graphics.getWidth() / 2,Gdx.graphics.getHeight() / 1.5f, Align.center);
        winMessage.setFontScale(4);
        winMessage.setVisible(false);
        uiStage.addActor(winMessage);

        uiStage.addActor(tableContainer);
        tableContainer.setPosition((WIDTH - WIDTH*0.5f) - (tableContainer.getWidth() / 2), (HEIGHT - HEIGHT*1.0f) / 2.0f);
        //Touchpad
        touchpad = new Touchpad(40.0f, skin, "default");
        touchpad.setPosition(WIDTH / 5, HEIGHT / 3);
        touchpad.setResetOnTouchUp(true);
        touchpad.getColor().a = 1.0f;

        playerHealth = new ProgressBar(0,100,1,false,comic2Skin);
        playerHealth.setPosition(WIDTH * 0.1f,HEIGHT * 0.9f);
        playerHealth.setSize(WIDTH * 0.35f,playerHealth.getPrefHeight());
        playerHealth.setValue(100);
        uiStage.addActor(playerHealth);

        enemyHealth = new ProgressBar(0,100,1,false,comicSkin);
        enemyHealth.setPosition(WIDTH * 0.6f,HEIGHT * 0.9f);
        enemyHealth.setSize(WIDTH * 0.35f,enemyHealth.getPrefHeight());
        enemyHealth.setValue(100);
        uiStage.addActor(enemyHealth);

        comboCounter=0;
        cellComboCounter=0;
        buttonPressed = false;

        restartButton = new TextButton("Restart Fight", skin.get(("default"), TextButton.TextButtonStyle.class));
        restartButton.setOrigin(Align.center);
        restartButton.setPosition(Gdx.graphics.getWidth() / 2,Gdx.graphics.getHeight() / 2, Align.center);
        restartButton.setTransform(true);
        restartButton.setScale(1);
        restartButton.setVisible(false);
        uiStage.addActor(restartButton);

        exitButton = new TextButton("Back To Menu", skin.get(("default"), TextButton.TextButtonStyle.class));
        exitButton.setOrigin(Align.center);
        exitButton.setPosition(Gdx.graphics.getWidth() / 2,Gdx.graphics.getHeight() / 2.5f, Align.center);
        exitButton.setTransform(true);
        exitButton.setScale(1);
        exitButton.setVisible(false);
        uiStage.addActor(exitButton);

        setUpButtons();




        //CREATE Cell
        cell = new Cell();
        cell.setPosition(WIDTH - cell.getWidth() , HEIGHT / 3);
        mainStage.addActor(cell);

        //Create Teen Gohan
        teenGohan = new TeenGohan();
        teenGohan.setPosition(teenGohan.getWidth(),HEIGHT/3);
        mainStage.addActor(teenGohan);

    }

    @Override
    public void update(float dt) {
        cell.act(dt);
        teenGohan.act(dt);
        touchpad.act(dt);
        gameClock -= Gdx.graphics.getDeltaTime();
        String timerText = String.format("%.0f",gameClock);
        timer.setText(timerText);
        aiTime += Gdx.graphics.getDeltaTime();

        if(touchpad.getKnobPercentX() > 0.5 && touchpad.getKnobPercentX() < 0.9) { //Forward Walk
            //Gdx.app.log("Delta X", "Knob X is " + touchpad.getKnobPercentX());
            if(isRunning && lastXTouchPad > 0.9) {
                teenGohan.setAnimation(teenGohan.endRun);
            }else if (!isRunning)
                teenGohan.setAnimation(teenGohan.walk);
            teenGohan.moveBy(3,0);
        }else if(touchpad.getKnobPercentX() > 0.9) { //Forward Run
            //Gdx.app.log("Delta X", "Knob X is " + touchpad.getKnobPercentX());
            if (!isRunning && lastXTouchPad < 0.9) {
                Gdx.app.log("Test", "Should see this once");
                teenGohan.setAnimation(teenGohan.startRun);
            }else if (isRunning)
                teenGohan.setAnimation(teenGohan.run);
            teenGohan.moveBy(7,0);
        }else if(touchpad.getKnobPercentX() < -0.5 && touchpad.getKnobPercentX() > -0.9) {//Reverse Walk
            //Gdx.app.log("Delta X", "Knob X is " + touchpad.getKnobPercentX());
            if(isRunning && lastXTouchPad < -0.9) {
                teenGohan.setAnimation(teenGohan.endRun);
            }else if (!isRunning)
                teenGohan.setAnimation(teenGohan.reverseWalk);
            teenGohan.moveBy(-3,0);
        }else if(touchpad.getKnobPercentX() < -0.9) { // Reverse Run
           // Gdx.app.log("Delta X", "Knob X is " + touchpad.getKnobPercentX());
            if(!isRunning && lastXTouchPad > -0.9) {
                teenGohan.setAnimation(teenGohan.startRun);
            }else if(isRunning)
                teenGohan.setAnimation(teenGohan.run);
            teenGohan.moveBy(-7,0);
        }else if (!buttonPressed)
            teenGohan.setAnimation(teenGohan.idle);

        if(touchpad.getKnobPercentY() > 0.5 && touchpad.getKnobPercentY() < 0.9) {
          //  Gdx.app.log("Delta Y", "Knob Y is " + touchpad.getKnobPercentX());
        }

        if(touchpad.getKnobPercentY() > 0.9) {
           // Gdx.app.log("Delta Y", "Knob Y is " + touchpad.getKnobPercentY());

        }

        if (enemyHealth.getValue() <= 0 || playerHealth.getValue() <= 0 || gameClock <= 0) {
            gameOver();
        }

        if (teenGohan.isAnimationFinished()) {
            buttonPressed = false;
        }

        lastXTouchPad = touchpad.getKnobPercentX();
        lastYTouchPad = touchpad.getKnobPercentY();

        if(!GameScreen.super.isPaused)
            enemyAI();


        cell.boundToWorld();
        teenGohan.boundToWorld();
    }

    public void setUpButtons()
    {
        uiTable.add(touchpad).width(touchpad.getWidth() * 1.5f).height(touchpad.getHeight() * 1.5f).padRight(800).padTop(600);

        Button aButton = new Button(uiSkin, "red");
        Button bButton = new Button(uiSkin, "blue");
        aButton.getColor().a = 1.0f;
        bButton.getColor().a = 1.0f;



       // uiTable.padRight(50).add(aButton).width(aButton.getWidth() * 2.0f).height(aButton.getHeight() * 2.0f).bottom().padRight(120);
        uiTable.add(bButton).width(bButton.getWidth() * 2.0f).height(bButton.getHeight() * 2.0f).bottom().padBottom(120);

        touchpad.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                float deltaX = ((Touchpad) actor).getKnobPercentX();
                float deltaY = ((Touchpad) actor).getKnobPercentY();

                Gdx.app.log("Delta X", "" + deltaX);
                Gdx.app.log("Delta Y", "" + deltaY);
            }
        });

        /*aButton.addListener(new ActorGestureListener() {
            @Override
            public void touchDown(InputEvent event, float x, float y, int pointer, int button) {
                super.touchDown(event, x, y, pointer, button);
                //playerHealth.setValue(playerHealth.getValue() - 10);

                cell.resetAnim();
                cell.setAnimation(cell.string[cellComboCounter]);
                cellComboCounter++;
                if (cellComboCounter >= 5)
                    cellComboCounter = 0;
                if (cell.overlaps(teenGohan)){
                    teenGohan.hit = true;
                    defaultSoundEffect.play();
                    playerHealth.setValue(playerHealth.getValue() - 10);
                }


            }
        });*/

        bButton.addListener(new ActorGestureListener() {
            @Override
            public void touchDown(InputEvent event, float x, float y, int pointer, int button) {
                super.touchDown(event, x, y, pointer, button);
               // if (!teenGohan.hit) {
                    buttonPressed = true;
                    teenGohan.resetAnim();
                    teenGohan.setAnimation(teenGohan.string[comboCounter]);
                    comboCounter++;
                    if (comboCounter >= 5)
                        comboCounter = 0;
                    if (teenGohan.overlaps(cell)){
                        cell.hit = true;
                        defaultSoundEffect.play();
                        enemyHealth.setValue(enemyHealth.getValue() - 10);
                    }

                }
            //}
        });


        restartButton.addListener(new ActorGestureListener()
        {
            @Override
            public void touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                super.touchDown(event, x, y, pointer, button);

                restartButton.setVisible(false);
                exitButton.setVisible(false);
                restartGame();
            }
        });

        exitButton.addListener(new ActorGestureListener()
        {
            @Override
            public void touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                super.touchDown(event, x, y, pointer, button);

                splashScreen = new SplashScreen();
                RockTheDragon.setActiveScreen(splashScreen);
            }
        });
    }

    private void restartGame(){
        StoryScreen.defaultBackgroundMusic.play();
        RockTheDragon.setActiveScreen(RockTheDragon.storyScreen);

    }

    private void gameOver(){
        if (enemyHealth.getValue() <= playerHealth.getValue())
        {
            //you win
            winMessage.setText("You Win!");
        }else{
            //you lose
            winMessage.setText("You Lose!");
        }

        winMessage.setVisible(true);
        restartButton.setVisible(true);
        exitButton.setVisible(true);
        StoryScreen.defaultBackgroundMusic.stop();
        GameScreen.super.isPaused = true;
    }
    //TO DO: proper AI
    private void enemyAI(){
        if(Math.abs(cell.getX() - teenGohan.getX()) > cell.getWidth() * 2){
            cell.setAnimation(cell.walk);
            cell.moveBy(-3,0);
            if(Math.abs(cell.getX() - teenGohan.getX()) < cell.getWidth() * 2){
                cell.setAnimation(cell.idle);
            }
        }else
        if(aiTime > 0.5f) {
            if (MathUtils.random(0,1)== 1 ){
            //if (!cell.hit) {
                cell.resetAnim();
                cell.setAnimation(cell.string[cellComboCounter]);
                cellComboCounter++;
                if (cellComboCounter >= 5)
                    cellComboCounter = 0;
                if (cell.overlaps(teenGohan)){
                    teenGohan.hit = true;
                    defaultSoundEffect.play();
                    playerHealth.setValue(playerHealth.getValue() - 10);
                }
           // }
            }else{
            cell.setAnimation(cell.idle);
            }
            aiTime = 0;
        }
    }



}
