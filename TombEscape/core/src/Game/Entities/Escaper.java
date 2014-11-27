package Game.Entities;

import Game.Globals;
import Game.Tile;
import Game.TileType;
import TombEscape.Game;

import com.badlogic.gdx.math.Vector2;

public class Escaper extends Entity{
	double speed = 100;
	double turnSpeed = 0.005;
	private double facing;
	
	public Vector2 order_dest;
		
	public Escaper(Vector2 pos) {
		super(pos);
		setFacing(0.);
	}

	@Override
	protected void update() {
		Tile currentTile = getTile();
		if(currentTile != null){
			if(currentTile.type == TileType.WALKABLE){
				speed = 130;
			}else if(currentTile.type == TileType.UNWALKABLE){
				kill();
				return;
			}
		}
		if(order_dest != null){
			if(pos.dst2(order_dest) < speed*speed/(Globals.ANIMATION_COUNT*Globals.ANIMATION_COUNT)){
				pos = order_dest;
				order_dest = null;
			}else{
				Vector2 v = new Vector2(order_dest);
				v.sub(pos);
				v.limit((float) speed/Globals.ANIMATION_COUNT);
				double angle = v.angle();
				if(Math.abs(getFacing() - angle) > turnSpeed){
					boolean add = false;//(Math.abs(getFacing() - angle) < Math.abs(getFacing() - 180 - angle));
					setFacing(angle + (add?turnSpeed:(-turnSpeed)));
				}
				//setFacing(v.angle());
				
				pos.add(v);
				
				//System.out.println(pos.x + " "+ pos.y);
			}
		}
	}	

	public Tile getTile(){
		System.out.println("Escaper - getTile()");
		return Game.getActiveBoard().getGameTile(Globals.getIndex(pos));
	}
	

	public double getFacing() {
		return facing;
	}

	public void setFacing(double facing) {
		this.facing = facing;
	}

	public void kill() {
		pos = Game.getActiveBoard().startPoint.getPos();
		order_dest = null;
		
	}

}
