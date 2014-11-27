package Game.Entities;

import java.io.Serializable;

import Game.Globals;
import TombEscape.Game;

import com.badlogic.gdx.math.Vector2;

public class FinishPoint extends Entity implements Serializable{

	public FinishPoint(Vector2 pos){
		super(pos);
		
	}
	
	@Override
	public void update(){
		if( pos.dst(Game.getActiveBoard().escaper.pos) < Globals.CHECKPOINT_SIZE ){
			Game.getActiveBoard().finished = true;
			Game.pauseGame(true);
		}

	}
}
