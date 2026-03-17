import axios from "axios";

const API = axios.create({
  baseURL: "http://localhost:8095"
});

export default API;