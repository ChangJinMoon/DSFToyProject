import React, { useState, useRef } from "react";
import AddProject from "./AddProject";
import axios from "axios";
import { useForm } from "react-hook-form";
import { useNavigate } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";
import { addProject } from "../../redux/_actions/user_actions";
const server = "/personalPage/1"; //post
const server2 = "/personalPage/1"; //get
const server3 = "/personalProject/"; //delete
function ProjectPage() {
  const {
    register,
    formState: { errors },
    handleSubmit,
  } = useForm();
  const [errorFromSubmit, setErrorFromSubmit] = useState("");

  const [inputs, setInputs] = useState({
    projectname: "",
    projectdetail: "",
  });
  const [getData, setData] = useState([]);
  const { projectname, projectdetail } = inputs;
  const onChange = (e) => {
    const { name, value } = e.target;
    setInputs({
      ...inputs,
      [name]: value,
    });
  };
  const [projects, setprojects] = useState([
    {
      id: 1,
      projectname: "test1",
      projectdetail: "detail1",
    },
  ]);
  const nextId = useRef(4);

  const navigate = useNavigate();
  const dispatch = useDispatch();
  const Id = useSelector((state) => state.user.currentUser);
  //프로젝트 생성시 서버에 보내는 부분(projectName,projectDetails)
  //생성된 프로젝트는 personalPage/숫자로 프로젝트 내용을 받아온다.
  //axios post
  const onSubmit = async () => {
    const project = {
      id: nextId.current,
      projectname,
      projectdetail,
    };
    setprojects([...projects, project]);
    setInputs({
      projectname: "",
      projectdetail: "",
    });
    nextId.current += 1;
    console.log(project.projectname);
    dispatch(addProject(Id, project)).then((res) => {
      console.log(res);
      if (res.payload.data.statusEum == "OK") {
        navigate("/LandingPage");
      }
    });
  };

  return (
    <>
      <AddProject
        handleSubmit={handleSubmit}
        projectname={projectname}
        projectdetail={projectdetail}
        onChange={onChange}
        onSubmit={onSubmit}
      />
      {/*처음 getData값이 null이여서 값을 넘겨줘도 아무것도 뜨지 않는다
        최신화를 시켜서 넘겨줘야한다.
      */}
    </>
  );
}

export default ProjectPage;
