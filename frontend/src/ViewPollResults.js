import React, {useState, useEffect} from "react";
import './ViewPollResults.css';
import Chart from "react-google-charts";
import axios from "axios";

/**
 * Functional component responsible for displaying the poll results in a PieChart from google charts.
 */
const ViewPollResults = (props) => {
    // const [results, setResults] = useState([]);
    // const [choices, setChoices] = useState([]);
    
    // useEffect(() => {
    //     handleResults();   
    // })
    /***
     * Function responsible for getting the poll result data needed for the PieChart.
     */
    // const handleResults = () => {
    //     axios.get('http://localhost:8080/results')
    //         .then(function (response) {
    //             console.log(response);

    //             let res = response.data;
    //             setResults(res);
    //             // convert object into array
    //             let choiceList = Object.keys(res).map((key) => [key, res[key]]);
    //             setChoices(choiceList);
    //         })
    //         .catch(function (error) {
    //             console.log(error);
    //         });
    // }

    /***
     * Function responsible for rendering tags for use in react methods.
     * @returns {JSX.Element}
     */
    return (
        <div className="piechart">
            <h2 key={props.title}>{props.title}</h2>
            <Chart
                width={'1500'}
                height={'300px'}
                chartType="PieChart"
                loader={<div>Loading Chart</div>}
                data={[
                    ['Choices', 'Number of votes'],
                    ...props.choicesCount
                ]}
                options={{
                    title: props.title,
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