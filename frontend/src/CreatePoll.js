import React, {Component, useState} from "react";
import './CreatePoll.css';
import axios from "axios";
import Header from "./Header";

/**
 * Functional component responsible for displaying and handling Poll Manager requests.
 */
const CreatePoll = () => {
    const [newQty, setNewQty] = useState(3); // Default amount of choices is 3.

    // Update the amount of choices the poll manager wants to enter for the poll.
    const updateQty = (e) => {
        e.preventDefault();
        let qty = parseInt(e.target.choiceQty.value);
        if (!Number.isInteger(qty) || qty > 15 || qty < 1) {
            qty = 3
        }
        setNewQty(parseInt(qty));
    }

    // Responsible for making a request to create a new poll.
    const handleCreate = (obj) => {
        axios.post('http://localhost:8080/create', obj).then(function (response) {
            console.log(response);
            alert("Poll Created Successfully.");
        }).catch(function (error) {
            console.log(error);
            alert("Poll Creation Failed.");
        });
    }

    // Responsible for setting up the title, question and choices to be sent to the servlet via /create request.
    const createPoll = (e) => {
        e.preventDefault();
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
     * Responsible for rendering tags for use in react methods.
     * @returns {JSX.Element}
     */
    return (
        <div>
            <Header />
            <div className="CreatePoll">
                <form onSubmit={updateQty}>
                    <label htmlFor="choice-qty">Input number of choices: </label>
                    <input type="number" min="1" step="1" name="choiceQty" id="choiceQty"/>
                    <button type="submit">Update</button>
                </form>
                <br/>

                <form onSubmit={createPoll}>
                    <label htmlFor="name">Name of the Poll:</label><br/>
                    <input type="text" id="name" name="name" required/><br/><br/>
                    <label htmlFor="question">Poll Question:</label><br/>
                    <input type="text" id="question" name="question" required/><br/><br/>
                    {
                        [...Array(newQty)].map((e, i) =>
                                <label key={i} htmlFor="choice1">Choice {i+1}:<br/>
                                <input type="text" id={"choice" + (i+1)} name="choice" required/><br/><br/>
                                </label>
                        )
                    }
                    <input type="submit" className="button-create" value="Submit"/>
                </form>
            </div>
        </div>
    );
}

export default CreatePoll;
