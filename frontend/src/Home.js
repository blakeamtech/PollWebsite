import React, {useState, useEffect, useRef} from "react";
import VotingPage from './VotingPage';
import ViewPollResults from "./ViewPollResults";
import axios from "axios";
import './Home.css';
import WaitingPage from "./WaitingPage";

// Responsible for keeping track of the poll information (title, question, choices, etc.)
const Home = () => {
    const [pollState, setPollState] = useState("closed");
    const [poll, setPoll] = useState();
    const [choices, setChoices] = useState([]);
    const [title, setTitle] = useState("");
    const [question, setQuestion] = useState("");
    const [choicesCount, setChoicesCount] = useState([]);

    // interval to poll backend for poll status update every X seconds
    const getPollState = () => {
        // retrieve backend poll state and set pollState
        axios.get('http://localhost:8080/state', )
        .then(function (response) {
            setPoll(response.data);
            setPollState(response.data.state);
            setChoices(response.data.choices);
            setTitle(response.data.title);
            setQuestion(response.data.question);
            handleResults();
            console.log(response.data);
        })
        .catch(function (error) {
            console.log(error);
        });
    }

    // interval to poll backend for poll status update every X seconds
    // https://stackoverflow.com/questions/46140764/polling-api-every-x-seconds-with-react
    const useInterval = (callback, delay) => {
        const savedCallback = useRef();

        useEffect(() => {
          savedCallback.current = callback;
        }, [callback]);


        useEffect(() => {
          function tick() {
            savedCallback.current();
          }
          if (delay !== null) {
            const id = setInterval(tick, delay);
            return () => clearInterval(id);
          }
        }, [delay]);
      }

      useInterval(() => {
        getPollState();
        }, 1000 * 5);


     // Responsible for getting the poll result data needed for the PieChart.
    const handleResults = () => {
        axios.get('http://localhost:8080/results').then(function (response) {
            let res = response.data;
            // convert object into array
            let choiceList = Object.keys(res).map((key) => [key, parseInt(res[key])]);
            setChoicesCount(choiceList);
        }).catch(function (error) {
            console.log(error);
        });
    }

    // Responsible for making a request to search for the given poll id and PIN#
    const handleSearch = (obj) => {
        axios.post('http://localhost:8080/search', obj).then(function (response) {
            console.log(response);
        }).catch(function (error) {
            console.log(error);
            //alert("Poll Search Failed.");
        });
    }

    // Will look for the entered poll information and user choice if a PIN# was entered.
    const searchPoll = (e) => {
        e.preventDefault();
        let elements = e.target.elements;
        let obj = {};
        obj["pinNum"] = "";
        obj["pollId"] = "";

        for(var i = 0 ; i < elements.length - 1; i++) {
            var item = elements.item(i);
            if (item.name === "pinNum" && item.value !== "") {
                obj["pinNum"] = item.value;
                console.log("FOUND PIN: " + item.value);
            }
            else if (item.name === "pollId" && item.value !== "" ) {
                obj["pollId"] = item.value;
                console.log("Poll Id: " + item.value);
            }
            else {
                alert("Please enter a valid Poll ID and PIN#!");
                break;
            }
        }
        handleSearch(obj)
        console.log(obj)
    }

    // React code for rendering the body.
    // Contains the voting page, a waiting page, a link to download a file as well as the piechart.
    const renderBody = () => {
        switch (pollState) {
            case "running":
                return <VotingPage question={question} title={title} choices={choices} poll={poll} pollState={pollState}/>
            case "released":
                return (
                    <div>
                        <a href='http://localhost:8080/details?choice=TEXT' download>TEXT Results</a><br/>
                        <a href='http://localhost:8080/details?choice=JSON' download>JSON Results</a><br/>
                        <a href='http://localhost:8080/details?choice=XML' download>XML Results</a><br/>
                        <div className="centering">
                            <ViewPollResults question={question} title={title} choices={choices} poll={poll} pollState={pollState} choicesCount={choicesCount}/>
                        </div>
                    </div>
                )
            case "created":
                return <WaitingPage />
            default:
                return <WaitingPage />
        }
    }

    /**
     * Responsible for rendering tags for use in react methods.
     * @returns {JSX.Element}
     */
    return (
        <div>
            <div id="pollText">
                <form onSubmit={searchPoll}>
                    <label htmlFor="pollId">Enter a poll ID:</label><br/>
                    <input type="text" id="pollId" name="pollId"/><br/>
                    <label htmlFor="pinNum">Enter a given PIN#:</label><br/>
                    <input type="text" id="pinNum" name="pinNum"/><br/>
                    <input id="subBtn" type="submit" value="Submit"/>
                </form>
            </div>
            <h1>THE GREATEST POLL OF ALL TIME.</h1>
            {
                renderBody()
            }
        </div>
    )
}

export default Home;
