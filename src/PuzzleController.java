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
import javax.swing.*;

////////////////////////////////////////////////////////////////////// PuzzleController
public class PuzzleController extends JApplet {
    //=================================================================== fields
    Viewer_Canvas _canvas;
    ModelPieces MP= new ModelPieces();
    ModelBoard MB=new ModelBoard();
    ModelGameInfo MGI= new ModelGameInfo();
    
    JPanel jpanel_button, jpanel_rotate,jpanel_de, jpanel_combined;
    
    static JButton Piece_button[]=new JButton[22];
    
    // Rotation Buttons
    JButton Rot_1_button= new JButton();
    JButton Rot_2_button= new JButton();
    
    
    // Deselect Piece Button
    JButton Deselect_button= new JButton();
    
    
     int butt_press_no;
    
    // Set this to true when a piece button is pressed
    boolean is_piece_selected;
    
    boolean tmp_chk_brd_matrix[][]= new boolean[10][11];
    
    //===================================================================== main
    public static void main(String[] args) {
        //... Create and initialize the applet.
        JApplet theApplet = new PuzzleController(1);
        
        //... Create a window (JFrame) and make applet the content pane.
        JFrame window = new JFrame();
        window.setContentPane(theApplet);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("Penta-Hex Puzzle");
        window.pack();
        window.setLocationRelativeTo(null); // Center window.
        window.setResizable(false);
        // System.out.println(theApplet.getSize()); // to get applet size.
        window.setVisible(true);            // Make the window visible.
    }
    
