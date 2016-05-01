package scene;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class RoleEvent {

	Map map;
	int[][] mapSet;
	ArrayList<NPC> npcs;
	Role role;
	// ��Ա����
	// �����ܲ�״̬�жϵı���
	boolean b = false;
	long l;
	long c;

	public RoleEvent(Role role, Map map, int[][] mapSet, ArrayList<NPC> npcs) {
		this.role = role;
		this.map = map;
		this.mapSet = mapSet;
		this.npcs = npcs;
	}

	// �ﵽ�ܲ�������
	public boolean checkRun() {
		role.setRun(true);
		return false;
	}

	// ���ǵ��߶�
	public void switchWalk(int keyCode) {
		switch (keyCode) {
		case KeyEvent.VK_LEFT:
			role.setEvent(Role.LEFT);
			break;
		case KeyEvent.VK_RIGHT:
			role.setEvent(Role.RIGHT);
			break;
		case KeyEvent.VK_UP:
			role.setEvent(Role.UP);
			break;
		case KeyEvent.VK_DOWN:
			role.setEvent(Role.DOWN);
			break;
		}
	}

	// �ж������Ƿ�����߶�
	public boolean isAllow(int x, int y) {
		// �����߽�����
		if (x < 0 || x >= map.getCol() || y < 0 || y >= map.getRow()) {
			return false;
		}
		if (mapSet[y][x] != 0) {
			return false;
		}
		for (int i = 0; i < npcs.size(); i++) {
			if (x == npcs.get(i).getX() && y == npcs.get(i).getY() + 1) {
				return false;
			}
		}
		return true;
	}
}
