import React, {useEffect} from "react";
import {BrowserRouter, Routes, Route} from "react-router-dom";
import './App.css';
import MainWindow from "./components/MainWindow/MainWindow";
import Login from "./components/Header/Login/Login";
import Moving from "./components/Header/Moving/Moving";

function App() {
  return (
      <BrowserRouter>
            <Routes>
              <Route path="/" element={<MainWindow/>}/>
              <Route path="/signin" element={<Login/>}/>
              <Route path="/moving" element={<Moving/>}/>
            </Routes>
      </BrowserRouter>
  );
}

export default App;
