package model;

public class UnitStateMoving implements UnitState {

	private Unit unit;

	public UnitStateMoving(Unit unit) {
		this.unit = unit;
	}

	public void Do(Context context) {
		unit.Move(unit.getSpeed());
	}

}
