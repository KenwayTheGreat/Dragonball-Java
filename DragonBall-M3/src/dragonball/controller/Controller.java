package dragonball.controller;

import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import dragonball.model.attack.Attack;
import dragonball.model.attack.PhysicalAttack;
import dragonball.model.attack.SuperAttack;
import dragonball.model.attack.UltimateAttack;
import dragonball.model.battle.Battle;
import dragonball.model.battle.BattleEvent;
import dragonball.model.battle.BattleEventType;
import dragonball.model.cell.Collectible;
import dragonball.model.character.fighter.Earthling;
import dragonball.model.character.fighter.Fighter;
import dragonball.model.character.fighter.Frieza;
import dragonball.model.character.fighter.Majin;
import dragonball.model.character.fighter.Namekian;
import dragonball.model.character.fighter.NonPlayableFighter;
import dragonball.model.character.fighter.PlayableFighter;
import dragonball.model.character.fighter.Saiyan;
import dragonball.model.dragon.Dragon;
import dragonball.model.dragon.DragonWish;
import dragonball.model.exceptions.DuplicateAttackException;
import dragonball.model.exceptions.MapIndexOutOfBoundsException;
import dragonball.model.exceptions.MaximumAttacksLearnedException;
import dragonball.model.exceptions.MissingFieldException;
import dragonball.model.exceptions.NotASaiyanException;
import dragonball.model.exceptions.NotEnoughAbilityPointsException;
import dragonball.model.exceptions.NotEnoughKiException;
import dragonball.model.exceptions.NotEnoughSenzuBeansException;
import dragonball.model.exceptions.UnknownAttackTypeException;
import dragonball.model.game.Game;
import dragonball.model.game.GameListener;
import dragonball.view.BattleMode;
import dragonball.view.CreateAnother;
import dragonball.view.DragonMode;
import dragonball.view.StartMenu;
import dragonball.view.Upgrade;
import dragonball.view.WorldMode;
import dragonball.model.player.Player;

public class Controller implements MouseListener, GameListener {

	JCheckBox saiyan;
	JCheckBox earthling;
	JCheckBox namekian;
	JCheckBox frieza;
	JCheckBox majin;
	JCheckBox hp;
	JCheckBox Pd;
	JCheckBox Bd;
	JCheckBox sta;
	JCheckBox ki;

	ButtonGroup group;

	JTextField enterYourName;
	JTextField fighterName;

	JButton create;
	JButton loadGame;
	JButton createAn;
	JButton switchFi;
	JButton upgrade;
	JButton save;
	JButton up;
	JButton down;
	JButton left;
	JButton right;
	JButton switchSup;
	JButton switchUlt;
	JButton phys;
	JButton sup1;
	JButton sup2;
	JButton sup3;
	JButton sup4;
	JButton ult1;
	JButton ult2;
	JButton block;
	JButton recover;
	JButton createAnother;
	JButton upgradeInt;
	JButton exit;
	JButton grant;

	JComboBox<String> fighters;
	JComboBox<String> superAtk;
	JComboBox<String> ultiAtk;

	StartMenu s;
	WorldMode w;
	CreateAnother cr;
	BattleMode b;
	DragonMode d;
	Upgrade u;
	Game g;

	String attacksLearned;

