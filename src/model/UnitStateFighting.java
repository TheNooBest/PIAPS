package model;

public class UnitStateFighting implements UnitState {

	private Unit unit;

	public UnitStateFighting(Unit unit) {
		this.unit = unit;
	}

	public void Do(Context context) {
		if (unit instanceof UnitArcher) {
			if (context.getEnemyBase().isClose(unit, 40)) {
				context.getEnemyBase().TakeDamage((int)(unit.getDamage() * 0.7));
				return;
			}
			if (context.getClosetEnemyPosition() < 40) {
				context.getClosestEnemy().TakeDamage((int)(unit.getDamage() * 0.7));
				return;
			}
			if (context.getEnemyBase().isClose(unit, 200)) {
				unit.ChangeState(new UnitStateShooting(unit));
				return;
			}
			if (context.getClosetEnemyPosition() < 200) {
				unit.ChangeState(new UnitStateShooting(unit));
				return;
			}
			unit.ChangeState(new UnitStateMoving(unit));
			return;
		}

		if (context.getEnemyBase().isClose(unit, 40)) {
			context.getEnemyBase().TakeDamage(unit.getDamage());
			return;
		}
		if (context.getClosetEnemyPosition() < 40) {
			context.getClosestEnemy().TakeDamage(unit.getDamage());
			return;
		}
		unit.ChangeState(new UnitStateMoving(unit));
	}

}
