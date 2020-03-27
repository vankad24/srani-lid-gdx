package com.bulbels.game.balls;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Shape2D;
import com.badlogic.gdx.math.Vector2;

public class Ball {
    static float startX;
    float velocityX,velocityY;
    float maxVelocity = 30;
    boolean motion;
    Vector2 center;

    Rectangle rectBall;
    public Sprite spriteBall;
    public Circle ballCircle;
    int n = 30;
    public Ball() {
        rectBall = new Rectangle();
        rectBall.height=n;
        rectBall.width=n;
        rectBall.x= Gdx.graphics.getWidth()/2-n/2;
        rectBall.y= 20;

        spriteBall = new Sprite(new Texture("ball.png"));
        center = new Vector2(rectBall.x+n/2,rectBall.y+n/2);
        ballCircle= new Circle(center,n/2);
    }

//    public Texture getTexture() {
//        return texture;
//    }

    public float centerX(){return rectBall.x-n/2;}
    public float centerY(){return rectBall.y-n/2;}
    public float getX(){return rectBall.x;}
    public float getY(){return rectBall.y;}
    public void setX(float x){rectBall.x=x;}
    public void setY(float y){rectBall.y=y;}
    public boolean inMotion(){
        return motion;
    }

    public void move(){
        rectBall.x+=velocityX;
        rectBall.y+=velocityY;
        if (getX()+n>= Gdx.graphics.getWidth()|| getX()<=0){
            velocityX*=-1;
            setX(getX()<=Gdx.graphics.getWidth()/2?0:Gdx.graphics.getWidth()-n);
        }

        if ( getY()+n>=Gdx.graphics.getHeight()){
            velocityY*=-1;
            setY(Gdx.graphics.getHeight()-n);
        }
        if (getY()<=0) {
            startX = getX();
            setY(0);
            motion = false;
        }

//        old_x=x;
//        old_y=y;
    }
    public void checkCollision(Rectangle rectangle){
        if (Intersector.overlaps(ballCircle, rectangle)) System.out.println("Collision!!!");
    }

    public void normalizeVelocity(float x,float y){
        float width = x-rectBall.x;
        float height = y-rectBall.y;
        float index =(float)(maxVelocity/Math.hypot(width,height));
        velocityX = width*index;
        velocityY = height*index;
    }

    public void launch(float x,float y){
        normalizeVelocity(x, y);
        motion=true;
        //Log.d("myteg","x:"+velocityX+" y:"+velocityY);

    }
    public void dispose () {
        spriteBall.getTexture().dispose();

    }
}
