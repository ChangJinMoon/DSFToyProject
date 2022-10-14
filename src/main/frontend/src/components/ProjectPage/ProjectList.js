import React, { useState } from 'react'
import { Link } from "react-router-dom";
function ProjectList({project}){
    console.log(project)
    return(
        <div>
            <li>
                <Link to={`/PersonalProject/${project.projectId}`}>{project.projectName}</Link>/
                {project.projectDetail}
            </li>
        </div>
    )
}
export default ProjectList
