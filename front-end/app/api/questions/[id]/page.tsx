"use client";
import { useParams } from "next/navigation";
import React, { useEffect, useState } from "react";

const QuestionsPage = () => {
  const { id } = useParams();
  const [questions, setQuestions] = useState([]);
  const [answers, setAnswers] = useState({});

  useEffect(() => {
    const fetchQuestions = async () => {
      try {
        const res = await fetch(
          `${process.env.NEXT_PUBLIC_API_URL}/api/questions/${id}`
        );
        const data = await res.json();
        console.log("Fetched data:", data);

        setQuestions(data);
      } catch (error) {
        console.error("Failed to fetch questions:", error);
      }
    };

    fetchQuestions();
  }, [id]);

  const handleInputChange = (questionId, value) => {
    setAnswers({ ...answers, [questionId]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    console.log("Submitted answers:", answers);
  };

  return (
    <div>
      <h1>Questionnaire ID: {id}</h1>
      <form onSubmit={handleSubmit}>
        {questions.map((question, index) => {
          let parsedQuestionText;
          try {
            parsedQuestionText = JSON.parse(question.questionText);
          } catch (error) {
            console.error("Error parsing question text:", error);
            parsedQuestionText = null;
          }

          return (
            <div key={index} className="mb-4">
              <label className="block text-gray-700 mb-2">
                {parsedQuestionText
                  ? parsedQuestionText.question
                  : "Invalid question format"}
              </label>
              {parsedQuestionText && parsedQuestionText.type === "mcq" ? (
                parsedQuestionText.options.map((option, idx) => (
                  <div key={idx} className="mb-2">
                    <input
                      type="checkbox"
                      value={option}
                      onChange={(e) =>
                        handleInputChange(
                          question.questionText,
                          e.target.checked
                            ? [
                                ...(answers[question.questionText] || []),
                                option,
                              ]
                            : answers[question.questionText].filter(
                                (o) => o !== option
                              )
                        )
                      }
                    />
                    <label className="ml-2">{option}</label>
                  </div>
                ))
              ) : parsedQuestionText && parsedQuestionText.type === "input" ? (
                <input
                  type="text"
                  value={answers[question.questionText] || ""}
                  onChange={(e) =>
                    handleInputChange(question.questionText, e.target.value)
                  }
                  className="w-full p-2 border rounded"
                />
              ) : null}
            </div>
          );
        })}
        <button
          type="submit"
          className="w-full bg-blue-500 text-white p-2 rounded mt-4"
        >
          Submit
        </button>
      </form>
    </div>
  );
};

export default QuestionsPage;
