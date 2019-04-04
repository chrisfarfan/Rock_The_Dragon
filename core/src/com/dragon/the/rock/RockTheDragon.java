package com.dragon.the.rock;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import javax.sound.midi.spi.SoundbankReader;

public class RockTheDragon extends GameBeta {
	static SplashScreen splashScreen;
	static MenuScreen menuScreen;
	static GameScreen gameScreen;
	static StoryScreen storyScreen;
	static Sound voiceLines[];

	boolean paused = false;

	@Override
	public void create() {

		super.create();

		voiceLines = new Sound[10];
		for (int i = 0; i < 10; i++){
			voiceLines[i] = Gdx.audio.newSound(Gdx.files.internal("sounds/cutscene/" + i + ".ogg"));
		}
		splashScreen = new SplashScreen();

		setScreen(splashScreen);
	}

}
