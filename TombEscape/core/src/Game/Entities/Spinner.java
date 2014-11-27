package Game.Entities;

import java.io.Serializable;

import Game.Globals;
import TombEscape.Game;

import com.badlogic.gdx.math.Vector2;

public class Spinner extends Entity{
	private float speed = 60;
	private float size = Globals.SPINNER_SIZE;
	public float facing = 0;
	public Vector2 patrol_start;
	private Vector2 patrol_end;
	private boolean direction;
	

	public Spinner(Vector2 pos, Vector2 end) {
		super(new Vector2(pos));
		patrol_start = new Vector2(pos);
		patrol_end = new Vector2(end);
		direction = true;
	}

	@Override
	protected void update() {
		float dst = pos.dst(Game.getActiveBoard().escaper.pos) ;
		//System.out.println("Distance:" + dst);
		if((dst ) <size){
			Game.getActiveBoard().escaper.kill();
		}
		facing= (facing -10)%360;
		Vector2 v;
		if(direction){
			v = new Vector2(patrol_end);
		}else{
			v = new Vector2(patrol_start);
		}
		if(v.dst(pos) < speed/Globals.ANIMATION_COUNT){
			direction = !direction;
		}
		v.sub(pos);
		v.limit( speed/Globals.ANIMATION_COUNT);
		pos.add(v);

	}

}
