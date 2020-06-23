package model;

// Контекст для юнитов. Т.е. информация об окружении, чтобы они знали, как себя вести
public class Context {

	private int closetEnemy;

	public Context(int distance) {
		closetEnemy = distance;
	}

	public int getClosetEnemy() {
		return closetEnemy;
	}

}
