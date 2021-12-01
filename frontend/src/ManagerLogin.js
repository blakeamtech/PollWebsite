import React, {useState} from "react";
import './CreatePoll.css';
import {Link, useHistory} from 'react-router-dom';
import axios from "axios";
import Header from "./Header";

// Responsible for handling the Manager Login page, where the poll manager must enter the correct password to enter.
const ManagerLogin = (props) => {
    const history = useHistory();

    // Check if the user entered the password "123" to confirm they are a poll manager.
    // const mockAuthenticate = (e) => {
    //     e.preventDefault();
    //     let elements = e.target.elements;
    //     const email = elements[0].value;
    //     const pass = elements[1].value;

    //     if ((email === "shadow@gmail.com" && pass === "123") || 
    //          (email === "alex@gmail.com" && pass === "abc") ||
    //          (email === "pan@gmail.com" && pass === "qwe")) {
    //         history.push("/");

    //         localStorage.setItem("email", email);
    //     }
    //     else {
    //         alert("Wrong email/passcode!");
    //     }
    // }

    const authenticate = (e) => {
        e.preventDefault();
        let elements = e.target.elements;
        const email = elements[0].value;
        const pass = elements[1].value;

        // data to be sent
        let obj = {
            "emailAddress": email,
            "hashedPassword": pass
        };

        console.log("OBJECT HERE:", obj);

        axios.post('http://localhost:8080/Assignment1_war/authentication', obj).then((e) => {
            console.log(e);
            localStorage.setItem("email", email);
            history.push("/pollmanager");
        }).catch((error) => {
            console.log(error);
            alert("Incorrect email/password.");
        });
    }

    /**
     * Responsible for rendering tags for use in react methods.
     * @returns {JSX.Element}
     */
    return (
        <div>
            <Header />
            <h1>Login</h1>
            <form onSubmit={(e) => authenticate(e)}>
                <label htmlFor="email">&nbsp;&nbsp; Email: </label>
                <input type="email" id="email"/><br/>
                <label htmlFor="passcode">Passcode: </label>
                <input type="password" id="passcode"/><br/>
                <button type="submit" style={{margin: '8px', padding: '10px'}}>Login</button>
            </form>
            <Link to="/forgotpassword">Forgot Password</Link>
        </div>
    );
}

export default ManagerLogin;