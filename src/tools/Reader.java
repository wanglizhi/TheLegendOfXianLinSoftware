package tools;

import java.awt.Image;
import java.io.*;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import scene.NPC;
import scene.SaveAndLoad;

public class Reader {
	// 文件流，缓冲流
	private BufferedReader br;
	private FileReader fileReader;
	// NPC链表
	private ArrayList<NPC> npcs;
	private ArrayList<String[]> npcList;
	// 地图名
	private String mapName;
	// 地图大小
	private int row;
	private int col;
	// 地图数组
	private int[][] mapSet;
	// 角色名,帧数、初始位置
	private int roleX = 12;
	private int roleY = 8;
	// 对话类型列表和对话列表
	private ArrayList<String> dialogueCode;
	private ArrayList<ArrayList<String[]>> dialogue;
	// 出口的坐标集合，以及下一个场景的文件名称
	private ArrayList<String[]> exits;
	private ArrayList<String> nextScene;
	private ArrayList<String[]> entrance;
	// 旁白集合
	private ArrayList<String> narratage;
	private String[] nextScript;
	// 战斗集合
	private ArrayList<String[]> battle0;
	private ArrayList<String[]> battle1;
	// 选择事件中的脚本数据(可以有多组但要一一对应)
	private ArrayList<String> selectShopPanel;
	private ArrayList<String> selectEquipmentShopPanel;
	private ArrayList<String[]> selectBattlePanel;
	private ArrayList<String[]> battle2;
	private ArrayList<String[]> selectQuestion;
	private ArrayList<ArrayList<String>> question;
	private ArrayList<String[]> answer;
	// 宝箱
	private ArrayList<String[]> treasureBox;
	// 场景音乐
	private String sceneMusic;
	public static String task = null;

