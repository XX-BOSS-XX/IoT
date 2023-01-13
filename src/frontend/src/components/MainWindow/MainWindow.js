import React, {useState} from "react";
import m from './MainWindow.module.css'
import logo from '../../app/assets/iot-all-white.svg'
import iotAllWhiteHor from '../../app/assets/iot-all-white-hor.svg'
import {NavLink} from "react-router-dom";
import axios from "axios";

const MainWindow = () => {
    const [value, setValue] = useState();
    const [open, setOpen] = useState(false);
    const [close, setClose] = useState(true);
    const [on, setOn] = useState(false);
    const [off, setOff] = useState(true);

    const token = localStorage.getItem("token");

    const handleAllOpen = () => {
        axios({
            method: "GET",
            url: "/api/v1/click/blinds/all/open",
            headers: {"Authorization": `Bearer ${token}`},
            data: {
                open: true,
                close: false
            },
        })
            .then((response) => {
                console.log("blind is open")
            })
            .catch(function (error) {
                console.log(error);
            });
    }

    const handleOn = () => {
        axios({
            method: "GET",
            url: "/api/v1/click/light/on",
            headers: {"Authorization": `Bearer ${token}`},
            data: {
                on: true,
                off: false
            },
        })
            .then((response) => {
                console.log("light is on")
            })
            .catch(function (error) {
                console.log(error);
            });
    }

    const handleOff = () => {
        axios({
            method: "GET",
            url: "/api/v1/click/light/off",
            headers: {"Authorization": `Bearer ${token}`},
            data: {
                on: false,
                off: true
            },
        })
            .then((response) => {
                console.log("light is off")
            })
            .catch(function (error) {
                console.log(error);
            });
    }

    const handleMedium = () => {
        axios({
            method: "GET",
            url: "/api/v1/click/light/medium",
            headers: {"Authorization": `Bearer ${token}`},
            data: {
                on: false,
                off: true
            },
        })
            .then((response) => {
                console.log("light is medium")
            })
            .catch(function (error) {
                console.log(error);
            });
    }

    const handleGetValue = (event) => {
        axios({
            method: "GET",
            url: "/api/v1/click/light/value/" + event.target.value,
            headers: {"Authorization": `Bearer ${token}`},
            data: {
                value: value
            },
        })
            .then((response) => {
                console.log("light value" + event.target.value)
            })
            .catch(function (error) {
                console.log(error);
            });
    }

    const handleAllCLose = () => {
        axios({
            method: "GET",
            url: "/api/v1/click/blinds/all/close",
            headers: {"Authorization": `Bearer ${token}`},
            data: {
                open: false,
                close: true
            },
        })
            .then((response) => {
                console.log("blind is closed")
            })
            .catch(function (error) {
                console.log(error);
            });
    }

    const handleOpen = () => {
        axios({
            method: "GET",
            url: "/api/v1/click/blinds/open",
            headers: {"Authorization": `Bearer ${token}`},
            data: {
                open: true,
                close: false
            },
        })
            .then((response) => {
                console.log("blind 1 is open")
            })
            .catch(function (error) {
                console.log(error);
            });
    }


    return (
        <>
            <header className={m.headerOuter}>
                <div className={m.headerContainer}>
                    <div className={m.headerRow}>
                        <div className={m.headerLogo}>
                    <span className={m.headerLogoLink}>
                        <img src={iotAllWhiteHor} className={m.headerLogoImage} alt="Logo IOT"/>
                    </span>
                        </div>
                    </div>
                </div>
            </header>

            <div className={m.blindsScreen}>
                <div className={m.homeScreenInner}>

                    <div className={m.homeScreenText}>
                        <h2 className={m.homeScreenTitle}>Control your blinds remotely.</h2>
                        <div className={m.homeScreenSubtitle}>
                            <blockquote>
                                <p><q>Umenie človeka v zásade oslobodzuje a odokrýva register možností – ponúka mu
                                    reflexiu –
                                    premýšľanie, zahĺbenie sa a rozjímanie. V konečnom dôsledku sebapoznanie širšie
                                    vnímanie a
                                    hodnotenie okolností a súvislostí.</q></p>
                                <footer>влосивжмловимжвлоаи</footer>
                            </blockquote>
                        </div>

                        <div className={m.homeScreenControl}>
                            <div className={m.controlBlind}>
                                <div className={m.homeScreenDescription}>
                                    <div className={m.titleBlind}></div>
                                    Blind 1
                                </div>
                                <input id="1"/>

                                <div className={m.controlButtons}>
                                    <button className={m.btnOn}
                                            onClick={handleOpen}
                                            >Open</button>
                                    <button className={m.btnOff} id="1">Close</button>
                                </div>

                            </div>

                            <div className={m.controlBlind}>
                                <div className={m.homeScreenDescription}>
                                    <div className={m.titleBlind}></div>
                                    Blind 2
                                    <input id="2"/>
                                </div>

                                <div className={m.controlBlindBar}>
                                </div>

                                <div className={m.controlButtons}>
                                    <button className={m.btnOn} id="2">Open</button>
                                    <button className={m.btnOff} id="2">Close</button>
                                </div>
                            </div>


                            <div className={m.controlBlind}>
                                <div className={m.homeScreenDescription}>
                                    <div className={m.titleBlind}></div>
                                    Blind 3
                                    <input id="3"/>
                                </div>

                                <div className={m.controlBlindBar}>
                                </div>

                                <div className={m.controlButtons}>
                                    <button className={m.btnOn} id="3">Open</button>
                                    <button className={m.btnOff} id="3">Close</button>
                                </div>
                            </div>

                            <div className={m.controlBlind}>
                                <div className={m.homeScreenDescription}>
                                    <div className={m.titleBlind}></div>
                                    Blind 4
                                    <input id="4"/>
                                </div>

                                <div className={m.controlBlindBar}>
                                </div>

                                <div className={m.controlButtons}>
                                    <button className={m.btnOn} id="4">Open</button>
                                    <button className={m.btnOff} id="4">Close</button>
                                </div>
                            </div>


                            <div className={m.controlBlind}>
                                <div className={m.homeScreenDescription}>
                                    <div className={m.titleBlind}></div>
                                    All blinds
                                </div>
                                <div className={m.controlButtons}>

                                    <button className={m.btnOn}
                                            onClick={handleAllOpen}>
                                        Open
                                    </button>
                                    <button className={m.btnOff}
                                            onClick={handleAllCLose}>
                                        Close
                                    </button>
                                </div>
                            </div>


                        </div>
                    </div>
                </div>
            </div>

            <div className={m.lightScreen}>
                <div className={m.homeScreenInner}>

                    <div className={m.homeScreenText}>
                        <h2 className={m.homeScreenTitle}>Control your lights remotely.</h2>
                        <div className={m.homeScreenSubtitle}>
                            <blockquote>
                                <p><q>Umenie človeka v zásade oslobodzuje a odokrýva register možností – ponúka mu
                                    reflexiu –
                                    premýšľanie, zahĺbenie sa a rozjímanie. V konečnom dôsledku sebapoznanie širšie
                                    vnímanie a
                                    hodnotenie okolností a súvislostí.</q></p>
                                <footer>Stanislav Stankoci</footer>
                            </blockquote>
                        </div>

                        <div className={m.controlBlind}>
                            <div className={m.homeScreenDescription}>
                                <div className={m.titleBlind}></div>
                                Light
                            </div>

                            <div className={m.controlBlindBar}>
                                <input value={value} onChange={handleGetValue}/>
                            </div>

                            <div className={m.controlButtons}>
                                <button className={m.btnOn}
                                        onClick={handleOn}>On</button>
                                <button className={m.btnOff} onClick={handleOff}>Off</button>
                                <button className={m.btnOn} onClick={handleMedium}>Medium</button>
                            </div>
                        </div>


                    </div>
                </div>
            </div>
        </>
    )
}

export default MainWindow;