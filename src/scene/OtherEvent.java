package scene;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import shop.Money;
import tools.Reader;

public class OtherEvent {
	// ��ͼ�������ƫ����
	int firstTileX;
	int lastTileX;
	int firstTileY;
	int lastTileY;
	int offsetX;
	int offsetY;
	Role role;
	Map map;
	ScenePanel scene;
	// ʵ�ֵ�ͼ��������
	ArrayList<Image> mapAddOfDay = new ArrayList<Image>();
	ArrayList<Image> mapAddOfNight = new ArrayList<Image>();

	// �õ������ͼ�ϻ��ƽ�Ǯ
	public OtherEvent(ScenePanel scene, Role role, Map map) {
		this.scene = scene;
		this.role = role;
		this.map = map;
	}

	public void addMap(Graphics g) {
		// ��Ǯ
		g.drawImage(Reader.readImage("maps//money.png"), 925, 615, scene);
		g.setColor(Color.WHITE);
		g.setFont(new Font("����", Font.BOLD, 16));
		g.drawString(Money.getCoins() + "", 940, 630);
		if (scene.getMapSet().length == 80) {
			String type = "";
			if (scene.reader.getMapName().equals("���ͼҹ.jpg")) {
				type = "ҹ";
			}
			// �����ݴ���
			g.drawImage(Reader.readImage("maps//tiyuguan2" + type + ".png"),
					918 - firstTileX * 8, 906 - firstTileY * 8, scene);
			// ����������
			g.drawImage(Reader.readImage("maps//tiyuguan1" + type + ".png"),
					930 - firstTileX * 8, 780 - firstTileY * 8, scene);
			// �����ݺ���
			g.drawImage(Reader.readImage("maps//tiyuguan3" + type + ".png"),
					1420 - firstTileX * 8, 394 - firstTileY * 8, scene);
			// ������
			g.drawImage(Reader.readImage("maps//dahuo" + type + ".png"),
					2028 - firstTileX * 8, 556 - firstTileY * 8, scene);
			// ͼ�������
			g.drawImage(Reader.readImage("maps//tushuguan" + type + ".png"),
					2590 - firstTileX * 8, 430 - firstTileY * 8, scene);
			// ��һ
			g.drawImage(Reader.readImage("maps//jiaoxuelou1" + type + ".png"),
					2016 - firstTileX * 8, 1476 - firstTileY * 8, scene);
			// �ɶ�
			g.drawImage(Reader.readImage("maps//jiaoxuelou1" + type + ".png"),
					2016 - firstTileX * 8, 1124 - firstTileY * 8, scene);
			// �ݷ�¥
			g.drawImage(Reader.readImage("maps//yifulou" + type + ".png"),
					2336 - firstTileX * 8, 2018 - firstTileY * 8, scene);
			// ʵ��¥��
			g.drawImage(Reader.readImage("maps//shiyanlou1" + type + ".png"),
					894 - firstTileX * 8, 1698 - firstTileY * 8, scene);
			// ʵ��¥��
			g.drawImage(Reader.readImage("maps//shiyanlou2" + type + ".png"),
					1404 - firstTileX * 8, 1700 - firstTileY * 8, scene);
		}
	}

	// ����ƫ����
	public void calOffset() {
		// Xƫ��λ��
		offsetX = ScenePanel.WIDTH / 2 - role.getRealX();
		// ����Xƫ����
		offsetX = Math.min(offsetX, 0);
		offsetX = Math.max(offsetX, ScenePanel.WIDTH - map.getCol() * Map.CS);
		// Yƫ��λ��
		offsetY = ScenePanel.HEIGHT / 2 - role.getRealY();
		// ����Yƫ����
		offsetY = Math.min(offsetY, 0);
		offsetY = Math.max(offsetY, ScenePanel.HEIGHT - map.getRow() * Map.CS);
		firstTileX = (int) Math.floor(-offsetX / 8);
		// ���X����
		lastTileX = firstTileX + (int) Math.floor(ScenePanel.WIDTH / 8) + 1;
		// ������СXƫ��ֵ
		lastTileX = Math.min(lastTileX, map.getCol() * 4);
		firstTileY = (int) Math.floor(-offsetY / 8);
		// ���Y����
		lastTileY = firstTileY + (int) Math.floor(ScenePanel.HEIGHT / 8) + 1;
		// ������СYƫ��ֵ
		lastTileY = Math.min(lastTileY, map.getRow() * 4);
	}
}
