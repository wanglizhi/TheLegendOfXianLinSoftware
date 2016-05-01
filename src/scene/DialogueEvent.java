package scene;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import main.GameLauncher;

public class DialogueEvent {
	ScenePanel scene;
	public boolean dialogueEventOver;// 有无主线对话
	public boolean isSpeaking;// 是否正在进行主线对话
	private int dialogueOrder = 0;
	private int sentenceOrder = 0;
	private boolean dialogueOver;
	public boolean dialogueFight;
	public boolean gameOver;
	public ArrayList<String> dialogueCode;
	public ArrayList<ArrayList<String[]>> dialogues;

	public DialogueEvent(ScenePanel scene, ArrayList<String> dialogueCode,
			ArrayList<ArrayList<String[]>> dialogues) {
		this.scene = scene;
		this.dialogueCode = dialogueCode;
		this.dialogues = dialogues;
		if (dialogueCode == null) {
			dialogueEventOver = true;
		}
	}

	public void checkLocationDialogue() {
		if (dialogueOrder < dialogueCode.size()) {
			if (!dialogueEventOver
					&& dialogueCode.get(dialogueOrder).length() > 2) {
				String[] locations = dialogueCode.get(dialogueOrder).split(",");
				int x1 = scene.role.getX();
				int y1 = scene.role.getY();
				for (int i = 0; i < locations.length; i++) {
					int x2 = Integer.parseInt((locations[i].split(" ")[0]));
					int y2 = Integer.parseInt((locations[i].split(" ")[1]));
					if (x2 == x1 && y2 == y1) {
						startSpeak();
					}
				}
			}
		}
	}

	public boolean checkDialogue() {
		if (scene.isScript && !dialogueEventOver
				&& dialogueCode.get(dialogueOrder).length() <= 2) {
			if (Integer.parseInt(dialogueCode.get(dialogueOrder)) != -1) {
				int type = scene.npcs.get(
						Integer.parseInt(dialogueCode.get(dialogueOrder)))
						.getType();
				if (type == 0) {
					int x1 = scene.npcs.get(
							Integer.parseInt(dialogueCode.get(dialogueOrder)))
							.getX();
					int y1 = scene.npcs.get(
							Integer.parseInt(dialogueCode.get(dialogueOrder)))
							.getY();
					int x2 = scene.role.getX();
					int y2 = scene.role.getY();
					if ((x1 - 1 == x2 && y1 == y2 - 1)
							|| (x1 + 1 == x2 && y1 == y2 - 1)
							|| (x1 == x2 && y1 == y2)
							|| (x1 == x2 && y1 + 2 == y2)) {
						startSpeak();
						return true;
					}
				} else if (type == 1) {
					if (!scene.npcs
							.get(Integer.parseInt(dialogueCode
									.get(dialogueOrder))).getWalk().isRunning()) {
						startSpeak();
						return true;
					}
				} else if (type == 2) {
					if (!scene.npcs
							.get(Integer.parseInt(dialogueCode
									.get(dialogueOrder))).getAction()
							.isRunning()) {
						startSpeak();
						return true;
					}
				}

			}
		}
		return false;
	}

	public void checkAutoDialogue() {
		if (dialogueOrder == 0) {
			if (!dialogueEventOver
					&& dialogueCode.get(dialogueOrder).length() <= 2) {
				if (Integer.parseInt(dialogueCode.get(dialogueOrder)) == -1) {
					startSpeak();
				}
			}
		}
	}

	public void startSpeak() {
		dialogueOver = false;
		sentenceOrder = 0;
		isSpeaking = true;
		scene.role.setEvent(true);
		scene.dialogue.showSentence(dialogues.get(dialogueOrder).get(
				sentenceOrder));
		sentenceOrder++;
		dialogueOrder++;
	}

	public void keyPressed(int keyCode) {
		if (keyCode == KeyEvent.VK_SPACE) {
			if (dialogueOver) {
				if (dialogueOrder >= dialogues.size()) {
					dialogueEventOver = true;
				}
				isSpeaking = false;
				if (dialogueFight) {
					scene.fightEvent.startBattle1();
					dialogueFight = false;
				}
				if (gameOver) {
					GameLauncher.switchTo("end");
					gameOver = false;
				}
			} else {
				if (scene.dialogue.isBufferedTextOver) {
					scene.dialogue.begin();
				} else if (scene.dialogue.isSentenceOver) {
					scene.dialogue.showSentence(dialogues
							.get(dialogueOrder - 1).get(sentenceOrder));
					sentenceOrder++;
					scene.dialogue.icon1Run.stop();
					if (sentenceOrder >= dialogues.get(dialogueOrder - 1)
							.size()) {
						scene.dialogue.icon1Run.stop();
						dialogueOver = true;
					}
				}
			}
		}
	}

	public int getDialogueOrder() {
		return dialogueOrder;
	}

	public void setDialogueOrder(int dialogueOrder) {
		this.dialogueOrder = dialogueOrder;
	}
}
