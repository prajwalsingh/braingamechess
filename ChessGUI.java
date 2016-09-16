import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
class ChessGUI extends JFrame
{
 public JPanel    homePanel,gamePanel,topPanel,bottomPanel,player1Panel,player2Panel,subPanel1,subPanel1_1,subPanel2,subPanel2_2;
 public JLabel    boxLabel[]=new JLabel[64],brownDie[]=new JLabel[16],whiteDie[]=new JLabel[16];
 public JLabel    showMessages,headLabel,player1Score,player2Score,blank;
 public ImageIcon brownPlayer[]=new ImageIcon[16],brownCopy[]=new ImageIcon[16],brownDieImage[]=new ImageIcon[16];
 public ImageIcon whitePlayer[]=new ImageIcon[16],whiteCopy[]=new ImageIcon[16],whiteDieImage[]=new ImageIcon[16];
 public ImageIcon img=null,saveCurrentImage=null;
 public int       enable=0,index=0,brownCount=0,whiteCount=0,brownDieIndex[]=new int[16],whiteDieIndex[]=new int[16],DIE=0,dieIndexB=-1,dieIndexW=-1,optValue=0;
 public int       enableIndex=0,totalMoveB=0,totalMoveW=0,piecesLeftB=16,piecesLeftW=16,moveDone=0;  
 public  Color    backColor=null,kingColor;
 public String    player1Details="[ Player 1  Total Moves : 0 Pieces Left : 16 ]",player2Details="[ Player 2  Total Moves : 0  Pieces Left : 16 ]";
 public String    piecesNameB[]=new String[16],piecesNameW[]=new String[16];
 public int       traceIndex[]=new int[100],traceCount=0,saveHIndex[]=new int[300],saveHCount=0,saveHImageCount=0,wKingPosition=60,bKingPosition=3;
 public JButton   undo;
 public ImageIcon saveHImage[]=new ImageIcon[300],killedPieces[]=new ImageIcon[300]; 
 public int       saveCurrent=0,countMoves=0,done=0,killedCount=0,playerChance=1,playerCArr[]=new int[300],playerCCount=0,kingFocus=0; 
 
  ChessGUI()
  {
     setIconImage((Image)Toolkit.getDefaultToolkit().getImage(getClass().getResource("gamelogo.png"))); 
     addPanel();
     addLabel(); 
     addButton();
     setPieces();
     addImageIcon();
     addAction();
  }
  
  public void addPanel()
  {
     homePanel=new JPanel();
     homePanel.setBackground(Color.WHITE);
     homePanel.setLayout(new BorderLayout());
     add(homePanel);
     
     topPanel=new JPanel(new CardLayout());
     topPanel.setPreferredSize(new Dimension(JFrame.WIDTH,80));
     topPanel.setBackground(new Color(36,106,119));
     homePanel.add(topPanel,BorderLayout.NORTH);
     
     bottomPanel=new JPanel();
     bottomPanel.setPreferredSize(new Dimension(JFrame.WIDTH,80));
     bottomPanel.setBackground(/*new Color(36,106,119)*/Color.BLACK);
     homePanel.add(bottomPanel,BorderLayout.SOUTH);
     
     player1Panel=new JPanel();
     player1Panel.setPreferredSize(new Dimension(150,JFrame.HEIGHT));
     player1Panel.setBackground(/*new Color(36,106,119)*/Color.WHITE);
         subPanel1=new JPanel(new GridLayout(2,1,10,0));
            JLabel l1=new JLabel("Player 1");
            l1.setHorizontalAlignment(SwingConstants.CENTER);
            l1.setForeground(Color.WHITE);
            l1.setOpaque(true);
            l1.setBackground(new Color(147,55,57));
            l1.setPreferredSize(new Dimension(player1Panel.getWidth(),30));
            l1.setBorder(BorderFactory.createLineBorder(Color.WHITE,2));
         subPanel1.add(l1);
            subPanel1_1=new JPanel(new FlowLayout());
            subPanel1_1.setBackground(/*new Color(232,87,90)*/Color.BLACK);
         subPanel1.add(subPanel1_1);   
     player1Panel.setLayout(new GridLayout(2,1));
     player1Panel.add(subPanel1);
     subPanel1.setBackground(new Color(36,106,119));
     homePanel.add(player1Panel,BorderLayout.WEST);
     
     player2Panel=new JPanel();
     player2Panel.setPreferredSize(new Dimension(150,JFrame.HEIGHT));
     player2Panel.setBackground(/*new Color(36,106,119)*/Color.WHITE);
       subPanel2=new JPanel(new GridLayout(2,1));
            JLabel l2=new JLabel("PLAYER 2");
            l2.setHorizontalAlignment(SwingConstants.CENTER);
            l2.setForeground(Color.WHITE);
            l2.setOpaque(true);
            l2.setBackground(new Color(225,225,0));
            l2.setPreferredSize(new Dimension(player1Panel.getWidth(),30));
            l2.setBorder(BorderFactory.createLineBorder(Color.WHITE,2));
         subPanel2.add(l2);
            subPanel2_2=new JPanel(new FlowLayout());
            subPanel2_2.setBackground(/*new Color(232,87,90)*/Color.BLACK);
         subPanel2.add(subPanel2_2);   
     player2Panel.setLayout(new GridLayout(2,1));
     player2Panel.add(subPanel2);
     homePanel.add(player2Panel,BorderLayout.EAST);
     
     gamePanel=new JPanel();
     gamePanel.setLayout(new GridLayout(8,8));
     gamePanel.setBackground(new Color(48,107,89));
     gamePanel.setBorder(BorderFactory.createLineBorder(Color.WHITE,2));
     homePanel.add(gamePanel,BorderLayout.CENTER);
  }
  
  public void addInSubPanel1(ImageIcon imgDie,int i)
  {  
    if(brownCount<16)
    {
     brownDieImage[brownCount]=imgDie;   
     brownDie[brownCount]=new JLabel(resizeImage(imgDie,25,25,20));  
     brownDie[brownCount].setOpaque(true);
     brownDie[brownCount].setBackground(new Color(230,107,89));
     brownDie[brownCount].setHorizontalAlignment(SwingConstants.CENTER);
     brownDie[brownCount].setBorder(BorderFactory.createLineBorder(Color.WHITE,1));
     brownDieIndex[brownCount]=i;
     subPanel1_1.add(brownDie[brownCount++]);
    }
  }
  
  public void addInSubPanel2(ImageIcon imgDie,int i)
  {
    if(whiteCount<16)
    {
     whiteDieImage[whiteCount]=imgDie;   
     whiteDie[whiteCount]=new JLabel(resizeImage(imgDie,25,25,20));  
     whiteDie[whiteCount].setOpaque(true);
     whiteDie[whiteCount].setBackground(new Color(230,107,89));
     whiteDie[whiteCount].setHorizontalAlignment(SwingConstants.CENTER);
     whiteDie[whiteCount].setBorder(BorderFactory.createLineBorder(Color.WHITE,1));
     whiteDieIndex[whiteCount]=i;
     subPanel2_2.add(whiteDie[whiteCount++]);
    }  
  }
  
  
  public void addLabel()
  {
      int label=0;
      for(int i=1;i<65;i++)
      {
         boxLabel[i-1]=new JLabel();
         boxLabel[i-1].setOpaque(true);
         boxLabel[i-1].setHorizontalAlignment(SwingConstants.CENTER);
         if(label==0)
         {
             if(i%2==0)boxLabel[i-1].setBackground(Color.BLACK);
              else boxLabel[i-1].setBackground(Color.WHITE);
         }  
         else if(label==1)
         {
           if(i%2==0)boxLabel[i-1].setBackground(Color.WHITE);
              else boxLabel[i-1].setBackground(Color.BLACK);
         }  
         if(i%8==0 && label==0)label=1;
         else if(i%8==0 && label==1)label=0;
         gamePanel.add(boxLabel[i-1]);  
      }
      
      showMessages=new JLabel();
      showMessages.setPreferredSize(new Dimension(100,20));
      showMessages.setOpaque(true);
      showMessages.setHorizontalAlignment(SwingConstants.CENTER);
      showMessages.setBackground(Color.WHITE);
      bottomPanel.add(showMessages);
            
      headLabel=new JLabel();
      headLabel.setOpaque(true);
      headLabel.setBackground(Color.BLACK);
      headLabel.setForeground(Color.WHITE);
      headLabel.setBorder(BorderFactory.createLineBorder(Color.WHITE,2));
      headLabel.setText("BRAIN BATTLE");
      headLabel.setFont(new Font("ShyFonts",Font.BOLD,30));
      headLabel.setHorizontalAlignment(SwingConstants.CENTER);
      topPanel.add(headLabel);
      
      player1Score=new JLabel();      
      //player1Score.setOpaque(true);
      player1Score.setForeground(Color.WHITE);
      player1Score.setHorizontalAlignment(SwingConstants.LEFT);
      player1Score.setText(player1Details);
      bottomPanel.add(player1Score,FlowLayout.LEFT);
      
      player2Score=new JLabel();      
      //player@Score.setOpaque(true);
      player2Score.setForeground(Color.WHITE);
      player2Score.setHorizontalAlignment(SwingConstants.RIGHT);
      player2Score.setText(player2Details);
      bottomPanel.add(player2Score,FlowLayout.RIGHT);
  }
  
