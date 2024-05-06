import { useEffect, useState } from 'react'
import ClickList from '../components/ClickList'
import { getWorkers, unassign } from '../services/dataService'
import LocationID from '../utils/location'
import { darkGrayContainerStyle, grayContainerStyle, pageStyle } from '../utils/styles'
import { Component } from 'react'
import { createWorkers } from '../services/dataService'
import React from 'react'
import Button from '../components/Button/Button'
import { getQualifications, createQualification } from '../services/dataService'
import CreateQualificationForm from '../components/createQualificationForm/createQualificationForm'
// import { MantineProvider} from '@mantine/core'
// import { Modal, Button, Input } from '@mantine/core'
// import {useDisclosure} from '@mantine/hooks'
import { useMatch } from 'react-router-dom'
import CreateWorkerForm from '../components/createWorkerForm/createWorkerForm'

const Worker = (worker, active) => {
    return (
        <div>
            <div>{worker.name}</div>
            {active === true ? WorkerBody(worker) : null}
        </div>
    )
}

export function createWorker(data){
    console.log(data);
    let name = data.name;
    console.log(data.name);
    let quals = data.Qualifications.split(" "); // turn into list
    console.log(quals);
    let salary = Number(data.salary);

    createWorkers(name, quals, salary);
    setTimeout(() => {
        window.location.reload();
    }, 100);
    return false;
}

const Project = (project) => {
    const worker = useMatch('workers/:name').params.name;
    return (
        <div>
         
            {console.log(worker)}
            <div>{project} <button onClick={(e)=>{e.stopPropagation(); unassign(worker, project); window.location.reload();}}> Unassign </button></div>
            
        </div>

    )
}


const WorkerBody = (worker) => {
    return (
        <div style={grayContainerStyle}>
            <div>Salary: ${worker.salary}</div>
            <div>Work Load: {worker.workload}</div>
          <div>Projects: <ClickList  list={worker.projects} item={Project} styles={darkGrayContainerStyle} path="/project" /></div> 

          <div>Qualifications: <ClickList list={worker.qualifications} styles={darkGrayContainerStyle} path="/qualifications" /> </div> 
        </div>
    )
}

const Workers = () => {
    const [workers, setWorkers] = useState([])
    const [showMenu, setShowMenu] = useState(false);
    useEffect(() => {getWorkers().then(setWorkers) }, [])
    const active = LocationID('workers', workers, 'name')

    const toggleMenu = () => {
        setShowMenu(!showMenu);
    };

    return (
        <div style={pageStyle}>
            <h1>
                This page displays a table containing all the workers.
            </h1>
            <Button handleClick={toggleMenu} label="Create Worker" />
            {showMenu && <CreateWorkerForm />}
            <ClickList active={active} list={workers} item={Worker} path='/workers' id='name'/>
        </div>
    )
}
  

export default Workers