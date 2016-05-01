package scene;

import java.util.ArrayList;
import main.GameLauncher;
import battle.Enemy;
import battle.LuXueQi;
import battle.YuJie;
import battle.ZhangXiaoFan;

public class FightEvent {

	public static boolean fight;
	ScenePanel scene;
	public ArrayList<String[]> battle0;
	public ArrayList<String[]> battle1;
	public int countOfBattle1;
	public int count;
	private int x;
	private int y;
	private int count_battle0;
	public boolean battle1Over;

	public FightEvent(ScenePanel scene, ArrayList<String[]> battle0,
			ArrayList<String[]> battle1) {
		this.scene = scene;
		this.battle0 = battle0;
		if (battle0 != null) {
			if (scene.getMapSet().length > 20) {
				count_battle0 = 50;
			} else {
				count_battle0 = 30;
			}
		}
		// 与对话剧情相对应的战斗需要保存-----------
		this.battle1 = battle1;
	}

	public void startBattle1() {
		if (!battle1Over)
			fight(battle1.get(countOfBattle1));
		if (countOfBattle1 >= battle1.size() - 1) {
			battle1Over = true;
		} else {
			countOfBattle1++;
		}
	}

	public void startBattle0() {
		int i = (int) (Math.random() * battle0.size());
		fight(battle0.get(i));
	}

	public void fight(String[] battleInfo) {
		String fileName = battleInfo[0];
		String zhang = battleInfo[1];
		String yu = battleInfo[2];
		String lu = battleInfo[3];
		String enemy1 = battleInfo[4];
		String enemy2 = battleInfo[5];
		String enemy3 = battleInfo[6];
		ZhangXiaoFan zxf;
		YuJie yj;
		LuXueQi lxq;
		Enemy en1;
		Enemy en2;
		Enemy en3;
		if (zhang.equals("zhang")) {
			zxf = GameLauncher.zhangXiaoFan;
		} else {
			zxf = null;
		}
		if (yu.equals("yu")) {
			yj = GameLauncher.yuJie;
		} else {
			yj = null;
		}
		if (lu.equals("lu")) {
			lxq = GameLauncher.luXueQi;
		} else {
			lxq = null;
		}
		if (!enemy1.equals("null")) {
			en1 = new Enemy(enemy1.split("/")[0], Integer.parseInt(enemy1
					.split("/")[1]), GameLauncher.battlePanel);
		} else {
			en1 = null;
		}
		if (!enemy2.equals("null")) {
			en2 = new Enemy(enemy2.split("/")[0], Integer.parseInt(enemy2
					.split("/")[1]), GameLauncher.battlePanel);
		} else {
			en2 = null;
		}
		if (!enemy3.equals("null")) {
			en3 = new Enemy(enemy3.split("/")[0], Integer.parseInt(enemy3
					.split("/")[1]), GameLauncher.battlePanel);
		} else {
			en3 = null;
		}
		if (enemy1.equals("物理阁堂主/5") || enemy1.equals("罹年居士/5")
				|| enemy1.equals("缘铭道者/5") || enemy1.equals("大刀/5")
				|| enemy1.equals("蒙面怪人/5") || enemy1.equals("商塔堂主/5")
				|| enemy1.equals("最终李洵/5") || enemy1.equals("李洵/5"))
			scene.exitEvent.nextScript();
		GameLauncher.battlePanel.initial(fileName, zxf, yj, lxq, en1, en2, en3);
		scene.role.setEvent(true);
		GameLauncher.switchTo("battle");
	}

	public void checkBattle0() {
		if (scene.role.getX() != x || scene.role.getY() != y) {
			count++;
			x = scene.role.getX();
			y = scene.role.getY();
		}
		if (count == count_battle0) {
			// 开始战斗
			startBattle0();
			count = 0;
		}
	}
}
