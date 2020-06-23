package model;

import java.util.concurrent.atomic.AtomicInteger;

public interface AbstractUnitFactory {
	Unit createUnitSword(AtomicInteger money);
	Unit createUnitArrow(AtomicInteger money);
	Unit createUnitPig(AtomicInteger money);
}
