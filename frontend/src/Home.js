import React, {useState, useEffect, useRef} from "react";
import VotingPage from './VotingPage';
import ViewPollResults from "./ViewPollResults";
import axios from "axios";
import './Home.css';
import WaitingPage from "./WaitingPage";
import Header from "./Header";

// Responsible for keeping track of the poll information (title, question, choices, etc.)
const Home = () => {
    const [pollStatus, setPollStatus] = useState("closed");
    const [poll, setPoll] = useState({});
    // pin: 6 digits number
    const [pin, setPin] = useState("");
    const [choicesCount, setChoicesCount] = useState([]);
    const [email, setEmail] = useState("none");

    let mockPoll = {
        "id": "1283ADE870",
        "state": "running",
        "title": "Kappa 123",
        "question": "Yes or No?",
        "choices": [
            {"choice":"yes", "choiceId": 125234},
            {"choice":"no", "choiceId": 897692}
        ],
        "pins": {
            "273648": 0,
            "834294": 0
        },
        "votes": {
            "yes": "1",
            "no": "1"
        }
    };

    useEffect(() => {
        if (localStorage.getItem("email") === null) {
            localStorage.setItem("email", "none");
        }
        else {
            setEmail(localStorage.getItem("email"));
        }
    })

    // interval to poll backend for poll status update every X seconds
    // const getPollState = () => {
    //     // retrieve backend poll state and set pollState
    //     axios.get('http://localhost:8080/Assignment1_war/state', )
    //     .then(function (response) {
    //         setPoll(response.data);
    //         setPollState(response.data.state);
    //         setChoices(response.data.choices);
    //         setTitle(response.data.title);
    //         setQuestion(response.data.question);
    //         handleResults();
    //         console.log(response.data);
    //     })
    //     .catch(function (error) {
    //         console.log(error);
    //     });
    // }

    // interval to poll backend for poll status update every X seconds
    // https://stackoverflow.com/questions/46140764/polling-api-every-x-seconds-with-react
    // const useInterval = (callback, delay) => {
    //     const savedCallback = useRef();

    //     useEffect(() => {
    //       savedCallback.current = callback;
    //     }, [callback]);


    //     useEffect(() => {
    //       function tick() {
    //         savedCallback.current();
    //       }
    //       if (delay !== null) {
    //         const id = setInterval(tick, delay);
    //         return () => clearInterval(id);
    //       }
    //     }, [delay]);
    //   }

    //   useInterval(() => {
    //     getPollState();
    //     }, 1000 * 5);


    //  // Responsible for getting the poll result data needed for the PieChart.
    // const handleResults = () => {
    //     axios.get('http://localhost:8080/Assignment1_war/results').then(function (response) {
    //         let res = response.data;
    //         // convert object into array
    //         let choiceList = Object.keys(res).map((key) => [key, parseInt(res[key])]);
    //         setChoicesCount(choiceList);
    //     }).catch(function (error) {
    //         console.log(error);
    //     });
    // }

    // convert object to array for results view
    const formatVotes = (votes) => {
            let res = votes;
            // convert object into array
            let choiceList = Object.keys(res).map((key) => [key, res[key]]);
            setChoicesCount(choiceList);
    }

    // Responsible for making a request to search for the given poll id and PIN#
    const handlePollSearch = (obj) => {
        axios.get(`http://localhost:8080/Assignment1_war/state?id=${obj["id"]}`).then(function (response) {
            /**
             * - maybe don't need to send pin here
             */
             console.log('HEREEEEEEEEEE0000000');
            let newPoll = response.data
            console.log(newPoll);
            setPollStatus(newPoll.state);
            setPoll(newPoll);
            setPin(obj["pin"]);
            formatVotes(newPoll.votes);
            console.log(response);

        }).catch(function (error) {
            console.log('HEREEEEEEEEEE');
            console.log(error);
            setPoll({});
            setPin("");
            setPollStatus("closed");
            alert("Poll Search Failed. Incorrect ID or PIN.");
        });
    }

    // const mockHandlePollSearch = (obj) => {
    //     setPollStatus(mockPoll.state);
    //     setPoll(mockPoll);
    //     setPin(obj["pin"]);
    //     formatVotes(mockPoll.votes);
    // }

    // Will look for the entered poll information and user choice if a PIN# was entered.
    const searchPoll = (e) => {
        e.preventDefault();
        let elements = e.target.elements;
        let obj = {};
        obj["id"] = elements[0].value;
        obj["pin"] = elements[1].value;

        if (obj["id"].length !== 10) {
            alert("Please enter a valid poll ID.");
            return;
        }
        else if (obj["pin"].length !== 6 && obj["pin"].length !== 0) {
            alert("Please enter a valid PIN (6-digits).")
            return;
        }

        console.log(obj);
        //mockHandlePollSearch(obj);
        handlePollSearch(obj);
    }
    
    // Generate new 6 digit pin if none entered. Checks if already exist for given poll.
    const generatePin = () => {
        let doesntExist = true;

        let newPin = Math.floor(100000 + Math.random() * 900000).toString();
        while (doesntExist) {
            if (!(newPin in poll.pins)) {
                setPin(newPin);
                break;
            }

            newPin = Math.floor(100000 + Math.random() * 900000);
        }
    }

    // const mockGeneratePin = () => {
    //     let doesntExist = true;

    //     let newPin = Math.floor(100000 + Math.random() * 900000);
    //     while (doesntExist) {
    //         if (!(newPin in mockPoll.pins)) {
    //             setPin(newPin);
    //             break;
    //         }

    //         newPin = Math.floor(100000 + Math.random() * 900000);
    //     }
    // }

    // React code for rendering the body.
    // Contains the voting page, a waiting page, a link to download a file as well as the piechart.
    const renderBody = () => {
        console.log(pollStatus);
        switch (pollStatus) {
            case "running":
                if (pin && pin.length === 0) {
                    generatePin();
                }
                return <VotingPage id={poll.id} pin={pin} pins={poll.pins} question={poll.question} title={poll.title} choices={poll.choices} poll={poll.poll} pollState={poll.state}/>
            case "released":
                return (
                    <div>
                        <a href='http://localhost:8080/Assignment1_war/details?choice=TEXT' download>TEXT Results</a><br/>
                        <a href='http://localhost:8080/Assignment1_war/details?choice=JSON' download>JSON Results</a><br/>
                        <a href='http://localhost:8080/Assignment1_war/details?choice=XML' download>XML Results</a><br/>
                        <div className="centering">
                            <ViewPollResults question={poll.question} title={poll.title} choices={poll.choices} pollState={poll.state} choicesCount={choicesCount}/>
                        </div>
                    </div>
                )
            default:
                console.log("hererere");
                return <WaitingPage pollStatus={pollStatus}/>
        }
    }

    /**
     * Responsible for rendering tags for use in react methods.
     * @returns {JSX.Element}
     */
    return (
        <div>
            <Header setEmail={setEmail}/>
            <div id="pollText">
            <h1>THE GREATEST POLL OF ALL TIME.</h1>
                <form onSubmit={searchPoll}>
                    <label htmlFor="pollId">Enter a poll ID:</label><br/>
                    <input type="text" id="pollId" name="pollId"/><br/>
                    <label htmlFor="pinNum">Enter a given PIN#:</label><br/>
                    <input type="text" id="pin" name="pin" placeholder="Optional"/><br/>
                    <input id="subBtn" type="submit" value="Go!"/>
                </form>
            </div>
            { renderBody() }
        </div>
    )
}

export default Home;
