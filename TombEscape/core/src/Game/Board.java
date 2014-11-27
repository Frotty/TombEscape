package Game;

import java.util.LinkedList;

import Game.Entities.Entity;
import Game.Entities.Escaper;
import Game.Entities.FinishPoint;
import Game.Entities.Spinner;
import Game.Entities.StartPoint;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Json.Serializable;
import com.badlogic.gdx.utils.JsonValue;

public class Board implements java.io.Serializable{
	public Tile[][] tiles = new Tile[Globals.BOARD_SIZE][Globals.BOARD_SIZE];
	public Escaper escaper;
	public boolean finished = false;
	public StartPoint startPoint;
	public LinkedList<Spinner> spinners = new LinkedList<Spinner>();
	public FinishPoint finishPoint;
	
	static private int[][] pattern = {{0,0,0,0,0, 0,0,0,0,0},
									  {0,0,1,1,1, 1,0,0,1,0},
									  {0,0,1,0,0, 1,0,0,1,0},
									  {0,0,1,0,0, 1,0,0,1,0},
									  {0,0,1,1,0, 1,0,0,1,0},
									  {0,0,0,0,0, 1,0,0,1,0},
									  {0,1,1,1,1, 1,0,1,1,0},
									  {0,1,0,0,0, 0,0,1,1,0},
									  {0,1,1,1,1, 1,1,1,0,0},
									  {0,0,0,0,0, 0,0,0,0,0}};
	
	public Board(){
		for (int i = 0; i < Globals.BOARD_SIZE; i++) {
			for(int j = 0; j < Globals.BOARD_SIZE; j++){
				if(pattern[i][j] == 1){
					tiles[i][j] = new Tile(new Index(i,j), TileType.WALKABLE);
					System.out.println("Creating Walkable");
				}else{
					tiles[i][j] = new Tile(new Index(i,j), TileType.UNWALKABLE);
				}
			}
			
		}
		startPoint = new StartPoint(tiles[1][8].pos.getGamePos().add(new Vector2(Globals.TILE_SIZE/2,Globals.TILE_SIZE/2)));
		escaper = new Escaper(startPoint.pos);
		spinners.add(new Spinner( tiles[4][9].pos.getGamePos().add(new Vector2(Globals.TILE_SIZE/2,Globals.TILE_SIZE/2))
							, tiles[4][6].pos.getGamePos().add(new Vector2(Globals.TILE_SIZE/2,Globals.TILE_SIZE/2)) ));

		spinners.add(new Spinner( tiles[6][6].pos.getGamePos().add(new Vector2(Globals.TILE_SIZE/2,Globals.TILE_SIZE/2))
							, tiles[6][9].pos.getGamePos().add(new Vector2(Globals.TILE_SIZE/2,Globals.TILE_SIZE/2)) ));
		finishPoint = new FinishPoint(tiles[4][5].pos.getGamePos().add(new Vector2(Globals.TILE_SIZE/2,Globals.TILE_SIZE/2)));
		
	}
	
	public Tile getGameTile(Index i){
		
		Tile tmp = null;
		if (i.x<0 || i.x >= Globals.BOARD_SIZE || i.y >= Globals.BOARD_SIZE || i.y < 0){
			throw new IndexOutOfBoundsException("Board index out of bounds.");
		}else{
			tmp = tiles[i.x][i.y];
		}
		return tmp;
	}

	public void activateEntities(){
		Entity.addEntity(escaper);
		Entity.addEntity(startPoint);
		Entity.addEntity(finishPoint);
		for (Spinner s : spinners) {
			Entity.addEntity(s);
		}
	}

	public void deactivate() {
		Entity.removeEntity(escaper);
		Entity.removeEntity(startPoint);
		Entity.removeEntity(finishPoint);
		for (Spinner s : spinners) {
			Entity.removeEntity(s);
		}
	/*	escaper = null;
		startPoint = null;
		finishPoint = null;
		spinners = null;
	*/}
	
}
