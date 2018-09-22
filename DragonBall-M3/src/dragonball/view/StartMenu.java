package dragonball.view;

import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class StartMenu extends JFrame {
	JLabel theme;

	JLabel playername;
	JLabel fightername;

	JTextField enterYourName;
	JTextField fighterName;

	JCheckBox saiyan;
	JCheckBox earthling;
	JCheckBox namekian;
	JCheckBox frieza;
	JCheckBox majin;

	JButton create;
	JButton loadGame;

	public StartMenu(JButton create, JButton loadGame,
			JTextField enterYourName, JTextField fighterName, JCheckBox saiyan,
			JCheckBox earthling, JCheckBox namekian, JCheckBox frieza,
			JCheckBox majin) {
		this.setTitle("Dragon Ball Z Start Menu");
		this.setVisible(true);
		this.setSize(1000, 700);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);

		theme = new JLabel();
		theme.setBounds(0, 0, 1000, 700);
		theme.setIcon(resize("startMenu.jpg"));
		this.add(theme);

		playername = new JLabel("Enter your Name !");
		playername.setBounds(30, 170, 150, 50);
		playername.setForeground(Color.ORANGE);
		theme.add(playername);

		fightername = new JLabel("Enter your Fighter's Name !");
		fightername.setBounds(30, 220, 170, 50);
		fightername.setForeground(Color.ORANGE);
		theme.add(fightername);

		create.setBounds(150, 500, 200, 50);
		create.setIcon(resizeicon("dragonball.png"));
		create.setForeground(Color.ORANGE);
		create.setFont(create.getFont().deriveFont(15.0f));
		create.setOpaque(false);
		create.setContentAreaFilled(false);
		create.setBorderPainted(false);
		theme.add(create);

		loadGame.setBounds(140, 550, 200, 50);
		loadGame.setIcon(resizeicon("dragonball.png"));
		loadGame.setForeground(Color.ORANGE);
		loadGame.setFont(loadGame.getFont().deriveFont(15.0f));
		loadGame.setOpaque(false);
		loadGame.setContentAreaFilled(false);
		loadGame.setBorderPainted(false);
		theme.add(loadGame);

		enterYourName.setBounds(200, 180, 150, 25);
		theme.add(enterYourName);

		fighterName.setBounds(200, 230, 150, 25);
		theme.add(fighterName);

		saiyan.setBounds(200, 260, 150, 50);
		saiyan.setOpaque(false);
		saiyan.setIcon(resizeicon("Saiyan map.png"));
		saiyan.setForeground(Color.ORANGE);
		theme.add(saiyan);

		earthling.setBounds(200, 300, 150, 50);
		earthling.setOpaque(false);
		earthling.setIcon(resizeicon("Earthling map.png"));
		earthling.setForeground(Color.ORANGE);
		theme.add(earthling);

		namekian.setBounds(200, 350, 150, 50);
		namekian.setOpaque(false);
		namekian.setIcon(resizeicon("Namekian map.png"));
		namekian.setForeground(Color.ORANGE);
		theme.add(namekian);

		frieza.setBounds(200, 400, 150, 50);
		frieza.setOpaque(false);
		frieza.setIcon(resizeicon("Frieza map.png"));
		frieza.setForeground(Color.ORANGE);
		theme.add(frieza);

		majin.setBounds(200, 440, 150, 50);
		majin.setOpaque(false);
		majin.setIcon(resizeicon("Majin map.png"));
		majin.setForeground(Color.ORANGE);
		theme.add(majin);

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

	public ImageIcon resizeicon(String path) {
		Image img;
		try {
			img = ImageIO.read(new File(path));
			Image I = img.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
			return new ImageIcon(I);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
