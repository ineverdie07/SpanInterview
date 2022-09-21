package com.model;

import java.util.Objects;

/** Represent a team in a game **/
public class Team {
	
	/* Save the team name*/
	private String teamName;
	/* Save the team score*/
	private int score;
	/* Save the points winned by the team */
	private int points;
	
	public Team() {}
	
	public Team(String teamName, int score) {
		super();
		this.teamName = teamName;
		this.score = score;
	}
	
	public Team(String teamName, int score, int points) {
		super();
		this.teamName = teamName;
		this.score = score;
		this.points = points;
	}
	
	public Team(String teamName, String score) throws IllegalArgumentException{
		super();
		this.teamName = teamName;
		try {
			this.score = Integer.valueOf(score);
		}catch(Exception e) {
			throw new IllegalArgumentException("Score value is not numeric");
		}
	}
	
	public String getTeamName() {
		return teamName;
	}

	public int getScore() {
		return score;
	}
	
	public int getPoints() {
		return points;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	public void setPoints(int points) {
		this.points = points;
	}

	@Override
	public int hashCode() {
		return Objects.hash(teamName);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Team other = (Team) obj;
		return Objects.equals(teamName, other.teamName);
	}

}
