package dragonball.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.sun.glass.ui.InvokeLaterDispatcher.InvokeLaterSubmitter;

import dragonball.model.attack.Attack;
import dragonball.model.attack.MaximumCharge;
import dragonball.model.attack.SuperAttack;
import dragonball.model.attack.SuperSaiyan;
import dragonball.model.attack.UltimateAttack;
import dragonball.model.battle.Battle;
import dragonball.model.character.fighter.Earthling;
import dragonball.model.character.fighter.Fighter;
import dragonball.model.character.fighter.Frieza;
import dragonball.model.character.fighter.Majin;
import dragonball.model.character.fighter.Namekian;
import dragonball.model.character.fighter.NonPlayableFighter;
import dragonball.model.character.fighter.PlayableFighter;
import dragonball.model.character.fighter.Saiyan;

public class BattleMode extends JFrame {

	JButton attack;
	JButton block;
	JButton senzu;
	JButton super1;
	JButton super2;
	JButton super3;
	JButton super4;
	JButton ultimate1;
	JButton ultimate2;

	JLabel fighter;
	JLabel opponent;
	JLabel fiLevel;
	JLabel opLevel;
	JLabel fiHp;
	JLabel opHp;
	JLabel fiKi;
	JLabel opKi;
	JLabel fiStam;
	JLabel opStam;
	JLabel current;
	JLabel player;
	JLabel enemy;
	JLabel hitFoe;
	JLabel hitMe;

	Battle b;

