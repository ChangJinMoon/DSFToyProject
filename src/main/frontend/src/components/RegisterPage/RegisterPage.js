import React, {useRef, useState } from 'react'
import { Link } from 'react-router-dom';
import { useForm } from 'react-hook-form';
import axios from "axios";

const server="/join";

function RegisterPage() {
  const { register, watch, formState: {errors}, handleSubmit } = useForm();
  const [errorFromSubmit, setErrorFromSubmit]=useState("");
  const [loading, setLoading] =useState(false);

  const password = useRef();
  password.current = watch("password");

  const onSubmit=async(data)=>{
    console.log(data.email)
    //try{
      setLoading(true)
      axios
      .post(server,{
        email:data.email,
        name:data.name,
        password:data.password,
        findPasswordAnswer:data.findPasswordAnswer,
      })
    .then((res)=>{
      console.log(res);
    })
    .catch((error)=>{
      console.log("error ",error);
    })
    //}
    //catch{

   // }
    
  }
  return (
    <div className="auth-wrapper">
      <div style={{textAlign:'center'}}>
        <h3>회원가입</h3>
      </div>
      <form onSubmit={handleSubmit(onSubmit)}>
        <label>Email</label>
        <input name="email" type="email" {...register("email", {required: true, pattern: /^\S+@\S+$/i})}/>
        {errors.email && <p>This email field is required</p>}

        <label>Name</label>
        <input name="name" {...register("name",{required:true, maxLength:10})}/>
        {errors.name && errors.name.type === "required"
          && <p>This name field is required</p>}
        {errors.name && errors.name.type === "maxLength"
          && <p>Your input exceed maxinum length</p>}
                
        <label>Password</label>
        <input name="password" type="password" {...register("password", {required:true, pattern: /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,16}$/})}/>
        {errors.password && errors.password.type === "required" && <p>This name field is required</p>}
        {errors.password && errors.password.type === "pattern" && <p>최소 8자에서 최대 16자, 최소 하나의 문자, 하나의 숫자 및 하나의 특수 문자 사용</p>}
                
        <label>findPasswordAnswer</label>
        <input name="findPasswordAnswer"  {...register("findPasswordAnswer",{required:true})}/>
        {errors.findPasswordAnswer && <p>This field is required</p>}

        {errorFromSubmit&&<p>{errorFromSubmit}</p>}

        <input type="submit"/>
        <Link style={{color:'gray', textDecoration:'none'}} to="/LoginPage">로그인하기</Link>
      </form>
    </div>
  )
}

export default RegisterPage