  public void addButton()
  {
    undo=new JButton("  BACK  ");
    undo.setBackground(Color.BLACK);
    undo.setForeground(Color.WHITE);
    undo.setBorder(BorderFactory.createLineBorder(Color.WHITE,2));
    bottomPanel.add(undo,FlowLayout.CENTER);
  }
  
  public void addImageIcon()
  {
     int j=0; 
     for(int i=0;i<16;i++) 
     boxLabel[i].setIcon(brownPlayer[i]);
     for(int i=63;i>47;i--) 
     boxLabel[i].setIcon(whitePlayer[j++]);
  }
  
  
  public void setPieces()
  {
     brownPlayer[0]=new ImageIcon(getClass().getResource("BRooks.png")); piecesNameB[0]="Brown Rook";
     brownPlayer[1]=new ImageIcon(getClass().getResource("BKnights.png")); piecesNameB[1]="Brown Knight";
     brownPlayer[2]=new ImageIcon(getClass().getResource("BBishops.png")); piecesNameB[2]="Brown Bishop";
     brownPlayer[3]=new ImageIcon(getClass().getResource("BKing.png")); piecesNameB[3]="Brown King";
     brownPlayer[4]=new ImageIcon(getClass().getResource("BQueen.png")); piecesNameB[4]="Brown Queen";
     brownPlayer[5]=new ImageIcon(getClass().getResource("BBishops.png")); piecesNameB[5]="Brown Bishop";
     brownPlayer[6]=new ImageIcon(getClass().getResource("BKnights.png")); piecesNameB[6]="Brown Knight";
     brownPlayer[7]=new ImageIcon(getClass().getResource("BRooks.png")); piecesNameB[7]="Brown Rook";
     for(int i=8;i<16;i++)
     {
     brownPlayer[i]=new ImageIcon(getClass().getResource("BPawns.png"));
     piecesNameB[i]="Brown Pawn";
     }
     
     whitePlayer[0]=new ImageIcon(getClass().getResource("WRooks.png")); piecesNameW[0]="White Rook";
     whitePlayer[1]=new ImageIcon(getClass().getResource("WKnights.png")); piecesNameW[1]="White Knight";
     whitePlayer[2]=new ImageIcon(getClass().getResource("WBishops.png")); piecesNameW[2]="White Bishop";
     whitePlayer[3]=new ImageIcon(getClass().getResource("WKing.png")); piecesNameW[3]="White King";
     whitePlayer[4]=new ImageIcon(getClass().getResource("WQueen.png")); piecesNameW[4]="White Queen";
     whitePlayer[5]=new ImageIcon(getClass().getResource("WBishops.png")); piecesNameW[5]="White Bishop";
     whitePlayer[6]=new ImageIcon(getClass().getResource("WKnights.png")); piecesNameW[6]="White Knight";
     whitePlayer[7]=new ImageIcon(getClass().getResource("WRooks.png"));  piecesNameW[7]="White Rook";   
     for(int i=8;i<16;i++)
     {
     whitePlayer[i]=new ImageIcon(getClass().getResource("WPawns.png"));
     piecesNameW[i]="White Pawn";
     }
     
     brownCopy=brownPlayer.clone();
     whiteCopy=whitePlayer.clone();
//     changePiecesSize();   
  }
//  
//  public void changePiecesSize()
//  {    
//     for(int i=0;i<16;i++)
//     {
//         brownPlayer[i]=resizeImage(brownCopy[i],boxLabel[0].getWidth()-20,boxLabel[0].getHeight()-20,20);
//         whitePlayer[i]=resizeImage(whiteCopy[i],boxLabel[0].getWidth()-20,boxLabel[0].getHeight()-20,20);
//     }  
//  }
  
  public ImageIcon resizeImage(ImageIcon img,int width,int height,int blur)
  {   
   return new ImageIcon( ((img.getImage()).getScaledInstance(width,height,blur)) );
  }
  
  public void addAction()
  {
    MouseAction mouseObj=new MouseAction();
    MouseMotion mouseMotionObj=new MouseMotion();
    ButtonAction butAction=new ButtonAction();
    undo.addActionListener(butAction);
     for(int i=0;i<64;i++)
     {
         boxLabel[i].addMouseListener(mouseObj);
         boxLabel[i].addMouseMotionListener(mouseMotionObj);
     }
  }
   
  public class MouseAction extends MouseAdapter
  {
      
