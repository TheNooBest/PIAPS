package model;

// Контекст для юнитов. Т.е. информация об окружении, чтобы они знали, как себя вести
public class Context {

	private Unit closestEnemy;
	private int closetEnemyPosition;
	private Player enemyBase;

	public Context(Unit enemy, int distance, Player base) {
		closestEnemy = enemy;
		closetEnemyPosition = distance;
		enemyBase = base;
	}

	public Unit getClosestEnemy() {
		return closestEnemy;
	}

	public int getClosetEnemyPosition() {
		return closetEnemyPosition;
	}

	public Player getEnemyBase() {
		return enemyBase;
	}

}
