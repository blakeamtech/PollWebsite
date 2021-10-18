import React, {useState} from "react";
import './ViewPollResults.css';
import Chart from "react-google-charts";
import axios from "axios";

/**
 * Functional component responsible for displaying the poll results in a PieChart from google charts.
 */
const ViewPollResults = () => {
    let results = null;

    /***
     * Function responsible for getting the poll result data needed for the PieChart.
     */
    const handleResults = (obj) => {
        axios.get('http://localhost:8080/results')
            .then(function (response) {
                console.log(response);
                results = response;
            })
            .catch(function (error) {
                console.log(error);
            });
    }

    /***
     * Function responsible for rendering tags for use in react methods.
     * @returns {JSX.Element}
     */
    return (
        <div className="piechart">
            <Chart
                width={'1500'}
                height={'300px'}
                chartType="PieChart"
                loader={<div>Loading Chart</div>}
                data={[
                    ['Task', 'Hours per Day'],
                    ['Work', 11],
                    ['Eat', 2],
                    ['Commute', 2],
                    ['Watch TV', 2],
                    ['Sleep', 7],
                ]}
                options={{
                    title: 'My Daily Activities',
                    width: 1500,
                    height: 850,
                    is3D: true
                }}
                rootProps={{ 'data-testid': '1' }}
            />
        </div>
    );
}

export default ViewPollResults;