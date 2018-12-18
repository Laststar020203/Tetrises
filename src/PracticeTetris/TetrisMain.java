package PracticeTetris;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.sun.glass.events.WheelEvent;
import com.sun.xml.internal.ws.api.pipe.Tube;

public class TetrisMain extends Canvas implements Runnable, KeyListener {

	public final static int WIDTH = 400, HEIGHT = 565;

	public static void main(String[] args) {

		JFrame frame = new JFrame("Tetris");
		frame.setSize(WIDTH, HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // �� �÷����� �����ص� �޸𸮻� ���� ���� �ʰ���
		frame.setLocationRelativeTo(null); // ������ â�� ��� ȭ�鿡 ���� ��
		frame.setResizable(false); // �������� ũ�⸦ ����ڰ� �����Ҽ������� ���� �����ϴ� �޼ҵ�
		frame.setLayout(null);

		KeyGetter.loadKeys();

		JMenuBar bar = new JMenuBar();
		bar.setBounds(0, 0, WIDTH - 10, 25);
		JMenu file = new JMenu("file");
		file.setBounds(0, 0, 45, 24);

		JMenuItem newGame = new JMenuItem("New Game");
		newGame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Code for new Game
				System.out.println("Starting new Game....");
			}
		});

		JMenuItem highScore = new JMenuItem("HighScore");
		highScore.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Code for new Game
				int highscore = 0;
				final JFrame alert = new JFrame("High Score"); // ����Ŭ�������� JFrame�� �����ϱ� ���� ��������� ����?!
				alert.setSize(200, 150);
				alert.setLayout(null);
				alert.setLocationRelativeTo(null);

				JLabel score = new JLabel("The HighScore is: " + highscore);
				score.setBounds(0, 0, 200, 50);

				JButton okayBtn = new JButton("Okay");
				okayBtn.setBounds(50, 80, 100, 30);
				okayBtn.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						alert.dispose();
					}
				});

				alert.add(score);
				alert.add(okayBtn);
				alert.setResizable(false);
				alert.setVisible(true);

				// ���̳��Ǽ� : ���̽��ھ �������� �̷��� �������� ���� �����ϱ� ���ٴ� �̸� �����س��� ���� ���� ������Ʈ �ϰ� setVisible ����
				// �ٲ��ִ� �� �� ���� ������?
			}
		});

		JMenuItem option = new JMenuItem("Option");
		option.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Config.opneConfig(frame);
			}
		});

		JMenuItem exit = new JMenuItem("Exit");
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Code for new Game
				System.out.println("Closing....");
				System.exit(0);
			}
		});

		TetrisMain main = new TetrisMain();
		main.setBounds(0, 25, WIDTH, HEIGHT - 25);
		frame.add(main);
		bar.add(file);

		file.add(newGame);
		file.add(highScore);
		file.add(option);
		file.add(exit);

		frame.add(bar);
		frame.setVisible(true);
		main.start();

	}

	public void start() {
		Thread t = new Thread(this);
		t.setPriority(Thread.MAX_PRIORITY);
		t.start();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		boolean running = true;

		while (running) {
			update();
			BufferStrategy buf = getBufferStrategy();
			if (buf == null) {
				createBufferStrategy(3);
				/*
				 * �� ȭ���� �����ϴٺ���, ���� �ִ� ���� ȭ���� �����̴� ����(flickering)�Դϴ� �̷� ���� �ذ��ϱ� ���ؼ� ������� Ŭ����
				 * �Դϴ�. ���� ���۸��� �����Ϸ���, �ϴ�, �ӽ� ���ۿ� ��� �׸��� ����, �� �ӽù��۸� �ٽ� ����ȭ��ǥ�ÿ� ���ۿ� �׸��� �������
				 * �߾����ϴ�. �̷��� �ϸ�, �ϴ� paint()�� ���� ������ ������ �˴ϴ�....swing������ �� ������ ���� ���� �ذ��� �� �ֵ���,
				 * JFrame���� �������ݴϴ� http://quadflask.tistory.com/290
				 */
				continue;
			}
			Graphics2D g = (Graphics2D) buf.getDrawGraphics();
			render(g);
			buf.show();
		}

	}

	public void render(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Calibri", Font.PLAIN, 20));

		// Font���� http://blog.naver.com/PostView.nhn?blogId=bestheroz&logNo=103808914

		g.drawString("Tetris", 170, 50);

	}

	public void update() {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
