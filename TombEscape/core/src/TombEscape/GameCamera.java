package TombEscape;

import com.badlogic.gdx.graphics.OrthographicCamera;

public class GameCamera extends OrthographicCamera{
	
	public GameCamera(){
		super();
		setToOrtho(false, 640, 640);
	}
	
}
