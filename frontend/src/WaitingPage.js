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
                (props.pollStatus === "closed") && <h2 id="waiting-page">The poll you've searched for has been closed.</h2>
            }
            {
                (props.pollStatus === "created") && <h2 id="waiting-page">The poll you've searched for has not been run yet.</h2>
            }

            <h2 id="waiting-page">Please search for a poll.</h2>
        </div>
    )
}

export default WaitingPage;