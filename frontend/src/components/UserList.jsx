import React, {useState} from 'react';

const User = ({server}) => {
    const [user, setUser] = useState();
    const token = localStorage.getItem("ACCESS_TOKEN")
    console.log(token);
    const getUser = () =>{
        server.getUserList().then(resp =>
            setUser(resp)
        );
    }
    return (
        <div className="user">
            <h1>user page</h1>
            <button onClick={getUser}></button>
            {
                
            }
            <h1>{user}</h1>
        </div>
    )
}

export default User;