package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.TextureAtlasData.Region;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


class Laser

{
  //characteristics of the laser
  float movementSpeed; //in world units per second

  //the position and dimensions of the laser
  float xPosition, yPosition;

  float width, height;

  TextureRegion textureregion;

  public Laser( float xPosition, float yPosition, float width, float height, float movementSpeed, TextureRegion textureregion)
  {
    this.xPosition = xPosition;
    this.yPosition = yPosition;
    this.width = width;
    this.height = height;
    this.movementSpeed = movementSpeed;
    this.textureregion = textureregion;
  }
  //private SpriteBatch batch;

  //used to draw the batch passed to Ship class
  //multiple draw commands can be used to draw the ship and the shield
  public void draw(SpriteBatch batch)
  {
    //batch = new SpriteBatch();

    //first ship is drawn then shield is drawn
    //to move it to left enough since xposition is for the centre of it
    batch.draw(textureregion, xPosition - width/2, yPosition, width, height);
  }
}
