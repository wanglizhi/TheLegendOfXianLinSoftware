package scene;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.Timer;
import tools.Reader;

public class Dialogue {
	ScenePanel scene;
	// 对话框图片
	private Image dialogueImage;
	// 对话框的坐标
	private int x_dialogueImage;
	private int y_dialogueImage;
	// 小组件1
	private Image[] icon1 = new Image[2];
	private int count_icon1;
	private int x_icon1 = 0;
	private int y_icon1 = 0;
	// 是否打印对话
	public boolean isPrint;
	// 头像图片集合
	ArrayList<Image> heads = new ArrayList<Image>();
	private int x_head;
	private int y_head;
	// 名字、
	private String name = null;
	private Image nameImage;
	private int x_name;
	private int y_name;
	private int fontSize_dialogue = 20;
	private int fontSize_name = 30;
	// 对话类型，头像序号或者名字，该句话的字符串
	private int type;
	private int headNo;
	// 当前要逐字打印的句子，它的长度，打印的数组，是否打印完该句子
	private String currentSentence = null;
	// 对话的规格
	private int maxRow = 4;
	private int maxCol = 20;
	private char[][] bufferedText = new char[maxRow][maxCol];
	public boolean isSentenceOver;
	public boolean isBufferedTextOver;
	private int count_row;
	private int count_col;
	private int count_sentence;
	Timer dialogueMoveLeft;
	Timer dialogueMoveRight;
	Timer wordsRun;
	Timer icon1Run;
	Timer headRun;
	Timer nameRun;

	// 构造函数
	public Dialogue(ScenePanel scene) {
		this.scene = scene;
		dialogueMoveLeft = new Timer(20, new DialogueImageMoveLeft());
		dialogueMoveRight = new Timer(20, new DialogueImageMoveRight());
		wordsRun = new Timer(30, new timerWordsRun());
		icon1Run = new Timer(500, new Icon1Run());
		headRun = new Timer(10, new HeadMove());
		nameRun = new Timer(10, new NameMove());
		// 把头像图片载入（固定的）先读4个
		for (int i = 1; i <= 91; i++) {
			Image head = Reader.readImage("heads/heads (" + i + ").png");
			heads.add(head);
		}
		// 把对话框载入，固定的
		dialogueImage = Reader.readImage("dialogue//对话框.png");
		nameImage = Reader.readImage("dialogue//name.png");
		// 组件1载入
		icon1[0] = Reader.readImage("dialogue//36-18.png");
		icon1[1] = Reader.readImage("dialogue//36-19.png");
	}

	public void drawDialogue(Graphics g) {
		// 画对话框
		if (type == 0) {
			g.drawImage(dialogueImage, 160, 480, x_dialogueImage,
					y_dialogueImage, 0, 0, x_dialogueImage - 160,
					y_dialogueImage - 480, scene);
		} else if (type == 1) {
			g.drawImage(dialogueImage, x_dialogueImage, y_dialogueImage, 752,
					192, x_dialogueImage - 272, y_dialogueImage - 32, 480, 160,
					scene);
		}
		if (type == 0) {
			// 画头像
			g.drawImage(heads.get(headNo), x_head, y_head, scene);
		} else if (type == 1) {
			g.drawImage(nameImage, x_name, y_name, scene);
			Font font2 = new Font("文鼎粗钢笔行楷", Font.BOLD, fontSize_name);
			g.setFont(font2);
			g.setColor(Color.black);
			g.drawString(name, x_name, y_name + 28);
		}
		if (isPrint) {
			// 画出对话
			Font font1 = new Font("文鼎粗钢笔行楷", Font.BOLD, fontSize_dialogue);
			g.setFont(font1);
			g.setColor(Color.blue);
			for (int i = 0; i < maxRow; i++) {
				for (int j = 0; j < maxCol; j++) {
					if (bufferedText[i][j] != 0) {
						if (bufferedText[i][j] == '@') {
							scene.dialogueEvent.dialogueFight = true;
							continue;
						} else if (bufferedText[i][j] == '$') {
							scene.dialogueEvent.gameOver = true;
							continue;
						}
						if (bufferedText[i][j] == '【') {
							g.setColor(Color.red);
						}
						// 要更改
						if (type == 0) {
							g.drawString(String.valueOf(bufferedText[i][j]),
									160 + 40 + j * 20, 480 + 40 + i * 30);
							x_icon1 = 160 + 40 + fontSize_dialogue * j;
							y_icon1 = 480 + 30 + i * 30;
						} else if (type == 1) {
							g.drawString(String.valueOf(bufferedText[i][j]),
									x_dialogueImage + 40 + j
											* fontSize_dialogue,
									y_dialogueImage + 40 + i * 30);
							x_icon1 = x_dialogueImage + 40 + fontSize_dialogue
									* j;
							y_icon1 = y_dialogueImage + 30 + i * 30;
						}
						if (bufferedText[i][j] == '】') {
							g.setColor(Color.blue);
						}
					}
				}
			}
			if (isSentenceOver || isBufferedTextOver) {
				g.drawImage(icon1[count_icon1], x_icon1, y_icon1, scene);
			}
		}
	}

