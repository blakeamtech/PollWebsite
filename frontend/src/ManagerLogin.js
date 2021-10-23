import React, {useState} from "react";
import './CreatePoll.css';
import { useHistory } from 'react-router-dom';


// Responsible for handling the Manager Login page, where the poll manager must enter the correct password to enter.
const ManagerLogin = (props) => {
    const history = useHistory();
    const [code, setCode] = useState();

    // Check if the user entered the password "123" to confirm they are a poll manager.
    const checkCode = () => {
        if (code === "123") {
            history.push("/pollmanager");
        }
        else {
            alert("Wrong passcode!");
        }
    }

    /**
     * Responsible for rendering tags for use in react methods.
     * @returns {JSX.Element}
     */
    return (
        <div>
            <h1>Login as Poll Manager</h1>
            <label htmlFor="passcode" style={{margin: '5px'}}>Passcode: </label>
            <input type="password" onChange={e => setCode(e.target.value)}/>
            <button type="button" onClick={checkCode} style={{margin: '5px'}}>Login</button>
        </div>
    );
}

export default ManagerLogin;