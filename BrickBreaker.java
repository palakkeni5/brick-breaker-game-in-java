import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class BrickBreaker extends JPanel implements KeyListener,
  ActionListener, Runnable {
 // movement keys..
 static boolean right = false;
 static boolean left = false;

 int ballx = 120;
 int bally = 350;
 
 int batx = 300;
 int baty = 500;
 
 int brickx = 200;
 int bricky = 100;
 int total_bricks=21;
 int brickBreadth = 30;
 int brickHeight = 20;
 // declaring ball,paddle,bricks
 Rectangle Ball = new Rectangle(ballx, bally, 12, 12);
 Rectangle Bat = new Rectangle(batx, baty, 100, 8);
 Rectangle[] Brick = new Rectangle[total_bricks];

//direction of ball
int movex = -1;
int movey = -2;

boolean ballFallDown = false;
boolean bricksOver = false;
int count = 0;
String status;


  // declaring ball, paddle,bricks

 public void paint(Graphics g) {
  //screen color
	 
  g.setColor(Color.black);
  g.fillRect(0, 0, 700, 600);
  //creating ball
  g.setColor(Color.yellow);
  g.fillOval(Ball.x, Ball.y, Ball.width, Ball.height);
  //creting paddle
  g.setColor(Color.green);
  g.fill3DRect(Bat.x, Bat.y, Bat.width, Bat.height, true);
  //creating borders
  g.setColor(Color.red);
  g.fillRect(0, 0, 3, 592);
  g.fillRect(0, 0, 692, 3);
  g.fillRect(691, 0, 3, 596);
  
  for (int i = 0; i < Brick.length; i++) {
   if (Brick[i] != null) {
    g.fill3DRect(Brick[i].x, Brick[i].y, Brick[i].width,
      Brick[i].height, true);
   }
  }
  if (ballFallDown == true || bricksOver == true) {
	   
	   
	   Font f = new Font("Arial", Font.BOLD, 50);
	   g.setFont(f);
	   g.setColor(Color.white);
	   g.drawString(status, 70, 300);
	   ballFallDown = false;
	   bricksOver = false;
 }
}
  	


public void run() {

     createBricks();
  
  
  
  while (true) {

   for (int i = 0; i < Brick.length; i++) {
    if (Brick[i] != null) {
     if (Brick[i].intersects(Ball)) {
      Brick[i] = null;
      //movex = -movex;
      movey = -movey;
      count++;
     }// end of 2nd if..
    }// end of 1st if..
   }// end of for loop..



   if (count == Brick.length) {// check if ball hits all bricks
    bricksOver = true;
    status = "YOU WON THE GAME";
   
    repaint();
    return;
   }
  
   repaint();
   Ball.x += movex;
   Ball.y += movey;

   if (left == true) {

    Bat.x -= 4;
    right = false;
   }
   if (right == true) {
    Bat.x += 4;
    left = false;
   }
   if (Bat.x <= 4) {
    Bat.x = 4;
   } else if (Bat.x >= 600) {
    Bat.x = 600;
   }
   // /===== Ball reverses when strikes the bat
   if (Ball.intersects(Bat)) {
    movey = -movey;
    // if(Ball.y + Ball.width >=Bat.y)
   }
   // //=====================================
   // ....ball reverses when touches left and right boundary
   if (Ball.x <= 0 || Ball.x + Ball.height >= 700) {
    movex = -movex;
   }// if ends here
   if (Ball.y <= 0) {// ////////////////|| bally + Ball.height >= 250
    movey = -movey;
   }// if ends here.....
   if (Ball.y >= 700) {// when ball falls below bat game is over...
    ballFallDown = true;
    status = "YOU LOST THE GAME";
    repaint();
    return;
//    System.out.print("game");
   }
   try {
    Thread.sleep(10);
   } catch (Exception ex) {
   }// try catch ends here

  }// while loop ends here

 }

 // loop ends here

 // ///////..... HANDLING KEY EVENTS................//
 @Override
 public void keyPressed(KeyEvent e) {
  int keyCode = e.getKeyCode();
  if (keyCode == KeyEvent.VK_LEFT) {
   left = true;
   // System.out.print("left");
  }

  if (keyCode == KeyEvent.VK_RIGHT) {
   right = true;
   // System.out.print("right");
  }
 }

 public void keyReleased(KeyEvent e) {
  int keyCode = e.getKeyCode();
  if (keyCode == KeyEvent.VK_LEFT) {
   left = false;
  }

  if (keyCode == KeyEvent.VK_RIGHT) {
   right = false;
  }
 }

 @Override
 public void keyTyped(KeyEvent arg0) {

 }

 @Override
 public void actionPerformed(ActionEvent e) {}
 

  public void createBricks(){
    
      
      for (int i = 0; i < Brick.length; i++) {
    	   Brick[i] = new Rectangle(brickx, bricky, brickBreadth, brickHeight);
    	  if(i%2==1)
    	  {
    		  brickx+=brickBreadth;
    	  }
    	  if(i%2==0)
    	  {
    		  bricky+=brickHeight;
    	  }
      
       /*if (i == 5) {
        brickx = 70;
        bricky = (bricky + brickHeight + 2);
       
       }
       if (i == 9) {
        brickx = 100;
        bricky = (bricky + brickHeight + 2);
      
       }*/
      
       
      }
 }
}

