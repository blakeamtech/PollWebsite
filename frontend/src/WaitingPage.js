import React, {Component, useState} from "react";

// Responsible for displaying the waiting page when there is no poll running/released.
const WaitingPage = () => {
    /**
     * Responsible for rendering tags for use in react methods.
     * @returns {JSX.Element}
     */
    return (
        <div>
            <h2 id="waiting-page">Please wait for the poll.</h2>
        </div>
    )
}

export default WaitingPage;