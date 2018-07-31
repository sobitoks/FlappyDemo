package com.sobitoks.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Bird {
    private static final int GRAVITY = -15;
    private static final int MOVEMENT = 100;

    private Vector3 position;
    private Vector3 velocity;
    private Rectangle bounds;
    private Texture birdAnimationTexture;
    private Animation birdAnimation;

    public Bird(int x, int y) {
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);
        birdAnimationTexture = new Texture("birdanimation.png");
        birdAnimation = new Animation(new TextureRegion(birdAnimationTexture), 3, 0.5f);
        bounds = new Rectangle(x, y, birdAnimationTexture.getWidth() / 3, birdAnimationTexture.getHeight());
    }

    public void update(float dt) {

        birdAnimation.update(dt);

        //check if it is overground and add gravity
        if (position.y > 0) {
            velocity.add(0, GRAVITY, 0);
        }

        velocity.scl(dt);
        //add movement
        position.add(MOVEMENT * dt, velocity.y, 0);
        velocity.scl(1/dt);

        //check if it is ground lvl and don't let it go deeper
        if (position.y <= 0) {
            position.y = 0;
        }

        bounds.setPosition(position.x, position.y);
    }

    public Vector3 getPosition() {
        return position;
    }

    public TextureRegion getTexture() {
        return birdAnimation.getFrame();
    }

    public void jump() {
        velocity.y = 200;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void dispose() {
        birdAnimationTexture.dispose();
    }
}
