package Game.Entities;

import java.io.Serializable;
import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;

public abstract class Entity  implements Serializable{
	static ArrayList<Entity> instances = new ArrayList<Entity>();
	public Vector2 pos;
	boolean active = true;
	
	public Entity(Vector2 pos){
		this.pos = new Vector2(pos);
		instances.add(this);
	}
	
	public static void updateEntities(){
		for (Entity e : instances) {
			if(e.active){
				e.update();
			}
		}
	}
	
	abstract protected void update();
	
	public Vector2 getPos(){
		return new Vector2(pos.x,pos.y);
	}
	
	public static void addEntity(Entity e){
		instances.add(e);
	}
	public static void removeEntity(Entity e){
		instances.remove(e);
	}
}

