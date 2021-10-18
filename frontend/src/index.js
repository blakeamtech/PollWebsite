import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import {Link} from "react-router-dom";
import reportWebVitals from './reportWebVitals';
import history from "./history";
import {Route, Router, Switch} from "react-router";
import PollManager from "./PollManager";
import CreatePoll from "./CreatePoll";
import ManagerLogin from "./ManagerLogin";
import Home from "./Home";

ReactDOM.render(
  <React.StrictMode>
      <Router history={history}>
          <div id="nav">
              <Link to="/">Home</Link><br/>
              <Link to="/pollmanagerlogin">Poll Manager</Link><br/>
          </div>
          <hr />
          <Switch>
            <Route exact path="/">
                  <Home />
              </Route>
              <Route path="/pollmanagerlogin">
                  <ManagerLogin />
              </Route>
              <Route path="/pollmanager">
                  <PollManager />
              </Route>
              <Route path="/createpoll">
                  <CreatePoll />
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