package com.shephertz.app42.sdk.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

import com.shephertz.app42.paas.sdk.java.ServiceAPI;
import com.shephertz.app42.paas.sdk.java.game.Game;
import com.shephertz.app42.paas.sdk.java.game.GameService;
import com.shephertz.app42.paas.sdk.java.game.ScoreBoardService;

public class UserServiceSample {

	/**
	 * Main Method To Create App42 User on Cloud
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		createUser();
	}

	/**
	 * Test Method for creating the User in App42 Cloud.
	 */
	public static void createUser() {
		ServiceAPI sp = new ServiceAPI(
				"30de7e0dcf044cb4c5b46b606868a619a7057e727312392a0f346aafb3036e0f",
				"80375913972b990a07495686c40ebe012672aa9821d1999e19b9459a3f133191");
		GameService gameService = sp.buildGameService();
		ScoreBoardService scoreBoardService = sp.buildScoreBoardService();
		String gameName = "Pingo" + new Date().getTime();
		Game gameObj = gameService.createGame(gameName, "gameDescription01");
		System.out.println("testing" + gameObj);
		Game saveUserScoreObj = scoreBoardService.saveUserScore(gameName,
				"Nick", new BigDecimal(3500));
		System.out.println("saveUserScoreObj---------- " + saveUserScoreObj);
		Game saveUserScoreObj1 = scoreBoardService.saveUserScore(gameName,
				"Ajay", new BigDecimal(35001));
		System.out.println("saveUserScoreObj1---------- " + saveUserScoreObj1);
		ArrayList<String> userList = new ArrayList<String>();
		userList.add("Ajay");
		for (int i = 0; i < 850; i++) {
			userList.add("sach" + i);
		}
		Game getTopRanker = scoreBoardService.getTopRankingsByGroup(gameName,
				userList);
		System.out.println("getTopRanker------------ " + getTopRanker);

	}

}
