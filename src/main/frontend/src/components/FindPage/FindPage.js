import React, {useRef, useState } from 'react'
import { Link } from 'react-router-dom';
import { useForm } from 'react-hook-form';
import axios from "axios";

const server="/find-password";
function FindPage() {
  const { register, watch, formState:{errors},handleSubmit}=useForm();
  const [errorFromSubmit, setErrorFromSubmit]=useState("");
  const [loading, setLoading] =useState(false);

  const onSubmit=async(data)=>{
    console.log(data.email)
    try{
      setLoading(true)
      axios
      .post(server,{
        email:data.email,
        password:data.password,
      })
    .then({

    })
    .catch({

    })
    }
    catch{

    }
    
  }

  return (
    <div className="auth-wrapper">
      <div style={{textAlign:'center'}}>
        <h3>회원정보 찾기</h3>
      </div>
      <form onSubmit={handleSubmit(onSubmit)}>
        <label>Email</label>
        <input name="email" type="email" {...register("email",{required:true, pattern: /^\S+@\S+$/i})}/>
        {errors.email && <p>This email field is required</p>}

        <label>findPasswordAnswer</label>
        <input name="findPasswordAnswer"  {...register("findPasswordAnswer",{required:true})}/>
        {errors.findPasswordAnswer && <p>This field is required</p>}
        <input type="submit"/>
        <Link style={{color:'gray', textDecoration:'none'}} to="/LoginPage">로그인하기</Link>
      </form>

    </div>
  )
}

export default FindPage
