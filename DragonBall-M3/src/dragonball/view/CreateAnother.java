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
import javax.swing.JTextField;

public class CreateAnother extends JFrame {

	JButton create;

	JCheckBox saiyan;
	JCheckBox earthling;
	JCheckBox namekian;
	JCheckBox frieza;
	JCheckBox majin;

	public CreateAnother(JCheckBox saiyan, JCheckBox earthling,
			JCheckBox namekian, JCheckBox frieza, JCheckBox majin,
			JButton create,JTextField fiName) {

		this.setTitle("Create Another Fighter");
		this.setSize(500, 500);
		this.setResizable(false);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);

		JLabel theme = new JLabel();
		theme.setBounds(0, 0, 500, 500);
		theme.setIcon(resize("create.jpg"));
		this.add(theme);

		JLabel label1 = new JLabel("Create Another One");
		label1.setBounds(100, 10, 450, 100);
		label1.setFont(label1.getFont().deriveFont(30.0f));
		label1.setForeground(Color.RED);
		theme.add(label1);

		JLabel enterFi = new JLabel("Enter Your New Fighter's Name");
		enterFi.setBounds(150, 125, 250, 25);
		enterFi.setFont(enterFi.getFont().deriveFont(14.0f));
		enterFi.setForeground(Color.RED);
		theme.add(enterFi);

		fiName.setBounds(150, 150, 220, 25);
		theme.add(fiName);

		saiyan.setBounds(200, 200, 150, 25);
		saiyan.setForeground(Color.RED);
		saiyan.setOpaque(false);
		theme.add(saiyan);

		earthling.setBounds(200, 225, 150, 25);
		earthling.setForeground(Color.RED);
		earthling.setOpaque(false);
		theme.add(earthling);

		namekian.setBounds(200, 250, 150, 25);
		namekian.setForeground(Color.RED);
		namekian.setOpaque(false);
		theme.add(namekian);

		frieza.setBounds(200, 275, 150, 25);
		frieza.setForeground(Color.RED);
		frieza.setOpaque(false);
		theme.add(frieza);

		majin.setBounds(200, 300, 150, 25);
		majin.setForeground(Color.RED);
		majin.setOpaque(false);
		theme.add(majin);

		ButtonGroup group = new ButtonGroup();
		group.add(saiyan);
		group.add(earthling);
		group.add(namekian);
		group.add(frieza);
		group.add(majin);

		create.setBounds(120, 350, 250, 50);
		create.setIcon(resizeicon("dragonball.png"));
		create.setForeground(Color.RED);
		create.setFont(create.getFont().deriveFont(20.0f));
		create.setOpaque(false);
		create.setContentAreaFilled(false);
		create.setBorderPainted(false);
		theme.add(create);

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

}
