import React,{useState, useRef, useEffect} from 'react'
import ProjectList from './ProjectList';
import AddProject from './AddProject';
import axios from "axios";

const server="/personalPage/1"; //post
const server2="/personalPage/1";  //get

function ProjectPage() {
  const [inputs, setInputs]=useState({
    projectname:"",
    projectdetail:"",
  });
  const [getData,setData]=useState();
  const{projectname,projectdetail}=inputs;
  const onChange=e=>{
    const{name,value}=e.target;
    setInputs({
      ...inputs,
      [name]:value
    })
  }
  const [projects,setprojects]=useState([
    {
      id:1,
      projectname:"test1",
      projectdetail:"detail1"
    },
  ])
  const nextId=useRef(4);
  const onCreate=()=>{
    const project={
      id:nextId.current,
      projectname,
      projectdetail,
    }
    setprojects([...projects,project])
    setInputs({
      projectname:"",
      projectdetail:""
    })
    nextId.current+=1;
  }
  //프로젝트 생성시 서버에 보내는 부분(projectName,projectDetails)
  //생성된 프로젝트는 personalPage/숫자로 프로젝트 내용을 받아온다.
  //axios post
  const onSubmit=()=>{
    const project={
      id:nextId.current,
      projectname,
      projectdetail,
    }
    setprojects([...projects,project])
    setInputs({
      projectname:"",
      projectdetail:""
    })
    nextId.current+=1;
    console.log(project.projectname)
    
      axios.post(server,{
        projectName:project.projectname,
        projectDetails:project.projectdetail
      })
          .then((res)=>{
            console.log("응답",res);
          })
          .catch((error)=>{
            console.log("error",error);
          })
    
  }
  //axios get
  const onGet=()=>{

      axios.get(server2)
          .then((res)=>{
            console.log("응답",res);
            setData(res.data.data);//response중 필요한 값만 
            console.log("getData: ", res.data.data); //getData값을 보여주면서 key설정을 해야할수 있음
          })
          .catch((error)=>{
            console.log("error",error);
          })
    
  }
  return (
    <>
      <AddProject projectname={projectname} projectdetail={projectdetail} onChange={onChange} onCreate={onCreate} onSubmit={onSubmit} onGet={onGet}/>
      {/*처음 getData값이 null이여서 값을 넘겨줘도 아무것도 뜨지 않는다
        최신화를 시켜서 넘겨줘야한다.
      */ }
      <ProjectList projects={projects} getData={getData}/>
    </>
  )
}

export default ProjectPage
