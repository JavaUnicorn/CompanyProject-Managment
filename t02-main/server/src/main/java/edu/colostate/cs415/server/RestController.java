package edu.colostate.cs415.server;


import static spark.Spark.after;
import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.options;
import static spark.Spark.path;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.put;
import static spark.Spark.redirect;


import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;


import com.google.gson.Gson;


import edu.colostate.cs415.db.DBConnector;
import edu.colostate.cs415.dto.AssignmentDTO;
import edu.colostate.cs415.dto.ProjectDTO;
import edu.colostate.cs415.dto.QualificationDTO;
import edu.colostate.cs415.dto.WorkerDTO;
import edu.colostate.cs415.dto.ProjectDTO;
import edu.colostate.cs415.model.Company;
import edu.colostate.cs415.model.Qualification;
import edu.colostate.cs415.model.Project;
import edu.colostate.cs415.model.ProjectStatus;
import edu.colostate.cs415.model.Worker;


import spark.Request;
import spark.Response;
import spark.Spark;


public class RestController {


   private static Logger log = Logger.getLogger(RestController.class.getName());
   private static String OK = "OK";
   private static String KO = "KO";


   private DBConnector dbConnector;
   private Company company;
   private Gson gson;


   public RestController(int port, DBConnector dbConnector) {
       port(port);
       this.dbConnector = dbConnector;
       gson = new Gson();
   }


   public void start() {
       // Load data from DB
       company = dbConnector.loadCompanyData();


       // Redirect
       redirect.get("/", "/helloworld");


       // Logging
       after("/*", (req, res) -> logRequest(req, res));
       exception(Exception.class, (exc, req, res) -> handleException(exc, res));


       // Hello World
       get("/helloworld", (req, res) -> helloWorld());


       // API
       path("/api", () -> {
           // Enable CORS
           options("/*", (req, res) -> optionsCORS(req, res));
           after("/*", (req, res) -> enableCORS(res));


           // Qualifications
           path("/qualifications", () -> {
               get("", (req, res) -> getQualifications(), gson::toJson);
               get("/:description", (req, res) -> getQualification(req.params("description")), gson::toJson);
               post("/:description", (req, res) -> createQualification(req));
           });

		   //Workers
            path("/worker", () -> {
                get("", (req, res) -> getWorkers(), gson::toJson);
                get("/:name", (req, res) -> getWorkers(req.params("name")), gson::toJson);
                post("/:name", (req, res) -> createWorker(req));
            });

            //Project
            path("/project", () -> {
                get("", (req, res) -> getProjects(), gson::toJson);
                get("/:name", (req, res) -> getProjects(req.params("name")), gson::toJson);
                post("/:name", (req, res) -> createProject(req));
            });

           //Company (think /api -> then the path)
           // Assign Worker to Project
            put("/assign", (req, res) -> assign(req));
            // Unassign Worker from Project
            put("/unassign", (req, res) -> unassign(req));
            // Start Project
            put("/start", (req, res) -> startProject(req));
            // Finish Project
            put("/finish", (req, res) -> finish(req));
   });
   }




   public void stop() {
       Spark.stop();
   }


   private String helloWorld() {
       return "Hello World!";
   }


   private QualificationDTO[] getQualifications() {
       QualificationDTO[] companyQualificationsDTO = new QualificationDTO[company.getQualifications().size()];
       Set<Qualification> companyQualifications = company.getQualifications();
       int i = 0;
       for(Qualification q: companyQualifications){
           companyQualificationsDTO[i] = q.toDTO();
           i++;
       }
       return companyQualificationsDTO;
   }


   private QualificationDTO getQualification(String description) {
       for(Qualification q: company.getQualifications()){
           if(q.toString().equals(description)){
               return q.toDTO();
           }
       }
       throw new RuntimeException("Qualification does not exist in this company.");
   }


   private String createQualification(Request request) {
       QualificationDTO assignmentDTO = gson.fromJson(request.body(), QualificationDTO.class);
       if (request.params("description").equals(assignmentDTO.getDescription())) {
           company.createQualification(assignmentDTO.getDescription());
       } else
           throw new RuntimeException("Qualification descriptions do not match.");
       return OK;
   }


   private WorkerDTO[] getWorkers(){
       WorkerDTO[] companyWorkerDTO = new WorkerDTO[company.getEmployedWorkers().size()];
       // Set<Worker>
       int i = 0;
       for(Worker w: company.getEmployedWorkers()){
           companyWorkerDTO[i] = w.toDTO();
           i++;
       }
       return companyWorkerDTO;
   }


   private WorkerDTO getWorkers(String name){
       for(Worker w: company.getEmployedWorkers()){
           if(w.getName().equals(name)){
               return w.toDTO();
           }
       }
       throw new RuntimeException("This Worker is not an EmployedWorker at this Company.");
   }


