import './App.css';
import axios from 'axios';
import React, {Component} from "react";

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
                    <a href="pollmanager.xhtml">Go To Poll Manager</a><br/>
                    <button type="button" onClick={this.handleViewPoll}>View Poll Results</button><br/>
                    <button type="button" onClick={this.handleDownloadPoll}>Download Poll Results</button>
                </header>
            </div>
        );
    }
}

export default App;
