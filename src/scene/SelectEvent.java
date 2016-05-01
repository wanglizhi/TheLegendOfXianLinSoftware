package scene;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.Timer;

import main.GameLauncher;

import shop.Money;
import tools.Reader;

/**
 * ʵ��ѡ���̵꣩selectShopPanel(NPC����) ѡ���Ƿ�ս��selectBattlePanel(NPC����)
 * ѡ���Ƿ�ش�����selectDialogue(NPC����) ���⣡question(selectDialogue����)------�ش���ȷ��õ���Ǯ��
 * 
 * @author wanglizhi
 * 
 */
public class SelectEvent {
	ScenePanel scene;
	FightEvent fightEvent;
	Image selectImage;
	Image questionImage;
	Image selectIcon;
	// ѡ���¼��еĽű�����(�����ж��鵫Ҫһһ��Ӧ)
	ArrayList<String> selectShopPanel;
	ArrayList<String> selectEquipmentShopPanel;
	ArrayList<String[]> selectBattlePanel;
	ArrayList<String[]> battle2;
	ArrayList<String[]> selectQuestion;
	ArrayList<ArrayList<String>> question;
	ArrayList<String[]> answer;
	// �Ի�����صĲ���
	private int x_selectImage;
	private int y_selectImage;
	private int x1_questionImage;
	private int y1_questionImage;
	private int x2_questionImage;
	private int y2_questionImage;
	private String[] bufferedText;
	private int count_sentence = 1;
	private int count_word;
	private int count_bufferedSentence;
	private int maxLine = 20;
	private int maxLength = 22;
	private int fontSize = 20;
	private ArrayList<String> currentSentences;
	Timer wordsRun = new Timer(30, new WordsRun());
	Timer selectImageMove = new Timer(40, new SelectImageMove());
	Timer questionImageMove = new Timer(50, new QuestionImageMove());
	private boolean shopSelect;
	private boolean equipmentSelect;
	private boolean battleSelect;
	private boolean questionSelect;
	public static ArrayList<String> mapName = new ArrayList<String>();
	public static ArrayList<ArrayList<Boolean>> answeredRecorder = new ArrayList<ArrayList<Boolean>>();
	private ArrayList<Boolean> haveAnswered = new ArrayList<Boolean>();
	private ArrayList<Boolean> haveFighted = new ArrayList<Boolean>();
	private boolean isQuestion;
	private boolean isAnswer;
	private int count_selectYesNo = 2;
	private int count_selectABCD;
	private int count_battle2;
	private int count_questionAndAnswer;
	// ����紫��
	public boolean isSelect;
	public boolean haveEnteredTheScene;
	public int count_scene;

	public SelectEvent(ScenePanel scene, FightEvent fightEvent,
			String fileName, ArrayList<String> selectShopPanel,
			ArrayList<String> selectEquipmentShopPanel,
			ArrayList<String[]> selectBattlePanel,
			ArrayList<String[]> selectQuestion, ArrayList<String[]> battle2,
			ArrayList<ArrayList<String>> question, ArrayList<String[]> answer) {
		this.scene = scene;
		this.fightEvent = fightEvent;
		this.selectShopPanel = selectShopPanel;
		this.selectEquipmentShopPanel = selectEquipmentShopPanel;
		this.selectBattlePanel = selectBattlePanel;
		this.selectQuestion = selectQuestion;
		this.question = question;
		this.battle2 = battle2;
		this.answer = answer;
		// ���������Ƿ�ش���ź�
		if (question != null) {
			for (int i = 0; i < mapName.size(); i++) {
				if (mapName.get(i).equals(fileName)) {
					haveEnteredTheScene = true;
					count_scene = i;
				}
			}
			if (haveEnteredTheScene) {
				haveAnswered = answeredRecorder.get(count_scene);
				haveEnteredTheScene = false;
			} else {
				for (int i = 0; i < question.size(); i++) {
					haveAnswered.add(false);
				}
				mapName.add(fileName);
				answeredRecorder.add(haveAnswered);
			}

		}
		// ѡ��ս��
		if (selectBattlePanel != null) {
			for (int i = 0; i < selectBattlePanel.size(); i++) {
				haveFighted.add(false);
			}
		}
		selectImage = Reader.readImage("dialogue//ѡ���.png");// 500*150
		questionImage = Reader.readImage("dialogue//�����.png");// 500*500
		selectIcon = Reader.readImage("dialogue//icon.png");// 24*24
		bufferedText = new String[maxLine];
	}