     public void mouseClicked(MouseEvent evt)
     {
        
       /**********************************************************************************************/    
          
        for(int i=0;i<64;i++)
        {
            if(evt.getSource()==boxLabel[i])
             {
                 if(boxLabel[i].getIcon()==null)
                 {
                     if(img==null)
                     { 
                        showMessages.setPreferredSize(new Dimension(100,20));
                        showMessages.setText("Wrong Move");
                     }
                     else
                     {
                         if(checkMoves(i)==1)
                         {
                            if(optValue==1)
                            {
                            storeChance(); playerChance=2;     
                            player1Details="[ Player 1  Total Moves : "+(++totalMoveB)+"  Pieces Left : "+piecesLeftB+" ]";
                            player1Score.setText(player1Details);
                            }
                            else if(optValue==2)
                            {
                            storeChance(); playerChance=1;     
                            player2Details="[ Player 2  Total Moves : "+(++totalMoveW)+"  Pieces Left : "+piecesLeftW+" ]";
                            player2Score.setText(player2Details);
                            }
                            countMoves++;
                            boxLabel[i].setIcon(img);
                            if((ImageIcon)boxLabel[i].getIcon()==whiteCopy[3])
                                      wKingPosition=i;
                            else if((ImageIcon)boxLabel[i].getIcon()==brownCopy[3])
                                      bKingPosition=i;
                            img=null; enable=0; optValue=0; dSelectIndex(); 
                            boxLabel[index].setIcon(null);
                            boxLabel[index].setBackground(backColor);
                         } 
                         else
                         {
                            showMessages.setPreferredSize(new Dimension(100,20));
                            showMessages.setText("Wrong Move");
                         }
                     }
                 }
                 
                 else if(enable!=1  && checkChance(i))
                 {
                     img=(ImageIcon) boxLabel[i].getIcon();
                     backColor=boxLabel[i].getBackground();
                     boxLabel[i].setBackground(new Color(248,111,71)); 
                     enable=1;
                     index=i;
                     saveH(i,img,0);
                      for(int k=0;k<16;k++)
                      {
                         if(img==brownCopy[k])
                         {
                            optValue=1;
                             chessAI(i,k,optValue);
                            break;
                         }
                         else if(img==whiteCopy[k])
                         {
                            optValue=2;
                             chessAI(i,k,optValue);
                            break;
                         }
                      }
                 }
                 
                 else if((ImageIcon)boxLabel[i].getIcon()==img)
                 {
                       boxLabel[i].setBackground(backColor);
                       img=null;
                       enable=0;
                       backColor=null;
                       optValue=0;
                       deleteH();
                       dSelectIndex();
                       repaint();
                 }
                 
                 else if(boxLabel[i].getIcon()!=null)
                 {
                      for(int k=0;k<16;k++)
                      {
                          if((ImageIcon)boxLabel[i].getIcon()==brownCopy[k])
                          {
                             if(optValue==2)
                             {
                               if(checkMoves(i)==1)  
                               {
                                    storeChance(); playerChance=1; 
                                    countMoves++;
                                    saveH(i,(ImageIcon)boxLabel[i].getIcon(),1);
                                    addInSubPanel1((ImageIcon)boxLabel[i].getIcon(),i);  
                                    boxLabel[i].setIcon(img);
                                    if((ImageIcon)boxLabel[i].getIcon()==whiteCopy[3])
                                      wKingPosition=i;
                                    boxLabel[index].setBackground(backColor);
                                    boxLabel[index].setIcon(null);
                                    img=null;
                                    enable=0;
                                    optValue=0;
                                    backColor=null;
                                    player1Details="[ Player 1  Total Moves : "+(totalMoveB)+"  Pieces Left : "+(--piecesLeftB)+" ]";
                                    player2Details="[ Player 2  Total Moves : "+(++totalMoveW)+"  Pieces Left : "+(piecesLeftW)+" ]";
                                    player1Score.setText(player1Details);
                                    player2Score.setText(player2Details);
                                    dSelectIndex();
                                    repaint();
                               }
                               else
                               {
                                 showMessages.setPreferredSize(new Dimension(100,20));
                                 showMessages.setText("Wrong Move");
                               }
                             }
                             else
                             {
                                showMessages.setPreferredSize(new Dimension(100,20));
                                showMessages.setText("Wrong Move");
                             }
                             break;
                          }
                          else if((ImageIcon)boxLabel[i].getIcon()==whiteCopy[k])
                          {
                            if(optValue==1)
                             {
                                 if(checkMoves(i)==1)
                                 {
                                    storeChance(); playerChance=2;  
                                    countMoves++; 
                                    saveH(i,(ImageIcon)boxLabel[i].getIcon(),1); 
                                    addInSubPanel2((ImageIcon)boxLabel[i].getIcon(),i);  
                                    boxLabel[i].setIcon(img);
                                    if((ImageIcon)boxLabel[i].getIcon()==brownCopy[3])
                                      bKingPosition=i;
                                    boxLabel[index].setBackground(backColor);
                                    boxLabel[index].setIcon(null);
                                    img=null;
                                     enable=0;
                                     optValue=0;
                                     backColor=null;
                                     player1Details="[ Player 1  Total Moves : "+(++totalMoveB)+"   Pieces Left : "+(piecesLeftB)+" ]";
                                     player2Details="[ Player 2  Total Moves : "+(totalMoveW)+"  Pieces Left : "+(--piecesLeftW)+" ]";
                                     player1Score.setText(player1Details);
                                     player2Score.setText(player2Details); 
                                     dSelectIndex();
                                    repaint();
                                 }
                                 else
                                 {
                                     showMessages.setPreferredSize(new Dimension(100,20));
                                     showMessages.setText("Wrong Move");
                                 }
                             }
                             else
                             {
                                showMessages.setPreferredSize(new Dimension(100,20));
                                showMessages.setText("Wrong Move");
                             }
                             break;
                          }
                      }
                 
                 }
                 
                 else
                 {
                     showMessages.setPreferredSize(new Dimension(100,20));
                     showMessages.setText("Wrong Move");
                 }
                 
               } 
            }
        }
  }
  
  public class MouseMotion extends MouseMotionAdapter
  {
      public void mouseMoved(MouseEvent evt)
      {
         showMessages.setPreferredSize(new Dimension(100,20));
         showMessages.setText("");
           for(int i=0;i<64 && moveDone!=1;i++)
           {
             if(evt.getSource()==boxLabel[i])
             {
                  if(boxLabel[i].getIcon()!=null)
                  {
                       for(int j=0;j<16;j++)
                       {//JOptionPane.showMessageDialog(null,"Hi");
                           if((ImageIcon)boxLabel[i].getIcon()==brownCopy[j])
                           {
                              showMessages.setText(""+piecesNameB[j]);
                              moveDone=1;
                              break;
                           }
                           
                           else if((ImageIcon)boxLabel[i].getIcon()==whiteCopy[j])
                           {
                             showMessages.setText(""+piecesNameW[j]);
                             moveDone=1;
                             break;
                           }
                       }
                  }
             }
           }
           
           moveDone=0;
      }
  
  }
  
  class ButtonAction implements ActionListener
  {
      public void actionPerformed(ActionEvent evt)
      {
          if(saveHCount>0 && enable!=1)
          {
              
              ImageIcon img_h=saveHImage[saveHImageCount-1];
                 
                if(killedCount>0 && img_h==killedPieces[killedCount-1])
                {
                    playerChance=playerCArr[--playerCCount];
                   --saveHImageCount; 
                   boxLabel[saveHIndex[--saveHCount]].setIcon(img_h);
                   boxLabel[saveHIndex[--saveHCount]].setIcon(saveHImage[--saveHImageCount]);
                   repaint();
                }
                
                else
                { 
                   playerChance=playerCArr[--playerCCount]; 
                   --saveHImageCount;  
                   boxLabel[saveHIndex[--saveHCount]].setIcon(img_h);   
                   int i=saveHIndex[saveHCount];  
                     String str1=img_h.toString(),str2=brownCopy[1].toString(),str3=whiteCopy[1].toString();
                   if(str1.equals(str2) || str1.equals(str3))
                     {
                      if((i-10)>=0 && img_h==(ImageIcon)boxLabel[i-10].getIcon())boxLabel[i+10].setIcon(null);   
                      if((i+10)<=63 && img_h==(ImageIcon)boxLabel[i+10].getIcon())boxLabel[i+10].setIcon(null);
                      if((i-17)>=0 && img_h==(ImageIcon)boxLabel[i-17].getIcon())boxLabel[i-17].setIcon(null);
                      if((i+17)<=63 && img_h==(ImageIcon)boxLabel[i+17].getIcon())boxLabel[i+17].setIcon(null);
                      if((i-15)>=0 && img_h==(ImageIcon)boxLabel[i-15].getIcon())boxLabel[i-15].setIcon(null);
                      if((i+15)<=63 && img_h==(ImageIcon)boxLabel[i+15].getIcon())boxLabel[i+15].setIcon(null);
                     }
                   else
                   {
                   for(int j=i-8;j>=0 && (ImageIcon)boxLabel[j].getIcon()==img_h;j=j-8){boxLabel[j].setIcon(null);}
                   for(int j=i+8;j<=63 && (ImageIcon)boxLabel[j].getIcon()==img_h;j=j+8){boxLabel[j].setIcon(null);}
                   for(int j=i-8;j>=0 && ((ImageIcon)boxLabel[j].getIcon()==img_h || boxLabel[j].getIcon()==null);j=j-8){boxLabel[j].setIcon(null);}
                   for(int j=i+8;j<=63 && ((ImageIcon)boxLabel[j].getIcon()==img_h || boxLabel[j].getIcon()==null);j=j+8){boxLabel[j].setIcon(null);}
                   for(int j=i-1;j>=0 && ((ImageIcon)boxLabel[j].getIcon()==img_h || boxLabel[j].getIcon()==null);j=j-1){boxLabel[j].setIcon(null);}
                   for(int j=i+1;j<=63 && ((ImageIcon)boxLabel[j].getIcon()==img_h || boxLabel[j].getIcon()==null);j=j+1){boxLabel[j].setIcon(null);}
                   for(int j=i-7;j>=0 && j>checkPointsL(j) && ((ImageIcon)boxLabel[j].getIcon()==img_h || boxLabel[j].getIcon()==null);j=j-7){boxLabel[j].setIcon(null);}
                   for(int j=i-9;j>=0 && j<checkPointsR(j) && ((ImageIcon)boxLabel[j].getIcon()==img_h || boxLabel[j].getIcon()==null);j=j-9){boxLabel[j].setIcon(null);}
                   for(int j=i+7;j<=63 && j<checkPointsR(j) && ((ImageIcon)boxLabel[j].getIcon()==img_h || boxLabel[j].getIcon()==null);j=j+7){boxLabel[j].setIcon(null);}
                   for(int j=i+9;j<=63 && j>checkPointsL(j) && ((ImageIcon)boxLabel[j].getIcon()==img_h || boxLabel[j].getIcon()==null);j=j+9){boxLabel[j].setIcon(null);}
                   }
                }
              
              
                     if(killedCount!=0 && img_h==killedPieces[killedCount-1])
                         {
                               if(brownCount!=0 && brownDieImage[brownCount-1]==killedPieces[killedCount-1])
                               {
                                      subPanel1_1.remove(brownDie[brownCount-1]);
                                      brownCount--;
                                      killedPieces[--killedCount]=null;
                                      player1Details="[ Player 1  Total Moves : "+(totalMoveB)+"  Pieces Left : "+(++piecesLeftB)+" ]";
                                      player1Score.setText(player1Details);
                                      repaint();
                               }
                               else if(whiteCount!=0 && whiteDieImage[whiteCount-1]==killedPieces[killedCount-1]) 
                               {
                                      subPanel2_2.remove(whiteDie[whiteCount-1]);
                                      whiteCount--;
                                      killedPieces[--killedCount]=null;
                                      player2Details="[ Player 2  Total Moves : "+(totalMoveW)+"  Pieces Left : "+(++piecesLeftW)+" ]";
                                      player2Score.setText(player2Details);
                                      repaint();
                               }
                          } 
                     
                  
                     
              repaint();
          }
      }
  }
  
