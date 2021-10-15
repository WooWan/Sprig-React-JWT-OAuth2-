import React from 'react';

const User = ({user}) => {

    console.log(user)
    console.log("userd임")
    const username = user.username;
    const email = user.email
    return(
        <div className="user">
            <h3>username: {username}</h3>
            <h3>email :{email}</h3>
        </div>
    )
}

export default User;