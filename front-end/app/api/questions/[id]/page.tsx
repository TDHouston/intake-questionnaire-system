"use client";
import { useParams, useRouter } from "next/navigation";
import React, { useEffect, useState } from "react";

const QuestionsPage = () => {
  const { id } = useParams();
  const router = useRouter();
  const [questions, setQuestions] = useState([]);
  const [answers, setAnswers] = useState({});

  useEffect(() => {
    const fetchQuestions = async () => {
      try {
        const response = await fetch(
          `${process.env.NEXT_PUBLIC_API_URL}/api/questions/${id}`
        );
        const data = await response.json();

        setQuestions(data);

        const initialAnswers = {};
        data.forEach((question) => {
          const parsedQuestionText = JSON.parse(question.questionText);

          if (parsedQuestionText.answer) {
            initialAnswers[question.id] = parsedQuestionText.answer;
          }
        });
        setAnswers(initialAnswers);
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

    if (Object.keys(answers).length !== questions.length) {
      alert("Please answer all questions before submitting.");
      return;
    }

    console.log("Answers to be submitted:", answers);

    const payload = {
      questionnaireId: id,
      answers,
    };

    console.log("Payload being sent:", payload);

    try {
      const response = await fetch(
        `${process.env.NEXT_PUBLIC_API_URL}/api/answers`,
        {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify(payload),
        }
      );

      if (response.ok) {
        console.log("Answers submitted successfully");
        alert("Thank you! Your answers have been submitted.");
        router.push("/questionnaires");
      } else {
        console.error("Failed to submit answers", await response.text());
        alert("Failed to submit answers. Please try again.");
      }
    } catch (error) {
      console.error("An error occurred while submitting answers:", error);
      alert("An error occurred. Please try again later.");
    }
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
                      checked={answers[question.id]?.includes(option) || false}
                      onChange={(e) =>
                        handleInputChange(
                          question.id,
                          e.target.checked
                            ? [...(answers[question.id] || []), option]
                            : answers[question.id].filter((o) => o !== option)
                        )
                      }
                    />
                    <label className="ml-2">{option}</label>
                  </div>
                ))
              ) : parsedQuestionText && parsedQuestionText.type === "input" ? (
                <input
                  type="text"
                  value={answers[question.id] || ""}
                  onChange={(e) =>
                    handleInputChange(question.id, e.target.value)
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
