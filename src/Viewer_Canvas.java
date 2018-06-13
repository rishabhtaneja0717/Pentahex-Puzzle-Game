/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Rishabh Taneja and Jinen Gandhi
 */

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.io.*;

///////////////////////////////////////////////////////////////////// Viewer_Canvas
class Viewer_Canvas extends JPanel implements MouseListener, MouseMotionListener  {
    
    ModelBoard MB= new ModelBoard();
    
    ModelGameInfo MGI= new ModelGameInfo(); 
    
   // For Restart game paint button  
   boolean is_rstrt_image_hvr, is_rstrt_image_pressed;
   
   // For no of player selection paint button
   int plyr_selection_val;
   boolean is_plyr_sln_pressed;
    
   // For Start Game paint button
   boolean is_start_image_hvr, is_start_image_pressed;
   
    int  init_graphics;
    double side;
    
    int mouseX, mouseY, drag_diff_x, drag_diff_y;
    
    boolean to_draw_polygon, piece_being_dragged;
    
    int draw_piece_no, start_x, start_y;
    double h,r,b,a;
    
    // Pieces relative centre positions
    int Piece_x_r[]=new int[5];
    int Piece_y_r[]=new int[5];
    
    
    // Pieces actual centre Co-ordinates
    int Piece_x_act[]=new int[5];
    int Piece_y_act[]=new int[5];
    Polygon[] poly_Piece = new Polygon[5];
    
    // Array to store Matrix row and column values of 5 board piece being hovered when penthex is dragged
    int brd_hover_row[]= new int[5];
    int brd_hover_col[]= new int[5];
    
    
    // Gradients for different colored hexagons in a piece
    GradientPaint[] grad = new GradientPaint[5];
    
    
    // Counter determining no of pieces of penthex overlap on board pieces
    int ct_brd_pcs_overlapped;
    
    ModelPieces MP= new ModelPieces();
    
    PuzzleController PD;
    
    // IMAGES FOR NO OF PLAYERS DISPLAY SELECTION
    BufferedImage  image_no_of_plyrs_bk;                           
    BufferedImage img_select_no_of_plyrs_text; // 270 * 60
    BufferedImage img_1_plyr_nm, img_1_plyr_hv ; // 180 * 30
    BufferedImage img_2_plyr_nm, img_2_plyr_hv ; // 180 * 30
    
    // IMAGES FOR START GAME DISPLAY 
    BufferedImage  image_start_game_bk; // 812 * 612
    BufferedImage  image_start_game_txt_nm; // 305 * 50
    BufferedImage  image_start_game_txt_hv;  // 305 * 50
    
    // IMAGES FOR MADE BY Display
    BufferedImage  image_made_by; // 812 * 35
    
    // IMAGES FOR Instructions
    BufferedImage  image_instruct_sel; // 812 * 35
    BufferedImage  image_instruct_drag; // 812 * 35
    
    // Images FOR TURN INO
    BufferedImage  image_turn_1; // 113 * 35
    BufferedImage  image_turn_2; // 113 * 35
    
    static boolean is_game_over;
    
