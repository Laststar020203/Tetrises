package PracticeTetris;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Shape {

	// �ڹ��� ��ǥ�� ����Ƽ�� �޸� �Ʒ��� ������ Y���� �����ϰ� ���������� ������ X���� �����Ѵ�.

	private BufferedImage block;
	private int[][] coords;
	private Board board;
	private int deltaX = 0;
	private int x, y;
	private long time, lastTime;

	private int color;

	private boolean collision = false, moveX = false;
	

	private int normalSpeed = 600, speedDown = 60, cuurentSpeed;

	public Shape(BufferedImage block, int[][] coords, Board board, int color) {
		this.block = block;
		this.coords = coords;
		this.board = board;
		this.color = color;

		cuurentSpeed = normalSpeed;
		time = 0;
		lastTime = 0;

		x = 3;
		y = 0;

	}

	public BufferedImage getBlock() {
		return block;
	}

	public int[][] getCoords() {
		return coords;
	}

	public void update() {
		time += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();

		System.out.println(time + " : "+ lastTime);
		
		if (collision) {

			for (int row = 0; row < coords.length; row++) {
				for (int col = 0; col < coords[row].length; col++) {
					if (coords[row][col] != 0)
						board.getBoard()[y + row][x + col] = color;
				}
			}
			// �ٴڿ� ������뵵 ��� ����Ʈ�ϰ� ���� ����(�÷� ������)

			checkLine();
			board.setNextShape();
		}

		if (!(x + deltaX + coords[0].length > 10) && !(x + deltaX < 0)) {

			for (int row = 0; row < coords.length; row++) {
				for (int col = 0; col < coords[row].length; col++) {
					if (coords[row][col] != 0) {
						if (board.getBoard()[y + row][x + deltaX + col] != 0) {
							moveX = false;
						}
					}

				}
			} // �� ���� ������ ���ϵ��� �̸� ���Ƴ�
			if (moveX)
				x += deltaX;
		}
		if (!(y + 1 + coords.length > 20)) {

			for (int row = 0; row < coords.length; row++) {
				for (int col = 0; col < coords[row].length; col++) {
					if (coords[row][col] != 0) {
						if (board.getBoard()[y + row + 1][col + x] != 0) // ���� �ִ� �ڸ��� �ٸ� ���� �ִ� ���
							collision = true;
					}

				}
			}

			if (time > cuurentSpeed) {
				y++;
				time = 0;
			}
			// ���� ��� ����Ʈ��

		} else {
			collision = true;
		}

		deltaX = 0; // ��� ���� ����
		moveX = true;

	}

	public void render(Graphics g) {

		for (int row = 0; row < coords.length; row++) {
			for (int col = 0; col < coords[row].length; col++) {
				if (coords[row][col] != 0) // �� �ڸ����� ���� ����
					g.drawImage(block, col * board.getBlockSize() + x * board.getBlockSize(),
							row * board.getBlockSize() + y * board.getBlockSize(), null);
				// g.drawImage(IMG , X, Y , ImageObserver < ���⿡ ���� �ڼ��� ������ ���׿�);
			}
		}

	}

	public void rotate() {

		if (collision)
			return; // collision�� �Ͼ���� ȸ�� �ð��� ��ġ�� ���� �����ϱ����� (������)

		int[][] rotatedmatrix = null;

		rotatedmatrix = getTranspose(coords);

		rotatedmatrix = getReverseMatrix(rotatedmatrix);

		if (x + rotatedmatrix[0].length > 10 || y + rotatedmatrix.length > 20)
			return;

		for (int row = 0; row < rotatedmatrix.length; row++) {
			for (int col = 0; col < rotatedmatrix[0].length; col++) {

				if (board.getBoard()[y + row][x + col] != 0) {
					return;
				}
			}
		}

		coords = rotatedmatrix;
	}

	private int[][] getTranspose(int[][] matrix) {
		int[][] newMatrix = new int[matrix[0].length][matrix.length];

		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				newMatrix[j][i] = matrix[i][j];
			}
		}

		return newMatrix;
	}

	private int[][] getReverseMatrix(int[][] matrix) {
		int middle = matrix.length / 2;

		for (int i = 0; i < middle; i++) {
			int[] m = matrix[i];
			matrix[i] = matrix[matrix.length - i - 1];
			matrix[matrix.length - i - 1] = m;
		}

		return matrix;
	}

	public void setDeltaX(int deltaX) {
		this.deltaX = deltaX;
	}

	public void speedDown() {
		cuurentSpeed = speedDown;
	}

	public void normalSpeed() {
		cuurentSpeed = normalSpeed;
	}

	public int getColor() {
		return color;
	}

	private void checkLine() {
		int height = board.getBoard().length - 1;

		for (int i = height; i > 0; i--) { // �Ʒ�������

			int count = 0;
			for (int j = 0; j < board.getBoard()[0].length; j++) {

				if (board.getBoard()[i][j] != 0)
					count++;

				board.getBoard()[height][j] = board.getBoard()[i][j];
				// height ���� ���� ��ȭ�� ������ ���� �ٱ� �ݺ����� i ���� ���� ��ȭ�� ������ ��ȭ�� ���ٸ� ���� �޶�(���� height ���� ŭ)
				// �� �ٲ��� �Ͼ�� ��

			}
			if (count < board.getBoard()[0].length)
				height--;
		}

	}

}
