package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Batch;

import com.badlogic.gdx.graphics.g2d.TextureRegion;


class EnemyShip extends Ship
{
  public EnemyShip(float movementSpeed, int shield, float xPosition, float yPosition, float width, float height,float laserWidth,
  float laserHeight, float laserMovementSpeed,float timeBetweenShots,
  TextureRegion shipTextureRegion, TextureRegion shieldTextureRegion, TextureRegion laserTextureRegion)
  {
    super( movementSpeed,  shield,  xPosition,  yPosition,  width, height,laserWidth, laserHeight,laserMovementSpeed,timeBetweenShots, shipTextureRegion, shieldTextureRegion, laserTextureRegion);
  }

  public Laser[] fireLasers()
  {
    Laser[] laser = new Laser[2];
    //we want both lasers to be in different positions therefore multuplying accordingly (7% and 93%)
    laser[0] = new Laser(xPosition+(width*0.18f),yPosition-laserHeight,laserWidth,laserHeight,laserMovementSpeed,laserTextureRegion);
    laser[1] = new Laser(xPosition+(width*0.82f),yPosition-laserHeight,laserWidth,laserHeight,laserMovementSpeed,laserTextureRegion);

    timeSinceLastShot = 0;


    return laser;
  }

  //@Override
  public void draw(Batch batch)
  {
        batch.draw(shipTextureRegion, xPosition, yPosition, width, height);
        if (shield > 0) {
            batch.draw(shieldTextureRegion, xPosition, yPosition-height*0.2f, width, height);
        }
    }

}
