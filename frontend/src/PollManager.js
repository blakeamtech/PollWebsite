import {React, useEffect, useState} from "react";
import './PollManager.css';
import axios from "axios";
import {Link} from "react-router-dom";
import Header from "./Header";

/**
 * Function responsible for displaying and handling Poll Manager requests.
 */
const PollManager = () => {
    const [polls, setPolls] = useState([]);
    const [chosenPoll, setChosenPoll] = useState("");
    const [username, setUsername] = useState(localStorage.getItem("username"));
    const [chosenMessage, setChosenMessage] = useState("");

    /**
     * Function responsible for sending a request to clear the poll.
     */
    const handleClear = () => {
        if (chosenPoll === "" || username === "none") {
            alert("Choose a poll first!");
            return;
        }
        axios.put(`http://localhost:8080/clear?username=${username}&id=${chosenPoll}`)
            .then(function (response) {
                console.log(response);

                alert("Clear Successful.");
            })
            .catch(function (error) {
                console.log(error);
                alert("Clear Failed.");
            });
    };

    /**
     * Function responsible for sending a request to close the poll.
     */
    const handleClose = () => {
        if (chosenPoll === "" || username === "none") {
            alert("Choose a poll first!");
            return;
        }
        axios.put(`http://localhost:8080/close?username=${username}&id=${chosenPoll}`)
            .then(function (response) {
                console.log(response);

                alert("Close Successful.");
            })
            .catch(function (error) {
                console.log(error);

                alert("Close Failed.");
            });
    }

    /**
     * Function responsible for sending a request to run the poll.
     */
    const handleRun = () => {
        if (chosenPoll === "" || username === "none") {
            alert("Choose a poll first!");
            return;
        }
        axios.put(`http://localhost:8080/run?username=${username}&id=${chosenPoll}`)
            .then(function (response) {
                console.log(response);

                alert("Run Successful.");
            })
            .catch(function (error) {
                console.log(error);

                alert("Run Failed.");
            });
    }

    /**
     * Function responsible for sending a request to release the poll.
     */
    const handleRelease = () => {
        if (chosenPoll === "" || username === "none") {
            alert("Choose a poll first!");
            return;
        }
        axios.put(`http://localhost:8080/release?username=${username}&id=${chosenPoll}`)
            .then(function (response) {
                console.log(response);

                alert("Release Successful.");
            })
            .catch(function (error) {
                console.log(error);

                alert("Release Failed.");
            });
    }

    /**
     * Function responsible for sending a request to unrelease the poll.
     */
    const handleUnrelease = () => {
        if (chosenPoll === "" || username === "none") {
            alert("Choose a poll first!");
            return;
        }
        axios.put(`http://localhost:8080/unrelease?username=${username}&id=${chosenPoll}`)
            .then(function (response) {
                console.log(response);

                alert("Unrelease Successful.");
            })
            .catch(function (error) {
                console.log(error);
                alert("Unrelease Failed.");
            });
    }

    // Format: one attribute polls which is an array of objects. The objects are of format {pollid : question}
    let mockPolls = {
        "polls": [
            ["1234567890", "Yes or No?"],
            ["827364AIZS", "Where is Kappa?"]
        ]
    }
    /**
     * - Should return all polls
     * - User can only delete or close the ones he created
     * - What about clear, update, release, run and unrelease?
     *      - If they are also creator only, then send username along with request and only retrieve those associated with creator.
     */
    const fetchPolls = () => {
        axios.get(`http://localhost:8080/polls?username=${username}`)
            .then((res) => {
                console.log(res);
                setPolls(res.polls);
            })
            .catch(function (error) {
                console.log(error);
                alert("Poll fetching failed.");
            });
    }

    const mockFetchPolls = () => {
        setPolls(mockPolls.polls);
    }

    const choosePoll = (id, question) => {
        setChosenPoll(id);
        setChosenMessage(question);
    }

    const renderPolls = () => {
        if (polls.length === 0) {
            return (
                <h3>There are no created polls.</h3>
            )
        }
        // each poll clicked will set chosenPoll to that poll's id.
        const list = polls.map((poll) => <a href="javascript:void(0);"><li tabindex="1" key={poll[0]} onClick={() => choosePoll(poll[0], poll[1])}>{poll[1]}</li></a>);
        return (
            <ol>
                {list}
            </ol>
        )
    }

    useEffect(() => {
        mockFetchPolls();
    }, []);

    const canUpdate = (e) => {
        if (chosenPoll === "") {
            alert("Choose a poll first!");
            e.preventDefault();
        }
    }

    /***
     * Function responsible for rendering tags for use in react methods.
     * @returns {JSX.Element}
     */
        return (
            <div>
                <Header />
                <div className="Manager">
                    <header className="center">
                        <Link className="button-pollmanager" to="/createpoll">Create Poll</Link>
                        <Link className="button-pollmanager" to={{ pathname: "/updatepoll", state:{pollId: chosenPoll} }} onClick={canUpdate}>Update Poll</Link>
                        <button type="button" className="button-pollmanager" onClick={handleClear}>Clear Poll</button><br/>
                        <button type="button" className="button-pollmanager" onClick={handleClose}>Close Poll</button>
                        <button type="button" className="button-pollmanager" onClick={handleRun}>Run Poll</button>
                        <button type="button" className="button-pollmanager" onClick={handleRelease}>Release Poll</button><br/>
                        <button type="button" className="button-pollmanager" onClick={handleUnrelease}>Unrelease Poll</button>
                    </header>
                </div>
                {chosenMessage !== "" && <h3>You chose poll "{chosenMessage}"</h3>}
                {chosenPoll !== "" && <h3>Poll ID: {chosenPoll}</h3>}
                {renderPolls()}
            </div>
        );
}


export default PollManager;
