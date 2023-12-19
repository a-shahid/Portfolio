package com.mygdx.game;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


class PlayerShip extends Ship
{

  public PlayerShip(float movementSpeed, int shield, float xPosition, float yPosition, float width, float height,float laserWidth,
  float laserHeight, float laserMovementSpeed,float timeBetweenShots,
  TextureRegion shipTextureRegion, TextureRegion shieldTextureRegion, TextureRegion laserTextureRegion)
  {
    super( movementSpeed,  shield,  xPosition,  yPosition,  width, height,laserWidth, laserHeight,laserMovementSpeed,timeBetweenShots, shipTextureRegion, shieldTextureRegion, laserTextureRegion);
  }

//change positioning of the lasers because we want them to be different for the player ship and the enemy ship
  public Laser[] fireLasers()
  {
    Laser[] laser = new Laser[2];
    //we want both lasers to be in different positions therefore multuplying accordingly (7% and 93%)
    laser[0] = new Laser(xPosition+(width*0.07f),yPosition+(height*0.45f),
    laserWidth,laserHeight,laserMovementSpeed,laserTextureRegion);
    laser[1] = new Laser(xPosition+(width*0.93f),yPosition+(height*0.45f),
    laserWidth,laserHeight,laserMovementSpeed,laserTextureRegion);

    timeSinceLastShot = 0;


    return laser;
  }
}
