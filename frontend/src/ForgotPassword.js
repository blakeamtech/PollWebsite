import axios from "axios";
import Header from "./Header";

/**
 * Function responsible for displaying and handling Sign Up requests.
 */
const ForgotPassword = () => {

    const forgot = (e) => {
        e.preventDefault();
        let elements = e.target.elements;
        const email = elements[0].value;

        // data to be sent
        let obj = {
            "emailAddress": email
        };

        axios.post('http://localhost:8080/Assignment1_war/forgot', obj).then((e) => {
            console.log(e);
            alert("An email has been sent to you, please click the link provided to change your password!");
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
            <h1>Forgot Password</h1>
            <form onSubmit={(e) => forgot(e)}>
                <label htmlFor="email">&nbsp;&nbsp; Email: </label>
                <input type="email" id="email" required/><br/>
                <button type="submit" style={{margin: '8px', padding: '10px'}}>Send Request</button>
            </form>
        </div>
    );
}

export default ForgotPassword;