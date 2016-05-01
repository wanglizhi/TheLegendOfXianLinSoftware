package battle;

import java.awt.*;
import tools.*;
import java.util.*;
import shop.*;
import main.GameLauncher;
//սʤ���������ʾ
public class VictoryReminder {
    //�ײ�ͼƬ����
	Image back;
	//�����Ʒ�ײ�ͼƬ
	Image thingBack;
	int thing_dx1;
	int thing_dy1;
	int thing_dx2;
	int thing_dy2;
	int thing_sx1;
	int thing_sy1;
	int thing_sx2;
	int thing_sy2;
    //ͼƬ����
	ArrayList<Image> images=new ArrayList<Image>();
	//��С��
	Image zhang1;
	Image zhang2;
	//����
	Image wen1;
	Image wen2;
	//½ѩ��
	Image lu1;
	Image lu2;
	//�δ���
	Image song1;
	Image song2;
    //����ͼƬ
    Image levelUpImage;
    //�����Ʒ��ͼƬ
    Image getThingImage;
    //�Ƿ񻭳�
    boolean isDraw;
    //�Ƿ�ֹͣ
    boolean isStop;
    
    //��һ��ͼƬ�Ƿ񻭳�
    boolean firstIsDraw;
    //�ڶ���ͼƬ�Ƿ񻭳�
    boolean secondIsDraw;
    //����СͼƬ�Ƿ񻭳�
    boolean levelUpIsDraw;
    //�����ƷͼƬ�Ƿ񻭳�
    boolean getThingIsDraw;
    
    //��һ���ַ��Ƿ���ʾ
    boolean firstString;
    //�ڶ����ַ��Ƿ���ʾ
    boolean secondString;
    //�������ַ��Ƿ���ʾ
    boolean thirdString;
    
    //��ʱ���
    int timeCode;
    
    //��Ҫ��ʾ�ַ��ļ���
    ArrayList<Integer> showNums=new ArrayList<Integer>();
    
    //ս���������
    BattlePanel bp;
    
  //Ŀ����ε�һ���ǵ�����
  	int dx1;
  	int dy1;
  	//Ŀ����εڶ����ǵ�����
  	int dx2;
  	int dy2;
  	//Դ���ε�һ���ǵ�����
  	int sx1;
  	int sy1;
  	//Դ���εڶ����ǵ�����
  	int sx2;
  	int sy2;
  	
  	//��һ��ͼƬ����
  	int firstX;
  	int firstY;
  	
  	//��þ�������
  	int firstStringX;
  	int firstStringY;
  	
  	//��������
  	int secondStringX;
  	int secondStringY;
  	
  	//��һ�������Ʒ����
  	int thirdStringX;
  	int thirdStringY;
  	
  	//����ͼƬ����
  	int levelUpX;
  	int levelUpY;
  	
  	//�����ƷͼƬ����
  	int thingX;
  	int thingY;
  	
  	//��ǰս�����Ի�õľ���ֵ
  	int expToGet;
  	//��ǰս�����Ի�õ���Ʒ
  	ArrayList<String> things=new ArrayList<String>();
  	//��ǰս�����Ի�õĽ�Ǯ
  	int moneyToGet;
  	
  	//���췽��
  	public VictoryReminder(BattlePanel bp){
  		this.bp=bp;
  		
  		isDraw=false;
  		isStop=true;
  		
  		dx1=412;
  		dy1=80;
  		dx2=612;
  		dy2=80;
  		
  		sx1=0;
  		sy1=0;
  		sx2=200;
  		sy2=0;
  		
  		thing_dx1=710;
  		thing_dy1=155;
  		thing_dx2=710;
  		thing_dy2=155;
  		thing_sx1=60;
  		thing_sy1=75;
  		thing_sx2=60;
  		thing_sy2=75;
  		
  		levelUpX=412+75;
  		levelUpY=80+60;
  		
  		thingX=660;
  		thingY=80;
  		
  		firstX=412;
  		firstY=160;
  		
  		firstStringX=532;
  		firstStringY=80+120;
  		
  		secondStringX=412+90;
  		secondStringY=80+100;
  		
  		thirdStringX=690;
  		thirdStringY=80+70;
  		
  		loadImage();
  		getInformation();
  	}
  	
  	//����ͼƬ
  	public void loadImage(){
  		back=Reader.readImage("image/ս��ʤ��/0����.png");
  		thingBack=Reader.readImage("image/ս��ʤ��/��Ʒ.png");
  		levelUpImage=Reader.readImage("image/ս��ʤ��/9.png");
  		getThingImage=Reader.readImage("image/ս��ʤ��/�����Ʒ.png");
  		for(int i=1;i<=8;i++){
  			//0--3�ǵ�һ�� 4--7�ǵڶ���
  			Image image=Reader.readImage("image/ս��ʤ��/"+i+".png");
  			images.add(image);
  		}
  	   zhang1=images.get(0);
  	   zhang2=images.get(4);
  	   wen1=images.get(1);
  	   wen2=images.get(5);
  	   lu1=images.get(2);
  	   lu2=images.get(6);
  	   song1=images.get(3);
  	   song2=images.get(7);
  	}
  	
