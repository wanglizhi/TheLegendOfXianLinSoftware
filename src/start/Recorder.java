package start;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import main.*;
import scene.*;
import tools.Reader;

public class Recorder {
	//接受四个信息列表，分别为role，scene，menu，shop
	private ArrayList<String> roleAndMapInfo;
	private ArrayList<String> zhangXiaoFanInfo;
	private ArrayList<String> luXueQiInfo;
	private ArrayList<String> yuJieInfo;
	private ArrayList<String> sceneInfo;
	private ArrayList<String> menuInfo;
	private ArrayList<String> shopInfo;
	private ArrayList<String> equipmentShopInfo;
	private ArrayList<String> questionInfo;
	private ArrayList<String> answerInfo;
	private SaveAndLoad sa;
	private BufferedWriter writer;
	
	public Recorder(){
		sa =new SaveAndLoad(GameLauncher.scenePanel);
	}

	public void save(int textCode) {
		roleAndMapInfo=new ArrayList<String>();
		roleAndMapInfo.add(""+SaveAndLoad.zhang);
		roleAndMapInfo.add(""+SaveAndLoad.lu);
		roleAndMapInfo.add(""+SaveAndLoad.wen);
		roleAndMapInfo.add(SaveAndLoad.mapName);
		roleAndMapInfo.add(Reader.task);
		zhangXiaoFanInfo=GameLauncher.zhangXiaoFan.saveRoleInfo();
		luXueQiInfo=GameLauncher.luXueQi.saveRoleInfo();
		yuJieInfo=GameLauncher.yuJie.saveRoleInfo();
		sceneInfo=sa.saveSceneInfo();
		menuInfo=GameLauncher.menuPanel.equipPanel.saveEquipInfo();
		shopInfo=GameLauncher.shopPanel.saveShopInfo();
		equipmentShopInfo=GameLauncher.equipmentShopPanel.saveEquipmentShopInfo();
		questionInfo=sa.saveQuestion();
		answerInfo=sa.saveAnswer();
		writeInfo(textCode);
	}
	
	private void writeInfo(int textcode){
		try {
			writer=new BufferedWriter(new FileWriter(new File("sources/Record/存档"+textcode+".txt")));
			for(String word:roleAndMapInfo)
				writer.write(word+"A");
			writer.newLine();
			for(String word:zhangXiaoFanInfo)
				writer.write(word+"A");
			writer.newLine();
			for(String word:luXueQiInfo)
				writer.write(word+"A");
			writer.newLine();
			for(String word:yuJieInfo)
				writer.write(word+"A");
			writer.newLine();
			for(String word:sceneInfo)
				writer.write(word+"A");
			writer.newLine();
			for(String word:menuInfo)
				writer.write(word+"A");
			writer.newLine();
			for(String word:shopInfo)
				writer.write(word+"A");
			writer.newLine();
			for(String word:equipmentShopInfo)
				writer.write(word+"A");
			for(String word:questionInfo)
				writer.write(word+"A");
			writer.newLine();
			for(String word:answerInfo)
				writer.write(word+"A");
			writer.newLine();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
