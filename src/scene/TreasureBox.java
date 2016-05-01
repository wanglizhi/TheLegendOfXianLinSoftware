package scene;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;

import shop.DrugPack;
import tools.Reader;

public class TreasureBox {
	private Image fullBox = Reader.readImage("dialogue//fullBox.png");
	private Image emptyBox = Reader.readImage("dialogue//emptyBox.png");
	private String treasureName;
	private int x;
	private int y;
	EquipmentEvent equipmentEvent;
	private boolean isEmpty;
	private boolean AroundHero;

	public TreasureBox(String location, String treasureName,
			EquipmentEvent equipmentEvent) {
		this.x = Integer.parseInt(location.split("/")[0]);
		this.y = Integer.parseInt(location.split("/")[1]);
		this.treasureName = treasureName;
		this.equipmentEvent = equipmentEvent;
	}

	public void paintBox(Graphics g) {
		if (isEmpty) {
			g.drawImage(emptyBox, x * 32
					- equipmentEvent.scene.otherEvent.firstTileX * 8, y * 32
					- equipmentEvent.scene.otherEvent.firstTileY * 8,
					equipmentEvent.scene);
		} else {
			g.drawImage(fullBox, x * 32
					- equipmentEvent.scene.otherEvent.firstTileX * 8, y * 32
					- equipmentEvent.scene.otherEvent.firstTileY * 8,
					equipmentEvent.scene);
		}
	}

	public void checkHero(int x_hero, int y_hero) {
		if (!isEmpty) {
			if ((x - 1 == x_hero && y == y_hero)
					|| (x + 1 == x_hero && y == y_hero)
					|| (x == x_hero && y - 1 == y_hero)
					|| (x == x_hero && y + 1 == y_hero)) {
				AroundHero = true;
			}
		}
	}

	public void keyPressed(int keyCode) {
		if (AroundHero && keyCode == KeyEvent.VK_SPACE) {
			if (!isEmpty) {
				isEmpty = true;
				int i = 1 + (int) (2 * Math.random());
				DrugPack.addDrug(treasureName, i);
				equipmentEvent.drawString("µÃµ½" + treasureName + " * " + i);
			}
		}
	}
}