  public void saveH(int i,ImageIcon h_img,int kill)
  { 
      if(kill==1)
        killedPieces[killedCount++]=h_img;
     saveHIndex[saveHCount++]=i;
     saveHImage[saveHImageCount++]=h_img;
  }
  public void deleteH()
  {       
     saveHIndex[--saveHCount]=0;
     saveHImage[--saveHImageCount]=null;
  }
  
  public void chessAI(int i,int k,int optvalue)
  {
     if(optValue==1)
     {
         int m=0;
         //JOptionPane.showMessageDialog(null,"Hello");
         
/*********************************************** BROWN I *****************************************************/          
         switch(piecesNameB[k])
         {
             case "Brown Rook":
                             if(i>0)
                             {
                              for(m=i-8;m>=0 && boxLabel[m].getIcon()==null;m=m-8)
                              {
                                boxLabel[m].setBorder(BorderFactory.createLineBorder(new Color(100,160,180),3));
                                traceIndex[traceCount++]=m;
                              }
                              if(m>=0 && boxLabel[m].getIcon()!=null)
                                 checkPositionB(m);
                               if(i==8|| i==15 && boxLabel[i-8].getIcon()!=null)
                                 checkPositionB(i-8);  
                             }
                             
                             for(m=i+8;m<=63 && boxLabel[m].getIcon()==null;m=m+8)
                              {
                                boxLabel[m].setBorder(BorderFactory.createLineBorder(new Color(100,160,180),3));
                                traceIndex[traceCount++]=m;
                              }
                               if(m<=63 && boxLabel[m].getIcon()!=null)
                                 checkPositionB(m);
                               if(i==48|| i==55 && boxLabel[i+8].getIcon()!=null)
                                 checkPositionB(i+8);
                              
                              for(m=i+1;m<=checkPointsR(i) && boxLabel[m].getIcon()==null;m=m+1)
                              {
                                boxLabel[m].setBorder(BorderFactory.createLineBorder(new Color(100,160,180),3));
                                traceIndex[traceCount++]=m;
                              }
                              if(m<=checkPointsR(i) && boxLabel[m].getIcon()!=null)
                                 checkPositionB(m);
                              
                              if(i>checkPointsL(i))
                              {
                                    for(m=i-1;m>=checkPointsL(i) && boxLabel[m].getIcon()==null;m=m-1)
                                    {
                                      boxLabel[m].setBorder(BorderFactory.createLineBorder(new Color(100,160,180),3));
                                      traceIndex[traceCount++]=m;
                                    }  
                                if(m>=checkPointsL(i) && boxLabel[m].getIcon()!=null)
                                    checkPositionB(m);    
                              }
                 
             break;
             case "Brown Knight":
                 int verticalWR=0,verticalWL=0,horizontalLU=0,horizontalRU=0;
                      
                        if(i<=47)
                        {   
                           verticalWR=(i+16)+1;   
                            verticalWL=(i+16)-1;
                          
                            if(verticalWR<=checkPointsR(verticalWR-1) && boxLabel[verticalWR].getIcon()==null)
                            {
                              boxLabel[verticalWR].setBorder(BorderFactory.createLineBorder(new Color(100,160,180),3));
                              traceIndex[traceCount++]=verticalWR;
                            }
                            else if(verticalWR<=checkPointsR(verticalWR-1) && boxLabel[verticalWR].getIcon()!=null)
                            {
                                  checkPositionB(verticalWR);   
                            }
                            
                            if(verticalWL>=checkPointsL(verticalWL+1) && boxLabel[verticalWL].getIcon()==null)
                            {
                               boxLabel[verticalWL].setBorder(BorderFactory.createLineBorder(new Color(100,160,180),3));
                               traceIndex[traceCount++]=verticalWL;
                            }
                            else if(verticalWL>=checkPointsL(verticalWL+1) && boxLabel[verticalWL].getIcon()!=null)
                            {
                               checkPositionB(verticalWL);
                            }                       
                      }
                      if(i+2<=checkPointsR(i) && i<=55)
                      {
                       horizontalLU=(i+2)+8;
                        if(boxLabel[horizontalLU].getIcon()==null) 
                        {
                         boxLabel[horizontalLU].setBorder(BorderFactory.createLineBorder(new Color(100,160,180),3));
                         traceIndex[traceCount++]=horizontalLU;
                        }
                        else if(boxLabel[horizontalLU].getIcon()!=null)
                        {
                         checkPositionB(horizontalLU);
                        }
                      
                      }
                      if(i-2>=checkPointsL(i) && i<=55)
                      {  
                       horizontalRU=(i-2)+8;
                        if(boxLabel[horizontalRU].getIcon()==null)
                        {
                          boxLabel[horizontalRU].setBorder(BorderFactory.createLineBorder(new Color(100,160,180),3));
                          traceIndex[traceCount++]=horizontalRU;
                        }
                        else if(boxLabel[horizontalRU].getIcon()!=null)
                        {
                          checkPositionB(horizontalRU);    
                        } 
                        
                      }
                      if(i>=16)
                      {   
                       verticalWR=(i-16)+1;   
                       verticalWL=(i-16)-1;
                          
                            if(verticalWR<=checkPointsR(verticalWR-1) && boxLabel[verticalWR].getIcon()==null)
                            {
                              boxLabel[verticalWR].setBorder(BorderFactory.createLineBorder(new Color(100,160,180),3));
                              traceIndex[traceCount++]=verticalWR;
                            }
                            else if(verticalWR<=checkPointsR(verticalWR-1) && boxLabel[verticalWR].getIcon()!=null)
                            {
                                  checkPositionB(verticalWR);   
                            }
                            
                            if(verticalWL>=checkPointsL(verticalWL+1) && boxLabel[verticalWL].getIcon()==null)
                            {
                               boxLabel[verticalWL].setBorder(BorderFactory.createLineBorder(new Color(100,160,180),3));
                               traceIndex[traceCount++]=verticalWL;
                            }
                            else if(verticalWL>=checkPointsL(verticalWL+1) && boxLabel[verticalWL].getIcon()!=null)
                            {
                               checkPositionB(verticalWL);
                            }                       
                      }
                      if(i+2<=checkPointsR(i) && i>=8)
                      {
                       horizontalLU=(i+2)-8;
                        if(boxLabel[horizontalLU].getIcon()==null) 
                        {
                         boxLabel[horizontalLU].setBorder(BorderFactory.createLineBorder(new Color(100,160,180),3));
                         traceIndex[traceCount++]=horizontalLU;
                        }
                        else if(boxLabel[horizontalLU].getIcon()!=null)
                        {
                         checkPositionB(horizontalLU);
                        }
                      
                      }
                      if(i-2>=checkPointsL(i) && i>=8)
                      {  
                       horizontalRU=(i-2)-8;
                        if(boxLabel[horizontalRU].getIcon()==null)
                        {
                          boxLabel[horizontalRU].setBorder(BorderFactory.createLineBorder(new Color(100,160,180),3));
                          traceIndex[traceCount++]=horizontalRU;
                        }
                        else if(boxLabel[horizontalRU].getIcon()!=null)
                        {
                          checkPositionB(horizontalRU);    
                        } 
                        
                      }
              break;
             case "Brown Bishop":
                 
                       for(m=i+7;m<=63 && m<checkPointsR(m) && boxLabel[m].getIcon()==null;m=m+7)
                          { 
                            boxLabel[m].setBorder(BorderFactory.createLineBorder(new Color(100,160,180),3));
                            traceIndex[traceCount++]=m; 
                          }
                           if(m<=63 && m<checkPointsR(m)&&boxLabel[m].getIcon()!=null)
                              checkPositionB(m);  
                              
                          for(m=i+9;m<=63 && m>checkPointsL(m) && boxLabel[m].getIcon()==null;m=m+9)
                          {
                            boxLabel[m].setBorder(BorderFactory.createLineBorder(new Color(100,160,180),3));
                            traceIndex[traceCount++]=m; 
                          }
                           if(m<=63 && m>checkPointsL(m)&&boxLabel[m].getIcon()!=null)
                              checkPositionB(m);  
                           
                           for(m=i-7;m>=0 && m>checkPointsL(m) && boxLabel[m].getIcon()==null;m=m-7)
                          { 
                            boxLabel[m].setBorder(BorderFactory.createLineBorder(new Color(100,160,180),3));
                            traceIndex[traceCount++]=m; 
                          }
                           if(m>=7 && m>checkPointsL(m)&&boxLabel[m].getIcon()!=null)
                              checkPositionB(m);  
                           
                          for(m=i-9;m>=0 && m<checkPointsR(m) && boxLabel[m].getIcon()==null;m=m-9)
                          {
                            boxLabel[m].setBorder(BorderFactory.createLineBorder(new Color(100,160,180),3));
                            traceIndex[traceCount++]=m; 
                          }
                           if(m>=0 && m<checkPointsR(m)&&boxLabel[m].getIcon()!=null)
                              checkPositionB(m);
                 
             break;
             case "Brown Queen":
                        
                       if(i>0)
                             {
                              for(m=i-8;m>=0 && boxLabel[m].getIcon()==null;m=m-8)
                              {
                                boxLabel[m].setBorder(BorderFactory.createLineBorder(new Color(100,160,180),3));
                                traceIndex[traceCount++]=m;
                              }
                              if(m>=0 && boxLabel[m].getIcon()!=null)
                                 checkPositionB(m);
                               if(i==8|| i==15 && boxLabel[i-8].getIcon()!=null)
                                 checkPositionB(i-8);  
                             }
                             
                             for(m=i+8;m<=63 && boxLabel[m].getIcon()==null;m=m+8)
                              {
                                boxLabel[m].setBorder(BorderFactory.createLineBorder(new Color(100,160,180),3));
                                traceIndex[traceCount++]=m;
                              }
                               if(m<=63 && boxLabel[m].getIcon()!=null)
                                 checkPositionB(m);
                               if(i==48|| i==55 && boxLabel[i+8].getIcon()!=null)
                                 checkPositionB(i+8);
                              
                              for(m=i+1;m<=checkPointsR(i) && boxLabel[m].getIcon()==null;m=m+1)
                              {
                                boxLabel[m].setBorder(BorderFactory.createLineBorder(new Color(100,160,180),3));
                                traceIndex[traceCount++]=m;
                              }
                              if(m<=checkPointsR(i) && boxLabel[m].getIcon()!=null)
                                 checkPositionB(m);
                              
                              if(i>checkPointsL(i))
                              {
                                    for(m=i-1;m>=checkPointsL(i) && boxLabel[m].getIcon()==null;m=m-1)
                                    {
                                      boxLabel[m].setBorder(BorderFactory.createLineBorder(new Color(100,160,180),3));
                                      traceIndex[traceCount++]=m;
                                    }  
                                if(m>=checkPointsL(i) && boxLabel[m].getIcon()!=null)
                                    checkPositionB(m);    
                              }
                 
                      
                       for(m=i+7;m<=63 && m<checkPointsR(m) && boxLabel[m].getIcon()==null;m=m+7)
                          { 
                            boxLabel[m].setBorder(BorderFactory.createLineBorder(new Color(100,160,180),3));
                            traceIndex[traceCount++]=m; 
                          }
                           if(m<=63 && m<checkPointsR(m)&&boxLabel[m].getIcon()!=null)
                              checkPositionB(m);  
                              
                          for(m=i+9;m<=63 && m>checkPointsL(m) && boxLabel[m].getIcon()==null;m=m+9)
                          {
                            boxLabel[m].setBorder(BorderFactory.createLineBorder(new Color(100,160,180),3));
                            traceIndex[traceCount++]=m; 
                          }
                           if(m<=63 && m>checkPointsL(m)&&boxLabel[m].getIcon()!=null)
                              checkPositionB(m);  
                           
                           for(m=i-7;m>=0 && m>checkPointsL(m) && boxLabel[m].getIcon()==null;m=m-7)
                          { 
                            boxLabel[m].setBorder(BorderFactory.createLineBorder(new Color(100,160,180),3));
                            traceIndex[traceCount++]=m; 
                          }
                           if(m>=7 && m>checkPointsL(m)&&boxLabel[m].getIcon()!=null)
                              checkPositionB(m);  
                           
                          for(m=i-9;m>=0 && m<checkPointsR(m) && boxLabel[m].getIcon()==null;m=m-9)
                          {
                            boxLabel[m].setBorder(BorderFactory.createLineBorder(new Color(100,160,180),3));
                            traceIndex[traceCount++]=m; 
                          }
                           if(m>=0 && m<checkPointsR(m)&&boxLabel[m].getIcon()!=null)
                              checkPositionB(m);
                 
                 
             break;
             case "Brown King":
                 
                 if(i-1>=checkPointsL(i) && boxLabel[i-1].getIcon()==null)
                      {
                        boxLabel[i-1].setBorder(BorderFactory.createLineBorder(new Color(100,160,180),3));
                        traceIndex[traceCount++]=i-1;
                      }
                      else if(i-1>=checkPointsL(i) && boxLabel[i-1].getIcon()!=null)
                          checkPositionB(i-1);
                      
                      if(i+1<=checkPointsR(i) && boxLabel[i+1].getIcon()==null)
                      {
                        boxLabel[i+1].setBorder(BorderFactory.createLineBorder(new Color(100,160,180),3));
                        traceIndex[traceCount++]=i+1;
                      }
                      else if(i+1<=checkPointsR(i) && boxLabel[i+1].getIcon()!=null)
                         checkPositionB(i+1);
                      
                      if(i<=55 && boxLabel[i+8].getIcon()==null)
                      {
                        boxLabel[i+8].setBorder(BorderFactory.createLineBorder(new Color(100,160,180),3));
                        traceIndex[traceCount++]=i+8;
                      }
                      else if(i<=55 && boxLabel[i+8].getIcon()!=null)
                         checkPositionB(i+8);
                      
                      if(i<=54 && boxLabel[i+9].getIcon()==null)
                      {
                        boxLabel[i+9].setBorder(BorderFactory.createLineBorder(new Color(100,160,180),3));
                        traceIndex[traceCount++]=i+9;
                      }
                      else if(i<=54 && boxLabel[i+9].getIcon()!=null)
                         checkPositionB(i+9);
                      
                      if(i<=56 && boxLabel[i+7].getIcon()==null)
                      {
                        boxLabel[i+7].setBorder(BorderFactory.createLineBorder(new Color(100,160,180),3));
                        traceIndex[traceCount++]=i+7;
                      }
                      else if(i<=56 && boxLabel[i+7].getIcon()!=null)
                         checkPositionB(i+7);
                      
                      if(i>=8 && boxLabel[i-8].getIcon()==null)
                      {
                        boxLabel[i-8].setBorder(BorderFactory.createLineBorder(new Color(100,160,180),3));
                        traceIndex[traceCount++]=i-8;
                      }
                      else if(i>=8 && boxLabel[i-8].getIcon()!=null)
                         checkPositionB(i-8);
                      
                      if(i>=9 && boxLabel[i-9].getIcon()==null)
                      {
                        boxLabel[i-9].setBorder(BorderFactory.createLineBorder(new Color(100,160,180),3));
                        traceIndex[traceCount++]=i-9;
                      }
                      else if(i>=9 && boxLabel[i-9].getIcon()!=null)
                         checkPositionB(i-9);
                      
                      if(i>=7 && boxLabel[i-7].getIcon()==null)
                      {
                        boxLabel[i-7].setBorder(BorderFactory.createLineBorder(new Color(100,160,180),3));
                        traceIndex[traceCount++]=i-7;
                      }
                      else if(i>=7 && boxLabel[i-7].getIcon()!=null)
                         checkPositionB(i-7);
                      
             break;
             case "Brown Pawn":  
                       if((i<=56) && boxLabel[i+8].getIcon()==null)
                       {
                          boxLabel[i+8].setBorder(BorderFactory.createLineBorder(new Color(100,160,180),3));
                          traceIndex[traceCount++]=i+8;   
                       }
                        if((i<=56) &&  (i%8!=0) && boxLabel[i+7].getIcon()!=null)
                       {
                            for(int l=0;l<16;l++)
                            {
                                if((ImageIcon)boxLabel[i+7].getIcon()==whiteCopy[l])
                                {
                                  boxLabel[i+7].setBorder(BorderFactory.createLineBorder(new Color(30,202,116),3));  
                                  traceIndex[traceCount++]=i+7;   
                                  break;
                                }
                            }
                       }
                        if((i<=56) &&  ((i+1)%8!=0) && boxLabel[i+9].getIcon()!=null)
                       {
                            for(int l=0;l<16;l++)
                            {
                                if((ImageIcon)boxLabel[i+9].getIcon()==whiteCopy[l])
                                {
                                  boxLabel[i+9].setBorder(BorderFactory.createLineBorder(new Color(30,202,116),3));  
                                  traceIndex[traceCount++]=i+9;   
                                  break;
                                }
                            }
                       }
                 
                 
             break;    
         }
     }
/**************************************************************************************************************/
/*********************************************** WHITE I *****************************************************/     
     else if(optValue==2)
     {
        int l=0; 
        switch(piecesNameW[k])
         {
             case "White Rook"://       JOptionPane.showMessageDialog(null,"Hello");
                              
                              if(i<63)
                              {
                               for(l=i+8;l<=63 && boxLabel[l].getIcon()==null;l=l+8)
                              {
                                boxLabel[l].setBorder(BorderFactory.createLineBorder(new Color(100,160,180),3));
                                traceIndex[traceCount++]=l;
                              }
                               if(l<=63 && boxLabel[l].getIcon()!=null)
                                 checkPositionW(l);
                               if(i==48|| i==55 && boxLabel[i+8].getIcon()!=null)
                                 checkPositionW(i+8);
                              }
                             
                              for(l=i-8;l>=0 && boxLabel[l].getIcon()==null;l=l-8)
                              {
                                boxLabel[l].setBorder(BorderFactory.createLineBorder(new Color(100,160,180),3));
                                traceIndex[traceCount++]=l;
                              }
                              if(l>=0 && boxLabel[l].getIcon()!=null)
                                 checkPositionW(l);
                               if(i==8|| i==15 && boxLabel[i-8].getIcon()!=null)
                                 checkPositionW(i-8);
                              
                              for(l=i+1;l<=checkPointsR(i) && boxLabel[l].getIcon()==null;l=l+1)
                              {
                                boxLabel[l].setBorder(BorderFactory.createLineBorder(new Color(100,160,180),3));
                                traceIndex[traceCount++]=l;
                              }
                              if(l<=checkPointsR(i) && boxLabel[l].getIcon()!=null)
                                 checkPositionW(l);
                              
                              if(i>checkPointsL(i))
                              {
                                    for(l=i-1;l>=checkPointsL(i) && boxLabel[l].getIcon()==null;l=l-1)
                                    {
                                      boxLabel[l].setBorder(BorderFactory.createLineBorder(new Color(100,160,180),3));
                                      traceIndex[traceCount++]=l;
                                    }  
                                if(l>=checkPointsL(i) && boxLabel[l].getIcon()!=null)
                                    checkPositionW(l);    
                              }
                               
                               
             break;
             case "White Knight":
                 int verticalWR=0,verticalWL=0,horizontalLU=0,horizontalRU=0;
                      if(i>=16)
                      {   
                       verticalWR=(i-16)+1;   
                       verticalWL=(i-16)-1;
                          
                            if(verticalWR<=checkPointsR(verticalWR-1) && boxLabel[verticalWR].getIcon()==null)
                            {
                              boxLabel[verticalWR].setBorder(BorderFactory.createLineBorder(new Color(100,160,180),3));
                              traceIndex[traceCount++]=verticalWR;
                            }
                            else if(verticalWR<=checkPointsR(verticalWR-1) && boxLabel[verticalWR].getIcon()!=null)
                            {
                                  checkPositionW(verticalWR);   
                            }
                            
                            if(verticalWL>=checkPointsL(verticalWL+1) && boxLabel[verticalWL].getIcon()==null)
                            {
                               boxLabel[verticalWL].setBorder(BorderFactory.createLineBorder(new Color(100,160,180),3));
                               traceIndex[traceCount++]=verticalWL;
                            }
                            else if(verticalWL>=checkPointsL(verticalWL+1) && boxLabel[verticalWL].getIcon()!=null)
                            {
                               checkPositionW(verticalWL);
                            }                       
                      }
                      if(i+2<=checkPointsR(i) && i>=8)
                      {
                       horizontalLU=(i+2)-8;
                        if(boxLabel[horizontalLU].getIcon()==null) 
                        {
                         boxLabel[horizontalLU].setBorder(BorderFactory.createLineBorder(new Color(100,160,180),3));
                         traceIndex[traceCount++]=horizontalLU;
                        }
                        else if(boxLabel[horizontalLU].getIcon()!=null)
                        {
                         checkPositionW(horizontalLU);
                        }
                      
                      }
                      if(i-2>=checkPointsL(i) && i>=8)
                      {  
                       horizontalRU=(i-2)-8;
                        if(boxLabel[horizontalRU].getIcon()==null)
                        {
                          boxLabel[horizontalRU].setBorder(BorderFactory.createLineBorder(new Color(100,160,180),3));
                          traceIndex[traceCount++]=horizontalRU;
                        }
                        else if(boxLabel[horizontalRU].getIcon()!=null)
                        {
                          checkPositionW(horizontalRU);    
                        } 
                        
                      }
                     
                        if(i<=47)
                        {   
                           verticalWR=(i+16)+1;   
                            verticalWL=(i+16)-1;
                          
                            if(verticalWR<=checkPointsR(verticalWR-1) && boxLabel[verticalWR].getIcon()==null)
                            {
                              boxLabel[verticalWR].setBorder(BorderFactory.createLineBorder(new Color(100,160,180),3));
                              traceIndex[traceCount++]=verticalWR;
                            }
                            else if(verticalWR<=checkPointsR(verticalWR-1) && boxLabel[verticalWR].getIcon()!=null)
                            {
                                  checkPositionW(verticalWR);   
                            }
                            
                            if(verticalWL>=checkPointsL(verticalWL+1) && boxLabel[verticalWL].getIcon()==null)
                            {
                               boxLabel[verticalWL].setBorder(BorderFactory.createLineBorder(new Color(100,160,180),3));
                               traceIndex[traceCount++]=verticalWL;
                            }
                            else if(verticalWL>=checkPointsL(verticalWL+1) && boxLabel[verticalWL].getIcon()!=null)
                            {
                               checkPositionW(verticalWL);
                            }                       
                      }
                      if(i+2<=checkPointsR(i) && i<=55)
                      {
                       horizontalLU=(i+2)+8;
                        if(boxLabel[horizontalLU].getIcon()==null) 
                        {
                         boxLabel[horizontalLU].setBorder(BorderFactory.createLineBorder(new Color(100,160,180),3));
                         traceIndex[traceCount++]=horizontalLU;
                        }
                        else if(boxLabel[horizontalLU].getIcon()!=null)
                        {
                         checkPositionW(horizontalLU);
                        }
                      
                      }
                      if(i-2>=checkPointsL(i) && i<=55)
                      {  
                       horizontalRU=(i-2)+8;
                        if(boxLabel[horizontalRU].getIcon()==null)
                        {
                          boxLabel[horizontalRU].setBorder(BorderFactory.createLineBorder(new Color(100,160,180),3));
                          traceIndex[traceCount++]=horizontalRU;
                        }
                        else if(boxLabel[horizontalRU].getIcon()!=null)
                        {
                          checkPositionW(horizontalRU);    
                        } 
                        
                       }    
                      
             break;
             case "White Bishop":
                 
                          for(l=i-7;l>=0 && l>checkPointsL(l) && boxLabel[l].getIcon()==null;l=l-7)
                          { 
                            boxLabel[l].setBorder(BorderFactory.createLineBorder(new Color(100,160,180),3));
                            traceIndex[traceCount++]=l; 
                          }
                           if(l>=0 && l>checkPointsL(l)&&boxLabel[l].getIcon()!=null)
                              checkPositionW(l);  
                           
                          for(l=i-9;l>=0 && l<checkPointsR(l) && boxLabel[l].getIcon()==null;l=l-9)
                          {
                            boxLabel[l].setBorder(BorderFactory.createLineBorder(new Color(100,160,180),3));
                            traceIndex[traceCount++]=l; 
                          }
                           if(l>=0 && l<checkPointsR(l)&&boxLabel[l].getIcon()!=null)
                              checkPositionW(l);  
                 
                           
                       for(l=i+7;l<=63 && l<checkPointsR(l) && boxLabel[l].getIcon()==null;l=l+7)
                          { 
                            boxLabel[l].setBorder(BorderFactory.createLineBorder(new Color(100,160,180),3));
                            traceIndex[traceCount++]=l; 
                          }
                           if(l<=63 && l<checkPointsR(l)&&boxLabel[l].getIcon()!=null)
                              checkPositionW(l);  
                              
                          for(l=i+9;l<=63 && l>checkPointsL(l) && boxLabel[l].getIcon()==null;l=l+9)
                          {
                            boxLabel[l].setBorder(BorderFactory.createLineBorder(new Color(100,160,180),3));
                            traceIndex[traceCount++]=l; 
                          }
                           if(l<=63 && l>checkPointsL(l)&&boxLabel[l].getIcon()!=null)
                              checkPositionW(l);  
                 
             break;
             case "White Queen":
                 
                          
                              if(i<63)
                              {
                               for(l=i+8;l<=63 && boxLabel[l].getIcon()==null;l=l+8)
                              {
                                boxLabel[l].setBorder(BorderFactory.createLineBorder(new Color(100,160,180),3));
                                traceIndex[traceCount++]=l;
                              }
                               if(l<=63 && boxLabel[l].getIcon()!=null)
                                 checkPositionW(l);
                               if(i==48|| i==55 && boxLabel[i+8].getIcon()!=null)
                                 checkPositionW(i+8);
                              }
                             
                              for(l=i-8;l>=0 && boxLabel[l].getIcon()==null;l=l-8)
                              {
                                boxLabel[l].setBorder(BorderFactory.createLineBorder(new Color(100,160,180),3));
                                traceIndex[traceCount++]=l;
                              }
                              if(l>=0 && boxLabel[l].getIcon()!=null)
                                 checkPositionW(l);
                               if(i==8|| i==15 && boxLabel[i-8].getIcon()!=null)
                                 checkPositionW(i-8);
                              
                              for(l=i+1;l<=checkPointsR(i) && boxLabel[l].getIcon()==null;l=l+1)
                              {
                                boxLabel[l].setBorder(BorderFactory.createLineBorder(new Color(100,160,180),3));
                                traceIndex[traceCount++]=l;
                              }
                              if(l<=checkPointsR(i) && boxLabel[l].getIcon()!=null)
                                 checkPositionW(l);
                              
                              if(i>checkPointsL(i))
                              {
                                    for(l=i-1;l>=checkPointsL(i) && boxLabel[l].getIcon()==null;l=l-1)
                                    {
                                      boxLabel[l].setBorder(BorderFactory.createLineBorder(new Color(100,160,180),3));
                                      traceIndex[traceCount++]=l;
                                    }  
                                if(l>=checkPointsL(i) && boxLabel[l].getIcon()!=null)
                                    checkPositionW(l);    
                              }          
                 
                          for(l=i-7;l>=0 && l>checkPointsL(l) && boxLabel[l].getIcon()==null;l=l-7)
                          { 
                            boxLabel[l].setBorder(BorderFactory.createLineBorder(new Color(100,160,180),3));
                            traceIndex[traceCount++]=l; 
                          }
                           if(l>=0 && l>checkPointsL(l)&&boxLabel[l].getIcon()!=null)
                              checkPositionW(l);  
                           
                          for(l=i-9;l>=0 && l<checkPointsR(l) && boxLabel[l].getIcon()==null;l=l-9)
                          {
                            boxLabel[l].setBorder(BorderFactory.createLineBorder(new Color(100,160,180),3));
                            traceIndex[traceCount++]=l; 
                          }
                           if(l>=0 && l<checkPointsR(l)&&boxLabel[l].getIcon()!=null)
                              checkPositionW(l);  
                 
                           
                       for(l=i+7;l<=63 && l<checkPointsR(l) && boxLabel[l].getIcon()==null;l=l+7)
                          { 
                            boxLabel[l].setBorder(BorderFactory.createLineBorder(new Color(100,160,180),3));
                            traceIndex[traceCount++]=l; 
                          }
                           if(l<=63 && l<checkPointsR(l)&&boxLabel[l].getIcon()!=null)
                              checkPositionW(l);  
                              
                          for(l=i+9;l<=63 && l>checkPointsL(l) && boxLabel[l].getIcon()==null;l=l+9)
                          {
                            boxLabel[l].setBorder(BorderFactory.createLineBorder(new Color(100,160,180),3));
                            traceIndex[traceCount++]=l; 
                          }
                           if(l<=63 && l>checkPointsL(l)&&boxLabel[l].getIcon()!=null)
                              checkPositionW(l);  
                 
             break;
             case "White King":
                 
                      if(i-1>=checkPointsL(i) && boxLabel[i-1].getIcon()==null)
                      {
                        boxLabel[i-1].setBorder(BorderFactory.createLineBorder(new Color(100,160,180),3));
                        traceIndex[traceCount++]=i-1;
                      }
                      else if(i-1>=checkPointsL(i) && boxLabel[i-1].getIcon()!=null)
                          checkPositionW(i-1);
                      
                      if(i+1<=checkPointsR(i) && boxLabel[i+1].getIcon()==null)
                      {
                        boxLabel[i+1].setBorder(BorderFactory.createLineBorder(new Color(100,160,180),3));
                        traceIndex[traceCount++]=i+1;
                      }
                      else if(i+1<=checkPointsR(i) && boxLabel[i+1].getIcon()!=null)
                         checkPositionW(i+1);
                      
                      if(i>=8 && boxLabel[i-8].getIcon()==null)
                      {
                        boxLabel[i-8].setBorder(BorderFactory.createLineBorder(new Color(100,160,180),3));
                        traceIndex[traceCount++]=i-8;
                      }
                      else if(i>=8 && boxLabel[i-8].getIcon()!=null)
                         checkPositionW(i-8);
                      
                      if(i>=9 && boxLabel[i-9].getIcon()==null)
                      {
                        boxLabel[i-9].setBorder(BorderFactory.createLineBorder(new Color(100,160,180),3));
                        traceIndex[traceCount++]=i-9;
                      }
                      else if(i>=9 && boxLabel[i-9].getIcon()!=null)
                         checkPositionW(i-9);
                      
                      if(i>=7 && boxLabel[i-7].getIcon()==null)
                      {
                        boxLabel[i-7].setBorder(BorderFactory.createLineBorder(new Color(100,160,180),3));
                        traceIndex[traceCount++]=i-7;
                      }
                      else if(i>=7 && boxLabel[i-7].getIcon()!=null)
                         checkPositionW(i-7);
                      
                      if(i<=55 && boxLabel[i+8].getIcon()==null)
                      {
                        boxLabel[i+8].setBorder(BorderFactory.createLineBorder(new Color(100,160,180),3));
                        traceIndex[traceCount++]=i+8;
                      }
                      else if(i<=55 && boxLabel[i+8].getIcon()!=null)
                         checkPositionW(i+8);
                      
                      if(i<=54 && boxLabel[i+9].getIcon()==null)
                      {
                        boxLabel[i+9].setBorder(BorderFactory.createLineBorder(new Color(100,160,180),3));
                        traceIndex[traceCount++]=i+9;
                      }
                      else if(i<=54 && boxLabel[i+9].getIcon()!=null)
                         checkPositionW(i+9);
                      
                      if(i<=56 && boxLabel[i+7].getIcon()==null)
                      {
                        boxLabel[i+7].setBorder(BorderFactory.createLineBorder(new Color(100,160,180),3));
                        traceIndex[traceCount++]=i+7;
                      }
                      else if(i<=56 && boxLabel[i+7].getIcon()!=null)
                         checkPositionW(i+7);
                 
             break;
             case "White Pawn":
                        
                       if((i>=8) && boxLabel[i-8].getIcon()==null)
                       {
                          boxLabel[i-8].setBorder(BorderFactory.createLineBorder(new Color(100,160,180),3));
                          traceIndex[traceCount++]=i-8;   
                       }
                        if((i>=8) &&  (i%8!=0) && boxLabel[i-9].getIcon()!=null)
                       {
                            for(l=0;l<16;l++)
                            {
                                if((ImageIcon)boxLabel[i-9].getIcon()==brownCopy[l])
                                {
                                  boxLabel[i-9].setBorder(BorderFactory.createLineBorder(new Color(30,202,116),3));  
                                  traceIndex[traceCount++]=i-9;   
                                  break;
                                }
                            }
                       }
                        if((i>=8) &&  (i%15!=0) && boxLabel[i-7].getIcon()!=null)
                       {
                            for(l=0;l<16;l++)
                            {
                                if((ImageIcon)boxLabel[i-7].getIcon()==brownCopy[l])
                                {
                                  boxLabel[i-7].setBorder(BorderFactory.createLineBorder(new Color(30,202,116),3));  
                                  traceIndex[traceCount++]=i-7;   
                                  break;
                                }
                            }
                       }
                 
             break;    
         }

      }
     
/**************************************************************************************************************/
/**************************************************************************************************************/     
      
   }
     
