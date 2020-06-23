package model;

public class UnitStateShooting implements UnitState {

	private Unit unit;

	public UnitStateShooting(Unit unit) {
		this.unit = unit;
	}

	public void Do(Context context) {
		if (context.getEnemyBase().isClose(unit, 40)) {
			unit.ChangeState(new UnitStateFighting(unit));
			return;
		}
		if (context.getClosetEnemyPosition() < 40) {
			unit.ChangeState(new UnitStateFighting(unit));
			return;
		}
		if (context.getEnemyBase().isClose(unit, 200)) {
			context.getEnemyBase().TakeDamage(unit.getDamage());
			return;
		}
		if (context.getClosetEnemyPosition() < 200) {
			context.getClosestEnemy().TakeDamage(unit.getDamage());
			return;
		}
		unit.ChangeState(new UnitStateMoving(unit));
	}
}
