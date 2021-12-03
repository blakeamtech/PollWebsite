import React from "react";
import Header from "./Header";
import axios from "axios";
import {useHistory} from "react-router-dom";

/**
 * Function responsible for asking the user for a new password to be changed.
 */
const ChangePassword = () => {
    const history = useHistory();
    let email = localStorage.getItem("email");
    const change = (e) => {
        e.preventDefault();
        let elements = e.target.elements;
        const pass = elements[0].value;

        if (email === "none")
            email = "";

        var pathArray = window.location.pathname.split('/');
        var token = pathArray[2];
        if (token === "none")
            token = "";

        let obj = {
            "emailAddress": email,
            "hashedPassword": pass,
            "token": token
        };

        console.log("Email: " + email);
        console.log("Pass: " + pass);
        console.log("Token: " + token);

        axios.put('http://localhost:8080/Assignment1_war/change', obj)
            .then(function (response) {
                console.log(response);
                console.log(obj);
                alert("Password Updated Successfully.");
                history.push("/pollmanagerlogin");
            })
            .catch(function (error) {
                console.log(error);
                alert("Password Update Failed.");
            });
    }

    /**
     * Responsible for rendering tags for use in react methods.
     * @returns {JSX.Element}
     */
    return (
        <div>
            <Header />
            <h1>Change Password</h1>
            <form onSubmit={(e) => change(e)}>
                <label htmlFor="pass">&nbsp;&nbsp; New Password: </label>
                <input type="password" id="pass"/><br/>
                <button type="submit" style={{margin: '8px', padding: '10px'}} required>Save Password</button>
            </form>
        </div>
    );
}

export default ChangePassword;