from typing import Any

from app.agents.base import BaseAgent
from app.llm.provider import LlmProvider
from app.prompts.diary import DIARY_TRANSFORM_PROMPT


class DiaryAgent(BaseAgent):
    name = "diary"
    description = "Transforma registros do diário em bullets de currículo, LinkedIn e material para entrevistas"

    def __init__(self):
        self.llm = LlmProvider()

    async def run(self, user_id: str, payload: dict[str, Any]) -> dict[str, Any]:
        entry = payload.get("entry", {})
        prompt = DIARY_TRANSFORM_PROMPT.format(
            what_i_did=entry.get("whatIDid", ""),
            problems=entry.get("problemsSolved", ""),
            technologies=", ".join(entry.get("technologies", [])),
            lessons=entry.get("lessonsLearned", ""),
        )
        response = await self.llm.complete(prompt)
        return {
            "resumeBullets": response.get("resume_bullets", []),
            "linkedinUpdate": response.get("linkedin_update", ""),
            "interviewTalkingPoints": response.get("interview_points", []),
            "achievementSuggestions": response.get("achievements", []),
        }
