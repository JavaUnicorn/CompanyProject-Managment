import { useEffect, useState } from 'react'
import ClickList from '../components/ClickList'
import { getQualifications, createQualification } from '../services/dataService'
import LocationID from '../utils/location'
import Button from '../components/Button/Button'
import { darkGrayContainerStyle, grayContainerStyle, pageStyle } from '../utils/styles'
import CreateQualificationForm from '../components/createQualificationForm/createQualificationForm'

const Qualification = (qualification, active) => {
    return (
        <div>
            <div>{qualification.description}</div>
            {active === true ? QualificationBody(qualification) : null}
        </div>
    )
}

export function parseQualification(data){
    console.log(data);
    let description = data.description;

    createQualification(description);
    window.location.reload();
    return false;
}

const QualificationBody = (qualification) => {
    return (
        <div style={grayContainerStyle}>
            Workers: <ClickList list={qualification.workers} styles={darkGrayContainerStyle} path="/workers" />
           
        </div>
    )
}

const Qualifications = () => {
    const [qualifications, setQualifications] = useState([])
    const [showMenu, setShowMenu] = useState(false);
    useEffect(() => { getQualifications().then(setQualifications) }, [])
    const active = LocationID('qualifications', qualifications, 'description')
    
    const toggleMenu = () => {
        setShowMenu(!showMenu);
    };

    return (
        <div style={pageStyle}>
            <h1>Qualifications</h1>
            <Button handleClick={toggleMenu} label="Create Qualification" />
            {showMenu && <CreateQualificationForm />}
            <ClickList active={active} list={qualifications} item={Qualification} path='/qualifications' id='description'/>
        </div>
    )
}


export default Qualifications