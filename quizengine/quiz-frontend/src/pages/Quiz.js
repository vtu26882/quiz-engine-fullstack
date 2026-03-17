import React,{useState,useEffect} from "react";
import { useParams,useNavigate } from "react-router-dom";
import API from "../services/api";
import axios from "axios";
import { useCallback } from "react";
function Quiz(){

  const {id} = useParams();
  const navigate = useNavigate();

  const [questions,setQuestions] = useState([]);
  const [responses,setResponses] = useState([]);
  const [timeLeft, setTimeLeft] = useState(300);
  const minutes = Math.floor(timeLeft / 60);
const seconds = timeLeft % 60;

  

  useEffect(()=>{

    const loadQuestions = async () => {

      const res = await API.get("/quiz/get/"+id);

      setQuestions(res.data);

    }

    loadQuestions();

  },[id]);

  const selectOption = (qid, option) => {

  const updatedResponses = responses.filter(r => r.id !== qid);

  updatedResponses.push({
    id: qid,
    response: option
  });

  setResponses(updatedResponses);

}

 const submitQuiz = useCallback(async () => {

  const username = localStorage.getItem("username");

  const res = await axios.post(
    "http://localhost:8095/quiz/submit/" + id + "?username=" + username,
    responses
  );

  alert("Score: " + res.data.score + " / " + res.data.total);

  // Open certificate automatically
  window.open(
    "http://localhost:8095/certificate/download" +
      "?username=" + username +
      "&quizTitle=JavaQuiz" +
      "&score=" + res.data.score +
      "&total=" + res.data.total
  );
navigate("/leaderboard/"+id);
},[id, responses, navigate]);

useEffect(() => {

  if (timeLeft === 0) {
    submitQuiz();
    return;
  }

  const timer = setTimeout(() => {
    setTimeLeft(timeLeft - 1);
  }, 1000);

  return () => clearTimeout(timer);

}, [timeLeft, submitQuiz]);


  return(
    <div>
      <h2 className="timer">
⏱ Time Remaining: {minutes}:{seconds < 10 ? "0" : ""}{seconds}
</h2>

      <h2>Quiz</h2>

      {questions.map(q => (

        <div key={q.id}>

          <h3>{q.questionTitle}</h3>

          <button 
          onClick={()=>selectOption(q.id,q.option1)}
          >
            {q.option1}
          </button>

          <button 
          onClick={()=>selectOption(q.id,q.option2)}
          >
            {q.option2}
          </button>

          <button 
          onClick={()=>selectOption(q.id,q.option3)}
          >
            {q.option3}
          </button>

          <button 
          onClick={()=>selectOption(q.id,q.option4)}
          >
            {q.option4}
          </button>

        </div>

      ))}

      <br/>

      <button onClick={submitQuiz}>
        Submit Quiz
      </button>

    </div>
  )
}

export default Quiz;