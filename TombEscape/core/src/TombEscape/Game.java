package TombEscape;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import Game.Board;
import Game.Entities.Entity;
import Game.Entities.Spinner;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class Game extends ApplicationAdapter {
	//SpriteBatch batch;
	//Texture img;
	GameDrawer drawer;
	public static GameCamera cam;
	public static Board activeBoard;
	private static boolean paused;
	
	boolean saved = false;
	
	@Override
	public void create () {
		activeBoard = new Board();
		
		drawer = new GameDrawer();
		
		cam = new GameCamera();
		
		paused = false;
	}

	@Override
	public void render () {
		//Gdx.gl.glClearColor(1, 0.5f, 0.0f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		cam.update();
		drawer.batch.setProjectionMatrix(cam.combined);
		drawer.render();
		if(!paused){
			Entity.updateEntities();	
		}
	
		if(Gdx.input.isButtonPressed(Buttons.LEFT)){ 
			Vector3 ordPos = new Vector3();
			ordPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			cam.unproject(ordPos);
			getActiveBoard().escaper.order_dest = new Vector2(ordPos.x,ordPos.y);
		}else if(Gdx.input.isButtonPressed(Buttons.RIGHT)){
			try {
				if(!saved){
					saveBoard(getActiveBoard(), "level1");
					System.out.println("Saving...");
					saved = true;	
				}else{
					activeBoard = loadBoard("level1");
					saved = false;
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static Board getActiveBoard(){
		return activeBoard;
	}
	
	public static void pauseGame(boolean p){
		paused = p;
	}
	
	public Board loadBoard(String name) throws IOException, ClassNotFoundException{
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(name +".tomb"));
		Board b =(Board) ois.readObject();
		ois.close();
		b.activateEntities();
		return b;
	}
	
	public void saveBoard(Board board, String name) throws FileNotFoundException, IOException{
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(name + ".tomb"));
		oos.writeObject(board);
		oos.close();
		board.deactivate();
		//board = null;
	}

}
