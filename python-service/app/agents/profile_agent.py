from typing import Any

from app.agents.base import BaseAgent
from app.llm.provider import LlmProvider


class ProfileAgent(BaseAgent):
    name = "profile"
    description = "Conhece toda a carreira do usuário e sugere melhorias no perfil mestre"

    def __init__(self):
        self.llm = LlmProvider()

    async def run(self, user_id: str, payload: dict[str, Any]) -> dict[str, Any]:
        profile = payload.get("profile", {})
        prompt = (
            "Analise o perfil profissional e sugira melhorias objetivas.\n"
            f"Perfil: {profile}"
        )
        response = await self.llm.complete(prompt)
        return {"suggestions": response}


class ResumeAgent(BaseAgent):
    name = "resume"
    description = "Gera currículos personalizados por vaga"

    def __init__(self):
        self.llm = LlmProvider()

    async def run(self, user_id: str, payload: dict[str, Any]) -> dict[str, Any]:
        return {
            "resume": await self.llm.complete(
                f"Gere um currículo para a vaga: {payload.get('job', {})} "
                f"usando o perfil: {payload.get('profile', {})}"
            )
        }


class LinkedInAgent(BaseAgent):
    name = "linkedin"
    description = "Sugere headline, sobre, experiências e competências com SEO para LinkedIn"

    def __init__(self):
        self.llm = LlmProvider()

    async def run(self, user_id: str, payload: dict[str, Any]) -> dict[str, Any]:
        return {"suggestions": await self.llm.complete(
            f"Sugira melhorias de LinkedIn (não publique): {payload}"
        )}


class JobAgent(BaseAgent):
    name = "jobs"
    description = "Analisa vagas e calcula compatibilidade com o perfil mestre"

    def __init__(self):
        self.llm = LlmProvider()

    async def run(self, user_id: str, payload: dict[str, Any]) -> dict[str, Any]:
        return {
            "compatibilityScore": 0.0,
            "analysis": await self.llm.complete(
                f"Analise a vaga {payload.get('job', {})} vs perfil {payload.get('profile', {})}"
            ),
        }


class AtsAgent(BaseAgent):
    name = "ats"
    description = "Simulador ATS - calcula aderência, keywords faltantes e sugestões"

    def __init__(self):
        self.llm = LlmProvider()

    async def run(self, user_id: str, payload: dict[str, Any]) -> dict[str, Any]:
        return {
            "atsScore": 0.0,
            "missingKeywords": [],
            "suggestions": await self.llm.complete(
                f"Simule ATS para currículo vs vaga: {payload}"
            ),
        }


class InterviewAgent(BaseAgent):
    name = "interview"
    description = "Simulador de entrevistas (RH, técnico, tech lead, arquiteto, system design)"

    def __init__(self):
        self.llm = LlmProvider()

    async def run(self, user_id: str, payload: dict[str, Any]) -> dict[str, Any]:
        mode = payload.get("mode", "technical")
        return {
            "questions": [],
            "report": await self.llm.complete(
                f"Simule entrevista modo {mode} para: {payload}"
            ),
        }


class MentorAgent(BaseAgent):
    name = "mentor"
    description = "Agente mentor com memória persistente da carreira"

    def __init__(self):
        self.llm = LlmProvider()

    async def run(self, user_id: str, payload: dict[str, Any]) -> dict[str, Any]:
        return {
            "advice": await self.llm.complete(
                f"Como mentor de carreira, sugira melhorias: {payload}"
            )
        }
