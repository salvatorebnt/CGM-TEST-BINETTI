# CGM Visits Management
This application will have to manage basic patient data (name, surname, date of birth and social security number). 
For each patient should be possible to record one o more visit(s). 
Each visit has to contain:
the date and time of the visit, 
the type of the visit (either at home or at the doctor office), 
the reason of the visit (first visit, recurring visit, urgent) 
and the family history (a free text section).

The application workflow has to:
*	select a patient
*	create a visit for the patient
*	view/update a visit
*	all data have to be persisted

### Patients REST API
* 	POST	localhost:8080/management/api/patients
*	PUT		localhost:8080/management/api/patients
*	DELETE	localhost:8080/management/api/patients/{:id}
*	GET		localhost:8080/management/api/patients
*	GET		localhost:8080/management/api/patients/{:id}

### Visits REST API
* 	POST	localhost:8080/management/api/visits
*	PUT		localhost:8080/management/api/visits
*	DELETE	localhost:8080/management/api/visits/{:id}
*	GET		localhost:8080/management/api/visits
*	GET		localhost:8080/management/api/visits/{:id}
*	GET		localhost:8080/management/api/visits/patient/{:id}


### Swagger ui
* http://localhost:8080/management/swagger-ui/


### H2 DB Console
http://localhost:8080/management/h2-console/
* jdbc url : jdbc:h2:file:./data/demo
* username : sa
* password : 

### H2 DB Console
http://localhost:8080/management/h2-console/
* jdbc url : jdbc:h2:file:./data/demo
* username : sa
* password : 

### Minimal User Interface
* http://localhost:8080/management/
