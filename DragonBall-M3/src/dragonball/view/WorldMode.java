package dragonball.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dragonball.model.character.fighter.Earthling;
import dragonball.model.character.fighter.Frieza;
import dragonball.model.character.fighter.Namekian;
import dragonball.model.character.fighter.Saiyan;
import dragonball.model.player.Player;

public class WorldMode extends JFrame {

	JPanel world;

	JLabel theme;
	JLabel playName;
	JLabel dragonBalls;
	JLabel senzuBeans;
	JLabel fighterName;
	JLabel level;
	JLabel abilityP;
	JLabel maxHp;
	JLabel maxStam;
	JLabel maxKi;
	JLabel physDam;
	JLabel blastDam;
	JLabel ella3eeb;
	JLabel superAtk;
	JLabel ultiAtk;

	JTextField playerName;
	JTextField FighterName;

	JComboBox<String> fighters;
	JComboBox<String> superAttack;
	JComboBox<String> superAttack2;
	JComboBox<String> ultimateAttack;
	JComboBox<String> ultimateAttack2;

	JLabel[][] mapLabel;

	JButton createAn;
	JButton switchFi;
	JButton switchSup;
	JButton switchUlt;
	JButton upgrade;
	JButton save;
	JButton up;
	JButton down;
	JButton right;
	JButton left;

	Player player;
	String attacksLearned;

	public WorldMode(JButton createAn, JButton switchFi,JButton switchSup,JButton switchUlt, JButton upgrade,
			JButton save, JButton up, JButton down, JButton left,
			JButton right, Player player) {

		this.setTitle("Dragon Ball Z : World");
		this.setSize(1360, 730);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);

		theme = new JLabel();
		theme.setIcon(resize("world.png"));
		theme.setLayout(null);
		this.add(theme);

		world = new JPanel();
		world.setBounds(0, 0, 1100, 700);
		world.setLayout(new GridLayout(10, 10));
		world.setOpaque(false);
		theme.add(world);

		playName = new JLabel("Player Name : " + player.getName());
		playName.setBounds(1110, 10, 200, 25);
		playName.setForeground(Color.ORANGE);
		theme.add(playName);

		dragonBalls = new JLabel("Dragon Balls: " + player.getDragonBalls());
		dragonBalls.setBounds(1110, 40, 200, 25);
		dragonBalls.setForeground(Color.ORANGE);
		theme.add(dragonBalls);

		senzuBeans = new JLabel("Senzu Beans: " + player.getSenzuBeans());
		senzuBeans.setBounds(1110, 70, 200, 25);
		senzuBeans.setForeground(Color.ORANGE);
		theme.add(senzuBeans);

		fighters = new JComboBox<String>();
		for(int i = 0; i<player.getFighters().size(); i++){
			fighters.addItem(player.getFighters().get(i).getName());
		}
		fighters.setBounds(1110, 100, 100, 20);
		theme.add(fighters);

		switchFi.setText("switch");
		switchFi.setBounds(1110, 125, 200, 20);
		switchFi.setIcon(resizeicon("dragonball.png"));
		switchFi.setForeground(Color.ORANGE);
		switchFi.setFont(switchFi.getFont().deriveFont(10.0f));
		// switch1.setOpaque(false);
		// switch1.setContentAreaFilled(false);
		// switch1.setBorderPainted(false);
		theme.add(switchFi);

		fighterName = new JLabel("Fighter: "
				+ player.getActiveFighter().getName());
		fighterName.setBounds(1110, 145, 200, 25);
		fighterName.setForeground(Color.BLUE);
		theme.add(fighterName);

		level = new JLabel("Level: " + player.getActiveFighter().getLevel());
		level.setBounds(1110, 175, 200, 25);
		level.setForeground(Color.BLUE);
		theme.add(level);

		superAtk = new JLabel("Super Attacks");
		superAtk.setBounds(1110, 200, 150, 20);
		superAtk.setForeground(Color.BLUE);
		theme.add(superAtk);

		superAttack = new JComboBox<String>();
		superAttack.setBounds(1110, 225, 150, 20);
		for (int i = 0; i < player.getSuperAttacks().size(); i++) {
			superAttack.addItem(player.getSuperAttacks().get(i).getName());
		}
		theme.add(superAttack);

