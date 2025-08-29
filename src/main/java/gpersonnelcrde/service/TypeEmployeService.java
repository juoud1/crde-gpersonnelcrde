package gpersonnelcrde.service;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gpersonnelcrde.domain.dto.TypeEmployeDto;
import gpersonnelcrde.domain.entities.TypeEmploye;
import gpersonnelcrde.repository.TypeEmployeRepository;

@Service
@Transactional
public class TypeEmployeService {
	private final TypeEmployeRepository typeEmployeRepository;

	public TypeEmployeService(TypeEmployeRepository typeEmployeRepository) {
		this.typeEmployeRepository = typeEmployeRepository;
	}

	public List<TypeEmployeDto> getAllTypeEmp(){
		return typeEmployeRepository.findAll().stream()
				.map((TypeEmploye tEmp) -> {
					TypeEmployeDto tDto = new TypeEmployeDto();
					tDto.setTypeEmpCode(tEmp.getTypeEmpCode());
					tDto.setTypeEmp(tEmp.getTypeEmp());
					return tDto;
				})
				.toList();
	}

	public Optional<TypeEmployeDto> getTypeEmpByCode (String typeEmpCode){
		if (StringUtils.isBlank(typeEmpCode)) {
			return Optional.empty();
		}

		var optionalTypeEmp = typeEmployeRepository.findByTypeEmpCode(typeEmpCode);

		return typeEmpMapper(optionalTypeEmp);
	}

	private Optional<TypeEmployeDto> typeEmpMapper(Optional<TypeEmploye> optTypeEmp){
		if (optTypeEmp.isPresent()){
			var typeEmp = optTypeEmp.get();
			var tDto = new TypeEmployeDto();
			tDto.setTypeEmpCode(typeEmp.getTypeEmpCode());
			tDto.setTypeEmp(typeEmp.getTypeEmp());

			return Optional.of(tDto);
		}

		return Optional.empty();
	}
}
