package scene;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;

import tools.Reader;
import media.*;

public class EquipmentEvent {
	ScenePanel scene;
	private int x_presentImage;
	Timer presentImageMove = new Timer(50, new PresentImage());
	Timer wordsRun = new Timer(100, new WordsRun());
	private String bufferedText;
	private String text;
	private int count_word;
	private boolean isDrawString;
	private ArrayList<String[]> treasureBox;
	private ArrayList<TreasureBox> treasureBoxes;

	public EquipmentEvent(ScenePanel scene, ArrayList<String[]> treasureBox) {
		this.scene = scene;
		this.treasureBox = treasureBox;
		if (treasureBox != null) {
			treasureBoxes = new ArrayList<TreasureBox>();
			for (int i = 0; i < treasureBox.size(); i++) {
				TreasureBox box = new TreasureBox(treasureBox.get(i)[0],
						treasureBox.get(i)[1], this);
				treasureBoxes.add(box);
			}
		}
	}

	// 画出得到物品的对话框的函数
	public void drawString(String s) {
		x_presentImage = -320;
		bufferedText = null;
		count_word = 0;
		text = s;
		isDrawString = true;
		presentImageMove.start();
		MusicReader.readmusic("Clip750.wav");
	}

	// 画宝箱
	public void drawTreasureBox(Graphics g) {
		if (treasureBox != null) {
			for (int i = 0; i < treasureBoxes.size(); i++) {
				treasureBoxes.get(i).paintBox(g);
			}
		}
	}

	// 轮询宝箱
	public void checBoxes(int x, int y) {
		if (treasureBox != null) {
			for (int i = 0; i < treasureBoxes.size(); i++) {
				treasureBoxes.get(i).checkHero(x, y);
			}
		}
	}

	public void keyPressed(int keyCode) {
		if (treasureBox != null) {
			for (int i = 0; i < treasureBoxes.size(); i++) {
				treasureBoxes.get(i).keyPressed(keyCode);
			}
		}
	}

	// 画出提示信息
	public void drawPresentation(Graphics g) {
		if (isDrawString) {
			g.drawImage(Reader.readImage("dialogue//提示框.png"), x_presentImage,
					288, scene);
			if (bufferedText != null) {
				g.setColor(Color.red);
				g.setFont(new Font("宋体", Font.BOLD, 30));
				g.drawString(bufferedText, x_presentImage + 20, 320);
			}
		}
	}

	// 提示对话框的移动
	class PresentImage implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (x_presentImage <= 352) {
				x_presentImage += 32;
			}
			if (x_presentImage == 352) {
				wordsRun.start();
				presentImageMove.stop();
			}
			if (x_presentImage > 352 && x_presentImage <= 1024) {
				x_presentImage += 32;
			} else if (x_presentImage >= 1024) {
				presentImageMove.stop();
			}
		}

	}

	// 提示信息的逐行打印
	class WordsRun implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (count_word < text.length()) {
				count_word++;
				bufferedText = text.substring(0, count_word);
			} else {
				wordsRun.stop();
				presentImageMove.start();
			}
		}

	}
}
