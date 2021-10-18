import React, {useState, useEffect} from "react";
import './Home.css';
import axios from "axios";


const Home = () => {
    const [name, setName] = useState("The Greatest Poll Ever");
    const [question, setQuestion] = useState("No active poll.");
    const [choices, setChoices] = useState([]);
    // stores index of currently chosen answer to avoid repeated increments
    const [chosenAnswer, setChosenAnswer] = useState("");
    const [showMessage, setShowMessage] = useState(false);

    // MOCK - format: Choice, nbVotes
    const pollAnswers = [
        [ 'A', 20 ],
        [ 'B', 2 ],
        [ 'C', 3 ],
        [ 'D', 0 ]
    ]

    // only activates on first load
    useEffect(() => {
        // need a function to query from back-end for first display
        // set quesiton and choices

        setShowMessage(chosenAnswer);

        //mock
        setQuestion("What grade we gon get kekw");
        setChoices(pollAnswers);
    }, [chosenAnswer]);


    // updates vote on front-end then sends to back-end.
    const handleVote = (e, index) => {
        let voteAnswer = e.target.innerHTML;
        let changed = false;

        // increments the count of input choice
        const newPollAnswers = choices.map(choice => {
            if (choice[0] === voteAnswer && chosenAnswer != voteAnswer) {
                choice[1]++;
                changed = true;
            }
            return choice
        })

        // no need continue if user clicked on an answer he had already chosen
        if (changed) {
            setChosenAnswer(voteAnswer);
            setChoices(newPollAnswers);
            handleVotePoll(voteAnswer);
        }
    }

    /**
     * Function responsible for sending a request when user votes in the poll.
     */
     const handleVotePoll = (answer) => {
        axios.post(`http://localhost:8080/vote&choice=${answer}`)
            .then(function (response) {
                console.log(response);
            })
            .catch(function (error) {
                console.log(error);
            });
    }

    const displayChoices = () => {
        return (
            <ul>
                {
                    choices.map((choice,i) => 
                        <a href="javascript:void(0);">
                            <li tabindex={i} key={i} onClick={e => handleVote(e)}>
                                { choice[0] }
                            </li>
                        </a>
                    )
                }
            </ul>
        )
    }

    return (
        <div>
            <h1>{name}</h1>
            <h2>{question}</h2>
            {
                displayChoices()
            }
            {
                showMessage && <h2 className="chosen">{"You voted for: " + showMessage}</h2>
            }
            <button type="button" className="results-button">View Results</button> <br/>
            <button type="button" className="results-button">Download Results</button>
        </div>
    )
}

export default Home;