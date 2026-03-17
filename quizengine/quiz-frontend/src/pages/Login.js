import React,{useState} from "react";
import API from "../services/api";
import {useNavigate} from "react-router-dom";

function Login(){

const [username,setUsername] = useState("");
const [password,setPassword] = useState("");

const navigate = useNavigate();

const loginUser = async () =>{

const res = await API.post("/auth/login",{
username,
password
});

if(res.data==="LOGIN SUCCESS"){

localStorage.setItem("username",username);

navigate("/dashboard");

}else{
alert("Invalid User");
}

};

return(

<div className="container">

<div className="card">

<h2>Quiz Login</h2>

<input
placeholder="Username"
onChange={(e)=>setUsername(e.target.value)}
/>

<input
type="password"
placeholder="Password"
onChange={(e)=>setPassword(e.target.value)}
/>

<button onClick={loginUser}>
Login
</button>

</div>

</div>

)

}

export default Login;