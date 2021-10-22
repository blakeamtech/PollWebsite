import React, {useState} from "react";
import './CreatePoll.css';
import { useHistory } from 'react-router-dom';

const ManagerLogin = (props) => {
    const history = useHistory();

    const [code, setCode] = useState();

    const checkCode = () => {
        if (code === "123") {
            history.push("/pollmanager");
        }
        else {
            alert("Wrong passcode!");
        }
    }

    return (
        <div>
            <h1>Login as Poll Manager</h1>
            <label htmlFor="passcode" style={{margin: '5px'}}>Passcode: </label>
            <input type="password" onChange={e => setCode(e.target.value)}></input>
            <button type="button" onClick={checkCode} style={{margin: '5px'}}>Login</button>
        </div>
    );
}

export default ManagerLogin;