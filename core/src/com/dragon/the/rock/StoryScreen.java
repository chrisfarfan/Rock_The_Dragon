package com.dragon.the.rock;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

/**
 * Created by markapptist on 2018-11-16.
 */

public class StoryScreen extends ScreenBeta {

    Scene scene;
    ActorBeta characterPortrait;

    ActorBeta cellCP;
    ActorBeta cellWorriedCP;

    ActorBeta teenGohanCP;
    ActorBeta ssj2GohanCP;

    ActorBeta ranger;
    ActorBeta cell;
    ActorBeta teenGohan;


    int voiceCounter;
    boolean hasTransformed = false;

    public void initialize() {
        ActorBeta whiteBackground = new ActorBeta(0,0,mainStage);
        whiteBackground.loadTexture("sprites/backgrounds/cellgamesWhite.jpg");
        whiteBackground.setSize(WIDTH, HEIGHT);
        whiteBackground.setOpacity(0);

        ActorBeta background = new ActorBeta(0, 0, mainStage);
        background.loadTexture("sprites/backgrounds/cellgames.jpg");
        background.setSize(WIDTH, HEIGHT);
        background.setOpacity(0);

        ActorBeta.setWorldBounds(WIDTH, HEIGHT);

        DialogBox dialogBox = new DialogBox(0,0, uiStage);
        dialogBox.setDialogSize(600, 280);
        dialogBox.setBackgroundColor( new Color(0.6f, 0.6f, 0.8f, 1) );
        dialogBox.setFontScale(3.0f);
        dialogBox.setVisible(false);


        voiceCounter = 0;



        uiTable.add(dialogBox).expandX().expandY().bottom();


        uiStage.addActor(uiTable);

        ranger = new ActorBeta(0, 0, mainStage);
        ranger.loadTexture("sprites/CP/35.png");
        ranger.setScale(3.0f);
        ranger.setVisible(true);

        cell = new ActorBeta(0, 0, mainStage);
        cell.loadTexture("sprites/CP/0.png");
        cell.setScale(3.0f);
        cell.setVisible(false);

        teenGohan = new ActorBeta(0, 0, mainStage);
        teenGohan.loadTexture("sprites/CP/25.png");
        teenGohan.setScale(3.0f);
        teenGohan.setVisible(false);

        characterPortrait = new ActorBeta(0,0,uiStage);
        characterPortrait.loadTexture("sprites/CP/A16HDEB.png");
        characterPortrait.setVisible(false);
        mainStage.addActor(characterPortrait);
        characterPortrait.setPosition( dialogBox.getWidth() - characterPortrait.getWidth(), 0 );

        cellCP = new ActorBeta(0,0,uiStage);
        cellCP.loadTexture("sprites/CP/1B.png");
        cellCP.setVisible(false);
        mainStage.addActor(cellCP);
        cellCP.setPosition( dialogBox.getWidth() - characterPortrait.getWidth(), 0 );

        cellWorriedCP = new ActorBeta(0,0,uiStage);
        cellWorriedCP.loadTexture("sprites/CP/2B.png");
        cellWorriedCP.setVisible(false);
        mainStage.addActor(cellCP);
        cellWorriedCP.setPosition( dialogBox.getWidth() - characterPortrait.getWidth(), 0 );


        teenGohanCP = new ActorBeta(0,0,uiStage);
        teenGohanCP.loadTexture("sprites/CP/ssjgohanHDB.png");
        teenGohanCP.setVisible(false);
        teenGohanCP.setPosition(dialogBox.getWidth() - characterPortrait.getWidth(), 0 );
        mainStage.addActor(teenGohanCP);

        ssj2GohanCP = new ActorBeta(0,0,uiStage);
        ssj2GohanCP.loadTexture("sprites/teengohan/4.png");
        ssj2GohanCP.setVisible(false);
        ssj2GohanCP.setPosition(dialogBox.getWidth() - characterPortrait.getWidth(), 0 );
        mainStage.addActor(ssj2GohanCP);



        scene = new Scene();
        mainStage.addActor(scene);

        scene.addSegment( new SceneSegment( whiteBackground, Actions.show()));
        scene.addSegment( new SceneSegment( background, Actions.fadeIn(1) ));
        scene.addSegment( new SceneSegment( ranger, SceneActions.moveToScreenCenterLeft(2) ));
        scene.addSegment( new SceneSegment( background, SceneActions.pause() ));

        scene.addSegment( new SceneSegment( dialogBox, Actions.show() ));
        scene.addSegment( new SceneSegment( dialogBox,
                SceneActions.setText("It is not a crime to fight for a just cause." ) ));

        scene.addSegment( new SceneSegment( characterPortrait, Actions.show() ));
        scene.addSegment( new SceneSegment( background, SceneActions.pause() ));
        scene.addSegment( new SceneSegment( dialogBox,
                SceneActions.setText("There are those who simply cannot be reasoned with." ) ));

        scene.addSegment( new SceneSegment( background, SceneActions.pause() ));
        scene.addSegment( new SceneSegment( dialogBox,
                SceneActions.setText("Let the anger built up inside you flow freely." ) ));

        scene.addSegment( new SceneSegment( background, SceneActions.pause() ));
        scene.addSegment( new SceneSegment( dialogBox,
                SceneActions.setText("There is no need to hold it back any longer." ) ));

        scene.addSegment( new SceneSegment( background, SceneActions.pause() ));
        scene.addSegment( new SceneSegment( dialogBox,
                SceneActions.setText("Please protect the plants, the animals..." ) ));

        scene.addSegment( new SceneSegment( background, SceneActions.pause() ));
        scene.addSegment( new SceneSegment( dialogBox,
                SceneActions.setText("Protect this world I loved." ) ));

        scene.addSegment( new SceneSegment( background, SceneActions.pause() ));
        scene.addSegment( new SceneSegment( dialogBox,
                SceneActions.setText("*SMASH*" ) ));
        scene.addSegment(new SceneSegment(ranger, Actions.fadeOut(0.3f)));
        scene.addSegment( new SceneSegment( cell, SceneActions.moveToScreenCenterRight(0.1f) ));

       // scene.addSegment( new SceneSegment( background, SceneActions.pause() ));
        scene.addSegment(new SceneSegment(cell,Actions.show()));
        scene.addSegment(new SceneSegment(cell,Actions.fadeIn(0.1f)));
        scene.addSegment(new SceneSegment(characterPortrait, Actions.hide()));
        scene.addSegment(new SceneSegment(cellCP,Actions.show()));
        scene.addSegment( new SceneSegment( dialogBox,
                SceneActions.setText("You've prattled on long enough, you miserable failure." ) ));

        scene.addSegment( new SceneSegment( background, SceneActions.pause() ));
        scene.addSegment(new SceneSegment(cellCP,Actions.hide()));
        scene.addSegment(new SceneSegment(teenGohanCP,Actions.show()));
        scene.addSegment( new SceneSegment( dialogBox,
                SceneActions.setText("....!" ) ));
        scene.addSegment( new SceneSegment( teenGohan, SceneActions.moveToScreenCenterLeft(0.1f) ));

        scene.addSegment( new SceneSegment( background, SceneActions.pause() ));
        scene.addSegment(new SceneSegment(teenGohanCP,Actions.hide()));
        scene.addSegment(new SceneSegment(ssj2GohanCP,Actions.show()));
        scene.addSegment(new SceneSegment(cell,Actions.hide()));
        scene.addSegment(new SceneSegment(teenGohan,Actions.show()));
        scene.addSegment( new SceneSegment( dialogBox,
                SceneActions.setText("AAAAAAAAAAAAAAARGH!!" ) ));
        scene.addSegment( new SceneSegment( background, Actions.fadeOut(0.1f) ));
        scene.addSegment( new SceneSegment( whiteBackground, Actions.fadeIn(0.1f) ));
        scene.addSegment( new SceneSegment( whiteBackground, Actions.fadeOut(0.1f) ));
        scene.addSegment( new SceneSegment( background, Actions.fadeIn(0.1f) ));
        scene.addSegment( new SceneSegment( background, Actions.fadeOut(0.1f) ));
        scene.addSegment( new SceneSegment( whiteBackground, Actions.fadeIn(0.1f) ));
        scene.addSegment( new SceneSegment( whiteBackground, Actions.fadeOut(0.1f) ));
        scene.addSegment( new SceneSegment( background, Actions.fadeIn(0.1f) ));




        scene.addSegment( new SceneSegment( background, SceneActions.pause() ));
        scene.addSegment( new SceneSegment( dialogBox,
                SceneActions.setText("He's...changing..." ) ));
        scene.addSegment(new SceneSegment(ssj2GohanCP,Actions.hide()));
        scene.addSegment(new SceneSegment(cellWorriedCP,Actions.show()));

        scene.addSegment( new SceneSegment( background, SceneActions.pause() ));
        scene.addSegment( new SceneSegment( cellWorriedCP, Actions.hide() ));
        scene.addSegment( new SceneSegment( dialogBox, Actions.hide() ) );
        scene.addSegment( new SceneSegment( ranger, SceneActions.moveToOutsideRight(1) ));
        scene.addSegment( new SceneSegment( background, Actions.fadeOut(1) ));

        defaultBackgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("music/CellTheme.mp3"));
        defaultBackgroundMusic.play();
        defaultBackgroundMusic.setLooping(true);

        scene.start();

    }

    public void update(float dt)
    {
        if ( scene.isSceneFinished() )
            RockTheDragon.setActiveScreen( new GameScreen() );
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        super.touchDown(screenX, screenY, pointer, button);

        scene.loadNextSegment();
        if(voiceCounter <10) {
            if (voiceCounter != 0)
                RockTheDragon.voiceLines[voiceCounter-1].stop();
            if(voiceCounter != 7) {
                RockTheDragon.voiceLines[voiceCounter].play(1.0f);
                voiceCounter++;
            }else if (hasTransformed) {
                RockTheDragon.voiceLines[voiceCounter].play(1.0f);
                voiceCounter++;
            }else
                hasTransformed = true;
        }


        return true;
    }
}
