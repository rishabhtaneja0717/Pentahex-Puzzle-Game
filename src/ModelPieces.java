/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Rishabh Taneja and Jinen Gandhi
 */


import java.awt.*;
public class ModelPieces {
    
   
    // Current Piece configuration (ONLY MATRIX
    int Piece_Current_x[][], Piece_Current_y[][], neighbor_config[][],neighbor_config_odd[][], curr_config[];
    
    // Set Final Polygon type of penthexes
    Polygon Piece_Final_Polygon[][]; //Piece_Final_Polygon[piece no] [ subpart hexagon no]
    
    
    // Boolean values to determine whwther piece is placed on board or in focus
    static boolean is_piece_placed[];
    
    int no_of_pieces_placed;
    int piece_5_config, piece_15_config;
    
    public ModelPieces()
    {   
      
        // For Current configurations
        Piece_Current_x= new int [22][5];   // new int [piece_no][sub_pice no]
        Piece_Current_y= new int [22][5];   // new int [piece_no][sub_pice no]
        
        Piece_Final_Polygon= new Polygon[22][5];
        
        
        
        is_piece_placed= new boolean[22];
        
        curr_config= new int[22];
                
        neighbor_config = new int [6][2];
        neighbor_config_odd = new int [6][2];
        
        piece_5_config=piece_15_config=0;
       
        // Call Function to initialize all piece configurations
        init();
        
        
        
    }
    
