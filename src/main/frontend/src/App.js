import React from 'react';
import {
  BrowserRouter,
  Routes,
  Route,
  Link,
}from "react-router-dom";
import LandingPage from './components/LandingPage/LandingPage';
import LoginPage from './components/LoginPage/LoginPage';
import RegisterPage from './components/RegisterPage/RegisterPage';
import FindPage from './components/FindPage/FindPage';
import Auth from './auth';

function App() {
  return (

        <Routes>
          <Route path="/" element={<LandingPage/>}/>
          <Route path="/LoginPage" element={<LoginPage/>}/>
          <Route path="/RegisterPage" element={<RegisterPage/>}/>
          <Route path="/FindPage" element={<FindPage/>}/>
        </Routes>

  );
}

export default App;
