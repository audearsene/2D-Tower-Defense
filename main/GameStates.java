package main;

public enum GameStates {
    PLAYING, MENU, SETTINGS, EDIT, GAME_OVER;

    public static GameStates gameStates = MENU;

    public static void SetGameState(GameStates state){
        gameStates = state;
    }
}
