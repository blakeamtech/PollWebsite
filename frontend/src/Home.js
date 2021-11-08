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

    // React code for rendering the body.
    // Contains the voting page, a waiting page, a link to download a file as well as the piechart.
    const renderBody = () => {
        switch (pollState) {
            case "running":
                return <VotingPage question={question} title={title} choices={choices} poll={poll} pollState={pollState}/>
            case "released":
                return (
                    <div>
                        <a href='http://localhost:8080/details' download>Download Results</a>
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
            <h1>THE GREATEST POLL OF ALL TIME.</h1>
            {
                renderBody()
            }
        </div>
    )
}

export default Home;