	public Controller() throws MissingFieldException,
			UnknownAttackTypeException {
		create = new JButton("Create Fighter");
		create.addMouseListener(this);

		loadGame = new JButton("Load Game");
		loadGame.addMouseListener(this);

		enterYourName = new JTextField();
		fighterName = new JTextField();

		saiyan = new JCheckBox("Saiyan");
		earthling = new JCheckBox("Earthling");
		namekian = new JCheckBox("Namekian");
		frieza = new JCheckBox("Frieza");
		majin = new JCheckBox("Majin");

		group = new ButtonGroup();
		group.add(saiyan);
		group.add(earthling);
		group.add(namekian);
		group.add(frieza);
		group.add(majin);

		s = new StartMenu(create, loadGame, enterYourName, fighterName, saiyan,
				earthling, namekian, frieza, majin);

		createAn = new JButton("Create Another");
		createAn.addMouseListener(this);

		createAnother = new JButton("Create !");
		createAnother.addMouseListener(this);

		switchFi = new JButton("Switch");
		switchFi.addMouseListener(this);

		switchSup = new JButton();
		switchSup.addMouseListener(this);

		switchUlt = new JButton();
		switchUlt.addMouseListener(this);

		upgrade = new JButton("Upgrade");
		upgrade.addMouseListener(this);

		save = new JButton("Save");
		save.addMouseListener(this);

		up = new JButton("Up");
		up.addMouseListener(this);

		down = new JButton("Down");
		down.addMouseListener(this);

		right = new JButton("Right");
		right.addMouseListener(this);

		left = new JButton("Left");
		left.addMouseListener(this);

		fighters = new JComboBox<String>();

		phys = new JButton();
		phys.addMouseListener(this);

		sup1 = new JButton();
		sup1.addMouseListener(this);

		sup2 = new JButton();
		sup2.addMouseListener(this);

		sup3 = new JButton();
		sup3.addMouseListener(this);

		sup4 = new JButton();
		sup4.addMouseListener(this);

		ult1 = new JButton();
		ult1.addMouseListener(this);

		ult2 = new JButton();
		ult2.addMouseListener(this);

		block = new JButton();
		block.addMouseListener(this);

		recover = new JButton();
		recover.addMouseListener(this);

		hp = new JCheckBox();
		Pd = new JCheckBox();
		Bd = new JCheckBox();
		sta = new JCheckBox();
		ki = new JCheckBox();

		upgradeInt = new JButton("Upgrade");
		upgradeInt.addMouseListener(this);

		exit = new JButton("Exit");
		exit.addMouseListener(this);

		grant = new JButton("Grant");
		grant.addMouseListener(this);

		g = new Game();
		g.setListener(this);

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == create) {
			beginGame();
		}
		if (e.getSource() == up) {
			try {
				moveUp();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (e.getSource() == down) {
			try {
				moveDown();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (e.getSource() == left) {
			try {
				moveLeft();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (e.getSource() == right) {
			try {
				moveRight();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (e.getSource() == createAn) {
			cr = new CreateAnother(saiyan, earthling, namekian, frieza, majin,
					createAnother, fighterName);
		}
		if (e.getSource() == createAnother) {
			if (!fighterName.getText().equals("")) {
				createAndAddFighter();
				cr.dispose();
				w.updateText();
			} else {

				JOptionPane.showMessageDialog(null,
						"Your Fighter Must Have A Name !", "No Name",
						JOptionPane.WARNING_MESSAGE);
			}

		}
		if (e.getSource() == loadGame) {
			s.dispose();
			load();
		}
		if (e.getSource() == save) {
			try {
				save();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		if (e.getSource() == switchFi) {
			for (int i = 0; i < g.getPlayer().getFighters().size(); i++) {
				if (i == (w.getFighters().getSelectedIndex()))

				{
					g.getPlayer().setActiveFighter(
							g.getPlayer().getFighters().get(i));
				}
			}
			w.updateText();
			updatePosition();
		}

		if (e.getSource() == switchSup && ((JButton) e.getSource()).isEnabled()) {
			switchSuperAttacks();
		}
		if (e.getSource() == switchUlt && ((JButton) e.getSource()).isEnabled()) {
			switchUltimateAttacks();
		}
		if (e.getSource() == phys && ((JButton) e.getSource()).isEnabled()) {
			try {
				useAttack(new PhysicalAttack());
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (e.getSource() == sup1 && ((JButton) e.getSource()).isEnabled()) {
			try {
				useAttack(g.getPlayer().getActiveFighter().getSuperAttacks().get(0));
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (e.getSource() == sup2 && ((JButton) e.getSource()).isEnabled()) {
			try {
				useAttack(g.getPlayer().getActiveFighter().getSuperAttacks().get(1));
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (e.getSource() == sup3 && ((JButton) e.getSource()).isEnabled()) {
			try {
				useAttack(g.getPlayer().getActiveFighter().getSuperAttacks().get(2));
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (e.getSource() == sup4 && ((JButton) e.getSource()).isEnabled()) {
			try {
				useAttack(g.getPlayer().getActiveFighter().getSuperAttacks().get(3));
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (e.getSource() == ult1 && ((JButton) e.getSource()).isEnabled()) {
			try {
				useAttack(g.getPlayer().getActiveFighter().getUltimateAttacks()
						.get(0));
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (e.getSource() == ult2 && ((JButton) e.getSource()).isEnabled()) {
			try {
				useAttack(g.getPlayer().getActiveFighter().getUltimateAttacks()
						.get(1));
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (e.getSource() == block && ((JButton) e.getSource()).isEnabled()) {
			try {
				block();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (e.getSource() == recover && ((JButton) e.getSource()).isEnabled()) {
			try {
				use();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (e.getSource() == upgradeInt) {
			if (hp.isSelected()) {
				try {
					g.getPlayer().upgradeFighter(
							g.getPlayer().getActiveFighter(), 'H');
				} catch (NotEnoughAbilityPointsException e1) {
					JOptionPane.showMessageDialog(null,
							"You Don't Have Enough Ability Points!",
							"Not Enough Ability Points",
							JOptionPane.WARNING_MESSAGE);
				}
			} else if (Bd.isSelected()) {
				try {
					g.getPlayer().upgradeFighter(
							g.getPlayer().getActiveFighter(), 'B');
				} catch (NotEnoughAbilityPointsException e1) {
					JOptionPane.showMessageDialog(null,
							"You Don't Have Enough Ability Points!",
							"Not Enough Ability Points",
							JOptionPane.WARNING_MESSAGE);
				}

			} else if (Pd.isSelected()) {
				try {
					g.getPlayer().upgradeFighter(
							g.getPlayer().getActiveFighter(), 'P');
				} catch (NotEnoughAbilityPointsException e1) {
					JOptionPane.showMessageDialog(null,
							"You Don't Have Enough Ability Points!",
							"Not Enough Ability Points",
							JOptionPane.WARNING_MESSAGE);
				}

			} else if (sta.isSelected()) {
				try {
					g.getPlayer().upgradeFighter(
							g.getPlayer().getActiveFighter(), 'S');
				} catch (NotEnoughAbilityPointsException e1) {
					JOptionPane.showMessageDialog(null,
							"You Don't Have Enough Ability Points!",
							"Not Enough Ability Points",
							JOptionPane.WARNING_MESSAGE);
				}

			} else if (ki.isSelected()) {
				try {
					g.getPlayer().upgradeFighter(
							g.getPlayer().getActiveFighter(), 'K');
				} catch (NotEnoughAbilityPointsException e1) {
					JOptionPane.showMessageDialog(null,
							"You Don't Have Enough Ability Points!",
							"Not Enough Ability Points",
							JOptionPane.WARNING_MESSAGE);
				}

			} else {
				JOptionPane.showMessageDialog(null,
						"You Have To Choose A Stat To Upgrade !",
						"Choose Stat", JOptionPane.WARNING_MESSAGE);
			}
			u.updateText();
		}
		if (e.getSource() == upgrade) {
			u = new Upgrade(hp, Pd, Bd, sta, ki, upgradeInt, exit, g
					.getPlayer().getActiveFighter());
		}
		if (e.getSource() == exit) {
			u.dispose();
		}
		if (e.getSource() == grant) {
			g.getPlayer().chooseWish(
					d.getWish()[d.getWishes().getSelectedIndex()]);
			d.dispose();
			w = new WorldMode(createAn, switchFi, switchSup, switchUlt,
					upgrade, save, up, down, left, right, g.getPlayer());
			w.updateText();
			updatePosition();
		}

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onDragonCalled(Dragon dragon) {
		w.dispose();
		d = new DragonMode(grant, dragon.getWishes());

	}

	@Override
	public void onCollectibleFound(Collectible collectible) {
		if (collectible == Collectible.DRAGON_BALL) {
			JOptionPane.showMessageDialog(null, "You Found A Dragon Ball !!!",
					"Collectible Found", JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "You Found A Senzu Bean !!!",
					"Collectible Found", JOptionPane.INFORMATION_MESSAGE);
		}
		w.updateText();
	}

	@Override
	public void onBattleEvent(BattleEvent e) throws InterruptedException {
		if (e.getType() == BattleEventType.STARTED) {
			w.dispose();
			Battle battle = (Battle) e.getSource();
			b = new BattleMode(battle, phys, sup1, sup2, sup3, sup4, ult1,
					ult2, block, recover);
			attacksLearned = "";
			NonPlayableFighter f = (NonPlayableFighter) b.getB().getFoe();
			for (int i = 0; i < f.getSuperAttacks().size(); i++) {
				if (!g.getPlayer().getSuperAttacks()
						.contains(f.getSuperAttacks().get(i))) {
					attacksLearned = attacksLearned + " "
							+ f.getSuperAttacks().get(i).getName();
				}
			}
		}
		if (e.getType() == BattleEventType.ATTACK) {
			Fighter f = (Fighter) e.getCurrentOpponent();
			JOptionPane.showMessageDialog(null, f.getName() + " used "
					+ e.getAttack().getName(), "Attack Used",
					JOptionPane.INFORMATION_MESSAGE);
		//	b.animationAttack(e.getAttack());
		}
		if (e.getType() == BattleEventType.BLOCK) {
			Fighter f = (Fighter) e.getCurrentOpponent();
			// option pane saying that f blocked
			JOptionPane.showMessageDialog(null,
					"The Fighter is Blocking Now ! ", "Blocking",
					JOptionPane.INFORMATION_MESSAGE);
		}

		if (e.getType() == BattleEventType.USE) {
			Fighter f = (Fighter) e.getCurrentOpponent();
			JOptionPane.showMessageDialog(null,
					"You Have Used 1 Senzu Bean ! ", "Recovered",
					JOptionPane.INFORMATION_MESSAGE);
		}

		if (e.getType() == BattleEventType.NEW_TURN) {
			b.updateText();
			if (e.getCurrentOpponent() == b.getB().getFoe()) {
				try {
					b.getB().play();
				} catch (NotEnoughKiException e1) {
					try {
						b.getB().attack(new PhysicalAttack());
					} catch (NotEnoughKiException e2) {
						// This should be inaccessible
						e2.printStackTrace();
					}
				}
			}
		}
		if (e.getType() == BattleEventType.ENDED) {
			NonPlayableFighter f = (NonPlayableFighter) b.getB().getFoe();
			if (e.getWinner() == b.getB().getMe()) {

				// option pane includes current level, exp to next level,
				// current ability points and new attacks learned in the string
				// above
				// make a special option pane in case the opponent was strong
				// that he will be put in a new map
				// to find the opponent's strength: f.isStrong();

				if (f.isStrong()) {
					JOptionPane.showMessageDialog(null,
							"You Have Beat The Boss ! Now To The Next Stage !",
							"CONGRATULATIONS !",
							JOptionPane.INFORMATION_MESSAGE);
				}

				JOptionPane.showMessageDialog(null,
						"You Have Won The Battle ! "
								+ "\n"
								+ "Level : "
								+ g.getPlayer().getActiveFighter().getLevel()
								+ "\n"
								+ "XP Remains: "
								+ (g.getPlayer().getActiveFighter()
										.getTargetXp() - g.getPlayer()
										.getActiveFighter().getXp())
								+ "\n"
								+ "Ability Points: "
								+ g.getPlayer().getActiveFighter()
										.getAbilityPoints() + "\n"
								+ "You Have Learned: " + attacksLearned,

						"CONGRATULATIONS !", JOptionPane.INFORMATION_MESSAGE);

				b.dispose();
				w = new WorldMode(createAn, switchFi, switchSup, switchUlt,
						upgrade, save, up, down, left, right, g.getPlayer());
				w.updateText();
				updatePosition();
			} else {

				JOptionPane.showMessageDialog(null, "Your Fighter Died ",
						"You Died !", JOptionPane.INFORMATION_MESSAGE);
				// Option Pane telling the player that he sucks for losing
				// and
				// if he didn't save he sucks even more

				try {
					g.load("Save");
					b.dispose();
					w = new WorldMode(createAn, switchFi, switchSup, switchUlt,
							upgrade, save, up, down, left, right, g.getPlayer());
					w.updateText();
					updatePosition();

				} catch (ClassNotFoundException | IOException e1) {
					JOptionPane
							.showMessageDialog(
									null,
									"You Either Didn't Even Save the Game Or The File Has Been Corrupted ! ",
									"Load Error", JOptionPane.ERROR_MESSAGE);
					// option pane telling the player that there was no save
					// file or that there was a problem reading it
					b.dispose();
					s = new StartMenu(create, loadGame, enterYourName,
							fighterName, saiyan, earthling, namekian, frieza,
							majin);
				}
			}
		}
	}

	public boolean createAndAddFighter() {
		if (saiyan.isSelected()) {
			g.getPlayer().createFighter('S', "" + fighterName.getText());
		} else if (earthling.isSelected()) {
			g.getPlayer().createFighter('E', "" + fighterName.getText());
		} else if (namekian.isSelected()) {
			g.getPlayer().createFighter('N', "" + fighterName.getText());
		} else if (frieza.isSelected()) {
			g.getPlayer().createFighter('F', "" + fighterName.getText());
		} else if (majin.isSelected()) {
			System.out.println(fighterName.getText());
			g.getPlayer().createFighter('M', "" + fighterName.getText());
		} else {
			JOptionPane.showMessageDialog(null,
					"You have to choose the Race of the Fighter ",
					"Restriction !", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		return true;
	}

	public void beginGame() {

		if (enterYourName.getText().equals("")
				|| fighterName.getText().equals("")) {

			JOptionPane.showMessageDialog(null,
					"You have to write your Name and the Fighter Name",
					"Restriction !", JOptionPane.WARNING_MESSAGE);

		} else if (createAndAddFighter()) {
			g.getWorld().generateMap(g.getWeakFoes(), g.getStrongFoes());
			g.getPlayer().setName(enterYourName.getText());
			s.dispose();
			w = new WorldMode(createAn, switchFi, switchSup, switchUlt,
					upgrade, save, up, down, left, right, g.getPlayer());
		}
	}

	public void updatePosition() {
		for (int j = 0; j < 10; j++) {
			for (int i = 0; i < 10; i++) {
				if (j == g.getWorld().getPlayerColumn()
						&& i == g.getWorld().getPlayerRow()) {
					if (g.getPlayer().getActiveFighter() instanceof Saiyan)
						w.getEl5ara()[i][j]
								.setIcon(resizeWorldFi("Saiyan map.png"));
					else if (g.getPlayer().getActiveFighter() instanceof Namekian)
						w.getEl5ara()[i][j]
								.setIcon(resizeWorldFi("Namekian map.png"));
					else if (g.getPlayer().getActiveFighter() instanceof Frieza)
						w.getEl5ara()[i][j]
								.setIcon(resizeWorldFi("Frieza map.png"));
					else if (g.getPlayer().getActiveFighter() instanceof Earthling)
						w.getEl5ara()[i][j]
								.setIcon(resizeWorldFi("Earthling map.png"));
					else {
						w.getEl5ara()[i][j]
								.setIcon(resizeWorldFi("Majin map.png"));
					}
				} else {
					w.getEl5ara()[i][j].setIcon(null);
				}

			}
		}
	}

	public void moveUp() throws InterruptedException {
		g.getWorld().moveUp();
		updatePosition();

	}

	public void moveDown() throws InterruptedException {
		g.getWorld().moveDown();
		updatePosition();
	}

	public void moveLeft() throws InterruptedException {
		g.getWorld().moveLeft();
		updatePosition();
	}

	public void moveRight() throws InterruptedException {
		g.getWorld().moveRight();
		updatePosition();
	}

	public ImageIcon resizeicon(String path) {
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

	public void load() {
		try {
			g.load("save");
			g.setListener(this);
			this.w = new WorldMode(createAn, switchFi, switchSup, switchUlt,
					upgrade, save, up, down, left, right, g.getPlayer());
			updatePosition();
			System.out.println(g.getWorld());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"The File You've Been Trying To Load is Corrupted ",
					"Load Failure", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void save() throws IOException {
		g.save("Save");
	}

	public void switchSuperAttacks() {
		SuperAttack n = new SuperAttack("fsd", 9);
		SuperAttack o = new SuperAttack("fsd", 9);
		for (int i = 0; i < g.getPlayer().getSuperAttacks().size(); i++) {
			if (w.getSuperAttack().getSelectedIndex() == i) {
				n = g.getPlayer().getSuperAttacks().get(i);
			}
			if (w.getSuperAttack2().getSelectedIndex() == i + 1) {
				o = g.getPlayer().getActiveFighter().getSuperAttacks().get(i);
			}

		}
		if (w.getSuperAttack2().getSelectedItem().equals("null")) {
			o = null;
		}
		try {
			g.getPlayer().assignAttack(g.getPlayer().getActiveFighter(), n, o);
		} catch (NotASaiyanException e1) {

			JOptionPane.showMessageDialog(null,
					"You Don't Look Like A Saiyan To Me ! ", "Not A Saiyan",
					JOptionPane.WARNING_MESSAGE);
		} catch (MaximumAttacksLearnedException e2) {

			JOptionPane.showMessageDialog(null,
					"You Exeeded The Number of Attacks Learned ! ",
					"Attack Stack Full", JOptionPane.WARNING_MESSAGE);
		} catch (DuplicateAttackException e3) {
			JOptionPane.showMessageDialog(null, "You Have The Same Attack ! ",
					"Attack Exists", JOptionPane.WARNING_MESSAGE);
		}
		w.updateText();
	}

	public void switchUltimateAttacks() {
		UltimateAttack n = new UltimateAttack("fsd", 9);
		UltimateAttack o = new UltimateAttack("fsd", 9);
		for (int i = 0; i < g.getPlayer().getUltimateAttacks().size(); i++) {
			if (w.getUltimateAttack().getSelectedIndex() == i) {
				n = g.getPlayer().getUltimateAttacks().get(i);
			}
			if (w.getUltimateAttack2().getSelectedIndex() == i + 1) {
				o = g.getPlayer().getActiveFighter().getUltimateAttacks()
						.get(i);
			}

		}
		if (w.getUltimateAttack2().getSelectedItem().equals("null")) {
			o = null;
		}
		try {
			g.getPlayer().assignAttack(g.getPlayer().getActiveFighter(), n, o);
		} catch (NotASaiyanException e1) {

			JOptionPane.showMessageDialog(null,
					"You Don't Look Like A Saiyan To Me ! ", "Not A Saiyan",
					JOptionPane.WARNING_MESSAGE);
		} catch (MaximumAttacksLearnedException e2) {

			JOptionPane.showMessageDialog(null,
					"You Exeeded The Number of Attacks Learned ! ",
					"Attack Stack Full", JOptionPane.WARNING_MESSAGE);
		} catch (DuplicateAttackException e3) {
			JOptionPane.showMessageDialog(null, "You Have The Same Attack ! ",
					"Attack Exists", JOptionPane.WARNING_MESSAGE);
		}
		w.updateText();
	}

	public void useAttack(Attack attack) throws InterruptedException {
		try {
			b.getB().attack(attack);
		} catch (NotEnoughKiException e) {

			JOptionPane.showMessageDialog(null,
					"You Don't Have Enough Ki For This Attack ! ",
					"Not Enough Ki", JOptionPane.WARNING_MESSAGE);
		}
	}

	public void block() throws InterruptedException {
		b.getB().block();
	}

	public void use() throws InterruptedException {
		try {
			b.getB().use(g.getPlayer(), Collectible.SENZU_BEAN);
		} catch (NotEnoughSenzuBeansException e) {
			JOptionPane.showMessageDialog(null,
					"You Don't Have Enough Senzu Beans! ", "No Recovery",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	public static void main(String[] args) throws MissingFieldException,
			UnknownAttackTypeException {

		Controller c = new Controller();
	}

}
