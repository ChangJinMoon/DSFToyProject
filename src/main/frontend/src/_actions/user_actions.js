import {
  RegisterUser,
  LoginUser,
  LogoutUser,
  ProjectList,
  AddProject,
  DeleteProject,
  UpdateProject,
  Sprintinit,
  AddSprint,
  Sprint,
  SelectProject,
  SelectSprint,
}from './types';
import axios from 'axios';

//register, post메소드
export function registerUser(data){
  const request=axios.post("/join",{
    email:data.email,
    name:data.name,
    password:data.password,
    findPasswordAnswer:data.findPasswordAnswer
  })
  return{
    type:RegisterUser,
    payload:request
  }
}
//login, post메소드
export function loginUser(data){
  const request=axios.post("/login",{
    email:data.email,
    password:data.password
  })
  return{
    type:LoginUser,
    payload:request
  }
}
export function logoutUser(){
  return{
    type:LogoutUser
  }
}
//project list 불러오기, get메소드
export function projectlist(id){
  const request=axios.get("/personalPage/"+id)
  return{
    type:ProjectList,
    payload:request
  }
}
//addproject, post 메소드
export function addProject(id,project){
  const request=axios.post("/personalPage/"+id,{
    projectName:project.projectname,
    projectDetails:project.projectdetail
  })
  return{
    type:AddProject,
    payload:request
  }
}
//delete project, delete 메소드
export function deleteProject(projectId){
  const request=axios.delete("/personalProject/"+projectId)
  return{
    type:DeleteProject,
    payload:request
  }
}
//update project, put 메소드
export function updateProject(projectId,project){
  const request=axios.put("/personalProject/update/"+projectId,{
    projectName:project.projectname,
    projectDetails:project.projectdetail
  })
  return{
    type:UpdateProject,
    payload:request
  }
}
//sprint list받기, get 메소드
export function sprintinit(userId,projectId){
  const request=axios.get("/personalProject/"+projectId,{
    params:{
      userId:userId,
      projectId:projectId
    }
  })
  return{
    type:Sprintinit,
    payload:request
  }
}
//add sprint, post 메소드
export function addsprint(projectId,sprint){
  const request=axios.post("/personalProject/addSprint/"+projectId,{
    sprintName:sprint.sprintname,
    sprintDetails:sprint.sprintdetail
  })
  return{
    type:AddSprint,
    payload:request
  }
}
//sprint 내용 받기, get 메소드
export function sprint(userId,projectId){
  const request=axios.get("/Sprint/"+projectId,{
    params:{
      userId:userId,
      projectId:projectId
    }
  })
  return{
    type:Sprint,
    payload:request
  }
}

export function selectproject(userId){
  const request=axios.get("/personalProject/now/"+userId)
  return{
    type:SelectProject,
    payload:request
  }
}

export function selectsprint(userId){
  const request=axios.get("/Sprint/now/"+userId)
  return{
    type:SelectSprint,
    payload:request
  }
}


