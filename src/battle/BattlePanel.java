package battle;
import javax.swing.*;

import tools.Reader;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;
import java.util.ArrayList;
import media.*;

public class BattlePanel extends JPanel implements Runnable{
	private static final long serialVersionUID = 4L;
	//�����Ⱥ͸߶�
	private static final int WIDTH=32*32;
	private static final int HEIGHT=20*32;
	
	//ս��ͼƬ����
	Image background;
	//����ͼƬ
	Image bufferedPic;
	//���廭��
	Graphics bufferedGraphics;
	//����
	Font font;	
	//�α����
	Mouse mouse;
	
	//�α굱ǰλ��
	int currentX;
	int currentY;
	
	//����̨
	Command command;
	
	//���ܲ˵�
	SkillMenu skillMenu;
	
	//ҩƷ�˵�
	DrugMenu drugMenu;
	
	//״̬��
	StateBlank stateBlank;
	
	//ָʾ��־
	Instruct instruct;
	
	//����ѡ����
	EnemySlector enemySlector;
	
	//����������
	LaunchAttack launchAttack;
	
	//�����
	Check check;
	
	//�˺�ֵ��ʾ
	ArrayList<HurtValue> hurtValues;
	
	//��ʼ����
	StartAnimation startAnimation;
	
	//��������
	BackgroundAnimation backgroundAnimation;
	
	//���ܶ���
	SkillAnimation skillAnimation;
	
	//��ʾ
	Reminder reminder;
	
	//ս��ʤ����ʾ
	VictoryReminder victoryReminder;
	
	//ŭ����
	ArrayList<AngryBar> angryBars=new ArrayList<AngryBar>();
	
	//�ҷ�ս����λ����
	ZhangXiaoFan zxf;
	YuJie yj;
	LuXueQi lxq;
	//�ҷ�ս����λ����
	ArrayList<Hero> heroes=new ArrayList<Hero>();
	
	//��������
	Enemy em1;
	Enemy em2;
	Enemy em3;
	ArrayList<Enemy> enemies =new ArrayList<Enemy>();
	
	//��������
	EnemyAI enemyAI;
	
	//������
	ProgressBar progressBar;
	
	//С����
	Pet pet;
	
	//��Ϸ��������
	GameOver gameOver;
	
	//��ǰ�غ� 1.��С�� 2.���� 3.½ѩ�� 4.�δ��� 5.����1 6.����2 7.����3
	int currentRound;
	
	//��ǰ���������� 1.��С�� 2.���� 3.½ѩ�� 4.�δ��� 5.����1 6.����2 7.����3
	int currentBeAttacked;
	
	//��ǰ����ģʽ  1.��ͨ���� 2.����1 3.����2 4.����3 5.����4 6.����5
	int currentPattern;
	
	//���췽��
	public BattlePanel(){
		 setPreferredSize(new Dimension(WIDTH,HEIGHT));
		 //˫����׼��
		 bufferedPic=new BufferedImage(WIDTH, HEIGHT,BufferedImage.TYPE_INT_ARGB);
		 bufferedGraphics=bufferedPic.getGraphics();
		 font=new Font("�Ķ��ֱָ��п�", Font.BOLD, 15);
		 bufferedGraphics.setFont(font);
		 
		 //�����α�
		 mouse=new Mouse(this);
		 setMouse();
		 requestFocus();
		 
		 
		 //��������̨
		 command=new Command(this);
		 
		 //����ָʾͼ��
		 instruct=new Instruct(this);
		 
		 //����ҩƷ�˵�
		 drugMenu=new DrugMenu(this);
		 
		 //�����˺�ֵ��ʾ
		 hurtValues=new ArrayList<HurtValue>();
		 
		 //������������
		 backgroundAnimation=new BackgroundAnimation(this);
		 
		 //�������ܶ���
		 skillAnimation=new SkillAnimation(this);
		 
		 //������ʾ
		 reminder=new Reminder(this, 500,120);
		 
		 //���������
		 check=new Check(this);
		 	 
		 //�����߳�
		 Thread t=new Thread(this);
		 t.start();
	}
	
