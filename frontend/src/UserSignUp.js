import Header from "./Header";
import React from "react";
import axios from "axios";
import {useHistory} from "react-router-dom";

/**
 * Function responsible for displaying and handling Sign Up requests.
 */
const UserSignUp = () => {

    const signUp = (e) => {
        e.preventDefault();
        let elements = e.target.elements;
        const email = elements[0].value;
        const name = elements[1].value;
        const pass = elements[2].value;

        // data to be sent
        let obj = {
            "emailAddress": email,
            "hashedPassword": pass,
            "fullName": name
        };

        console.log("OBJECT HERE:", obj);

        axios.post('http://localhost:8080/Assignment1_war/signup', obj).then((e) => {
            console.log(e);
            alert("An email has been sent to you, please click the link provided to verify your account!");
        }).catch((error) => {
            console.log(error);
        });
    }

    /**
     * Responsible for rendering tags for use in react methods.
     * @returns {JSX.Element}
     */
    return (
        <div>
            <Header />
            <h1>Sign Up</h1>
            <form onSubmit={(e) => signUp(e)}>
                <label htmlFor="email">&nbsp;&nbsp; Email: </label>
                <input type="email" id="email" required/><br/>

                <label htmlFor="name">Full Name: </label>
                <input type="text" id="name" required/><br/>

                <label htmlFor="passcode">Passcode: </label>
                <input type="password" id="passcode" required/><br/>
                <button type="submit" style={{margin: '8px', padding: '10px'}}>Login</button>
            </form>

        </div>
    )
}

export default UserSignUp;