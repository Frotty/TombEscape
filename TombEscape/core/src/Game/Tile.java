package Game;

import java.io.Serializable;

public class Tile implements Serializable {
	public TileType type;
	public Index pos;
	
	public Tile(Index pos, TileType ty){
		this.pos = pos;
		type = ty;
		}
	
}