    // Function to initialize all piece configurations
    void init()
    {
        
        no_of_pieces_placed=0;
        
        for(int i=0;i<22;i++)
        {
           
            is_piece_placed[i]=false;
            
            curr_config[i]=0;
            
            
        }
            // ########    Piece 0  Starts  #############
            
            // Subpart 0
             Piece_Current_x[0][0]=0;
             Piece_Current_y[0][0]=0;
             
             // Subpart 1
             Piece_Current_x[0][1]=0;
             Piece_Current_y[0][1]=1;
             
             // Subpart 2
             Piece_Current_x[0][2]=1;
             Piece_Current_y[0][2]=1;
             
             // Subpart 3
             Piece_Current_x[0][3]=2;
             Piece_Current_y[0][3]=1;
             
             // Subpart 4
             Piece_Current_x[0][4]=2;
             Piece_Current_y[0][4]=0;
             
              // ########    Piece 0  Ends  #############
             
             
             
             
             // ########    Piece 1  Starts  #############
            
            // Subpart 0
             Piece_Current_x[1][0]=0;
             Piece_Current_y[1][0]=0;
             
             // Subpart 1
             Piece_Current_x[1][1]=1;
             Piece_Current_y[1][1]=0;
             
             // Subpart 2
             Piece_Current_x[1][2]=0;
             Piece_Current_y[1][2]=1;
             
             // Subpart 3
             Piece_Current_x[1][3]=1;
             Piece_Current_y[1][3]=1;
             
             // Subpart 4
             Piece_Current_x[1][4]=1;
             Piece_Current_y[1][4]=2;
             
              // ########    Piece 1  Ends  #############
             
             
             // ########    Piece 2  Starts  #############
            
            // Subpart 0
             Piece_Current_x[2][0]=0;
             Piece_Current_y[2][0]=0;
             
             // Subpart 1
             Piece_Current_x[2][1]=0;
             Piece_Current_y[2][1]=1;
             
             // Subpart 2
             Piece_Current_x[2][2]=0;
             Piece_Current_y[2][2]=2;
             
             // Subpart 3
             Piece_Current_x[2][3]=0;
             Piece_Current_y[2][3]=3;
             
             // Subpart 4
             Piece_Current_x[2][4]=0;
             Piece_Current_y[2][4]=4;
             
              // ########    Piece 2  Ends  #############
             
             
             // ########    Piece 3  Starts  #############
            
            // Subpart 0
             Piece_Current_x[3][0]=0;
             Piece_Current_y[3][0]=0;
             
             // Subpart 1
             Piece_Current_x[3][1]=1;
             Piece_Current_y[3][1]=0;
             
             // Subpart 2
             Piece_Current_x[3][2]=1;
             Piece_Current_y[3][2]=1;
             
             // Subpart 3
             Piece_Current_x[3][3]=0;
             Piece_Current_y[3][3]=1;
             
             // Subpart 4
             Piece_Current_x[3][4]=0;
             Piece_Current_y[3][4]=2;
             
              // ########    Piece 3  Ends  #############
             
             
             
             // ########    Piece 4  Starts  #############
            
            // Subpart 0
             Piece_Current_x[4][0]=0;
             Piece_Current_y[4][0]=0;
             
             // Subpart 1
             Piece_Current_x[4][1]=1;
             Piece_Current_y[4][1]=0;
             
             // Subpart 2
             Piece_Current_x[4][2]=2;
             Piece_Current_y[4][2]=0;
             
             // Subpart 3
             Piece_Current_x[4][3]=2;
             Piece_Current_y[4][3]=1;
             
             // Subpart 4
             Piece_Current_x[4][4]=3;
             Piece_Current_y[4][4]=2;
             
              // ########    Piece 4  Ends  #############
             
               
             // ########    Piece 6  Starts  #############
            
            // Subpart 0
             Piece_Current_x[6][0]=0;
             Piece_Current_y[6][0]=0;
             
             // Subpart 1
             Piece_Current_x[6][1]=-1;
             Piece_Current_y[6][1]=0;
             
             // Subpart 2
             Piece_Current_x[6][2]=-2;
             Piece_Current_y[6][2]=1;
             
             // Subpart 3
             Piece_Current_x[6][3]=-1;
             Piece_Current_y[6][3]=2;
             
             // Subpart 4
             Piece_Current_x[6][4]=-1;
             Piece_Current_y[6][4]=3;
             
              // ########    Piece 6  Ends  #############
             
             
              // ########    Piece 7  Starts  #############
            
            // Subpart 0
             Piece_Current_x[7][0]=0;
             Piece_Current_y[7][0]=0;
             
             // Subpart 1
             Piece_Current_x[7][1]=0;
             Piece_Current_y[7][1]=-1;
             
             // Subpart 2
             Piece_Current_x[7][2]=1;
             Piece_Current_y[7][2]=-2;
             
             // Subpart 3
             Piece_Current_x[7][3]=1;
             Piece_Current_y[7][3]=-1;
             
             // Subpart 4
             Piece_Current_x[7][4]=2;
             Piece_Current_y[7][4]=0;
             
              // ########    Piece 7  Ends  #############
             
             // ########    Piece 8  Starts  #############
            
            // Subpart 0
             Piece_Current_x[8][0]=0;
             Piece_Current_y[8][0]=0;
             
             // Subpart 1
             Piece_Current_x[8][1]=-1;
             Piece_Current_y[8][1]=-1;
             
//             // Subpart 2
             Piece_Current_x[8][2]=0;
             Piece_Current_y[8][2]=-2;
//             
             // Subpart 3
             Piece_Current_x[8][3]=1;
             Piece_Current_y[8][3]=-2;
             
             // Subpart 4
             Piece_Current_x[8][4]=1;
             Piece_Current_y[8][4]=-1;
//             
              // ########    Piece 8  Ends  #############
             
              
             // ########    Piece 9  Starts  #############
            
            // Subpart 0
             Piece_Current_x[9][0]=0;
             Piece_Current_y[9][0]=0;
             
             // Subpart 1
             Piece_Current_x[9][1]=-1;
             Piece_Current_y[9][1]=1;
             
//             // Subpart 2
             Piece_Current_x[9][2]=-1;
             Piece_Current_y[9][2]=2;
//             
             // Subpart 3
             Piece_Current_x[9][3]=-1;
             Piece_Current_y[9][3]=3;
             
             // Subpart 4
             Piece_Current_x[9][4]=-1;
             Piece_Current_y[9][4]=4;
//             
              // ########    Piece 9  Ends  #############
             
               // ########    Piece 10  Starts  #############
            
            // Subpart 0
             Piece_Current_x[10][0]=0;
             Piece_Current_y[10][0]=0;
             
             // Subpart 1
             Piece_Current_x[10][1]=-1;
             Piece_Current_y[10][1]=0;
             
//             // Subpart 2
             Piece_Current_x[10][2]=-2;
             Piece_Current_y[10][2]=-1;
//             
             // Subpart 3
             Piece_Current_x[10][3]=-1;
             Piece_Current_y[10][3]=-2;
             
             // Subpart 4
             Piece_Current_x[10][4]=-2;
             Piece_Current_y[10][4]=-3;
//             
              // ########    Piece 10  Ends  #############
             
              // ########    Piece 11  Starts  #############
            
            // Subpart 0
             Piece_Current_x[11][0]=0;
             Piece_Current_y[11][0]=0;
             
             // Subpart 1
             Piece_Current_x[11][1]=1;
             Piece_Current_y[11][1]=0;
             
//             // Subpart 2
             Piece_Current_x[11][2]=0;
             Piece_Current_y[11][2]=1;
//             
             // Subpart 3
             Piece_Current_x[11][3]=1;
             Piece_Current_y[11][3]=2;
             
             // Subpart 4
             Piece_Current_x[11][4]=0;
             Piece_Current_y[11][4]=3;
//             
              // ########    Piece 11  Ends  #############
             
              // ########    Piece 12  Starts  #############
            
            // Subpart 0
             Piece_Current_x[12][0]=0;
             Piece_Current_y[12][0]=0;
             
             // Subpart 1
             Piece_Current_x[12][1]=1;
             Piece_Current_y[12][1]=0;
             
//             // Subpart 2
             Piece_Current_x[12][2]=0;
             Piece_Current_y[12][2]=-1;
//             
             // Subpart 3
             Piece_Current_x[12][3]=1;
             Piece_Current_y[12][3]=-1;
             
             // Subpart 4
             Piece_Current_x[12][4]=2;
             Piece_Current_y[12][4]=-1;
//             
              // ########    Piece 12  Ends  #############
             
               // ########    Piece 13  Starts  #############
            
            // Subpart 0
             Piece_Current_x[13][0]=0;
             Piece_Current_y[13][0]=0;
             
             // Subpart 1
             Piece_Current_x[13][1]=1;
             Piece_Current_y[13][1]=0;
             
//             // Subpart 2
             Piece_Current_x[13][2]=2;
             Piece_Current_y[13][2]=0;
//             
             // Subpart 3
             Piece_Current_x[13][3]=3;
             Piece_Current_y[13][3]=0;
             
             // Subpart 4
             Piece_Current_x[13][4]=4;
             Piece_Current_y[13][4]=0;
//             
              // ########    Piece 13  Ends  #############
             
              // ########    Piece 14  Starts  #############
            
            // Subpart 0
             Piece_Current_x[14][0]=0;
             Piece_Current_y[14][0]=0;
             
             // Subpart 1
             Piece_Current_x[14][1]=1;
             Piece_Current_y[14][1]=0;
             
//             // Subpart 2
             Piece_Current_x[14][2]=2;
             Piece_Current_y[14][2]=0;
//             
             // Subpart 3
             Piece_Current_x[14][3]=3;
             Piece_Current_y[14][3]=0;
             
             // Subpart 4
             Piece_Current_x[14][4]=3;
             Piece_Current_y[14][4]=1;
//             
              // ########    Piece 14  Ends  #############
             
//              // ########    Piece 15  Starts  #############
//            
//            // Subpart 0
//             Piece_Current_x[15][0]=0;
//             Piece_Current_y[15][0]=0;
//             
//             // Subpart 1
//             Piece_Current_x[15][1]=1;
//             Piece_Current_y[15][1]=0;
//             
////             // Subpart 2
//             Piece_Current_x[15][2]=2;
//             Piece_Current_y[15][2]=0;
////             
//             // Subpart 3
//             Piece_Current_x[15][3]=3;
//             Piece_Current_y[15][3]=0;
//             
//             // Subpart 4
//             Piece_Current_x[15][4]=3;
//             Piece_Current_y[15][4]=1;
////             
//              // ########    Piece 15  Ends  #############
             
              // ########    Piece 16  Starts  #############
            
            // Subpart 0
             Piece_Current_x[16][0]=0;
             Piece_Current_y[16][0]=0;
             
             // Subpart 1
             Piece_Current_x[16][1]=0;
             Piece_Current_y[16][1]=1;
             
//             // Subpart 2
             Piece_Current_x[16][2]=1;
             Piece_Current_y[16][2]=0;
//             
             // Subpart 3
             Piece_Current_x[16][3]=2;
             Piece_Current_y[16][3]=0;
             
             // Subpart 4
             Piece_Current_x[16][4]=1;
             Piece_Current_y[16][4]=-1;
//             
              // ########    Piece 16  Ends  #############
             
              // ########    Piece 17  Starts  #############
            
            // Subpart 0
             Piece_Current_x[17][0]=0;
             Piece_Current_y[17][0]=0;
             
             // Subpart 1
             Piece_Current_x[17][1]=1;
             Piece_Current_y[17][1]=0;
             
//             // Subpart 2
             Piece_Current_x[17][2]=1;
             Piece_Current_y[17][2]=1;
//             
             // Subpart 3
             Piece_Current_x[17][3]=2;
             Piece_Current_y[17][3]=2;
             
             // Subpart 4
             Piece_Current_x[17][4]=3;
             Piece_Current_y[17][4]=2;
//             
              // ########    Piece 17  Ends  #############
             
             // ########    Piece 18  Starts  #############
            
            // Subpart 0
             Piece_Current_x[18][0]=0;
             Piece_Current_y[18][0]=0;
             
             // Subpart 1
             Piece_Current_x[18][1]=0;
             Piece_Current_y[18][1]=-1;
             
//             // Subpart 2
             Piece_Current_x[18][2]=0;
             Piece_Current_y[18][2]=-2;
//             
             // Subpart 3
             Piece_Current_x[18][3]=1;
             Piece_Current_y[18][3]=-2;
             
             // Subpart 4
             Piece_Current_x[18][4]=2;
             Piece_Current_y[18][4]=-2;
//             
              // ########    Piece 18  Ends  #############
             
              // ########    Piece 19  Starts  #############
            
            // Subpart 0
             Piece_Current_x[19][0]=0;
             Piece_Current_y[19][0]=0;
             
             // Subpart 1
             Piece_Current_x[19][1]=0;
             Piece_Current_y[19][1]=-1;
             
//             // Subpart 2
             Piece_Current_x[19][2]=1;
             Piece_Current_y[19][2]=-1;
//             
             // Subpart 3
             Piece_Current_x[19][3]=2;
             Piece_Current_y[19][3]=-1;
             
             // Subpart 4
             Piece_Current_x[19][4]=3;
             Piece_Current_y[19][4]=0;
//             
              // ########    Piece 19  Ends  #############
             
                // ########    Piece 20  Starts  #############
            
            // Subpart 0
             Piece_Current_x[20][0]=0;
             Piece_Current_y[20][0]=0;
             
             // Subpart 1
             Piece_Current_x[20][1]=0;
             Piece_Current_y[20][1]=-1;
             
//             // Subpart 2
             Piece_Current_x[20][2]=1;
             Piece_Current_y[20][2]=0;
//             
             // Subpart 3
             Piece_Current_x[20][3]=2;
             Piece_Current_y[20][3]=0;
             
             // Subpart 4
             Piece_Current_x[20][4]=3;
             Piece_Current_y[20][4]=0;
//             
              // ########    Piece 20  Ends  #############
             
             // ########    Piece 20  Starts  #############
            
            // Subpart 0
             Piece_Current_x[21][0]=0;
             Piece_Current_y[21][0]=0;
             
             // Subpart 1
             Piece_Current_x[21][1]=1;
             Piece_Current_y[21][1]=0;
             
//             // Subpart 2
             Piece_Current_x[21][2]=1;
             Piece_Current_y[21][2]=-1;
//             
             // Subpart 3
             Piece_Current_x[21][3]=2;
             Piece_Current_y[21][3]=0;
             
             // Subpart 4
             Piece_Current_x[21][4]=3;
             Piece_Current_y[21][4]=0;
//             
              // ########    Piece 21  Ends  #############
             
             
             
             
             // ####################### NEIGHBOUR CONFIGURATION #########################
            // Neighbour 0
             neighbor_config[0][0]=0; // x value of 0th neighnour
             neighbor_config[0][1]=-1; // y value of 0th neighbour
             
             // Neighbour 1
             neighbor_config[1][0]=1; // x value of 0th neighnour
             neighbor_config[1][1]=0; // y value of 0th neighbour
             
             // Neighbour 2
             neighbor_config[2][0]=0; // x value of 0th neighnour
             neighbor_config[2][1]=1; // y value of 0th neighbour
             
             // Neighbour 3
             neighbor_config[3][0]=-1; // x value of 0th neighnour
             neighbor_config[3][1]=1; // y value of 0th neighbour
             
             // Neighbour 4
             neighbor_config[4][0]=-1; // x value of 0th neighnour
             neighbor_config[4][1]=0; // y value of 0th neighbour
             
             // Neighbour 5
             neighbor_config[5][0]=-1; // x value of 0th neighnour
             neighbor_config[5][1]=-1; // y value of 0th neighbour
             
             
             
             
             // ####################### NEIGHBOUR CONFIGURATION ODD PARENT#########################
            // Neighbour 0
             neighbor_config_odd[0][0]=1; // x value of 0th neighnour
             neighbor_config_odd[0][1]=-1; // y value of 0th neighbour
             
             // Neighbour 1
             neighbor_config_odd[1][0]=1; // x value of 0th neighnour
             neighbor_config_odd[1][1]=0; // y value of 0th neighbour
             
             // Neighbour 2
             neighbor_config_odd[2][0]=1; // x value of 0th neighnour
             neighbor_config_odd[2][1]=1; // y value of 0th neighbour
             
             // Neighbour 3
             neighbor_config_odd[3][0]=0; // x value of 0th neighnour
             neighbor_config_odd[3][1]=1; // y value of 0th neighbour
             
             // Neighbour 4
             neighbor_config_odd[4][0]=-1; // x value of 0th neighnour
             neighbor_config_odd[4][1]=0; // y value of 0th neighbour
             
             // Neighbour 5
             neighbor_config_odd[5][0]=0; // x value of 0th neighnour
             neighbor_config_odd[5][1]=-1; // y value of 0th neighbour
             
       
        
    }//void init()
    
    
    
