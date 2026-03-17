import React,{useState} from "react";
import API from "../services/api";
import {useNavigate} from "react-router-dom";

function Register(){

const [username,setUsername] = useState("");
const [password,setPassword] = useState("");

const navigate = useNavigate();

const registerUser = async ()=>{

const res = await API.post("/auth/register",{
username,
password
});

alert(res.data);

navigate("/");

};

return(

<div className="container">

<div className="card">

<h2>Create Account</h2>

<input
placeholder="Username"
onChange={(e)=>setUsername(e.target.value)}
/>

<input
type="password"
placeholder="Password"
onChange={(e)=>setPassword(e.target.value)}
/>

<button onClick={registerUser}>
Register
</button>

</div>

</div>

)

}

export default Register;