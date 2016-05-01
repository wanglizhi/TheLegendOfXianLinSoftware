package shop;

public class Money {
	static int coins=10000;

	public static int getCoins() {
		return coins;
	}

	public static void setCoins(int coins) {
		Money.coins = coins;
	}
	//此方法为增加金钱
	public static void addCoins(int addCoins){
		coins=coins+addCoins;
	}
	//此方法为减少金钱
	public static void reduceCoins(int reduceCoins){
		coins=coins-reduceCoins;
	}
}
