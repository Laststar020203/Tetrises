package PracticeTetris;

import java.awt.Choice;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Config {

	public static String rotate = "Up", left = "Left", right = "Right", down = "Down", pause = "P";
	private static ArrayList<Choice> choices;

	public static void opneConfig(JFrame frame) {
		choices = new ArrayList<Choice>();
		final JFrame option = new JFrame("Option");
		option.setSize(400, 300);
		option.setResizable(false);
		option.setLocationRelativeTo(frame);
		option.setVisible(true);

		Choice left = addChoice("Left", option, 30, 30);
		left.select(Config.left); // ���ڷ� ���� ����� ���� ���� �߰��� ��)�⺻�� �����Ҷ�
		Choice right = addChoice("Right", option, 150, 30);
		right.select(Config.right);
		Choice rotate = addChoice("Rotate", option, 30, 80);
		rotate.select(Config.rotate);
		Choice down = addChoice("Down", option, 150, 80);
		down.select(Config.down);
		Choice pause = addChoice("Pause", option, 30, 130);
		down.select(Config.pause);

		JButton done = new JButton("Done");
		done.setBounds(150, 220, 100, 30);
		done.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				option.dispose();
				saveChoice();
			}
		});

		option.add(done);

		option.setLayout(null);

	}

	public static void saveChoice() {

		Choice left = choices.get(0);
		Choice right = choices.get(1);
		Choice rotate = choices.get(2);
		Choice down = choices.get(3);
		Choice pause = choices.get(4);

		Config.left = left.getSelectedItem();
		// ���� ���Ե� ����� Config.left�� ���������ν� opconfig�޼ҵ忡�� addChoice�Ҷ� �ε��ϰ� ��
		Config.right = right.getSelectedItem();
		Config.rotate = rotate.getSelectedItem();
		Config.down = down.getSelectedItem();
		Config.pause = pause.getSelectedItem();
	}

	public static Choice addChoice(String name, JFrame option, int x, int y) {
		JLabel label = new JLabel(name);
		label.setBounds(x, y - 20, 100, 20);
		Choice key = new Choice();
		for (String s : getKeyNames()) {
			key.add(s);
		}
		key.setBounds(x, y, 100, 20);
		option.add(key);
		option.add(label);
		choices.add(key);

		return key;
	}

	public static ArrayList<String> getKeyNames() {
		ArrayList<String> result = new ArrayList<String>();
		for (String s : KeyGetter.keyNames) {
			// �� (String s : KeyGetter.keys.keySet ���� �� �ڵ�� �ٲ�����? 4-15:03 KeySet()�� ���İ� ������
			// ������?
			result.add(s);
			if (s.equalsIgnoreCase("F24"))
				break;
		}
		return result;
	}
	
	public static void saveConfig() {
		
	}
	public static void loadConfig() {
		
	}
	public static String getDefaultDirectory() {
		String OS = System.getProperty("os.name");
	}
}
