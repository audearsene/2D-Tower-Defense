package main;

//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.InputStream;

//import javax.imageio.ImageIO;
import javax.swing.JFrame;

import helpz.LoadSave;
import main.scenes.Editing;
import main.scenes.GameOver;
//import inputs.KeyboardListener;
//import inputs.MyMouseListener;
import main.scenes.Menu;
import main.scenes.Playing;
import main.scenes.Settings;
import managers.TileManager;

public class Game extends JFrame implements Runnable{

	private final double FPS_SET = 120.0;
	private final double UPS_SET = 60.0;

	// private long lastTimeUPS;

	private Thread gameThread;

	//Classes
	private GameScreen gameScreen;
	private Render render;
	private Menu menu;
	private Playing playing;
	private Settings settings;
	private Editing editing;
	private GameOver gameOver;
    private TileManager tileManager;

	public Game() {

		initClasses();
		createDefaultLevel();

		//setSize(928,864);//image 944*852 ici 29*27
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("Tower Defense");
		add(gameScreen);
		pack();
		setVisible(true);
	}

    private void createDefaultLevel() {
        int[] arr = new int[400];
        for(int i=0; i< arr.length;i++)
            arr[i] = 0;

        LoadSave.CreateLevel("new_level", arr);
    }

	private void initClasses() {
        tileManager = new TileManager();
        render = new Render(this);
		gameScreen=new GameScreen(this);
		menu = new Menu(this);
		playing = new Playing(this);
		settings = new Settings(this);
		editing =new Editing(this);
		gameOver = new GameOver(this);
	}

	private void start(){
		gameThread = new Thread(this){};

		gameThread.start();
	}

	// private void callUPS(){
	// 	if(System.currentTimeMillis() - lastTimeUPS>= 1000){
	// 		System.out.println("UPS: " + update);
	// 		update = 0;
	// 		lastTimeUPS = System.currentTimeMillis();
	// 	}
	// }

	public void updateGame(){
		switch (GameStates.gameStates) {
			case EDIT:
				
				break;
			case MENU:
				
				break;
			case PLAYING:
				playing.update();
				break;
			case SETTINGS:
				
				break;
		
			default:
				break;
		}
	}
	public static void main(String[] args) {
		
		Game game = new Game();
		game.gameScreen.initInputs();
		game.start();
		
	}

	@Override
	public void run() {

		double timePerFrame=1000000000.0/FPS_SET;
		double timePerUpdate=1000000000.0/UPS_SET;

		long lastFrame=System.nanoTime();
		long lastTimeCheck = System.currentTimeMillis();
		long lastUpdate=System.nanoTime();

		int frames = 0;
		int updates = 0;

		long now;

		while (true) {
			
			now = System.nanoTime();
			//Render
			if (now- lastFrame >= timePerFrame){
				repaint();				lastFrame=now;
				frames++;
			}

			//Update
			if(now - lastUpdate >= timePerUpdate){
				updateGame();
				lastUpdate =now;
				updates++;
			}

			//Checking FPS and UPS
			if(System.currentTimeMillis() - lastTimeCheck >= 1000){
				System.out.println("FPS: " + frames + " | UPS: " + updates);
				frames=0;
				updates=0;
				lastTimeCheck=System.currentTimeMillis();
			}
			
		}
	}

	//Getters and Setters
	public Render getRender(){
		return render;
	}

	public Menu getMenu() {
		return menu;
	}

	public Playing getPlaying() {
		return playing;
	}

	public Settings getSettings() {
		return settings;
	}

	public Editing getEditor() {
		return editing;
	}

	public GameOver getGameOver() {
		return gameOver;
	}

	public TileManager getTileManager() {
		return tileManager;
	}

}
