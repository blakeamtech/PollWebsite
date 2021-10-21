import './App.css';
import axios from 'axios';
import React, {Component} from "react";
import Poll from 'react-polls';
import {Link} from "react-router-dom";

// Declaring poll question and answers
const pollQuestion = 'What grade will we get?'
const pollAnswers = [
    { option: 'A', votes: 20 },
    { option: 'B', votes: 2 },
    { option: 'C', votes: 3 },
    { option: 'D', votes: 0 }
]

/**
 * Class responsible for displaying and handling user poll requests.
 */
class App extends Component {
    /**
     * Constructor which allows functions to use the "this" keyword.     *
     * @param props
     */
    constructor(props) {
        super(props);
        this.handleDownloadPoll = this.handleDownloadPoll.bind(this);
        this.handleViewPoll = this.handleViewPoll.bind(this);
    }

    /***
     * PROVIDED BY SITE, NEEDS BIG CHANGES
     * @type {{pollAnswers}}
     */
    // Setting answers to state to reload the component with each vote
    state = {
        pollAnswers: [...pollAnswers]
    }

    // Handling user vote
    // Increments the votes count of answer when the user votes
    handleVote = voteAnswer => {
        const { pollAnswers } = this.state
        const newPollAnswers = pollAnswers.map(answer => {
            if (answer.option === voteAnswer) answer.votes++
            return answer
        })
        this.setState({
            pollAnswers: newPollAnswers
        })
    }

    /***
     * Function responsible for making a request to download the poll results to a text file.
     */
    handleDownloadPoll() {
        axios.get('http://localhost:8080/details')
            .then(function (response) {
                console.log(response);
            })
            .catch(function (error) {
                console.log(error);
            });
    }

    /**
     * Function responsible for sending a request for poll results.
     */
    handleViewPoll() {
        axios.get('http://localhost:8080/results')
            .then(function (response) {
                console.log(response);
            })
            .catch(function (error) {
                console.log(error);
            });
    }

    /**
     * Function responsible for sending a request when user votes in the poll.
     */
    handleVotePoll() {
        var getData = null // Needs to be implemented
        axios.post('http://localhost:8080/vote', getData)
            .then(function (response) {
                console.log(response);
            })
            .catch(function (error) {
                console.log(error);
            });
    }

    /***
     * Function responsible for rendering tags for use in react methods.
     * @returns {JSX.Element}
     */
    render() {
        return (
            <div className="App">
                <header>
                    <h1 id="name">Name of the Poll!</h1>
                    <div id="poll">
                        <Poll question={pollQuestion} answers={pollAnswers} onVote={this.handleVote}/>
                    </div><br/>
                    <button type="button" className="button-home" onClick={this.handleDownloadPoll}>Download Poll Results</button>
                </header>
            </div>
        );
    }
}

export default App;