    // FUNCTION TO CALL WHEN GAME IS RESTARTED
    void restart_game_settings()
    {
        piece_5_config=piece_15_config=0;
        
        no_of_pieces_placed=0;
        
        init();
        
         for(int i=0;i<22;i++)
        {
           
            is_piece_placed[i]=false;
            
            curr_config[i]=0;
            
            
        }
           
    }
    
    
    
    
    // Function to determine Neighbour Cycle no for a sub-piece (w.r.t its neighnour)
    int neighbour_cycle_no(int x, int y,int parent)
    {
        int i=-1;
        if((Math.abs(parent)%2)==0)
        {
            for( i=0;i<6;i++)
            {
                if(x==neighbor_config[i][0] && y==neighbor_config[i][1])
                {
                    break;

                }
            }
        }
        else 
        {
            for( i=0;i<6;i++)
            {
                if(x==neighbor_config_odd[i][0] && y==neighbor_config_odd[i][1])
                {
                    break;

                }
            }
        }
        return i;
        
    }
    
    // Function to determine next relative neighbour
    public int[] next_relative_neighbour(int neigh_cycle_no, int rot, int parent_y, int num) // rot will be -1 or 1 depending upon rotating clockwise or anticlockwise
    {
     int n_c_no=neigh_cycle_no;
     
     int a[]=new int[2];
     int new_config;
     
     if(rot==1)
     {
         new_config=(neigh_cycle_no+1)%6;
     }
     else// (rot= -1)
     {
         new_config=(neigh_cycle_no-1);
         if(new_config==-1)
             new_config=5;
     }
     
     
     if(Math.abs(parent_y)%2==0)
     {
         a[0]=neighbor_config[new_config][0];
         a[1]=neighbor_config[new_config][1];
         
     }
     
     else
     {
        
               
         if(new_config==0)
         {
             a[0]=1;
             a[1]=-1;
         }
         
         if(new_config==1)
         {
             a[0]=1;
             a[1]=0;
         }
         
         if(new_config==2)
         {
             a[0]=1;
             a[1]=1;
         }
         
         if(new_config==3)
         {
             a[0]=0;
             a[1]=1;
         }
         
         if(new_config==4)
         {
             a[0]=-1;
             a[1]=0;
         }
         
         if(new_config==5)
         {
             a[0]=0;
             a[1]=-1;
         }
     } 
        
       
    
     return a;
     
    }
    
