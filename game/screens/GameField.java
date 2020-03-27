package com.bulbels.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.bulbels.game.Bulbels;
import com.bulbels.game.balls.Ball;

public class GameField implements Screen {

	Ball ball;
	Bulbels game;
	OrthographicCamera camera;

	Vector3 touchPos;
	Sprite bad;
	public Rectangle rectangle;
	public GameField(Bulbels game) {
		this.game=game;
		ball = new Ball();
		ball.setY(0);
		ball.setX(Gdx.graphics.getWidth()/2);
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

		touchPos = new Vector3();

		bad = new Sprite(new Texture("badlogic.jpg"));
		rectangle = new Rectangle(30,40,50,100);
		bad.setBounds(30,40,50,100);
	}

	@Override
	public void render (float delta) {
		Gdx.gl.glClearColor(.17f, .17f, .17f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camera.update();
		game.batch.setProjectionMatrix(camera.combined);
		game.batch.begin();
		game.batch.draw(bad,bad.getX(), bad.getY());
		game.batch.draw(ball.spriteBall, ball.centerX(), ball.centerY());
		if (ball.inMotion())ball.move();


		game.batch.end();


		if (Gdx.input.isTouched()){
			touchPos.set(Gdx.input.getX(),Gdx.input.getY(),0);
			camera.unproject(touchPos);
//			if (!ball.inMotion())ball.launch(touchPos.x,touchPos.y);
			ball.setX(touchPos.x);
			ball.setY(touchPos.y);
			ball.checkCollision(rectangle);
		}
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void show() {

	}

	@Override
	public void dispose () {

		ball.dispose();
	}
}
