import Header from "./Header";
import React from "react";
import axios from "axios";
import {useHistory} from "react-router-dom";


/**
 * Function responsible for displaying and handling Token Validation/Password change requests.
 */
const TokenHandler = () => {
    var pathArray = window.location.pathname.split('/');
    var token = pathArray[2];
    const history = useHistory();

    const verify = () => {
        axios.put('http://localhost:8080/Assignment1_war/token?type=verify&token='+token).then((e) => {
            console.log(e);
            alert("Thank you, login to enjoy polls!");
            history.push("/pollmanagerlogin");
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
            <h2>Please click the verify button to verify your account!</h2>
            <button type="submit" onClick={verify} style={{margin: '8px', padding: '20px'}}>Verify</button>
        </div>
    )
}

export default TokenHandler;