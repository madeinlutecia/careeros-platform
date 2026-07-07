from typing import Any

from app.agents.base import BaseAgent
from app.agents.diary_agent import DiaryAgent
from app.agents.profile_agent import (
    AtsAgent,
    InterviewAgent,
    JobAgent,
    LinkedInAgent,
    MentorAgent,
    ProfileAgent,
    ResumeAgent,
)


class AgentRegistry:
    def __init__(self):
        self._agents: dict[str, BaseAgent] = {}
        for agent in [
            ProfileAgent(),
            ResumeAgent(),
            LinkedInAgent(),
            JobAgent(),
            AtsAgent(),
            DiaryAgent(),
            InterviewAgent(),
            MentorAgent(),
        ]:
            self._agents[agent.name] = agent

    def get(self, name: str) -> BaseAgent | None:
        return self._agents.get(name)

    def list_agents(self) -> list[dict[str, str]]:
        return [{"name": a.name, "description": a.description} for a in self._agents.values()]
