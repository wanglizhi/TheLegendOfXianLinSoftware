package scene;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import shop.Money;
import tools.Reader;

public class OtherEvent {
	// 地图，人物等偏移量
	int firstTileX;
	int lastTileX;
	int firstTileY;
	int lastTileY;
	int offsetX;
	int offsetY;
	Role role;
	Map map;
	ScenePanel scene;
	// 实现地图遮掩问题
	ArrayList<Image> mapAddOfDay = new ArrayList<Image>();
	ArrayList<Image> mapAddOfNight = new ArrayList<Image>();

	// 得到宝物、地图上绘制金钱
	public OtherEvent(ScenePanel scene, Role role, Map map) {
		this.scene = scene;
		this.role = role;
		this.map = map;
	}

	public void addMap(Graphics g) {
		// 画钱
		g.drawImage(Reader.readImage("maps//money.png"), 925, 615, scene);
		g.setColor(Color.WHITE);
		g.setFont(new Font("正楷", Font.BOLD, 16));
		g.drawString(Money.getCoins() + "", 940, 630);
		if (scene.getMapSet().length == 80) {
			String type = "";
			if (scene.reader.getMapName().equals("大地图夜.jpg")) {
				type = "夜";
			}
			// 体育馆大门
			g.drawImage(Reader.readImage("maps//tiyuguan2" + type + ".png"),
					918 - firstTileX * 8, 906 - firstTileY * 8, scene);
			// 体育馆正门
			g.drawImage(Reader.readImage("maps//tiyuguan1" + type + ".png"),
					930 - firstTileX * 8, 780 - firstTileY * 8, scene);
			// 体育馆后门
			g.drawImage(Reader.readImage("maps//tiyuguan3" + type + ".png"),
					1420 - firstTileX * 8, 394 - firstTileY * 8, scene);
			// 大活大门
			g.drawImage(Reader.readImage("maps//dahuo" + type + ".png"),
					2028 - firstTileX * 8, 556 - firstTileY * 8, scene);
			// 图书馆正门
			g.drawImage(Reader.readImage("maps//tushuguan" + type + ".png"),
					2590 - firstTileX * 8, 430 - firstTileY * 8, scene);
			// 仙一
			g.drawImage(Reader.readImage("maps//jiaoxuelou1" + type + ".png"),
					2016 - firstTileX * 8, 1476 - firstTileY * 8, scene);
			// 仙二
			g.drawImage(Reader.readImage("maps//jiaoxuelou1" + type + ".png"),
					2016 - firstTileX * 8, 1124 - firstTileY * 8, scene);
			// 逸夫楼
			g.drawImage(Reader.readImage("maps//yifulou" + type + ".png"),
					2336 - firstTileX * 8, 2018 - firstTileY * 8, scene);
			// 实验楼左
			g.drawImage(Reader.readImage("maps//shiyanlou1" + type + ".png"),
					894 - firstTileX * 8, 1698 - firstTileY * 8, scene);
			// 实验楼右
			g.drawImage(Reader.readImage("maps//shiyanlou2" + type + ".png"),
					1404 - firstTileX * 8, 1700 - firstTileY * 8, scene);
		}
	}

	// 计算偏移量
	public void calOffset() {
		// X偏移位置
		offsetX = ScenePanel.WIDTH / 2 - role.getRealX();
		// 计算X偏移量
		offsetX = Math.min(offsetX, 0);
		offsetX = Math.max(offsetX, ScenePanel.WIDTH - map.getCol() * Map.CS);
		// Y偏移位置
		offsetY = ScenePanel.HEIGHT / 2 - role.getRealY();
		// 计算Y偏移量
		offsetY = Math.min(offsetY, 0);
		offsetY = Math.max(offsetY, ScenePanel.HEIGHT - map.getRow() * Map.CS);
		firstTileX = (int) Math.floor(-offsetX / 8);
		// 变更X坐标
		lastTileX = firstTileX + (int) Math.floor(ScenePanel.WIDTH / 8) + 1;
		// 返回最小X偏移值
		lastTileX = Math.min(lastTileX, map.getCol() * 4);
		firstTileY = (int) Math.floor(-offsetY / 8);
		// 变更Y坐标
		lastTileY = firstTileY + (int) Math.floor(ScenePanel.HEIGHT / 8) + 1;
		// 返回最小Y偏移值
		lastTileY = Math.min(lastTileY, map.getRow() * 4);
	}
}
