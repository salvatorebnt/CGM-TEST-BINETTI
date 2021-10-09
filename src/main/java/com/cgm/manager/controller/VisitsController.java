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

import com.cgm.manager.constants.RestErrors;
import com.cgm.manager.constants.SwaggerConstants;
import com.cgm.manager.constants.VisitsRestConstants;
import com.cgm.manager.exception.CgmException;
import com.cgm.manager.model.visit.Visit;
import com.cgm.manager.model.visit.VisitData;
import com.cgm.manager.service.VisitsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = { SwaggerConstants.TAG_VISITS })
@RestController
@RequestMapping(value = VisitsRestConstants.VISITS_APIS)
public class VisitsController {
	Logger logger = LoggerFactory.getLogger(VisitsController.class);
	
	@Autowired
	VisitsService visitsService;
	
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Create a visit", notes = "This service creates a new visit")
	public ResponseEntity<Visit> insert(@Valid @RequestBody VisitData visitData) throws CgmException {
        HttpStatus status = HttpStatus.OK;
        Visit saved = visitsService.insert(visitData);
        return new ResponseEntity<>(saved, status);
    }
	
	
	@RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Update visit", notes = "This service updates visit data")
	public ResponseEntity<Visit> update(@Valid @RequestBody Visit visit) throws NoSuchElementException, CgmException {
		HttpStatus status = HttpStatus.OK;
		Visit updated = visitsService.update(visit);
        return new ResponseEntity<>(updated, status);
    }
	
	@RequestMapping(method = RequestMethod.DELETE, path = VisitsRestConstants.ID_PATH_VARIABLE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Delete visit", notes = "This service delete a existing visit")
	public ResponseEntity<Visit> delete(@PathVariable(name = VisitsRestConstants.ID_PARAM, required = true) Long id) throws EmptyResultDataAccessException, CgmException {
        HttpStatus status = HttpStatus.OK;
        visitsService.delete(id);
        return new ResponseEntity<>(null, status);
    }
    
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Find all visits", notes = "This service return all registered visits")
	public ResponseEntity<List<Visit>> findAll() throws CgmException {
		HttpStatus status = HttpStatus.OK;
		List<Visit> list = visitsService.findAll();
		return new ResponseEntity<>(list, status);
	}
	
	@RequestMapping(method = RequestMethod.GET, path = VisitsRestConstants.ID_PATH_VARIABLE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Find visit", notes = "This service return a visit by identification")
	public ResponseEntity<Visit> findById(@PathVariable(name = VisitsRestConstants.ID_PARAM, required = true) Long id) throws NoSuchElementException, CgmException {
        HttpStatus status = HttpStatus.OK;
        Visit found = visitsService.findById(id);
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