    //============================================================== constructor
     
     
    public PuzzleController(int val)
    {
        if(val==1)
            _canvas = new Viewer_Canvas(MP,MB,MGI,this);
       
       is_piece_selected=false;
       
       Deselect_button.setEnabled(false);
       
        //... ARRAY OF 22 Buttons ...................
       
        
        for(int i=0;i<22;i++)
        {
            butt_press_no=i;
            
            Piece_button[i] = new JButton();
            Piece_button[i].setPreferredSize(new Dimension(67, 70));
            //Piece_button[i].setBorder(null);
            
           
            
            // Setting Buttons normal Icon
            Piece_button[i].setIcon(new ImageIcon("Images/Piece_normal/P" + Integer.toString(i+1)+ ".png"));      


            // Button Rollovr effects
            Piece_button[i].setRolloverIcon(new ImageIcon("Images/Piece_hover/P" + Integer.toString(i+1)+ ".png"));      

            // Button Pressed effects
            Piece_button[i].setPressedIcon(new ImageIcon("Images/Piece_selected/P" + Integer.toString(i+1)+ ".png")); 
            
            // Button Disabled Effects
            Piece_button[i].setDisabledIcon(new ImageIcon("Images/Piece_deselect/P" + Integer.toString(i+1)+ ".png"));
            
            // Piece Buttons Action Listener
            Piece_button[i].setActionCommand("PRESSED_BUTTON_"+i);
            
            // Disable all Piece Buttons initially
            Piece_button[i].setEnabled(false);
            
            
            Piece_button[i].addActionListener(new ActionListener() {
 
             public void actionPerformed(ActionEvent e)
                        {
                           String action = e.getActionCommand();
                        String prefix = "PRESSED_BUTTON_";

                        if(action.startsWith(prefix)) 
                        {
                           String buttonNumberAsString = action.substring(prefix.length());
                           int buttonIndex = new Integer(buttonNumberAsString).intValue();
                           butt_press_no=buttonIndex;
                           
                            if(is_piece_selected==false)
                            {

                                    disable_other_buttons(butt_press_no);


                                    _canvas.drawPiece(butt_press_no);

                                    // Enable Rotation Buttons
                                    Rot_1_button.setEnabled(true);
                                    Rot_2_button.setEnabled(true);
                                    
                                    Deselect_button.setEnabled(true);
                            }
                           
                           
                        }  

                    }// end of public void actionPerformed(ActionEvent e)
            });
        
        }// end of for(int i=0;i<22;i++)
        
            
        
        //... Create Button Jpanel ............................        
        jpanel_button = new JPanel();
        
        
        // .... Set Button Jpanel Border
        jpanel_button.setBorder(BorderFactory.createTitledBorder("Pieces"));
        
        
        // .... Set Button Jpanel Size
        jpanel_button.setPreferredSize(new Dimension(67*8, 245));
        
         // .... Set Button Jpanel Layout as GridBagLayout
        jpanel_button.setLayout(new GridBagLayout());
        
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.NORTHWEST;
        
        //c.gridwidth = GridBagConstraints.REMAINDER;
        
        
        // ADD BUTTONS ROW WISE
       // First Row Of buttons
        for(int k=0;k<8;k++)
        {   
            c.fill = GridBagConstraints.HORIZONTAL;
            c.weightx = 0.5;
            c.gridy = 0;
            c.gridx =k;
            jpanel_button.add(Piece_button[k], c);
            
        }
        
        
        // Second Row Of buttons
        for(int k=8;k<15;k++)
        {   
            c.fill = GridBagConstraints.HORIZONTAL;
            c.weightx = 0.5;
            c.gridy = 1;
            c.gridx =k-8;
            jpanel_button.add(Piece_button[k], c);
            
        }
        
         // Third Row Of buttons
        for(int k=15;k<22;k++)
        {   
            c.fill = GridBagConstraints.HORIZONTAL;
            c.weightx = 0.5;
            c.gridy = 2;
            c.gridx =k-15;
            jpanel_button.add(Piece_button[k], c);
            
        }
        
        
        
        // ......  Rotate Buttons .....
        
        Rot_1_button.setPreferredSize(new Dimension(90, 180));
        Rot_1_button.setIcon(new ImageIcon("Images/Rotate1.png"));
        Rot_1_button.setRolloverIcon(new ImageIcon("Images/Rotate1_2.png"));
        Rot_1_button.setDisabledIcon(new ImageIcon("Images/Rotate1_des.png"));
        Rot_1_button.setBorder(null);
        Rot_1_button.setBorderPainted(false);
        Rot_1_button.setMargin(new Insets(0, 0, 0, 0));
        Rot_1_button.setFocusPainted(false);
        Rot_1_button.setContentAreaFilled(false);
        Rot_1_button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        Rot_1_button.setEnabled(false);
        
        // Adding Acion Listener To Rotate Button
        Rot_1_button.addActionListener(new ActionListener() {
 
             public void actionPerformed(ActionEvent e)
                        {
                            _canvas.chang_config_next(butt_press_no,1);
                           
                        }
        });
        
        
        
        
        Rot_2_button.setIcon(new ImageIcon("Images/Rotate2.png"));
        Rot_2_button.setRolloverIcon(new ImageIcon("Images/Rotate2_2.png"));
        Rot_2_button.setDisabledIcon(new ImageIcon("Images/Rotate2_des.png"));
        Rot_2_button.setPreferredSize(new Dimension(90, 80));
        Rot_2_button.setBorder(null);
        Rot_2_button.setBorderPainted(false);
        Rot_2_button.setMargin(new Insets(0, 0, 0, 0));
        Rot_2_button.setFocusPainted(false);
        Rot_2_button.setContentAreaFilled(false);
        Rot_2_button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        Rot_2_button.setEnabled(false);
        
        
         // Adding Acion Listener To Rotate Button
        Rot_2_button.addActionListener(new ActionListener() {
 
             public void actionPerformed(ActionEvent e)
                        {
                            _canvas.chang_config_next(butt_press_no,-1);
                            
                        }
        });
        
        //... Create Rotate Buttons Jpanel ............................        
        jpanel_rotate = new JPanel();
        
        
        // .... Set Button Jpanel Border
        jpanel_rotate.setBorder(BorderFactory.createTitledBorder("Rotate"));
        
        
        // .... Set Button Jpanel Size
        jpanel_rotate.setPreferredSize(new Dimension(150, 110));
        
         // .... Set Button Jpanel Layout as GridBagLayout
        jpanel_rotate.setLayout(new GridBagLayout());
        
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.NORTHWEST;
        c.weightx = 0.5;
       
        c.gridy = 0;
        c.gridx =0;
        //c.insets=new Insets(0,0,0,10);
        jpanel_rotate.add(Rot_2_button, c);
        
        c.gridy = 0;
        c.gridx =1;
        c.insets=new Insets(0,10,0,0);
        jpanel_rotate.add(Rot_1_button, c);
         
        
        Deselect_button.setText("Deselect Piece");
        //Deselect_button.setIcon(new ImageIcon("Images/butt_bk.png"));
        Deselect_button.setPreferredSize(new Dimension(50, 50));
        //Deselect_button.setBorder(null);
        //Deselect_button.setBorderPainted(false);
        Deselect_button.setMargin(new Insets(0, 0, 0, 0));
        Deselect_button.setFocusPainted(false);
        //Deselect_button.setContentAreaFilled(false);
        Deselect_button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        
        // Adding ActionListener to Deselect_button
        Deselect_button.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e)
            {
                enable_buttons();
                _canvas.to_draw_polygon=false;
                _canvas.repaint();
            }
        }); 
        
        
        
        
        
         //... Create Deselect Buttons Jpanel ............................        
        jpanel_de = new JPanel();
        
        
        // .... Set Button Jpanel Border
        jpanel_de.setBorder(BorderFactory.createTitledBorder("Deselect Piece"));
        
        
        // .... Set Button Jpanel Size
        jpanel_de.setPreferredSize(new Dimension(150, 110));
        
         // .... Set Button Jpanel Layout as GridBagLayout
        jpanel_de.setLayout(new GridBagLayout());
        
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.NORTHWEST;
        c.weightx = 0.5;
       
        c.gridy = 0;
        c.gridx =0;
        c.insets=new Insets(5,10,0,10);
        jpanel_de.add(Deselect_button, c);
        
        
        
          
        // .. Panel For Combining Pieces Jpanel, Rotation Buttons JPanel, Deselect Button JPanel
       jpanel_combined = new JPanel();
       jpanel_combined.setLayout(new GridBagLayout());
        
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.NORTHWEST;
        
        c.insets=new Insets(0,5,0,5);
        c.weightx = 0.5;
        c.gridx =0;
        c.gridy = 0;
        c.gridheight=2;
        jpanel_combined.add(jpanel_button, c);
        
        
        c.insets=new Insets(0,5,0,5);
        c.weightx = 0.5;
        c.gridx =1;
        c.gridy = 0;
        jpanel_combined.add(jpanel_rotate, c);
        
        
        c.insets=new Insets(120,0,0,0);
        c.weightx = 0.5;
        c.gridx =1;
        c.gridy = 1;
        jpanel_combined.add(jpanel_de, c);
       
        if(val==1)
        {
            //... layout the applet ...........................................
            setLayout(new BorderLayout());
            add(jpanel_combined, BorderLayout.NORTH);
            add(_canvas    , BorderLayout.CENTER);
        }
    }// END OF CLASS CONSTRUCTOR
    
    
    // FUNCTION TO CALL WHEN GAME IS RESTARTED
    void restart_game_settings()
    {
        
        MP.restart_game_settings();
        MB.restart_game_settings();
        MGI.restart_game_settings();
        MGI.set_is_game_over(false);
        _canvas.restart_game_settings();
        
        
        
        is_piece_selected=false;
        
        Deselect_button.setEnabled(false);
       
        //... ARRAY OF 22 Buttons ...................
       
        
        for(int i=0;i<22;i++)
        {
            butt_press_no=i;
            
            
            // Button Disabled Effects
            Piece_button[i].setDisabledIcon(new ImageIcon("Images/Piece_deselect/P" + Integer.toString(i+1)+ ".png"));
            
            
        
        }// end of for(int i=0;i<22;i++)
    }
 
    
  // Disable all other buttons when a piece button is pressed 
    void disable_other_buttons(int b) 
    {
        
            for(int i=0;i<22;i++)
            {
                if(i!=b)
                    Piece_button[i].setEnabled(false);
            }
           
        
    }
    
    // Enable buttons after piece is placed on board or deselect piece button is pressed
    void enable_buttons() 
    {
        Deselect_button.setEnabled(false);
        
        for(int i=0;i<22;i++)
        {
            if(MP.is_piece_placed[i]==false)
                Piece_button[i].setEnabled(true);
        }
        is_piece_selected=false;
        butt_press_no=-1;
        
        // Disble Rotation Buttons
                Rot_1_button.setEnabled(false);
                Rot_2_button.setEnabled(false);
    }
    
    
    
    // Enable All Buttons
    void Enable_ALL_Buttons()
    {
        for(int i=0;i<22;i++)
        {
            
                Piece_button[i].setEnabled(true);
        }
        
    }
    
    // Function to be called after placing of each pent-hex piece
    // to check whether game is over or not
    void after_penthex_placed()
    {
        
        // Temporary Mark board matrix
        
        
        
        for(int u=0;u<10;u++)
        {
            for(int v=0;v<11;v++)
            {
                tmp_chk_brd_matrix[u][v]=false;
            }
        }
        
        int i=-1,j=-1;
        
        
        boolean c_b_f=true;
        
        for(i=0;i <10; i++)
        {
            
            for(j=0;j<11; j++)
            {
                c_b_f=true;
                
                
                if(j==10)
                {
                    if(MB.get_is_brd_piece_covered(i, j)==false && MB.get_is_brd_piece_covered(i, (j-1))==true && tmp_chk_brd_matrix[i][j]==false)
                    //if(MB.get_is_brd_piece_covered(i, j)==false && MB.get_is_brd_piece_covered(i, (j-1))==true)
                    {
                        System.out.println("\n\nUnmarked Block Focus____1 i: "+(i)+ "    j: "+j);
                            c_b_f= check_blocked_config(i,j,false);
                    }
                }
                 
              
                  
                 /*
                if(j==0)
                {
                    if(MB.get_is_brd_piece_covered(i, j)==false && MB.get_is_brd_piece_covered(i, j+1)==true && tmp_chk_brd_matrix[i][j]==false)
                    {
                         System.out.println("\n\nMarked Block Focus i: "+(i)+ "    j: "+j);


                            c_b_f= check_blocked_config(i,j);
                    }
                }
                  * */
                  
                if(j!=0 && j!=10)
                {
                
                    if(MB.get_is_brd_piece_covered(i, j)==false && MB.get_is_brd_piece_covered(i, j-1)==true && tmp_chk_brd_matrix[i][j]==false)
                    {
                        int new_pos=-1;
                        int k;
                        
                        System.out.println("\nTell me i: "+i+"    j: "+ j);
                        for(k=j;k<=10;k++ )
                        {
                            if(MB.get_is_brd_piece_covered(i, k)==true && tmp_chk_brd_matrix[i][k-1]==false)
                            {
                                new_pos=k-1;
                                k=13;
                                System.out.println("k is 13");
                            }
                        }
                        if(k==11)
                        {
                            System.out.println("k is 11");
                            if(MB.get_is_brd_piece_covered(i, 10)==false && tmp_chk_brd_matrix[i][10]==false)
                                new_pos=10;
                            else
                                new_pos=j;
                        }
                        
                        
                        
                         System.out.println("\n\nUnmarked Block Focus___2 i: "+(i)+ "    j: "+new_pos);
                         
                         
                                c_b_f= check_blocked_config(i,new_pos,false);
                         
                    }
                    
                    
                    else if(MB.get_is_brd_piece_covered(i, j)==true && MB.get_is_brd_piece_covered(i, j-1)==false && tmp_chk_brd_matrix[i][j-1]==false)               
                    {
                        System.out.println("\n\nUnmarked Block Focus___2_2 i: "+(i)+ "    j: "+(j-1));


                            c_b_f= check_blocked_config(i,j-1,false);
                    }
                    else
                    {
                        
                    }
                     
                     
                }
                
                if(c_b_f==false)
                {
                    // GAME OVER CODE
                    
                    game_over_settings2();
                    System.out.println("Game over bcoz of i: "+(i)+ "    j: "+j);
                    
                    i=10;
                    j=11;
                    //repaint();
                }
                
                
                
            }
        }
        
       
        
    }
    
    // Function that aids in determining whether game is over or not
    boolean check_blocked_config(int i, int j, boolean call_within)
    {
       
        
        int cncted_blks_x[]= new int[6];
        int cncted_blks_y[]=new int[6];
        
        
        int blocks_in_board=0;
        
        int blocks_in_this_row=0;
        
        
        int ref_block_x_r_up=i;
        int ref_block_x_l_up=-1;
        
        int ref_block_x_r_down=i;
        int ref_block_x_l_down=-1;
        
        // ************** GO TO SAME  LINE   **********************
        
        for(int m=j;m>=0;m--)
        {
            if(MB.get_is_brd_piece_covered(i, m)==false)
            {
                ref_block_x_l_up=ref_block_x_l_down=m;
                
                blocks_in_this_row++;
                
                
                cncted_blks_x[blocks_in_board]=i;
                cncted_blks_y[blocks_in_board]=m;
                
                blocks_in_board++;
                        
                
                if(blocks_in_board>5)
                {
                    for(int q=0;q<5;q++)
                    {
                        tmp_chk_brd_matrix[cncted_blks_x[q]][cncted_blks_y[q]]= true;
                        
                    }
                    return true;
                }
            }
            else
                break;
        }// end of for(int m=j;m>=0;m--)
        
        
        ref_block_x_r_up=j;
        ref_block_x_r_down=j;
        
        
        // Setting w.r.t parent Left and right Blocks for moving up 
        // direction and moving down direction and 
        
        
        // Setting left referenced block
        if(ref_block_x_l_up!=0)
        {
            if((i%2)==0)
            {
                ref_block_x_l_up=ref_block_x_l_down=ref_block_x_l_up-1;
            }
        }
        
        // Setting right referenced block
        if(ref_block_x_r_up!=10)
        {
            if((i%2)==1)
            {
                ref_block_x_r_up=ref_block_x_r_down=ref_block_x_r_up+1;
            }
        }
        
       
        
         //  ************  GO UPWARD DIRECTION STARTS *************
        int direction=0 ;
        
       for(int up=1;up<5;up++)
       {
           if(i-up<0)
               break;
           
           blocks_in_this_row=0;
           
           
           
           // ***** PRECHECK UPWARD ROW STARTS ******
           boolean precheck_cond=false;
           
           for(int k=ref_block_x_l_up;k<=ref_block_x_r_up;k++)
           {
               if(MB.get_is_brd_piece_covered(i-up, k)==false)
               {
                   k=ref_block_x_r_up+1;
                   precheck_cond=true;
               }
           }
           
           if(precheck_cond==false)
               break;
           
                   
           // ***** PRECHECK UPWARD ROW ENDS ******
                   
           
           // Print Left and Right Reference blocks for reference
           System.out.println("UP  "+ up+ " -  Before setting left and right -  left: "+ref_block_x_l_up+ "   right: "+ ref_block_x_r_up );
           
           
           // @@@  SET LEFT REFERENCE BLOCK  @@@
           
           // if the current reference block is unmarked continue moving left until marked one is encountered
           // ELSE move right(direction=1) and stop at first block which is unmarked
           
           if(MB.get_is_brd_piece_covered(i-up,ref_block_x_l_up)==false)
               direction=-1;
           else 
               direction =1;
           
           
           System.out.println("UP  "+ up+ " -  Left Direction:  "+ direction );
           boolean a=true;
          
           while(a)
           {
               
              
               // if the current reference block is unmarked continue moving left until marked one is encountered
              if(direction==-1)
              {
                  if((ref_block_x_l_up - 1)>=0)
                  {
                      if(MB.get_is_brd_piece_covered(i-up,ref_block_x_l_up - 1)==false)
                          ref_block_x_l_up-=1;
                      else
                          a=false;
                  }
                  else
                      a=false;
              }
               
              // ELSE move right(direction=1) and stop at first block which is unmarked
              if(direction==1)
              {
                  if((ref_block_x_l_up + 1)<=10)
                  {
                      if(MB.get_is_brd_piece_covered(i-up,ref_block_x_l_up + 1)==true)
                          ref_block_x_l_up+=1;
                      else
                      {
                          a=false;
                          ref_block_x_l_up+=1;
                      }
                  }
                  else
                      a=false;
              }
             
               
           };
           
           
           direction=0;
           
            // @@@  SET RIGHT REFERENCE BLOCK  @@@
           
           // if the current reference block is unmarked continue moving right untill marked one is encountered
           // ELSE move left(direction=-1) and stop at first block which is unmarked
           
           
           
           
           
           if(MB.get_is_brd_piece_covered(i-up,ref_block_x_r_up)==false)
               direction=1;
           else
               direction=-1;
           
           
           
           boolean b=true;
           
       
           while(b)
           {
               
               
               // if the current reference block is unmarked continue moving right untill marked one is encountered
              if(direction==1)
              {
                  if((ref_block_x_r_up + 1)<=10)
                  {
                      if(MB.get_is_brd_piece_covered(i-up,ref_block_x_r_up + 1)==false)
                          ref_block_x_r_up+=1;
                      else
                          b=false;
                  }
                  else
                      b=false;
              }
               
              // ELSE move left(direction=-1) and stop at first block which is unmarked
              if(direction==-1)
              {
                  if((ref_block_x_r_up - 1)>=0)
                  {
                      if(MB.get_is_brd_piece_covered(i-up,ref_block_x_r_up - 1)==true)
                          ref_block_x_r_up-=1;
                      else
                      {
                          b=false;
                          ref_block_x_r_up-=1;
                      }
                  }
                  else
                      b=false;
              }
               
              
               
               
               
           };
           
           
           // Print Left and Right Reference blocks for reference
           System.out.println("UP "+ up+ "-  left: "+ref_block_x_l_up+ "   right: "+ ref_block_x_r_up );
           
          
           
           // NOW COUNT UNMARKED ELEMENTS BETWEEN THEM
           
           for(int k=ref_block_x_l_up;k<=ref_block_x_r_up;k++)
           {
               if(MB.get_is_brd_piece_covered(i-up, k)==false)
               {
                   // CHECK EXCEPTIONAL SAME ROW PROBLEM
                   boolean excp_prob_bool=false;
                   
                   if(up==1 && call_within==false)
                   {
                               excp_prob_bool=check_blocked_config(i-up,k,true);

                           if(excp_prob_bool)
                               return true;
                   }
                   
                   blocks_in_this_row++;
                   
                   
                    cncted_blks_x[blocks_in_board]=i-up;
                    cncted_blks_y[blocks_in_board]=k;
                   
                   blocks_in_board++;
                   
                   
                   if(blocks_in_board>5)
                   {
                        for(int q=0;q<5;q++)
                        {
                            tmp_chk_brd_matrix[cncted_blks_x[q]][cncted_blks_y[q]]= true;

                        }
                        return true;
                   }
                   
               }
           }// end of for loop of k
           
           // Print no of elements found
           System.out.println("Up "+ up + " -   No of unmarked blocks: "+ blocks_in_this_row);
           
           
           // COUNT ENDS
           
           // SET LEFT AND RIGHT REFERENCE BLOCKS FOR NEXT UPWARD ROW
           
           // Setting left referenced block
            if(ref_block_x_l_up!=0)
            {
                if(((i-up)%2)==0)
                {
                    ref_block_x_l_up--;
                }
            }

            // Setting right referenced block
            if(ref_block_x_r_up!=10)
            {
                if(((i-up)%2)==1)
                {
                    ref_block_x_r_up++;
                }
            }
           
            System.out.println("Up "+up+ " - Left and rigt after up end ...  Left: "+ ref_block_x_l_up+ "  right: "+ref_block_x_r_up+"\n");
            
            // if no elemnts found in this row end upward look through
            
            if(blocks_in_this_row==0)
                up=5;
            
           
       } // end of for(int up=1;up<5;up++)
        
        
        //  ************  GO UPWARD DIRECTION ENDS *************
        
        //  ************  GO DOWNWARD DIRECTION STARTS *************
        
       
       
       direction=0 ;
        
       for(int down=1;down<5;down++)
       {
           if(i+down>9)
               break;
           
           
           // ***** PRECHECK DOWNWARD ROW STARTS ******
           boolean precheck_cond=false;
           
           for(int k=ref_block_x_l_down;k<=ref_block_x_r_down;k++)
           {
               if(MB.get_is_brd_piece_covered(i+down, k)==false)
               {
                   k=ref_block_x_r_down+1;
                   precheck_cond=true;
               }
           }
           
           if(precheck_cond==false)
               break;
           
                   
           // ***** PRECHECK DOWNWARD ROW ENDS ******
           
           blocks_in_this_row=0;
           
           // @@@  SET LEFT REFERENCE BLOCK  @@@
           
           // if the current reference block is unmarked continue moving left until marked one is encountered
           // ELSE move right(direction=1) and stop at first block which is unmarked
           
           if(MB.get_is_brd_piece_covered(i+down,ref_block_x_l_down)==false)
               direction=-1;
           else 
               direction =1;
           
           boolean a=true;
           
           while(a)
           {
               
              
               // if the current reference block is unmarked continue moving left until marked one is encountered
              if(direction==-1)
              {
                  if((ref_block_x_l_down - 1)>=0)
                  {
                      if(MB.get_is_brd_piece_covered(i+down,ref_block_x_l_down - 1)==false)
                          ref_block_x_l_down-=1;
                      else
                          a=false;
                  }
                  else
                      a=false;
              }
               
              // ELSE move right(direction=1) and stop at first block which is unmarked
              if(direction==1)
              {
                  if((ref_block_x_l_down + 1)<=10)
                  {
                      if(MB.get_is_brd_piece_covered(i+down,ref_block_x_l_down + 1)==true)
                          ref_block_x_l_down+=1;
                      else
                      {
                          a=false;
                          ref_block_x_l_down+=1;
                      }
                  }
                  else
                      a=false;
              }
             
               
           };
           
           
           direction=0;
           
            // @@@  SET RIGHT REFERENCE BLOCK  @@@
           
           // if the current reference block is unmarked continue moving right untill marked one is encountered
           // ELSE move left(direction=-1) and stop at first block which is unmarked
           
           
           
           
           
           if(MB.get_is_brd_piece_covered(i+down,ref_block_x_r_down)==false)
               direction=1;
           else
               direction=-1;
           
           
           
           boolean b=true;
              
        
           while(b)
           {
               
               
               // if the current reference block is unmarked continue moving right untill marked one is encountered
              if(direction==1)
              {
                  if((ref_block_x_r_down + 1)<=10)
                  {
                      if(MB.get_is_brd_piece_covered(i+down,ref_block_x_r_down + 1)==false)
                          ref_block_x_r_down+=1;
                      else
                          b=false;
                  }
                  else
                      b=false;
              }
               
              // ELSE move left(direction=-1) and stop at first block which is unmarked
              if(direction==-1)
              {
                  if((ref_block_x_r_down - 1)>=0)
                  {
                      if(MB.get_is_brd_piece_covered(i+down,ref_block_x_r_down - 1)==true)
                          ref_block_x_r_down-=1;
                      else
                      {
                          b=false;
                          ref_block_x_r_down-=1;
                      }
                  }
                  else
                      b=false;
              }
               
            
           };
           
           
           // Print Left and Right blocks for reference
           System.out.println("DOWN "+ down+ "-  left: "+ref_block_x_l_down+ "   right: "+ ref_block_x_r_down );
           
           
            
           
           
           
           // NOW COUNT UNMARKED ELEMENTS BETWEEN THEM
           
           for(int k=ref_block_x_l_down;k<=ref_block_x_r_down;k++)
           {
               if(MB.get_is_brd_piece_covered(i+down, k)==false)
               {
                   
                   // CHECK EXCEPTIONAL SAME ROW PROBLEM
                   boolean excp_prob_bool=false;
                   
                   if(down==1 && call_within==false)
                   {
                               excp_prob_bool=check_blocked_config(i+down,k,true);

                           if(excp_prob_bool)
                               return true;
                   }
                   
                   
                   blocks_in_this_row++;
                   
                   cncted_blks_x[blocks_in_board]=i+down;
                   cncted_blks_y[blocks_in_board]=k;
                    
                    
                   blocks_in_board++;
                   
                   
                   if(blocks_in_board>5)
                   {
                        for(int q=0;q<5;q++)
                        {
                            tmp_chk_brd_matrix[cncted_blks_x[q]][cncted_blks_y[q]]= true;

                        }
                         return true; 
                       
                   }
                   
               }
           }// end of for loop of k
           
           // Print no of elements found
           System.out.println("Down "+ down + " -   No of unmarked blocks: "+ blocks_in_this_row);
           
           
           // COUNT ENDS
           
           // SET LEFT AND RIGHT REFERENCE BLOCKS FOR NEXT DOWNWARD ROW
           
           // Setting left referenced block
            if(ref_block_x_l_down!=0)
            {
                if(((i+down)%2)==0)
                {
                    ref_block_x_l_down--;
                }
            }

            // Setting right referenced block
            if(ref_block_x_r_down!=10)
            {
                if(((i+down)%2)==1)
                {
                    ref_block_x_r_down++;
                }
            }
           
            System.out.println("Down "+down+ " - Left and rigt after down end ...  Left: "+ ref_block_x_l_down+ "  right: "+ref_block_x_r_down+"\n");
            
            // if no elemnts found in this row end downward look through
            
            if(blocks_in_this_row==0)
                down=5;
            
           
       } // end of for(int down=1;down<5;down++)
       
        
        //  ************  GO DOWNWARD DIRECTION ENDS *************
        
       if(blocks_in_board==5)
           return true;
        
        return false;
        
    }// end of function boolean check_blocked_config(int i, int j)
    
    
    
    
    // Function to be called when game gets over
    void game_over_settings2()
    {
        MGI.set_game_state(3);
        MGI.set_is_game_over(true);
        repaint();
        
        System.out.println("\nGAME OVER FN CALLED !!!!\n");
        for(int i=0;i<22;i++)
        {
            Piece_button[i].setEnabled(false);
        }
    }
    
}