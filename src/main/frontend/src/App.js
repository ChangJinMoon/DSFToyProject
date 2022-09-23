import React,{useState} from 'react';
import {
  BrowserRouter,
  Routes,
  Route,
  Link,
  Navigate,
}from "react-router-dom";
import LandingPage from './components/LandingPage/LandingPage';
import LoginPage from './components/LoginPage/LoginPage';
import RegisterPage from './components/RegisterPage/RegisterPage';
import ProjectPage from './components/ProjectPage/ProjectPage';
import CheckPage from './CheckPage/CheckPage';
import SprintPage from './components/SprintPage/SprintPage';

const App=()=> {
  
  return (
        <Routes>
          <Route path="/" element={<LoginPage/>}/>
          <Route path="/LandingPage" element={<LandingPage/>}/>
          <Route path="/RegisterPage" element={<RegisterPage/>}/>
          <Route path="/ProjectPage" element={<ProjectPage/>}/>
          <Route path="/SprintPage" element={<SprintPage/>}/>
          <Route path="/CheckPage" element={<CheckPage/>}/>
        </Routes>
  );
}

export default App;
