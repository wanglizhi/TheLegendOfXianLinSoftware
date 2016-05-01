package scene;

import java.awt.*;

import tools.Reader;

public class Map {
	// 像素
	public static int CS = 32;
	// 行数和列数
	private int row;
	private int col;
	// 地图图片
	private Image image = null;
	// 面板
	ScenePanel scene = null;
	// 地图种类（是静止还是卷动）
	private boolean isStatic;

	// 构造函数，由脚本传入
	public Map(String s, ScenePanel sc, int row, int col) {
		// 初始化成员变量
		this.image = Reader.readImage("maps//" + s);
		this.scene = sc;
		this.row = row;
		this.col = col;
		if (row > 20 || col > 32) {
			this.isStatic = false;
		}
	}

	// 换算当前image像素到布局
	public int pixelsToTiles(double pixels) {
		return (int) Math.floor(pixels / 8);
	}

	// 换算当前布局到image像素
	public int tilesToPixels(int tiles) {
		return tiles * 8;
	}

	// 画地图的函数
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