	public BattleMode(Battle b, JButton attack, JButton super1, JButton super2,
			JButton super3, JButton super4, JButton ultimate1,
			JButton ultimate2, JButton block, JButton senzu) {
		this.setTitle("Dragon Ball Z : Battle");
		this.setVisible(true);
		this.setSize(1300, 700);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		PlayableFighter p = (PlayableFighter) b.getMe();
		NonPlayableFighter e = (NonPlayableFighter) b.getFoe();

		JLabel setting = new JLabel();
		setting.setBounds(0, 0, 1300, 700);
		setting.setIcon(resize("area.jpg"));
		this.add(setting);

		fighter = new JLabel("Name: " + p.getName());
		fighter.setBounds(80, 80, 250, 25);
		fighter.setForeground(Color.BLUE);
		fighter.setFont(new Font("Name: " + p.getName(), Font.BOLD, 20));
		setting.add(fighter);

		opponent = new JLabel("Name: " + e.getName());
		opponent.setBounds(1000, 80, 250, 20);
		opponent.setForeground(Color.RED);
		opponent.setFont(new Font("Name: " + e.getName(), Font.BOLD, 20));
		setting.add(opponent);

		fiHp = new JLabel("Health Points: " + p.getHealthPoints());
		fiHp.setBounds(80, 110, 250, 20);
		fiHp.setForeground(Color.BLUE);
		fiHp.setFont(new Font("Health Points: " + p.getHealthPoints(),
				Font.BOLD, 20));
		setting.add(fiHp);

		opHp = new JLabel("Health Points: " + e.getHealthPoints());
		opHp.setBounds(1000, 110, 250, 20);
		opHp.setForeground(Color.RED);
		opHp.setFont(new Font("Health Points: " + e.getHealthPoints(),
				Font.BOLD, 20));
		setting.add(opHp);

		fiLevel = new JLabel("Level: " + p.getLevel());
		fiLevel.setBounds(80, 140, 200, 20);
		fiLevel.setForeground(Color.BLUE);
		fiLevel.setFont(new Font("Level: " + p.getLevel(), Font.BOLD, 20));
		setting.add(fiLevel);

		opLevel = new JLabel("Level: " + e.getLevel());
		opLevel.setBounds(1000, 140, 250, 20);
		opLevel.setForeground(Color.RED);
		opLevel.setFont(new Font("Level: " + e.getLevel(), Font.BOLD, 20));
		setting.add(opLevel);

		fiKi = new JLabel("Ki: " + p.getKi());
		fiKi.setBounds(80, 170, 250, 20);
		fiKi.setForeground(Color.BLUE);
		fiKi.setFont(new Font("Ki: " + p.getKi(), Font.BOLD, 20));
		setting.add(fiKi);

		opKi = new JLabel("Ki: " + e.getKi());
		opKi.setBounds(1000, 170, 250, 20);
		opKi.setForeground(Color.RED);
		opKi.setFont(new Font("Ki: 3", Font.BOLD, 20));
		setting.add(opKi);

		fiStam = new JLabel("Stamina: " + p.getStamina());
		fiStam.setBounds(80, 200, 250, 20);
		fiStam.setForeground(Color.BLUE);
		fiStam.setFont(new Font("Stamina: " + p.getStamina(), Font.BOLD, 20));
		setting.add(fiStam);

		opStam = new JLabel("Stamina: " + e.getStamina());
		opStam.setBounds(1000, 200, 250, 20);
		opStam.setForeground(Color.RED);
		opStam.setFont(new Font("Stamina: " + e.getStamina(), Font.BOLD, 20));
		setting.add(opStam);

		current = new JLabel("Current Attacker: "
				+ ((Fighter) b.getAttacker()).getName());
		current.setBounds(500, 100, 300, 25);
		current.setForeground(Color.ORANGE);
		current.setFont(new Font("Current Attacker: "
				+ ((Fighter) b.getAttacker()).getName(), Font.BOLD, 20));
		setting.add(current);

		attack.setText("Physical Attack");
		attack.setBounds(580, 530, 140, 20);
		attack.setForeground(Color.ORANGE);
		attack.setFont(new Font("Level: " + p.getLevel(), Font.BOLD, 12));
		setting.add(attack);

		senzu.setText("Recover");
		senzu.setBounds(600, 630, 100, 20);
		senzu.setForeground(Color.ORANGE);
		setting.add(senzu);

		ArrayList<SuperAttack> s = p.getSuperAttacks();
		ArrayList<UltimateAttack> u = p.getUltimateAttacks();
		String[] sa = new String[4];
		String[] ua = new String[2];
		for (int i = 0; i < 4; i++) {
			if (i >= s.size()) {
				sa[i] = null;
			} else {
				sa[i] = s.get(i).getName();
			}
		}

		for (int i = 0; i < 2; i++) {
			if (i >= u.size()) {
				ua[i] = null;
			} else {
				ua[i] = u.get(i).getName();
			}
		}

		block.setText("Block");
		block.setBounds(600, 650, 100, 20);
		block.setForeground(Color.ORANGE);
		setting.add(block);

		if (sa[0] != null) {
			super1.setText(sa[0]);
			super1.setEnabled(true);

		} else {
			super1.setText("Not Assigned");
			super1.setEnabled(false);
		}
		super1.setBounds(530, 555, 120, 20);
		super1.setFont(new Font("Level: " + p.getLevel(), Font.BOLD, 10));
		setting.add(super1);

		if (sa[1] != null) {
			super2.setText(sa[1]);
			super2.setEnabled(true);

		} else {
			super2.setText("Not Assigned");
			super2.setEnabled(false);
		}
		super2.setBounds(650, 555, 120, 20);
		super2.setFont(new Font("Level: " + p.getLevel(), Font.BOLD, 10));
		setting.add(super2);

		if (sa[2] != null) {
			super3.setText(sa[2]);
			super3.setEnabled(true);

		} else {
			super3.setText("Not Assigned");
			super3.setEnabled(false);
		}
		super3.setBounds(530, 575, 120, 20);
		super3.setFont(new Font("Level: " + p.getLevel(), Font.BOLD, 10));
		setting.add(super3);

		if (sa[3] != null) {
			super4.setText(sa[3]);
			super4.setEnabled(true);

		} else {
			super4.setText("Not Assigned");
			super4.setEnabled(false);
		}
		super4.setBounds(650, 575, 120, 20);
		super4.setFont(new Font("Level: " + p.getLevel(), Font.BOLD, 10));
		setting.add(super4);

		if (ua[0] != null) {
			ultimate1.setText(ua[0]);
			ultimate1.setEnabled(true);

		} else {
			ultimate1.setText("Not Assigned");
			ultimate1.setEnabled(false);
		}
		ultimate1.setBounds(530, 600, 120, 20);
		ultimate1.setFont(new Font("Level: " + p.getLevel(), Font.BOLD, 10));
		setting.add(ultimate1);

		if (ua[1] != null) {
			ultimate2.setText(ua[1]);
			ultimate2.setEnabled(true);

		} else {
			ultimate2.setText("Not Assigned");
			ultimate2.setEnabled(false);
		}
		ultimate2.setBounds(650, 600, 120, 20);
		ultimate2.setFont(new Font("Level: " + p.getLevel(), Font.BOLD, 10));
		setting.add(ultimate2);

		player = new JLabel();
		player.setBounds(110, 250, 300, 400);
		if (((PlayableFighter) b.getMe()) instanceof Namekian)
			player.setIcon(resizeNam("Namekian.png"));
		if (((PlayableFighter) b.getMe()) instanceof Saiyan)
			player.setIcon(resizeSaiyan("Saiyan.png"));
		if (((PlayableFighter) b.getMe()) instanceof Earthling)
			player.setIcon(resizeEarth("Earthling.png"));
		if (((PlayableFighter) b.getMe()) instanceof Frieza)
			player.setIcon(resizeFri("Frieza.png"));
		if (((PlayableFighter) b.getMe()) instanceof Majin)
			player.setIcon(resizeMaj("Majin.png"));

		setting.add(player);

		enemy = new JLabel();
		enemy.setBounds(890, 250, 300, 390);
		if (((Fighter) b.getFoe()).getName() == "Saibaman")
			enemy.setIcon(resizeSaiba(((Fighter) b.getFoe()).getName() + ".png"));
		else if (((Fighter) b.getFoe()).getName() == "Jinkoumen")
			enemy.setIcon(resizeSaiba(((Fighter) b.getFoe()).getName() + ".png"));
		else{
			try{
			enemy.setIcon(resizeEnem(((Fighter) b.getFoe()).getName() + ".png"));
			}
			catch(Exception e3){
			enemy.setIcon(resizeEnem(("Rasberry.png")));
			}
		}

		setting.add(enemy);

		this.b = b;

		/*hitFoe = new JLabel();
		hitFoe.setBounds(890, 250, 300, 390);
		setting.add(hitFoe);

		hitMe = new JLabel();
		hitMe.setBounds(110, 250, 300, 400);
		setting.add(hitMe);*/

		this.revalidate();
	}