   private String createWorker(Request request) {
       Set<Qualification> qualifications = new HashSet<Qualification>();
       WorkerDTO assignmentDTO = gson.fromJson(request.body(), WorkerDTO.class);
       for(String q: assignmentDTO.getQualifications()){
           qualifications.add(new Qualification(q));
       }
       if (request.params("name").equals(assignmentDTO.getName())) {
           company.createWorker(assignmentDTO.getName(), qualifications, assignmentDTO.getSalary());
       } else
           throw new RuntimeException("Your worker name is missing or your qualifications are incorrect, or your salary is 0 or below");
       return OK;
   }


   private ProjectDTO[] getProjects(){
       ProjectDTO[] companyProjectDTO = new ProjectDTO[company.getProjects().size()];
       // Set<Worker>
       int i = 0;
       for(Project p: company.getProjects()){
           companyProjectDTO[i] = p.toDTO();
           i++;
       }
       return companyProjectDTO;
   }


   private ProjectDTO getProjects(String name){
       for(Project p: company.getProjects()){
           if(p.getName().equals(name)){
               return p.toDTO();
           }
       }
       throw new RuntimeException("This Project is not a Project at this Company.");
   }


   private String createProject(Request request) {
       Set<Qualification> qualifications = new HashSet<Qualification>();
       ProjectDTO assignmentDTO = gson.fromJson(request.body(), ProjectDTO.class);
       for(String p: assignmentDTO.getQualifications()){
           qualifications.add(new Qualification(p));
       }
       if (request.params("name").equals(assignmentDTO.getName())) {
           company.createProject(assignmentDTO.getName(), qualifications, assignmentDTO.getSize());
       } else
           throw new RuntimeException("Your project name is missing or your qualifications are incorrect, or your size is 0 or below");
       return OK;
   }


   private String assign(Request request) {
    AssignmentDTO assignmentDTO = gson.fromJson(request.body(), AssignmentDTO.class);
   
    // Extract worker name and project name from the AssignmentDTO
    String workerName = assignmentDTO.getWorker();
    String projectName = assignmentDTO.getProject();
   
    for (Project project : company.getProjects()) {
        if (project.getName().equals(projectName)) {
            for (Worker worker : company.getEmployedWorkers()) {
                if (worker.getName().equals(workerName)) {
                    company.assign(worker, project);
                    return OK;
                }
            }
            throw new RuntimeException("Worker with name '" + workerName + "' not found.");
        }
    }
    throw new RuntimeException("Project with name '" + projectName + "' not found.");
}

private String unassign(Request request) {
    AssignmentDTO assignmentDTO = gson.fromJson(request.body(), AssignmentDTO.class);
   
    String workerName = assignmentDTO.getWorker();
    String projectName = assignmentDTO.getProject();
   
    for (Project project : company.getProjects()) {
        if (project.getName().equals(projectName)) {
            for (Worker worker : company.getEmployedWorkers()) {
                if (worker.getName().equals(workerName)) {
                    company.unassign(worker, project);
                    return "OK"; // Return "OK" after successful unassignment
                }
            }
            throw new RuntimeException("Worker with name '" + workerName + "' not found.");
        }
    }
    throw new RuntimeException("Project with name '" + projectName + "' not found.");
}

private String startProject(Request request) {
    // Deserialize the JSON body into a ProjectDTO object
    ProjectDTO projectDTO = gson.fromJson(request.body(), ProjectDTO.class);
   
    // Check if projectDTO or projectName is null
    if (projectDTO == null || projectDTO.getName() == null || projectDTO.getName().isEmpty()) {
        throw new IllegalArgumentException("Invalid project data provided.");
    }
   
    String projectName = projectDTO.getName();
   
    for (Project project : company.getProjects()) {
        if (project.getName().equals(projectName)) {
            // Start the project
            company.start(project);
            if(project.getStatus() == ProjectStatus.ACTIVE){
                return "OK";

            }
             // Return "OK" after successful start
        }
    }
   
    throw new RuntimeException("Project with name '" + projectName + "' not found.");
}

private String finish(Request request) {
    ProjectDTO projectDTO = gson.fromJson(request.body(), ProjectDTO.class);
   
    String projectName = projectDTO.getName();
   
    for (Project project : company.getProjects()) {
        if (project.getName().equals(projectName)) {
            // Finish the project
            company.finish(project);
            return "OK"; // Return "OK" after successful finish
        }
    }
   
    throw new RuntimeException("Project with name '" + projectName + "' not found.");
}




   // Logs every request received
    private void logRequest(Request request, Response response) {
    log.info(request.requestMethod() + " " + request.pathInfo() + "\nREQUEST:\n" + request.body() + "\nRESPONSE:\n" + response.body());}
  
  


   // Exception handling
   private void handleException(Exception exception, Response response) {
       StringWriter sw = new StringWriter();
       PrintWriter pw = new PrintWriter(sw);
       exception.printStackTrace();
       exception.printStackTrace(pw);
       log.severe(sw.toString());
       response.body(KO);
       response.status(500);
   }


   // Enable CORS
   private void enableCORS(Response response) {
       response.header("Access-Control-Allow-Origin", "*");
   }


   // Enable CORS
   private String optionsCORS(Request request, Response response) {
       String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
       if (accessControlRequestHeaders != null)
           response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);


       String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
       if (accessControlRequestMethod != null)
           response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
       return OK;
   }
}



