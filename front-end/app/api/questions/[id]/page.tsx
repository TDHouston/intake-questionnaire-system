"use client";
import { useParams } from "next/navigation";
import React, { useEffect, useState } from "react";

const QuestionsPage = () => {
    const { id } = useParams();
    const [questions, setQuestions] = useState([]);
  
    useEffect(() => {
        const fetchQuestions = async () => {
          try {
            const res = await fetch(`${process.env.NEXT_PUBLIC_API_URL}/api/questions/${id}`);
            const data = await res.json();
            console.log("API Response:", data);  // Add this line to inspect the data
            setQuestions(data);
          } catch (error) {
            console.error("Failed to fetch questions:", error);
          }
        };
      
        fetchQuestions();
      }, [id]);
      
  
    return (
      <div>
        <h1>Questionnaire ID: {id}</h1>
        {/* Render questions */}
        {questions.map((question) => (
          <div key={question.id}>{question.questionText}</div>
        ))}
      </div>
    );
};

export default QuestionsPage;