	public void showSentence(String[] sentenceInfo) {
		count_sentence = 0;
		count_row = 0;
		count_col = 0;
		for (int i = 0; i < maxRow; i++) {
			for (int j = 0; j < maxCol; j++) {
				bufferedText[i][j] = 0;
			}
		}
		this.currentSentence = sentenceInfo[2];
		type = Integer.parseInt(sentenceInfo[0]);
		if (type == 0) {
			headNo = Integer.parseInt(sentenceInfo[1]);
			x_dialogueImage = 160;
			y_dialogueImage = 480;
			x_head = 1024;
			y_head = 416;
		} else if (type == 1) {
			name = sentenceInfo[1];
			x_name = -192;
			y_name = 0;
			x_dialogueImage = 752;
			y_dialogueImage = 192;
		}
		if (type == 0) {
			dialogueMoveRight.start();
		} else if (type == 1) {
			dialogueMoveLeft.start();
		}
		isBufferedTextOver = false;
		isSentenceOver = false;
		isPrint = false;
	}

	public void begin() {
		if (!isSentenceOver) {
			for (int i = 0; i < maxRow; i++) {
				for (int j = 0; j < maxCol; j++) {
					bufferedText[i][j] = 0;
				}
			}
			isBufferedTextOver = false;
			icon1Run.stop();
			count_row = 0;
			count_col = 0;
			wordsRun.start();
		}
	}

	public void stop() {
		for (int i = 0; i < maxRow; i++) {
			for (int j = 0; j < maxCol; j++) {
				bufferedText[i][j] = 0;
			}
		}
		count_sentence = 0;
		count_row = 0;
		count_col = 0;
		icon1Run.stop();
	}

	// 组件1动画
	class Icon1Run implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (count_icon1 == 0) {
				count_icon1 = 1;
			} else if (count_icon1 == 1) {
				count_icon1 = 0;
			}
		}

	}

	// 逐字打印
	class timerWordsRun implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (count_sentence < currentSentence.length()) {
				if (count_col < maxCol) {
					bufferedText[count_row][count_col] = currentSentence
							.charAt(count_sentence);
					count_sentence++;
					count_col++;
				} else {
					count_row++;
					count_col = 0;
					if (count_row < maxRow) {
						bufferedText[count_row][count_col] = currentSentence
								.charAt(count_sentence);
						count_sentence++;
						count_col++;
					} else {
						isBufferedTextOver = true;
						wordsRun.stop();
						icon1Run.start();
					}
				}
			} else {
				isSentenceOver = true;
				wordsRun.stop();
				icon1Run.start();
			}
		}
	}

	// 对话框左移
	class DialogueImageMoveLeft implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (x_dialogueImage > 272) {
				x_dialogueImage -= 48;
				y_dialogueImage -= 16;
			} else {
				dialogueMoveLeft.stop();
				nameRun.start();
			}
		}
	}

	// 对话框右移
	class DialogueImageMoveRight implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (x_dialogueImage < 640) {
				x_dialogueImage += 48;
				y_dialogueImage += 16;
			} else {
				dialogueMoveRight.stop();
				headRun.start();
			}
		}

	}

	// 头像右移
	class HeadMove implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (x_head > 640) {
				x_head -= 16;
			} else {
				headRun.stop();
				wordsRun.start();
				isPrint = true;
			}
		}

	}

	// 名字左移
	class NameMove implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (x_name < 272) {
				x_name += 16;
			} else {
				nameRun.stop();
				wordsRun.start();
				isPrint = true;
			}
		}

	}
}
