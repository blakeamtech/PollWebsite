import React, {Component} from "react";
import './CreatePoll.css';
import axios from "axios";
import {Link} from "react-router-dom";

/**
 * Class responsible for displaying and handling Poll Manager requests.
 */
class CreatePoll extends Component {
    /**
     * Constructor which allows functions to use the "this" keyword.     *
     * @param props
     */
    constructor(props) {
        super(props);
    }

    /***
     * Function responsible for rendering tags for use in react methods.
     * @returns {JSX.Element}
     */
    render() {
        return (
            <div className="CreatePoll">
                <form method="post" action="http://localhost:8080/create">
                    <label htmlFor="name">Name of the Poll:</label><br/>
                    <input type="text" id="name" name="name"/><br/><br/>
                    <label htmlFor="question">Poll Question:</label><br/>
                    <input type="text" id="question" name="question"/><br/><br/>

                    <label htmlFor="choice1">Choice 1:</label><br/>
                    <input type="text" id="choice1" name="choice1"/><br/><br/>
                    <label htmlFor="choice2">Choice 2:</label><br/>
                    <input type="text" id="choice2" name="choice2"/><br/><br/>
                    <label htmlFor="choice3">Choice 3:</label><br/>
                    <input type="text" id="choice3" name="choice3"/><br/><br/>
                    <label htmlFor="choice4">Choice 4:</label><br/>
                    <input type="text" id="choice4" name="choice4"/><br/><br/>
                    <input type="submit" className="button-create" value="Submit"/>
                </form>
            </div>
        );
    }
}

export default CreatePoll;