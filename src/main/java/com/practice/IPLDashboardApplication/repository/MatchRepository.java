/**
 * 
 */
package com.practice.IPLDashboardApplication.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.practice.IPLDashboardApplication.model.Match;

/**
 * @author Neeraj
 *
 */
public interface MatchRepository extends CrudRepository<Match, Long>{

	@Query("Select m from Match m where (m.team1 = :teamName or m.team2= :teamName) and m.date between :dateStart and :dateEnd order by date desc")
	List<Match> getMatchesByTeamBetweenDates(
			@Param("teamName") String teamName, 
			@Param("dateStart") Date startDate, 
			@Param("dateEnd") Date endDate
			);
	/*
	 * List<Match> getByTeam1AndDateBetweenOrTeam2AndDateBetweenOrderByDateDesc(
	 * String teamName1, Date startDate, Date endDate, String teamName2, Date
	 * startDate2, Date endDate2 );
	 */
	List<Match> getByTeam1OrTeam2OrderByDateDesc(String teamName1, String teamName2, Pageable pageable);
	
	default List<Match> findLatestMatchesByTeam(String teamName, int count){
		return getByTeam1OrTeam2OrderByDateDesc(teamName, teamName, PageRequest.of(0, 4));
	}
	
	
}
