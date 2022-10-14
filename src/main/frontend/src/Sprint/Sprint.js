import React,{useState, useEffect, useRef} from 'react'
import { useParams, Link, useNavigate,  } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import { sprint } from '../_actions/user_actions';
function Sprint() {
    const {sprintId}=useParams();
    console.log(sprintId);
    const navigate=useNavigate();
    const dispatch=useDispatch();

    const userId=useSelector(state=>state.user.currentUser)
    dispatch(sprint(userId,sprintId))
        .then((res)=>{
            console.log(res);
            if(res.payload.data.statusEum="OK"){
                navigate("/LandingPage")
            }
        })
    return (
        <div>

        </div>
    )
}

export default Sprint