  	//�õ�ս����Ϣ
  	public void getInformation(){
  		//���㵱ǰս�����Ի�ö��پ���,��Ǯ,��Ʒ
  		for(Enemy e:bp.enemies){
  			expToGet+=e.exp;
  			moneyToGet+=e.money;
  			things.add(e.thing);
  		}
        
  		//�õ����� (0--2�Ǹ���Ӣ�۵�ǰ�������辭�� 3--6����С�������� 7--10������
  		//11--14��½ѩ��)
  		Integer i=ZhangXiaoFan.expToLevelUp-ZhangXiaoFan.exp;
  		if(i<0){
  			i=0;
  		}
  		showNums.add(i);
  		i=YuJie.expToLevelUp-YuJie.exp;
  		if(i<0){
  			i=0;
  		}
  		showNums.add(i);
  		i=LuXueQi.expToLevelUp-LuXueQi.exp;
  		if(i<0){
  			i=0;
  		}
  		showNums.add(i);
  		
  		i=ZhangXiaoFan.physicalPower;
  		showNums.add(i);
  		i=ZhangXiaoFan.sprit;
  		showNums.add(i);
  		i=ZhangXiaoFan.agile;
  		showNums.add(i);
  		i=ZhangXiaoFan.strength;
  		showNums.add(i);
  		
  		i=YuJie.physicalPower;
  		showNums.add(i);
  		i=YuJie.sprit;
  		showNums.add(i);
  		i=YuJie.agile;
  		showNums.add(i);
  		i=YuJie.strength;
  		showNums.add(i);
  		
  		i=LuXueQi.physicalPower;
  		showNums.add(i);
  		i=LuXueQi.sprit;
  		showNums.add(i);
  		i=LuXueQi.agile;
  		showNums.add(i);
  		i=LuXueQi.strength;
  		showNums.add(i);
  	}
  	
  	//�������ӵĶ���Ч��
  	public void addValue(int start,Hero hero){
  		if(showNums.get(start)<hero.getPhysicalPower()){
  			showNums.set(start, showNums.get(start)+1);
  		}
  		if(showNums.get(start+1)<hero.getSprit()){
  			showNums.set(start+1, showNums.get(start+1)+1);
  		}
  		if(showNums.get(start+2)<hero.getAgile()){
  			showNums.set(start+2, showNums.get(start+2)+1);
  		}
  		if(showNums.get(start+3)<hero.getStrength()){
  			showNums.set(start+3, showNums.get(start+3)+1);
  		}
  	}
  	
  	//����
  	public void drawVictoryReminder(Graphics g){
  		if(isDraw){
  			g.drawImage(back, dx1,dy1, dx2, dy2, sx1, sy1, sx2, sy2, bp);
  			g.drawImage(thingBack, thing_dx1, thing_dy1, thing_dx2,thing_dy2,thing_sx1,thing_sy1,thing_sx2,thing_sy2,bp);
  			if(firstIsDraw){
  				if(bp.zxf!=null){
  					g.drawImage(zhang1, firstX, firstY, bp);
  				}
  				if(bp.yj!=null){
  					g.drawImage(wen1, firstX, firstY+100, bp);
  				}
  				if(bp.lxq!=null){
  					g.drawImage(lu1, firstX, firstY+100*2,bp);
  				}
  			}
  			if(secondIsDraw){
  				if(bp.zxf!=null&&bp.zxf.isLevelUp==true){
  					g.drawImage(zhang2, firstX, firstY, bp);
  				}
  				if(bp.yj!=null&&bp.yj.isLevelUp==true){
  					g.drawImage(wen2, firstX, firstY+100, bp);
  				}
  				if(bp.lxq!=null&&bp.lxq.isLevelUp==true){
  					g.drawImage(lu2, firstX, firstY+100*2, bp);
  				}
  			}
  		   if(firstString){
  			   if(bp.zxf!=null){
  				g.drawString(expToGet+"", firstStringX, firstStringY);   
  				g.drawString(showNums.get(0)+"", firstStringX, firstStringY+30);
  			   }
  			   if(bp.yj!=null){
  				g.drawString(expToGet+"", firstStringX, firstStringY+100);   
   				g.drawString(showNums.get(1)+"", firstStringX, firstStringY+100+30);
  			   }
  			   if(bp.lxq!=null){
  				g.drawString(expToGet+"", firstStringX, firstStringY+100*2);
  			    g.drawString(showNums.get(2)+"", firstStringX, firstStringY+100*2+30);
  			   }
  		   }
  		   
  		   if(secondString){
  			   if(bp.zxf!=null&&bp.zxf.isLevelUp==true){   
  				  for(int i=3;i<=6;i++){
  					 g.drawString(showNums.get(i)+"", secondStringX, secondStringY+(i-3)*20); 
  				  }
  			   }
  			   
  			 if(bp.yj!=null&&bp.yj.isLevelUp==true){
  				for(int i=7;i<=10;i++){
 					 g.drawString(showNums.get(i)+"", secondStringX, secondStringY+(i-7)*20+100); 
 				  } 
  			 }
  			 
  			 if(bp.lxq!=null&&bp.lxq.isLevelUp==true){
   				for(int i=11;i<=14;i++){
  					 g.drawString(showNums.get(i)+"", secondStringX, secondStringY+(i-11)*20+100*2); 
  				  } 
   			 }
  		   }
  		   if(thirdString){
  			   for(int i=0;i<things.size();i++){
  				   g.drawString(things.get(i).split("/")[0], thirdStringX, thirdStringY+i*20);
  			   }
  			   g.drawString("��Ǯ "+moneyToGet, thirdStringX, thirdStringY+things.size()*20);
  		   }
  		   
  		   if(levelUpIsDraw){
  			   g.drawImage(levelUpImage, levelUpX, levelUpY, bp);
  		   }
  		   if(getThingIsDraw){
  			   g.drawImage(getThingImage, thingX, thingY, bp);
  		   }
  		}
  	}
  	
