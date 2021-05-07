/**
 * 
 */
package com.practice.IPLDashboardApplication.controller;

import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.practice.IPLDashboardApplication.model.Match;
import com.practice.IPLDashboardApplication.repository.MatchRepository;

/**
 * @author Neeraj
 *
 */
@RestController
public class MatchController {

	private MatchRepository matchRepository;
	
	public MatchController(MatchRepository matchRepository) {
		this.matchRepository = matchRepository;
	}


	@GetMapping("/team/{teamName}/matches")
	public List<Match> getMatchesFromTeam(@PathVariable String teamName, @RequestParam int year){
		Date startDate = new Date(year-1900, 1, 1);
		Date endDate = new Date(year+1-1900, 1, 1);
		return this.matchRepository.getMatchesByTeamBetweenDates(teamName, startDate, endDate);
	}
	
}
