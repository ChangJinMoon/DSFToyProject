import React,{useState, useEffect, useRef} from 'react'
import axios from 'axios';
import { Link } from 'react-router-dom';
import Slider from "react-slick";
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css"
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
function SprintPage() {
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
  const [search,setSearch]=useState("");
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

  return (
    <> 
    <form onSubmit={e=>onSearch(e)}>
      <input type="text" value={search} placeholder="검색어를 입력하세요" onChange={onChangeSearch}/>
      <button type="submit">검색</button>
    </form>
    <Link style={{float:'right'}} to="/">ADD Sprint</Link>
    <Slider {...settings}>
      <div className="card-wrapper">
        <h3>Sprint 1</h3>
        <div className="card-image">
          <img src=""/>
        </div>
        <ul className="social-icons">
          <li><a href="#"><i className="fa fa-facebook"></i></a></li>
          <li><a href="#"><i className="fa fa-"></i></a></li>
          <li><a href="#"><i className="fa fa-"></i></a></li>
          <li><a href="#"><i className="fa fa-"></i></a></li>
        </ul>
        <h3>Sprint 2</h3>
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
        <h3>Sprint 3</h3>
        <div className="card-image">
          <img src=""/>
        </div>
        <ul className="">
          <li><a href="#"><i className="fa fa-"></i></a></li>
          <li><a href="#"><i className="fa fa-"></i></a></li>
          <li><a href="#"><i className="fa fa-"></i></a></li>
          <li><a href="#"><i className="fa fa-"></i></a></li>
        </ul>
        <h3>Sprint 4</h3>
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
        <h3>Sprint 5</h3>
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
        <h3>Sprint 6</h3>
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
        <h3>Sprint 7</h3>
        <div className="card-image">
          <img src=""/>
        </div>
        <ul className="">
          <li><a href="#"><i className="fa fa-"></i></a></li>
          <li><a href="#"><i className="fa fa-"></i></a></li>
          <li><a href="#"><i className="fa fa-"></i></a></li>
          <li><a href="#"><i className="fa fa-"></i></a></li>
        </ul>
        <h3>Sprint 8</h3>
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
      
    </Slider>
    
    
    </>
   
  )
}

export default SprintPage
