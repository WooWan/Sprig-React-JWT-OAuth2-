
import './App.css';
import SocialLogin from "./components/SocialLogin";
import {Route, Switch} from "react-router-dom";
import React, {useEffect, useState} from "react";
import OAuth2RedirectHandler from "./components/OAuth2RedirectHandler";
import User from "./components/UserList";
import UserList from "./components/UserList";



function App({server}) {
    console.log(server);
    const [users, setUsers] = useState({});
    useEffect( () =>{
        server.getUserList().then(resp =>{
                setUsers(resp.data)
            }
        );
    },[server])
  return (
    <div className="App">
        <SocialLogin/>
        <div className="app-body">
            <Switch>
                <Route path="/oauth2/redirect" component={OAuth2RedirectHandler}/>
                <Route path = "/user" render ={ props =>
                    <UserList {...props} users = {users} />
                } />
            </Switch>
        </div>
    </div>
  );
}

export default App;
