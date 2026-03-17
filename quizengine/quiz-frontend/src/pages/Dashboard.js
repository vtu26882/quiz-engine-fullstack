import React from "react";
import {useNavigate} from "react-router-dom";

function Dashboard(){

const navigate = useNavigate();

return(

<div className="container">

<div className="card">

<h2>Select Quiz</h2>

<button onClick={()=>navigate("/quiz/3")}>
Java Quiz
</button>

<button onClick={()=>navigate("/quiz/5")}>
Python Quiz
</button>

<button onClick={()=>navigate("/quiz/2")}>
Programming Quiz
</button>

<button onClick={()=>navigate("/quiz/4")}>
Basic Quiz
</button>

</div>

</div>

)

}

export default Dashboard;