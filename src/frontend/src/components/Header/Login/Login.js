import React, {useEffect, useState} from "react";
import axios from "axios";
import l from './Login.module.css'
import {useNavigate} from "react-router-dom";

const Login = () => {
    const navigate = useNavigate();
    const [username, setUsername] = useState();
    const [password, setPassword] = useState();

    const [usernameDirty, setUsernameDirty] = useState(false);
    const [passwordDirty, setPasswordDirty] = useState(false);

    const [usernameError, setUsernameError] = useState('Username cannot be null or empty.');
    const [passwordError, setPasswordError] = useState('Password cannot be null or empty.');

    const usernameHandler = (e) => {
        setUsername(e.target.value)

        if (e.target.value.length < 1 || e.target.value.length > 33) {
            setUsernameError("Your name must contain" +
                " more than 1 characters and no more than 32 characters.")
            if (!e.target.value) {
                setUsernameError('Name cannot be null or empty.')
            }
        } else {
            setUsernameError("")
        }
    }

    const passwordHandler = (e) => {
        setPassword(e.target.value)

        if (e.target.value.length < 4 || e.target.value.length > 33) {
            setPasswordError("Your password must contain" +
                " more than 4 characters and no more than 32 characters.")
            if (!e.target.value) {
                setPasswordError('Password cannot be null or empty.')
            }
        } else {
            setPasswordError("")
        }
    }

    const blurHandler = (e) => {
        e.preventDefault()

        switch (e.target.name) {
            case 'username':
                setUsernameDirty(true)
                break;
            case 'password':
                setPasswordDirty(true)
                break;
            default:
                break;
        }

        // if (!passwordError && !usernameError) {
        //     setDisable(false);
        // }
    }

    const handleApi = () => {
        axios({
            method: "post",
            url: "http://localhost:8080/api/v1/auth/authenticate",
            data: {
                username: "admin",
                password: "qwerty"
            },
        })
            .then((response) => {
                console.log(response.data)
                navigate("/")
                const token = response.data.token
                localStorage.setItem('token', token);
            })
            .catch(function (error) {
                console.log(error);
            });
    }


    return (
        <div className={l.session}>
            <div className={l.left}></div>

            <div className={l.logIn}>
            </div>
            <div className={l.logIn}>
                <div className={l.divLogIn}>
                    <h4>
                        Log In to
                        <span>IoT</span>
                    </h4>

                    <p>Welcome back! Log in to your account:</p>

                    <div className={l.floatingLabel}>
                        <input value={username}
                               type="text" onBlur={(e) => blurHandler(e)}
                               onChange={(e) => {
                                   usernameHandler(e);
                               }}
                               id="username"
                               name="username"/>
                        <label htmlFor="username">Username:</label>
                        <div className={l.icon}></div>
                    </div>

                    {(usernameDirty && usernameError) &&
                        <label className={l.containSymbol}>
                            {usernameError}
                        </label>
                    }

                    <div className={l.floatingLabel}>
                        <input value={password}
                               type="password" onBlur={(e) => blurHandler(e)}
                               onChange={(e) => {
                                   passwordHandler(e);
                               }}
                               name="password" id="password"
                               autoComplete="off"/>
                        <label htmlFor="password">Password:</label>
                        <div className={l.icon}></div>
                    </div>

                    {(passwordDirty && passwordError) &&
                        <label className={l.containSymbol}>
                            {passwordError}
                        </label>
                    }

                    <button onClick={handleApi} type="submit">
                        Sign in
                    </button>
                </div>
            </div>
        </div>
    )
        ;
}

export default Login;