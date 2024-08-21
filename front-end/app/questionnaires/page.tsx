import Link from "next/link";
import React from "react";

const QuestionnairesPage = async () => {
  const response = await fetch(
    `${process.env.NEXT_PUBLIC_API_URL}/api/questionnaires`
  );
  const questionnaires = await response.json();

  return (
    <section className="flex flex-col justify-center items-center min-h-screen">
      <h1 className="text-3xl mb-6 text-center">Select a Questionnaire</h1>
      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4 w-full max-w-screen-lg">
        {questionnaires.map((q) => (
          <Link href={`/questionnaires/${q.id}`} key={q.id}>
            <div className="flex flex-col justify-center items-center">
              <div className="block p-4 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500 rounded hover:bg-gray-300 text-center w-3/4">
                {q.name}
              </div>
            </div>
          </Link>
        ))}
      </div>
    </section>
  );
};

export default QuestionnairesPage;
