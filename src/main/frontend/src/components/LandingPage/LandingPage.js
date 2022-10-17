import React, { useState, useEffect, useRef } from "react";
import axios from "axios";
import { useNavigate, Link } from "react-router-dom";
import Slider from "react-slick";
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";
import Modal from "./Modal";
import image1 from "../../images/image1.png";
import { useForm } from "react-hook-form";
import { useDispatch, useSelector } from "react-redux";
import {
  logoutUser,
  projectlist,
  deleteProject,
} from "../../redux/_actions/user_actions";
import ProjectList from "../ProjectPage/ProjectList";

const SampleNextArrow = (props) => {
  const { className, style, onClick } = props;
  return (
    <div
      className={className}
      style={{ ...style, display: "block", background: "red" }}
      onClick={onClick}
    />
  );
};

const SamplePrevArrow = (props) => {
  const { className, style, onClick } = props;
  return (
    <div
      className={className}
      style={{ ...style, display: "block", background: "green" }}
      onClick={onClick}
    />
  );
};

function LandingPage() {
  const [click, setClick] = useState(false);
  const [button, setButton] = useState(true);
  const [search, setSearch] = useState("");
  const [lists, setLists] = useState([]);
  const [loading, setLoading] = useState(false);
  const { handleSubmit } = useForm();
  let settings = {
    dots: true, //컨텐츠로 바로 이동이 가능한 버튼(false시 사라진다.)
    infinite: true, //컨텐츠 끝까지 갔을때 다음 콘텐츠로 가져와 반복
    speed: 500, //콘텐츠 이동속도
    slidesToShow: 1, //한 화면에 보이는 콘텐츠 개수
    slidesToScroll: 1, //한번에 넘어가는 콘텐츠 수
    cssEase: "linear",
    nextArrow: <SampleNextArrow />,
    prevArrow: <SamplePrevArrow />,
  };
  const slider = useRef(null);

  const target = (tg) => {
    slider.current = tg;
  };

  const nextBtn = () => {
    slider.current.slickNext();
  };
  const prevBtn = () => {
    slider.current.slickPrev();
  };

  const [toggleMenu, setToggleMenu] = useState(false);
  const [toggleBar, setToggleBar] = useState(true);
  const toggleChange = () => {
    setToggleMenu(!toggleMenu);
    setToggleBar(!toggleBar);
  };
  const onMenuClick = () => {
    setToggleMenu(!toggleMenu);
    setToggleBar(!toggleBar);
  };

  const [currentPage, setCurrentPage] = useState(1);
  const [currentPosts, setCurrentPosts] = useState([]);
  const postsPerPage = 10;
  const indexOfLastPost = currentPage * postsPerPage;
  const indexOfFirstPost = indexOfLastPost - postsPerPage;

  //modal창
  const [modalOpen, setModalOpen] = useState(false);
  const openModal = () => {
    setModalOpen(true);
  };
  const closeModal = () => {
    setModalOpen(false);
  };

  const onChangeSearch = (e) => {
    e.preventDefault();
    setSearch(e.target.value);
  };
  const onSearch = (e) => {
    e.preventDefault();
    if (search === null || search === "") {
      axios.get("").then((res) => {});
    } else {
    }
  };
  const handleClick = () => setClick(!click);
  const closeMenu = () => setClick(false);

  const navigate = useNavigate();
  const dispatch = useDispatch();
  const Id = useSelector((state) => state.user.currentUser);
  const data = useSelector((state) => state.user.project);
  //sessionStorge
  let sessionStorge = window.sessionStorage;
  const isLoading = useSelector((state) => state.user.isLoading);
  const [getlists, setlists] = useState(null);
  //컴포넌트가 렌더링 될 때마다 특정 작업을 실행할 수 있도록 하는 hook
  useEffect(() => {
    const project = async () => {
      dispatch(projectlist(Id)).then((res) => {
        console.log(res);
        setlists(res);
        console.log(data);
      });
    };
    project();
  }, []);
  const deletelist = (projectId) => {
    dispatch(deleteProject(projectId)).then((res) => {
      console.log(res);
    });
  };
  const logout = async () => {
    sessionStorge.removeItem("id");
    dispatch(logoutUser());
    navigate("/");
  };

  return (
    <>
      <div className="header">
        <img src={image1} title="logo" />
        <nav>
          <ul>
            <li className="active">
              <a href="">Home</a>
            </li>
            <li>
              <button onClick={() => logout()}>Logout</button>
            </li>
          </ul>
        </nav>
      </div>
      <h2>Search</h2>
      <form onSubmit={(e) => onSearch(e)}>
        <input
          type="text"
          value={search}
          placeholder="검색어를 입력하세요"
          onChange={onChangeSearch}
        />
        <button type="submit">검색</button>
      </form>
      <Link style={{ float: "right" }} to="/ProjectPage">
        ADD Project
      </Link>

      {/*ADD Project 모달
    <button onClick={openModal}>Modal</button>
    <Modal open={modalOpen} close={closeModal} header="Modal heading">
      <label>삭제하시겠습니까?</label>
      <br/>
    </Modal>
    */}

      <div>
        {data &&
          data.map((project) => (
            <ProjectList
              key={project.projectId}
              handleSubmit={handleSubmit}
              project={project}
              deletelist={deletelist}
            />
          ))}
      </div>

      {/*
    <Slider {...settings}>
      <div className="card-wrapper">
        <h3>1</h3>
        <div className="card-image">
          <img src=""/>
        </div>
        <ul className="social-icons">
          <li><a href="#"><i className="fa fa-facebook"></i></a></li>
          <li><a href="#"><i className="fa fa-"></i></a></li>
          <li><a href="#"><i className="fa fa-"></i></a></li>
          <li><a href="#"><i className="fa fa-"></i></a></li>
        </ul>
      </div>
      <div className="card-wrapper">
        <h3>2</h3>
        <div className="card-image">
          <img src=""/>
        </div>
        <ul className="">
          <li><a href="#"><i className="fa fa-"></i></a></li>
          <li><a href="#"><i className="fa fa-"></i></a></li>
          <li><a href="#"><i className="fa fa-"></i></a></li>
          <li><a href="#"><i className="fa fa-"></i></a></li>
        </ul>
      </div>

      <div className="card-wrapper">
        <h3>3</h3>
        <div className="card-image">
          <img src=""/>
        </div>
        <ul className="">
          <li><a href="#"><i className="fa fa-"></i></a></li>
          <li><a href="#"><i className="fa fa-"></i></a></li>
          <li><a href="#"><i className="fa fa-"></i></a></li>
          <li><a href="#"><i className="fa fa-"></i></a></li>
        </ul>
        <div className="details">
          <h2></h2>
        </div>
      </div>

      <div className="card-wrapper">
        <h3>4</h3>
        <div className="card-image">
          <img src=""/>
        </div>
        <ul className="">
          <li><a href="#"><i className="fa fa-"></i></a></li>
          <li><a href="#"><i className="fa fa-"></i></a></li>
          <li><a href="#"><i className="fa fa-"></i></a></li>
          <li><a href="#"><i className="fa fa-"></i></a></li>
        </ul>
      </div>

    </Slider>
    */}
    </>
  );
}

export default LandingPage;
