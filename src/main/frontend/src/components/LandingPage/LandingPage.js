import React,{useState, useEffect, useRef} from 'react'
import axios from 'axios';
import Appbar from "./Appbar";
import { Link } from 'react-router-dom';
import Slider from "react-slick";
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";
import Modal from "./Modal";
import image1 from "../../images/image1.png";

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
const server="";
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
  const [click, setClick]=useState(false);
  const [button, setButton]=useState(true);
  const [search,setSearch]=useState("");
  const [lists,setLists]=useState([]);
  const [loading, setLoading] =useState(false);
  
  let settings={
    dots:true, //컨텐츠로 바로 이동이 가능한 버튼(false시 사라진다.)
    infinite:true, //컨텐츠 끝까지 갔을때 다음 콘텐츠로 가져와 반복
    speed:500, //콘텐츠 이동속도
    slidesToShow:1, //한 화면에 보이는 콘텐츠 개수
    slidesToScroll:1, //한번에 넘어가는 콘텐츠 수
    cssEase:"linear",
    nextArrow: <SampleNextArrow />,
    prevArrow: <SamplePrevArrow />,
  }
  const slider=useRef(null);

  const target=(tg)=>{
    slider.current=tg;
  }

  const nextBtn=()=>{
    slider.current.slickNext();
  }
  const prevBtn=()=>{
    slider.current.slickPrev();
  }


  const [toggleMenu,setToggleMenu]=useState(false)
  const [toggleBar,setToggleBar]=useState(true)
  const toggleChange=()=>{
    setToggleMenu(!toggleMenu)
    setToggleBar(!toggleBar)
  }
  const onMenuClick=()=>{
    setToggleMenu(!toggleMenu)
    setToggleBar(!toggleBar)
  }

  const [currentPage,setCurrentPage]=useState(1);
  const [currentPosts,setCurrentPosts]=useState([]);
  const postsPerPage=10;
  const indexOfLastPost=currentPage*postsPerPage;
  const indexOfFirstPost=indexOfLastPost-postsPerPage;
  
  //modal창
  const [modalOpen,setModalOpen]=useState(false);
  const openModal=()=>{
    setModalOpen(true);
  }
  const closeModal=()=>{
    setModalOpen(false);
  }

  const onChangeSearch=(e)=>{
    e.preventDefault();
    setSearch(e.target.value);
  }
  const onSearch=(e)=>{
    e.preventDefault();
    if(search===null || search===""){
      axios.get("")
          .then((res)=>{
            })
    }else{

    }
  }
  const handleClick=()=>setClick(!click);
  const closeMenu=()=>setClick(false);
  
  //axios post
  const postdata=async(data)=>{
    setLoading(true)
    axios
      .post(server,{
        
      })
      .then((res)=>{
      console.log("성공여부",res);
      })
      .catch((error)=>{
        console.log("error ",error);
      })   
  }
  //axios get
  const getdata=async(data)=>{
    setLoading(true)
    axios
      .get(server,{
        
      })
      .then((res)=>{
      console.log("성공여부",res);
      })
      .catch((error)=>{
        console.log("error ",error);
      })   
  }

  return (
    <>
    <div className="header">
      <img src={image1} title='logo'/>
      <nav>
        <ul>
          <li className="active"><a href="">Home</a></li>
          <li><a href='/mypage'>MyPage</a></li>
          <li><a href='#'>Logout</a></li>
        </ul>
      </nav>
    </div>
    {
      /*
      
    
    <div className="menu-bar">
      <div className="menu1">
        <button onClick={handleClick}>Menu</button>
      </div>
      <div className="menu2" onClick={handleClick}>
        <button>my page</button>
        <button>setting</button>
      </div>
    </div>
    {click?<Appbar click={click}/>:""}
      */
    }
      <h2>Search</h2>
    <form onSubmit={e=>onSearch(e)}>
      <input type="text" value={search} placeholder="검색어를 입력하세요" onChange={onChangeSearch}/>
      <button type="submit">검색</button>
    </form>
    <Link style={{float:'right'}} to="/ProjectPage">ADD Project</Link>
    
    {/*ADD Project 모달 */}
    <button onClick={openModal}>Modal</button>
    <Modal open={modalOpen} close={closeModal} header="Modal heading" postdata={postdata}>
      <label>projectName</label>
      <br/>
      <input type="text"/>
      <br/>
      <label>projectDetails</label>
      <br/>
      <input type="text"/>
    </Modal>
    
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
    
    </>
  )
}

export default LandingPage
