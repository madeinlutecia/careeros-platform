import json
from typing import Any

from app.core.config import settings


class LlmProvider:
    """Abstraction over LLM providers (OpenAI, Gemini, Claude, Ollama)."""

    async def complete(self, prompt: str) -> dict[str, Any]:
        if not settings.openai_api_key:
            return self._mock_response(prompt)
        try:
            from openai import AsyncOpenAI

            client = AsyncOpenAI(api_key=settings.openai_api_key)
            response = await client.chat.completions.create(
                model=settings.ai_model,
                messages=[
                    {"role": "system", "content": "Você é um assistente de carreira especializado. Responda em JSON."},
                    {"role": "user", "content": prompt},
                ],
                response_format={"type": "json_object"},
            )
            content = response.choices[0].message.content or "{}"
            return json.loads(content)
        except Exception:
            return self._mock_response(prompt)

    def _mock_response(self, prompt: str) -> dict[str, Any]:
        return {
            "message": "Mock response - configure OPENAI_API_KEY for real AI",
            "prompt_preview": prompt[:200],
            "resume_bullets": [
                "Aumentou cobertura de testes de 20% para 82% utilizando JUnit e Mockito",
                "Otimizou query crítica reduzindo tempo de execução de 9s para 800ms",
            ],
            "linkedin_update": "Sugestão de atualização para o LinkedIn baseada no registro diário.",
            "interview_points": ["Explique o impacto da otimização de query no negócio"],
            "achievements": ["Melhoria de performance em query crítica"],
        }