	//��ʼ������
	public void initial(String s,ZhangXiaoFan z,YuJie y,LuXueQi l,Enemy e1,Enemy e2,Enemy e3){
		background=Reader.readImage(s);
		//���ݱ�����������
		switch(s){
		case "image/����ͼ/��ħɽ����.png":
			MusicReader.readBGM("B6.mp3");
			break;
		case "image/����ͼ/У԰С��.png":
			MusicReader.readBGM("B6.mp3");
			break;
		case "image/����ͼ/����Թ�.png":
			MusicReader.readBGM("�̽�.mp3");
			break;
		case "image/����ͼ/�ر�����.png":
			MusicReader.readBGM("佻�ϴ��.mp3");
			break;
		case "image/����ͼ/�ɶ��Թ�.png":
			MusicReader.readBGM("����һ��.mp3");
			break;
		case "image/����ͼ/�ɶ���ѧ¥.png":
			MusicReader.readBGM("佻�ϴ��������.mp3");
			break;
		case "image/����ͼ/�ؾ���һ��.png":
			MusicReader.readBGM("��ѧ�ȵ�һս.mp3");
			break;
		case "image/����ͼ/�ؾ�������.png":
			MusicReader.readBGM("��Σ����.mp3");
			break;
		case "image/����ͼ/�����Թ�.png":
			MusicReader.readBGM("��Ӱ��Ե.mp3");
			break;
		case "image/����ͼ/��������.png":
			MusicReader.readBGM("����.mp3");
			break;
		case "image/����ͼ/У԰�ڲ�.png":
			MusicReader.readBGM("C45.mp3");
			break;
		case "image/����ͼ/���䳡.png":
			MusicReader.readBGM("��ӿ����.mp3");
			break;
		}
		
		
		//����ҷ���ս����λ
		zxf=z;
		yj=y;
		lxq=l;
		if(zxf!=null){
		heroes.add(z);
		}
		if(yj!=null){
		heroes.add(y);
		}
		if(lxq!=null){
		heroes.add(l);
		}
		//��ӵ���
		em1=e1;
		em2=e2;
		em3=e3;
		
		//��������
		enemyAI=new EnemyAI(this);
		//��ӹ��� ע��˳�� �������������
		if(em2!=null){
			enemies.add(em2);
		}
		if(em1!=null){
			enemies.add(em1);
		}
		if(em3!=null){
			enemies.add(em3);
		}
		
		//����С����
		pet=null;
		 
		progressBar=new ProgressBar(300, 50, this);
		stateBlank=new StateBlank(this);
		
		//����ŭ����
		angryBars.clear();
		for(Hero hero:heroes){
			AngryBar an=new AngryBar(this, hero);
			angryBars.add(an);
		}
		
		 //�������ܲ˵�
		 skillMenu=new SkillMenu(this);
		 
		 //��������ѡ����
		 enemySlector=new EnemySlector(this);
		 
		 //��������������
		 launchAttack=new LaunchAttack(this);
		 
		 //����ʤ����ʾ
		 victoryReminder=new VictoryReminder(this);
		 
		 //������Ϸ��������
		 gameOver=new GameOver(this);
		 
		 //������ʼ����
		 startAnimation=new StartAnimation(this);
		 
		//��������ʼ
		 progressBar.isStop=false;
		 command.isDraw=false;
		 drugMenu.isDraw=false;
		 instruct.isDraw=false;
		 
		 //����ϳ�ս��ʱ����������,����,ÿ�˻ظ�10%��hp
		 for(Hero hero:heroes){
			 if(hero.wheatherDead()){
				 hero.setDead(false);
				 //���hpΪ0
				 if(hero.getHp()==0){
				 hero.setHp(((int)(hero.getHpMax()*0.1)));
				 }
			 }
		 }
	}
	
