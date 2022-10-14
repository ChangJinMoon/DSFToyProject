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
}from '../_actions/types';

const dataState={
  currentUser:null,
  isLoading:true,
  isLogin:false,
  project:[],
  Sprint:[],
}

export default function(state=dataState,action){
  switch(action.type){
    case RegisterUser:
      return{
        ...state,
        success:action.payload
      }
    case LoginUser:
      return{
        ...state,
        currentUser:action.payload.data.data,
        isLoading:false
      }
    case LogoutUser:{
      return{
        ...state,
        currentUser:null,
        isLoading:false,
      }
    }
    case ProjectList:
      return{
        ...state,
        project:action.payload.data.data,
        isLoading:false,
      }
    case AddProject:
      return{
        ...state,
        isLoading:false,
      }
    case DeleteProject:
      return{
        ...state,
        isLoading:false
      }
    case UpdateProject:
      return{
        ...state,
        isLoading:false
      }
    case Sprintinit:
      return{
        ...state,
        Sprint:action.payload.data.data,
        isLoading:false
      }
    case AddSprint:
      return{
        ...state
      }
    case Sprint:
      return{
        ...state
      }
    case SelectProject:
      return{
        ...state
      }
    case SelectSprint:
      return{
        ...state
      }
    default:
      return state;
  }
}