     public int checkPointsR(int points)
     {
         if(points>=0 && points<=7)return 7;
         else if(points>=8 && points<=15)return 15;
         else if(points>=16 && points<=23)return 23;
         else if(points>=24 && points<=31)return 31;
         else if(points>=32 && points<=39)return 39;
         else if(points>=40 && points<=47)return 47;
         else if(points>=48 && points<=55)return 55;
         else if(points>=56 && points<=63)return 63;
         return 0; 
     }
     public int checkPointsL(int points)
     {
         if(points>=0 && points<=7)return 0;
         else if(points>=8 && points<=15)return 8;
         else if(points>=16 && points<=23)return 16;
         else if(points>=24 && points<=31)return 24;
         else if(points>=32 && points<=39)return 32;
         else if(points>=40 && points<=47)return 40;
         else if(points>=48 && points<=55)return 48;
         else if(points>=56 && points<=63)return 56;
         return 0; 
     }
     
     public void checkPositionW(int i)
     {
       for(int l=0;l<16;l++)
       {
           if((ImageIcon)boxLabel[i].getIcon()==brownCopy[l])
           {
               if((ImageIcon)boxLabel[i].getIcon()==brownCopy[3])
               { 
                 kingFocus=1;  
                 kingColor=boxLabel[i].getBackground();  
                 boxLabel[i].setBorder(BorderFactory.createLineBorder(new Color(30,202,116),3));  
                 boxLabel[i].setBackground(new Color(232,51,79));
                 traceIndex[traceCount++]=i;   
                 break;
               }
               else
               {  
                boxLabel[i].setBorder(BorderFactory.createLineBorder(new Color(30,202,116),3));  
                traceIndex[traceCount++]=i;   
                break;
               }
           }
        }
     }
     public void checkPositionB(int i)
     {
       for(int l=0;l<16;l++)
       {
         if((ImageIcon)boxLabel[i].getIcon()==whiteCopy[l])
         { 
           if((ImageIcon)boxLabel[i].getIcon()==whiteCopy[3])
           {
             kingFocus=2;    
             kingColor=boxLabel[i].getBackground();    
             boxLabel[i].setBorder(BorderFactory.createLineBorder(new Color(30,202,116),3));  
             boxLabel[i].setBackground(new Color(232,51,79));
             traceIndex[traceCount++]=i;   
             break;
           }
           else if((ImageIcon)boxLabel[i].getIcon()==whiteCopy[l])
           {
             boxLabel[i].setBorder(BorderFactory.createLineBorder(new Color(30,202,116),3));  
             traceIndex[traceCount++]=i;   
             break;
           }
         }
        } 
     }
  
  
     public int checkMoves(int index)
     {
        for(int i=0;i<traceCount;i++)
        {
            if(index==traceIndex[i])
                return 1;
        } 
        return 0;
     }
  
