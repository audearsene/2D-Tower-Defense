package main;

import javax.swing.JPanel;

import inputs.KeyboardListener;
import inputs.MyMouseListener;

import java.awt.Dimension;
import java.awt.Graphics;
// import java.awt.image.BufferedImage;
// import java.util.ArrayList;
// import java.util.Random;
//import java.awt.Color;

public class GameScreen extends JPanel{

    //private Random random;
    //private BufferedImage img;

    private Dimension size;
    private Game game;

	private MyMouseListener myMouseListener;
	private KeyboardListener keyboardListener;

    //private long lastTime;
    //private int frames;
    //private ArrayList<BufferedImage> sprites = new ArrayList<>();

    // private Color getRandColor(){
    //     int r = random.nextInt(256);
    //     int g = random.nextInt(256);
    //     int b = random.nextInt(256);

    //     return new Color(r, g, b);
    // }
    
    public GameScreen(Game game){
        this.game = game;

        setPanelSize();
    }

	public void initInputs(){
		myMouseListener = new MyMouseListener(game);
		keyboardListener = new KeyboardListener(game);

		addMouseListener(myMouseListener);
		addMouseMotionListener(myMouseListener);
		addKeyListener(keyboardListener);

		requestFocus();//get focus on the previous inputs
	}

    private void setPanelSize() {
        size = new Dimension(640,800);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);


    }

    // public GameScreen(){
    //     random = new Random();
    // }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        game.getRender().render(g);

        //g.drawImage(img, 0, 0, null);

        //g.drawImage(img.getSubimage(0,14,32,32), 0, 0, null);

        //g.drawImage(sprites.get(19), 0, 0, null);


        // for (int i=0; i<20;i++){
        //     for (int y=0; y<20;y++){
        //         g.setColor(getRandColor());
        //         g.fillRect(y*32,i*32, 32, 32);
        //     }
        // }

        
        // callFPS();
    }

    // private void callFPS(){
    //     frames++;
    //     if(System.currentTimeMillis()-lastTime >=1000){
    //         System.out.println("FPS: "+ frames);
    //         frames=0;
    //         lastTime = System.currentTimeMillis();
    //     }

    // }

}