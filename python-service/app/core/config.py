from pydantic_settings import BaseSettings


class Settings(BaseSettings):
    app_name: str = "CareerOS AI Service"
    api_prefix: str = "/api/v1"
    ai_provider: str = "openai"
    ai_model: str = "gpt-4o-mini"
    embedding_model: str = "text-embedding-3-small"
    openai_api_key: str = ""
    backend_url: str = "http://localhost:8080"

    class Config:
        env_file = ".env"


settings = Settings()
