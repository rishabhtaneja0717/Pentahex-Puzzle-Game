/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Rishabh Taneja and Jinen Gandhi
 */

import java.awt.*;

public class ModelBoard {
    
    // Polynomial storing polynomial type for each board piece hexagon shape
    Polygon[][] polyboard=new Polygon[10][11];
    
    // Boolean value to determine whether the particular board piece is covered or not
    static boolean is_board_piece_covered[][]= new boolean [10][11];
    
    // To store board piece's (hexagon) x and y coordinates of centre
    int brd_pc_x[][]= new int[10][11];
    
    int brd_pc_y[][]= new int[10][11];
   
    
    public ModelBoard()
    {
        for(int i=0;i<10;i++)
        {
            for(int j=0;j<11;j++)
            {
                is_board_piece_covered[i][j]=false;
            }// end of for loop of j
        }// end of for loop of j
    } // end of CONSTRUCTOR
    
    
    // FUNCTION TO CALL WHEN GAME IS RESTARTED
    void restart_game_settings()
    {
        for(int i=0;i<10;i++)
        {
            for(int j=0;j<11;j++)
            {
                is_board_piece_covered[i][j]=false;
            }// end of for loop of j
        }// end of for loop of j
    }
    
    // Method to determine whether a point is contained in a particular piece of board
    boolean is_piece_on_board(int i, int j, int x, int y)
    {
        if(polyboard[i][j].contains(x,y))
            return true;
        else 
            return false;
    }
    
    // Method to set board piece polygon
    void setBoardPiecePolygon(int i, int j,Polygon a)
    {
        polyboard[i][j]=a;
    }
    
    // Method to get board piece polygon
    Polygon getBoardPiecePolygon(int i, int j)
    {
        return polyboard[i][j];
    }
    
    // Method to mark particular board piece as covered
    void cover_board_piece(int i,int j)
    {
        is_board_piece_covered[i][j]=true;
    }
    
    // Function to set centre of a board piece
    void set_piece_position(int i, int j, int x, int y)
    {
        brd_pc_x[i][j]=x;
        brd_pc_y[i][j]=y;
    }
    
    
    // Function to get centre of a board piece
    int[] get_piece_position(int i, int j)
    {
        int a[] = new int[2];
        a[0]=brd_pc_x[i][j];
        a[1]=brd_pc_y[i][j];
        
        return a;
        
    }
    
    // Function  to determine whether particular board piece is covered or not
    boolean get_is_brd_piece_covered(int i, int j)
    {
        return is_board_piece_covered[i][j];
    }
    
    // Function  to cover a particular piece of board
    void set_brd_piece_covered(int i, int j)
    {
        is_board_piece_covered[i][j]=true;
    }
    
}