	// ��ͼ����
	public void drawSelectImage(Graphics g) {
		if (shopSelect || battleSelect || questionSelect || equipmentSelect) {
			g.drawImage(selectImage, 262, 245, 262 + x_selectImage,
					y_selectImage + 245, 0, 0, x_selectImage, y_selectImage,
					scene);
			g.setColor(Color.CYAN);
			g.setFont(new Font("����", Font.BOLD, fontSize));
			for (int i = 0; i < bufferedText.length; i++) {
				if (bufferedText[i] != null) {
					if (i == count_selectYesNo) {
						g.drawImage(selectIcon, 292, 270 + 30 * i, scene);
						g.setColor(Color.red);
						g.drawString(bufferedText[i], 312, 285 + 30 * i);
						g.setColor(Color.CYAN);
					} else {
						g.drawString(bufferedText[i], 292, 285 + 30 * i);
					}
				}
			}
		} else if (isQuestion) {
			g.drawImage(questionImage, x1_questionImage, y1_questionImage,
					x2_questionImage, y2_questionImage, x1_questionImage - 262,
					y1_questionImage - 70, x2_questionImage - 262,
					y2_questionImage - 70, scene);
			g.setColor(Color.CYAN);
			g.setFont(new Font("�Ķ��ֱָ��п�", Font.BOLD, fontSize));
			for (int i = 0; i < bufferedText.length; i++) {
				if (bufferedText[i] != null) {
					if (i == count_selectABCD) {
						g.drawImage(selectIcon, 292, 83 + 30 * i, scene);
						g.setColor(Color.red);
						g.drawString(bufferedText[i], 312, 100 + 30 * i);
						g.setColor(Color.CYAN);
					} else {
						g.drawString(bufferedText[i], 292, 100 + 30 * i);
					}
				}
			}
		} else if (isAnswer) {
			g.drawImage(selectImage, 262, 245, 262 + x_selectImage,
					y_selectImage + 245, 0, 0, x_selectImage, y_selectImage,
					scene);
			g.setColor(Color.CYAN);
			g.setFont(new Font("����", Font.BOLD, fontSize));
			for (int i = 0; i < bufferedText.length; i++) {
				if (bufferedText[i] != null) {
					g.drawString(bufferedText[i], 292, 285 + 30 * i);
				}
			}
		}
	}

	// �жϵ�ǰNPC�Ƿ����ѡ���¼���Ҫ����
	public boolean checkSelectEvent(int npcNo) {
		if (selectShopPanel != null
				&& npcNo == Integer.parseInt(selectShopPanel.get(0))) {
			if (!isSelect)
				showSelectShopPanel();
			return true;
		}
		if (selectEquipmentShopPanel != null
				&& npcNo == Integer.parseInt(selectEquipmentShopPanel.get(0))) {
			if (!isSelect)
				showSelectEquipmentShopPanel();
			return true;
		}
		if (selectBattlePanel != null) {
			for (int i = 0; i < selectBattlePanel.size(); i++) {
				if (!haveFighted.get(i)) {
					if (npcNo == Integer.parseInt(selectBattlePanel.get(i)[0])) {
						if (!isSelect)
							showSelectBattlePanel(i);
						count_battle2 = i;
						return true;
					}
				}
			}
		}
		if (selectQuestion != null) {
			if (isSelect)
				return true;
			for (int i = 0; i < selectQuestion.size(); i++) {
				if (!haveAnswered.get(i)) {
					if (npcNo == Integer.parseInt(selectQuestion.get(i)[0])) {
						if (!isSelect) {
							showSelectQuestion(i);
						}
						return true;
					}
				}
			}
		}
		return false;
	}

