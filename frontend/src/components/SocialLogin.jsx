import React from 'react';
import {GOOGLE_AUTH_URL} from "../constants/oauth";


const SocialLogin = (props) => {
    return (
        <div className="social_login">
            <a className="btn btn-block social-btn google" href={GOOGLE_AUTH_URL} >
                <img src="img/google-logo.png" alt="Google" /> Log in with Google</a>
        </div>
    )
};

export default SocialLogin;