package dragonball.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import dragonball.model.dragon.DragonWish;

public class DragonMode extends JFrame {

	JButton grant;

	JComboBox<String> wishes;

	private DragonWish[] wish;
	

	

	public DragonWish[] getWish() {
		return wish;
	}

	public DragonMode(JButton grant, DragonWish[] wish) {

		this.setTitle("Dragon Ball Z Start Menu");
		this.setVisible(true);
		this.setSize(1000, 700);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);

		JLabel theme = new JLabel();
		theme.setBounds(0, 0, 1000, 700);
		theme.setLayout(null);
		theme.setIcon(resize("shenron.jpg"));
		this.add(theme);

		JLabel label1 = new JLabel("Choose Your Wish !");
		label1.setBounds(650, 525, 250, 30);
		label1.setFont(label1.getFont().deriveFont(15.0f));
		label1.setForeground(Color.ORANGE);
		theme.add(label1);

		wishes = new JComboBox<String>();
		wishes.setBounds(650, 550, 200, 25);
		theme.add(wishes);
		wishes.addItem(wish[0].getSenzuBeans() + " Senzu Beans");
		wishes.addItem(wish[1].getAbilityPoints() + " Ability Beans");
		wishes.addItem("Super Attack "+ wish[2].getSuperAttack().getName());
		wishes.addItem("Ultimate Attack " + wish[3].getUltimateAttack().getName());

		grant.setBounds(120, 550, 250, 50);
		grant.setIcon(resizeIcon("dragonball.png"));
		grant.setForeground(Color.ORANGE);
		grant.setFont(grant.getFont().deriveFont(20.0f));
		grant.setOpaque(false);
		grant.setContentAreaFilled(false);
		grant.setBorderPainted(false);
		theme.add(grant);
		this.wish = wish;

		this.revalidate();
		this.repaint();

	}

	public ImageIcon resize(String path) {
		Image img;
		try {
			img = ImageIO.read(new File(path));
			Image I = img.getScaledInstance(1000, 700, Image.SCALE_SMOOTH);
			return new ImageIcon(I);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ImageIcon resizeIcon(String path) {
		Image img;
		try {
			img = ImageIO.read(new File(path));
			Image I = img.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
			return new ImageIcon(I);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public JComboBox<String> getWishes() {
		return wishes;
	}

}
