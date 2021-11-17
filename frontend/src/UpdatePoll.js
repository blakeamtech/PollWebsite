import React, {Component, useEffect, useState} from "react";
import './UpdatePoll.css';
import axios from "axios";
import Header from "./Header";

/**
 * Functional component responsible for displaying and handling Poll Manager requests.
 */
const UpdatePoll = () => {
    // Keep track of poll information.
    const [newQty, setNewQty] = useState(3);
    const [choices, setChoices] = useState([]);
    const [title, setTitle] = useState("");
    const [question, setQuestion] = useState("");

    // Updates quantity of choices for the poll to be created.
    const updateQty = (e) => {
        e.preventDefault();
        let qty = parseInt(e.target.choiceQty.value);
        if (!Number.isInteger(qty) || qty > 15 || qty < 1){
            qty = 3
        }
        setNewQty(parseInt(qty));
    }

    // Responsible for making a request to update a poll.
    const handleUpdate = (obj) => {
        axios.put('http://localhost:8080/update', obj)
            .then(function (response) {
                console.log(response);
                alert("Poll Updated Successfully.");
            })
            .catch(function (error) {
                console.log(error);
                alert("Poll Update Failed.");
            });
    }

    // Run when the page loads, will get the data of the currently running poll.
    const getPollState = () => {
        // retrieve backend poll state and set pollState
        axios.get('http://localhost:8080/state')
            .then(function (response) {
                setNewQty(parseInt(response.data.choices.length));
                setChoices(response.data.choices);
                setTitle(response.data.title);
                setQuestion(response.data.question);
            })
            .catch(function (error) {
                console.log(error);
            });
    }

    // On page load, get the poll state so the poll information will be already filled.
    useEffect(() => {
        getPollState();
    }, []);

    // Responsible for setting up the title, question and choices to be sent to the servlet via /update request.
    const updatePoll = (e) => {
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
        handleUpdate(obj);
        console.log(obj);
    }

    /***
     * Function responsible for rendering tags for use in react methods.
     * @returns {JSX.Element}
     */
    return (
        <div>
            <Header />        
            <div className="UpdatePoll">
                <form onSubmit={updateQty}>
                    <label htmlFor="choice-qty">Input number of choices: </label>
                    <input type="number" min="1" step="1" name="choiceQty" id="choiceQty"/>
                    <button type="submit">Update</button>
                </form>
                <br/>

                <form onSubmit={updatePoll}>
                    <label htmlFor="name">Name of the Poll:</label><br/>
                    <input type="text" id="name" name="name" defaultValue={title}/><br/><br/>
                    <label htmlFor="question">Poll Question:</label><br/>
                    <input type="text" id="question" name="question" defaultValue={question}/><br/><br/>
                    {
                        [...Array(newQty)].map((e, i) =>
                            <label key={i} htmlFor="choice1">Choice {i+1}:<br/>
                                <input type="text" id={"choice" + (i+1)} name="choice" defaultValue={choices[i]}/><br/><br/>
                            </label>
                        )
                    }
                    <input type="submit" className="button-update" value="Submit"/>
                </form>
            </div>
        </div>
    );
}

export default UpdatePoll;
