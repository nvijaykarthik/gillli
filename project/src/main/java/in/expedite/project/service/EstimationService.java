package in.expedite.project.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.expedite.project.entity.Estimates;
import in.expedite.project.repository.EstimatesRepository;

@Service
public class EstimationService {

	@Autowired
	private EstimatesRepository estimatesRepository;
	
	public Estimates saveEstimates(Estimates estimates, String username){
		return estimatesRepository.save(estimates);
	}
	
	public void deleteEstimates(Long id){
		estimatesRepository.delete(id);		
	}
	
	public List<Estimates> getEstimatesProjTeam(Long projId,Long teamId){
		return estimatesRepository.findByProjectIdAndTeamId(projId,teamId);
	}

	public List<Estimates> getEstimatesProj(Long projId) {
		return estimatesRepository.findByProjectId(projId);
	}

	
	public Map<Long, Integer> getConsEstimatesProj(Long projId) {
		List<Estimates> esti=estimatesRepository.findByProjectId(projId);
		Map<Long,Integer> consolidated=new HashMap<>();
			esti.forEach(estimate->{
				Long team_id= estimate.getTeamId();
				Integer estimatedValue=estimate.getEstimate();
				Integer availEstimate=consolidated.get(team_id);
				if(null!=availEstimate){
					availEstimate=availEstimate+estimatedValue;
					consolidated.put(team_id, availEstimate);
				}else{
					consolidated.put(team_id, estimatedValue);
				}
				
			});

		return consolidated;
	}
	
	public List<Estimates> getEstimatesTeam(Long teamId) {
		return estimatesRepository.findByTeamId(teamId);
	}
}