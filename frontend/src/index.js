import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import reportWebVitals from './reportWebVitals';
import history from "./history";
import {Route, Router, Switch} from "react-router";
import PollManager from "./PollManager";
import CreatePoll from "./CreatePoll";
import ManagerLogin from "./ManagerLogin";
import ViewPollResults from "./ViewPollResults";
import Home from "./Home";
import UpdatePoll from "./UpdatePoll";

// Render the nav bar which will be displayed on the top of every page.
// Keep track of all routes to pages so when using "Link", it will find the specified page and display it.
ReactDOM.render(
  <React.StrictMode>
      <Router history={history}>
          <div id="body">
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
                  <Route path="/updatepoll">
                      <UpdatePoll />
                  </Route>
                  <Route path="/viewpollresults">
                      <ViewPollResults />
                  </Route>
              </Switch>
          </div>
      </Router>
  </React.StrictMode>,
  document.getElementById('root')
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();