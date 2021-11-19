import React, {useState, useEffect} from "react";
import './VotingPage.css';
import axios from "axios";


const VotingPage = (props) => {
    // stores index of currently chosen answer to avoid repeated increments
    const [chosenAnswer, setChosenAnswer] = useState("");
    const [showMessage, setShowMessage] = useState(false);

    // only activates on first load
    useEffect(() => {
        // show message if user picked a choice
        setShowMessage(chosenAnswer);
    }, [chosenAnswer]);

    // updates vote on front-end then sends to back-end.
    const handleVote = (e, choiceId) => {
        let voteAnswer = e.target.innerHTML;
        setChosenAnswer(voteAnswer);
        handleVotePoll(voteAnswer, choiceId);
    }

    // Responsible for sending a request when user votes in the poll.
    // Will add the choice to the query string.
     const handleVotePoll = (answer, choiceId) => {
        axios.post(`http://localhost:8080/vote?choice=${answer}&choiceid=${choiceId}&id=${props.id}&pin=${props.pin}`)
            .then(function (response) {
                console.log(response);
            })
            .catch(function (error) {
                console.log(error);
                alert("Vote error!");
            });
    }

    // Dynamically display poll choices.
    const displayChoices = () => {
        return (
            <ul>
                {
                    props.choices.map((choice,i) =>
                        <a key={i} href="javascript:void(0);">
                            <li tabIndex={i} key={choice["choiceId"]} onClick={e => handleVote(e, choice["choiceId"])}>
                                { choice["choice"] }
                            </li>
                        </a>
                    )
                }
            </ul>
        )
    }

    /**
     * Responsible for rendering tags for use in react methods.
     * @returns {JSX.Element}
     */
    return (
        <div>
            <p id="pin-msg">Your PIN# is: {props.pin}</p>
            <h2>{props.question}</h2>
            {
                displayChoices()
            }
            {
                showMessage && <h2 className="chosen">{"You voted for: " + showMessage}</h2>
            }
            {console.log(props.pin)}
        </div>
    )
}

export default VotingPage;