  	//����
  	public void update(){
  		if(!isStop){
  			if(sy2<480){
  				dy2+=20;
  				sy2+=20;
  			}
  			if(thing_sx1>0){
  				thing_dx1-=4;
  				thing_dx2+=4;
  				thing_sx1-=4;
  				thing_sx2+=4;
  				thing_dy1-=5;
  				thing_dy2+=5;
  				thing_sy1-=5;
  				thing_sy2+=5;
  			}
  			if(thing_sx1==4){
  				 //��ý�Ǯ����Ʒ
  				for(String s:things){
  					switch(s.split("/")[1]){
  					case "1":
  						DrugPack.addDrug(s.split("/")[0], 1);
  						break;
  					case "2":
  						EquipmentPack.addEqupment(s.split("/")[0], 1);
  						break;
  					}
  				}
  				Money.addCoins(moneyToGet);
  			}
  			if(thing_sx1==0){
  				getThingIsDraw=true;
  				thirdString=true;
  			}
  			if(sy2==480){
  				if(secondIsDraw==false&&secondString==false){
  				firstIsDraw=true;
  				firstString=true;
  				}
  				if(timeCode<10){
  					timeCode++;
  				}
  				if(timeCode==10){
  					//��ʼ���о���ֵ�ļ��ٶ���
  					if(expToGet>0){
  					   expToGet-=40;
  					for(int i=0;i<=2;i++){
  						if(showNums.get(i)>0){
  					         showNums.set(i,showNums.get(i)-40);
  						}else{
  							showNums.set(i, 0);
  						}
  					 }
  				   }
  				}//����ֵ���ٵĶ���Ч���������
  					if(expToGet<=0){
  					    expToGet=0;
  						//�ӳ�һ��
  						timeCode++;
  					}
  					if(timeCode==15){
  					for(Hero hero:bp.heroes){
  						if(hero.wheatherLevelUp()==true){
  							levelUpIsDraw=true;
  						}
  					}
  					//û���������˳�ϵͳ
  					if(!levelUpIsDraw){
  						//levelUpIsDraw=false;
  						GameLauncher.zhangXiaoFan.isLevelUp=false;
  						GameLauncher.yuJie.isLevelUp=false;
  						GameLauncher.luXueQi.isLevelUp=false;
  						bp.heroes.clear();
  						GameLauncher.switchTo("scene");	
  				      }
  					}
  					if(timeCode==25){
  					firstIsDraw=false;
  					firstString=false;
  					//��ʾ�ڶ�������
  					secondIsDraw=true;
  					secondString=true;
  				}
  				if(timeCode>=35&&timeCode<55){
  					//��ʼ�������ӵĶ���Ч��
  					if(bp.zxf!=null){
  					addValue(3, bp.zxf);
  					}
  					if(bp.yj!=null){
  					addValue(7, bp.yj);
  					}
  					if(bp.lxq!=null){
  					addValue(11,bp.lxq);
  					}
  				}
  				if(timeCode==55){
  					//����
  					//System.exit(0);
  					levelUpIsDraw=false;
					GameLauncher.zhangXiaoFan.isLevelUp=false;
  					GameLauncher.yuJie.isLevelUp=false;
  					GameLauncher.luXueQi.isLevelUp=false;
  					bp.heroes.clear();
  					GameLauncher.switchTo("scene");
					}
  				}
  			}
  		}
  	}