    void chang_config_next(int piece_no, int rot) // rot will be -1 or 1 depending upon rotating clockwise or anticlockwise
    {
        if(piece_no==5 || piece_no==15)
        {
            int new_nonfig;
            
            if(piece_no==5)
            {
                piece_5_config=piece_5_config+rot;
                
                if(piece_5_config>5)
                    piece_5_config=0;
                
                if(piece_5_config<0)
                    piece_5_config=5;
                
                update_piece_5(piece_5_config);
                
            }
            
            
            
            if(piece_no==15)
            {
                piece_15_config=piece_15_config+rot;
                
                if(piece_15_config>5)
                    piece_15_config=0;
                
                if(piece_15_config<0)
                    piece_15_config=5;
                
                update_piece_15(piece_15_config);
                
            }
            
        }
        else
        {
            
        
                        // No change in first sub piece
                        Piece_Current_x[piece_no][0]=0;
                        Piece_Current_y[piece_no][0]=0;

                        int diff_x[]= new int[4];
                        int diff_y[]= new int[4];
                        int prnt[]=new int[4];

                        for(int k=1;k<5;k++)
                        {
                            diff_x[k-1]=Piece_Current_x[piece_no][k]-Piece_Current_x[piece_no][k-1];
                            diff_y[k-1]=Piece_Current_y[piece_no][k]-Piece_Current_y[piece_no][k-1];
                            prnt[k-1]=Piece_Current_y[piece_no][k-1];;
                        }

                        for(int i=1;i <5;i++)
                        {
                            //int temp_x= Piece_Current_x[piece_no][i];
                            //int temp_y= Piece_Current_y[piece_no][i];



                            int neig_cyc_no;

                            //if((Math.abs(prnt[i-1])%2)==0)
                                neig_cyc_no=neighbour_cycle_no(diff_x[i-1], diff_y[i-1], prnt[i-1]);

                            int a[] = new int[2];

                            a=next_relative_neighbour(neig_cyc_no,rot,Piece_Current_y[piece_no][i-1],i);


                             Piece_Current_x[piece_no][i]=Piece_Current_x[piece_no][i-1]+a[0];

                            Piece_Current_y[piece_no][i]=Piece_Current_y[piece_no][i-1]+a[1];

                        }// end of for(int i=1;i <5;i++)
                        
        } // end of else
        
        
    } // end of void chang_config_next(int piece_no, int rot)
    
    
    
    
    void update_piece_5(int config)
    {
        if(config==0)
        {
              
             // ########    Piece 5 - cofig 1  Starts  #############
            
            // Subpart 0
             Piece_Current_x[5][0]=0;
             Piece_Current_y[5][0]=0;
             
             // Subpart 1
             Piece_Current_x[5][1]=0;
             Piece_Current_y[5][1]=1;
             
             // Subpart 2
             Piece_Current_x[5][2]=1;
             Piece_Current_y[5][2]=1;
             
             // Subpart 3
             Piece_Current_x[5][3]=2;
             Piece_Current_y[5][3]=0;
             
             // Subpart 4
             Piece_Current_x[5][4]=2;
             Piece_Current_y[5][4]=2;
             
              // ########    Piece 5 - config 1  Ends  #############
        }
        
        
        
        
        if(config==1)
        {
              
             // ########    Piece 5 - cofig 1  Starts  #############
            
            // Subpart 0
             Piece_Current_x[5][0]=0;
             Piece_Current_y[5][0]=0;
             
             // Subpart 1
             Piece_Current_x[5][1]=-1;
             Piece_Current_y[5][1]=1;
             
             // Subpart 2
             Piece_Current_x[5][2]=0;
             Piece_Current_y[5][2]=2;
             
             // Subpart 3
             Piece_Current_x[5][3]=1;
             Piece_Current_y[5][3]=2;
             
             // Subpart 4
             Piece_Current_x[5][4]=-1;
             Piece_Current_y[5][4]=3;
             
              // ########    Piece 5 - config 1  Ends  #############
        }
        
        
        if(config==2)
        {
              
             // ########    Piece 5 - cofig 1  Starts  #############
            
            // Subpart 0
             Piece_Current_x[5][0]=0;
             Piece_Current_y[5][0]=0;
             
             // Subpart 1
             Piece_Current_x[5][1]=-1;
             Piece_Current_y[5][1]=0;
             
             // Subpart 2
             Piece_Current_x[5][2]=-2;
             Piece_Current_y[5][2]=1;
             
             // Subpart 3
             Piece_Current_x[5][3]=-1;
             Piece_Current_y[5][3]=2;
             
             // Subpart 4
             Piece_Current_x[5][4]=-3;
             Piece_Current_y[5][4]=1;
             
              // ########    Piece 5 - config 1  Ends  #############
        }
        
        
        
        if(config==3)
        {
              
             // ########    Piece 5 - cofig 1  Starts  #############
            
            // Subpart 0
             Piece_Current_x[5][0]=0;
             Piece_Current_y[5][0]=0;
             
             // Subpart 1
             Piece_Current_x[5][1]=-1;
             Piece_Current_y[5][1]=-1;
             
             // Subpart 2
             Piece_Current_x[5][2]=-2;
             Piece_Current_y[5][2]=-1;
             
             // Subpart 3
             Piece_Current_x[5][3]=-2;
             Piece_Current_y[5][3]=0;
             
             // Subpart 4
             Piece_Current_x[5][4]=-2;
             Piece_Current_y[5][4]=-2;
             
              // ########    Piece 5 - config 1  Ends  #############
        }
        
        
        if(config==4)
        {
              
             // ########    Piece 5 - cofig 1  Starts  #############
            
            // Subpart 0
             Piece_Current_x[5][0]=0;
             Piece_Current_y[5][0]=0;
             
             // Subpart 1
             Piece_Current_x[5][1]=0;
             Piece_Current_y[5][1]=-1;
             
             // Subpart 2
             Piece_Current_x[5][2]=0;
             Piece_Current_y[5][2]=-2;
             
             // Subpart 3
             Piece_Current_x[5][3]=-1;
             Piece_Current_y[5][3]=-2;
             
             // Subpart 4
             Piece_Current_x[5][4]=0;
             Piece_Current_y[5][4]=-3;
             
              // ########    Piece 5 - config 1  Ends  #############
        }
        
        
         if(config==5)
        {
              
             // ########    Piece 5 - cofig 1  Starts  #############
            
            // Subpart 0
             Piece_Current_x[5][0]=0;
             Piece_Current_y[5][0]=0;
             
             // Subpart 1
             Piece_Current_x[5][1]=1;
             Piece_Current_y[5][1]=0;
             
             // Subpart 2
             Piece_Current_x[5][2]=1;
             Piece_Current_y[5][2]=-1;
             
             // Subpart 3
             Piece_Current_x[5][3]=2;
             Piece_Current_y[5][3]=-2;
             
             // Subpart 4
             Piece_Current_x[5][4]=1;
             Piece_Current_y[5][4]=-2;
             
              // ########    Piece 5 - config 1  Ends  #############
        }
        
        
    }
    
    
    
    
    
    
    
    
    void update_piece_15(int config)
    {
        if(config==0)
        {
              
             // ########    Piece 15 - cofig 1  Starts  #############
            
            // Subpart 0
             Piece_Current_x[15][0]=0;
             Piece_Current_y[15][0]=0;
             
             // Subpart 1
             Piece_Current_x[15][1]=1;
             Piece_Current_y[15][1]=0;
             
             // Subpart 2
             Piece_Current_x[15][2]=2;
             Piece_Current_y[15][2]=0;
             
             // Subpart 3
             Piece_Current_x[15][3]=2;
             Piece_Current_y[15][3]=-1;
             
             // Subpart 4
             Piece_Current_x[15][4]=2;
             Piece_Current_y[15][4]=1;
             
              // ########    Piece 15 - config 1  Ends  #############
        }
        
        
        
        
        if(config==1)
        {
              
             // ########    Piece 15 - cofig 1  Starts  #############
            
            // Subpart 0
             Piece_Current_x[15][0]=0;
             Piece_Current_y[15][0]=0;
             
             // Subpart 1
             Piece_Current_x[15][1]=0;
             Piece_Current_y[15][1]=1;
             
             // Subpart 2
             Piece_Current_x[15][2]=1;
             Piece_Current_y[15][2]=2;
             
             // Subpart 3
             Piece_Current_x[15][3]=2;
             Piece_Current_y[15][3]=2;
             
             // Subpart 4
             Piece_Current_x[15][4]=0;
             Piece_Current_y[15][4]=3;
             
              // ########    Piece 15 - config 1  Ends  #############
        }
        
        
        if(config==2)
        {
              
             // ########    Piece 15 - cofig 1  Starts  #############
            
            // Subpart 0
             Piece_Current_x[15][0]=0;
             Piece_Current_y[15][0]=0;
             
             // Subpart 1
             Piece_Current_x[15][1]=-1;
             Piece_Current_y[15][1]=1;
             
             // Subpart 2
             Piece_Current_x[15][2]=-1;
             Piece_Current_y[15][2]=2;
             
             // Subpart 3
             Piece_Current_x[15][3]=-1;
             Piece_Current_y[15][3]=3;
             
             // Subpart 4
             Piece_Current_x[15][4]=-2;
             Piece_Current_y[15][4]=2;
             
              // ########    Piece 15 - config 1  Ends  #############
        }
        
        
        
        if(config==3)
        {
              
             // ########    Piece 15 - cofig 1  Starts  #############
            
            // Subpart 0
             Piece_Current_x[15][0]=0;
             Piece_Current_y[15][0]=0;
             
             // Subpart 1
             Piece_Current_x[15][1]=-1;
             Piece_Current_y[15][1]=0;
             
             // Subpart 2
             Piece_Current_x[15][2]=-2;
             Piece_Current_y[15][2]=0;
             
             // Subpart 3
             Piece_Current_x[15][3]=-3;
             Piece_Current_y[15][3]=-1;
             
             // Subpart 4
             Piece_Current_x[15][4]=-3;
             Piece_Current_y[15][4]=1;
             
              // ########    Piece 15 - config 1  Ends  #############
        }
        
        
        if(config==4)
        {
              
             // ########    Piece 15 - cofig 1  Starts  #############
            
            // Subpart 0
             Piece_Current_x[15][0]=0;
             Piece_Current_y[15][0]=0;
             
             // Subpart 1
             Piece_Current_x[15][1]=-1;
             Piece_Current_y[15][1]=-1;
             
             // Subpart 2
             Piece_Current_x[15][2]=-1;
             Piece_Current_y[15][2]=-2;
             
             // Subpart 3
             Piece_Current_x[15][3]=-2;
             Piece_Current_y[15][3]=-2;
             
             // Subpart 4
             Piece_Current_x[15][4]=-1;
             Piece_Current_y[15][4]=-3;
             
              // ########    Piece 15 - config 1  Ends  #############
        }
        
        
         if(config==5)
        {
              
             // ########    Piece 15 - cofig 1  Starts  #############
            
            // Subpart 0
             Piece_Current_x[15][0]=0;
             Piece_Current_y[15][0]=0;
             
             // Subpart 1
             Piece_Current_x[15][1]=0;
             Piece_Current_y[15][1]=-1;
             
             // Subpart 2
             Piece_Current_x[15][2]=1;
             Piece_Current_y[15][2]=-2;
             
             // Subpart 3
             Piece_Current_x[15][3]=0;
             Piece_Current_y[15][3]=-3;
             
             // Subpart 4
             Piece_Current_x[15][4]=2;
             Piece_Current_y[15][4]=-2;
             
              // ########    Piece 15 - config 1  Ends  #############
        }
        
        
    }
    
    
    
    
    
}
