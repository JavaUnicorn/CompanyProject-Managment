# USE CASES

  
  

 ## 1.  View Company Qualifications

  
|User Action| System Action |
|--|--|
| 1. A user with the website open clicks on the "Qualifications" button in the menu. |  |
||2. Website displays "Qualification" page.|
||3. Displays Qualification table for Company|

  
### Alternative Course of Events:

Step 1: Click on different page will lead to different page

Step 2: Error from client side

Step 3: Server doesn't connect resulting in no table displaying

___


## 2. View Company Workers

|User Action| System Action |
|--|--|
| 1. A user with the website open clicks on the "Workers" button on the menu. |  |
||2. Website displays "Workers" page.|
||3. Displays Workers table for Company|

  
### Alternative Course of Events:

Step 1: Click on different page this will lead to different page

Step 2: Error from client side

Step 3: Server doesn't connect resulting in no table displaying

___


## 3. View Company Projects
|User Action|System Action|
|--|--|
| 1. A user with the website open clicks on the "Project" button on the menu. |  |
||2. Website displays "Project" page.|
||3. Displays Project table for Company|

  
### Alternative Course of Events:

Step 1: Click on different page this will lead to different page

Step 2: Error from client side

Step 3: Server doesn't connect resulting in no table displaying

___


## 4. View Qualification Details

 
|User Action| System Action |
|--|--|
| 1. A user with the website open clicks on the "Qualifications" button in the menu. |  |
||2. Website displays "Qualification" page.|
||3. Displays Qualification table for Company|
|4. Click Qualification XYZ||
||5. Renders dropdown portion with assigned workers|

  
### Alternative Course of Events:

Step 1: Click on different page will lead to different page

Step 2: Error from client side

Step 3: Server doesn't connect resulting in no table displaying

Step 4: If company does not have information about a qualification, the user will not be able to find a row for it in the table.

Step 5: Qualification may have no workers

___


## 5. View Worker Details

|User Action| System Action |
|--|--|
| 1. A user with the website open clicks on the "Employee" button on the menu. |  |
||2. Website displays "Employee" page.|
||3. Displays Employee table for Company|
|4. Click Worker XYZ||
||5. Renders dropdown portion with all of worker information - quallifications, assigned projects, workload, and salary -|

  
### Alternative Course of Events:

Step 1: Click on different page this will lead to different page

Step 2: Error from client side

Step 3: Server doesn't connect resulting in no table displaying

Step 4: If company does not have information about a worker, the user will not be able to find a row for it in the table.

___


## 6. View Project Details

|User Action| System Action |
|--|--|
| 1. A user with the website open clicks on the "Project" button on the menu. |  |
||2. Website displays "Project" page.|
||3. Displays Project table for Company|
|4. Click Project XYZ||
||5. Renders dropdown portion with all of project information - name, size, status, assigned employees, required qualifications, and missing qualifications - with button to change status|

  
### Alternative Course of Events:

Step 1: Click on different page this will lead to different page

Step 2: Error from client side

Step 3: Server doesn't connect resulting in no table displaying

Step 4: If company does not have information about a project, the user will not be able to find a row for it in the table. It Is possible to lead to a white page from here if a API error appears.

___


## 7. Create a new Qualification
|User Action| System Action |
|--|--|
| 1. A user with the website open clicks on the "Qualification" button on the menu. |  |
||2. Website displays "Qualification" page.|
||3. Displays Qualification table for Company|
|4. Click "Create Qualification" button||
||5. System displays a popup where the actor can input the qualification name and includes a submit button to confirm the action|
|6. Clicks submit||
||7. System displays the "Qualification" page|

  
### Alternative Course of Events:

Step 1: Click on different page this will lead to different page

Step 2: Error from client side

Step 3: Server doesn't connect resulting in no table displaying

Step 4: If the User did not enter valid details required to create a Qualification,or did not enter enough details required to Create a new Qualification the Entry for a new Qualification becomes invalid and the User is taken back to the Qualification page where the Qualification table is available

Step 5: User exits the popup resulting in no qualification being added and the "Qualification" page being displayed.

___


## 8. Create a new Worker
|User Action| System Action |
|--|--|
 | 1. The user clicks on the "Worker" button on the menu ||
 || 2. The website displayes "Worker" page. | |
 || 3. Worker table for the Company is displayed |
 | 4. User clicks "Create Worker" button ||
 || 5. System displays a popup where the user can input the Worker name and then add name, Qualifications, salary and assign project to the worker. |
 | 6. User inputs all the informations and clicks Submit ||
 || 7. System displays the updated "Worker" page with the new Worker

### Alternative Course of Events:

Step 1: Click on different page this will lead to different page

Step 2: Error from client side

Step 3: Server doesn't connect resulting in no table displaying

Step 4: If the User did not enter valid details required to create a Worker,or did not enter enough details required to Create a new new worker the Entry for a new worker becomes invalid and the User is taken back to the Worker page where the original worker table is available

Step 5: User exits the popup resulting in no worker being added and the "Worker" page with the original table being displayed.