	// ѡ���Ƿ����ҽԺ
	public void showSelectShopPanel() {
		isSelect = true;
		shopSelect = true;
		currentSentences = selectShopPanel;
		x_selectImage = 0;
		y_selectImage = 0;
		clear();
		count_selectYesNo = 2;
		maxLength = 22;
		selectImageMove.start();
	}

	// ѡ���Ƿ����װ���̵�
	public void showSelectEquipmentShopPanel() {
		isSelect = true;
		equipmentSelect = true;
		currentSentences = selectEquipmentShopPanel;
		x_selectImage = 0;
		y_selectImage = 0;
		clear();
		count_selectYesNo = 2;
		maxLength = 22;
		selectImageMove.start();
	}

	// ѡ���Ƿ�ս��
	public void showSelectBattlePanel(int battleNo) {
		isSelect = true;
		battleSelect = true;
		currentSentences = new ArrayList<String>();
		for (int i = 0; i < 4; i++) {
			currentSentences.add(selectBattlePanel.get(battleNo)[i]);
		}
		x_selectImage = 0;
		y_selectImage = 0;
		clear();
		count_selectYesNo = 2;
		maxLength = 22;
		selectImageMove.start();
	}

	// ѡ���Ƿ�ش�����
	public void showSelectQuestion(int questionNo) {
		count_questionAndAnswer = questionNo;
		isSelect = true;
		questionSelect = true;
		currentSentences = new ArrayList<String>();
		for (int i = 0; i < 4; i++) {
			currentSentences.add(selectQuestion.get(questionNo)[i]);
		}
		x_selectImage = 0;
		y_selectImage = 0;
		clear();
		count_selectYesNo = 2;
		maxLength = 22;
		selectImageMove.start();
	}

	// �ش�����׶�
	public void showQuestion(int questionNo) {
		isQuestion = true;
		currentSentences = question.get(questionNo);
		x1_questionImage = 512;
		y1_questionImage = 320;
		x2_questionImage = 512;
		y2_questionImage = 320;
		clear();
		maxLength = 44;
		count_selectABCD = question.get(questionNo).size() - 5;
		questionImageMove.start();
	}

	// ��ʾ�ش�
	public void showAnswer(ArrayList<String> response) {
		isAnswer = true;
		currentSentences = response;
		x_selectImage = 0;
		y_selectImage = 0;
		clear();
		count_selectYesNo = 2;
		maxLength = 22;
		selectImageMove.start();
	}

	public void clear() {
		for (int i = 0; i < bufferedText.length; i++) {
			bufferedText[i] = null;
		}
		count_sentence = 1;
		count_word = 0;
		count_bufferedSentence = 0;
	}

