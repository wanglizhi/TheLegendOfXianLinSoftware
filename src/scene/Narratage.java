package scene;

import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import tools.Reader;

public class Narratage {
	ScenePanel scene;
	// 背景图片
	private ArrayList<Image> backImages1 = new ArrayList<Image>();
	private ArrayList<String> narratage;
	private int index = 0;
	Timer background = new Timer(180, new Background());
	Timer wordRun = new Timer(50, new WordRun());
	// 最大行数
	private int maxLine = 10;
	private Color color1;
	private Font font;
	// 记载第几句话
	private int count0;
	// 记录第几个字
	private int count1;
	// 记录第几行
	private int count2;
	private String bufferedText[];
	private int fontSize;
	private boolean isRun;
	public boolean isNarratage;
	public boolean narratageOver;

	public Narratage(ScenePanel scene, ArrayList<String> narratage) {
		this.narratage = narratage;
		if (narratage == null) {
			narratageOver = true;
		}
		this.scene = scene;
		for (int i = 2; i <= 53; i++) {
			backImages1.add(Reader
					.readImage("backImages//NarratageBackImages//all_magic_21-"
							+ i + ".png"));
		}
	}

	public void drawNarratage(Graphics g) {
		g.drawImage(backImages1.get(index), 0, 0, 1024, 640, 0, 0, 639, 395,
				scene);
		g.setColor(color1);
		g.setFont(font);
		for (int i = 0; i < maxLine; i++) {
			// 转换当前字体top位置
			Double top = new Double((double) fontSize
					* (3.0D + 2.0D * (double) i));
			if (bufferedText[i] != null) {
				g.drawString(bufferedText[i], 50, top.intValue());
			}
		}
	}

	public void init() {
		bufferedText = new String[maxLine];
		fontSize = 20;
		font = new Font("文鼎粗钢笔行楷", Font.BOLD, fontSize);
		color1 = Color.WHITE;
		background.start();
		wordRun.start();
	}

	public void stop() {
		count0 = 0;
		count1 = 0;
		count2 = 0;
		wordRun.stop();
		background.stop();
		isNarratage = false;
		narratageOver = true;
		narratage.clear();
	}

	public void begin() {
		// 清除所有缓存数据。
		for (int i = 0; i < bufferedText.length; i++) {
			bufferedText[i] = null;
			count2 = 0;
		}
	}

	public void checkNarratage() {
		if (!narratage.isEmpty()) {
			if (!isRun) {
				init();
				isRun = true;
				isNarratage = true;
			}
		}
	}

	class WordRun implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (count1 == narratage.get(count0).length()) {
				bufferedText[count2] = narratage.get(count0).substring(0,
						count1);
				try {
					Thread.sleep(500);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (count2 == maxLine) {
					begin();
				}
				count0++;
				count2++;
				count1 = 0;
			} else {
				count1++;
				bufferedText[count2] = narratage.get(count0).substring(0,
						count1);
			}
			if (count0 == narratage.size()) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				stop();
			}
		}

	}

	class Background implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (index < backImages1.size() - 1) {
				index++;
			} else {
				index = 0;
			}
		}
	}

	public ArrayList<String> getNarratage() {
		return narratage;
	}

	public void setNarratage(ArrayList<String> narratage) {
		this.narratage = narratage;
	}
}
