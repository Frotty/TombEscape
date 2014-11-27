package Game;

import com.badlogic.gdx.math.Vector2;

public class Globals {
	public static int TILE_SIZE = 64;
	
	public static int BOARD_SIZE = 10;
	
	public static int ANIMATION_COUNT = 60; // Per second
	public static double ANIMATION_TIME = (1/ANIMATION_COUNT);
	
	public static int EXPLORER_SIZE = 48;
	public static int SPINNER_SIZE =  TILE_SIZE/2;
	public static final float CHECKPOINT_SIZE = TILE_SIZE/4;
	
	public static Index getIndex(Vector2 v){
		int xi = (int) (v.x/TILE_SIZE);
		int yi = (int) (v.y/TILE_SIZE);
		System.out.println("Measured index: ["+ xi +" , "+ yi +"]");
		return new Index(xi,yi);
	}
}