    static boolean rpt_first_time;
    //============================================================== constructor
    public Viewer_Canvas(ModelPieces mp,ModelBoard mb,ModelGameInfo mgi, PuzzleController pd) {
        
        
        
        try 
        {
            // INITIALIZE IMAGES FOR NO OF PLAYERS SELECTION DISPLAY
            
            image_no_of_plyrs_bk = ImageIO.read(new File("Images/no_of_plyr_BK.png"));            
            img_select_no_of_plyrs_text= ImageIO.read(new File("Images/select_no_of_plyrs_text.png")); 
            img_1_plyr_nm= ImageIO.read(new File("Images/1_plyr_hv.png"));
            img_1_plyr_hv= ImageIO.read(new File("Images/1_plyr_nml.png"));
            img_2_plyr_nm= ImageIO.read(new File("Images/2_plyrs_hv.png"));            
            img_2_plyr_hv= ImageIO.read(new File("Images/2_plyrs_nml.png"));
            
            // INITIALIZE IMAGES FOR NO OF PLAYERS SELECTION DISPLAY
            
            image_start_game_bk = ImageIO.read(new File("Images/Start_game_bk.png"));            
            image_start_game_txt_nm= ImageIO.read(new File("Images/Start_game_txt_1.png")); 
            image_start_game_txt_hv= ImageIO.read(new File("Images/Start_game_txt_2.png"));
            
            // INITIALIZE IMAGES FOR NO OF PLAYERS SELECTION DISPLAY
            
            image_made_by = ImageIO.read(new File("Images/Made_by.png"));   
            
            // INITIALIZE IMAGES FOR TWO INSTRUCTIONS
            
            image_instruct_sel = ImageIO.read(new File("Images/Instructions_select.png")); 
            image_instruct_drag = ImageIO.read(new File("Images/Instructions_drag.png")); 
            
            // INITIALIZE IMAGES FOR PLAYER TURN
            
            image_turn_1 = ImageIO.read(new File("Images/Turn_1.png"));
            image_turn_2 = ImageIO.read(new File("Images/Turn_2.png"));
            


        }
        catch (IOException ie) 
        {
              
            System.out.println("Critical Error. Unable To load Images...");
        }
        
        
        
        
        MGI=mgi;
        
        rpt_first_time=false;
        MP=mp;
        
        PD= new PuzzleController(-1);
        
        PD=pd;
        
        MB=mb;
       
        is_game_over=false;
        
        ct_brd_pcs_overlapped=0;
        
        
        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.white);
        
         init_graphics=1;
         side=20;
         
         to_draw_polygon=false;
         piece_being_dragged=false;
         
         draw_piece_no=-1;
         
         start_x=-1;
         start_y=-1;
         
         
         
         
          h=Math.sin(Math.PI/6)*side;
          r=Math.cos(Math.PI/6)*side;
          b=side+(2*h);
          a=2*r;
         
        // ...... Create gradients .....
           
           grad[0]= new GradientPaint(75,75,new Color(111, 162, 213),115,115, new Color(51,102,153),true); // blue
           grad[1]= new GradientPaint(75,75,new Color(255, 97, 97),115,115, new Color(218,37,37),true); // red
           grad[2]= new GradientPaint(75,75,new Color(172, 200, 95),115,115, new Color(112,140,35),true); // green
           grad[3]= new GradientPaint(75,75,new Color(213, 162, 213),115,115, new Color(153,102,153),true); // purple
           grad[4]= new GradientPaint(75,75,new Color(255, 175, 107),115,115, new Color(240,115,47),true); // orange
          
           
           // Initialize Board Hover Rows and columns
           for(int k=0;k<5;k++)
           {
               brd_hover_row[k]=-1;
               brd_hover_col[k]=-1;
           }
           
           plyr_selection_val=-1;
           
