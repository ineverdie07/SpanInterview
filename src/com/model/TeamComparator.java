package com.model;

import java.util.Comparator; 

public class TeamComparator implements Comparator<Team> {

	@Override
	public int compare(Team o1, Team o2) {
		return ((o2.getPoints()-o1.getPoints())*100)-o2.getTeamName().compareTo(o1.getTeamName());
	}

}
