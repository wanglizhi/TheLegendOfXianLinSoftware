package shop;

public class Money {
	static int coins=10000;

	public static int getCoins() {
		return coins;
	}

	public static void setCoins(int coins) {
		Money.coins = coins;
	}
	//�˷���Ϊ���ӽ�Ǯ
	public static void addCoins(int addCoins){
		coins=coins+addCoins;
	}
	//�˷���Ϊ���ٽ�Ǯ
	public static void reduceCoins(int reduceCoins){
		coins=coins-reduceCoins;
	}
}
