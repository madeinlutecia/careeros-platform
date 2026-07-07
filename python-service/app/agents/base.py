from abc import ABC, abstractmethod
from typing import Any


class BaseAgent(ABC):
    name: str
    description: str

    @abstractmethod
    async def run(self, user_id: str, payload: dict[str, Any]) -> dict[str, Any]:
        pass