		superAttack2 = new JComboBox<String>();
		superAttack2.setBounds(1110, 250, 200, 20);
		superAttack2.addItem("null");
		for (int i = 0; i < player.getActiveFighter().getSuperAttacks().size(); i++) {
			superAttack2.addItem(player.getActiveFighter().getSuperAttacks()
					.get(i).getName());
		}
		theme.add(superAttack2);

		switchSup.setText("switch su");
		switchSup.setBounds(1110, 275, 200, 20);
		switchSup.setIcon(resizeicon("dragonball.png"));
		switchSup.setForeground(Color.ORANGE);
		switchSup.setFont(switchSup.getFont().deriveFont(10.0f));
		// switch1.setOpaque(false);
		// switch1.setContentAreaFilled(false);
		// switch1.setBorderPainted(false);
		if (player.getSuperAttacks().isEmpty()) {
			switchSup.setEnabled(false);
		}
		theme.add(switchSup);

		ultiAtk = new JLabel("Ultimate Attack");
		ultiAtk.setBounds(1110, 300, 150, 20);
		theme.add(ultiAtk);

		ultimateAttack = new JComboBox<String>();
		ultimateAttack.setBounds(1110, 325, 150, 20);
		for (int i = 0; i < player.getUltimateAttacks().size(); i++) {
			ultimateAttack.addItem(player.getUltimateAttacks().get(i).getName());
		}
		theme.add(ultimateAttack);
		
		

		ultimateAttack2 = new JComboBox<String>();
		ultimateAttack2.setBounds(1110, 350, 150, 20);
		ultimateAttack2.addItem("null");
		for (int i = 0; i < player.getActiveFighter().getUltimateAttacks().size(); i++) {
			ultimateAttack2.addItem(player.getActiveFighter().getUltimateAttacks()
					.get(i).getName());
		}
		theme.add(ultimateAttack2);

		switchUlt.setText("switch ult");
		switchUlt.setBounds(1110, 375, 200, 20);
		switchUlt.setIcon(resizeicon("dragonball.png"));
		switchUlt.setForeground(Color.ORANGE);
		switchUlt.setFont(switchUlt.getFont().deriveFont(10.0f));
		// switch1.setOpaque(false);
		// switch1.setContentAreaFilled(false);
		// switch1.setBorderPainted(false);
		if (player.getUltimateAttacks().isEmpty()) {
			switchUlt.setEnabled(false);
		}
		theme.add(switchUlt);

		createAn.setBounds(1110, 420, 200, 25);
		createAn.setIcon(resizeicon("dragonball.png"));
		createAn.setForeground(Color.ORANGE);
		createAn.setFont(createAn.getFont().deriveFont(10.0f));
		// createAn.setOpaque(false);
		// createAn.setContentAreaFilled(false);
		// createAn.setBorderPainted(false);
		theme.add(createAn);

		upgrade.setBounds(1110, 450, 200, 25);
		upgrade.setIcon(resizeicon("dragonball.png"));
		upgrade.setForeground(Color.ORANGE);
		upgrade.setFont(upgrade.getFont().deriveFont(10.0f));
		// upgrade.setOpaque(false);
		// upgrade.setContentAreaFilled(false);
		// upgrade.setBorderPainted(false);
		theme.add(upgrade);

		save.setBounds(1110, 480, 200, 25);
		save.setIcon(resizeicon("dragonball.png"));
		save.setForeground(Color.ORANGE);
		save.setFont(save.getFont().deriveFont(10.0f));
		// save.setOpaque(false);
		// save.setContentAreaFilled(false);
		// save.setBorderPainted(false);
		theme.add(save);

		up.setBounds(1200, 550, 50, 25);
		up.setForeground(Color.ORANGE);
		// up.setContentAreaFilled(false);
		// up.setBorderPainted(false);
		theme.add(up);

		down.setBounds(1190, 650, 75, 25);
		down.setForeground(Color.ORANGE);
		// down.setContentAreaFilled(false);
		// down.setBorderPainted(false);
		theme.add(down);

		left.setBounds(1110, 600, 100, 25);
		left.setForeground(Color.ORANGE);
		// left.setContentAreaFilled(false);
		// left.setBorderPainted(false);
		theme.add(left);

		right.setBounds(1250, 600, 100, 25);
		right.setForeground(Color.ORANGE);
		// right.setContentAreaFilled(false);
		// right.setBorderPainted(false);
		theme.add(right);

