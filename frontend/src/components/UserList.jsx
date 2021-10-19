import React, {useEffect, useState} from 'react';
import User from "./User";

const UserList = ({server}) => {
    const [users, setUsers] = useState({});
    const token = localStorage.getItem("ACCESS_TOKEN")

    useEffect( () =>{
        server.getUserList().then(resp =>{
                setUsers(resp.data)
            }
        );
    },[server])
    // console.log(`users :${users} `)
    console.log(token);

    return (
        <div className="user">
            <h1>user page</h1>
            {Object.keys(users).map(key => (
                <User user={users[key]} />
            ))}
        </div>
    )
}

export default UserList;