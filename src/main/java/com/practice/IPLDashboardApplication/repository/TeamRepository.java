/**
 * 
 */
package com.practice.IPLDashboardApplication.repository;

import org.springframework.data.repository.CrudRepository;

import com.practice.IPLDashboardApplication.model.Team;

/**
 * @author Neeraj
 *
 */
public interface TeamRepository extends CrudRepository<Team, Long> {
	
	Team findByTeamName(String teamName);
	
}
