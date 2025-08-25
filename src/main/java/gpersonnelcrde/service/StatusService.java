package gpersonnelcrde.service;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gpersonnelcrde.domain.dto.StatusDto;
import gpersonnelcrde.domain.entities.Status;
import gpersonnelcrde.repository.StatusRepository;

@Service
@Transactional
public class StatusService {
	private StatusRepository statusRepository;

	public StatusService(StatusRepository statusRepository){
		this.statusRepository = statusRepository;
	}

	public List<StatusDto> getAllStatus() {
		return statusRepository.findAll().stream()
				.map((Status status) -> {
					StatusDto sDto = new StatusDto();
					sDto.setStatusCode(status.getStatusCode());
					sDto.setStatus(status.getStatus());
					return sDto;
				})
				.toList();
	}

	public Optional<StatusDto> getStatusByCode(String statusCode){
		if (StringUtils.isBlank(statusCode)) {
			return Optional.empty();
		}
		var optionalStatus = statusRepository.findByStatusCode(statusCode);

		return statusMapper(optionalStatus);

	}

	private Optional<StatusDto> statusMapper (Optional<Status> optStatus){
		if (optStatus.isPresent()){
			var status = optStatus.get(); 
			var sDto = new StatusDto();
			sDto.setStatusCode(status.getStatusCode());
			sDto.setStatus(status.getStatus());

			return Optional.of(sDto);
		}

		return Optional.empty();
	}
}
