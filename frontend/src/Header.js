import {React} from "react";
import {Link} from "react-router-dom";
import "./Header.css";

const Header = (prop) => {

    const logout = () => {
        if (prop.setEmail) {
            prop.setEmail("none");
        }
        console.log("header: ", localStorage.getItem("email"));
        localStorage.setItem("email", "none");
    }

    return(
        <div id="nav-bar">
            <div id="nav-bar-left">
                <Link to="/">Home</Link><br/>
                {localStorage.getItem("email") === "none" ? <Link to="/pollmanagerlogin">Login</Link> : <Link to="/" onClick={logout}>Logout</Link>}
            </div>
            <div id="nav-bar-right">
                {
                 localStorage.getItem("email") === "none" ? <a href="" onClick={() => alert("Sorry, this feature isn't implemented yet!")}>Register</a> : 
                                            <Link to="/pollmanager">Poll Management</Link>
                }
                <br />
            </div>
            <hr />
        </div>
    )
}

export default Header;