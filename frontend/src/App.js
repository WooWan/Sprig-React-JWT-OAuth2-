
import './App.css';
import SocialLogin from "./components/SocialLogin";
import {Route, Switch} from "react-router-dom";
import React, {useEffect, useState} from "react";
import OAuth2RedirectHandler from "./components/OAuth2RedirectHandler";
import UserList from "./components/UserList";



function App({server}) {
    const [users, setUsers] = useState({});

  return (
    <div className="App">
        <SocialLogin/>
        <div className="app-body">
            <Switch>
                <Route path="/oauth2/redirect" component={OAuth2RedirectHandler}/>
                <Route path = "/user" render ={ props =>
                    <UserList {...props} server = {server} />
                } />
            </Switch>
        </div>
    </div>
  );
}

export default App;
