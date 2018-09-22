package dragonball.view;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class test extends JFrame{
	JLabel test;
	
	public test(){
		this.setTitle("Dragon Ball Z : Battle");
		this.setVisible(true);
		this.setSize(550, 700);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		
		test=new JLabel();
		test.setIcon(new ImageIcon("celeb.gif"));
		this.add(test);
		
		this.revalidate();
		this.repaint();
		
		
	}
	public static void main(String[] args) {
		test t=new test();
	}

}
