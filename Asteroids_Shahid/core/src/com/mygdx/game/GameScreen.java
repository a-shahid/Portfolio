package com.mygdx.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.TextureAtlasData.Region;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import java.util.LinkedList;
import java.util.ListIterator;

class GameScreen implements Screen
{

  //screen
  //2 things to keep track of stuff
  private Camera camera;
  private Viewport viewport;

  //graphics
  //a list of sprites to draw to fill up the batch in order
  private SpriteBatch batch;

  private TextureAtlas textureAtlas;


  //a graphic or image for our background
  //private TextureRegion[] backgrounds; instead of having a single background we have an array of textureregions
  private TextureRegion[] backgrounds;
  private float backgroundHeight; //height of background in World units


  //a texture region for each of our visual objects on screen
  private TextureRegion playerShipTextureRegion, playerShieldTextureRegion, enemyShipTextureRegion, enemyShieldTextureRegion, playerLaserTextureRegion, enemyLaserTextureRegion;

  //timing
  private float[] backgroundOffsets = {0, 0, 0, 0};
  private float backgroundMaxScrollingSpeed;

  //world parameters
  //values to help us display things consistently on the screen
  private final int WORLD_WIDTH = 72;
  private final int WORLD_HEIGHT = 128;

  //game objects
  private Ship playerShip;
  private Ship enemyShip;

  private LinkedList<Laser> playerLaserList;

  private LinkedList<Laser> enemyLaserList;

  //building game screen constructor

  GameScreen()
  {
    //a 2D camera
    camera = new OrthographicCamera();
    //the screen the user sees/what we see
    viewport = new StretchViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);

    //setup the texture atlas
    textureAtlas = new TextureAtlas("images.atlas");


    //set background image
    backgrounds = new TextureRegion[4];
    backgrounds[0] = textureAtlas.findRegion("Starscape00");
    backgrounds[1] = textureAtlas.findRegion("Starscape01");
    backgrounds[2] = textureAtlas.findRegion("Starscape02");
    backgrounds[3] = textureAtlas.findRegion("Starscape03");

    //background = new Texture("darkPurpleStarScape.png");

    //start at 0
    backgroundHeight = WORLD_HEIGHT * 2;
    backgroundMaxScrollingSpeed = (float) (WORLD_HEIGHT) / 4;


    //initialize texture regions
    playerShipTextureRegion = textureAtlas.findRegion("playerShip2_blue");
    enemyShipTextureRegion = textureAtlas.findRegion("enemyRed3");
    playerShieldTextureRegion = textureAtlas.findRegion("shield2");
    enemyShieldTextureRegion = textureAtlas.findRegion("shield1");
    enemyShieldTextureRegion.flip(false, true);

    playerLaserTextureRegion = textureAtlas.findRegion("laserBlue03");
    enemyLaserTextureRegion = textureAtlas.findRegion("laserRed03");

    playerShip = new PlayerShip(2,3,WORLD_WIDTH/2, WORLD_HEIGHT/4,10,10,0.4f,4,45,0.5f,
                               playerShipTextureRegion, playerShieldTextureRegion,playerLaserTextureRegion);

    //make some differences in laser of enemyShip
    enemyShip = new EnemyShip(2,1,WORLD_WIDTH/3, WORLD_HEIGHT/2,10,10,0.3f,5,50,0.8f,enemyShipTextureRegion, enemyShieldTextureRegion,enemyLaserTextureRegion);

    playerLaserList = new LinkedList<>();

    enemyLaserList = new LinkedList<>();


    //create sprite batch itself
    batch = new SpriteBatch();
  }

  @Override
  // @deltaTime : that is how much time has passed since the previous render cycle
  public void render(float deltaTime)
  {
    batch.begin();

    //we add these updates to make sure that it is moving along as time passes. this will make lasers fire now that we are keeping track of lasers on the screen
    playerShip.update(deltaTime);
    enemyShip.update(deltaTime);

    //scrolling background
    renderBackground(deltaTime);


    //enemy ships
    enemyShip.draw(batch);

    //player ships
    playerShip.draw(batch);

    //lasers
    //create new lasers if necessary

    //ask ship if it is time to fire yet
    if (playerShip.canFireLaser())
    {
      Laser[] lasers = playerShip.fireLasers(); //make laser objects

      //loop looks every object for lasers and places it inside for loop
      for (Laser laser:lasers)
      {
        playerLaserList.add(laser);
      }
    }

    //enemy lasers
    if (enemyShip.canFireLaser())
    {
      Laser[] lasers = enemyShip.fireLasers();

      for (Laser laser: lasers)
      {
        enemyLaserList.add(laser);
      }
    }

    //draw lasers
    //draw each laser and game screen moves lasers around on its own

    //we cant remove lasers while iterating through them therefore you create a new for loop
    //make a list iterator which moves through our array
    ListIterator<Laser> iterator = playerLaserList.listIterator();
        while(iterator.hasNext()) {
            Laser laser = iterator.next();
            laser.draw(batch);
            laser.yPosition += laser.movementSpeed*deltaTime;
            if (laser.yPosition > WORLD_HEIGHT) {
                iterator.remove();
            }
        }
        iterator = enemyLaserList.listIterator();
        while(iterator.hasNext()) {
            Laser laser = iterator.next();
            laser.draw(batch);
            laser.yPosition -= laser.movementSpeed*deltaTime;
            if (laser.yPosition + laser.height < 0) {
                iterator.remove();
            }
        }
    //and remove old lasers


    //explosions


    batch.end();
  }

  private void renderBackground(float deltaTime) {

      //update position of background images
      backgroundOffsets[0] += deltaTime * backgroundMaxScrollingSpeed / 8;
      backgroundOffsets[1] += deltaTime * backgroundMaxScrollingSpeed / 4;
      backgroundOffsets[2] += deltaTime * backgroundMaxScrollingSpeed / 2;
      backgroundOffsets[3] += deltaTime * backgroundMaxScrollingSpeed;

      //draw each background layer
      for (int layer = 0; layer < backgroundOffsets.length; layer++) {
          if (backgroundOffsets[layer] > WORLD_HEIGHT) {
              backgroundOffsets[layer] = 0;
          }
          batch.draw(backgrounds[layer], 0, -backgroundOffsets[layer],
                  WORLD_WIDTH, backgroundHeight);
      }
  }

  @Override
  public void show()
  {

  }


  @Override
  public void hide()
  {

  }

  @Override
  public void resume()
  {

  }

  @Override
  public void pause()
  {

  }

  @Override
  //runs this method when application starts
  public void resize(int width, int height)
  {
    viewport.update(width, height, true);
    batch.setProjectionMatrix(camera.combined);
  }

  @Override
  public void dispose()
  {

  }
}
