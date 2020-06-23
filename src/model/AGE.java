package model;

public enum AGE {
	FIRST,
	SECOND,
	THIRD;

	public int getNumber() {
		// Да, я не стал думать что-то умнее
		switch (this) {
			case FIRST:
				return 1;
			case SECOND:
				return 2;
			case THIRD:
				return 3;
		}
		return 1;
	}
}
