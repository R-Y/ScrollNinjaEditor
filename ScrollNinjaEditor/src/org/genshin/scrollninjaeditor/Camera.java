package org.genshin.scrollninjaeditor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class Camera extends OrthographicCamera {
	
	private boolean moveFlag          = false;
	private boolean useFlg            = false;
	private float   oldmousePositionX = 0.0f;
	private float   oldmousePositionY = 0.0f;
	private float   mousePositionX = 0.0f;
	private float   mousePositionY = 0.0f;
	
	public Camera(float width , float height) {
		super(width , height);
	}
	
	public void move() {
			
		oldmousePositionX = mousePositionX;
		oldmousePositionY = mousePositionY;
		
		if(Gdx.input.isKeyPressed(Keys.SPACE)) {
			
			mousePositionX = (Gdx.input.getX() - Gdx.graphics.getWidth() / 2) + this.position.x;
			mousePositionY = (Gdx.input.getY() - Gdx.graphics.getHeight() / 2) - this.position.y;
			
			if(Gdx.input.isButtonPressed(Buttons.LEFT))	{
				moveFlag = true;
		
				
				this.position.x -= (mousePositionX - oldmousePositionX) / 2;
				this.position.y += (mousePositionY - oldmousePositionY) / 2;
				
			}
			
		}
		else {
			moveFlag = false;
		}
		
	}
	
	public void zoom(float zoomSize) {
		if(!this.useFlg){
			if((Gdx.input.isKeyPressed(Keys.CONTROL_LEFT) || Gdx.input.isKeyPressed(Keys.CONTROL_RIGHT)) && Gdx.input.isKeyPressed(Keys.NUM_0)) {
				this.zoom = zoomSize;
				this.useFlg = true;
			}
		}
		else{
			if((Gdx.input.isKeyPressed(Keys.CONTROL_LEFT) || Gdx.input.isKeyPressed(Keys.CONTROL_RIGHT)) && Gdx.input.isKeyPressed(Keys.NUM_0)) {
				this.zoom = 1;
				this.useFlg = false;
			}
		}
	}
	
	public boolean update(float zoomSize)
	{
		move();
		zoom(zoomSize);
		this.update();
		return moveFlag;
	}
}