	//����һ�����̼���(���)
	public void keyPressed(int keyCode){
		if(keyCode==KeyEvent.VK_J){
			enemies.clear();
			em1=null;
			em2=null;
			em3=null;
			check.checkEnemyDead();
		}
	}
	
	
	
	//�������
	public void setMouse(){
		int[] pixels = new int[256];
	    Image image = Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(16, 16, pixels, 0, 16));
	    // ����һ��͸�����α�      
	     Cursor transparentCursor = Toolkit.getDefaultToolkit() .createCustomCursor(image, new Point(0, 0), "hidden");
	     // ����͸���α꣬�Դ�ģ�����α�״̬       
	     setCursor(transparentCursor);
	     addMouseListener(new MouseAdapter() {
	    	 public void mousePressed(MouseEvent e){
	    		 currentX=e.getX();
	    		 currentY=e.getY();
	    		 
	    		 if(command.isDraw){
	    			 command.checkPressed();
	    		 }
	    		 
	    		 if(skillMenu.isDraw){
	    			 skillMenu.checkPressed();
	    		 }
	    		 
	    		 if(drugMenu.isDraw){
	    			 drugMenu.checkPressed();
	    		 }
	    		 
	    		 if(enemySlector.isSlectable){
	    			 enemySlector.checkClick(currentX, currentY);
	    		 }
	    	 }
	    	 
	    	 public void mouseReleased(MouseEvent e) {
	    		 currentX=e.getX();
	    		 currentY=e.getY();
	    		 
	    		 if(command.isDraw){
	    			 command.checkReleased();
	    		 }
	    		 
	    		 if(skillMenu.isDraw){
	    			 skillMenu.checkReleased();
	    		 }
	    		 
	    		 if(drugMenu.isDraw){
	    			 drugMenu.checkReleased();
	    		 }
	    	 }
		});
	     
