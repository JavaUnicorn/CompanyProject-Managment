package edu.colostate.cs415.server;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.HashSet;
import java.util.Arrays;
import java.util.Set;

import org.apache.hc.client5.http.fluent.Request;
import org.apache.hc.core5.http.ContentType;

import edu.colostate.cs415.model.Company;
import edu.colostate.cs415.model.Qualification;
import edu.colostate.cs415.model.Worker;
import edu.colostate.cs415.model.Project;
import edu.colostate.cs415.model.ProjectSize;

import edu.colostate.cs415.db.DBConnector;
import edu.colostate.cs415.dto.AssignmentDTO;
import edu.colostate.cs415.dto.QualificationDTO;
import edu.colostate.cs415.dto.WorkerDTO;
import edu.colostate.cs415.dto.ProjectDTO;

import com.google.gson.Gson;


public class RestControllerTest {
    
    // this method would be useful if you want to use mockito rather than the generic DBConnector class
    @BeforeClass
    public static void initData(){
        DBConnector dbConnect = new DBConnector();
        RestController restController = new RestController(4567, dbConnect);  
        restController.start();
    }

    @Test
    public void testGetQualifications() throws IOException {
     Gson gson = new Gson();

        QualificationDTO[] quals = gson.fromJson(
            Request.get("http://localhost:4567/api/qualifications").execute().returnContent().asString(), QualificationDTO[].class
            );

        
        assertEquals(13,quals.length);
    }
    @Test 
    public void testGetQualificationExists() throws IOException {
        Gson gson = new Gson();
        String python = "Python";
        QualificationDTO qual = gson.fromJson(
            Request.get("http://localhost:4567/api/qualifications/"+python).execute().returnContent().asString(), 
            QualificationDTO.class);
        assertEquals(qual.getDescription(), python);
    }

    @Test (expected = Exception.class)
    public void testGetQualificationNotExists() throws IOException {
        Gson gson = new Gson();
        String jackwagon = "JackWagon";
        QualificationDTO qual = gson.fromJson(
            Request.get("http://localhost:4567/api/qualifications/"+jackwagon).execute().returnContent().asString(), 
            QualificationDTO.class);
    }

    @Test 
    public void testCreateQualification() throws IOException {
        Gson gson = new Gson();
        String dingleBerry = "dingleBerry";
        Qualification qual2 =  new Qualification(dingleBerry);
        String qual2String = gson.toJson(qual2.toDTO());
        
        String response= 
            Request.post(("http://localhost:4567/api/qualifications/"+dingleBerry).replace(" ", "%20")) 
            .bodyString(qual2String, ContentType.APPLICATION_JSON).execute().returnContent().asString();

        QualificationDTO qual = gson.fromJson(
            Request.get("http://localhost:4567/api/qualifications/"+dingleBerry).execute().returnContent().asString(), 
            QualificationDTO.class);
        assertEquals(qual.getDescription(), dingleBerry);
    }

    @Test
    public void testGetWorkers() throws IOException {
        Gson gson = new Gson();

        WorkerDTO[] workers = gson.fromJson(
            Request.get("http://localhost:4567/api/worker").execute().returnContent().asString(), WorkerDTO[].class
            );
        
        assertEquals(13,workers.length);
    }
    
    @Test 
    public void testGetWorkerName() throws IOException {
        Gson gson = new Gson();
        String  worker = "Terry Hampton";
        // String worker2 = worker.replace(" ","%20");
        WorkerDTO workerdto = gson.fromJson(
            Request.get("http://localhost:4567/api/worker/"+ worker.replace(" ","%20")).execute().returnContent().asString(), 
            WorkerDTO.class);
        assertEquals(workerdto.getName(), worker);
    }

    @Test 
    public void testCreateWorker() throws IOException {
        Gson gson = new Gson();
        String HarryKane = "Harry Kane";
        Qualification java = new Qualification("Java");
        Qualification javaScript = new Qualification("JavaScript");
        Qualification python = new Qualification("Python");
        Worker worker =  new Worker(HarryKane, 
                new HashSet<Qualification>(Arrays.asList(python, java, javaScript)), 150000.0);
        String worker2String = gson.toJson(worker.toDTO());
        
        String response= 
            Request.post(("http://localhost:4567/api/worker/"+HarryKane.replace(" ", "%20"))) 
            .bodyString(worker2String, ContentType.APPLICATION_JSON).execute().returnContent().asString();

        WorkerDTO workerdto = gson.fromJson(
            Request.get("http://localhost:4567/api/worker/"+HarryKane.replace(" ", "%20")).execute().returnContent().asString(), 
            WorkerDTO.class);
        assertEquals(workerdto.getName(), HarryKane);
    }

    @Test (expected = Exception.class)
    public void testGetWorkerNameException() throws IOException {
        Gson gson = new Gson();
        String  worker = "Mahmoud Hassan";
        WorkerDTO workerdto = gson.fromJson(
            Request.get("http://localhost:4567/api/worker/"+worker).execute().returnContent().asString(), 
            WorkerDTO.class);
    }

    @Test
    public void testGetProjects() throws IOException {
        Gson gson = new Gson();

        ProjectDTO[] projects = gson.fromJson(
            Request.get("http://localhost:4567/api/project").execute().returnContent().asString(), ProjectDTO[].class
            );
        
        assertEquals(13,projects.length);
    }

    @Test 
    public void testGetProjectName() throws IOException {
        Gson gson = new Gson();
        String  project = "Android Task Monitoring";
        ProjectDTO projectdto = gson.fromJson(
            Request.get("http://localhost:4567/api/project/"+ project.replace(" ","%20")).execute().returnContent().asString(), 
            ProjectDTO.class);
        assertEquals(projectdto.getName(), project);
    }

    @Test (expected = Exception.class)
    public void testGetProjectNameException() throws IOException {
        Gson gson = new Gson();
        String  project = "Animation Player";
        ProjectDTO projectdto = gson.fromJson(
            Request.get("http://localhost:4567/api/project/"+project).execute().returnContent().asString(), 
            ProjectDTO.class);
    }

    @Test 
    public void testCreateProject() throws IOException {
        // ProjectSize medium = new ProjectSize(2);
        Gson gson = new Gson();
        String projectName = "Inflation Prediction System";
        Qualification java = new Qualification("Java");
        Qualification MachineLearning = new Qualification("Machine learning");
        Qualification python = new Qualification("Python");
        Project project =  new Project(projectName, 
                new HashSet<Qualification>(Arrays.asList(python, java, MachineLearning)), ProjectSize.BIG);
        String project2String = gson.toJson(project.toDTO());
        
        String response= 
            Request.post(("http://localhost:4567/api/project/"+projectName.replace(" ", "%20"))) 
            .bodyString(project2String, ContentType.APPLICATION_JSON).execute().returnContent().asString();

        ProjectDTO projectdto = gson.fromJson(
            Request.get("http://localhost:4567/api/project/"+projectName.replace(" ", "%20")).execute().returnContent().asString(), 
            ProjectDTO.class);
        assertEquals(projectdto.getName(), projectName);
    }

}


