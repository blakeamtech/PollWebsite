import React, {useState} from "react";
import './CreatePoll.css';
import { useHistory } from 'react-router-dom';


// Responsible for handling the Manager Login page, where the poll manager must enter the correct password to enter.
const ManagerLogin = (props) => {
    const history = useHistory();

    // Check if the user entered the password "123" to confirm they are a poll manager.
    const authenticate = (e) => {
        e.preventDefault();
        let elements = e.target.elements;
        const username = elements[0].value;
        const pass = elements[1].value;

        if ((username === "shadow" && pass === "123") || 
             (username === "alex" && pass === "abc") ||
             (username === "pan" && pass === "qwe")) {
            history.push("/pollmanager");
        }
        else {
            alert("Wrong username/passcode!");
        }
    }

    /**
     * Responsible for rendering tags for use in react methods.
     * @returns {JSX.Element}
     */
    return (
        <div>
            <h1>Login</h1>
            <form onSubmit={(e) => authenticate(e)}>
                <label htmlFor="username">Username: </label>
                <input type="username" id="username"/><br/>
                <label htmlFor="passcode">Passcode: </label>
                <input type="password" id="passcode"/><br/>
                <button type="submit" style={{margin: '8px', padding: '10px'}}>Login</button>
            </form>
        </div>
    );
}

export default ManagerLogin;