import React from 'react';
import qs from "qs";
import {Redirect} from "react-router-dom";

const OAuth2RedirectHandler = ({location}) => {
    const query  = qs.parse(location.search ,{
        ignoreQueryPrefix: true
    })
    const refreshToken = query.refreshToken
    const token = query.token
    if (token) {
        localStorage.setItem("ACCESS_TOKEN", token)
        localStorage.setItem("REFRESH_TOKEN", refreshToken)
        return <Redirect to= "/user" />
    }

    return(

        <div className="oauth2RedirectHandler">

        </div>
    );
}

export default OAuth2RedirectHandler;