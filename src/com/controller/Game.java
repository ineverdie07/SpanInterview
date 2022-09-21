package com.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import com.exceptions.GameException;
import com.model.PlayMatch;
import com.model.Team;
import com.model.TeamComparator;

public class Game {
	
	public static String readInput(String input) throws GameException{
		if(input == null || input.isEmpty()) {
			throw new GameException("No values in the message");
		}
		String[] games = input.split("\n");
		ArrayList<PlayMatch> matches = new ArrayList<>();
		for(String s:games) {
			String[] finalScore = s.split(",");
			if(finalScore.length != 2) {
				throw new GameException("Exception trying to parse final score [" + s + "]");
			}
			matches.add(parseStringArrayToPlayMatch(s.split(",")));
		}
		return getGameResult(matches);
	}
	
	private static String getGameResult(ArrayList<PlayMatch> matches) {
		Map<String, Integer> teamMap = new HashMap<>();
		for(PlayMatch pM:matches) {
			calculatePoints(pM);
			if(teamMap.containsKey(pM.getTeam1().getTeamName())) {
				teamMap.put(pM.getTeam1().getTeamName(), 
						teamMap.get(pM.getTeam1().getTeamName())
						+ pM.getTeam1().getPoints());
			} else {
				teamMap.put(pM.getTeam1().getTeamName(),pM.getTeam1().getPoints());
			}
			if(teamMap.containsKey(pM.getTeam2().getTeamName())) {
				teamMap.put(pM.getTeam2().getTeamName(), 
						teamMap.get(pM.getTeam2().getTeamName())
						+ pM.getTeam2().getPoints());
			} else {
				teamMap.put(pM.getTeam2().getTeamName(),pM.getTeam2().getPoints());
			}
		}
		return finalResultString(teamMap);
	}
	
	private static void calculatePoints(PlayMatch playMatch) {
		if(playMatch.getTeam1().getScore()>playMatch.getTeam2().getScore()) {
			playMatch.getTeam1().setPoints(3);
		} else if(playMatch.getTeam1().getScore()<playMatch.getTeam2().getScore()) {
			playMatch.getTeam2().setPoints(3);
		} else {
			playMatch.getTeam1().setPoints(1);
			playMatch.getTeam2().setPoints(1);
		}
	}
	
	private static PlayMatch parseStringArrayToPlayMatch(String[] stringArray) throws IllegalArgumentException {
		return new PlayMatch(
				getTeamFromArray(stringArray[0].trim().split(" ")), 
				getTeamFromArray(stringArray[1].trim().split(" ")));
	}
	
	private static String finalResultString(Map<String, Integer> teamMap) {
		ArrayList<Team> teams = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		
		teamMap.forEach((k,v) -> teams.add(new Team(k,0,v)));
		Collections.sort(teams,new TeamComparator());
		for(int count = 1; count <= teams.size(); count++){
			sb.append(count + ". " + teams.get(count-1).getTeamName() + ", " + teams.get(count-1).getPoints() + " pt"+(teams.get(count-1).getPoints()!=1?"s":""));
			if(count<teams.size()) {
				sb.append("\n");
			}
		}
		
		return sb.toString();
	}
	
	private static Team getTeamFromArray(String[] array) throws IllegalArgumentException {
		if(array.length < 2 ) {
			throw new IllegalArgumentException("The final score should be compose by team name and final score");
		}
		
		StringBuilder teamNameBuilder = new StringBuilder();
		teamNameBuilder.append(array[0]);
		for(int counter = 1;counter<array.length-1;counter++) {
			teamNameBuilder.append(" " + array[counter]);
		}
		
		return new Team(teamNameBuilder.toString(), array[array.length-1]);
	}

}