	// ��������
	public void keyPressed(int keyCode) {
		if (keyCode == KeyEvent.VK_DOWN || keyCode == KeyEvent.VK_UP) {
			if (count_selectYesNo == 2) {
				count_selectYesNo = 3;
			} else if (count_selectYesNo == 3) {
				count_selectYesNo = 2;
			}
			if (isQuestion) {
				if (keyCode == KeyEvent.VK_DOWN) {
					if (count_selectABCD < question
							.get(count_questionAndAnswer).size() - 2) {
						count_selectABCD++;
					} else {
						count_selectABCD = question
								.get(count_questionAndAnswer).size() - 5;
					}
				} else if (keyCode == KeyEvent.VK_UP) {
					if (count_selectABCD > question
							.get(count_questionAndAnswer).size() - 5) {
						count_selectABCD--;
					} else {
						count_selectABCD = question
								.get(count_questionAndAnswer).size() - 2;
					}
				}
			}
		} else if (keyCode == KeyEvent.VK_ENTER) {
			if (shopSelect) {
				if (count_selectYesNo == 2) {
					GameLauncher.switchTo("shop");
				} else {
					isSelect = false;
					shopSelect = false;
				}
			} else if (equipmentSelect) {
				if (count_selectYesNo == 2) {
					GameLauncher.switchTo("equipmentShop");
				} else {
					isSelect = false;
					equipmentSelect = false;
				}
			} else if (battleSelect) {
				if (count_selectYesNo == 2) {
					fightEvent.fight(battle2.get(count_battle2));
					haveFighted.remove(count_battle2);
					haveFighted.add(count_battle2, true);
					GameLauncher.switchTo("battle");
					ArrayList<String> response = new ArrayList<String>();
					response.add(null);
					response.add("��Ȼ������ô�þͱ�װ����!��,�����Ժ���Ҫ��������!  ��������õ��˾�����ɧ!");
					showAnswer(response);
					battleSelect = false;
				} else {
					isSelect = false;
					battleSelect = false;
				}
			} else if (questionSelect) {
				if (count_selectYesNo == 2) {
					showQuestion(count_questionAndAnswer);
					questionSelect = false;
					isQuestion = true;
				} else {
					isSelect = false;
					questionSelect = false;
				}
			} else if (isQuestion) {
				if (count_selectABCD == Integer.parseInt(answer
						.get(count_questionAndAnswer)[0])) {
					// �ش���ȷ����Ǯ
					int i = 500 + (int) (500 * Math.random());
					Money.addCoins(i);
					scene.equipmentEvent.drawString("�õ�" + i + "�����");
					ArrayList<String> response = new ArrayList<String>();
					response.add(null);
					response.add(answer.get(count_questionAndAnswer)[1]);
					response.add(answer.get(count_questionAndAnswer)[3]);
					isQuestion = false;
					isAnswer = true;
					showAnswer(response);
					haveAnswered.remove(count_questionAndAnswer);
					haveAnswered.add(count_questionAndAnswer, true);
					answeredRecorder.remove(count_scene);
					answeredRecorder.add(count_scene, haveAnswered);
				} else {
					// �ش���ȷ����Ǯ
					int i = 500 + (int) (500 * Math.random());
					Money.reduceCoins(i);
					scene.equipmentEvent.drawString("�ش���󣬿۵�" + i + "�����");
					ArrayList<String> response = new ArrayList<String>();
					response.add(null);
					response.add(answer.get(count_questionAndAnswer)[1]);
					response.add(answer.get(count_questionAndAnswer)[2]);
					isQuestion = false;
					isAnswer = true;
					showAnswer(response);
					haveAnswered.remove(count_questionAndAnswer);
					haveAnswered.add(count_questionAndAnswer, true);
					answeredRecorder.remove(count_scene);
					answeredRecorder.add(count_scene, haveAnswered);
				}
			}
		} else if (keyCode == KeyEvent.VK_SPACE) {
			if (isAnswer) {
				isSelect = false;
				isAnswer = false;
			}
		}
	}

	// ѡ��Ի���
	class SelectImageMove implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (x_selectImage <= 500) {
				x_selectImage += 50;
				y_selectImage += 15;
			} else {
				selectImageMove.stop();
				wordsRun.start();
			}
		}
	}

	// ����Ի���
	class QuestionImageMove implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (x1_questionImage >= 262) {
				x1_questionImage -= 25;
				y1_questionImage -= 25;
				x2_questionImage += 25;
				y2_questionImage += 25;
			} else {
				questionImageMove.stop();
				wordsRun.start();
			}
		}

	}

	// ���ִ�ӡ
	class WordsRun implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (count_sentence < currentSentences.size()) {
				if (count_word < currentSentences.get(count_sentence).length()) {
					count_word++;
					if (count_word == maxLength) {
						count_bufferedSentence++;
					}
					if (count_word >= maxLength)
						bufferedText[count_bufferedSentence] = currentSentences
								.get(count_sentence).substring(maxLength - 1,
										count_word);
					else {
						bufferedText[count_bufferedSentence] = currentSentences
								.get(count_sentence).substring(0, count_word);
					}

				} else {
					count_sentence++;
					count_bufferedSentence++;
					count_word = 0;
				}
			} else {
				wordsRun.stop();
			}
		}

	}
}
