import React, {useState, useEffect, useRef} from "react";
import VotingPage from './VotingPage';
import ViewPollResults from "./ViewPollResults";
import axios from "axios";


const Home = () => {
    const [pollState, setPollState] = useState("released");
    const [pollUpdate, setPollUpdate] = useState(0);
    const [poll, setPoll] = useState();
    const [choices, setChoices] = useState([]);
    const [title, setTitle] = useState("");
    const [question, setQuestion] = useState("");

    // useEffect(() => {
    //     // retrieve latest state from backend
    //     setInterval(() => getPollState(), pollUpdate)
    // }, []);

    // interval to poll backend for poll status update every X seconds
    const getPollState = () => {
        // retrieve backend poll state and set pollState
        axios.get('http://localhost:8080/state')
        .then(function (response) {
            setPoll(response.data);
            setPollState(response.data.state);
            setChoices(response.data.choices);
            setTitle(response.data.title);
            setQuestion(response.data.question);
            console.log(response.data);
        })
        .catch(function (error) {
            console.log(error);
        });
        //
        //setPollUpdate(20);
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
        }, 1000 * 30);

    const renderBody = () => {
        switch (pollState) {
            case "running":
                return <VotingPage question={question} title={title} choices={choices} poll={poll} pollState={pollState}/>
            case "released":
                return <ViewPollResults question={question} title={title} choices={choices} poll={poll} pollState={pollState}/>
            case "created":
                // return waiting page
            default:
                // return waiting page
        }
    }

    return (
        <div>
            <h1>THE GREATEST POLL OF ALL TIME.</h1>
            <h2>{title}</h2>
            {
                renderBody()
            }
        </div>
    )
}

export default Home;
