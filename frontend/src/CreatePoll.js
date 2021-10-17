import React, {Component, useState} from "react";
import './CreatePoll.css';
import axios from "axios";
import {Link} from "react-router-dom";

/**
 * Functional component responsible for displaying and handling Poll Manager requests.
 */
const CreatePoll = () => {
    
    const [newQty, setNewQty] = useState(3);

    // Updates quantity of choices for the poll to be created.
    const updateQty = (e) => {
        e.preventDefault();
        let qty = parseInt(e.target.choiceQty.value);
        if (!Number.isInteger(qty) || qty > 15 || qty < 1){
            qty = 3     
        }
        setNewQty(parseInt(qty));
        console.log(newQty);
    }

    /***
     * Function responsible for rendering tags for use in react methods.
     * @returns {JSX.Element}
     */
    return (
        <div className="CreatePoll">
            <form onSubmit={updateQty}>
                <label htmlFor="choice-qty">Input number of choices: </label>
                <input type="number" min="1" step="1" name="choiceQty" id="choiceQty"/>
                <button type="submit">Update</button>
            </form>
            <br/>

            <form method="post" action="http://localhost:8080/create">
                <label htmlFor="name">Name of the Poll:</label><br/>
                <input type="text" id="name" name="name"/><br/><br/>
                <label htmlFor="question">Poll Question:</label><br/>
                <input type="text" id="question" name="question"/><br/><br/>
                {
                    [...Array(newQty)].map((e, i) => 
                            <label htmlFor="choice1">Choice {i+1}:<br/>
                            <input type="text" id={"choice" + (i+1)} name={"choice" + (i+1)}/><br/><br/>
                            </label>
                    )
                }
                <input type="submit" className="button-create" value="Submit"/>
            </form>
        </div>
    );
}

export default CreatePoll;