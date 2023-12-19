package com.mygdx.game;

//ship contains information about the ship object


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.TextureAtlasData.Region;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

//abstract means a portion of the class is written but a portion cant be written until the class gets specialized into subclasses
abstract class Ship

{

  //ship charactesristics
    float movementSpeed; //for the ships movement speed //in world units per second
    int shield; //amount of shield ship has remaining

  //position and dimension
    float xPosition, yPosition; //for the lower left corner
    float width, height;

    //laser information
    float laserWidth, laserHeight;
    float timeBetweenShots;
    float timeSinceLastShot  = 0; //right now there has been no time since the last shot
    float laserMovementSpeed;

    //graphics
    TextureRegion shipTextureRegion, shieldTextureRegion, laserTextureRegion;

    public Ship(float movementSpeed, int shield, float xPosition, float yPosition, float width, float height,float laserWidth, float laserHeight, float laserMovementSpeed,float timeBetweenShots, TextureRegion shipTextureRegion, TextureRegion shieldTextureRegion, TextureRegion laserTextureRegion)
    {
      this.movementSpeed = movementSpeed;
      this.shield = shield;
      this.xPosition = xPosition;
      this.yPosition = yPosition;
      this.width = width;
      this.height = height;
      this.shipTextureRegion = shipTextureRegion;
      this.shieldTextureRegion = shieldTextureRegion;
      this.laserTextureRegion = laserTextureRegion;
      this.laserWidth = laserWidth;
      this.laserHeight = laserHeight;
      this.laserMovementSpeed = laserMovementSpeed;
      this.timeBetweenShots = timeBetweenShots;
    }

    //its going to need to know when laser was fired
    public void update(float deltaTime)
    {
        timeSinceLastShot += deltaTime;
    }

    //method which asks ship if it is able to fire laser
    public boolean canFireLaser()
    {
      //check if enough time has passed
      boolean result = (timeSinceLastShot - timeBetweenShots >= 0);
      return result;
    }

    //method to produce array of lasers
    public abstract Laser[] fireLasers();




    //used to draw the batch passed to Ship class
    //multiple draw commands can be used to draw the ship and the shield
    public void draw(SpriteBatch batch)
    {
      //batch = new SpriteBatch();

      //first ship is drawn then shield is drawn
      batch.draw(shipTextureRegion, xPosition, yPosition, width, height);
      if (shield > 0)
      {
        batch.draw(shieldTextureRegion, xPosition, yPosition, width, height);
      }
    }
}
