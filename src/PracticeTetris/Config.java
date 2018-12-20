package PracticeTetris;

import java.awt.Choice;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.sun.org.apache.bcel.internal.classfile.Field;

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
		
		try {
			saveConfig();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

	public static void saveConfig() throws Exception {
		File config = new File(getDefaultDirectory(), "Tetris/config.text");
		if (!config.exists()) {
			// exists �޼ҵ�� ���� ���� ���θ� Ȯ���ϴ� �޼ҵ��Դϴ�.
			config.createNewFile();
		}
		PrintWriter pw = new PrintWriter(config);
		//PritnWriter�� ���� �������� ����� �Ҷ� ����ϴ� Ŭ����
		//PritnWriter�� print�� println�� �ִµ� �� ���� �������� Println�� ���๮�ڱ��� ���ٿ��� ����Ѵٴ� ��
		//http://iamscjper.tistory.com/1866
		pw.println("right:" + right);
		pw.println("left:" + left);
		pw.println("rotate:" + rotate);
		pw.println("down:" + down);
		pw.println("pause:" + pause);
		pw.close();
		//��µ��� �ʴ� �����Ͱ� ������ ���� ����� �ϰ� ��Ʈ���� �ݴ´�.
	}

	public static void loadConfig() throws Exception {
		File directory = new File(getDefaultDirectory() , "/Tetris");
		if(!directory.exists()) {
			directory.mkdirs();
			//�ش� ��ο� ������ �����.
		}
		File config = new File(directory, "/config.text");
		if (!config.exists()) {
			// exists �޼ҵ�� ���� ���� ���θ� Ȯ���ϴ� �޼ҵ��Դϴ�.
			config.createNewFile();
		}
		// File Ŭ���� ������ : ù��° ���ڶ�� �̸��� ���� ������ �ι�° ���ڶ�� �̸��� ���� ���Ͽ� ���� File ��ü�� �����Ѵ�.
		// File Ŭ���� ���� http://hyeonstorage.tistory.com/233
		if (!config.exists()) {
			// exists �޼ҵ�� ���� ���� ���θ� Ȯ���ϴ� �޼ҵ��Դϴ�.
			config.createNewFile();
			// �־��� �̸��� ������ ������ ���� �����Ѵ�.
			System.out.println("File not Found saving defaults");
			saveConfig();
			return;
		}
		Scanner s = new Scanner(config);
		// ���ڿ� File Ŭ���� ������ ���� ��� ������ ������ ����ϴ� Scanner Ŭ���� ��ü�� �����Ѵ�.

		HashMap<String, String> values = new HashMap<String, String>();

		while (s.hasNextLine()) {
			//�� ��ĳ�ʰ� �Է¿� ���� ���� �ִ°�� true�� ��ȯ
			String[] entry = s.nextLine().split(":");
			String key = entry[0];
			String value = entry[1];
			values.put(key, value);
		}
		if (values.size() != 5) {
			System.out.println("Config is unnusable , saving defaults");
			saveConfig();
			return;
		}
		if(!values.containsKey("right") || !values.containsKey("left") || !values.containsKey("rotate")
				||!values.containsKey("down") || !values.containsKey("pause")) {
			System.out.println("Invalue names in config saving default");
			saveConfig();
			return;
			
		}
		String left = values.get("left");
		String right = values.get("right");
		String rotate = values.get("rotate");
		String down = values.get("down");
		String pause = values.get("pause");
		
		if(!(getKeyNames().contains(right) && getKeyNames().contains(left) &&getKeyNames().contains(rotate) &&getKeyNames().contains(down) &&
				getKeyNames().contains(pause))) {
			//ArrayListŬ������ contians�޼ҵ�� Arraylist�߿� ã���� �ϴ� �����Ͱ� �ִ��� Ȯ���ϴ� �޼ҵ��Դϴ�.
			System.out.println("Invailed key in config, saving default");
			return;
		}
		
		Config.right = right;
		Config.left = left;
		Config.rotate = rotate;
		Config.down = down;
		Config.pause = pause;
	}

	public static String getDefaultDirectory() {
		String OS = System.getProperty("os.name");
		// �ڹٸ� ����Ҷ� �ü���� JVM�� �������� ������ �˾ƿö��� �ʿ��Ҷ� ����ϴ� �޼ҵ��Դϴ�.
		http: // unabated.tistory.com/entry/Java%EC%97%90%EC%84%9C-SystemgetProperty
		if (OS.contains("WIN")) {
			// String�� contains �޼ҵ�� Ư�����ڿ��� ���ԵǾ� �ִ��� Ȯ���ϴ� �޼ҵ��Դϴ�.
			return System.getenv("APPDATA");
			// �ý��ۿ� ����� ȯ�溯������ �ҷ���
		}
		if (OS.contains("MAC")) {
			return System.getProperty("user.home") + "Library/Application Support";
		}
		return System.getProperty("user.home");
	}
}
