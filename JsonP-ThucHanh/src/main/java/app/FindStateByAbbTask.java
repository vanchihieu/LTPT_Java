package app;

import entity.State;
import util.StateUtil;

public class FindStateByAbbTask {
	public static void main(String[] args) {
		State state = StateUtil.findByAb("ak");
		if (state == null)
			System.out.println("Not found");
		else
			System.out.println(state);
	}
}
