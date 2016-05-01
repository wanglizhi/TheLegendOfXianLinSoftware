package scene;

import java.awt.*;

import tools.Reader;

public class Map {
	// ����
	public static int CS = 32;
	// ����������
	private int row;
	private int col;
	// ��ͼͼƬ
	private Image image = null;
	// ���
	ScenePanel scene = null;
	// ��ͼ���ࣨ�Ǿ�ֹ���Ǿ���
	private boolean isStatic;

	// ���캯�����ɽű�����
	public Map(String s, ScenePanel sc, int row, int col) {
		// ��ʼ����Ա����
		this.image = Reader.readImage("maps//" + s);
		this.scene = sc;
		this.row = row;
		this.col = col;
		if (row > 20 || col > 32) {
			this.isStatic = false;
		}
	}

	// ���㵱ǰimage���ص�����
	public int pixelsToTiles(double pixels) {
		return (int) Math.floor(pixels / 8);
	}

	// ���㵱ǰ���ֵ�image����
	public int tilesToPixels(int tiles) {
		return tiles * 8;
	}

	// ����ͼ�ĺ���
	public void drawMap(Graphics g, int firstTileX, int firstTileY,
			int lastTileX, int lastTileY) {
		if (!isStatic) {
			g.drawImage(image, 0, 0, 32 * 32, 20 * 32, firstTileX * 8,
					firstTileY * 8, lastTileX * 8 - 8, lastTileY * 8 - 8, scene);
		} else {
			g.drawImage(image, 0, 0, scene);
		}
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}
}