	     addMouseMotionListener(new MouseMotionAdapter() {
	    	 public void mouseMoved(MouseEvent ex) {
	    		 currentX=ex.getX();
	    		 currentY=ex.getY();
	    	
	    		 if(command.isDraw){
	    			 command.checkMoveIn();
	    		 }
	    		 
	    		 if(skillMenu.isDraw){
	    			 skillMenu.checkMoveIn();
	    		 }
	    		 
	    		 if(drugMenu.isDraw){
	    			 drugMenu.checkMoveIn();
	    		 }
	    		 
	    		 enemySlector.checkMoveIn(currentX, currentY);
	    	 }
	    	 
	    	 public void mouseDragged(MouseEvent ex) {
	    		 currentX=ex.getX();
	    		 currentY=ex.getY();
	    		 
	    		 if(command.isDraw){
	    			 command.checkMoveIn();
	    		 }
	    		 
	    		 if(skillMenu.isDraw){
	    			 skillMenu.checkMoveIn();
	    		 }
	    		 
	    		 if(drugMenu.isDraw){
	    			 drugMenu.checkMoveIn();
	    		 }
	    	 }
		});
	    	 
	     
	}
	
	public void paint(Graphics g){
		bufferedGraphics.drawImage(background, 0, 0, this);
		backgroundAnimation.drawBackAnimation(bufferedGraphics);
		stateBlank.drawStateBlank(bufferedGraphics);
		for(AngryBar angryBar:angryBars){
			angryBar.drawAngryBar(bufferedGraphics);
		}
		if(command!=null){
		command.drawCommand(bufferedGraphics);
		}
		
		drugMenu.drawDrugMenu(bufferedGraphics);
		for(Hero hero:heroes){
			if(hero!=null){
				hero.drawHero(bufferedGraphics);
			}
		}
		for(Hero hero:heroes){
			if(hero!=null){
				hero.getDeadAnimation().drawDeadAniamtion(bufferedGraphics);
			}
		}
		for(Hero hero:heroes){
			if(hero!=null){
				hero.getVictoryAnimation().drawVictoryAnimation(bufferedGraphics);
			}
		}
	    for(Enemy enemy:enemies){
	    	if(enemy!=null){
	    	enemy.drawEnemy(bufferedGraphics);
	    	}
	    }
	    if(pet!=null){
	    pet.drawPet(bufferedGraphics);
	    }
	    if(progressBar!=null){
	    progressBar.drawProgressBar(bufferedGraphics);
	    }
	    skillMenu.drawSkillMenu(bufferedGraphics);
	    for(Enemy enemy:enemies){
	    	if(enemy.beAttackedAnimation!=null){
	    	enemy.beAttackedAnimation.drawAnimation(bufferedGraphics);
	    	}
	    }
	    for(Hero hero:heroes){
	    	if(hero.getBeAttackedAnimation()!=null){
	    		hero.getBeAttackedAnimation().drawAnimation(bufferedGraphics);
	    	}
	    }
	    skillAnimation.drawAnimation(bufferedGraphics);
	    for(Hero hero:heroes){
			   hero.getBattleState().drawState(bufferedGraphics);
		   }
	    for(Enemy enemy:enemies){
	    	enemy.battleState.drawState(bufferedGraphics);
	    }
	    for(HurtValue hurtValue:hurtValues){
	    hurtValue.drawHurtValue(bufferedGraphics);
	    }
	    instruct.drawInstruct(bufferedGraphics);
	   
	    reminder.drawReminder(bufferedGraphics);
	    victoryReminder.drawVictoryReminder(bufferedGraphics);
	    mouse.drawMouse(bufferedGraphics);
	    if(gameOver!=null){
	    	gameOver.drawGameOver(bufferedGraphics);
	    }
	    if(startAnimation!=null){
	    startAnimation.drawStartAnimation(bufferedGraphics);
	    }
	    g.drawImage(bufferedPic,0,0,this);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			if(mouse!=null){
				mouse.update();
			}
			if(backgroundAnimation!=null){
				backgroundAnimation.update();
			}
		    for(Hero hero:heroes){
		    	if(hero!=null){
		    		hero.doAction();
		    	}
		    }
		  for(Hero hero:heroes){
			  if(hero!=null){
				  hero.getVictoryAnimation().update();
			  }
		  }
		  for(Hero hero:heroes){
			  if(hero!=null){
				  hero.getDeadAnimation().update();
			  }
		  }
		 for(Enemy enemy:enemies){
				 if(enemy!=null){
			    	enemy.doAction();
				 }
			    }
			if(progressBar!=null){
				progressBar.updateProgress();
			}
			skillAnimation.update();
			for(Enemy enemy:enemies){
			    if(enemy.beAttackedAnimation!=null){
			    	enemy.beAttackedAnimation.update();
			    }
			  }
			for(Hero hero:heroes){
				if(hero.getBeAttackedAnimation()!=null){
					hero.getBeAttackedAnimation().update();
				}
			}
			if(pet!=null){
				pet.update();
			}
			for(HurtValue hurtValue:hurtValues){
			if(hurtValue!=null){
				hurtValue.update();
			}
			}
			if(instruct!=null){
				instruct.update();
				
			}
			if(reminder!=null){
				reminder.update();
			}	
			for(Enemy enemy:enemies){
				if(enemy!=null){
		    	enemy.battleState.check();
				}
		    }
			if(launchAttack!=null){
				launchAttack.check();
			}
			
			if(stateBlank!=null){
				stateBlank.update();
			}
			if(angryBars.size()!=0){
			for(AngryBar angryBar:angryBars){
				if(angryBar!=null){
					angryBar.update();
				}
			  }
			}
			if(victoryReminder!=null){
				victoryReminder.update();
			}
			for(Hero hero:heroes){
				if(hero!=null){
				hero.getBattleState().check();
				}
			}
			if(gameOver!=null){
				gameOver.update();
			}
			if(startAnimation!=null){
				startAnimation.update();
			}
			repaint();
		}
	}
}
