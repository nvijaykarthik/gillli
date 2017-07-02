package in.expedite.project.service;

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
}
