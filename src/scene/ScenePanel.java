package scene;

import tools.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;
import java.util.ArrayList;
import javax.swing.*;

import main.GameLauncher;
import media.MusicReader;

public class ScenePanel extends JPanel implements Runnable {
	private static final long serialVersionUID = 1L;
	// ���
	public static final int WIDTH = 1024;
	public static final int HEIGHT = 640;
	// ��ͼ�������Ƕ���NPC����Reader����
	public Dialogue dialogue;
	public Narratage narratage;
	public Role role;
	public ArrayList<NPC> npcs;
	private Map map;
	Reader reader;
	// ��ͼͼƬ�����屳��ͼƬ��
	private Image backImage;
	private Graphics backImageGraphics;
	// ��ͼ����
	private int mapSet[][];
	// �����¼�
	public ExitEvent exitEvent;
	public DialogueEvent dialogueEvent;
	public RoleEvent roleEvent;
	public NPCEvent npcEvent;
	public OtherEvent otherEvent;
	public FightEvent fightEvent;
	public SelectEvent selectEvent;
	public EquipmentEvent equipmentEvent;
	public SaveAndLoad sal;
	boolean isInitiateOver = false;
	private boolean isPaintOver = false;
	boolean b;
	public ArrayList<String> memory = new ArrayList<>();
	// ��ǰ�����Լ���һ������
	public String fileName;
	public String[] currentScript = new String[3];
	public String[] nextScript = new String[3];
	GameLauncher game;
	public boolean isScript = true;

	// ���캯��
	public ScenePanel(GameLauncher gameLauncher) {
		this.game = gameLauncher;
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		currentScript[0] = "7/7";
		currentScript[1] = "����.txt";
		currentScript[2] = "�ű�1.txt";
		sal = new SaveAndLoad(this);
		setMouse();
	}

	public void paint(Graphics g) {
		isPaintOver = false;
		otherEvent.calOffset();
		super.paint(backImageGraphics);
		// ע��˳��1.�Ƿ����԰�
		if (!narratage.isNarratage) {
			map.drawMap(backImageGraphics, otherEvent.firstTileX,
					otherEvent.firstTileY, otherEvent.lastTileX,
					otherEvent.lastTileY);
			equipmentEvent.drawTreasureBox(backImageGraphics);
			boolean b = false;
			for (int i = 0; i < npcs.size(); i++) {
				if (role.getY() > npcs.get(i).getY()
						&& role.getX() - 2 <= npcs.get(i).getX()
						&& role.getX() + 1 >= npcs.get(i).getX()) {
					b = true;
				}
			}
			if (b) {
				for (int i = 0; i < npcs.size(); i++) {
					npcs.get(i).drawNPC(backImageGraphics,
							otherEvent.firstTileX, otherEvent.firstTileY);
				}
				role.drawHero(backImageGraphics, otherEvent.offsetX,
						otherEvent.offsetY);
			} else {
				role.drawHero(backImageGraphics, otherEvent.offsetX,
						otherEvent.offsetY);
				for (int i = 0; i < npcs.size(); i++) {
					npcs.get(i).drawNPC(backImageGraphics,
							otherEvent.firstTileX, otherEvent.firstTileY);
				}
			}
			// ��ͼ����
			otherEvent.addMap(backImageGraphics);
			// 2.�Ƿ����ڶԻ�
			if (dialogueEvent.isSpeaking || npcEvent.isOral) {
				dialogue.drawDialogue(backImageGraphics);
			}
			// 3.����ѡ��Ի�
			if (selectEvent.isSelect)
				selectEvent.drawSelectImage(backImageGraphics);
			// 4.������ʾ�Ի�
			equipmentEvent.drawPresentation(backImageGraphics);

		} else {
			if (!narratage.narratageOver) {
				narratage.drawNarratage(backImageGraphics);
			}
		}
		// �����屳������������
		g.drawImage(backImage, 0, 0, this);
		// ����֡�ѻ���
		this.isPaintOver = true;
	}

	public void initiation(String fileName) {
		this.fileName = fileName;
		// ���屳��ͼƬ
		backImage = new BufferedImage(WIDTH, HEIGHT,
				BufferedImage.TYPE_INT_ARGB);
		backImageGraphics = backImage.getGraphics();
		// �½��Ķ�����������Ҫ���Ľű��ļ���ַ
		reader = new Reader(fileName);
		// �õ���ͼ����
		mapSet = reader.getMapSet();
		// �½���ͼ����
		map = new Map(reader.getMapName(), this, reader.getRow(),
				reader.getCol());
		// ����NPC�����б�
		npcs = new ArrayList<NPC>();
		if (reader.getNpcs() != null) {
			npcs = reader.getNpcs();
		}
		// �½����Ƕ���
		role = new Role(reader.getRoleX() * Map.CS, reader.getRoleY() * Map.CS,
				this);
		// ���������¼�����
		roleEvent = new RoleEvent(role, map, mapSet, npcs);
		npcEvent = new NPCEvent(this);
		// �����Ի�����
		dialogue = new Dialogue(this);
		// ���ó����ĶԻ���ţ����жԻ��������Ӧ����������
		if (reader.getDialogueCode() != null) {
			dialogueEvent = new DialogueEvent(this, reader.getDialogueCode(),
					reader.getDialogue());
		} else if (sal.isLoad) {
			dialogueEvent = new DialogueEvent(this, reader.getDialogueCode(),
					reader.getDialogue());
			sal.isLoad = false;
		}
		// �����԰׶���
		narratage = new Narratage(this, reader.getNarratage());
		// �ó����ĳ��ڼ���Ӧ��һ��������
		exitEvent = new ExitEvent(this, reader.getExits(),
				reader.getNextScene(), reader.getEntrance());
		otherEvent = new OtherEvent(this, role, map);
		if (reader.getNextScript() != null) {
			nextScript = reader.getNextScript();
		}
		fightEvent = new FightEvent(this, reader.getBattle0(),
				reader.getBattle1());
		selectEvent = new SelectEvent(this, fightEvent, fileName,
				reader.getSelectShopPanel(),
				reader.getSelectEquipmentShopPanel(),
				reader.getSelectBattlePanel(), reader.getSelectQuestion(),
				reader.getBattle2(), reader.getQuestion(), reader.getAnswer());
		equipmentEvent = new EquipmentEvent(this, reader.getTreasureBox());
		MusicReader.readBGM(reader.getSceneMusic());
		isInitiateOver = true;
	}

