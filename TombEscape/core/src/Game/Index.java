package Game;

import com.badlogic.gdx.math.Vector2;
import java.io.Serializable;

public class Index implements Serializable{
	int x;
	int y;
	
	public Index(){
		
	}
	
	public Index(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public boolean sameAs(Index otherIndex) {
		return this.x == otherIndex.x && this.y == otherIndex.y;
	}
	
	//Pivot point of the Vector is not in the middle yet
	public Vector2 getGamePos(){
		return new Vector2(x*Globals.TILE_SIZE, y*Globals.TILE_SIZE);
	}
}
