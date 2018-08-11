
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



class Main1{
public static void main(String[] args) {
  JFrame frame = new JFrame();
  BrickBreaker game = new BrickBreaker();
  frame.setBounds(10,10,700,600);
  frame.setTitle("WELCOME TO BRICKBREAKER GAME");
  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  frame.add(game);
  frame.setResizable(false);
  frame.setVisible(true);
  game.addKeyListener(game);
  game.setFocusable(true);
  Thread t = new Thread(game);
  t.start();

 }
}