## 9. Create a new Project
|User Action| System Action |
|--|--|
| 1. The User clicks on "Project" button on the menu||
|| 2. Website displays "Project" Page ||
|| 3. Displays the Project table for the Comapny |
| 4. Click "Create Project" button ||
|| 5. System displays a pop up where the user can create a new project for the company where the user can input Project name, Status, Size, Company, Qualifications and assign workers |
| 6. The user inputs all the informations and assignes worker to the project and clicks Submit.||
|| 7. System displays the new Project created along with it's information and workers assigned in the Project table of the "Project" Page.

### Alternative Course of Events:

Step 1: Click on different page this will lead to different page

Step 2: Error from client side

Step 3: Server doesn't connect resulting in no table displaying

Step 4: If company does not have information about a Project, the user will not be able to find a row for it in the table. It Is possible to lead to a white page from here if a API error appears.

Step 5: User exits the popup resulting in no worker being added and the "Worker" page with the original table being displayed.


## 10. Assign Worker
|User Action| System Action |
|--|--|
| 1. The user clicks on "Worker" button on the menu to view all the Workers in the company|| 
|| 2. The website displays "Worker" Page|
| 3. The user clicks on each worker to see if they are assigned to a project or not||
||4. A drop down appears with a "Assign wroker" button.|
| 5. The user clicks on the "Assign Worker" button to assign the Worker from the list of Projects||
|| 6. The button activats a popup which has a list of projects that the user to assign the Worker||
|| 7. After the user is done assigning the project for the worker, 'submit' is clicked and the 'Worker' page appears with the new status of the worker from 'unassigned' to 'Assign'


### Alternative Course of Events:

Step 1: Click on different page this will lead to different page

Step 2: Error from client side

Step 3: Server doesn't connect resulting in no table displaying

Step 4: If the project choosen is overloaded, a error message appears not allowing the Worker to be assigned to that project. in this case an error message appears and the user is taken back to the 'Worker' page where the status of the worker remaines unchaged as Unassigned.

Step 5: User exits the dropdown resulting in no assignment of project to worker and the "Worker" page with the original status being displayed.




## 11. Unassign Worker
|User Action| System Action |
|--|--|
| 1. The user clicks on "Workers" button on the menu||
|| 2. Website displays the "Workers" Page|
| 3. The user clicks on each worker to check if they are assigned or not.||
|| 4. A dropdown apppears with an 'unassigned worker'button along with the list of projects the worker is assigned to.|
| 5. The user clicks the project the worker should be unassigned to through the popup.
|| 6. The 'workers' page appears with the worker unassigned to that pirticular project||

 ### Alternative Course of Events:

Step 1: Click on different page this will lead to different page

Step 2: Error from client side

Step 3: Server doesn't connect resulting in no table displaying

Step 4:User wants to unassign a project for a worker that the worker is not assigned to in the first place resulting in the project not being in the list at all

Step 5: User exits the dropdown resulting in no unassignment of project from the list fo projects assigned to the worker and the "Worker" page with the original list of projects being displayed.



## 12.  Start Project.

|User Action| System Action |
|--|--|
| 1. A user with the website open clicks on the "Project" button on the menu. |  |
||2. Website displays "Project" page.|
||3. Displays Project table for Company|
|4. Click Project XYZ||
||5. Renders dropdown portion with all of project information - name, size, status, assigned employees, required qualifications, and missing qualifications - with "start" button on the bottom|
| 6. Click "Start" button| |
| | 7. Change status of project|
| | 8. Refresh page to display new status of project|

  
### Alternative Course of Events:

Step 1: Click on different page this will lead to different page

Step 2: Error from client side

Step 3: Server doesn't connect resulting in no table displaying

Step 4: If company does not have information about a project, the user will not be able to find a row for it in the table. It Is possible to lead to a white page from here if a API error appears.

Step 5: If company is finished or already started the button will not be labeled "start" anymore instead a different Status will be present or not present if the state of the project can no longer change.

Step 6: Status will not change if there is a missing requierment.

___

## 13.  Finish Project.
|User Action| System Action |
|--|--|
| 1. A user with the website open clicks on the "Project" button on the menu. |  |
||2. Website displays "Project" page.|
||3. Displays Project table for Company|
|4. Click Project XYZ||
||5. Renders dropdown portion with all of project information - name, size, status, assigned employees, required qualifications, and missing qualifications - with "Finish" button on the bottom|
| 6. Click "Finish" button| |
| | 7. Change status of project|
| | 8. Refresh page to display new information of project - status change and all qualifications are now missing |
| | 9. Use button to change status of project|

  
### Alternative Course of Events:

Step 1: Click on different page this will lead to different page

Step 2: Error from client side

Step 3: Server doesn't connect resulting in no table displaying

Step 4: If company does not have information about a project, the user will not be able to find a row for it in the table. It Is possible to lead to a white page from here if a API error appears.

Step 5: If company is finished or had not been started the button will not be labeled "finish" instead a different status will be present or not present if the state of the project can no longer change.

___