	public Reader(String fileName) {
		try {
			fileReader = new FileReader("script//" + fileName);
			br = new BufferedReader(fileReader);
			String s;
			String[] ss;
			// 1.地图名称
			s = br.readLine();
			mapName = s;
			SaveAndLoad.mapName = s;
			// 2.地图规格
			s = br.readLine();
			ss = s.split("/");
			col = Integer.parseInt(ss[0]);
			row = Integer.parseInt(ss[1]);
			mapSet = new int[row][col];
			for (int i = 0; i < row; i++) {
				s = br.readLine();
				ss = s.split(" ");
				for (int j = 0; j < col; j++) {
					mapSet[i][j] = Integer.parseInt(ss[j]);
				}
			}
			// 读其他东西
			while ((s = br.readLine()) != null) {
				s = s.trim();
				switchReader(s, br);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				br.close();
				fileReader.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
	}

	// 不同的内容不同的读法
	public void switchReader(String s, BufferedReader br) {
		try {
			switch (s) {
			// 3.主人公的个数
			case "Role":
				String[] ss = br.readLine().split(" ");
				if (Integer.parseInt(ss[0]) == 1)
					SaveAndLoad.zhang = true;
				else
					SaveAndLoad.zhang = false;
				if (Integer.parseInt(ss[1]) == 1)
					SaveAndLoad.lu = true;
				else
					SaveAndLoad.lu = false;
				if (Integer.parseInt(ss[2]) == 1)
					SaveAndLoad.wen = true;
				else
					SaveAndLoad.wen = false;
				break;
			// 4.NPC群的 状态（0.静止 1.单向走动 2.原地运动.3.四向运动）
			// 、初始位置、图片帧数、文件夹名称、口头禅、运动的人的方向和范围
			case "NPC":
				npcs = new ArrayList<NPC>();
				npcList = new ArrayList<String[]>();
				ss = br.readLine().split("/");
				for (int i = 0; i < ss.length; i++) {
					String[] npcInfo = ss[i].split(" ");
					npcList.add(npcInfo);
				}
				for (int i = 0; i < npcList.size(); i++) {
					NPC npc;
					String[] sg = npcList.get(i);
					if (Integer.parseInt(sg[0]) == 2) {
						npc = new NPC(Integer.parseInt(sg[1]),
								Integer.parseInt(sg[2]),
								Integer.parseInt(sg[3]), sg[4], sg[5]);
						npcs.add(npc);
					} else if (Integer.parseInt(sg[0]) == 1) {
						npc = new NPC(Integer.parseInt(sg[1]),
								Integer.parseInt(sg[2]),
								Integer.parseInt(sg[3]),
								Integer.parseInt(sg[4]),
								Integer.parseInt(sg[5]), sg[6], sg[7]);
						npcs.add(npc);
					} else if (Integer.parseInt(sg[0]) == 0) {
						npc = new NPC(Integer.parseInt(sg[1]),
								Integer.parseInt(sg[2]), sg[3], sg[4]);
						npcs.add(npc);
					}
				}
				break;
			// 5.读所有的对话
			case "Dialogue":
				// 先读出对话的类型，自动或者对应某个NPC
				dialogueCode = new ArrayList<String>();
				dialogue = new ArrayList<ArrayList<String[]>>();
				ss = br.readLine().split("/");
				for (int i = 0; i < ss.length; i++) {
					dialogueCode.add(ss[i]);
					ArrayList<String[]> al = new ArrayList<String[]>();
					while (!(s = br.readLine()).equals("#")) {
						al.add(s.split("/"));
					}
					dialogue.add(al);
				}
				break;
			// 6.读旁白
			case "Narratage":
				narratage = new ArrayList<String>();
				while (!(s = br.readLine()).equals("#")) {
					String s2 = s;
					narratage.add(s2);
				}
				break;
			// 7.读出口
			case "Exit":
				exits = new ArrayList<String[]>();
				nextScene = new ArrayList<String>();
				entrance = new ArrayList<String[]>();
				int num = Integer.parseInt(br.readLine());
				for (int i = 0; i < num; i++) {
					s = br.readLine();
					exits.add(s.split("/"));
					s = br.readLine();
					entrance.add(s.split("/"));
					s = br.readLine();
					nextScene.add(s);
				}
				break;
			case "NextScript":
				nextScript = new String[3];
				nextScript[0] = br.readLine();
				nextScript[1] = br.readLine();
				nextScript[2] = br.readLine();
				break;
			case "Fight":
				s = br.readLine();
				if (s.equals("0")) {
					battle0 = new ArrayList<String[]>();
					while (!(s = br.readLine()).equals("#")) {
						battle0.add(s.split(" "));
					}
				} else if (s.equals("1")) {
					battle1 = new ArrayList<String[]>();
					while (!(s = br.readLine()).equals("#")) {
						battle1.add(s.split(" "));
					}
				}
				break;
			case "SelectShopPanel":
				selectShopPanel = new ArrayList<String>();
				for (int i = 0; i < 4; i++) {
					selectShopPanel.add(br.readLine());
				}
				break;
			case "SelectEquipmentShopPanel":
				selectEquipmentShopPanel = new ArrayList<String>();
				for (int i = 0; i < 4; i++) {
					selectEquipmentShopPanel.add(br.readLine());
				}
				break;
			case "SelectBattlePanel":
				selectBattlePanel = new ArrayList<String[]>();
				battle2 = new ArrayList<String[]>();
				while (!(s = br.readLine()).equals("#")) {
					String[] sss = new String[4];
					sss[0] = s;
					for (int i = 1; i < 4; i++) {
						sss[i] = br.readLine();
					}
					battle2.add(br.readLine().split(" "));
					selectBattlePanel.add(sss);
				}
				break;
			case "SelectQuestion":
				selectQuestion = new ArrayList<String[]>();
				question = new ArrayList<ArrayList<String>>();
				answer = new ArrayList<String[]>();
				while (!(s = br.readLine()).equals("#")) {
					String[] sss = new String[4];
					sss[0] = s;
					for (int i = 1; i < 4; i++) {
						sss[i] = br.readLine();
					}
					selectQuestion.add(sss);
					ArrayList<String> que = new ArrayList<String>();
					while (!(s = br.readLine()).equals("Answer")) {
						que.add(s);
					}
					question.add(que);
					String[] s2 = new String[4];
					for (int i = 0; i < 4; i++) {
						s2[i] = br.readLine();
					}
					answer.add(s2);
				}
				break;
			case "TreasureBox":
				treasureBox = new ArrayList<String[]>();
				while (!(s = br.readLine()).equals("#")) {
					ss = s.split(" ");
					treasureBox.add(ss);
				}
				break;
			case "Music":
				sceneMusic = br.readLine();
				break;
			case "Task":
				task = br.readLine();
				break;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	// 得到文件的类型
	public static String getType(File f) {
		String fileName = f.getName();
		String[] str = fileName.split("\\.");
		String filetype = str[str.length - 1];
		return filetype;
	}

	// 读取图片的静态函数
	public static Image readImage(String imageName) {
		ImageIcon icon = new ImageIcon(imageName);
		return icon.getImage();
	}

	// 一堆getters and setters
	public ArrayList<String[]> getNpcList() {
		return npcList;
	}

	public void setNpcList(ArrayList<String[]> npcList) {
		this.npcList = npcList;
	}

	public String getMapName() {
		return mapName;
	}

	public void setMapName(String mapName) {
		this.mapName = mapName;
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

	public int[][] getMapSet() {
		return mapSet;
	}

	public void setMapSet(int[][] mapSet) {
		this.mapSet = mapSet;
	}

	public int getRoleX() {
		return roleX;
	}

	public void setRoleX(int roleX) {
		this.roleX = roleX;
	}

	public int getRoleY() {
		return roleY;
	}

	public void setRoleY(int roleY) {
		this.roleY = roleY;
	}

	public ArrayList<ArrayList<String[]>> getDialogue() {
		return dialogue;
	}

	public void setDialogue(ArrayList<ArrayList<String[]>> dialogue) {
		this.dialogue = dialogue;
	}

	public ArrayList<String[]> getExits() {
		return exits;
	}

	public void setExits(ArrayList<String[]> exits) {
		this.exits = exits;
	}

	public ArrayList<String> getNextScene() {
		return nextScene;
	}

	public void setNextScene(ArrayList<String> nextScene) {
		this.nextScene = nextScene;
	}

	public ArrayList<String> getNarratage() {
		return narratage;
	}

	public void setNarratage(ArrayList<String> narratage) {
		this.narratage = narratage;
	}

	public ArrayList<String> getDialogueCode() {
		return dialogueCode;
	}

	public void setDialogueCode(ArrayList<String> dialogueCode) {
		this.dialogueCode = dialogueCode;
	}

	public ArrayList<NPC> getNpcs() {
		return npcs;
	}

	public void setNpcs(ArrayList<NPC> npcs) {
		this.npcs = npcs;
	}

	public ArrayList<String[]> getEntrance() {
		return entrance;
	}

	public void setEntrance(ArrayList<String[]> entrance) {
		this.entrance = entrance;
	}

	public String[] getNextScript() {
		return nextScript;
	}

	public void setNextScript(String[] nextScript) {
		this.nextScript = nextScript;
	}

	public ArrayList<String[]> getBattle0() {
		return battle0;
	}

	public void setBattle0(ArrayList<String[]> battle0) {
		this.battle0 = battle0;
	}

	public ArrayList<String[]> getBattle1() {
		return battle1;
	}

	public void setBattle1(ArrayList<String[]> battle1) {
		this.battle1 = battle1;
	}

	public ArrayList<String> getSelectShopPanel() {
		return selectShopPanel;
	}

	public void setSelectShopPanel(ArrayList<String> selectShopPanel) {
		this.selectShopPanel = selectShopPanel;
	}

	public ArrayList<String[]> getSelectBattlePanel() {
		return selectBattlePanel;
	}

	public void setSelectBattlePanel(ArrayList<String[]> selectBattlePanel) {
		this.selectBattlePanel = selectBattlePanel;
	}

	public ArrayList<String[]> getBattle2() {
		return battle2;
	}

	public void setBattle2(ArrayList<String[]> battle2) {
		this.battle2 = battle2;
	}

	public ArrayList<String[]> getSelectQuestion() {
		return selectQuestion;
	}

	public void setSelectQuestion(ArrayList<String[]> selectQuestion) {
		this.selectQuestion = selectQuestion;
	}

	public ArrayList<ArrayList<String>> getQuestion() {
		return question;
	}

	public void setQuestion(ArrayList<ArrayList<String>> question) {
		this.question = question;
	}

	public ArrayList<String[]> getAnswer() {
		return answer;
	}

	public void setAnswer(ArrayList<String[]> answer) {
		this.answer = answer;
	}

	public ArrayList<String[]> getTreasureBox() {
		return treasureBox;
	}

	public void setTreasureBox(ArrayList<String[]> treasureBox) {
		this.treasureBox = treasureBox;
	}

	public ArrayList<String> getSelectEquipmentShopPanel() {
		return selectEquipmentShopPanel;
	}

	public void setSelectEquipmentShopPanel(
			ArrayList<String> selectEquipmentShopPanel) {
		this.selectEquipmentShopPanel = selectEquipmentShopPanel;
	}

	public String getSceneMusic() {
		return sceneMusic;
	}

	public void setSceneMusic(String sceneMusic) {
		this.sceneMusic = sceneMusic;
	}
}
