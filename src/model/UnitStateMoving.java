package model;

public class UnitStateMoving implements UnitState {

	private Unit unit;

	public UnitStateMoving(Unit unit) {
		this.unit = unit;
	}

	public void Do(Context context) {
		if (unit instanceof UnitArcher) {
			if (context.getEnemyBase().isClose(unit, 40)) {
				unit.ChangeState(new UnitStateFighting(unit));
				return;
			}
			if (Math.abs(context.getClosetEnemyPosition() - unit.getPosition()) < 40) {
				unit.ChangeState(new UnitStateFighting(unit));
				return;
			}
			if (context.getEnemyBase().isClose(unit, 200)) {
				unit.ChangeState(new UnitStateShooting(unit));
				return;
			}
			if (Math.abs(context.getClosetEnemyPosition() - unit.getPosition()) < 200) {
				unit.ChangeState(new UnitStateShooting(unit));
				return;
			}
			unit.Move();
			return;
		}

		if (context.getEnemyBase().isClose(unit, 40)) {
			unit.ChangeState(new UnitStateFighting(unit));
			return;
		}
		if (Math.abs(context.getClosetEnemyPosition() - unit.getPosition()) < 40) {
			unit.ChangeState(new UnitStateFighting(unit));
			return;
		}
		unit.Move();
	}

}