	public ImageIcon resize(String path) {
		Image img;
		try {
			img = ImageIO.read(new File(path));
			Image I = img.getScaledInstance(1300, 700, Image.SCALE_SMOOTH);
			return new ImageIcon(I);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ImageIcon resizeEnem(String path) {
		Image img;
		try {

			img = ImageIO.read(new File(path));
			Image I = img.getScaledInstance(230, 380, Image.SCALE_SMOOTH);
			return new ImageIcon(I);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ImageIcon resizeSaiyan(String path) {
		Image img;
		try {
			img = ImageIO.read(new File(path));
			Image I = img.getScaledInstance(300, 350, Image.SCALE_SMOOTH);
			return new ImageIcon(I);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ImageIcon resizeNam(String path) {
		Image img;
		try {
			img = ImageIO.read(new File(path));
			Image I = img.getScaledInstance(300, 350, Image.SCALE_SMOOTH);
			return new ImageIcon(I);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ImageIcon resizeFri(String path) {
		Image img;
		try {
			img = ImageIO.read(new File(path));
			Image I = img.getScaledInstance(250, 300, Image.SCALE_SMOOTH);
			return new ImageIcon(I);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ImageIcon resizeEarth(String path) {
		Image img;
		try {
			img = ImageIO.read(new File(path));
			Image I = img.getScaledInstance(300, 350, Image.SCALE_SMOOTH);
			return new ImageIcon(I);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ImageIcon resizeMaj(String path) {
		Image img;
		try {
			img = ImageIO.read(new File(path));
			Image I = img.getScaledInstance(230, 300, Image.SCALE_SMOOTH);
			return new ImageIcon(I);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ImageIcon resizeSaiba(String path) {
		Image img;
		try {
			img = ImageIO.read(new File(path));
			Image I = img.getScaledInstance(150, 155, Image.SCALE_SMOOTH);
			return new ImageIcon(I);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void updateText() {
		PlayableFighter p = (PlayableFighter) b.getMe();
		NonPlayableFighter e = (NonPlayableFighter) b.getFoe();
		fiHp.setText("Health Points: " + p.getHealthPoints());
		opHp.setText("Health Points: " + e.getHealthPoints());
		fiKi.setText("Ki: " + p.getKi());
		opKi.setText("Ki: " + e.getKi());
		fiStam.setText("Stamina: " + p.getStamina());
		opStam.setText("Stamina: " + e.getStamina());
		current.setText("Current Attacker: "
				+ ((Fighter) b.getAttacker()).getName());
		// check if the player is a Saiyan and if he is transformed, if so,
		// update the image, if he is a Saiyan and not transformed, make the
		// image the normal Saiyan image
		if (p instanceof Saiyan) {
			if (((Saiyan) p).isTransformed()) {
				player.setIcon(resizeSaiyan("Saiyan (Transformed).png"));
			} else {
				player.setIcon(resizeSaiyan("Saiyan.png"));
			}
		}
	}

	/*public void animationAttack(Attack a) throws InterruptedException {
		Timer t = new Timer();
		if (b.getAttacker().equals(b.getMe())) {
			if (a instanceof SuperSaiyan) {
				hitMe.setIcon(new ImageIcon("Super Sayian.gif"));
//				Thread.sleep(700000000);
				hitMe.setIcon(null);
			} else if (a instanceof MaximumCharge) {
				hitMe.setIcon(new ImageIcon("Maximum Charge.gif"));
//				Thread.sleep(5000);
				hitMe.setIcon(null);
			} else if (a instanceof SuperAttack) {
				hitFoe.setIcon(new ImageIcon("Super Attack.gif"));
//				Thread.sleep(5000);
				hitFoe.setIcon(null);
			} else if (a instanceof UltimateAttack) {
				hitFoe.setIcon(new ImageIcon("Ultimate Attack.gif"));
//				Thread.sleep(700000000);
				hitFoe.setIcon(null);

			} else {
				hitFoe.setIcon(new ImageIcon("Physical Attack.gif"));
				revalidate();
				repaint();
//				t.schedule(task, 100);
				hitFoe.setIcon(null);
				

				System.out.println("used");
			}
		} else {

			if (a instanceof SuperSaiyan) {
				hitFoe.setIcon(new ImageIcon("Super Sayian.gif"));
//				Thread.sleep(70);
				hitFoe.setIcon(null);
			} else if (a instanceof MaximumCharge) {
				hitFoe.setIcon(new ImageIcon("Maximum Charge.gif"));
//				Thread.sleep(50);
				hitFoe.setIcon(null);
			} else if (a instanceof SuperAttack) {
				hitMe.setIcon(new ImageIcon("Super Attack.gif"));
//				Thread.sleep(50);
				hitMe.setIcon(null);
			} else if (a instanceof UltimateAttack) {
				hitMe.setIcon(new ImageIcon("Ultimate Attack.gif"));
//				Thread.sleep(70);
				hitMe.setIcon(null);

			} else {
				hitMe.setIcon(new ImageIcon("Physical Attack.gif"));
//				Thread.sleep(60);
				hitMe.setIcon(null);
			}
		}
	}*/

	public Battle getB() {
		return b;
	}

}
