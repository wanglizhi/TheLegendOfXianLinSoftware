package start;

public class StartTimer {
	int timeLeft;
	boolean isCompleted;
	boolean isStarted;
	
	public StartTimer(){
		timeLeft=0;
		isCompleted=false;
		isStarted=false;
	}
	
	public void initial(){
		timeLeft=0;
		isCompleted=false;
		isStarted=false;
	}
	
	public void start(int interval){
		isStarted=true;
		this.timeLeft=interval;
	}
	
	public void update(){
		if(isStarted)
			timeLeft--;
		if(timeLeft==0&&isStarted)
			isCompleted=true;
	}
	
	public boolean stop(){
		return isCompleted;
	}

	public int getTimeLeft() {
		return timeLeft;
	}
	
}
