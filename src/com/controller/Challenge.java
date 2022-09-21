package com.controller;

import java.util.Scanner;

import com.exceptions.GameException;

public class Challenge {

	public static void main(String[] args) {
		
		Scanner readConsole = new Scanner(System.in);
		
		try {
			System.out.println(Game.readInput(readConsole.nextLine()));
		} catch (GameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
