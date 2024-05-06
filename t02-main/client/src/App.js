import React, { createContext, useEffect, useState } from 'react'
import { getQualifications, getWorkers} from './services/dataService.js'
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom'
import './App.css'
import Navbar from './components/Navbar'
import Home from './pages'
import About from './pages/about'
import Projects from './pages/projects'
import Qualifications from './pages/qualifications'
import Workers from './pages/workers'

export const CompanyEmployees = createContext([]);

function App() {
  const [workers, setWorkers] = useState([])
  const [qualifications, setQualifications] = useState([])

  useEffect(() => {getWorkers().then(setWorkers) }, [])
  useEffect(() => {getQualifications().then(setQualifications) }, [])
  return (
    <Router>
      <Navbar />
      <CompanyEmployees.Provider value = {{workers, getWorkers, qualifications, setQualifications}}>
        <Routes>
          <Route path='/' element={<Home />} />
          <Route path='/about' element={<About />} />
          <Route path='/workers/*' element={<Workers />} />
          <Route path='/projects/*' element={<Projects />} />
          <Route path='/qualifications/*' element={<Qualifications />} />
        </Routes>
      </CompanyEmployees.Provider>
    </Router>
  )
}

export default App