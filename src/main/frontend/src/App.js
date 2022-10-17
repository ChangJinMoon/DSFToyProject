import React, { useEffect, useState } from "react";
import {
  BrowserRouter,
  Routes,
  Route,
  Link,
  Navigate,
  useNavigate,
} from "react-router-dom";
import LandingPage from "./components/LandingPage/LandingPage";
import LoginPage from "./components/LoginPage/LoginPage";
import RegisterPage from "./components/RegisterPage/RegisterPage";
import ProjectPage from "./components/ProjectPage/ProjectPage";
import CheckPage from "./CheckPage/CheckPage";
import SprintPage from "./components/SprintPage/SprintPage";
import PersonalProject from "./components/PersonalPage/PersonalProject";
import EditPage from "./components/PersonalPage/EditPage";
import Sprint from "./Sprint/Sprint";
import { useDispatch, useSelector } from "react-redux";

function App() {
  const navigate = useNavigate();
  let dispatch = useDispatch();
  const isLogin = useSelector((state) => state.user.isLogin);

  useEffect(() => {}, []);

  return (
    <Routes>
      <Route path="/" element={<LoginPage />} />
      <Route path="/LandingPage" element={<LandingPage />} />
      <Route path="/RegisterPage" element={<RegisterPage />} />
      <Route path="/ProjectPage" element={<ProjectPage />} />
      <Route path="/SprintPage/:projectId" element={<SprintPage />} />
      <Route path="/CheckPage" element={<CheckPage />} />
      <Route path="/PersonalProject/:projectId" element={<PersonalProject />} />
      <Route path="/EditPage/:projectId" element={<EditPage />} />
      <Route path="/Sprint/:sprintId" element={<Sprint />} />
    </Routes>
  );
}

export default App;
