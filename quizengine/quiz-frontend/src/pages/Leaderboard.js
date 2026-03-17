import React, { useEffect, useState } from "react";
import axios from "axios";
import { useParams } from "react-router-dom";
import "./Leaderboard.css";

function Leaderboard() {

  const { id } = useParams(); // get quiz id from URL
  const [leaders, setLeaders] = useState([]);

  useEffect(() => {

    axios
      .get("http://localhost:8095/quiz/leaderboard/" + id)
      .then((res) => {
        setLeaders(res.data);
      });

  }, [id]);

  return (
    <div className="leaderboard-container">

      <h1>🏆 Leaderboard</h1>

      {leaders.map((user, index) => (
        <div key={index} className="leader-card">

          <div className="rank">
            #{index + 1}
          </div>

          <div className="username">
            {user.username}
          </div>

          <div className="score">
            {user.score}
          </div>

        </div>
      ))}

    </div>
  );
}

export default Leaderboard;