		this.player = player;

		mapLabel = new JLabel[10][10];

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				JLabel lulu = new JLabel();
				lulu.setSize(50, 10);
				world.add(lulu);
				mapLabel[i][j] = lulu;

			}
		}
		if (player.getActiveFighter() instanceof Saiyan)
			mapLabel[9][9].setIcon(resizeWorldFi("Saiyan map.png"));
		else if (player.getActiveFighter() instanceof Namekian)
			mapLabel[9][9].setIcon(resizeWorldFi("Namekian map.png"));
		else if (player.getActiveFighter() instanceof Frieza)
			mapLabel[9][9].setIcon(resizeWorldFi("Frieza map.png"));
		else if (player.getActiveFighter() instanceof Earthling)
			mapLabel[9][9].setIcon(resizeWorldFi("Earthling map.png"));
		else {
			mapLabel[9][9].setIcon(resizeWorldFi("Majin map.png"));
		}
		
		mapLabel[0][0].setText("Boss here");

		this.switchSup = switchSup;
		this.switchUlt = switchUlt;
		this.revalidate();
		this.repaint();

	}

	public ImageIcon resize(String path) {
		Image img;
		try {
			img = ImageIO.read(new File(path));
			Image I = img.getScaledInstance(1100, 730, Image.SCALE_SMOOTH);
			return new ImageIcon(I);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ImageIcon resizeSide(String path) {
		Image img;
		try {
			img = ImageIO.read(new File(path));
			Image I = img.getScaledInstance(260, 730, Image.SCALE_SMOOTH);
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
			Image I = img.getScaledInstance(18, 18, Image.SCALE_SMOOTH);
			return new ImageIcon(I);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ImageIcon resizeWorldFi(String path) {
		Image img;
		try {
			img = ImageIO.read(new File(path));
			Image I = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
			return new ImageIcon(I);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public JLabel[][] getEl5ara() {
		return mapLabel;
	}

	public void setEl5ara(JLabel[][] el5ara) {
		this.mapLabel = el5ara;
	}

	public void updateText() {
		playName.setText("Player Name : " + player.getName());
		dragonBalls.setText("Dragon Balls: " + player.getDragonBalls());
		senzuBeans.setText("Senzu Beans: " + player.getSenzuBeans());
		fighterName.setText("Fighter: " + player.getActiveFighter().getName());
		level.setText("Level: " + player.getActiveFighter().getLevel());
		superAttack.removeAllItems();
		superAttack2.removeAllItems();
		ultimateAttack.removeAllItems();
		ultimateAttack2.removeAllItems();
		fighters.removeAllItems();
		for(int i = 0; i<player.getFighters().size(); i++){
			fighters.addItem(player.getFighters().get(i).getName());
		}
		for (int i = 0; i < player.getSuperAttacks().size(); i++) {
			superAttack.addItem(player.getSuperAttacks().get(i).getName());
		}
		superAttack2.addItem("null");
		for (int i = 0; i < player.getActiveFighter().getSuperAttacks().size(); i++) {
			superAttack2.addItem(player.getActiveFighter().getSuperAttacks()
					.get(i).getName());
		}
		for (int i = 0; i < player.getUltimateAttacks().size(); i++) {
			ultimateAttack.addItem(player.getUltimateAttacks().get(i).getName());
		}
		ultimateAttack2.addItem("null");
		for (int i = 0; i < player.getActiveFighter().getUltimateAttacks().size(); i++) {
			ultimateAttack2.addItem(player.getActiveFighter().getUltimateAttacks()
					.get(i).getName());
		}
		if(!player.getSuperAttacks().isEmpty()){
			try{
			switchSup.setEnabled(true);
			}catch(Exception e){
				
			}
		}
		if(!player.getUltimateAttacks().isEmpty()){
			try{
			switchUlt.setEnabled(true);
				}catch(Exception e){
				
			}
		}
		
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public JComboBox<String> getFighters() {
		return fighters;
	}

	public JComboBox<String> getSuperAttack() {
		return superAttack;
	}

	public JComboBox<String> getSuperAttack2() {
		return superAttack2;
	}

	public JComboBox<String> getUltimateAttack() {
		return ultimateAttack;
	}

	public JComboBox<String> getUltimateAttack2() {
		return ultimateAttack2;
	}
	
}
