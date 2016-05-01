package main;

public class Game {

	public static GameLauncher game;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		setGame(new GameLauncher());
	}

	public static GameLauncher getGame() {
		return game;
	}

	public static void setGame(GameLauncher game) {
		Game.game = game;
	}

}
