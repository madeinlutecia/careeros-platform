from fastapi import APIRouter, HTTPException
from pydantic import BaseModel, Field
from typing import Any

from app.agents.registry import AgentRegistry

router = APIRouter()
registry = AgentRegistry()


class AgentRequest(BaseModel):
    userId: str
    payload: dict[str, Any] = Field(default_factory=dict)


@router.post("/agents/{agent_name}")
async def invoke_agent(agent_name: str, request: AgentRequest):
    agent = registry.get(agent_name)
    if not agent:
        raise HTTPException(status_code=404, detail=f"Agent '{agent_name}' not found")
    result = await agent.run(request.userId, request.payload)
    return {"agent": agent_name, "result": result}
