package scene;

import java.awt.event.KeyEvent;

public class NPCEvent {
	ScenePanel scene;
	public boolean isOral;
	public boolean oralOver;
	private int oralCount;
	private int oralNPC;
	private String[] oral;

	public NPCEvent(ScenePanel scene) {
		this.scene = scene;
	}

	public void sayOral(int oralNPC) {
		isOral = true;
		this.oralNPC = oralNPC;
		String[] ss = new String[3];
		ss[0] = String.valueOf(1);
		ss[1] = scene.npcs.get(oralNPC).name;
		oral = scene.npcs.get(oralNPC).oral;
		ss[2] = oral[oralCount];
		scene.dialogue.showSentence(ss);
		oralCount++;
	}

	public void keyPress(int keyCode) {
		if (keyCode == KeyEvent.VK_SPACE) {

			if (scene.dialogue.isBufferedTextOver) {
				scene.dialogue.begin();
			} else if (scene.dialogue.isSentenceOver) {
				scene.dialogue.icon1Run.stop();
				if (oralCount >= oral.length) {
					oralOver = true;
					oralCount = 0;
					isOral = false;
				} else {
					sayOral(oralNPC);
					oralOver = false;
				}
			}

		}
	}

	public void checkNPCOral() {
		// 是否说话
		for (int i = 0; i < scene.npcs.size(); i++) {
			int type = scene.npcs.get(i).getType();
			if (type == 0) {
				int x1 = scene.npcs.get(i).getX();
				int y1 = scene.npcs.get(i).getY();
				int x2 = scene.role.getX();
				int y2 = scene.role.getY();
				if ((x1 - 1 == x2 && y1 == y2 - 1)
						|| (x1 + 1 == x2 && y1 == y2 - 1)
						|| (x1 == x2 && y1 == y2) || (x1 == x2 && y1 + 2 == y2)) {
					// 先判断是否有选择事件触发
					if (!scene.selectEvent.checkSelectEvent(i))
						sayOral(i);
				}
			} else if (type == 1) {
				if (!scene.npcs.get(i).getWalk().isRunning()) {
					if (!scene.selectEvent.checkSelectEvent(i))
						sayOral(i);
				}
			} else if (type == 2) {
				if (!scene.npcs.get(i).getAction().isRunning()) {
					if (!scene.selectEvent.checkSelectEvent(i))
						sayOral(i);
				}
			}

		}
	}

	// 对NPC判断
	public void checkNPCStop() {
		// 判断NPC是否停止运动
		for (int i = 0; i < scene.npcs.size(); i++) {
			if (scene.npcs.get(i).getType() == 1) {
				int x1 = scene.npcs.get(i).getX();
				int y1 = scene.npcs.get(i).getY();
				int x2 = scene.role.getX();
				int y2 = scene.role.getY();
				switch (scene.npcs.get(i).getDirection()) {
				case 1:
					if (x1 == x2 && y1 == y2 - 1) {
						scene.npcs.get(i).getWalk().stop();
					} else if (!scene.npcs.get(i).getWalk().isRunning()) {
						scene.npcs.get(i).getWalk().start();
					}
					break;
				case 5:
					if (x1 + 1 == x2 && y1 == y2 - 1) {
						scene.npcs.get(i).getWalk().stop();
					} else if (!scene.npcs.get(i).getWalk().isRunning()) {
						scene.npcs.get(i).getWalk().start();
					}
					break;
				case 9:
					if (y1 + 2 == y2 && x1 == x2) {
						scene.npcs.get(i).getWalk().stop();
					} else if (!scene.npcs.get(i).getWalk().isRunning()) {
						scene.npcs.get(i).getWalk().start();
					}
					break;
				case 13:
					if (y1 + 1 == y2 && x1 == x2) {
						scene.npcs.get(i).getWalk().stop();
					} else if (!scene.npcs.get(i).getWalk().isRunning()) {
						scene.npcs.get(i).getWalk().start();
					}
					break;
				}
			} else if (scene.npcs.get(i).getType() == 2) {
				int x1 = scene.npcs.get(i).getX();
				int y1 = scene.npcs.get(i).getY();
				int x2 = scene.role.getX();
				int y2 = scene.role.getY();
				if ((x1 - 1 == x2 && y1 == y2 - 1)
						|| (x1 + 1 == x2 && y1 == y2 - 1)
						|| (x1 == x2 && y1 == y2) || (x1 == x2 && y1 + 2 == y2)) {
					scene.npcs.get(i).getAction().stop();
				} else {
					scene.npcs.get(i).getAction().start();
				}
			}
		}
	}
}
