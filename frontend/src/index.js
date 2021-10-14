import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import {Link} from "react-router-dom";
import reportWebVitals from './reportWebVitals';
import history from "./history";
import {Route, Router, Switch} from "react-router";
import PollManager from "./PollManager";

ReactDOM.render(
  <React.StrictMode>
      <Router history={history}>
          <div id="nav">
              <Link to="/">Home</Link><br/>
              <Link to="/pollmanager">Poll Manager</Link>
          </div>
          <hr />
          <Switch>
            <Route exact path="/">
                  <App />
              </Route>
              <Route path="/pollmanager">
                  <PollManager />
              </Route>
          </Switch>
      </Router>
  </React.StrictMode>,
  document.getElementById('root')
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();