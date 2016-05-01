package start;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import scene.SaveAndLoad;

import main.GameLauncher;

public class Loader {
	// 这些list是为了接受寻当中的信息的
	private ArrayList<String> zhangXiaoFanInfo;
	private ArrayList<String> luXueQiInfo;
	private ArrayList<String> yuJieInfo;
	private ArrayList<String> sceneInfo;
	private ArrayList<String> menuInfo;
	private ArrayList<String> shopInfo;
	private ArrayList<String> equipmentShopInfo;
	private ArrayList<String> questionInfo;
	private ArrayList<String> answerInfo;
	private BufferedReader reader;

	public ArrayList<String> getQuestionInfo() {
		return questionInfo;
	}

	public void setQuestionInfo(ArrayList<String> questionInfo) {
		this.questionInfo = questionInfo;
	}

	public ArrayList<String> getAnswerInfo() {
		return answerInfo;
	}

	public void setAnswerInfo(ArrayList<String> answerInfo) {
		this.answerInfo = answerInfo;
	}

	public void load(int textcode) {
		zhangXiaoFanInfo = loadLine(textcode, 2);
		luXueQiInfo=loadLine(textcode,3);
		yuJieInfo=loadLine(textcode,4);
		sceneInfo=loadLine(textcode, 5);
		menuInfo=loadLine(textcode,6);
		shopInfo=loadLine(textcode, 7);
		equipmentShopInfo=loadLine(textcode, 8);
		questionInfo=loadLine(textcode, 9);
		answerInfo=loadLine(textcode, 10);
		GameLauncher.zhangXiaoFan.loadRoleInfo(zhangXiaoFanInfo);
		GameLauncher.luXueQi.loadRoleInfo(luXueQiInfo);
		GameLauncher.yuJie.loadRoleInfo(yuJieInfo);
		GameLauncher.scenePanel.sal.loadSceneInfo(sceneInfo);
		GameLauncher.menuPanel.equipPanel.initialEquipInfo(menuInfo);
		GameLauncher.shopPanel.initialShopInfo(shopInfo);
		GameLauncher.equipmentShopPanel.initialEquipmentShopInfo(equipmentShopInfo);
		SaveAndLoad.zhang=Boolean.parseBoolean(getTextInfo(textcode).get(0));
		SaveAndLoad.lu=Boolean.parseBoolean(getTextInfo(textcode).get(1));
		SaveAndLoad.wen=Boolean.parseBoolean(getTextInfo(textcode).get(2));
		
	}
	
	public ArrayList<String> getTextInfo(int textcode){
		return loadLine(textcode,1);
	}

	private ArrayList<String> loadLine(int textcode, int linecode) {
		ArrayList<String> bufferList = new ArrayList<String>();
		int readingLineCode = 0;
		try {
			reader = new BufferedReader(new FileReader(new File(
					"sources/Record/存档" + textcode + ".txt")));
			String line;
			while ((line = reader.readLine()) != null) {
				readingLineCode++;
				String[] lineArray = line.split("A");
				for (int i = 0; i < lineArray.length; i++)
					if (lineArray[i] != null && readingLineCode == linecode)
						bufferList.add(lineArray[i]);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bufferList;
	}
	
	boolean isNull(int textcode){
		if(loadLine(textcode,1).isEmpty())
		return true;
		else
		return false;
	}
}
