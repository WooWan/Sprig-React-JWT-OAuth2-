
import './App.css';
import SocialLogin from "./components/SocialLogin";
import {Route, Switch} from "react-router-dom";
import React from "react";
import OAuth2RedirectHandler from "./components/OAuth2RedirectHandler";

function App() {
  return (
    <div className="App">
      <SocialLogin>

      </SocialLogin>
    <div className="app-body">
        <Switch>
            <Route path="/oauth2/redirect" component={OAuth2RedirectHandler}></Route>
        </Switch>
    </div>
    </div>
  );
}

export default App;
