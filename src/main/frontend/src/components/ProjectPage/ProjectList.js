import React from 'react'
function Project({project,getData}){
  return(
    <div>
      <b>{project.projectname}</b>
      <b>{project.projectdetail}</b>
    </div>
  )
}
function ProjectList({projects,getData}) {
  console.log(projects)
  console.log(getData)
  return (
    <div>
      {projects.map(project=>(
        <Project project={project} getData={getData} key={project.id}/>
      ))}
      
    </div>
  )
}

export default ProjectList
