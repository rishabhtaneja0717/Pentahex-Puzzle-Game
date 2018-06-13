/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Rishabh Taneja and Jinen Gandhi
 */
public class ModelGameInfo {
    
    
    // Game state = 0
    //  Game Being started after compilation
    // show Logo and all
    
    // Game state 1
    // Game Restart state ( show how many players buttons on canvas)
    
    // Game state 2
    // Normal game progression
    
    // game state 3
    // Game is over and game over is displayed on screen
    
    //Game state 4
    // Player Won Screen Settings
    
    
    int game_state;
    
    boolean is_two_plyrs;
    
    static boolean is_game_over;    
    
    
    // Constructor
    public ModelGameInfo()
    {
        game_state=0;
        is_game_over=false;
    }
    
    
    // FUNCTION TO CALL WHEN GAME IS RESTARTED
    void restart_game_settings()
    {
        game_state=1;
        is_game_over=false;
    }
    
    void set_game_state(int a)
    {
        game_state=a;
    }
    
    int get_game_state()
    {
        return game_state;
    }
    
    
    void set_is_two_plyrs(boolean a)
    {
        is_two_plyrs=a;
    }
    
    boolean get_is_two_plyrs()
    {
        return is_two_plyrs;
    }
    
    
    void set_is_game_over(boolean a)
    {
        is_game_over=a;
    }
    
    
    
    boolean get_is_game_over()
    {
        return is_game_over;
    }
}
