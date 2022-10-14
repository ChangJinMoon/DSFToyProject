import React, {useEffect,useState } from 'react'
import { useParams, Link, useNavigate } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import { deleteProject, sprintinit } from "../../_actions/user_actions";
import { useForm } from "react-hook-form";
import SprintList from '../SprintPage/SprintList';
function PersonalProject() {
    const { projectId }=useParams();
    console.log(projectId);
    const data=useSelector(state=>state.user.project);
    console.log(data);
    const [array,setarray]=useState({});
    const {handleSubmit}=useForm();
    //projectId값으로 filter를 해서 원하는 프로젝트 값만 가져온다.
    const selectproject=(projectId)=>{
        const array1=data.filter(x=>x.projectId==projectId);
        console.log(array1)
        if(array1.length==1){
            return array1[0];
        }
        return null;
    }
    const navigate=useNavigate();
    const dispatch=useDispatch();
    //delete
    const deletelist=(projectId)=>{
        dispatch(deleteProject(projectId))
            .then((res)=>{
                console.log(res);
                if(res.payload.data.statusEum=="OK"){
                    navigate("/LandingPage");
                }
            })
    }
    const userId=useSelector(state=>state.user.currentUser)
    const sprintlist=useSelector(state=>state.user.Sprint)
    const project=useSelector(state=>state.user.project)
    console.log(userId);
    useEffect(()=>{
        setarray(selectproject(projectId));
        const sprint=async()=>{
            dispatch(sprintinit(userId,projectId))
                .then((res)=>{
                    console.log(res);
                    console.log(sprintlist);
                    console.log(project)
                })
        }
        sprint();
    },[])
    return (
        <div>
            {
                array?(
                    <>
                        <div>
                            <label>Project Name</label>
                            <br/>
                            <label>{array.projectName}</label>
                        </div>
                        <div>
                            <label>Project Detail</label>
                            <br/>
                            <label>{array.projectDetail}</label>
                        </div>
                        <Link to={`/EditPage/${array.projectId}`}><button>수정</button></Link>
                        <button onClick={()=>{deletelist(projectId)}}>삭제</button>
                        <Link to={`/SprintPage/${array.projectId}`}><button>ADD sprint</button></Link>

                    </>
                ):<label>찾을 수 없습니다.</label>
            }
            <div>
                {sprintlist&&sprintlist.map(project=>(
                    <SprintList key={project.sprintId} handleSubmit={handleSubmit} sprintlist={project}/>
                ))}
            </div>


        </div>
    )
}

export default PersonalProject
