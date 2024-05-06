import { useContext, useEffect, useState } from 'react'
import ClickList from '../components/ClickList'
import { finish, getProjects, createProjects, start, assign} from '../services/dataService'
import LocationID from '../utils/location'
import { baigeContainerStyle, darkGrayContainerStyle, grayContainerStyle, pageStyle } from '../utils/styles'
import Button from '../components/Button/Button'
import CreateProjectForm from '../components/CreateProjectForm/CreateProjectForm'
import { CompanyEmployees } from '../App'

const Project = (project, active) => {
    return (
        <div>
            <div>{project.name} is currently: {project.status}</div>
            {active === true ? ProjectBody(project) : null}
        </div>
    )
}

export function createProject(data){
    console.log(data);
    let name = data.Name;
    console.log(data.Name);
    let quals = data.Qualifications.split(" "); // turn into list
    console.log(quals);
    let size = data.Size;

    createProjects(name, quals, size);
}

function changeStatus(project){
    if(project.status === "PLANNED"){
        start(project.name)
        window.location.reload();
        return false;

    }else if (project.status === "ACTIVE") {
        finish(project.name)
        window.location.reload();
        return false;

    }
    return false;
}
function oppositeStatus(project){
    if(project.status === "PLANNED" || project.status === "SUSPENDED"){
        return "ACTIVE";

    }else if (project.status === "ACTIVE") {
        return "FINISHED";

    }
    return "N/A";
}

const ProjectBody = (project) => {
    const { workers} = useContext(CompanyEmployees);
    let choosenOne = undefined
   
    
    let workeroptions = workers.map((worker)=>{
        let workerNeeded = false
        for (let index = 0; index < (project.missingQualifications).length; index++) {
            console.log((worker.qualifications).includes(project.missingQualifications[index]))
            if((worker.qualifications).includes(project.missingQualifications[index])){
               workerNeeded = true
            }
        }
       
        if(workerNeeded === true){
             return <option value ={worker.name} onClick={(e)=>  e.stopPropagation()}>{worker.name}</option> 
        }       
    })
    
    return (
        <div style={grayContainerStyle}>
            <div>Project size is: {project.size}</div>
           <div>Current Workers: <ClickList list={project.workers} styles={darkGrayContainerStyle} path="/workers" /></div> 
           <div>Qualifications: <ClickList list={project.qualifications} styles={darkGrayContainerStyle} path="/qualifications" /> </div> 
           
           <hr />
           {project.missingQualifications.length !== 0 && <div>Missing qualifications: <ClickList list={project.missingQualifications} styles={baigeContainerStyle} path="/qualifications" /> </div>}
           {project.status !== "FINISHED" && <div>Change project status to <button onClick={()=>{changeStatus(project)}}>{oppositeStatus(project)}</button></div> }
           {project.missingQualifications.length !== 0 && <hr />}
           <div>
           {project.missingQualifications.length !== 0 && <form onSubmit={(e)=>{
            e.preventDefault();
           }}>
            
            <select multiple name="workers" onChange={(e)=>{choosenOne = e.target.selectedOptions.item(0).value; console.log(e.target.selectedOptions.item(0).value)}}>    
                {workeroptions}
            </select>
            <input type="submit" value="Submit" onClick={(e)=>{assign(choosenOne, project.name);window.location.reload();}}/>
           </form> }
           </div>
        </div>
    )
}

const Projects = () => {
    const [projects, setProjects] = useState([])
    const [showMenu, setShowMenu] = useState(false);
    useEffect(() => { getProjects().then(setProjects) }, [])
    const active = LocationID('projects', projects, 'name')

    const toggleMenu = () => {
        setShowMenu(!showMenu);
    };

    return (
        <div style={pageStyle}>
            <h1>Projects</h1>
            <Button handleClick={toggleMenu} label="+" />
            {showMenu && <CreateProjectForm />}
            <ClickList active={active} list={projects} item={Project} path='/projects' id='name' />
        </div>
    )
}

export default Projects