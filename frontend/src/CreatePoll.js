import React, {Component, useState} from "react";
import './CreatePoll.css';
import axios from "axios";
import {Link} from "react-router-dom";
import PollManager from "./PollManager.js";
import { useLocation } from 'react-router-dom'

/**
 * Functional component responsible for displaying and handling Poll Manager requests.
 */
const CreatePoll = () => {
    const location = useLocation()
    const create = location.state
    
    const [newQty, setNewQty] = useState(3);

    // Updates quantity of choices for the poll to be created.
    const updateQty = (e) => {
        e.preventDefault();
        let qty = parseInt(e.target.choiceQty.value);
        if (!Number.isInteger(qty) || qty > 15 || qty < 1){
            qty = 3     
        }
        setNewQty(parseInt(qty));
    }

    /***
     * Function responsible for making a request to create a new poll.
     */
    const handleCreate = (obj) => {
            axios.post('http://localhost:8080/create', obj)
                .then(function (response) {
                    console.log(response);
                })
                .catch(function (error) {
                    console.log(error);
                });
        }

    const createPoll = (e) => {
        e.preventDefault();
        console.log(create);
        let elements = e.target.elements;
        let obj = {};
        obj["choices"] = [];

        for(var i = 0 ; i < elements.length ; i++){
            var item = elements.item(i);
            if (item.name === "choice" && item.value) {
                obj["choices"].push(item.value)
            }
            else if (item.name === "name" || item.name === "question") {
                obj[item.name] = item.value;
            }
        }
        handleCreate(obj);
        console.log(obj);
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

            <form onSubmit={createPoll}>
                <label htmlFor="name">Name of the Poll:</label><br/>
                <input type="text" id="name" name="name"/><br/><br/>
                <label htmlFor="question">Poll Question:</label><br/>
                <input type="text" id="question" name="question"/><br/><br/>
                {
                    [...Array(newQty)].map((e, i) => 
                            <label key={i} htmlFor="choice1">Choice {i+1}:<br/>
                            <input type="text" id={"choice" + (i+1)} name="choice"/><br/><br/>
                            </label>
                    )
                }
                <input type="submit" className="button-create" value="Submit"/>
            </form>
        </div>
    );
}

export default CreatePoll;