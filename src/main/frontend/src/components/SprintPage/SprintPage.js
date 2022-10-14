import React,{useState, useEffect, useRef} from 'react'
import axios from 'axios';
import { useForm } from "react-hook-form";
import { useParams, Link, useNavigate,  } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import Slider from "react-slick";
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";
import { addsprint } from '../../_actions/user_actions';
import AddSprint from './AddSprint';


function SprintPage() {
  const { projectId }=useParams();
  console.log(projectId);
  const { register, formState:{errors},handleSubmit}=useForm();
  const [inputs, setInputs]=useState({
    sprintname:"",
    sprintdetail:"",
  });
  const [getData,setData]=useState([]);
  const{sprintname,sprintdetail}=inputs;
  const onChange=e=>{
    const{name,value}=e.target;
    setInputs({
      ...inputs,
      [name]:value
    })
  }
  const [projects,setprojects]=useState([
    {
      sprintname:"test1",
      sprintdetail:"detail1"
    },
  ])
  const navigate=useNavigate();
  const dispatch=useDispatch();
  const onSubmit=async()=>{
    const project={
      sprintname,
      sprintdetail,
    }
    setprojects([...projects,project])
    setInputs({
      sprintname:"",
      sprintdetail:""
    })
    dispatch(addsprint(projectId,project))
        .then((res)=>{
          console.log(res);
          if(res.payload.data.statusEum=="OK"){
            navigate("/LandingPage");
          }
        })
  }

  return (
      <>
        <AddSprint handleSubmit={handleSubmit} sprintname={sprintname} sprintdetail={sprintdetail} onChange={onChange} onSubmit={onSubmit}/>


      </>

  )
}

export default SprintPage
