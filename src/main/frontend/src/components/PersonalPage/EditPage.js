import React, { useEffect, useState, useRef } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";
import { updateProject } from "../../redux/_actions/user_actions";
import { useForm } from "react-hook-form";
import EditList from "./EditList";
function EditPage() {
  const {
    register,
    formState: { errors },
    handleSubmit,
  } = useForm();
  const { projectId } = useParams();
  console.log(projectId);
  const data = useSelector((state) => state.user.project);
  console.log(data);
  const [array, setarray] = useState({});

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
  //projectId값으로 filter를 해서 원하는 프로젝트 값만 가져온다.
  const selectproject = (projectId) => {
    const array1 = data.filter((x) => x.projectId == projectId);
    console.log(array1);
    if (array1.length == 1) {
      return array1[0];
    }
    return null;
  };
  useEffect(() => {
    setarray(selectproject(projectId));
    console.log(array);
  }, []);
  const [projects, setprojects] = useState([
    {
      projectname: "test1",
      projectdetail: "detail1",
    },
  ]);

  const navigate = useNavigate();
  const dispatch = useDispatch();
  const onSubmit = async () => {
    const project = {
      projectname,
      projectdetail,
    };
    setprojects([...projects, project]);
    setInputs({
      projectname: "",
      projectdetail: "",
    });
    console.log(project);
    dispatch(updateProject(projectId, project)).then((res) => {
      console.log(res);
      if (res.payload.data.statusEum == "OK") {
        navigate("/LandingPage");
      }
    });
  };
  return (
    <div>
      {array ? (
        <>
          <EditList
            handleSubmit={handleSubmit}
            projectname={projectname}
            projectdetail={projectdetail}
            onChange={onChange}
            onSubmit={onSubmit}
          />
        </>
      ) : (
        <label>찾을 수 없습니다.</label>
      )}
    </div>
  );
}

export default EditPage;