     public void dSelectIndex()
     {
        for(int i=0;i<traceCount;i++)
        {
             boxLabel[traceIndex[i]].setBorder(null);
                  if((ImageIcon)boxLabel[traceIndex[i]].getIcon()==brownCopy[3] && kingFocus==1)
                  {boxLabel[traceIndex[i]].setBackground(kingColor);kingFocus=0; }
                  else if((ImageIcon)boxLabel[traceIndex[i]].getIcon()==whiteCopy[3] && kingFocus==2)
                  {boxLabel[traceIndex[i]].setBackground(kingColor);kingFocus=0;}
             traceIndex[i]=0;
        }
        traceCount=0;
     }
     
     
    public boolean checkChance(int i)
    {
                      for(int k=0;k<16;k++)
                      {
                         if((ImageIcon)boxLabel[i].getIcon()==brownCopy[k] && playerChance==1)
                            return true;
                         else if((ImageIcon)boxLabel[i].getIcon()==whiteCopy[k] && playerChance==2)
                            return true;
                      }
       return false;
    }
    
    public void storeChance()
    {
       playerCArr[playerCCount++]=playerChance;
    }
    
    public void checkKing()
    {
      if(playerChance==1)
      {
     //     for(int i=bKingPosition+8;boxLabel[i]!=);
      }
      else if(playerChance==2){}
    }
     
}