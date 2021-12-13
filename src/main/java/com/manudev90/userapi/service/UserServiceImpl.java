package com.manudev90.userapi.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.manudev90.userapi.exception.DuplicateFieldException;
import com.manudev90.userapi.exception.FormatPasswordInvalidException;
import com.manudev90.userapi.exception.UserNotFoundException;
import com.manudev90.userapi.model.dto.PhoneDTO;
import com.manudev90.userapi.model.dto.UserRequestDTO;
import com.manudev90.userapi.model.dto.UserResponseDTO;
import com.manudev90.userapi.model.entity.ParameterEntity;
import com.manudev90.userapi.model.entity.PhoneEntity;
import com.manudev90.userapi.model.entity.UserEntity;
import com.manudev90.userapi.repository.ParameterRepository;
import com.manudev90.userapi.repository.PhoneRepository;
import com.manudev90.userapi.repository.UserRepository;
import com.manudev90.userapi.util.DateUtil;
import com.manudev90.userapi.util.JsonUtil;
import com.manudev90.userapi.util.RegexUtil;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

	private static final long PARAMETER_REGEX_PASSWORD = 1;
	private static final String CAMPO_DUPLICADO = "Campo duplicado";
	private static final String USUARIO_NO_ENCOTRADO = "Usuario no encotrado";
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PhoneRepository phoneRepository;
	
	@Autowired
	ParameterRepository parameterRepository;
	
	@Autowired
    JwtService jwtService;
	
	@Transactional 
	@Override
	public UserResponseDTO save(UserRequestDTO userRequestDTO) {
		
		validRepeatedEmail(userRequestDTO.getEmail());
		validFormatPassword(userRequestDTO.getPassword());
		UserEntity userEntityRequest = convertUserRequestDTOToEntity(userRequestDTO);
		validRepeatedPhones(userEntityRequest.getPhones());
		UserEntity userEntityResponse = userRepository.saveAndFlush(userEntityRequest);
		String jwt = jwtService.generateToken(userRequestDTO.getEmail(), userRequestDTO.getPassword());
		userEntityResponse.setToken(jwt);
		userEntityResponse = userRepository.saveAndFlush(userEntityResponse);
		return converUserEntityToDTO(userEntityResponse);
		
	}

	@Override
	public UserResponseDTO findById(Long id) throws UserNotFoundException {
		UserEntity userEntityResponse = findUserEntity(id);
		return converUserEntityToDTO(userEntityResponse);

	}

	private UserEntity findUserEntity(Long id) throws UserNotFoundException {
		
		Optional<UserEntity> userEntityOptional = userRepository.findById(id);

		if (!userEntityOptional.isPresent()) {
			throw new UserNotFoundException(USUARIO_NO_ENCOTRADO);
		}
		
		UserEntity userEntityResponse = userRepository.save(userEntityOptional.get());
		return userEntityResponse;
	}

	@Override
	public void update(Long id, UserRequestDTO userRequestDTO) throws UserNotFoundException {
		
		validRepeatedEmail(userRequestDTO.getEmail());
		UserEntity userEntityFound = findUserEntity(id);
		userEntityFound.setName(userRequestDTO.getName());
		userEntityFound.setEmail(userRequestDTO.getEmail());
		userEntityFound.setPassword(userRequestDTO.getPassword());
		
		Collection<PhoneEntity> phonesEntity = new ArrayList<PhoneEntity>();
		
		userRequestDTO.getPhones().forEach(phoneRequestDTO -> {
			phonesEntity.add(converPhoneRequestDTOToEntity(phoneRequestDTO));
		});

		userEntityFound.setPhones(phonesEntity);
		
		validRepeatedPhones(userEntityFound.getPhones());
		
		userRepository.save(userEntityFound);

	}

	@Override
	public void delete(Long id) throws UserNotFoundException {
		UserEntity userEntityFound = findUserEntity(id);
		userRepository.delete(userEntityFound);

	}

	private void validRepeatedPhones(Collection<PhoneEntity> phones) {

		phones.forEach(phone -> {
			Example<PhoneEntity> phoneExample = Example.of(phone);
			Optional<PhoneEntity> phoneOptional = phoneRepository.findOne(phoneExample);
			if (phoneOptional.isPresent()) {
				throw new DuplicateFieldException(CAMPO_DUPLICADO + ":" + JsonUtil.objectToJson(phoneOptional.get()));
			}
		});

	}
	

	private void validFormatPassword(String password) {
		
	  Optional<ParameterEntity> parameteOptional =	parameterRepository.findById(PARAMETER_REGEX_PASSWORD);
	  
	   if (parameteOptional.isPresent()) {
		   ParameterEntity parameterEntity = parameteOptional.get();
		   RegexUtil.isValid(password, parameterEntity.getValue());
		   
		   if (!RegexUtil.isValid(password, parameterEntity.getValue())) {
				throw new FormatPasswordInvalidException("Formato de password no valido");
		}
		   
		
	}

	

	}

	private void validRepeatedEmail(String email) {
		
		UserEntity userEntity = new UserEntity();
		userEntity.setEmail(email);
		Example<UserEntity> userExample = Example.of(userEntity);
		Optional<UserEntity> userOptional = userRepository.findOne(userExample);
		if (userOptional.isPresent()) {
			throw new DuplicateFieldException(CAMPO_DUPLICADO + ":" + email);
		}

	}

	private UserEntity convertUserRequestDTOToEntity(UserRequestDTO userRequestDTO) {

		UserEntity userEntity = new UserEntity();
		Collection<PhoneEntity> phonesEntity = new ArrayList<PhoneEntity>();

		userEntity.setName(userRequestDTO.getName());
		userEntity.setEmail(userRequestDTO.getEmail());
		userEntity.setPassword(userRequestDTO.getPassword());
		userEntity.setIsActive(true);
		userEntity.setLastLogin(new Date());

		userRequestDTO.getPhones().forEach(phoneRequestDTO -> {
			phonesEntity.add(converPhoneRequestDTOToEntity(phoneRequestDTO));
		});

		userEntity.setPhones(phonesEntity);

		return userEntity;

	}

	private PhoneEntity converPhoneRequestDTOToEntity(PhoneDTO phoneRequestDTO) {

		PhoneEntity phoneEntity = new PhoneEntity();

		phoneEntity.setNumber(phoneRequestDTO.getNumber());
		phoneEntity.setCityCode(phoneRequestDTO.getCitycode());
		phoneEntity.setCountryCode(phoneRequestDTO.getContrycode());

		return phoneEntity;

	}

	private UserResponseDTO converUserEntityToDTO(UserEntity userEntity) {

		UserResponseDTO userResponseDTO = new UserResponseDTO();
		List<PhoneDTO> phonesDTO = new ArrayList<PhoneDTO>();

		userResponseDTO.setName(userEntity.getName());
		userResponseDTO.setEmail(userEntity.getEmail());
		userResponseDTO.setCreated(DateUtil.convertDateToString(userEntity.getCreated()));
		userResponseDTO.setId(userEntity.getId().toString());
		userResponseDTO.setLastLogin(DateUtil.convertDateToString(userEntity.getLastLogin()));
		userResponseDTO.setIsActive(userEntity.getIsActive());
		userResponseDTO.setToken(userEntity.getToken());

		userEntity.getPhones().forEach(phoneEntity -> {
			phonesDTO.add(converPhoneEntityToDTO(phoneEntity));
		});

		userResponseDTO.setPhones(phonesDTO);

		return userResponseDTO;

	}

	private PhoneDTO converPhoneEntityToDTO(PhoneEntity phoneEntity) {

		PhoneDTO phoneDTO = new PhoneDTO();

		phoneDTO.setCitycode(phoneEntity.getCityCode());
		phoneDTO.setContrycode(phoneEntity.getCountryCode());
		phoneDTO.setNumber(phoneEntity.getNumber());

		return phoneDTO;

	}

}