	// α���̼�������
	public void keyPressed(int keyCode, boolean isControl) {
		if (!narratage.isNarratage) {
			if (!dialogueEvent.isSpeaking) {
				if (keyCode == KeyEvent.VK_SPACE) {
					if (reader.getDialogueCode() != null) {
						b = dialogueEvent.checkDialogue();
					} else {
						b = false;
					}
				}
				if (!npcEvent.isOral) {
					if (keyCode == KeyEvent.VK_DOWN
							|| keyCode == KeyEvent.VK_UP
							|| keyCode == KeyEvent.VK_LEFT
							|| keyCode == KeyEvent.VK_RIGHT)
						if (!selectEvent.isSelect) {
							if (isControl)
								roleEvent.checkRun();
							roleEvent.switchWalk(keyCode);

						}
					if (keyCode == KeyEvent.VK_SPACE && !b) {
						npcEvent.checkNPCOral();
					}
					if (reader.getTreasureBox() != null)
						equipmentEvent.keyPressed(keyCode);
					if (selectEvent.isSelect)
						selectEvent.keyPressed(keyCode);
				} else {
					npcEvent.keyPress(keyCode);
				}
				if (keyCode == KeyEvent.VK_ESCAPE) {
					GameLauncher.switchTo("menu");
				}
			} else {
				dialogueEvent.keyPressed(keyCode);
			}
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			// ��ʼ����Ϻ�������
			if (isInitiateOver) {
				// 1.����԰�
				if (GameLauncher.currentPanel.equals(GameLauncher.scenePanel)
						&& isScript && !narratage.isNarratage
						&& !narratage.narratageOver)
					narratage.checkNarratage();
				// 2.����Զ��ĶԻ�
				if (isScript && !dialogueEvent.isSpeaking
						&& !dialogueEvent.dialogueEventOver
						&& !narratage.isNarratage) {
					dialogueEvent.checkAutoDialogue();
				}
				// 3.���NPC
				if (!dialogueEvent.isSpeaking && !narratage.isNarratage)
					npcEvent.checkNPCStop();
				// 4.������
				if (exitEvent.getExits() != null) {
					if (fightEvent.battle1 != null
							&& fightEvent.battle1.size() > 1) {
						if (fightEvent.battle1Over)
							exitEvent.checkExit();
					} else {
						exitEvent.checkExit();
					}
				}
				// 5.���λ�öԻ�
				if (isScript && !dialogueEvent.isSpeaking
						&& !dialogueEvent.dialogueEventOver
						&& !narratage.isNarratage) {
					dialogueEvent.checkLocationDialogue();
				}
				// 6.��鱦��
				if (reader.getTreasureBox() != null)
					equipmentEvent.checBoxes(role.getX(), role.getY());

				// 7.���Ʋ�ս��
				if (fightEvent.battle0 != null) {
					fightEvent.checkBattle0();
				}
				if (GameLauncher.SCENE_SIGNAL == 1) {
					MusicReader.readBGM(reader.getSceneMusic());
					GameLauncher.SCENE_SIGNAL = 0;
				}
				if (isPaintOver)
					this.repaint();
			}
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void keyReleased(int keyCode) {
		switch (keyCode) {
		case KeyEvent.VK_LEFT:
		case KeyEvent.VK_RIGHT:
		case KeyEvent.VK_UP:
		case KeyEvent.VK_DOWN:
			role.setEvent(true);
			break;
		}
	}

	// һ��getters and setters
	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public ArrayList<NPC> getNpcs() {
		return npcs;
	}

	public void setNpcs(ArrayList<NPC> npcs) {
		this.npcs = npcs;
	}

	public Reader getReader() {
		return reader;
	}

	public void setReader(Reader reader) {
		this.reader = reader;
	}

	public Image getBackImage() {
		return backImage;
	}

	public void setBackImage(Image backImage) {
		this.backImage = backImage;
	}

	public Graphics getBackImageGraphics() {
		return backImageGraphics;
	}

	public void setBackImageGraphics(Graphics backImageGraphics) {
		this.backImageGraphics = backImageGraphics;
	}

	public int[][] getMapSet() {
		return mapSet;
	}

	public void setMapSet(int[][] mapSet) {
		this.mapSet = mapSet;
	}

	public void setMouse() {
		int[] pixels = new int[256];
		Image image = Toolkit.getDefaultToolkit().createImage(
				new MemoryImageSource(16, 16, pixels, 0, 16));
		// ����һ��͸�����α�
		Cursor transparentCursor = Toolkit.getDefaultToolkit()
				.createCustomCursor(image, new Point(0, 0), "hidden");
		// ����͸���α꣬�Դ�ģ�����α�״̬
		setCursor(transparentCursor);
	}
}
