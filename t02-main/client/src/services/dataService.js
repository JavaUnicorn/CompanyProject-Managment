import axios from 'axios'

const SERVER_ADDRESS = 'http://localhost:4567/api/'

//Qualification
export function getQualification(description) {
    return axios.get(SERVER_ADDRESS + 'qualifications/' + description).then((res) => JSON.parse(res.request.response))
}

export function getQualifications() {
    return axios.get(SERVER_ADDRESS + 'qualifications').then((res) => JSON.parse(res.request.response).sort((a, b) => a.description.localeCompare(b.description)))
}

export function createQualification(description) {
    return axios.post(SERVER_ADDRESS + 'qualifications/' + description, { description: description })
}

//Project
export function getProject(name) {
    return axios.get(SERVER_ADDRESS + 'project/' + name).then((res) => JSON.parse(res.request.response))
}

export function getProjects() {
    return axios.get(SERVER_ADDRESS + 'project').then((res) => JSON.parse(res.request.response).sort((a, b) => a.name.localeCompare(b.name)))
}

export function createProjects(name, qualifications, size) {
    return axios.post(SERVER_ADDRESS + 'project/' + name, { name: name, qualifications: qualifications, size:size })
}

//Worker
export function getWorker(name) {
    return axios.get(SERVER_ADDRESS + 'worker/' + name).then((res) => JSON.parse(res.request.response))
}

export function getWorkers() {
    return axios.get(SERVER_ADDRESS + 'worker').then((res) => JSON.parse(res.request.response).sort((a, b) => a.name.localeCompare(b.name)))
}

export function createWorkers(name, qualifications, salary) {
    return axios.post(SERVER_ADDRESS + 'worker/' + name, { name: name, qualifications: qualifications, salary:salary })
}

//Company
export function start(projects) {
    return axios.put(SERVER_ADDRESS + 'start', {name: projects})
}

export function finish(projects) {
    return axios.put(SERVER_ADDRESS + 'finish', {name: projects})
}

export function assign(worker, project) {
    return axios.put(SERVER_ADDRESS + 'assign', {worker:worker, project:project})
}

export function unassign(worker, project) {
    return axios.put(SERVER_ADDRESS + 'unassign', {worker:worker, project:project})
}