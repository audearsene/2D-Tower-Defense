package inputs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import main.Game;
import main.GameStates;

public class MyMouseListener implements MouseListener, MouseMotionListener {

    private Game game;
    
    public MyMouseListener(Game game){
        this.game = game;
    }

    @Override
    public void mouseDragged(MouseEvent arg0) {
        switch (GameStates.gameStates) {
            case MENU:
                game.getMenu().mouseDragged(arg0.getX(), arg0.getY());
                break;
        
            case PLAYING:
                game.getPlaying().mouseDragged(arg0.getX(), arg0.getY());
                break;

            case SETTINGS:
                game.getSettings().mouseDragged(arg0.getX(), arg0.getY());
                break;

            case EDIT:
                game.getEditor().mouseDragged(arg0.getX(), arg0.getY());
                break;
            default:
                break;
        }
    }

    @Override
    public void mouseMoved(MouseEvent arg0) {
        switch (GameStates.gameStates) {
                case MENU:
                    game.getMenu().mouseMoved(arg0.getX(), arg0.getY());
                    break;
            
                case PLAYING:
                    game.getPlaying().mouseMoved(arg0.getX(), arg0.getY());
                    break;

                case SETTINGS:
                    game.getSettings().mouseMoved(arg0.getX(), arg0.getY());
                    break;

                case EDIT:
                    game.getEditor().mouseMoved(arg0.getX(), arg0.getY());
                    break;
                case GAME_OVER:
                    game.getGameOver().mouseMoved(arg0.getX(), arg0.getY());
                    break;
                default:
                    break;
            }
    }

    @Override
    public void mouseClicked(MouseEvent arg0) {
        if(arg0.getButton() == MouseEvent.BUTTON1){
            switch (GameStates.gameStates) {
                case MENU:
                    game.getMenu().mouseClicked(arg0.getX(), arg0.getY());
                    break;
            
                case PLAYING:
                    game.getPlaying().mouseClicked(arg0.getX(), arg0.getY());
                    break;

                case SETTINGS:
                    game.getSettings().mouseClicked(arg0.getX(), arg0.getY());
                    break;

                case EDIT:
                    game.getEditor().mouseClicked(arg0.getX(), arg0.getY());
                    break;
                case GAME_OVER:
                    game.getGameOver().mouseClicked(arg0.getX(), arg0.getY());
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'mouseEntered'");
    }

    @Override
    public void mouseExited(MouseEvent arg0) {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'mouseExited'");
    }

    @Override
    public void mousePressed(MouseEvent arg0) {
       switch (GameStates.gameStates) {
                case MENU:
                    game.getMenu().mousePressed(arg0.getX(), arg0.getY());
                    break;
            
                case PLAYING:
                    game.getPlaying().mousePressed(arg0.getX(), arg0.getY());
                    break;

                case SETTINGS:
                    game.getSettings().mousePressed(arg0.getX(), arg0.getY());
                    break;

                case EDIT:
                    game.getEditor().mousePressed(arg0.getX(), arg0.getY());
                    break;
                case GAME_OVER:
                    game.getGameOver().mousePressed(arg0.getX(), arg0.getY());
                    break;
                default:
                    break;
            }
    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
        switch (GameStates.gameStates) {
                case MENU:
                    game.getMenu().mouseReleased(arg0.getX(), arg0.getY());
                    break;
            
                case PLAYING:
                    game.getPlaying().mouseReleased(arg0.getX(), arg0.getY());
                    break;

                case SETTINGS:
                    game.getSettings().mouseReleased(arg0.getX(), arg0.getY());
                    break;

                case EDIT:
                    game.getEditor().mouseReleased(arg0.getX(), arg0.getY());
                    break;
                case GAME_OVER:
                    game.getGameOver().mouseReleased(arg0.getX(), arg0.getY());
                    break;
                default:
                    break;
            }
    }
    
}
