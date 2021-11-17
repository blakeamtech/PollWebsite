import React, {Component, useState} from "react";

// Responsible for displaying the waiting page when there is no poll running/released.
const WaitingPage = (props) => {
    /**
     * Responsible for rendering tags for use in react methods.
     * @returns {JSX.Element}
     */
    return (
        <div>
            { 
                (props.pollStatus === "none" || props.pollStatus === "closed") && <h2 id="waiting-page">Please search for a poll.</h2>
            }
        </div>
    )
}

export default WaitingPage;