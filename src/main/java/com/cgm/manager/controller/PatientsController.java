package com.cgm.manager.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cgm.manager.constants.PatientsRestConstants;
import com.cgm.manager.constants.RestErrors;
import com.cgm.manager.constants.SwaggerConstants;
import com.cgm.manager.exception.CgmException;
import com.cgm.manager.model.patient.Patient;
import com.cgm.manager.model.patient.PatientData;
import com.cgm.manager.service.PatientsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
/**
* Rest Controller serving patient services 
* <p>
* Accepted paths:
* 	POST	/management/api/patients
*	PUT		/management/api/patients
*	DELETE	/management/api/patients/{:id}
*	GET		/management/api/patients
*	GET		/management/api/patients/{:id}
* </p>
* @author salvatore.binetti
*/
@Api(tags = { SwaggerConstants.TAG_PATIENTS })
@RestController
@RequestMapping(value = PatientsRestConstants.PATIENTS_APIS)
public class PatientsController {
	Logger logger = LoggerFactory.getLogger(PatientsController.class);
	
	/**
	 * Service class serving patient services
	 */
	@Autowired
	PatientsService patientService;
	
	/**
	 * POST: Create a patient
	 * @param patientData:PatientData json
	 * @return ResponseEntity<Patient>
	 * @throws CgmException
	 * @author salvatore.binetti
	 */
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Create patient", notes = "This service creates a new patient")
	public ResponseEntity<Patient> insert(@Valid @RequestBody PatientData patientData) throws CgmException {
        HttpStatus status = HttpStatus.OK;
        Patient saved = patientService.insert(patientData);
        return new ResponseEntity<>(saved, status);
    }
	
	/**
	 * PUT: Update a patient
	 * @param patient:Patient json
	 * @return ResponseEntity<Patient>
	 * @throws NoSuchElementException, CgmException
	 * @author salvatore.binettill
	 */
	@RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Update patient", notes = "This service updates patients data")
	public ResponseEntity<Patient> update(@Valid @RequestBody Patient patient) throws NoSuchElementException, CgmException {
		HttpStatus status = HttpStatus.OK;
        Patient updated = patientService.update(patient);
        return new ResponseEntity<>(updated, status);
    }
	
	/**
	 * DELETE: Delete a patient
	 * @param id:long patient id
	 * @return
	 * @throws EmptyResultDataAccessException, CgmException
	 * @author salvatore.binetti
	 */
	@RequestMapping(method = RequestMethod.DELETE, path = PatientsRestConstants.ID_PATH_VARIABLE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Delete patient", notes = "This service delete a existing patient")
	public ResponseEntity<Patient> delete(@PathVariable(name = PatientsRestConstants.ID_PARAM, required = true) Long id) throws EmptyResultDataAccessException, CgmException {
        HttpStatus status = HttpStatus.OK;
        patientService.delete(id);
        return new ResponseEntity<>(null, status);
    }
    
	/**
	 * GET: Find all patients
	 * @return patients: List
	 * @author salvatore.binetti
	 */
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Find all patients", notes = "This service return all registered patients")
	public ResponseEntity<List<Patient>> findAll() throws CgmException {
		HttpStatus status = HttpStatus.OK;
		List<Patient> list = patientService.findAll();
		return new ResponseEntity<>(list, status);
	}
	
	/**
	 * GET: Find a patient
	 * @param id:long patient id
	 * @return ResponseEntity<Patient>
	 * @throws NoSuchElementException, CgmException
	 * @author salvatore.binetti
	 */
	@RequestMapping(method = RequestMethod.GET, path = PatientsRestConstants.ID_PATH_VARIABLE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Find patient", notes = "This service return a patient by identification")
	public ResponseEntity<Patient> findById(@PathVariable(name = PatientsRestConstants.ID_PARAM, required = true) Long id) throws NoSuchElementException, CgmException {
        HttpStatus status = HttpStatus.OK;
        Patient found = patientService.findById(id);
        return new ResponseEntity<>(found, status);
	}
	
	
	
	
	//Gestione eccezioni
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
    	logger.error(ex.getMessage());
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({NoSuchElementException.class, EmptyResultDataAccessException.class})
    public Map<String, String> handleValidationExceptions(Exception ex) {
    	logger.error(ex.getMessage());
        Map<String, String> errors = new HashMap<>();
        errors.put("ErrorCode", RestErrors.RESOURCE_NOT_FOUND);
        errors.put("Message", ex.getMessage());
        return errors;
    }
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CgmException.class)
    public Map<String, String> handleValidationExceptions(CgmException ex) {
    	logger.error(ex.getMessage());
        Map<String, String> errors = new HashMap<>();
        errors.put("ErrorCode", RestErrors.GENERIC_ERROR);
        errors.put("Message", ex.getMessage());
        return errors;
    }

}