           is_plyr_sln_pressed=false;
           
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }
    
     // FUNCTION TO CALL WHEN GAME IS RESTARTED
    void restart_game_settings()
    {
        rpt_first_time=false;
        
        is_game_over=false;
        
        ct_brd_pcs_overlapped=0;
        
         to_draw_polygon=false;
         piece_being_dragged=false;
         
         // Initialize Board Hover Rows and columns
           for(int k=0;k<5;k++)
           {
               brd_hover_row[k]=-1;
               brd_hover_col[k]=-1;
           }
           
           plyr_selection_val=-1;
           is_plyr_sln_pressed=false;
           
           repaint();
        
    }
    
    
    
    void drawPiece(int piece_no)
    {   
        System.out.println("Piece no: "+ piece_no);
        draw_piece_no=piece_no;
        to_draw_polygon=true;
        
        start_x=600;
        start_y=300;
        
        // get Relative and actual subpieces centre positions
        if(piece_no==15)
            MP.update_piece_15((MP.piece_15_config));
        
        if(piece_no==5)
            MP.update_piece_5((MP.piece_5_config));
        
        
        for(int i=0;i<5;i++)
        {
            Piece_x_r[i]=MP.Piece_Current_x[draw_piece_no][i];
            Piece_y_r[i]=MP.Piece_Current_y[draw_piece_no][i];
            
            Piece_x_act[i]=(int)(start_x+ (Piece_x_r[i]*(2*r))+(r*(Math.abs(Piece_y_r[i])%2))+(a));
            
             
            Piece_x_act[i]=(int)(start_x+ (Piece_x_r[i]*(2*r))+(r*(Math.abs(Piece_y_r[i])%2)));
            Piece_y_act[i]=(int)(start_y+ (Piece_y_r[i]*(side+h)));
            
            System.out.println("Piece_x_r: "+Piece_x_r[i]+ "    Piece_y_r: "+Piece_y_r[i]);
            
        }
        
        repaint();
    }
    
    void refresh_piece_config_after_rotate(int piece_no)
    {
        System.out.println("");
         for(int i=0;i<5;i++)
        {
            Piece_x_r[i]=MP.Piece_Current_x[draw_piece_no][i];
            Piece_y_r[i]=MP.Piece_Current_y[draw_piece_no][i];
            
            Piece_x_act[i]=(int)(start_x+ (Piece_x_r[i]*(2*r))+(r*(Math.abs(Piece_y_r[i])%2)));
            Piece_y_act[i]=(int)(start_y+ (Piece_y_r[i]*(side+h)));
            
            //  System.out.println("Piece_x_r: "+Piece_x_r[i]+ "    Piece_y_r: "+Piece_y_r[i]);
            
        }
        
        repaint();
    }
    
 
   
             //  drawing on the space.
		public void paintComponent(Graphics g) 
		{

			// The object referenced by the 'g' parameter is actually		
			// an instance of the Graphics2D class.
			// If you need to use a method from the Graphics2D class,
			// you can't use this paintComponent parameter('g')
			// straight from the method. But you can cast it with
			// a new GraphicsED variable.
			// Graphics2D g2d = (Graphics2D) g;

			

			Graphics2D g2d = (Graphics2D) g;
                        
                        if(MGI.get_game_state()!=0 && MGI.get_game_state()!=1 )
                        
                        {
                            
                             super.paintComponent(g);  // Fills the panel space with background color.
                        
                            // Player Turn image
                            
                            if(MGI.is_two_plyrs)
                            {
                                
                                if(MP.no_of_pieces_placed%2==0)
                                    g.drawImage(image_turn_1, 0, 55, null);
                                else
                                    g.drawImage(image_turn_2, 00, 55, null);
                            }
                            
                            
                         // ###################  DRAWING BOARD GRID STARTS  ################################
                       
                           
			g.setColor(new Color(255,174,95));
			int margin = 1;
			g.drawRect(margin,margin, getSize().width-2*margin,getSize().height-2*margin);
                        
                        
                            init_graphics=-1;
                            //System.out.println("Board being generated");
                            


                            double start_x=30, start_y=130, x_0, y_0;

                            int hex_x_coor []= new int [6];

                            int hex_y_coor []= new int [6];
                            g2d.setPaint(Color.BLACK);
                            
                            g.fillRect((int)(start_x-10),(int)(start_y-20), (int)((a*11)+40),(int)((b*10)-(h*6)-8));
                            
                            
                            // Loop for drawing board
                            for(int i=0;i<10;i++)
                            {
                                for(int j=0;j<11;j++)
                                {
                                    if(!rpt_first_time)
                                    {
                                        
                                    
                                            double relat_disp_x= r*(i%2);

                                            x_0=start_x+j*(2*r)+relat_disp_x;
                                            y_0=start_y+i*(side+h);

                                            // 0th co-ordinate
                                            hex_x_coor[0]=(int)x_0;
                                            hex_y_coor[0]=(int)y_0;


                                            // 1th co-ordinate
                                            hex_x_coor[1]=(int)(x_0+r);
                                            hex_y_coor[1]=(int)(y_0-h);


                                            // 2th co-ordinate
                                            hex_x_coor[2]=(int)(x_0+(2*r));
                                            hex_y_coor[2]=(int)(y_0);


                                            // 3th co-ordinate
                                            hex_x_coor[3]=(int)(x_0+(2*r));
                                            hex_y_coor[3]=(int)(y_0+side);


                                            // 4th co-ordinate
                                            hex_x_coor[4]=(int)(x_0+(r));
                                            hex_y_coor[4]=(int)(y_0+side+h);


                                            // 5th co-ordinate
                                            hex_x_coor[5]=(int)(x_0);
                                            hex_y_coor[5]=(int)(y_0+side);

                                            



                                            Polygon a= new Polygon(hex_x_coor,hex_y_coor,6);

                                            MB.setBoardPiecePolygon(i, j, a);
                                            MB.set_piece_position(i, j,(int)(x_0) ,(int) (y_0-h-(side/2)));
                                            
                                           
                                    }
                                    
                                    // defining gradient
                                    GradientPaint gp = new GradientPaint(75, 75, new Color(249, 249, 249),105, 105, new Color(237, 237, 237), true);
                                    
                                     //g2d.setPaint( getColor() );
                                     g2d.setPaint(gp);

                                    g.fillPolygon (MB.getBoardPiecePolygon(i, j));

                                    g2d.setPaint(Color.BLACK);
                                    g.drawPolygon (MB.getBoardPiecePolygon(i, j));
                                    
                                    
                                  
                                } // for(int j=0;j<11;j++) 

                            } // for(int i=0;i<10;i++)

                            
                            
                            if(!rpt_first_time)
                                rpt_first_time=true;
                            
                             // ############################  CODE FOR DRAW PENTHEXES ALREADY PLACED STARTS ########################
                            
                            // for loop for all pieces
                            for(int k=0;k<22;k++)
                            {
                                // if penthex piece is to be placed
                                if(MP.is_piece_placed[k])
                                {
                                    // for loop for 5 hexagon pieces
                                    for(int m=0;m<5;m++)
                                    {
                                         g2d.setPaint(grad[m]);
                                         g.fillPolygon (MP.Piece_Final_Polygon[k][m]);

                                         g2d.setPaint(Color.BLACK);
                                         g.drawPolygon (MP.Piece_Final_Polygon[k][m]);
                                    }
                                }
                            }
                            
                            
                            
                             // ############################  CODE FOR DRAW PENTHEXES ALREADY PLACED ENDS ########################
                            
                            
                            
                        // ############################  Highlight Board pieces For Pentahex when it is being dragged STARTS ########################
                            
                            if(piece_being_dragged)
                            {
                                
                                if(highlight_board_pieces_for_penthex())
                                {
                                    // Load Polygons for matrix row and column values of board pieces being hovered
                                    for(int s=0;s<5;s++)
                                    {
                                        Polygon py = new Polygon();
                                        py=MB.getBoardPiecePolygon(brd_hover_row[s],brd_hover_col[s]);
                                        
                                        // Fill with light color
                                        g2d.setPaint(new Color(249,183,183));
                                        g.fillPolygon(py);
                                        
                                        // Draw Border
                                        g2d.setPaint(new Color(218,37,37));
                                        g.drawPolygon(py);
                                    }
                                }
                                
                            }
                        // ############################  Highlight Board pieces For Pentahex when it is being dragged ENDS ########################
			
                        
                        //############################ CODE FOR Drawing Current selected Pentahex if selected or Being Dragged STARTS
                       if(to_draw_polygon)
                       {
                           // read x and y co-ordinates of centre of sub hexagon pieces
                           for(int i=0;i<5;i++)
                           {
                               int st_x= (int)(Piece_x_act[i]-r);
                               int st_y=(int)(Piece_y_act[i]-(side/2));
                               
                               int hx_piece_x[]=new int[6];
                               int hx_piece_y[]=new int[6];
                               
                               // Definining hexagon points
                               
                               // Point 0
                               hx_piece_x[0]=st_x;
                               hx_piece_y[0]=st_y;
                               
                               // Point 1
                               hx_piece_x[1]=(int)(st_x+r);
                               hx_piece_y[1]=(int)(st_y-h);
                               
                               // Point 2
                               hx_piece_x[2]=(int)(st_x+(2*r));
                               hx_piece_y[2]=(int)(st_y);
                               
                               // Point 3
                               hx_piece_x[3]=(int)(st_x+(2*r));
                               hx_piece_y[3]=(int)(st_y+side);
                               
                               // Point 4
                               hx_piece_x[4]=(int)(st_x+r);
                               hx_piece_y[4]=(int)(st_y+side+h);
                               
                               // Point 5
                               hx_piece_x[5]=(int)(st_x);
                               hx_piece_y[5]=(int)(st_y+side);
                               
                               // Now Create Polygons
                               
                                   poly_Piece[i] = new Polygon(hx_piece_x,hx_piece_y,6);
                                  
                               
                               
                           }// for(int i=0;i<5;i++)
                           
                           
                           
                           // Now CreatePentahex Polygons to
                           for(int k=0;k<5;k++)
                           {
                               g2d.setPaint(grad[k]);
                               g.fillPolygon (poly_Piece[k]);

                               g2d.setPaint(Color.BLACK);
                               g.drawPolygon (poly_Piece[k]);
                           }
                           
                           
                           
                       } // end of if(to_draw_polygon)
                       
                       
                       
                        //############################ CODE FOR Drawing Current selected Pentahex if selected or Being Dragged ENDS 
                    
                       
                       g.drawImage(image_made_by, 0, 577, null);
                       
                       if(to_draw_polygon)
                            g.drawImage(image_instruct_drag, 0,0, null);
                       else
                           g.drawImage(image_instruct_sel, 0,0, null);
                       
                        
                      } // end of if(MGI.get_game_state()!=0 && MGI.get_game_state()!=1 )
                       
                        
                        // ################## SHOW NO OF PLAYERS DISPLAY FOR GAME RESTART  --->  STARTS ###################################
                        if(MGI.get_game_state()==1)
                        {
                            
                            
                           
                             
                                 g.drawImage( image_no_of_plyrs_bk, 0, 0, null);
                                 
                                 
                               
                                 g.drawImage( img_select_no_of_plyrs_text, 150, 230, null);
                                 
                                 if(plyr_selection_val==1)
                                    g.drawImage( img_1_plyr_hv, 470, 230, null);
                                 else
                                     g.drawImage( img_1_plyr_nm, 470, 230, null);
                                     
                                 
                                 if(plyr_selection_val==2)
                                   g.drawImage( img_2_plyr_hv, 470, 260, null);
                                 else
                                     g.drawImage( img_2_plyr_nm, 470, 260, null);
                                 
                                
                           
                            
                        }
                        
                        // ################## SHOW NO OF PLAYERS DISPLAY FOR GAME RESTART  --->  ENDS ###################################
                        
                        
                          // ################## SHOW START GAME LOGO   --->  STARTS ###################################
                        if(MGI.get_game_state()==0)
                        {
                            
                                 g.drawImage( image_start_game_bk, 0, 0, null);
                                 
                                 if(is_start_image_hvr)
                                    g.drawImage( image_start_game_txt_hv, 250, 400, null);
                                 else
                                     g.drawImage( image_start_game_txt_nm, 250, 400, null);
                                  
                        }
                        
                        // ################## SHOW START GAME LOGO   --->  ENDS ###################################
                        
                        
                       // ################## IF GAME OVER STARTS ###################################
                       
                       if(MGI.get_is_game_over())
                       {
                           BufferedImage  image_game_over_bk;
                           
                           BufferedImage  imageGame_over_txt; // 210 * 44
                           
                           BufferedImage  image_Restart_text_nml; // 390 * 34
                            BufferedImage  image_Restart_text_hvr; // 390 * 34
                           
                           
                            try 
                            {
                                 image_game_over_bk = ImageIO.read(new File("Images/GameOver_bk.png"));
                                 g.drawImage( image_game_over_bk, 0, 0, null);
                                 
                                 imageGame_over_txt=ImageIO.read(new File("Images/Game_over_text.png"));
                                 g.drawImage( imageGame_over_txt, 295, 210, null);
                                 
                                
                                    image_Restart_text_nml=ImageIO.read(new File("Images/Restart_Text_nml.png"));
                                 
                                    image_Restart_text_hvr=ImageIO.read(new File("Images/Restart_Text_hvr.png"));
                                 
                                  if(!is_rstrt_image_hvr)
                                        g.drawImage( image_Restart_text_nml, 205, 290, null);
                                 else
                                      g.drawImage( image_Restart_text_hvr, 205, 290, null);
                            }
                            catch (IOException ie) 
                            {
                                g2d.setPaint(Color.RED); 
                                g.drawString("Critical Error. Unable To load Images...", 600, 200);
                                  System.out.println("Error:"+ie.getMessage());
                            }
                          
                       }
                       
                       
                        // ################## IF GAME OVER ENDS ###################################
                       
                       
                       
                       
                       
                       // ################## IF PLAYER WON RESTARTS ###################################
                       
                       if(MGI.game_state==4)
                       {
                           BufferedImage  image_game_over_bk;
                           
                           BufferedImage  imageGame_over_txt; // 210 * 44
                           
                           BufferedImage  image_Restart_text_nml; // 390 * 34
                            BufferedImage  image_Restart_text_hvr; // 390 * 34
                           
                           
                            try 
                            {
                                 image_game_over_bk = ImageIO.read(new File("Images/GameOver_bk.png"));
                                 g.drawImage( image_game_over_bk, 0, 0, null);
                                 
                                 imageGame_over_txt=ImageIO.read(new File("Images/YOU_WON_text.png"));
                                 g.drawImage( imageGame_over_txt, 295, 210, null);
                                 
                                
                                   
                                 
                            }
                            catch (IOException ie) 
                            {
                                g2d.setPaint(Color.RED); 
                                g.drawString("Critical Error. Unable To load Images...", 600, 200);
                                  System.out.println("Error:"+ie.getMessage());
                            }
                          
                       }
                       
                       
                        // ################## IF PLAYER WON RESTARTS ###################################
                       
                       
                       
		} // end of function paintComponent(Graphics g)   
    
               
        void Set_position_Dragged_penthex()
        {
             for(int i=0;i<5;i++)
                {
                    
                    Piece_x_act[i]=(int)(start_x+ (Piece_x_r[i]*(2*r))+(r*(Math.abs(Piece_y_r[i])%2)));
                    Piece_y_act[i]=(int)(start_y+ (Piece_y_r[i]*(side+h)));
                }
        
        
        }
                 
         void chang_config_next(int butt_press_no,int rot)
         {
             MP.chang_config_next(butt_press_no, rot);
             refresh_piece_config_after_rotate(butt_press_no);
         }
  
    
          // Method to Highlight Board pieces For Pentahex when it is being dragged
         
         boolean highlight_board_pieces_for_penthex()
         {
            
             ct_brd_pcs_overlapped=0; // NOTE by the end of this function if
                                        // ct_brd_pcs_overlapped=5 then all hexagon pieces 
                                        // are overlapping unmarked pices of board
             
             // for loop for each hexagon piece of penthex
             for(int k=0;k<5;k++)
             {
                 // for loop for column of board pieces matrix
                 for(int i=0;i<10;i++)
                 {
                     // for loop for row of board pieces matrix
                     for(int j=0;j<11;j++)
                     {
                         // Check whether particular piece of penthex is actually overlapping
                         // a particular piece of board ####AND  ### if it does #### THEN ###
                         //determine whether board piece is covered or not
                         
                         
                         
                         //Check whether particular piece of penthex is actually overlapping
                         // a particular piece of board
                         
                         
                         
                         if(MB.is_piece_on_board(i, j,Piece_x_act[k], Piece_y_act[k]))
                         {
                             
                             //determine whether board piece is covered or not
                             if(!MB.get_is_brd_piece_covered(i, j))
                             { 
                                 // Store the Mtrix values of Board pieces that are peing hovered by kth hexagon piece of penthex
                                 
                                 brd_hover_row[ct_brd_pcs_overlapped]=i;
                                 brd_hover_col[ct_brd_pcs_overlapped]=j;
                                 
                                 ct_brd_pcs_overlapped=ct_brd_pcs_overlapped+1;
                                 
                                 // move on to next hexagon piece of penthex( ie end i , j for loops)
                                 i=10;
                                 j=11;
                                 
                             }
                         }
                         
                     }
                 }
             }
             
             if(ct_brd_pcs_overlapped==5)
                 return true;
             else
             {
                 for(int m=0;m<5;m++)
                 {
                     brd_hover_row[m]=-1;
                     brd_hover_col[m]=-1;
                     
                     
                 }
                 
                 return false;
             }
         }
                 
    //============================================================= mousePressed
    public void mousePressed(MouseEvent e) {
        
        mouseX = e.getX();
	mouseY = e.getY();
    
       piece_being_dragged=false;
       if((MGI.get_is_game_over()))
       {
           if(is_rstrt_image_hvr)
               is_rstrt_image_pressed=true;
       }
       else if(MGI.get_game_state()==0) // FOR START GAME LOGO DISPLAY
        {
           if(is_start_image_hvr)
           {
               is_start_image_pressed=true;
               System.out.println("Mouse Pressed: start Game Button "+ is_start_image_pressed );
           
           }
        }
        else if(MGI.get_game_state()==1) // FOR NO OF PLAYERS RESTART GAME PANEL
       {
            if(plyr_selection_val!=-1)
                is_plyr_sln_pressed=true;
            else
                is_plyr_sln_pressed=false;
           
       }
       else
       {
           if(to_draw_polygon)
            {
                for(int i=0;i<5;i++)
                {
                    if(poly_Piece[i].contains(mouseX, mouseY))
                    {
                           drag_diff_x=start_x-mouseX;
                           drag_diff_y=start_y-mouseY;
                           piece_being_dragged=true;
                           break;
                    }

                }
            }
       }
        
        
        
        
    }
    
     // MouseMotionListener
    	public void mouseDragged(MouseEvent e) 
	{ 
            if((MGI.get_is_game_over()))
            {
                if(is_rstrt_image_pressed)
                {
                    if(!is_rstrt_image_hvr)
                        is_rstrt_image_pressed=false;
                }
            }
            else if(MGI.get_game_state()==0) // FOR START GAME LOGO DISPLAY
            {
                
                if(is_start_image_pressed)
                {
                    if(!is_start_image_hvr)
                        is_start_image_pressed=false;
                    else 
                        System.out.println("Mouse Dragged:  start Game Button");
                }
            }
            else if(MGI.get_game_state()==1) // FOR NO OF PLAYERS RESTART GAME PANEL
            {
                if(is_plyr_sln_pressed)
                {
                    if(plyr_selection_val==1)
                    {
                        if(e.getX()>=470 && e.getX()<=(470+180)  && e.getY()>=230 && e.getY()<=(230+30))
                        {
                            is_plyr_sln_pressed=true;
                        }
                        else
                            is_plyr_sln_pressed=false;
                            
                    }
                    
                    
                    if(plyr_selection_val==2)
                    {
                        if(e.getX()>=470 && e.getX()<=(470+180)  && e.getY()>=260 && e.getY()<=(260+30))
                        {
                            is_plyr_sln_pressed=true;
                        }
                        else
                            is_plyr_sln_pressed=false;
                    }
                }
                repaint();
            }
            else
            {
                  if(piece_being_dragged)
                    {
                        start_x=e.getX()+drag_diff_x;
                        start_y=e.getY()+drag_diff_y;

                        Set_position_Dragged_penthex();


                        repaint();
                    }  
            }
            
            
    	}
    
    //============================================================ mouseReleased
    public void mouseReleased(MouseEvent e) 
    {
        if((MGI.get_is_game_over()))
        {
            if(is_rstrt_image_pressed)
                PD.restart_game_settings();
        }
        else if(MGI.get_game_state()==0) // FOR START GAME LOGO DISPLAY
        {
            if(is_start_image_pressed)
            {
                PD.restart_game_settings();
             
            }
            
        }
        else if(MGI.get_game_state()==1) // FOR NO OF PLAYERS RESTART GAME PANEL
        {
            if(is_plyr_sln_pressed)
            {
                if(plyr_selection_val==1)
                    MGI.is_two_plyrs=false;
                else
                    MGI.is_two_plyrs=true;
                
                
                
                MGI.set_game_state(2);
                PD.Enable_ALL_Buttons();
                
                repaint();
            }
        }
        else
        {
                   if(piece_being_dragged)
                    {
                        if(highlight_board_pieces_for_penthex())
                        {


                            // Set Final Polygon types of penthex pieces
                            for(int i=0;i <5; i++)
                            {

                                MP.is_piece_placed[draw_piece_no]=true;

                                MP.Piece_Final_Polygon[draw_piece_no][i]=MB.getBoardPiecePolygon(brd_hover_row[i],brd_hover_col[i]);

                                PD.enable_buttons();

                                PD.Piece_button[draw_piece_no].setEnabled(false);
                                MB.set_brd_piece_covered(brd_hover_row[i], brd_hover_col[i]);

                                MP.no_of_pieces_placed++;
                                
                                if(MP.no_of_pieces_placed==22)
                                    MGI.game_state=4;

                                

                            } // end of for(int i=0;i <5; i++)

                               PD.after_penthex_placed();

                             to_draw_polygon=false;
                             draw_piece_no=-1;


                        } // end of if(highlight_board_pieces_for_penthex())
                        else
                        {
                            // reset piece being dragged to start positions
                            start_x=600;
                            start_y=300;
                            drag_diff_x=0;
                            drag_diff_y=0;
                            Set_position_Dragged_penthex();
                        }

                        repaint();

                    } // end of if(piece_being_dragged) 
                   
                   piece_being_dragged=false;
        }
        
        
        
    }
    
   
    public void mouseMoved  (MouseEvent e) {
        
        if(MGI.get_is_game_over())
        {
            
            if(e.getX()>=205 && e.getX()<=(205+390) && e.getY()>=290 && e.getY()<=(290+34))
            {
                
                is_rstrt_image_hvr=true;
            }
            else
            {
                is_rstrt_image_hvr=false;
            }
            
            repaint();
        }
        else if(MGI.get_game_state()==0) // FOR START GAME LOGO DISPLAY
        {
            // One player hover check
            if(e.getX()>=250 && e.getX()<=(250+305)  && e.getY()>=400 && e.getY()<=(400+50))
            {
                is_start_image_hvr=true;
            }
            
            else
            {
                is_start_image_hvr=false;
            }
            repaint();
        }
        else if(MGI.get_game_state()==1) // FOR NO OF PLAYERS RESTART GAME PANEL
        {
            // One player hover check
            if(e.getX()>=470 && e.getX()<=(470+180)  && e.getY()>=230 && e.getY()<=(230+30))
            {
                plyr_selection_val=1;
            }
            // Two player selection hover check
            else if(e.getX()>=470 && e.getX()<=(470+180)  && e.getY()>260 && e.getY()<=(260+30))
            {
                
                plyr_selection_val=2;
            }
            else
            {
                plyr_selection_val=-1;
            }
            repaint();
        }
        
        else
        {
            
        }
        
    }
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited (MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}
}