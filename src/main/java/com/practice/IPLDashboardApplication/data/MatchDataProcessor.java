/**
 * 
 */
package com.practice.IPLDashboardApplication.data;

import java.text.SimpleDateFormat;

/**
 * @author Neeraj
 *
 */
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.practice.IPLDashboardApplication.model.Match;

public class MatchDataProcessor implements ItemProcessor<MatchInput, Match> {

	private static final Logger log = LoggerFactory.getLogger(MatchDataProcessor.class);

	@Override
	public Match process(final MatchInput matchInput) throws Exception {
		log.info("Converting ( MatchInput ) into ( Match )");
		Match match = new Match();
		match.setId(Long.parseLong(matchInput.getId()));
		match.setCity(matchInput.getCity());
		match.setDate(new SimpleDateFormat("MM/dd/yyyy").parse(matchInput.getDate()));
		match.setMatchWinner(matchInput.getWinner());
		match.setPlayerOfMatch(matchInput.getPlayer_of_match());
		match.setVenue(matchInput.getVenue());
		
		// Set Team 1 and Team 2 depened on who bat first
		String firstInningsTeam, secondInnigsTeam;
		if("bat".equals(matchInput.getToss_decision())) {
			firstInningsTeam = matchInput.getToss_winner();
			secondInnigsTeam = matchInput.getToss_winner().equals(matchInput.getTeam1())
					?matchInput.getTeam2():matchInput.getTeam1();
		}
		else {
			secondInnigsTeam = matchInput.getToss_winner();
			firstInningsTeam = matchInput.getToss_winner().equals(matchInput.getTeam1())
					?matchInput.getTeam2():matchInput.getTeam1();
		}
		
		match.setTeam1(firstInningsTeam);
		match.setTeam2(secondInnigsTeam);
		
		match.setTossDecision(matchInput.getToss_decision());
		match.setTossWinner(matchInput.getToss_winner());
		match.setResult(matchInput.getResult());
		match.setResultMargin(matchInput.getResult_margin());
		match.setUmpire1(matchInput.getUmpire1());
		match.setUmpire2(matchInput.getUmpire2());
		return match;
	}

}