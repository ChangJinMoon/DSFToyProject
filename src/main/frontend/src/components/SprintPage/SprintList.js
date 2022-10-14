import React from 'react'
import { Link } from "react-router-dom";
function SprintList({sprintlist}) {
    console.log(sprintlist)
    return (
        <div>
            <li>
                <Link to={`/Sprint/${sprintlist.sprintId}`}>{sprintlist.sprintName}</Link>/
                {sprintlist.sprintDetail}
            </li>
        </div>
    )
}

export default SprintList
