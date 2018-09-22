package dragonball.view;

import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import dragonball.model.character.fighter.PlayableFighter;

public class Upgrade extends JFrame {

	JCheckBox hp;
	JCheckBox Pd;
	JCheckBox Bd;
	JCheckBox sta;
	JCheckBox ki;

	JButton upgrade;
	JButton exit;
	
	JLabel enterFi;

	PlayableFighter pf;

	public Upgrade(JCheckBox hp, JCheckBox Pd, JCheckBox Bd, JCheckBox sta,
			JCheckBox ki, JButton upgrade, JButton exit, PlayableFighter pf) {
		this.setTitle("Create Another Fighter");
		this.setSize(500, 500);
		this.setResizable(false);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);

		JLabel theme = new JLabel();
		theme.setBounds(0, 0, 500, 500);
		theme.setIcon(resize("create.jpg"));
		this.add(theme);

		JLabel label1 = new JLabel("Upgrade Your Fighter !");
		label1.setBounds(100, 10, 450, 100);
		label1.setFont(label1.getFont().deriveFont(30.0f));
		label1.setForeground(Color.RED);
		theme.add(label1);

		enterFi = new JLabel("Available Ability Points: " + pf.getAbilityPoints());
		enterFi.setBounds(140, 125, 250, 25);
		enterFi.setFont(enterFi.getFont().deriveFont(14.0f));
		enterFi.setForeground(Color.RED);
		theme.add(enterFi);

		this.hp = hp;
		hp.setText("Hit Points: " + pf.getMaxHealthPoints());
		hp.setBounds(200, 200, 150, 25);
		hp.setForeground(Color.RED);
		hp.setOpaque(false);
		theme.add(hp);

		this.Pd = Pd;
		Pd.setText("Physical Damage: " + pf.getPhysicalDamage());
		Pd.setBounds(200, 225, 150, 25);
		Pd.setForeground(Color.RED);
		Pd.setOpaque(false);
		theme.add(Pd);

		this.Bd = Bd;
		Bd.setText("Blast Damage: " + pf.getBlastDamage());
		Bd.setBounds(200, 250, 150, 25);
		Bd.setForeground(Color.RED);
		Bd.setOpaque(false);
		theme.add(Bd);

		this.sta = sta;
		sta.setText("Stamina: " + pf.getMaxStamina());
		sta.setBounds(200, 275, 150, 25);
		sta.setForeground(Color.RED);
		sta.setOpaque(false);
		theme.add(sta);

		this.ki = ki;
		ki.setText("Ki: " + pf.getMaxKi());
		ki.setBounds(200, 300, 150, 25);
		ki.setForeground(Color.RED);
		ki.setOpaque(false);
		theme.add(ki);

		ButtonGroup group = new ButtonGroup();
		group.add(hp);
		group.add(Pd);
		group.add(Bd);
		group.add(sta);
		group.add(ki);

		upgrade.setBounds(20, 350, 150, 50);
		upgrade.setIcon(resizeicon("dragonball.png"));
		upgrade.setForeground(Color.RED);
		upgrade.setFont(upgrade.getFont().deriveFont(15.0f));
		upgrade.setOpaque(false);
		upgrade.setContentAreaFilled(false);
		upgrade.setBorderPainted(false);
		theme.add(upgrade);

		exit.setBounds(270, 350, 150, 50);
		exit.setIcon(resizeicon("dragonball.png"));
		exit.setForeground(Color.RED);
		exit.setFont(upgrade.getFont().deriveFont(15.0f));
		exit.setOpaque(false);
		exit.setContentAreaFilled(false);
		exit.setBorderPainted(false);
		theme.add(exit);
		this.pf = pf;

		this.revalidate();
		this.repaint();

	}

	public ImageIcon resize(String path) {
		Image img;
		try {
			img = ImageIO.read(new File(path));
			Image I = img.getScaledInstance(500, 500, Image.SCALE_SMOOTH);
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
			Image I = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
			return new ImageIcon(I);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void updateText(){
		enterFi.setText("Available Ability Points: " + pf.getAbilityPoints());
		System.out.println(hp);
		hp.setText("Hit Points: " + pf.getMaxHealthPoints());
		Pd.setText("Physical Damage: " + pf.getPhysicalDamage());
		Bd.setText("Blast Damage: " + pf.getBlastDamage());
		sta.setText("Stamina: " + pf.getMaxStamina());
		ki.setText("Ki: " + pf.getMaxKi());
		
	}

}
